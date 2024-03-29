<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="highseaSet" value="false" />
<c:forEach items="${permissions}" var="item">
    <%--团队代理商分配--%>
    <c:if test="${item.url eq '/highsea/getconfig'}">
        <c:set var="highseaSet" value="true" />
    </c:if>
</c:forEach>
<c:set var="drag" value="false" />
<c:forEach items="${permissions}" var="item">
    <%--团队代理商分配--%>
    <c:if test="${item.url eq '/highsea/drag'}">
        <c:set var="drag" value="true" />
    </c:if>
</c:forEach>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公海客户列表</title>
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
                    <li>公海客户</li>
                    <li class="active">公海客户列表</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            公海客户列表
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal" >
                        <div class="clearfix maintop">
                            <div class="col-sm-3 hidden-xs btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="createDate" value="${param.createDate}"
                                           placeholder="注册时间"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-phone"></i>
                                    </span>
                                    <input type="text" name="mobile" class="form-control admin_sea"
                                           value="${param.mobile}" placeholder="手机号码"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check-square-o"></i>
                                    </span>
                                    <select name="customerType" class="form-control admin_sea">
                                        <option value="">客户类型</option>
                                        <c:forEach items="${cusType}" var="item">
                                            <option value="${item.value}" <c:if test="${param.customerType eq item.value}">selected</c:if>>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check-square-o"></i>
                                    </span>
                                    <select name="inCash">
                                        <option value="">入金状态</option>
                                        <option value="1" ${param.inCash=='1'?"selected":''}>已入金</option>
                                        <option value="0" ${param.inCash=='0'?"selected":''}>未入金</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix maintop">
                            <div class="col-sm-3 hidden-xs btn-sespan assignDateDiv">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="warehouseDate" value="${param.warehouseDate}"
                                           placeholder="入库时间"/>
                                </div>
                            </div>
                            <div>
                                <input type="hidden" name="orderType" value="">
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                                    <select name="idCardValidate">
                                        <option value="">实名状态</option>
                                        <option value="1"  ${param.idCardValidate=='1'?"selected":''}>已认证</option>
                                        <option value="0"  ${param.idCardValidate=='0'?"selected":''}>未认证</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-credit-card"></i>
                                    </span>
                                    <select name="bankSign">
                                        <option value="">绑卡状态</option>
                                        <option value="1"  ${param.bankSign=='1'?"selected":''}>已绑定</option>
                                        <option value="0"  ${param.bankSign=='0'?"selected":''}>未绑定</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="btn-group dropup">
                                    <button type="submit" class="btn btn-xs btn-purple">
                                        <span class="ace-icon fa fa-search"></span>
                                        搜索
                                    </button>
                                    <c:if test="${highseaSet eq true}">
                                    <a href="/highsea/getconfig" class="btn btn-xs btn-primary" style="margin-left: 20px">公海设置</a>
                                    </c:if>
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
                                <th>姓名</th>
                                <th>邀请码</th>
                                <th>实名认证</th>
                                <th>绑卡</th>
                                <th>入金</th>
                                <th>客户类型</th>
                                <th class="hidden-xs">
                                    注册时间
                                    <span class="glyphicon glyphicon-triangle-top small-hand ml5 orderUp" aria-hidden="true"></span>
                                    <span class="glyphicon glyphicon-triangle-bottom small-hand ml5 orderDown" aria-hidden="true"></span>
                                </th>
                                <th class="hidden-sm hidden-xs">入库时间</th>
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
                                <c:forEach items="${page.results}" var="item" >
                                    <tr>
                                        <!-- 姓名 -->
                                        <td>${item.linkPersonFm}</td>
                                        <!-- 邀请码 -->
                                        <td>${item.inviteCode}</td>

                                        <%-- 实名认证 --%>
                                        <td>
                                            <c:choose>
                                                <c:when test="${!empty item.idCard }">
                                                    <span class="green" data-toggle="tooltip"
                                                          title="认证时间：<fmt:formatDate value='${item.cardValidDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                                        <i class="ace-icon fa fa-check"></i>
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="ace-icon fa fa-times red"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <%-- 是否绑卡 --%>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.bankSign eq true}">
                                                    <span class="green" data-toggle="tooltip"
                                                          title="绑卡时间：<fmt:formatDate value='${item.bankSignDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                                        <i class="ace-icon fa fa-check"></i>
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="ace-icon fa fa-times red"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <%-- 是否入金 --%>
                                        <td>
                                            <c:choose>
                                                <c:when test="${not empty item.fristInCashDate and item.inCash > 0}">
                                                    <span class="green" data-toggle="tooltip"
                                                          title="首次入金时间：<fmt:formatDate value='${item.fristInCashDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                                        <i class="ace-icon fa fa-check"></i>
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="ace-icon fa fa-times red"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <%--客户类型--%>
                                        <td>${item.customerTypeStr}</td>
                                        <!-- 注册时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 入库时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.warehouseDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 操作 -->
                                        <td>
                                            <c:if test="${drag}">
                                            <button type="button" class="btn btn-primary dragCustomer" data-id="${item.id}">认领客户</button>
                                            </c:if>
                                        </td>
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
</div>

<%@include file="/inc/followup/script.jsp"%>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "daterangepicker"], function (common) {
        //菜单高亮
        common.head();

        var date_o = {
            autoUpdateInput: false,
            locale: locale_cn
        };
        date_o.locale.cancelLabel = "清空";
        $('.dates').daterangepicker(date_o);

        $('.dates').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
        });

        $('.dates').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });

        //认领客户
        $(".dragCustomer").on("click",function (e) {
            e.preventDefault();
            var agentId = $(this).attr("data-id");

            common.ajax.handle({
                method: 'get',
                url: "/highsea/drag",
                data: {
                    agentId: agentId
                },
                succback: function (data) {
                    common.successMsg(data.msg, "reload");
                }
            });
        });

        //注册时间升序
        $(".orderUp").on("click",function (e) {
            e.preventDefault();
            $("input[name='orderType']").val(1);
            $(".btn-purple").click();
        });

        //注册时间降序
        $(".orderDown").on("click",function (e) {
            e.preventDefault();
            $("input[name='orderType']").val(2);
            $(".btn-purple").click();
        });

    });
</script>
</body>
</html>