package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.FollowUp;
import com.dayi.follow.vo.AgentVo;
import com.dayi.follow.vo.LoginVo;
import com.dayi.mybatis.support.Page;

import javax.servlet.http.HttpServletRequest;
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
