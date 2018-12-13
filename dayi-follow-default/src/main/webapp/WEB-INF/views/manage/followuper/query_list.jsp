<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>跟进人查询</title>
    <%@include file="/inc/followup/csslink.jsp"%>
</head>
<body class="no-skin">
<%@include file="/inc/followup/topbar.jsp"%>
<div class="main-container" id="main-container">
    <%@include file="/inc/followup/sidebar.jsp"%>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li class="active">跟进人查询</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            跟进人查询
                        </small>
                    </h1>
                </div>

                <div class="row">
                    <form class="form-horizontal">
                        <div class="clearfix maintop">

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="当前跟进人"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="更变跟进人"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="名称"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="手机号"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="邀请码"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date"
                                           value="${param.date}" placeholder="变更日期"/>
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-xs btn-purple">
                                            <span class="ace-icon fa fa-search"></span>
                                            搜索
                                        </button>
                                        <a href="?" class="btn btn-xs btn-info">
                                            <span class="ace-icon fa fa-globe"></span>
                                            导出
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>

                <div class="space-10"></div>

                <div class="tabbable">
                    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">

                        <li class="active">
                            <a data-toggle="tab" href="#profile4" aria-expanded="false">代理商</a>
                        </li>

                        <li class="">
                            <a data-toggle="tab" href="#home4" aria-expanded="true">创客</a>
                        </li>

                    </ul>

                    <div class="tab-content">

                        <div id="profile4" class="tab-pane active">
                            <div id="conList"></div>
                        </div>

                        <div id="home4" class="tab-pane">
                            <div id="logList"></div>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common", "template", "validate", "addMethod"], function (common, template) {
        var agentId = ${param.agentId};
        common.head("teamCustomer",1);

        var $logList = $("#logList");
        var log_url = "/followup/uc/customer/agent/loginlog?agentId=" + agentId;
        common.loadPageHTML(log_url, null,$logList);
        common.clickPageFn(log_url, null, $logList);


        var $conList = $("#conList");
        var con_url = "/followup/uc/customer/agent/contact?agentId=" + agentId;
        common.loadPageHTML(con_url, null,$conList);
        common.clickPageFn(con_url, null, $conList);
    });
</script>
</body>
</html>