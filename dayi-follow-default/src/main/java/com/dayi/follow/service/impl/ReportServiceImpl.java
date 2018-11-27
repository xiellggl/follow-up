package com.dayi.follow.service.impl;

import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.ReportMapper;
import com.dayi.follow.service.CountService;
import com.dayi.follow.service.ReportService;
import com.dayi.follow.vo.DailyVo;
import com.dayi.follow.vo.OrgDataVo;
import org.apache.commons.collections.CollectionUtils;
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
    CountService countService;
    @Override
    public DailyVo countTeamDaily(String deptId) {
        return reportMapper.countTeamDaily(deptId);
    }

    @Override
    public DailyVo countDaily(String followId) {
        return reportMapper.countDaily(followId);
    }

    @Override
    public Map countSerTeamDaily(List<String> deptIds) {

        HashMap map = new HashMap();

        if (CollectionUtils.isEmpty(deptIds)) return map;


        //获取管辖部门（团队）的所有日报，包括KA
        DailyVo dailyVo = new DailyVo();
        List<DailyVo> dailyVos = new ArrayList<DailyVo>();
        for (String deptId : deptIds) {
            dailyVo = reportMapper.countTeamDaily(deptId);
            dailyVos.add(dailyVo);
        }

        map.put("dailyVos",dailyVos);


//        String[] roleArr = deptIdStr.split(",");
//
//        //KA的创客数据
//        OrgDataVo orgDataVo = countService.countTeamOrgData();
//
//        List<String> followIds = followUpMapper.findIdsByDeptId(Arrays.asList(roleArr));


        return map;
    }


}