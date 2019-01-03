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

    Page<FollowUpListVo> findFollowUps(Page page, @Param("mobile") String mobile, @Param("deptIds") List<String> deptIds,
                                       @Param("inviteCode") String inviteCode, @Param("assistDataBase") String assistDataBase);
    Page<FollowUpListVo> findAssignSelect(Page page, @Param("followUp") String followUp, @Param("followIds") List<String> followIds);

    Page<FollowUpListVo> findAllAssignSelect(Page page, @Param("followUp") String followUp);

    Page<FMDetailListVo> findAgents(Page page, @Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                                    @Param("assistDataBase") String assistDataBase);

    List<FMDetailListVo> findAgentList(@Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                                       @Param("assistDataBase") String assistDataBase);

    Page<FMDetailListVo> findOrgs(Page page, @Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                                  @Param("assistDataBase") String assistDataBase);

    List<FMDetailListVo> findOrgList(@Param("searchVo") SearchVo searchVo, @Param("followIds") List<String> followIds,
                                     @Param("assistDataBase") String assistDataBase);

    List<FollowUp> findAll();

    FollowUp getByInviteCode(String inviteCode);
}
