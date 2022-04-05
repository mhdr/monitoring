package net.pupli.interface_manager.services;

import net.pupli.interface_manager.libs.MyContext;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyContext.amqpTemplate= (AmqpTemplate) applicationContext.getBean("rabbitTemplate");
        MyContext.amqpTemplateCore= (AmqpTemplate) applicationContext.getBean("rabbitTemplateCore");
    }
}
