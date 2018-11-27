package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.AgentContact;
import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.OrgVo;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowOrgMapper extends BaseMapper<AgentListVo> {

    //统计资产规模
    double getTotalFund(@Param("followIds") List<String> followIds);

    List<OrgVo> findOrgsByfollowId(String followId, String deadline);


}
