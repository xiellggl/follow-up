package com.dayi.follow.dao.follow;

import com.dayi.follow.vo.DailyVo;
import com.dayi.follow.vo.index.IndexVo;
import com.dayi.mybatis.support.BaseMapper;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<IndexVo> {

    List<DailyVo> findDaily(List<String> followIds, String startDate, String endDate, Integer limitStart, Integer limitEnd);
    long findDailyCount(List<String>  followIds, String startDate, String endDate);

    List<DailyVo> findTeamDaily(List<String> followIds, String startDate, String endDate, Integer limitStart, Integer limitEnd);
    long findTeamDailyCount(List<String>  followIds, String startDate, String endDate);
}
