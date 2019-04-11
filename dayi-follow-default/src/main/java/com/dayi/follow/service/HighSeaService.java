package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Agent;
import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.model.follow.Config;
import com.dayi.follow.vo.LoginLogVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.agent.AgentListVo;
import com.dayi.follow.vo.highsea.HSConfigQo;
import com.dayi.follow.vo.highsea.HSConfigVo;
import com.dayi.mybatis.support.Page;

import java.io.IOException;
import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface HighSeaService {

    //批量更新用户状态-移至公海
    void updateBatch();

    //从公海捞
    BizResult drag(String followId, Integer agentId);

    //扔进公海
    BizResult kick(String followId, Integer agentId);

    //公海查询列表
    Page findPage(Page page, SearchVo searchVo);

    //公海设置
    BizResult set(HSConfigQo vo);

    List<HSConfigVo> findConfig();


}
