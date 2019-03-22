package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.FollowUpLog;
import com.dayi.follow.model.follow.SourceReport;
import com.dayi.follow.vo.report.ReportVo;
import com.dayi.follow.vo.report.SourceReportVo;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;


public interface SourceReportMapper extends BaseMapper<SourceReport> {
    @Select(" select * from source_report where create_time between #{startDate} " +
            " and #{endDate} and type =#{type} ")
    SourceReport getByTime(@Param("type") int type,@Param("startDate") String startDate,@Param("endDate") String endDate);

    SourceReportVo sumByTime(@Param("type") int type, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<SourceReportVo> findByTime(@Param("type") Integer type, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //获取指定时间内创客和城市服务商入金
    BigDecimal getMakerInCash(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("assistDataBase") String assistDataBase);
    //获取指定时间内创客和城市服务商出金
    BigDecimal getMakerOutCash(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("assistDataBase") String assistDataBase);
    //获取指定时间内创客和城市服务商管理资产规模
    BigDecimal getMakerManageFund(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("assistDataBase") String assistDataBase);


    //获取指定时间内无跟进人和创客的代理商入金
    BigDecimal getOtherInCash(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("assistDataBase") String assistDataBase);
    //获取指定时间内无跟进人和创客的代理商出金
    BigDecimal getOtherOutCash(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("assistDataBase") String assistDataBase);
    //获取指定时间内无跟进人和创客的代理商管理资产规模
    BigDecimal getOtherManageFund(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("assistDataBase") String assistDataBase);

    //获取指定时间内跟进人的代理商入金
    BigDecimal getFollowUpInCash(@Param("startDate") String startDate, @Param("endDate") String endDate);
    //获取指定时间内跟进人的代理商出金
    BigDecimal getFollowUpOutCash(@Param("startDate") String startDate, @Param("endDate") String endDate);
    //获取指定时间内跟进人的代理商管理资产规模
    BigDecimal getFollowUpManageFund(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
