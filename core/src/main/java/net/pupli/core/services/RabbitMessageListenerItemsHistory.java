package net.pupli.core.services;

import com.google.gson.Gson;
import com.mongodb.ClientSessionOptions;
import com.mongodb.session.ClientSession;
import net.pupli.core.dto.ReadValueDto;
import net.pupli.core.libs.GsonBuilders;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class RabbitMessageListenerItemsHistory implements MessageListener {

    Logger logger = LoggerFactory.getLogger(RabbitMessageListenerItemsHistory.class);

    @Override
    public void onMessage(Message message) {
        if (MyContext.myCache.getShouldProcessData()) {
            switch (message.getMessageProperties().getConsumerQueue()) {
                case "MonitoringV5_Queue7" -> Queue7(message);
            }
        }
    }


    @Transactional
    void Queue7(Message message) {
        try {
            String str = new String(message.getBody());

            Gson gson = GsonBuilders.gsonDateTime();
            FinalRealData data = gson.fromJson(str, FinalRealData.class);

            RealItemHistory realItemHistory = new RealItemHistory(data.getItemId(), data.getValue(), data.getTime());
            MyContext.realItemHistoryRepository.save(realItemHistory);

            RealItemHistoryWeek realItemHistoryWeek = new RealItemHistoryWeek(data.getItemId(), data.getValue(), data.getTime());
            MyContext.realItemHistoryWeekRepository.save(realItemHistoryWeek);

            var prevData = MyContext.prevRealDataSavedRepository.findByItemId(data.getItemId());

            if (prevData.isPresent()) {
                var prevDataValue = prevData.get();
                prevDataValue.setValue(data.getValue());
                prevDataValue.setTime(data.getTime());
                MyContext.prevRealDataSavedRepository.save(prevDataValue);
            } else {
                var prevDataValue = new PrevRealDataSaved();
                prevDataValue.setItemId(data.getItemId());
                prevDataValue.setValue(data.getValue());
                prevDataValue.setTime(data.getTime());
                MyContext.prevRealDataSavedRepository.save(prevDataValue);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
