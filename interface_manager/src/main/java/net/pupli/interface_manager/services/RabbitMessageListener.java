package net.pupli.interface_manager.services;

import com.google.gson.Gson;
import net.pupli.interface_manager.dto.ReadValueDto;
import net.pupli.interface_manager.dto.ResponseItemsDto;
import net.pupli.interface_manager.libs.MyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMessageListener implements MessageListener {

    Logger logger = LoggerFactory.getLogger(RabbitMessageListener.class);

    @Override
    public void onMessage(Message message) {
        switch (message.getMessageProperties().getConsumerQueue()) {
            case "MonitoringV5_Queue1" -> Queue1(message);
            case "MonitoringV5_Queue2" -> Queue2(message);
        }
    }

    private void Queue1(Message message) {
        String str = new String(message.getBody());
        String queue0 = "MonitoringV5_Queue3";
        String queue1 = "MonitoringV5_Queue5";

        Gson gson = new Gson();
        ReadValueDto readValueDto = gson.fromJson(str, ReadValueDto.class);
        ReadValueDto readValueDtoQos0 = new ReadValueDto();
        ReadValueDto readValueDtoQos1 = new ReadValueDto();

        readValueDtoQos0.setCredential(readValueDto.getCredential());
        readValueDtoQos0.setTime(readValueDto.getTime());

        readValueDtoQos1.setCredential(readValueDto.getCredential());
        readValueDtoQos1.setTime(readValueDto.getTime());

        if (MyContext.myCache.getCredentials().contains(readValueDto.getCredential())) {
            if (MyContext.myCache.getItems().isEmpty()) {
                // if items are not fetched from core or something else happened we want to send data without qos
                MyContext.amqpTemplateCore.convertAndSend(queue0, str);
            } else {
                for (ReadValueDto.Value value : readValueDto.getValueList()) {
                    if (MyContext.myCache.getItems().containsKey(value.getId())) {
                        ResponseItemsDto.Item item = MyContext.myCache.getItems().get(value.getId());
                        int qos = item.getQos();

                        switch (qos) {
                            case 0 -> readValueDtoQos0.getValueList().add(value);
                            case 1 -> readValueDtoQos1.getValueList().add(value);
                        }
                    }

                }

                if (readValueDtoQos0.getValueList().size() > 0) {
                    String str0 = gson.toJson(readValueDtoQos0);
                    MyContext.amqpTemplateCore.convertAndSend(queue0, str0);
                }
                if (readValueDtoQos1.getValueList().size() > 0) {
                    String str1 = gson.toJson(readValueDtoQos1);
                    MyContext.amqpTemplateCore.convertAndSend(queue1, str1);
                }
            }
        }

        // logger.info(str);
    }

    private void Queue2(Message message) {
        String str = new String(message.getBody());
        String queue0 = "MonitoringV5_Queue4";
        String queue1 = "MonitoringV5_Queue6";

        Gson gson = new Gson();
        ReadValueDto readValueDto = gson.fromJson(str, ReadValueDto.class);
        ReadValueDto readValueDtoQos0 = new ReadValueDto();
        ReadValueDto readValueDtoQos1 = new ReadValueDto();

        readValueDtoQos0.setCredential(readValueDto.getCredential());
        readValueDtoQos0.setTime(readValueDto.getTime());

        readValueDtoQos1.setCredential(readValueDto.getCredential());
        readValueDtoQos1.setTime(readValueDto.getTime());

        if (MyContext.myCache.getCredentials().contains(readValueDto.getCredential())) {
            if (MyContext.myCache.getItems().isEmpty()) {
                // if items are not fetched from core or something else happened we want to send data without qos
                MyContext.amqpTemplateCore.convertAndSend(queue0, str);
            } else {
                for (ReadValueDto.Value value : readValueDto.getValueList()) {
                    if (MyContext.myCache.getItems().containsKey(value.getId())) {
                        ResponseItemsDto.Item item = MyContext.myCache.getItems().get(value.getId());
                        int qos = item.getQos();

                        switch (qos) {
                            case 0 -> readValueDtoQos0.getValueList().add(value);
                            case 1 -> readValueDtoQos1.getValueList().add(value);
                        }
                    }
                }

                if (readValueDtoQos0.getValueList().size() > 0) {
                    String str0 = gson.toJson(readValueDtoQos0);
                    MyContext.amqpTemplateCore.convertAndSend(queue0, str0);
                }
                if (readValueDtoQos1.getValueList().size() > 0) {
                    String str1 = gson.toJson(readValueDtoQos1);
                    MyContext.amqpTemplateCore.convertAndSend(queue1, str1);
                }
            }
        }
        // logger.info(str);
    }
}
