package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowOrg;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.vo.AgentListVo;
import com.dayi.follow.vo.OrgListVo;
import com.dayi.follow.model.follow.Organization;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowOrgMapper extends BaseMapper<AgentListVo> {

    //统计资产规模
    double getTotalFund(@Param("followIds") List<String> followIds);

    List<Organization> findOrgsByfollowId(String followId, String deadline);

    List<Organization> findOrgsByfollowIds(List<String> followIds, String deadline);

    String getFollowIdByOrgId(Integer orgId);

    //获取follow_agent
    FollowOrg getFollowOrgByOrgId(Integer agentId);

    //获取联系记录
    Page<OrgContact> findContacts(Integer agentId, Integer limitStart, Integer limitEnd);

    List<OrgListVo> findOrgs(@Param("mobile") String mobile, @Param("inviteCode") String inviteCode,
                             @Param("followId") String followId, @Param("assistDataBase") String assistDataBase,
                             @Param("limitStart") Integer limitStart,@Param("limitEnd") Integer limitEnd);

    int findOrgsCount(@Param("mobile") String mobile, @Param("inviteCode") String inviteCode,
                          @Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    List<OrgListVo> findTeamOrgs(@Param("followUp") String followUp, @Param("inviteCode") String inviteCode,
                                 @Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase,
                                 @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    Integer findTeamOrgsCount(@Param("followUp") String followUp, @Param("inviteCode") String inviteCode,
                              @Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);
}
