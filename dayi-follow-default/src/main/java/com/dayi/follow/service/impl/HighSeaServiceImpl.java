package com.dayi.follow.service.impl;


import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.dayi.CountMapper;
import com.dayi.follow.dao.follow.*;
import com.dayi.follow.enums.ConfigEnum;
import com.dayi.follow.model.follow.Config;
import com.dayi.follow.model.follow.FollowAgent;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.HighSeaService;
import com.dayi.follow.vo.SearchVo;
import com.dayi.follow.vo.ConfigVo;
import com.dayi.follow.vo.highsea.HSConfigVo;
import com.dayi.follow.vo.highsea.HSListVo;
import com.dayi.mybatis.support.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //判断是否在公海范围
        Config c1 = configMapper.getByMark(ConfigEnum.HS_RANGE.name());

        if (c1 == null || StringUtils.isBlank(c1.getValue())) return BizResult.fail("请先设置公海范围！");

        String[] split = StringUtils.split(c1.getValue(), ",");

        FollowUp followUp = followUpMapper.get(followId);
        String deptId = followUp.getDeptId();

        if (ArrayUtils.contains(split, deptId)) {
            //判断是否超过私海上限
            int cusNum = followUpMapper.getCusNum(followId, dayiDataBaseStr);

            Config c2 = configMapper.getByMark(ConfigEnum.PS_NUM.name());

            if (c2 == null || StringUtils.isBlank(c2.getValue())) return BizResult.fail("请先设置私海上限!");

            Integer limit = Integer.valueOf(c2.getValue());

            if (cusNum >= limit) return BizResult.fail("超过私海限制！");

            fa.setHighSeaFlag(FollowAgent.NOT_HIGHSEA.getId());
            fa.setWarehouseDate(null);
            fa.setFollowId(followId);
            fa.setUpdateTime(new Date());
            return followAgentMapper.updateAll(fa) == 1 ? BizResult.SUCCESS : BizResult.FAIL;
        }else {
            return BizResult.fail("您不在公海范围内！");
        }
    }

    @Override
    public BizResult kick(String followId, Integer agentId) {
        if (agentId == null || StringUtils.isBlank(followId)) return BizResult.FAIL;

        FollowAgent fa = followAgentMapper.getFollowAgentByAgentId(agentId);

        if (!followId.equals(fa.getFollowId())) return BizResult.fail("无法操作该用户!");

        Config c = configMapper.getByMark(ConfigEnum.HS_RANGE.name());

        if (c == null || StringUtils.isBlank(c.getValue())) return BizResult.fail("请先设置公海范围！");

        String[] split = StringUtils.split(c.getValue(), ",");

        FollowUp followUp = followUpMapper.get(followId);
        String deptId = followUp.getDeptId();

        if (!ArrayUtils.contains(split, deptId)) {
            return BizResult.fail("您不在公海范围内!");
        }

        //判断当前是否代理中
        int num = followAgentMapper.getProtocolNum(agentId, dayiDataBaseStr);
        if (num > 0) return BizResult.fail("该用户有代理中的协议，操作失败！");

        fa.setFollowId(null);
        fa.setHighSeaFlag(FollowAgent.IS_HIGHSEA.getId());

        Date date = new Date();
        fa.setWarehouseDate(date);
        fa.setUpdateTime(date);
        return followAgentMapper.updateAll(fa) == 1 ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public Page<HSListVo> findPage(Page page, SearchVo searchVo, Integer orderType) {
        return followAgentMapper.findHSPage(page, searchVo, orderType, dayiDataBaseStr);
    }

    @Override
    public BizResult set(ConfigVo[] vos) {

        for (ConfigVo vo : vos) {
            Config c = configMapper.getByMark(vo.getMark());
            c.setValue(vo.getValue());
            configMapper.updateAll(c);
        }
        return BizResult.SUCCESS;

    }

    @Override
    public List<HSConfigVo> findConfig() {
        return configMapper.findConfig();
    }

}

