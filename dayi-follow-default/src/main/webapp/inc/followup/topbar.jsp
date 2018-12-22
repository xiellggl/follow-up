<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<div id="navbar" class="navbar navbar-default navbar-fixed-top h-navbar">
    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-header pull-left">
            <a href="/followup/${(flowUpVo.isAdmin eq 1 or flowUpVo.userName eq 'admin') ? 'manage':'uc'}/index"
               class="navbar-brand">
                <small>塑如意跟进人管理系统</small>
            </a>
        </div>
        <div class="navbar-buttons pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li style="display: none;"></li>
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <span class="user-info">
                            <small>
                               你好！${name}
                            </small>
						</span>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close" id="userMenu">
                        <li>
                            <a href="javascript:;" data-toggle="modal" data-act="info" data-target="#myModalInfo">
                                <i class="ace-icon fa fa-user"></i>
                                个人信息
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="javascript:;" data-toggle="modal" data-act="pwd" data-target="#myModalInfo">
                                <i class="ace-icon fa fa-lock"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a id="btnLogout" href="/user/loginout"  data-info="你确定要退出吗？">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>