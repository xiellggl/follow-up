package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.vo.user.UserVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface UserMapper extends BaseMapper<FollowUp> {
    @Select(" select * from follow_up where user_name =#{username} ")
    FollowUp getByUserName(String userName);

    @Select(" select * from follow_up where invite_code =#{inviteCode} ")
    FollowUp getByInviteCode(String inviteCode);

    List<FollowUp> findByDeptIds(List<String> deptIds);

    List<String> findIdsByDeptIds(List<String> deptIds);

    List<String> findIdsByDeptId(@Param("deptId") String deptId);

    List<UserVo> findUsers(@Param("mobile") String mobile, @Param("followIds") List<String> followIds,
                           @Param("inviteCode") String inviteCode, @Param("limitStart") Integer limitStart,
                           @Param("limitEnd") Integer limitEnd);

    int getUsersNum(@Param("mobile") String mobile, @Param("followIds") List<String> followIds, @Param("inviteCode") String inviteCode);


}
