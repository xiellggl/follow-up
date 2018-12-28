<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>团队报表-团队日报</title>
    <%@include file="/inc/followup/csslink.jsp" %>
</head>
<body class="no-skin">
<%@include file="/inc/followup/topbar.jsp" %>
<div class="main-container" id="main-container">
    <%@include file="/inc/followup/sidebar.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/index">首页</a>
                    </li>
                    <li class="active">团队日报详情</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <c:if test="${not empty date and not empty deptId}">
                    <div class="page-header clearfix">
                        <div class="pull-left">
                            日期：${date}<br />
                            团队：${deptName}
                        </div>
                        <div class="pull-right">
                            <a href="./detail/export?deptId=${deptId}&date=${date}" class="btn btn-xs btn-danger">
                                <span class="ace-icon glyphicon glyphicon-export"></span>
                                一键导出
                            </a>
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="row" id="listPan">
                        <div class="col-xs-12">
                            <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>今日新开户</th>
                                    <th>创客净增资金规模</th>
                                    <th>入金总额</th>
                                    <th>入金人数</th>
                                    <th>实际出金总额</th>
                                    <th>实际出金人数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty page.results}">
                                    <tr>
                                        <td colspan="9" class="no_data">暂无数据记录</td>
                                    </tr>
                                </c:if>

                                <c:if test="${not empty page.results}">
                                    <c:forEach items="${page.results}" var="item">
                                        <tr>
                                            <td>${item.name}</td>
                                            <td>${item.openAccountNum}</td>
                                            <td>${item.manageGrowthFund}</td>
                                            <td>${item.inCash}</td>
                                            <td>${item.inCashNum}</td>
                                            <td>${item.outCash}</td>
                                            <td>${item.outCashNum}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                            <c:if test="${not empty page}">
                                <div class="pagerBar" id="pagerBar">
                                    <common:page url="${pageUrl}" type="3"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty date or empty deptId}">
                    <div class="page-header clearfix">
                        <h1 class="pull-left">
                            您当前操作
                            <small>
                                <i class="ace-icon fa fa-angle-double-right"></i>
                            </small>
                            <span class="orange">
                                <i class="ace-icon fa fa-exclamation-triangle"></i>
                                参数出错啦！
                            </span>
                        </h1>
                        <a href="./list" class="pull-right">
                            <span class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-arrow-left"></i>
                                返回列表
                            </span>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</div>


<%@include file="/inc/followup/script.jsp" %>
<script>
    seajs.use(["common"], function (common) {
        common.head('_report_admin_daily');
    });
</script>
</body>
</html>