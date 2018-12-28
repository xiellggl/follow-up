package com.dayi.follow.interceptor;

import com.dayi.follow.dao.follow.UserMapper;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.service.RoleService;
import com.dayi.follow.service.UserService;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import com.dayi.user.authorization.authz.RoleBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(GlobalInterceptor.class);
    @Resource
    UserMapper userMapper;
    @Resource
    RoleService roleService;
    @Resource
    UserService userService;
    @Resource
    PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        if (accountInfo != null) {
            String userId = accountInfo.getUserId();
            FollowUp user = userService.get(userId);


            List<String> roleNameList = new ArrayList<>();
            List<Role> roles = roleService.queryRolesByIds(user.getRoleids());
            List<Permission> permissions = new ArrayList<>();
            for (Role role : roles) {
                roleNameList.add(role.getName());
                List<Permission> permissionList = permissionService.getPermissionsByRoleId(role.getId());
                permissions.addAll(permissionList);
            }

            //用户名称
            request.getSession().setAttribute("name", user.getName());

            //用户权限名称
            request.getSession().setAttribute("roleName", StringUtils.join(roleNameList,","));

            //获取权限列表
            request.getSession().setAttribute("permissions", permissions);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
