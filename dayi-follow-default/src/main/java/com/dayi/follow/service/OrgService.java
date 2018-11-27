package com.dayi.follow.service;


import com.dayi.follow.vo.OrgVo;
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
    OrgVo getByMarkerNum(String makerNum);

    /**
     * 根据邀请码获取机构商信息
     */
    OrgVo getByInviteCode(String inviteCode);
    /**
     * 根据邀请码获取经纪会员机构商信息
     */
    OrgVo getManagerOrgByInviteCode(Integer inviteCode);
    /**
     * 根据邀请码获取综合会员机构商信息
     */
    OrgVo getColligateOrgByInviteCode(String inviteCode);
    //获取管理资产
    double getManageFund(Integer orgId,Integer level);






}
