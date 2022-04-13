package net.pupli.interface_manager.libs;

import net.pupli.interface_manager.services.MyCache;
import org.springframework.amqp.core.AmqpTemplate;

public class MyContext {
    public static AmqpTemplate amqpTemplate;
    public static AmqpTemplate amqpTemplateCore;
    public static MyCache myCache;
}
