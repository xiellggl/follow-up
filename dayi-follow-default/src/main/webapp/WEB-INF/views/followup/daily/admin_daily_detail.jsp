<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>管理员报表-管理员日报详情</title>
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
                        <a href="/">首页</a>
                    </li>
                    <li>管理员报表</li>
                    <li class="active">管理员日报详情</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <c:if test="${not empty date}">
                    <div class="page-header clearfix">
                        <div class="pull-left">
                            <%--日期：${date}<br />--%>
                            <%--团队：${deptName}--%>
                        </div>
                        <div class="pull-right">
                            <a href="./detail/export?&date=${date}" class="btn btn-xs btn-danger">
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
                                    <th></th>
                                    <th>日期</th>
                                    <th>团队名称</th>
                                    <th>今日新开户</th>
                                    <th>新签创客</th>
                                    <th>入金总额</th>
                                    <th>出金总额</th>
                                    <th>管理资产规模</th>
                                    <th>
                                        资产规模净值
                                        <a href="#" data-toggle="tooltip" title="环比历史最高，当前管理资产规模 - 历史最高资产规模">
                                            <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                        </a>
                                    </th>
                                    <th>
                                        创客管理资产规模
                                        <a href="#" data-toggle="tooltip" title="创客名下所有代理商的协议资金（代理中）之和">
                                            <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                        </a>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty list}">
                                    <tr>
                                        <td colspan="10" class="no_data">暂无数据记录</td>
                                    </tr>
                                </c:if>

                                <c:if test="${not empty list}">
                                    <c:forEach items="${list}" var="item">
                                        <tr>
                                            <td class="collapse_row" data-tag="${item.date}-${item.deptId}" style="cursor: pointer">
                                                <span class="detail-icon">
                                                    <i class="fa fa-plus blue"></i>
                                                </span>
                                            </td>
                                            <td>${item.date}</td>
                                            <td>${item.deptName}</td>
                                            <td>${item.openAccountNum}</td>
                                            <%--新签创客--%>
                                            <td>${item.signOrgNum}</td>
                                            <%--入金总额--%>
                                            <td>${item.inCash}</td>
                                            <%--出金总额--%>
                                            <td>${item.outCash}</td>
                                            <%--管理资产规模--%>
                                            <td>${item.manageFund}</td>
                                            <%--资产规模净值--%>
                                            <td>${item.manageGrowthFund}</td>
                                            <%--创客管理资产规模--%>
                                            <td>${item.makerFund}</td>
                                        </tr>
                                        <c:forEach items="${item.pList}" var="row">
                                            <tr class="${row.date}-${row.deptId}" style="display: none;">
                                                <td></td>
                                                <td>${row.date}</td>
                                                <td>${row.name}</td>
                                                <td>${row.openAccountNum}</td>
                                                    <%--新签创客--%>
                                                <td>${row.signOrgNum}</td>
                                                    <%--入金总额--%>
                                                <td>${row.inCash}</td>
                                                    <%--出金总额--%>
                                                <td>${row.outCash}</td>
                                                    <%--管理资产规模--%>
                                                <td>${row.manageFund}</td>
                                                    <%--资产规模净值--%>
                                                <td>${row.manageGrowthFund}</td>
                                                    <%--创客管理资产规模--%>
                                                <td>${row.makerFund}</td>
                                            </tr>
                                        </c:forEach>
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
                <c:if test="${empty date}">
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

        $(".collapse_row").on("click",function (e) {
            var parentTag = $(this).attr("data-tag");

            if($(this).find('i.fa').hasClass('fa-plus')){
                //收起状态 +
                $(this).find('i.fa').removeClass('fa-plus').addClass(' fa-minus');
                $(this).parents('tr').siblings('.' + parentTag).show();  //点击 + 展开数据
            } else {
                //展开状态
                $(this).find('i.fa').addClass(' fa-plus').removeClass(' fa-minus');
                $(this).parents('tr').siblings('.' + parentTag).hide();
            }
        });
    });
</script>
</body>
</html>