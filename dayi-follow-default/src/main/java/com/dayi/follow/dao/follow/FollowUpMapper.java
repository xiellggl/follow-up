package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.followup.FMDetailListVo;
import com.dayi.follow.vo.followup.FollowUpListVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowUpMapper extends BaseMapper<FollowUp> {


    List<FollowUp> findByDeptIds(@Param("deptIds") List<String> deptIds);

    List<String> findIdsByDeptIds(@Param("deptIds") List<String> deptIds);

    List<String> findIdsByDeptId(@Param("deptId") String deptId);

    List<FollowUpListVo> findFollowUps(@Param("mobile") String mobile, @Param("followIds") List<String> followIds,
                                       @Param("inviteCode") String inviteCode, @Param("limitStart") Integer limitStart,
                                       @Param("limitEnd") Integer limitEnd,@Param("assistDataBase") String assistDataBase);

    int getFollowUpsNum(@Param("mobile") String mobile, @Param("followIds") List<String> followIds, @Param("inviteCode") String inviteCode);

    Page<FollowUpListVo> findAssignSelect(Page page,@Param("followUp") String followUp, @Param("followIds") List<String> followIds);

    int countAssignSelect(@Param("followUp") String followUp, @Param("followIds") List<String> followIds);

    List<FMDetailListVo> findAgents(@Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                                    @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd,
                                    @Param("assistDataBase") String assistDataBase);

    long findAgentscount(@Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                         @Param("assistDataBase") String assistDataBase);

    List<FMDetailListVo> findOrgs(@Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                                  @Param("limitStart") Integer limitStart, @Param("limitEnd") Integer limitEnd,
                                  @Param("assistDataBase") String assistDataBase);

    long findOrgscount(@Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                       @Param("assistDataBase") String assistDataBase);
}
