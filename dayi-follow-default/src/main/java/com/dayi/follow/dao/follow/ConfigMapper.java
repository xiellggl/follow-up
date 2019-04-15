package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.Config;
import com.dayi.follow.vo.highsea.HSConfigVo;
import com.dayi.mybatis.support.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiell
 * @date 2019/4/11
 */
public interface ConfigMapper extends BaseMapper<Config> {

    @Select(" select * from config ")
    List<HSConfigVo> findConfig();

}
