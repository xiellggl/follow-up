<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<script type="text/javascript">
    try{ace.settings.loadState('main-container')}catch(e){}
</script>
<div id="sidebar" class="sidebar responsive sidebar-fixed ace-save-state">
    <script type="text/javascript">
        try{ace.settings.loadState('sidebar')}catch(e){}
    </script>

<%--    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <!--左侧顶端按钮-->
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <a class="btn btn-success" href="#" role="button" title="文章列表"><i class="ace-icon fa fa-signal"></i></a>
            <a class="btn btn-info" href="#" role="button" title="添加文章"><i class="ace-icon fa fa-pencil"></i></a>
            <a class="btn btn-warning" href="#" role="button" title="会员列表"><i class="ace-icon fa fa-users"></i></a>
            <a class="btn btn-danger" href="#" role="button" title="站点设置"><i class="ace-icon fa fa-cogs"></i></a>
        </div>
        <!--左侧顶端按钮（手机）-->
        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <a class="btn btn-success" href="#" role="button" title="文章列表"></a>
            <a class="btn btn-info" href="#" role="button" title="添加文章"></a>
            <a class="btn btn-warning" href="#" role="button" title="会员列表"></a>
            <a class="btn btn-danger" href="#" role="button" title="站点设置"></a>
        </div>
    </div>--%>

    <div class="sidebar-shortcuts">
        <h5>
            <i class="menu-icon glyphicon glyphicon-th-large"></i>
            <span class="sidebar-shortcuts-large">导航菜单栏</span>
        </h5>
    </div>

    <!-- 菜单列表开始 -->
    <ul class="nav nav-list" id="navSide">
        <c:if test="${flowUpSession.userName ne 'admin' and flowUpSession.isAdmin ne 1}">
        <li data-rel="home">
            <a href="/followup/uc/index">
                <i class="menu-icon fa fa-home"></i>
                <span class="menu-text">首页</span>
            </a>
        </li>
        <li data-rel="myCustomer">
            <a href="javascript:void(0);" class="dropdown-toggle">
                <i class="menu-icon fa fa-users"></i>
                <span class="menu-text">我的客户</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <ul class="submenu">
                <li data-index="1">
                    <a href="/followup/uc/customer/agent/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        代理商
                    </a>
                </li>
                <li data-index="2">
                    <a href="/followup/uc/customer/maker/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        创客
                    </a>
                </li>
            </ul>
        </li>
        </c:if>
        <c:if test="${flowUpSession.userName ne 'admin' and flowUpSession.isAdmin ne 1 and flowUpSession.isManager ne 1}">
            <li data-rel="myLog">
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <i class="menu-icon fa fa-bar-chart-o"></i>
                    <span class="menu-text">我的报表</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li data-index="1">
                        <a href="/followup/uc/log/mydaily">
                            <i class="menu-icon fa fa-caret-right"></i>
                            日报
                        </a>
                    </li>
                    <li data-index="2">
                        <a href="/followup/uc/log/weekly/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            周报
                        </a>
                    </li>
                    <li data-index="3">
                        <a href="/followup/uc/log/monthly/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            月报
                        </a>
                    </li>

                </ul>
            </li>
        </c:if>
        <c:if test="${flowUpSession.userName ne 'admin' and flowUpSession.isAdmin ne 1 and flowUpSession.isManager eq 1}">
        <li data-rel="teamCustomer">
            <a href="javascript:void(0);" class="dropdown-toggle">
                <i class="menu-icon fa fa-users"></i>
                <span class="menu-text">团队客户</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <ul class="submenu">
                <li data-index="1">
                    <a href="/followup/uc/customer/team/agent/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        代理商
                    </a>
                </li>
                <li data-index="2">
                    <a href="/followup/uc/customer/team/maker/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        创客
                    </a>
                </li>
            </ul>
        </li>
            <c:if test="${flowUpSession.deptName eq '客户服务组'}">
                <li data-rel="followuperAssign">
                    <a href="javascript:void(0);" class="dropdown-toggle">
                        <i class="menu-icon fa fa-users"></i>
                        <span class="menu-text">跟进人分配</span>
                        <b class="arrow fa fa-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <li data-index="1">
                            <a href="/followup/manage/followuper/assign/list">
                                <i class="menu-icon fa fa-caret-right"></i>
                                代理商
                            </a>
                        </li>
                        <li data-index="2">
                            <a href="/followup/manage/followuper/assign/list?flowTypeTab=2">
                                <i class="menu-icon fa fa-caret-right"></i>
                                创客
                            </a>
                        </li>
                    </ul>
                </li>
            </c:if>
            <li data-rel="teamLog">
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <i class="menu-icon fa fa-bar-chart-o"></i>
                    <span class="menu-text">团队报表</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li data-index="1">
                        <a href="/followup/uc/log/teamdaily">
                            <i class="menu-icon fa fa-caret-right"></i>
                            日报
                        </a>
                    </li>
                    <li data-index="2">
                        <a href="/followup/uc/log/weekly/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            周报
                        </a>
                    </li>
                    <li data-index="3">
                        <a href="/followup/uc/log/monthly/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            月报
                        </a>
                    </li>

                </ul>
            </li>
        </c:if>

        <c:if test="${flowUpSession.userName eq 'admin' || flowUpSession.isAdmin eq 1}"> <%-- 超级管理员 或 部门管理员 --%>
        <li data-rel="manageHome">
            <a href="/followup/manage/index">
                <i class="menu-icon fa fa-home"></i>
                <span class="menu-text">首页</span>
            </a>
        </li>
        <li data-rel="followuperAssign">
            <a href="javascript:void(0);" class="dropdown-toggle">
                <i class="menu-icon fa fa-users"></i>
                <span class="menu-text">跟进人分配</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <ul class="submenu">
                <li data-index="1">
                    <a href="/followup/manage/followuper/assign/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        代理商
                    </a>
                </li>
                <li data-index="2">
                    <a href="/followup/manage/followuper/assign/list?flowTypeTab=2">
                        <i class="menu-icon fa fa-caret-right"></i>
                        创客
                    </a>
                </li>
            </ul>
        </li>
        <li data-rel="followuper">
            <a href="/followup/manage/followuper/list">
                <i class="menu-icon fa fa-cogs"></i>
                <span class="menu-text">跟进人管理</span>
            </a>
        </li>

        <c:if test="${flowUpSession.isAdmin eq 1}">
            <li data-rel="manageLog">
                <a href="/followup/manage/log/daily/list">
                    <i class="menu-icon fa fa-bar-chart-o"></i>
                    <span class="menu-text">团队日报</span>
                </a>
            </li>
        </c:if>
        </c:if>
        <c:if test="${flowUpSession.userName eq 'admin'}"> <%-- 超级管理员 --%>
        <li data-rel="manageLog">
            <a href="javascript:void(0);" class="dropdown-toggle">
                <i class="menu-icon fa fa-bar-chart-o"></i>
                <span class="menu-text">团队报表</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <ul class="submenu">
                <li data-index="1">
                    <a href="/followup/manage/log/daily/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        日报
                    </a>
                </li>

                <li data-index="2">
                    <a href="/followup/manage/log/weekly/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        周报
                    </a>
                </li>
                <li data-index="3">
                    <a href="/followup/manage/log/monthly/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        月报
                    </a>
                </li>

            </ul>
        </li>
        <li data-rel="system">
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <i class="menu-icon fa fa-bar-chart-o"></i>
                    <span class="menu-text">系统管理</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li data-index="1">
                        <a href="/followup/sys/module/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            模块管理
                        </a>
                    </li>

                    <li data-index="2">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            用户管理
                        </a>
                    </li>
                    <li data-index="3">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            系统日志
                        </a>
                    </li>
                    <li data-index="4">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            角色管理
                        </a>
                    </li>

                </ul>
        </li>
        <li data-rel="manageDept">
            <a href="/followup/manage/dept/list">
                <i class="menu-icon fa fa-cog"></i>
                <span class="menu-text">部门管理</span>
            </a>
        </li>
        <li data-rel="manageManager">
            <a href="/followup/manage/manager/list">
                <i class="menu-icon fa fa-user"></i>
                <span class="menu-text">管理员管理</span>
            </a>
        </li>
        </c:if>
    </ul><!-- 菜单列表结束 -->

    <!--菜单栏缩进开始-->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>