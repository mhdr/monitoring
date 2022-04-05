package net.pupli.core.services;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        switch (message.getMessageProperties().getConsumerQueue()) {
            case "MonitoringV5_Queue3" -> Queue3(message);
            case "MonitoringV5_Queue4" -> Queue4(message);
        }
    }

    private void Queue3(Message message) {
        String str = new String(message.getBody());
        System.out.println(str);
    }

    private void Queue4(Message message) {
        String str = new String(message.getBody());
        System.out.println(str);
    }
}
