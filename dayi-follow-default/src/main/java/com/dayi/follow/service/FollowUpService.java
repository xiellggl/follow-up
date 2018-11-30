package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.vo.LoginVo;
import com.dayi.mybatis.support.Page;

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
     * 添加跟进人信息
     */
    BizResult addFollowUp(FollowUp followUp);

    /**
     * 获取跟进人信息
     */
    FollowUp get(String followUpId);

    /**
     * 根据账号获取跟进人信息
     */
    FollowUp getByUserName(String userName);

    /**
     * 检查邀请码重复信息
     */
    boolean checkCodeRepeat(String inviteCode);

    /**
     * 更新跟进人信息
     */
    BizResult update(FollowUp followUp);

    /**
     * 修改密码
     */
    BizResult editPwd(String id, String oldPwd, String newPwd, String confirmPwd);

    //根据部门Id获取跟进人id集合
    List<String> findIdsByDeptId(String deptId);

    //查询全部跟进人
    Page<FollowUp> findPage(Page page);

    //查询全部跟进人
    Page<FollowUp> findAssignSelect(Page page, String followUp, String deptId);


}
