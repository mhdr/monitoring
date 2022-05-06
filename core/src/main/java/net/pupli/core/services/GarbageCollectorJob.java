package net.pupli.core.services;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarbageCollectorJob implements Job {

    Logger logger = LoggerFactory.getLogger(GarbageCollectorJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Garbage Collector Job is running ...");
    }
}
