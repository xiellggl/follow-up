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





        <li data-rel="home">
            <a href="/index">
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
                    <a href="/agent/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        代理商
                    </a>
                </li>
                <li data-index="2">
                    <a href="/org/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        创客
                    </a>
                </li>
            </ul>
        </li>
            <li data-rel="myLog">
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <i class="menu-icon fa fa-bar-chart-o"></i>
                    <span class="menu-text">我的报表</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li data-index="1">
                        <a href="/report/daily">
                            <i class="menu-icon fa fa-caret-right"></i>
                            日报
                        </a>
                    </li>
                    <li data-index="2">
                        <a href="/report/week">
                            <i class="menu-icon fa fa-caret-right"></i>
                            周报
                        </a>
                    </li>
                    <li data-index="3">
                        <a href="/report/month">
                            <i class="menu-icon fa fa-caret-right"></i>
                            月报
                        </a>
                    </li>

                </ul>
            </li>
        <li data-rel="teamCustomer">
            <a href="javascript:void(0);" class="dropdown-toggle">
                <i class="menu-icon fa fa-users"></i>
                <span class="menu-text">团队客户</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <ul class="submenu">
                <li data-index="1">
                    <a href="/team/agent/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        代理商
                    </a>
                </li>
                <li data-index="2">
                    <a href="/team/org/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        创客
                    </a>
                </li>
            </ul>
        </li>

            <li data-rel="teamLog">
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <i class="menu-icon fa fa-bar-chart-o"></i>
                    <span class="menu-text">团队报表</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li data-index="1">
                        <a href="/report/team/daily">
                            <i class="menu-icon fa fa-caret-right"></i>
                            日报
                        </a>
                    </li>
                    <li data-index="2">
                        <a href="/report/team/week">
                            <i class="menu-icon fa fa-caret-right"></i>
                            周报
                        </a>
                    </li>
                    <li data-index="3">
                        <a href="/report/team/month">
                            <i class="menu-icon fa fa-caret-right"></i>
                            月报
                        </a>
                    </li>

                </ul>
            </li>
        <li data-rel="followuperAssign">
            <a href="javascript:void(0);" class="dropdown-toggle">
                <i class="menu-icon fa fa-users"></i>
                <span class="menu-text">跟进人分配</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <ul class="submenu">
                <li data-index="1">
                    <a href="/agent/assign/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        代理商
                    </a>
                </li>
                <li data-index="2">
                    <a href="/org/assign/list">
                        <i class="menu-icon fa fa-caret-right"></i>
                        创客
                    </a>
                </li>
            </ul>
        </li>



        <li data-rel="manageLog">
            <a href="javascript:void(0);" class="dropdown-toggle">
                <i class="menu-icon fa fa-bar-chart-o"></i>
                <span class="menu-text">管理员团队报表</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <ul class="submenu">
                <li data-index="1">
                    <a href="/report/admin/daily">
                        <i class="menu-icon fa fa-caret-right"></i>
                        日报
                    </a>
                </li>

                <li data-index="2">
                    <a href="/report/admin/week">
                        <i class="menu-icon fa fa-caret-right"></i>
                        周报
                    </a>
                </li>
                <li data-index="3">
                    <a href="/report/admin/month">
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
                        <a href="/module/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            模块管理
                        </a>
                    </li>
                    <li data-index="10">
                        <a href="/permission/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            功能管理
                        </a>
                    </li>

                    <li data-index="2">
                        <a href="/user/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            用户管理
                        </a>
                    </li>
                    <li data-index="3">
                        <a href="/operatelog/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            系统日志
                        </a>
                    </li>
                    <li data-index="4">
                        <a href="/role/list">
                            <i class="menu-icon fa fa-caret-right"></i>
                            角色管理
                        </a>
                    </li>

                </ul>
        </li>
        <li data-rel="manageDept">
            <a href="/dept/list">
                <i class="menu-icon fa fa-cog"></i>
                <span class="menu-text">部门管理</span>
            </a>
        </li>
        <li data-rel="followuper">
            <a href="/followup/list">
                <i class="menu-icon fa fa-user"></i>
                <span class="menu-text">跟进人管理</span>
            </a>
        </li>
    </ul><!-- 菜单列表结束 -->

    <!--菜单栏缩进开始-->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>