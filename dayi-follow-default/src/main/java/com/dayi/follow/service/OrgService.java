package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.org.OrgListVo;
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

    BizResult addContact(OrgContact orgContact);

    /**
     * 获取代理商列表
     */
    Page<OrgListVo> findOrgPage(Page<OrgListVo> page, SearchVo vo, String followId);

    /**
     * 获取代理商列表
     */
    Page<OrgListVo> findTeamOrgPage(Page<OrgListVo> page,SearchVo vo, String followId, String deptId);

    Organization get(Integer orgId);


}
