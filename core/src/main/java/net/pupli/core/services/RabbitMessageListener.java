package net.pupli.core.services;

import com.google.gson.Gson;
import net.pupli.core.dto.ReadValueDto;
import net.pupli.core.libs.ConfigFile;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.redis.RawRealData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.time.LocalDateTime;
import java.util.Optional;

public class RabbitMessageListener implements MessageListener {

    Logger logger = LoggerFactory.getLogger(RabbitMessageListener.class);

    @Override
    public void onMessage(Message message) {
        switch (message.getMessageProperties().getConsumerQueue()) {
            case "MonitoringV5_Queue3" -> Queue3(message);
            case "MonitoringV5_Queue4" -> Queue4(message);
        }
    }

    private void Queue3(Message message) {
        String str = new String(message.getBody());
        //System.out.println(str);
        logger.info(str);
    }

    private void Queue4(Message message) {
        try {
            String str = new String(message.getBody());
            // System.out.println(str);
            logger.info(str);

            Gson gson = new Gson();
            ReadValueDto readValueDto = gson.fromJson(str, ReadValueDto.class);

            for (ReadValueDto.Value value : readValueDto.getDataList()) {
                LocalDateTime time = LocalDateTime.parse(readValueDto.getTime());
                Double v = Double.parseDouble(value.getValue());
                int itemId = value.getId();
                String id = MyContext.staticCache.getItemIds().get(itemId);

                if (id==null)
                {
                    continue;
                }

                Optional<RawRealData> matched=MyContext.rawRealDataRepository.findById(id);

                if (matched.isPresent())
                {
                    RawRealData data=matched.get();
                    data.setValue(v);
                    data.setTime(time);

                    MyContext.rawRealDataRepository.save(data);
                }
                else {
                    RawRealData realData = new RawRealData(id, itemId, v, time);
                    MyContext.rawRealDataRepository.save(realData);
                }
            }

        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
    }
}
