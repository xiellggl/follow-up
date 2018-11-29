package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */


import com.dayi.mybatis.support.Page;

/**
 * 跟进人 业务接口类
 */
public interface FollowOrgService {


    String getFollowIdByOrgId(Integer orgId);

    Page findContacts(Page page, Integer orgId);

}
