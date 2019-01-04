<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="allAgentList" value="false" />
<c:set var="allOrgList" value="false" />
<c:set var="agentList" value="false" />
<c:set var="orgList" value="false" />
<c:forEach items="${permissions}" var="item">
    <%--查看全部代理商明细--%>
    <c:if test="${item.url eq '/followup/all/agent/list'}">
        <c:set var="allAgentList" value="true" />
    </c:if>
    <%--查看全部创客明细--%>
    <c:if test="${item.url eq '/followup/all/org/list'}">
        <c:set var="allOrgList" value="true" />
    </c:if>
    <%--查看代理商明细--%>
    <c:if test="${item.url eq '/followup/org/list'}">
        <c:set var="agentList" value="true" />
    </c:if>
    <%--查看创客明细--%>
    <c:if test="${item.url eq '/followup/org/list'}">
        <c:set var="orgList" value="true" />
    </c:if>
</c:forEach>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>跟进人管理</title>
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
                        <a href="/">首页</a>
                    </li>
                    <li class="active">跟进人管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            跟进人管理
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal">
                        <div class="clearfix maintop">
                            <div class="col-xs-12 col-sm-3 btn-sespan maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-phone"></i>
                                    </span>
                                    <input type="text" name="mobile" class="form-control admin_sea"
                                           value="${param.mobile}" placeholder="手机号"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3 btn-sespan maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-cog"></i>
                                    </span>
                                    <select name="deptId" class="form-control admin_sea">
                                        <option selected="selected" disabled="disabled"  style='display: none' value=''>请选择部门</option>
                                            <c:forEach items="${deptList}" var="item" >
                                                <option value="${item.id}" ${item.pid eq item.id ? 'selected' : ''}>${item.treeName}</option>
                                            </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3 btn-sespan maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-barcode"></i>
                                    </span>
                                    <input type="text" name="inviteCode" class="form-control admin_sea" value="${param.inviteCode}" placeholder="邀请码"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3 btn-sespan maintop">
                                <div class="input-group-btn">
                                    <button type="submit" class="btn btn-xs btn-purple">
                                        <span class="ace-icon fa fa-search"></span>
                                        搜索
                                    </button>
                                    <a href="./list" class="btn btn-xs btn-info">
                                        <span class="ace-icon fa fa-globe"></span>
                                        显示全部
                                    </a>
                                    <c:choose>
                                        <c:when test="${allAgentList==false and allOrgList==true}">
                                            <a class="btn btn-xs btn-success" href="./all/org/list" >
                                                <span class="ace-icon fa fa-external-link"></span>
                                                全部明细
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-xs btn-success" href="./all/agent/list" >
                                                <span class="ace-icon fa fa-external-link"></span>
                                                全部明细
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="space-10"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>跟进人</th>
                                <th class="hidden-xs">邀请码</th>
                                <th>账号</th>
                                <th>所属团队</th>
                                <th colspan="2" style="text-align: center;">跟进客户数(代理商/创客)</th>
                                <th class="tr hidden-xs" colspan="2" style="text-align: center;">资金管理规模(代理商/创客)</th>
                                <th class="hidden-sm hidden-xs">创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.results}">
                                <tr>
                                    <td colspan="12" class="no_data">暂无跟进人</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item">
                                    <tr data-id="${item.id}">
                                        <td>${item.name}</td>
                                        <td class="hidden-xs">${item.inviteCode}</td>
                                        <td>${item.userName}</td>
                                        <td>${item.deptName}</td>
                                        <td>${item.agentNum}</td>
                                        <td>${item.orgNum}</td>
                                        <td class="tr hidden-xs">${item.agentFundFm}</td>
                                        <td class="tr hidden-xs">${item.orgFundFm}</td>
                                        <td class="hidden-sm hidden-xs">
                                            <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${agentList==false and orgList==true}">
                                                    <a href="/followup/agent/list?followId=${item.id}">
                                                        <i class="ace-icon fa fa-external-link"></i> 明细
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="/followup/org/list?followId=${item.id}">
                                                        <i class="ace-icon fa fa-external-link"></i> 明细
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty page.results}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page url="${pageUrl}" type="3"/>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common"], function (common) {
        common.head();
    });
</script>
</body>
</html>