package net.pupli.core.services;

import com.google.gson.Gson;
import net.pupli.core.dto.ReadValueDto;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.RawBooleanData;
import net.pupli.core.models.RawRealData;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.ArrayList;
import java.util.List;

public class RabbitMessageListenerWithQos implements MessageListener {

    Logger logger = LoggerFactory.getLogger(RabbitMessageListenerWithQos.class);

    @Override
    public void onMessage(Message message) {
        if (MyContext.myCache.getShouldProcessData()) {
            switch (message.getMessageProperties().getConsumerQueue()) {
                case "MonitoringV5_Queue5" -> Queue5(message);
                case "MonitoringV5_Queue6" -> Queue6(message);
            }
        }
    }

    private void Queue5(Message message) {
        try {
            String str = new String(message.getBody());
            //logger.info(str);

            Gson gson = new Gson();
            ReadValueDto readValueDto = gson.fromJson(str, ReadValueDto.class);

            List<RawBooleanData> dataList = new ArrayList<>();

            for (ReadValueDto.Value value : readValueDto.getValueList()) {
                String itemId = MyContext.myCache.getItemIds().get(value.getId());

                if (itemId == null) {
                    continue;
                }

                RawBooleanData matched = MyContext.myCache.getRawBooleanData().get(itemId);
                matched.setValue(value.getValue());
                matched.setTime(readValueDto.getTime());

                dataList.add(matched);
            }

            MyContext.rawBooleanDataRepository.saveAll(dataList);

        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void Queue6(Message message) {
        try {
            String str = new String(message.getBody());
            //logger.info(str);

            Gson gson = new Gson();
            ReadValueDto readValueDto = gson.fromJson(str, ReadValueDto.class);

            List<RawRealData> dataList = new ArrayList<>();

            for (ReadValueDto.Value value : readValueDto.getValueList()) {
                String itemId = MyContext.myCache.getItemIds().get(value.getId());

                if (itemId == null) {
                    continue;
                }

                RawRealData matched = MyContext.myCache.getRawRealData().get(itemId);
                matched.setValue(value.getValue());
                matched.setTime(readValueDto.getTime());

                dataList.add(matched);
            }

            MyContext.rawRealDataRepository.saveAll(dataList);

        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
