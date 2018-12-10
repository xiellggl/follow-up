package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowUpLog;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface FollowUpLogMapper extends BaseMapper<FollowUpLog> {
    @Select(" select * from follow_up_log where create_time between #{startDate} " +
            " and #{endDate} and follow_id =#{followId} ")
    FollowUpLog getLog(String followId, String startDate, String endDate);
}
