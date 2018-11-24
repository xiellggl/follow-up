package com.dayi.follow.dao.follow;


import com.dayi.follow.model.follow.Module;
import com.dayi.mybatis.support.BaseMapper;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/23
 */
public interface ModuleMapper extends BaseMapper<Module> {

    /**
     * 返回列表
     * @param module
     * @return
     */
    List<Module> findList(Module module);

    /**
     * 返回分组系统标识模块列表
     * @return
     */
    List<Module> findListGroupSystemId();


}
