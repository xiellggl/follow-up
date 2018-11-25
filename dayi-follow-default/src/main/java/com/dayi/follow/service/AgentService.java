package com.dayi.follow.service;


import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.IndexVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.Page;

import java.util.List;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface AgentService {


    /**
     * 获取代理商列表
     */
    Page<AgentListVo> findAgentPage(Page<AgentListVo> page, SearchVo searchVo, String followId, Integer deptId, Integer deptFlowId,
                                    Integer subDeptId, String followUp);

    IndexVo countCusStatus(String followId);

}
