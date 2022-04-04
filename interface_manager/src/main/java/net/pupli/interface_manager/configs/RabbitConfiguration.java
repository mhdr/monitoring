package net.pupli.interface_manager.configs;

import net.pupli.interface_manager.libs.ConfigFile;
import net.pupli.interface_manager.services.RabbitMessageListener;
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

    @Bean
    public CachingConnectionFactory connectionFactory() {
        ConfigFile configFile = new ConfigFile();
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(configFile.getRabbitmqHost());
        connectionFactory.setUsername(configFile.getRabbitmqUserName());
        connectionFactory.setPassword(configFile.getRabbitmqPassword());
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        template.setRetryTemplate(retryTemplate);
        return template;
    }

    @Bean
    public Queue MonitoringV5_Queue1() {
        return new Queue("MonitoringV5_Queue1");
    }

    @Bean
    public Queue MonitoringV5_Queue2() {
        return new Queue("MonitoringV5_Queue2");
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setConcurrentConsumers(8);
        container.setMaxConcurrentConsumers(32);
        container.setQueueNames("MonitoringV5_Queue1", "MonitoringV5_Queue2");
        container.setMessageListener(new RabbitMessageListener());
        return container;
    }

}