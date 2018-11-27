package com.dayi.follow.dao.follow;

import com.dayi.follow.vo.DailyVo;
import com.dayi.follow.vo.IndexVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<IndexVo>{
    //统计团队日报
    DailyVo countTeamDaily(String deptId);
    /**
     * 统计日报
     */

    DailyVo countDaily(String followId);

}
