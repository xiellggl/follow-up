package com.dayi.follow.dao.dayi;

import com.dayi.follow.model.follow.Organization;
import com.dayi.mybatis.support.BaseMapper;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface OrgMapper extends BaseMapper<Organization> {
    double getManageFundLevel1(Integer orgId);

    double getManageFundLevel2(Integer orgId);

}
