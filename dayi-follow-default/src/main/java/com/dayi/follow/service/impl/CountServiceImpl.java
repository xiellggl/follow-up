package com.dayi.follow.service.impl;


import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.vo.IndexVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 跟进人 业务实现类
 */
@Service
public class CountServiceImpl implements CountService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private ReportMapper reportMapper;

    @Override
    public IndexVo countPreDayDaily(String followId, String deptIdStr) {
        return reportMapper.countPreDayDaily(followId,deptIdStr);
    }
}

