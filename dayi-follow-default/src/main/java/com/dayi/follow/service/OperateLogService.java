package com.dayi.follow.service;

import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.vo.sys.OperateLogSearchVo;
import com.dayi.mybatis.support.Page;

/**
 * 系统日志Service
 *
 * @author zhoujiakai
 * @date 2018/12/03
 */
public interface OperateLogService {

    /**
     * 分页条件查询系统日志
     * @param operateLogSearchVo
     * @return
     */
    Page<OperateLog> searchOperateLog(OperateLogSearchVo operateLogSearchVo);
}
