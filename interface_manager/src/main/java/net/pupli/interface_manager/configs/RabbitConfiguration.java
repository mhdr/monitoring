package net.pupli.interface_manager.configs;

import net.pupli.interface_manager.libs.ConfigFile;
import net.pupli.interface_manager.services.RabbitMessageListener;
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

    @Bean("connectionFactoryCore")
    public CachingConnectionFactory connectionFactoryCore() {
        // configure to connect with credential
        ConfigFile configFile = ConfigFile.getInstance();
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(configFile.getRabbitmqCoreHost());
        connectionFactory.setUsername(configFile.getRabbitmqCoreUserName());
        connectionFactory.setPassword(configFile.getRabbitmqCorePassword());
        return connectionFactory;
    }

    @Bean("amqpAdmin")
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean("amqpAdminCore")
    public RabbitAdmin amqpAdminCore() {
        return new RabbitAdmin(connectionFactoryCore());
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

    @Bean("rabbitTemplateCore")
    public RabbitTemplate rabbitTemplateCore() {
        RabbitTemplate template = new RabbitTemplate(connectionFactoryCore());
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

    @Bean("MonitoringV5_Queue1")
    public Queue MonitoringV5_Queue1() {
        // Receives boolean raw data from interfaces
        return new Queue("MonitoringV5_Queue1",false,false,false);
    }

    @Bean("MonitoringV5_Queue2")
    public Queue MonitoringV5_Queue2() {
        // Receives real raw data from interfaces
        return new Queue("MonitoringV5_Queue2",false,false,false);
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
        container.setQueues(MonitoringV5_Queue1(),MonitoringV5_Queue2());
        container.setMessageListener(new RabbitMessageListener());
        return container;
    }

}
