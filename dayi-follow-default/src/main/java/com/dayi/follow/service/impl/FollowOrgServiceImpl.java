package com.dayi.follow.service.impl;


import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowOrgMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowOrgService;
import com.dayi.follow.service.OrgService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 跟进人 业务实现类
 */
@Service
public class FollowOrgServiceImpl implements FollowOrgService {
    @Resource
    private FollowAgentMapper followAgentMapper;
    @Resource
    private FollowOrgMapper followOrgMapper;
    @Resource
    private UserComponent userComponent;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;


    @Override
    public String getFollowIdByOrgId(Integer orgId) {
        return followOrgMapper.getFollowIdByOrgId(orgId);
    }
}

