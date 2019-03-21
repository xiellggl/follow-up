package com.dayi.follow.job;

import com.dayi.follow.service.CountService;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 统计跟进人相关信息
 *
 * @author xiell
 * @date 2018/7/20
 */
@Component
public class ScheduledJob {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(ScheduledJob.class);
    @Resource
    private CountService countService;

    /**
     * 统计跟进人的日志
     */
    @Scheduled(cron = "0 30 17 * * ?")
    public void countFollowUpLog() {
        logger.info("开始统计跟进人每天业绩...");
        if (countService.countFollowUpLog()) {
            logger.info("统计完成！");
        }
    }
}