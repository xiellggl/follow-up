package com.dayi.follow.dao.follow;


import com.dayi.follow.model.follow.Role;
import com.dayi.follow.vo.RoleVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;

import java.util.List;
/**
 * @author xiell
 * @date 2018/11/23
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取角色列表
     * @return
     */
    List<Role> getByIds(List<String> ids);

    /**
     * 返回分页列表
     * @param page
     * @param roleVo
     * @return
     */
    Page<Role> findPage(Page<Role> page, RoleVo roleVo);

    /**
     * 根据系统标识来获取角色列表
     * @return
     */
    List<Role> getListBySystemId(String systemId);

}
