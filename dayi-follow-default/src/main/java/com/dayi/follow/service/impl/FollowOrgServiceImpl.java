package com.dayi.follow.service.impl;


import com.dayi.common.util.BigDecimals;
import com.dayi.common.util.BizResult;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.component.UserComponent;
import com.dayi.follow.dao.dayi.AgentMapper;
import com.dayi.follow.dao.dayi.OrgMapper;
import com.dayi.follow.dao.follow.DeptMapper;
import com.dayi.follow.dao.follow.FollowAgentMapper;
import com.dayi.follow.dao.follow.FollowOrgMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.model.follow.*;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.FollowOrgService;
import com.dayi.follow.service.OrgService;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private OrgService orgService;
    @Resource
    private OrgMapper orgMapper;
    @Resource
    private AgentMapper agentMapper;
    @Resource
    private CountService countService;
    @Resource
    private DeptService deptService;
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
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "创客分配管理", note = "分配跟进人")
    public BizResult add(FollowOrg followOrgVo) {
        FollowUp followUp = followUpMapper.get(followOrgVo.getFollowId());
        if (followUp == null) return BizResult.FAIL;

        Organization org = orgMapper.get(followOrgVo.getOrgId());
        if (org == null) return BizResult.FAIL;

        FollowOrg followOrg = followOrgMapper.getFollowOrgByOrgId(followOrgVo.getOrgId());
        if (followOrg == null) {//创建
            followOrg = new FollowOrg();
            followOrg.setId(followOrgMapper.getNewId());
            followOrg.setOrgId(followOrgVo.getOrgId());
            followOrg.setFollowId(followOrgVo.getFollowId());
            followOrg.setAssignDate(new Date());
            followOrg.setCreateTime(new Date());
            followOrg.setUpdateTime(new Date());

            BigDecimal level1 = orgMapper.getManageFundLevel1(org.getId());
            BigDecimal level2 = BigDecimal.ZERO;
            if (org.getSwitchStatus() != null && org.getSwitchStatus().equals(1)) {//计算2级
                level2 = orgMapper.getManageFundLevel2(org.getId());
            }

            followOrg.setManageFundBefore(level1.add(level2));
        } else {//更新
            FollowUp oldFollowUp = followUpMapper.get(followOrg.getFollowId());
            if (oldFollowUp != null) {//当前有跟进人
                followOrg.setFollowUpBefore(oldFollowUp.getName());
                followOrg.setAssignDateBefore(followOrg.getAssignDate());

                BigDecimal level1 = orgMapper.getManageFundLevel1(org.getId());
                BigDecimal level2 = BigDecimal.ZERO;
                if (org.getSwitchStatus() != null && org.getSwitchStatus().equals(1)) {//计算2级
                    level2 = orgMapper.getManageFundLevel2(org.getId());
                }
                followOrg.setManageFundBefore(level1.add(level2));

            } else {
                //如何当前没有跟进人，而followOrg又不为空，说明原来有跟进人，被清除分配过，而清除分配时又会更新以下信息
                followOrg.setFollowUpBefore(followOrg.getFollowUpBefore());
                followOrg.setAssignDateBefore(followOrg.getAssignDateBefore());
                followOrg.setManageFundBefore(followOrg.getManageFundBefore());
            }
            followOrg.setFollowId(followOrgVo.getFollowId());
            followOrg.setAssignDate(new Date());
            followOrg.setUpdateTime(new Date());
            return 1 == followOrgMapper.updateAll(followOrg) ? BizResult.SUCCESS : BizResult.FAIL;
        }
        return 1 == followOrgMapper.add(followOrg) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "创客分配管理", note = "批量分配跟进人")
    public BizResult addBatch(List<FollowOrg> followOrgs) {
        for (FollowOrg followOrg : followOrgs) {
            BizResult add = this.add(followOrg);
            if (!add.isSucc()) return BizResult.FAIL;
        }
        return BizResult.SUCCESS;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "创客分配管理", note = "清除跟进人")
    public BizResult clear(FollowOrg followOrg) {
        Organization org = orgMapper.get(followOrg.getOrgId());
        if (org == null) return BizResult.FAIL;

        FollowUp followUp = followUpMapper.get(followOrg.getFollowId());
        if (followUp != null) {
            followOrg.setFollowUpBefore(followUp.getName());
        }
        followOrg.setFollowId(null);
        followOrg.setAssignDateBefore(followOrg.getAssignDate());
        followOrg.setAssignDate(null);

        BigDecimal level1 = orgMapper.getManageFundLevel1(org.getId());
        BigDecimal level2 = BigDecimal.ZERO;
        if (org.getSwitchStatus() != null && org.getSwitchStatus().equals(1)) {//计算2级
            level2 = orgMapper.getManageFundLevel2(org.getId());
        }
        followOrg.setManageFundBefore(level1.add(level2));
        followOrg.setUpdateTime(new Date());

        return 1 == followOrgMapper.updateAll(followOrg) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "创客分配管理", note = "批量清除跟进人")
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
    public Page findAssignPage(Page page, SearchVo searchVo) {
        if (searchVo.getAssignStatus() == null || searchVo.getAssignStatus() != 1) {//查未分配
            page = followOrgMapper.findAssignsNoFollow(page, searchVo, dayiDataBaseStr);
        } else {//查已分配
            page = followOrgMapper.findAssignsFollow(page, searchVo, dayiDataBaseStr);
        }

        return page;
    }

    @Override
    public List findAssignList(SearchVo searchVo) {
        List<AssignListVo> list;

        if (searchVo.getAssignStatus() == null || searchVo.getAssignStatus() != 1) {//查未分配
            list = followOrgMapper.findAssignsNoFollowLimit(searchVo, dayiDataBaseStr);
        } else {//查已分配
            list = followOrgMapper.findAssignsFollowLimit(searchVo, dayiDataBaseStr);
        }

        return list;
    }
}

