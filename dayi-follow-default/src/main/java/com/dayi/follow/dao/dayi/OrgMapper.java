package com.dayi.follow.dao.dayi;

import com.dayi.follow.model.follow.Organization;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface OrgMapper extends BaseMapper<Organization> {
    double getManageFundLevel1(Integer orgId);

    double getManageFundLevel2(Integer orgId);

    Organization getByMarkerNum(String makerNum);
    /**
     * 根据邀请码获取经纪会员机构商信息
     */
    Organization getManagerOrgByInviteCode(Integer inviteCode);
    /**
     * 根据邀请码获取综合会员机构商信息
     */
    Organization getColligateOrgByInviteCode(@Param("word") Integer word,@Param("inviteCode") Integer inviteCode);

}
