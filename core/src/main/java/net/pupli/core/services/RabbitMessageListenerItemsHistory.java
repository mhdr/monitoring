package net.pupli.core.services;

import com.google.gson.Gson;
import com.mongodb.ClientSessionOptions;
import com.mongodb.session.ClientSession;
import net.pupli.core.dto.ReadValueDto;
import net.pupli.core.libs.MyContext;
import net.pupli.core.models.FinalRealData;
import net.pupli.core.models.RawBooleanData;
import net.pupli.core.models.RealItemHistory;
import net.pupli.core.models.RealItemHistoryWeek;
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

            Gson gson = new Gson();
            FinalRealData data = gson.fromJson(str, FinalRealData.class);

            RealItemHistory realItemHistory = new RealItemHistory(data.getItemId(), data.getValue(), data.getTime());
            MyContext.realItemHistoryRepository.save(realItemHistory);

            RealItemHistoryWeek realItemHistoryWeek = new RealItemHistoryWeek(data.getItemId(), data.getValue(), data.getTime());
            MyContext.realItemHistoryWeekRepository.save(realItemHistoryWeek);
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
