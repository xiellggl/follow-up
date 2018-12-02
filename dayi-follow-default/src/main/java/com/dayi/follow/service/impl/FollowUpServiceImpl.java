package com.dayi.follow.service.impl;


import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Organization;
import com.dayi.follow.service.*;
import com.dayi.follow.util.CollectionUtil;
import com.dayi.follow.vo.followup.FollowUpListVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跟进人 业务实现类
 */
@Service
public class FollowUpServiceImpl implements FollowUpService {
    @Resource
    private FollowUpMapper followUpMapper;
    @Resource
    private FollowAgentMapper followAgentMapper;
    @Resource
    private CountMapper countMapper;
    @Resource
    private OrgMapper orgMapper;
    @Resource
    private FollowOrgMapper followOrgMapper;
    @Resource
    private CountService countService;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;

    @Override
    public FollowUp get(String followUpId) {
        return followUpMapper.get(followUpId);
    }


    @Override
    public List<String> findIdsByDeptId(String deptId) {
        return followUpMapper.findIdsByDeptId(deptId);
    }

    @Override
    public Page<FollowUpListVo> findPage(Page page, String deptId, String mobile, String queryDeptId, String inviteCode) {
        List<String> followIds = new ArrayList<String>();

        if (StringUtils.isBlank(queryDeptId)) followIds = followUpMapper.findIdsByDeptId(deptId);
        else followIds = followUpMapper.findIdsByDeptId(queryDeptId);

        List<FollowUpListVo> followUps = followUpMapper.findFollowUps(mobile, followIds, inviteCode, page.getStartRow(), page.getPageSize());

        for (FollowUpListVo vo : followUps) {
            List<Organization> orgs = followOrgMapper.findOrgsByfollowId(vo.getId(), null);
            vo.setOrgNum(orgs.size());

            double orgFund = countService.getOrgManageFund(orgs);
            vo.setOrgFund(orgFund);
        }

        int num = followUpMapper.getFollowUpsNum(mobile, followIds, inviteCode);

        page.setResults(followUps);
        page.setTotalRecord(num);
        return page;
    }

    @Override
    public Page<FollowUp> findAssignSelect(Page page, String followUp, String deptId) {
        List<String> followIds = this.findIdsByDeptId(deptId);
        List<FollowUp> followUps = followUpMapper.findAssignSelect(followUp, followIds, page.getStartRow(), page.getEndRow());
        int num = followUpMapper.countAssignSelect(followUp, followIds);
        page.setResults(followUps);
        page.setTotalRecord(num);
        return page;
    }

    @Override
    public Page<Agent> findAgentPage(Page page, String deptId, String followId) {
        List<String> followIds = new ArrayList<String>();

        if (StringUtils.isBlank(followId)) {
            followIds = followUpMapper.findIdsByDeptId(deptId);
        } else {
            followIds.add(followId);
        }

        //followUpMapper.findAgents();这个到时跟产品再了解一下

        return null;
    }

}

