<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="addContactAgent" value="false" />
<c:set var="detailAgent" value="false" />
<c:set var="contactAgent" value="false" />
<c:set var="loginlogAgent" value="false" />
<c:forEach items="${permissions}" var="item">
    <%--添加代理商联系记录--%>
    <c:if test="${item.url eq '/agent/contact/add**'}">
        <c:set var="addContactAgent" value="true" />
    </c:if>
    <%--查看代理商详情--%>
    <c:if test="${item.url eq '/agent/detail'}">
        <c:set var="detailAgent" value="true" />
    </c:if>
    <%--查看代理商联系记录--%>
    <c:if test="${item.url eq '/agent/contact'}">
        <c:set var="contactAgent" value="true" />
    </c:if>
    <%--查看代理商登录日志--%>
    <c:if test="${item.url eq '/agent/loginlog'}">
        <c:set var="loginlogAgent" value="true" />
    </c:if>
</c:forEach>
<%--用来判断是否显示历史货款编辑--%>
<c:set var="hisFundEdit" value="true" />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>我的客户-代理商明细</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>
    <style>
        .customer-info tr th{text-align: right;}
        .default_con button, .edit_con button { height: 30px;line-height: 10px;margin-left: 25px }
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
                        <a href="/">首页</a>
                    </li>
                    <li>
                        <a href="./list">我的客户-代理商</a>
                    </li>
                    <li class="active">明细</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <c:if test="${detailAgent}">
                <div class="page-content">
                    <c:set var="pageType" value="my" />
                    <div class=" clearfix">
                        <h2 class="pull-left blue">
                            客户：${detailVo.linkPersonFm}
                            &nbsp;&nbsp;&nbsp;
                        </h2>
                    <a href="/agent/list?${returnUrl}" style="float: right;margin: 20px 10px 0 0;" class="btn btn-sm btn-info" type="reset">返回</a>
                    </div>
                    <
                    <%@include file="../agent_detail_inc.jsp"%>
                </div>
            </c:if>

            <c:if test="${addContactAgent}">
                <div class="page-content" >
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="page-header clearfix">
                                <h4 class="pull-left blue">
                                    <i class="ace-icon fa fa-comments-o"></i>
                                    添加联系记录
                                </h4>
                                <c:if test="${not empty nextAgentVo}">
                                    <a href="./detail?agentId=${nextAgentVo.id}&${condition}" class="pull-right">
                                        <span class="btn btn-xs btn-info">联系下一客户 →</span>
                                    </a>
                                </c:if>
                            </div>
                            <form class="form-horizontal row" id="formEdit">
                                <input type="hidden" name="agentId" value="${param.agentId}">
                                <div class="col-xs-12 col-sm-6">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-right">联系方式： </label>
                                        <div class="col-xs-12 col-sm-6">
                                            <select name="contactType" class="width-100">
                                                <c:forEach items="${contactTypes}" var="item">
                                                    <option value="${item.value}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="space-4"></div>

                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-right">客户类型： </label>
                                        <div class="col-xs-12 col-sm-6">
                                            <select name="customerType" class="width-100">
                                                <c:forEach items="${customerTypes}" var="item">
                                                    <option value="${item.value}" <c:if test="${agent.customerType eq item.value}">selected</c:if>>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="space-4"></div>

                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-right">客户意向度： </label>
                                        <div class="col-xs-12 col-sm-6">
                                            <select name="cusIntentionType" class="width-100">
                                                <c:forEach items="${customerIntentionTypes}" var="item">
                                                    <option value="${item.value}" <c:if test="${agent.cusIntentionType eq item.value}">selected</c:if>>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="space-4"></div>

                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-right"> 下次联系时间： </label>
                                        <div class="col-xs-12 col-sm-6">
                                            <input type="text" name="nextContactTime" class="width-100" placeholder="选填" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <label class="control-label">沟通内容： </label>
                                            <textarea type="text" name="content" value="" style="height: 90px;" class="width-100" placeholder="140个字以内" required></textarea>
                                        </div>
                                        <div class="help-block col-xs-12 inline tips_box"></div>
                                    </div>
                                    <div class="form-group" style="text-align: center;">
                                        <button type="submit" class="btn btn-primary">提交记录</button>
                                        <button type="button" class="btn btn-danger goToUserLib">踢入公海</button>
                                        <button class="btn btn-info" type="reset">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${loginlogAgent or contactAgent}">
                <div class="page-content">
                    <div class="tabbable">
                        <ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
                            <c:if test="${contactAgent}">
                                <li class="active">
                                    <a data-toggle="tab" href="#profile4" aria-expanded="false">联系记录</a>
                                </li>
                            </c:if>
                            <c:if test="${loginlogAgent}">
                                <li ${!contactAgent ? 'class="active"' : ''} >
                                    <a data-toggle="tab" href="#home4" aria-expanded="true">登录日志</a>
                                </li>
                            </c:if>
                        </ul>

                        <div class="tab-content">
                            <c:if test="${contactAgent}">
                                <div id="profile4" class="tab-pane active">
                                    <div id="conList"></div>
                                </div>
                            </c:if>
                            <c:if test="${loginlogAgent}">
                                <div id="home4" class="tab-pane ${!contactAgent ? 'active' : ''}">
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
        //菜单高亮
        common.head("_agent_list");
        var agentId = ${param.agentId};

        <c:if test="${loginlogAgent}">
        var $logList = $("#logList");
        var log_url = "/agent/loginlog?agentId=" + agentId;
        common.loadPageHTML(log_url, null,$logList);
        common.clickPageFn(log_url, null, $logList);
        </c:if>

        <c:if test="${contactAgent}">
        var $conList = $("#conList");
        var con_url = "/agent/contact?agentId=" + agentId;
        common.loadPageHTML(con_url, null,$conList);
        common.clickPageFn(con_url, null, $conList);
        </c:if>

        var $formEdit = $("#formEdit");
        var $list = $("#list");

        var date_o = {
            singleDatePicker:true,
            autoUpdateInput: false,
            //timePicker24Hour : true,//设置小时为24小时制 默认false
            //timePicker : true,//可选中时分 默认false
            locale: locale_cn,
        };
        date_o.locale.cancelLabel = "清空";
        var $nextContactTime = $formEdit.find('[name="nextContactTime"]');
        $nextContactTime.daterangepicker(date_o).on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD'));
        }).on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });

        template.helper("dateFormat",common.dateFormat);

        $formEdit.validate({
            rules: {
                contactType: "required",
                customerType: "required",
                cusIntentionType: "required",
                content:{
                    required:true,
                    maxlength:140
                }
            },
            messages: {
                customerType: "客户类型有误",
                content:{
                    required:"请填写沟通内容",
                    maxlength:"140个字以内"
                }
            },
            errorPlacement: function (error, element) {
                var $tipsBox = element.closest(".form-group").find(".tips_box");
                if ($tipsBox.length) {
                    $tipsBox.html(error);
                } else {
                    element.after(error);
                }
            },
            errorClass: "field-error",
            success: function (label, element) {
                label.remove();
                return true;
            },
            submitHandler: function (form) {
                common.ajax.handle({
                    url: "/agent/contact/add/save.json",
                    data: $formEdit.serialize()
                });
                return false;
            }
        });


        //历史最高货款编辑 add by zengxiaobin
        $("#editMaxFundFm").on("click",function(){
            $(".default_con").hide();
            $(".edit_con").show();
        });

        //取消 add by zengxiaobin
        $("#cancel").on("click",function () {
            $(".default_con").show();
            $(".edit_con").hide();
        });

        //保存 add by zengxiaobin
        $("#save").on("click",function(){
            var newHisFund = Number($(".newHisFund").val());
            var hisMaxFund = Number('${detailVo.hisMaxFund}');
            if( newHisFund < 0 || newHisFund == null){
                layer.alert("输入的金额不能为负数哦");
                return;
            }
            if(newHisFund < hisMaxFund){
                layer.alert("所输金额需大于当前历史最高货款");
                return;
            }
            common.ajax.handle({
                url: "/agent/hisfund/edit",
                data: {
                    agentId: agentId,
                    hisFund: newHisFund
                },
                succback: function (data) {
                    common.successMsg(data.msg, function () {
                        $(".default_con").show();
                        $(".edit_con").hide();
                        window.location.reload();
                    });
                }
            });
        });


        //踢入公海
        $(".goToUserLib").on("click",function (e) {
            e.preventDefault();

        });

    });
</script>
</body>
</html>