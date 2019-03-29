package com.dayi.follow.dao.follow;

import com.dayi.follow.base.BaseVo;
import com.dayi.follow.vo.report.*;
import com.dayi.mybatis.support.BaseMapper;
import com.dayi.mybatis.support.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<BaseVo> {

    Page<ReportVo> findDaily(Page page, @Param("followIds") List<String> followIds, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Page<ReportVo> findTeamDaily(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Page<ReportVo> findTeamDailyDetail(Page page, @Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportVo> findTeamDailyDetail(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    ReportVo sumByTime(@Param("followId") String followId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ReportVo> findTeamByTime(@Param("deptId") String deptId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    //管理员日报详情
    List findAdminDailyTotal(@Param("startDate") String startDate, @Param("endDate") String endDate);
    //管理员日报详情
    List findAdminDailyPer(@Param("deptId") String deptId,@Param("startDate") String startDate, @Param("endDate") String endDate);

    List findAdminDetailTotal(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List findAdminDetailPer(@Param("deptId") String deptId,@Param("startDate") String startDate, @Param("endDate") String endDate);
}
