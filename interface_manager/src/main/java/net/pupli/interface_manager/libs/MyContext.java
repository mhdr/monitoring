package net.pupli.interface_manager.libs;

import org.springframework.amqp.core.AmqpTemplate;

public class MyContext {
    public static AmqpTemplate amqpTemplate;
    public static AmqpTemplate amqpTemplateCore;
}
