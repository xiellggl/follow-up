<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title><c:if test="${flowUpSession.userName eq 'admin'}">团队报表-</c:if>团队日报</title>
    <%@include file="/inc/followup/csslink.jsp" %>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>
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
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <c:if test="${flowUpSession.userName eq 'admin'}">
                        <li>团队报表</li>
                    </c:if>
                    <li class="active">团队日报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            团队日报
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal">
                        <div class="clearfix maintop">
                            <div class="col-xs-12 col-sm-3 maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value="${param.date}"
                                           placeholder="日志日期"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                                    <input type="text" name="deptName" class="form-control search-query admin_sea" value="${param.deptName}" placeholder="团队"/>
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-xs btn-purple">
                                            <span class="ace-icon fa fa-search"></span>
                                            搜索
                                        </button>
                                        <a href="?" class="btn btn-xs btn-info">
                                            <span class="ace-icon fa fa-globe"></span>
                                            显示全部
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>日期</th>
                                <th>团队名称</th>
                                <th>今日新开户</th>
                                <th>创客净增资金规模</th>
                                <th>入金总额</th>
                                <th>入金人数</th>
                                <th>实际出金总额</th>
                                <th>实际出金人数</th>
                                <th>操作</th>
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
                                            <td>${item.date}</td>
                                            <td>${item.deptName}</td>
                                            <td>${item.openAccountNum}</td>
                                            <td>${item.manageGrowthFund}</td>
                                            <td>${item.inCash}</td>
                                            <td>${item.inCashNum}</td>
                                            <td>${item.outCash}</td>
                                            <td>${item.outCashNum}</td>
                                        <td>
                                            <a href="./daily/detail?deptId=${item.deptId}&date=${item.date}">
                                                <i class="ace-icon fa fa-external-link"></i> 查看
                                            </a>
                                        </td>
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
            </div>
        </div>
    </div>
</div>
</div>


<%@include file="/inc/followup/script.jsp" %>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "daterangepicker"], function (common) {
        common.head();
        var date_o = {
            autoUpdateInput: false,
            locale: locale_cn,
        };
        date_o.locale.cancelLabel = "清空";
        $('.dates').daterangepicker(date_o);

        $('.dates').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
        });

        $('.dates').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });
    });
</script>
</body>
</html>