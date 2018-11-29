package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.vo.OrgListVo;
import com.dayi.follow.model.follow.Organization;
import com.dayi.mybatis.support.Page;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface OrgService {

    /**
     * 根据创客号获取机构商信息
     */
    Organization getByMarkerNum(String makerNum);

    /**
     * 根据邀请码获取机构商信息
     */
    Organization getByInviteCode(String inviteCode);

    /**
     * 根据邀请码获取经纪会员机构商信息
     */
    Organization getManagerOrgByInviteCode(Integer inviteCode);

    /**
     * 根据邀请码获取综合会员机构商信息
     */
    Organization getColligateOrgByInviteCode(String inviteCode);

    //获取管理资产
    double getManageFund(Integer orgId, Integer level);

    BizResult addContact(OrgContact orgContact);

    /**
     * 获取代理商列表
     */
    Page<OrgListVo> findOrgPage(Page<OrgListVo> page, String mobile, String inviteCode, String followId);

    /**
     * 获取代理商列表
     */
    Page<OrgListVo> findTeamOrgPage(Page<OrgListVo> page, String inviteCode, String followUp, String followId, String deptId);


}
