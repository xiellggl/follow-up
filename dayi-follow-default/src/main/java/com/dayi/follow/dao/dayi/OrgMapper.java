package com.dayi.follow.dao.dayi;

import com.dayi.follow.vo.OrgVo;
import com.dayi.mybatis.support.BaseMapper;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface OrgMapper extends BaseMapper<OrgVo> {
    double getManageFund(Integer orgId, Integer level);

}
