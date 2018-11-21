package com.dayi.follow.dao.follow;

import com.dayi.follow.vo.IndexVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface ReportMapper {
    /**
     * 负责人和管理员--统计前一天的部门日报
     */
    List<IndexVo> countPreDayDaily(@Param("followId") Integer followId, @Param("deptIdStr") String deptIdStr);
}
