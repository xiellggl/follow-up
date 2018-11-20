package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.FollowUp;
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




}
