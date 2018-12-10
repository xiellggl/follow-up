package com.dayi.follow.job;

import com.dayi.follow.service.CountService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 统计跟进人相关信息
 *
 * @author xiell
 * @date 2018/7/20
 */
@Component
@EnableScheduling
public class CountFollowUpLogJob {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(CountFollowUpLogJob.class);
    @Resource
    private CountService countService;

    /**
     * 统计跟进人的日志
     */
    public void countFollowUpLog() {
        logger.info("开始统计跟进人每天业绩...");
        if (countService.countFollowUpLog()) {
            logger.info("统计完成！");
        }

    }
}