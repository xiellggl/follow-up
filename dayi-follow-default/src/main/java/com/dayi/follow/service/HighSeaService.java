package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.ConfigVo;
import com.dayi.follow.vo.highsea.HSConfigVo;
import com.dayi.mybatis.support.Page;

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
    BizResult set(ConfigVo[] vos);

    List findConfig();

    HSConfigVo getConfig();

}
