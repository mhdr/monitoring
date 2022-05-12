package net.pupli.core.services;

import net.pupli.core.libs.MyContext;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarbageCollectorJob implements Job {

    Logger logger = LoggerFactory.getLogger(GarbageCollectorJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            DateTime now = DateTime.now();
            DateTime timeBefore = now.minusDays(7);

            // remove real items history before 1 week
            var realItemsHistory = MyContext.itemHistoryRealWeekRepository.findAllByTimeBefore(timeBefore);
            MyContext.itemHistoryRealWeekRepository.deleteAll(realItemsHistory);

            // remove boolean items history before 1 week
            var booleanItemsHistory = MyContext.itemHistoryBooleanWeekRepository.findAllByTimeBefore(timeBefore);
            MyContext.itemHistoryBooleanWeekRepository.deleteAll(booleanItemsHistory);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
