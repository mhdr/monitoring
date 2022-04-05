package net.pupli.interface_manager.services;

import net.pupli.interface_manager.libs.MyContext;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        switch (message.getMessageProperties().getConsumerQueue()) {
            case "MonitoringV5_Queue1" -> Queue1(message);
            case "MonitoringV5_Queue2" -> Queue2(message);
        }
    }

    private void Queue1(Message message) {
        String str = new String(message.getBody());
        System.out.println(str);
        MyContext.amqpTemplateCore.convertAndSend("MonitoringV5_Queue3", str);
    }

    private void Queue2(Message message) {
        String str = new String(message.getBody());
        System.out.println(str);
        MyContext.amqpTemplateCore.convertAndSend("MonitoringV5_Queue4", str);
    }
}