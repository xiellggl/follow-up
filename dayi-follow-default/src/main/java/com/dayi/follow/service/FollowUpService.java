package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.followup.FMDetailListVo;
import com.dayi.follow.vo.followup.FollowUpListVo;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface FollowUpService {

    /**
     * 获取跟进人信息
     */
    FollowUp get(String followUpId);

    //根据部门Id获取跟进人id集合
    List<String> findIdsByDeptId(String deptId);

    //查询全部跟进人
    Page<FollowUpListVo> findPage(Page page, String deptId, String mobile, String queryDeptId, String inviteCode);

    //查询全部跟进人
    Page<FollowUpListVo> findAssignSelect(Page<FollowUpListVo> page, String followUp, String deptId);

    //查询跟进人管理的代理商明细列表
    Page<FMDetailListVo> findAgentPage(Page page, SearchVo searchVo, String followId);

    //查询跟进人管理的全部代理商明细列表
    Page<FMDetailListVo> findAllAgentPage(Page page, SearchVo searchVo, String deptId);

    //查询跟进人管理的创客明细列表
    Page<FMDetailListVo> findOrgPage(Page page, SearchVo searchVo, String followId);

    //查询跟进人管理的全部创客明细列表
    Page<FMDetailListVo> findAllOrgPage(Page page, SearchVo searchVo, String deptId);


}
