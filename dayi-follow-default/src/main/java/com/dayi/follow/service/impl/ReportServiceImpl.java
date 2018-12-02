package com.dayi.follow.service.impl;

import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.vo.DailyVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author xiell
 * @date 2018/11/16
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    FollowUpMapper followUpMapper;
    @Resource
    ReportMapper reportMapper;
    @Resource
    DeptService deptService;
    @Resource
    CountService countService;

    @Override
    public List<DailyVo> countTeamDaily(String deptId) {
        List<DailyVo> dailyVos = new ArrayList<DailyVo>();
        if (StringUtils.isBlank(deptId)) return dailyVos;

        List<String> deptIds = deptService.getSubDeptIds(deptId);//下级部门id
        deptIds.add(deptId);//加上自己

        //获取管辖部门（团队）的所有日报，包括KA
        DailyVo dailyVo = new DailyVo();
        for (String subDeptId : deptIds) {
            dailyVo = reportMapper.countTeamDaily(deptId);
            dailyVos.add(dailyVo);
        }
        return dailyVos;
    }

    @Override
    public DailyVo countDaily(String followId) {
        return reportMapper.countDaily(followId);
    }



}