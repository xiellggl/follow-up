package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.model.follow.Config;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.HighSeaService;
import com.dayi.follow.util.StringUtil;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.highsea.HSConfigQo;
import com.dayi.follow.vo.highsea.HSConfigVo;
import com.dayi.follow.vo.highsea.HSListVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 跟进人 业务实现类
 */
@Service
public class HighSeaServiceImpl implements HighSeaService {
    @Resource
    FollowAgentMapper followAgentMapper;
    @Resource
    ConfigMapper configMapper;
    @Resource
    DeptMapper deptMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    FollowUpMapper followUpMapper;
    @Resource
    CountMapper countMapper;
    @Value("${dayi.dataBase}")
    String dayiDataBaseStr;

    @Override
    public void updateBatch() {
        List<String> ids = followAgentMapper.searchHGCusID();
        followAgentMapper.updateHSFlagBatch(ids);
    }

    @Override
    public BizResult drag(String followId, Integer agentId) {
        if (agentId == null || StringUtils.isBlank(followId)) return BizResult.FAIL;

        FollowAgent fa = followAgentMapper.getFollowAgentByAgentId(agentId);

        if (!StringUtils.isBlank(fa.getFollowId())) return BizResult.FAIL;

        //判断是否超过私海上限
        int cusNum = followUpMapper.getCusNum(followId);

        Config c = configMapper.getByMark(Config.MarkType.PS_NUM.name());

        if (c == null) return BizResult.fail("请先设置私海上限!");

        String value = c.getValue();
        Integer limit = Integer.valueOf(value);

        if (cusNum >= limit) return BizResult.fail("超过私海限制！");

        fa.setHighSeaFlag(FollowAgent.NOT_HIGHSEA.getId());
        fa.setFollowId(followId);
        return followAgentMapper.updateAll(fa) == 1 ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public BizResult kick(String followId, Integer agentId) {
        if (agentId == null || StringUtils.isBlank(followId)) return BizResult.FAIL;

        FollowAgent fa = followAgentMapper.getFollowAgentByAgentId(agentId);

        if (!followId.equals(fa.getFollowId())) return BizResult.fail("无法操作该用户!");

        Config c = configMapper.getByMark(Config.MarkType.HS_RANGE.name());

        if (c == null) return BizResult.fail("请先设置公海范围！");

        String value = c.getValue();

        if (StringUtils.isBlank(value)) return BizResult.fail("请先设置公海范围！");

        String[] split = StringUtils.split(value, ",");

        FollowUp followUp = followUpMapper.get(followId);
        String deptId = followUp.getDeptId();

        if (!ArrayUtils.contains(split, deptId)) {
            return BizResult.fail("无法操作该用户!");
        }

        fa.setFollowId(null);
        fa.setHighSeaFlag(FollowAgent.IS_HIGHSEA.getId());
        return followAgentMapper.updateAll(fa) == 1 ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public Page<HSListVo> findPage(Page page, SearchVo searchVo) {
        return followAgentMapper.findHSPage(page, searchVo, dayiDataBaseStr);
    }

    @Override
    public BizResult set(HSConfigQo vo) {

        List<String> deptIds = vo.getDeptIds();

        String value = "";

        for (int i = 0; i < deptIds.size(); i++) {
            if (i == deptIds.size() - 1) {
                value = value + deptIds.get(i);
            } else {
                value = value + deptIds.get(i) + ",";
            }
        }

        Config c1 = configMapper.get(vo.getId());
        if (c1 == null) {
            c1 = new Config();
            c1.setId(configMapper.getNewId());
            c1.setMark(Config.MarkType.HS_RANGE);
            c1.setValue(value);
            configMapper.add(c1);
        } else {
            c1.setValue(value);
            configMapper.updateAll(c1);
        }

        Config c2 = configMapper.get(vo.getId());
        if (c2 == null) {
            c2 = new Config();
            c2.setId(configMapper.getNewId());
            c2.setMark(Config.MarkType.PS_NUM);
            c2.setValue(String.valueOf(vo.getNum()));
            configMapper.add(c2);
        } else {
            c2.setValue(value);
            configMapper.updateAll(c2);
        }
        return BizResult.SUCCESS;

    }

    @Override
    public List<HSConfigVo> findConfig() {
        return configMapper.findConfig();
    }
}

