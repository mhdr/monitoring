package net.pupli.core.configs;

import net.pupli.core.libs.ConfigFile;
import net.pupli.core.services.RabbitMessageListener;
import net.pupli.core.services.RabbitMessageListenerWithQos;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitConfiguration {

    @Bean("connectionFactory")
    public CachingConnectionFactory connectionFactory() {
        // configure to connect with credential
        ConfigFile configFile = ConfigFile.getInstance();
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(configFile.getRabbitmqHost());
        connectionFactory.setUsername(configFile.getRabbitmqUserName());
        connectionFactory.setPassword(configFile.getRabbitmqPassword());
        return connectionFactory;
    }

    @Bean("amqpAdmin")
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        // configure template to use retry policy
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        template.setRetryTemplate(retryTemplate);
        return template;
    }

    @Bean("MonitoringV5_Queue3")
    public Queue MonitoringV5_Queue3() {
        // declare queue1
        return new Queue("MonitoringV5_Queue3", false, false, false);
    }

    @Bean("MonitoringV5_Queue4")
    public Queue MonitoringV5_Queue4() {
        // declare queue2
        return new Queue("MonitoringV5_Queue4", false, false, false);
    }

    @Bean("MonitoringV5_Queue5")
    public Queue MonitoringV5_Queue5() {
        // declare queue2
        return new Queue("MonitoringV5_Queue5", true, false, false);
    }

    @Bean("MonitoringV5_Queue6")
    public Queue MonitoringV5_Queue6() {
        // declare queue2
        return new Queue("MonitoringV5_Queue6", true, false, false);
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        // configure listener to receive messages from queues
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // qos = 0
        container.setAcknowledgeMode(AcknowledgeMode.NONE);
        // listen to queues
        container.setQueues(MonitoringV5_Queue3(), MonitoringV5_Queue4());
        container.setMessageListener(new RabbitMessageListener());
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainerWithQos() {
        // configure listener to receive messages from queues
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // qos = 1
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // listen to queues
        container.setQueues(MonitoringV5_Queue5(), MonitoringV5_Queue6());
        container.setMessageListener(new RabbitMessageListenerWithQos());
        return container;
    }

}