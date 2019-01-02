package com.dayi.follow.service.impl;

import com.dayi.common.util.Misc;
import com.dayi.follow.dao.follow.OperateLogMapper;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.service.OperateLogService;
import com.dayi.follow.vo.sys.OperateLogSearchVo;
import com.dayi.mybatis.support.Conditions;
import com.dayi.mybatis.support.Order;
import com.dayi.mybatis.support.Page;
import com.dayi.mybatis.support.ext.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * 系统日志Service实现
 *
 * @author zhoujiakai
 * @date 2018/12/03
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;

    @Override
    public Page<OperateLog> searchOperateLog(OperateLogSearchVo operateLogSearchVo) {
        Page<OperateLog> page = new Page<>();
        page.setPageNo(operateLogSearchVo.getPageNo());
        page.setPageSize(operateLogSearchVo.getPageSize());

        Conditions conditions = new Conditions();
        String operatorName = operateLogSearchVo.getOperatorName();
        Date beginDate = operateLogSearchVo.getBeginDate();
        Date endDate = operateLogSearchVo.getEndDate();
        if (!Misc.isEmpty(operatorName)) {
            conditions.add(Restrictions.like("author_name", "%" + operatorName + "%"));
        }
        if (null != beginDate) {
            conditions.add(Restrictions.ge("create_time", beginDate));
        }
        if (null != endDate) {
            // 结束时间加一天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            conditions.add(Restrictions.lt("create_time", calendar.getTime()));
        }
        conditions.addOrder(Order.desc("create_time"));

        page = operateLogMapper.searchByConditions(page, conditions);
        return page;
    }
}
