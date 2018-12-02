package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.vo.followup.FollowUpListVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowUpMapper extends BaseMapper<FollowUp> {


    List<FollowUp> findByDeptIds(List<String> deptIds);

    List<String> findIdsByDeptIds(List<String> deptIds);

    List<String> findIdsByDeptId(String deptId);

    List<FollowUpListVo> findFollowUps(String mobile, List<String> followIds, String inviteCode, Integer limitStart, Integer limitEnd);

    int getFollowUpsNum(String mobile, List<String> followIds, String inviteCode);

    List<FollowUp> findAssignSelect(String followUp, List<String> followIds, Integer limitStart, Integer limitEnd);

    int countAssignSelect(String followUp, List<String> followIds);

    //List<FollowUpListVo> getFollowUpsAgentPart(List<String> followIds, String assistDataBase);
}
