package com.dayi.follow.dao.follow;

import com.dayi.follow.model.FollowUp;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowUpMapper extends BaseMapper<FollowUp> {
    @Select(" select * from follow_up where user_name =#{userName} ")
    FollowUp getByUserName(String userName);
    @Select(" select * from follow_up where invite_code =#{inviteCode} ")
    FollowUp getByInviteCode(String inviteCode);
//    @Select(" select * from follow_up where id =#{followId}")
//    FollowUp get(String followId);
}