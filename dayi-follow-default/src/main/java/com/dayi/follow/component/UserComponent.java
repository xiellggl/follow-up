package com.dayi.follow.component;

import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.service.DeptService;
import com.dayi.follow.service.UserService;
import com.dayi.follow.vo.LoginVo;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xiell
 * @date 2018/11/14
 */
@Component
public class UserComponent {
    @Resource
    UserService userService;
    @Resource
    DeptService deptService;

    public LoginVo getCurrUser(HttpServletRequest request) {
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        String userId = accountInfo.getUserId();
        FollowUp followUp = userService.get(userId);
        if (followUp != null) {
            LoginVo loginVo = new LoginVo();
            String deptId = followUp.getDeptId();
            Department department = deptService.getDept(deptId);
            if (department != null) {
                loginVo.setDeptName(department.getName());
            }
            loginVo.setUsername(followUp.getUserName());
            loginVo.setDisable(followUp.getDisable());
            loginVo.setName(followUp.getName());
            loginVo.setId(followUp.getId());
            loginVo.setDeptId(followUp.getDeptId());
            return loginVo;
        }
        return null;
    }
}