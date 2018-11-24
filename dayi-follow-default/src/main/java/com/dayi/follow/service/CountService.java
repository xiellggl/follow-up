package com.dayi.follow.service;


import com.dayi.follow.vo.IndexVo;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface CountService {

    IndexVo countPreDayDaily(String followId, String deptIdStr);




}
