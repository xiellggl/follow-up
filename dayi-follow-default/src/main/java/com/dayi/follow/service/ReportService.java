package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */

import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.vo.DailyVo;

import java.util.List;
import java.util.Map;

/**
 *  部门 业务接口类
 */
public interface ReportService {

    DailyVo countDaily(String followId);

    DailyVo countTeamDaily(String deptId);

    //统计客服的团队日报
    Map countSerTeamDaily(List<String> deptIds);

}
