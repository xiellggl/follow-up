package com.dayi.follow.dao.dayi;

import com.dayi.follow.model.follow.Organization;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface OrgMapper extends BaseMapper<Organization> {
    BigDecimal getManageFundLevel1(Integer orgId);

    BigDecimal getManageFundLevel2(Integer orgId);

    BigDecimal getOrgManageFundAll(@Param("orgIds") List<Integer> orgIds);

    BigDecimal getOrgManageFundLevel1(@Param("orgIds") List<Integer> orgIds);

    Organization getByMarkerNum(String makerNum);

    /**
     * 根据邀请码获取经纪会员机构商信息
     */
    Organization getManagerOrgByInviteCode(Integer inviteCode);

    /**
     * 根据邀请码获取综合会员机构商信息
     */
    Organization getColligateOrgByInviteCode(@Param("word") Integer word, @Param("inviteCode") Integer inviteCode);

}
