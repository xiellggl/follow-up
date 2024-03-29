<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>创客跟进人查询</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>
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
                    <li>跟进人管理</li>
                    <li class="active">创客跟进人查询</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            创客跟进人查询
                        </small>
                    </h1>
                    <a class="btn btn-xs pull-right" href="/followup/list">返回</a>
                </div>

                <div class="row">
                    <form class="form-horizontal">
                        <c:if test="${not empty param.followId}">
                            <input type="hidden" class="" name="followId" value="${param.followId}"/>
                        </c:if>
                        <c:if test="${empty param.followId}">
                            <div class="col-xs-12 col-sm-4 btn-sespan maintop">
                                <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ace-icon fa fa-user"></i>
                                </span>
                                    <input type="text" class="form-control admin_sea" name="followUp" value="${param.followUp}"
                                           placeholder="当前跟进人"/>
                                </div>
                            </div>
                        </c:if>

                        <div class="col-xs-12 col-sm-4 btn-sespan maintop">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ace-icon fa fa-user"></i>
                                </span>
                                <input type="text" class="form-control admin_sea" name="followUpBefore" value="${param.followUpBefore}"
                                       placeholder="变更前跟进人"/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-4 btn-sespan maintop">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ace-icon fa fa-user"></i>
                                </span>
                                <input type="text" class="form-control admin_sea" name="cusName" value="${param.cusName}"
                                       placeholder="名称"/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-4 btn-sespan maintop">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ace-icon fa fa-mobile"></i>
                                </span>
                                <input type="text" class="form-control admin_sea" name="mobile" value="${param.mobile}"
                                       placeholder="手机号"/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-4 btn-sespan maintop">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ace-icon fa fa-calendar"></i>
                                </span>
                                <input type="text" class="form-control admin_sea dates" name="changeDate"
                                       value="${param.changeDate}" placeholder="变更日期"/>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-4 btn-sespan maintop">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ace-icon fa fa-barcode"></i>
                                </span>
                                <input type="text" name="inviteCode" class="form-control admin_sea"
                                       value="${inviteCode}" placeholder="邀请码"/>
                                <div class="input-group-btn">
                                    <button type="submit" id="storeButton" class="btn btn-xs btn-purple">
                                        <span class="ace-icon fa fa-search"></span>
                                        搜索
                                    </button>
                                    <a href="${requestURI}/export?${queryString}" class="btn btn-xs btn-info">
                                        <span class="ace-icon fa fa-globe"></span>
                                        导出
                                    </a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>


                <div class="space-10"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <a class="btn btn-white" href="../agent/list${not empty param.followId ? '?followId=' : ''}${param.followId}">代理商</a>
                        <a class="btn btn-white btn-info" href="../org/list${not empty param.followId ? '?followId=' : ''}${param.followId}">创客</a>
                    </div>
                </div>
                <div class="space-10"></div>

                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" >
                            <thead>
                            <tr>
                                <th>会员ID</th>
                                <th>名称</th>
                                <th>手机号</th>
                                <th class="hidden-xs">注册时间</th>
                                <th>邀请码</th>
                                <th>当前跟进人</th>
                                <th>变更前跟进人</th>
                                <th class="hidden-xs">变更日期</th>
                                <th>变更前代理货值</th>
                                <th>当前代理货值</th>
                                <th>净增代理货值</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:if test="${empty page.results}">
                                <tr>
                                    <td colspan="11" class="no_data">暂无数据</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item" >
                                    <tr  data-id="${item.id}">
                                            <%-- 会员ID --%>
                                        <td>${item.id}</td>
                                            <%--名称--%>
                                        <td><c:if test="${not empty item.linkPerson}">${item.linkPerson}</c:if></td>
                                            <%-- 手机号 --%>
                                        <td>${item.mobile}</td>
                                            <%-- 注册时间 --%>
                                        <td class="hidden-xs"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <%--邀请码--%>
                                        <td>${item.inviteCode}</td>
                                            <%--当前跟进人--%>
                                        <td>${item.followUp}</td>
                                            <%--变更前跟进人--%>
                                        <td>${item.followUpBefore}</td>
                                            <%--变更日期--%>
                                        <td class="hidden-xs"><fmt:formatDate value="${item.changeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <%--变更前代理货值--%>
                                        <td><fmt:formatNumber value="${item.agentCargoBefore}" pattern="#,##0.##" type="number"/></td>
                                            <%--当前代理货值--%>
                                        <td><fmt:formatNumber value="${item.agentCargo}" pattern="#,##0.##" type="number"/></td>
                                            <%--净增代理货值--%>
                                        <td><fmt:formatNumber value="${item.growthCargo}" pattern="#,##0.##" type="number"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>

                        <c:if test="${not empty page.results}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page url="${pageUrl}" type="3" />
                            </div>
                        </c:if>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common","daterangepicker"], function (common) {
        //菜单高亮
        common.head("_followup_list");

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