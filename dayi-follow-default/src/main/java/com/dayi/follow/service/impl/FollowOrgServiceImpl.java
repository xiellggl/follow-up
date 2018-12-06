package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowOrgMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.follow.FollowOrg;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.service.FollowOrgService;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private FollowUpMapper followUpMapper;
    @Resource
    private UserComponent userComponent;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;


    @Override
    public String getFollowIdByOrgId(Integer orgId) {
        return followOrgMapper.getFollowIdByOrgId(orgId);
    }

    @Override
    public Page<OrgContact> findContacts(Page page, Integer orgId) {
        List<OrgContact> contacts = followOrgMapper.findContacts(orgId, page.getStartRow(), page.getPageSize());
        int contactsNum = followOrgMapper.getContactsNum(orgId);
        page.setResults(contacts);
        page.setTotalRecord(contactsNum);
        return page;
    }

    @Override
    public BizResult add(FollowOrg followOrg) {

        FollowOrg followOrgOld = this.getFollowOrgByOrgId(followOrg.getOrgId());

        followOrgOld.setCreateTime(new Date());
        followOrgOld.setUpdateTime(new Date());

        if (followOrgOld == null) {//第一次分配
        } else {//删除原来的关系
            followOrg.setFollowDateBefore(followOrgOld.getCreateTime());//之前分配时间

            FollowUp followUp = followUpMapper.get(followOrgOld.getFollowId());
            followOrg.setFollowUpBefore(followUp.getName());//之前跟进人

            int delete = followOrgMapper.delete(followOrgOld);
            if (1 != delete) return BizResult.FAIL;

        }
        return 1 == followOrgMapper.add(followOrg) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public BizResult addBatch(List<FollowOrg> followOrgs) {
        for (FollowOrg followOrg : followOrgs) {
            BizResult add = this.add(followOrg);
            if (!add.isSucc()) return BizResult.FAIL;
        }
        return BizResult.SUCCESS;
    }

    @Override
    public BizResult clear(FollowOrg followOrg) {
        followOrg.setFollowId(null);
        followOrg.setFollowDate(null);
        return 1 == followOrgMapper.update(followOrg) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public BizResult clearBatch(List<FollowOrg> followOrgs) {
        for (FollowOrg followOrg : followOrgs) {
            BizResult clear = this.clear(followOrg);
            if (!clear.isSucc()) return BizResult.FAIL;
        }
        return BizResult.SUCCESS;

    }

    @Override
    public FollowOrg getFollowOrgByOrgId(Integer orgId) {
        return followOrgMapper.getFollowOrgByOrgId(orgId);
    }

    @Override
    public Page findAssignPage(Page page, SearchVo searchVo, String deptId) {
        List<String> followIds = followUpMapper.findIdsByDeptId(deptId);
        List<AssignListVo> assignListVos = new ArrayList<>();

        long num;
        if (searchVo.getAssignStatus() == 1) {//查已分配
            assignListVos = followOrgMapper.findAssignsFollow(page, searchVo, followIds, dayiDataBaseStr);
            num = followOrgMapper.getAssignsFollowNum(searchVo, followIds, dayiDataBaseStr);
        } else {//查未分配
            assignListVos = followOrgMapper.findAssignsNoFollow(page, searchVo, dayiDataBaseStr);
            num = followOrgMapper.getAssignsNoFollowNum(searchVo, dayiDataBaseStr);
        }

        page.setResults(assignListVos);
        page.setTotalRecord(num);
        return page;
    }
}

