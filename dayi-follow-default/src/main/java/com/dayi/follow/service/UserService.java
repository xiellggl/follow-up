package com.dayi.follow.service;


import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.vo.LoginVo;
import com.dayi.follow.vo.user.UserEditDto;
import com.dayi.follow.vo.user.UserVo;
import com.dayi.mybatis.support.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author xiell
 * @date 2018/11/12
 */

/**
 * 跟进人 业务接口类
 */
public interface UserService {

    /**
     * 添加用户
     */
    BizResult add(FollowUp followUp);

    /**
     * 获取用户
     */
    FollowUp get(String followUpId);

    /**
     * 根据账号获取用户
     */
    FollowUp getByUserName(String userName);

    /**
     * 检查邀请码重复信息
     */
    boolean checkCodeRepeat(String inviteCode);

    /**
     * 更新用户信息
     */
    BizResult update(UserEditDto userEditDto);

    /**
     * 修改密码
     */
    BizResult editPwd(String id, String oldPwd, String newPwd, String confirmPwd);

    //查询全部用户
    Page<UserVo> findPage(Page page, String deptId, String mobile, String queryDeptId, String inviteCode);

    //重置密码
    BizResult resetPwd(FollowUp followUp, String newPwd);

    //重置密码
    BizResult enable(FollowUp followUp);

    //重置密码
    BizResult disable(FollowUp followUp);

    //删除
    BizResult delete(FollowUp followUp);

    //处理减少部门人数
    void doReducePerson(Department department);

    //处理添加部门人数
    void doAddPerson(Department department);

    //处理更新部门人数
    void doUpdatePerson(Department oldDept, String newId);

    //登录
    BizResult login(HttpServletRequest request, LoginVo loginVo, String goTo);
    //登出
    void loginOut(HttpServletRequest request, HttpServletResponse response);

}
