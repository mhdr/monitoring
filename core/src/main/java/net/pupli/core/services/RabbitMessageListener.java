package net.pupli.core.services;

import com.google.gson.Gson;
import net.pupli.core.dto.ReadValueDto;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.RawBooleanData;
import net.pupli.core.models.RawRealData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RabbitMessageListener implements MessageListener {

    Logger logger = LoggerFactory.getLogger(RabbitMessageListener.class);

    @Override
    public void onMessage(Message message) {
        if (MyContext.myCache.getShouldProcessData()) {
            switch (message.getMessageProperties().getConsumerQueue()) {
                case "MonitoringV5_Queue3" -> Queue3(message);
                case "MonitoringV5_Queue4" -> Queue4(message);
            }
        }
    }

    private void Queue3(Message message) {
        try {
            String str = new String(message.getBody());
            //logger.info(str);

            Gson gson = new Gson();
            ReadValueDto readValueDto = gson.fromJson(str, ReadValueDto.class);

            List<RawBooleanData> dataList = new ArrayList<>();

            for (ReadValueDto.Value value : readValueDto.getDataList()) {
                LocalDateTime time = LocalDateTime.parse(readValueDto.getTime());
                Boolean v = Boolean.parseBoolean(value.getValue());
                String itemId = MyContext.myCache.getItemIds().get(value.getId());

                if (itemId == null) {
                    continue;
                }

                RawBooleanData matched = MyContext.myCache.getRawBooleanData().get(itemId);
                matched.setValue(v);
                matched.setTime(time);

                dataList.add(matched);
            }

            MyContext.rawBooleanDataRepository.saveAll(dataList);

        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void Queue4(Message message) {
        try {
            String str = new String(message.getBody());
            //logger.info(str);

            Gson gson = new Gson();
            ReadValueDto readValueDto = gson.fromJson(str, ReadValueDto.class);

            List<RawRealData> dataList = new ArrayList<>();

            for (ReadValueDto.Value value : readValueDto.getDataList()) {
                LocalDateTime time = LocalDateTime.parse(readValueDto.getTime());
                Double v = Double.parseDouble(value.getValue());
                String itemId = MyContext.myCache.getItemIds().get(value.getId());

                if (itemId == null) {
                    continue;
                }

                RawRealData matched = MyContext.myCache.getRawRealData().get(itemId);
                matched.setValue(v);
                matched.setTime(time);

                dataList.add(matched);
            }

            MyContext.rawRealDataRepository.saveAll(dataList);

        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
