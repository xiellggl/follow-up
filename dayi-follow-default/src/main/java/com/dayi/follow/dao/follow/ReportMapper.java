package com.dayi.follow.dao.follow;

import com.dayi.follow.vo.IndexVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper extends BaseMapper<IndexVo>{
    /**
     * 负责人和管理员--统计前一天的部门日报
     */
    IndexVo countPreDayDaily(@Param("followId") String followId, @Param("deptIdStr") String deptIdStr);
}
