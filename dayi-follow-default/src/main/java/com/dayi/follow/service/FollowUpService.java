package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.FollowUp;
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
    BizResult add(FollowUp followUp);

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


}
