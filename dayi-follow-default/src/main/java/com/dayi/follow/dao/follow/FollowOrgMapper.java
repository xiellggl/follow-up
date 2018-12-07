package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowOrg;
import com.dayi.follow.model.follow.OrgContact;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.org.OrgListVo;
import com.dayi.follow.model.follow.Organization;
import com.dayi.follow.vo.SearchVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowOrgMapper extends BaseMapper<FollowOrg> {

    //统计资产规模
    double getTotalFund(@Param("followIds") List<String> followIds,@Param("assistDataBase") String assistDataBase);

    List<Organization> findOrgsByfollowId(@Param("followId") String followId, @Param("deadline") String deadline, @Param("assistDataBase") String assistDataBase);

    List<Organization> findOrgsByfollowIds(@Param("followIds") List<String> followIds, @Param("deadline") String deadline, @Param("assistDataBase") String assistDataBase);

    String getFollowIdByOrgId(Integer orgId);

    //获取follow_org
    FollowOrg getFollowOrgByOrgId(Integer orgId);

    //获取联系记录
    List<OrgContact> findContacts(@Param("orgId") Integer orgId, @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);
    //获取联系记录
    int getContactsNum(@Param("orgId") Integer orgId);

    List<OrgListVo> findOrgs(@Param("mobile") String mobile, @Param("inviteCode") String inviteCode,
                             @Param("followId") String followId, @Param("assistDataBase") String assistDataBase,
                             @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    int getOrgsNum(@Param("mobile") String mobile, @Param("inviteCode") String inviteCode,
                   @Param("followId") String followId, @Param("assistDataBase") String assistDataBase);

    List<OrgListVo> findTeamOrgs(@Param("followUp") String followUp, @Param("inviteCode") String inviteCode,
                                 @Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase,
                                 @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd);

    Integer getTeamOrgsNum(@Param("followUp") String followUp, @Param("inviteCode") String inviteCode,
                              @Param("followIds") List<String> followIds, @Param("assistDataBase") String assistDataBase);

    //查找已分配跟进人的创客
    List<AssignListVo> findAssignsFollow(Page page, SearchVo searchVo, List<String> followIds, String assistDataBase);

    //统计已分配跟进人的创客数量
    long getAssignsFollowNum(SearchVo searchVo, List<String> followIds, String assistDataBase);

    //查找未分配跟进人的创客
    List<AssignListVo> findAssignsNoFollow(Page page, SearchVo searchVo, String assistDataBase);

    //统计未分配跟进人的创客数量
    long getAssignsNoFollowNum(SearchVo searchVo, String assistDataBase);


    //获取跟进用户数量
    long getCusNum(String followId);


    //获取跟进人旗下创客的管理资金
    double getMangeFund(String followId);

}
