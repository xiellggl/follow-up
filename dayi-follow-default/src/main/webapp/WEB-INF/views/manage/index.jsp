<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>系统管理</title>
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
                    <li class="active">
                        <i class="ace-icon fa fa-home home-icon"></i>
                        首页控制台
                    </li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="space-6"></div>
                            <div class="col-sm-4">
                                <h3 class="header smaller lighter green">
                                    <i class="ace-icon fa fa-bullhorn"></i>
                                    客户信息
                                </h3>
                                <div class="infobox-container">
                                    <div class="infobox infobox-blue2">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-comments"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <c:choose>
                                                <c:when test="${'admin' eq flowUpSession.userName}">
                                            <span class="infobox-data-number">${fUCountVo.flowUpCount}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="infobox-data-number">****</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="infobox-content">跟进人数量</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-red">
                                        <div class="infobox-icon">
                                            <i class="ace-icon glyphicon glyphicon-transfer"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <c:choose>
                                                <c:when test="${'admin' eq flowUpSession.userName}">
                                                <span class="infobox-data-number">${fUCountVo.waitAssignCusNum}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="infobox-data-number">****</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="infobox-content">待分配用户</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-pink">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-users"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <c:choose>
                                                <c:when test="${'admin' eq flowUpSession.userName}">
                                                    <span class="infobox-data-number">${fUCountVo.agentCount}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="infobox-data-number">****</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="infobox-content">跟进用户总数</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-green">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-check"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <c:choose>
                                                <c:when test="${'admin' eq flowUpSession.userName}">
                                            <span class="infobox-data-number">${fUCountVo.idCardTotal}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="infobox-data-number">****</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="infobox-content">已认证</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-green">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-credit-card"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <c:choose>
                                                <c:when test="${'admin' eq flowUpSession.userName}">
                                            <span class="infobox-data-number">${fUCountVo.signTotal}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="infobox-data-number">****</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="infobox-content">已绑卡</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-green">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-heart"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <c:choose>
                                                <c:when test="${'admin' eq flowUpSession.userName}">
                                            <span class="infobox-data-number">${fUCountVo.agentTotal}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="infobox-data-number">****</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="infobox-content">已代理</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-purple">
                                        <div class="infobox-icon">
                                            <i class="ace-icon glyphicon glyphicon-yen"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <c:choose>
                                                <c:when test="${'admin' eq flowUpSession.userName}">
                                            <span class="infobox-data-number">${fUCountVo.totalAssertsFormat}</span>
                                                </c:when>
                                                <c:otherwise>
                                                <span class="infobox-data-number">****</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="infobox-content">资产总规模</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <h3 class="header smaller lighter green">
                                    <i class="ace-icon fa fa-signal"></i>
                                    团队日报
                                </h3>
                                <div class="col-xs-12">
                                    <ul class="item-list">
                                        <c:forEach items="${sellDaily}" var="item">
                                            <li>
                                                <h4 class="header smaller lighter clearfix">
                                                    <span class="pull-left">${item.deptName}（${item.date}）</span>
                                                    <a href="/followup/manage/log/daily/detail?did=${item.deptId}&date=${item.date}" class="pull-right" style="font-size: 14px;">
                                                        <i class="ace-icon fa fa-external-link"></i> 查看详情
                                                    </a>
                                                </h4>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <p>新开户数：${item.newOpenNum}</p>
                                                        <p>入金人数：${item.inCashNum}</p>
                                                        <p>实际出金人数：${item.outCashNum}</p>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <c:choose>
                                                            <c:when test="${'admin' eq flowUpSession.userName}">
                                                                <p>入金总额：${item.cyberBankTotalInCash}</p>
                                                                <p>实际出金总额：${item.totalApplyOutCash}</p>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p>入金总额：${item.cyberBankTotalInCashFormat}</p>
                                                                <p>实际出金总额：${item.totalApplyOutCashFormat}</p>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                    <%--<div class="col-sm-3">
                                                        <h5>入金前三</h5>
                                                        <c:forEach items="${item.inCashList}" var="sub">
                                                            <p>${sub}</p>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <h5>开户数前三</h5>
                                                        <c:forEach items="${item.openAccountList}" var="sub">
                                                            <p>${sub}</p>
                                                        </c:forEach>
                                                    </div>--%>
                                                </div>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${orgCount!=null}">
                                        <li>
                                            <h4 class="header smaller lighter clearfix">
                                                <c:choose>
                                                    <c:when test="${orgCount.cyberBankTotalInCash!=null}">
                                                        <span class="pull-left">KA及渠道部（${orgCount.date}）</span>
                                                        <a href="/followup/manage/log/daily/detail?did=${orgCount.deptId}&date=${orgCount.date}" class="pull-right" style="font-size: 14px;">
                                                            <i class="ace-icon fa fa-external-link"></i> 查看详情
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="pull-left">KA及渠道部</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </h4>
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <c:choose>
                                                    <c:when test="${'admin' eq flowUpSession.userName}">
                                                        <p>入金总额：${orgCount.cyberBankTotalInCash}</p>
                                                        <p>入金人数：${orgCount.inCashNum}</p>
                                                        <p>实际出金总额：${orgCount.totalApplyOutCash}</p>
                                                        <p>实际出金人数：${orgCount.outCashNum}</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p>入金总额：${orgCount.cyberBankTotalInCashFormat}</p>
                                                        <p>入金人数：${orgCount.inCashNum}</p>
                                                        <p>实际出金总额：${orgCount.totalApplyOutCashFormat}</p>
                                                        <p>实际出金人数：${orgCount.outCashNum}</p>
                                                    </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col-sm-6">
                                                    <p>创客数量：${orgCount.kaOrgNum}</p>
                                                    <p>创客有效代理商：${orgCount.kaOrgValidAgentNum}</p>
                                                    <c:choose>
                                                        <c:when test="${'admin' eq flowUpSession.userName}">
                                                            <p>创客管理资金规模：${orgCount.kaOrgManageMoney}</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p>创客管理资金规模：${orgCount.kaOrgManageMoneyFormat}</p>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </li>
                                        </c:if>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use("common",function (common) {
        common.head();
    });
</script>
</body>
</html>