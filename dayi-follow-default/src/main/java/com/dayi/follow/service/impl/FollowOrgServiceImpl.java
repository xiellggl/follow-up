package com.dayi.follow.service.impl;


import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowOrgMapper;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.service.FollowOrgService;
import com.dayi.mybatis.support.Page;
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

    @Override
    public Page<OrgContact> findContacts(com.dayi.mybatis.support.Page page, Integer orgId) {
        return followOrgMapper.findContacts(orgId, page.getStartRow(), page.getPageSize());
    }
}

