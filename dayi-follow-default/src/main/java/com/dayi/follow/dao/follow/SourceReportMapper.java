package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowUpLog;
import com.dayi.follow.model.follow.SourceReport;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SourceReportMapper extends BaseMapper<SourceReport> {
    @Select(" select * from source_report where create_time between #{startDate} " +
            " and #{endDate} and type =#{type} ")
    SourceReport getByTime(@Param("type") int type,@Param("startDate") String startDate,@Param("endDate") String endDate);

    List findByTime(@Param("type") Integer type, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
