<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="teamDetailAgent" value="false" />
<c:set var="teamContactAgent" value="false" />
<c:set var="teamLoginlogAgent" value="false" />
<c:forEach items="${permissions}" var="item">
    <%--查看团队代理商详情--%>
    <c:if test="${item.url eq '/team/agent/detail'}">
        <c:set var="teamDetailAgent" value="true" />
    </c:if>
    <%--查看团队代理商联系记录--%>
    <c:if test="${item.url eq '/team/agent/contact'}">
        <c:set var="teamContactAgent" value="true" />
    </c:if>
    <%--查看团队代理商登录日志--%>
    <c:if test="${item.url eq '/team/agent/loginlog'}">
        <c:set var="teamLoginlogAgent" value="true" />
    </c:if>
</c:forEach>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>团队客户-代理商明细</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>
    <style>
        .customer-info tr th{text-align: right;}
    </style>
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
                        <a href="/followup/uc/index">首页</a>
                    </li>
                    <li>
                        <a href="./list">团队客户-代理商</a>
                    </li>
                    <li class="active">明细</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <%--查看团队代理商详情--%>
            <c:if test="${teamDetailAgent}">
                <div class="page-content">
                    <c:set var="pageType" value="team" />
                    <%@include file="../agent_detail_inc.jsp"%>
                </div>
            </c:if>

            <c:if test="${teamLoginlogAgent or teamContactAgent}">
                <div class="page-content">
                    <div class="tabbable">
                        <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                                <%--查看团队代理商联系记录--%>
                            <c:if test="${teamContactAgent}">
                                <li class="active">
                                    <a data-toggle="tab" href="#profile4" aria-expanded="false">联系记录</a>
                                </li>
                            </c:if>
                                <%--查看团队代理商登录日志--%>
                            <c:if test="${teamLoginlogAgent}">
                                <li class="">
                                    <a data-toggle="tab" href="#home4" aria-expanded="true">登录日志</a>
                                </li>
                            </c:if>

                        </ul>

                        <div class="tab-content">
                                <%--查看团队代理商联系记录--%>
                            <c:if test="${teamContactAgent}">
                                <div id="profile4" class="tab-pane active">
                                    <div id="conList"></div>
                                </div>
                            </c:if>
                                <%--查看团队代理商登录日志--%>
                            <c:if test="${teamLoginlogAgent}">
                                <div id="home4" class="tab-pane">
                                    <div id="logList"></div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script charset="UTF-8" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "template", "validate", "addMethod","daterangepicker"], function (common, template) {
        var agentId = ${param.agentId};
        common.head();

        <c:if test="${teamLoginlogAgent}">
        var $logList = $("#logList");
        var log_url = "/agent/loginlog?agentId=" + agentId;
        common.loadPageHTML(log_url, null,$logList);
        common.clickPageFn(log_url, null, $logList);
        </c:if>

        <c:if test="${teamContactAgent}">
        var $conList = $("#conList");
        var con_url = "/agent/contact?agentId=" + agentId;
        common.loadPageHTML(con_url, null,$conList);
        common.clickPageFn(con_url, null, $conList);
        </c:if>
    });
</script>
</body>
</html>