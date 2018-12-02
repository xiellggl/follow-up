package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.FollowOrg;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;

import java.util.List;

/**
 * 跟进人 业务接口类
 */
public interface FollowOrgService {


    String getFollowIdByOrgId(Integer orgId);

    Page findContacts(Page page, Integer orgId);

    BizResult add(FollowOrg followOrg);

    BizResult addBatch(List<FollowOrg> followOrgs);

    BizResult clear(FollowOrg followOrg);

    BizResult clearBatch(List<FollowOrg> followOrgs);

    FollowOrg getFollowOrgByOrgId(Integer orgId);

    Page findAssignPage(Page page, SearchVo searchVo, String deptId);

}
