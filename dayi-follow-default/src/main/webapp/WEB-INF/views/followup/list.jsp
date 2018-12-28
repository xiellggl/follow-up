<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
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
                        <a href="/index">首页</a>
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
                    <form class="form-horizontal" style="max-width: 1000px;">
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
                                    <select name="myDeptId" class="form-control admin_sea">
                                            <c:forEach items="${deptList}" var="item" >
                                                <option value="${item.id}" ${deptVo.pid eq item.id ? 'selected' : ''}>${item.treeName}</option>
                                            </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3 btn-sespan maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-barcode"></i>
                                    </span>
                                    <input type="text" name="inviteCode" class="form-control admin_sea" value="${inviteCode}" placeholder="邀请码"/>
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
                                    <a class="btn btn-xs btn-success" href="/followup/all/agent/list" >
                                        <span class="ace-icon fa fa-external-link"></span>
                                        全部明细
                                    </a>
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
                                            <a href="/followup/agent/list?followId=${item.id}">
                                                <i class="ace-icon fa fa-external-link"></i> 明细
                                            </a>
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