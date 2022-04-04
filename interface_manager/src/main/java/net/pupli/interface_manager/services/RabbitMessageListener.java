package net.pupli.interface_manager.services;

import com.google.gson.Gson;
import net.pupli.interface_manager.dto.ReadValueDto;
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
        String str=new String(message.getBody());
        System.out.println(str);
        Gson gson = new Gson();
        ReadValueDto value= gson.fromJson(str,ReadValueDto.class);
    }

    private void Queue2(Message message) {
        String str=new String(message.getBody());
        System.out.println(str);
        Gson gson = new Gson();
        ReadValueDto value= gson.fromJson(str,ReadValueDto.class);
    }
}
