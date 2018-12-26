package com.dayi.follow.filter;

import com.dayi.common.util.Misc;
import com.dayi.common.util.UriMatchUtil;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.Permission;
import com.dayi.follow.model.follow.Role;
import com.dayi.follow.service.PermissionService;
import com.dayi.follow.service.RoleService;
import com.dayi.follow.service.UserService;
import com.dayi.user.authorization.AuthorizationManager;
import com.dayi.user.authorization.authc.AccountInfo;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author xiell
 * @date 2018/11/13
 */
@WebFilter(urlPatterns = "/*", filterName = "globalFilter")
@Order(2)
public class GlobalFilter implements Filter {
    @Resource
    UserService userService;
    @Resource
    RoleService roleService;
    @Resource
    PermissionService permissionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        request.getSession().setAttribute("requestURI", requestURI);

        AccountInfo accountInfo = AuthorizationManager.getCurrentLoginUser(request);
        if (accountInfo != null) {
            String userId = accountInfo.getUserId();
            FollowUp user = userService.get(userId);
            request.getSession().setAttribute("name", user.getName());

            //获取权限列表
            List<Permission> permissions = new ArrayList<>();
            List<Role> roles = roleService.queryRolesByIds(user.getRoleids());
            for (Role role : roles) {
                List<Permission> permissionList = permissionService.getPermissionsByRoleId(role.getId());
                permissions.addAll(permissionList);
            }
            request.getSession().setAttribute("permissions", permissions);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
