package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowUp;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface FollowUpMapper extends BaseMapper<FollowUp> {
    @Select(" select * from follow_up where user_name =#{username} ")
    FollowUp getByUserName(String userName);
    @Select(" select * from follow_up where invite_code =#{inviteCode} ")
    FollowUp getByInviteCode(String inviteCode);


}
