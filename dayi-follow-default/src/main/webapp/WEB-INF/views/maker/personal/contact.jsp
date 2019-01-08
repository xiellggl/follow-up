<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="detailMaker" value="false" />
<%--<c:set var="addContactMaker" value="false" />--%>
<c:forEach items="${permissions}" var="item">
    <%--查看创客联系记录--%>
    <c:if test="${item.url eq '/org/contact'}">
        <c:set var="detailMaker" value="true" />
    </c:if>
    <%--&lt;%&ndash;添加创客联系记录&ndash;%&gt;--%>
    <%--<c:if test="${item.url eq '/contact/add/save'}">--%>
        <%--<c:set var="addContactMaker" value="true" />--%>
    <%--</c:if>--%>
</c:forEach>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>我的客户-创客明细</title>
    <%@include file="/inc/followup/csslink.jsp"%>
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
                        <a href="/">首页</a>
                    </li>
                    <li>
                        <a href="./list">我的客户-创客</a>
                    </li>
                    <li class="active">联系记录</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class=" clearfix">
                    <h2 class="pull-left blue">
                        客户：${org.linkPersonFm}
                        &nbsp;&nbsp;&nbsp;
                    </h2>
                    <a href="/org/list?${returnUrl}" style="float: right;margin: 20px 10px 0 0;" class="btn btn-sm btn-info" type="reset">返回</a>
                </div>
                <%--添加创客联系记录--%>
                <%--<c:if test="${addContactMaker}">--%>
                    <div class="row">
                        <div class="col-xs-12">
                            <h4 class="header smaller lighter blue ">
                                <i class="ace-icon fa fa-comments-o"></i>
                                添加联系记录
                            </h4>
                            <form class="form-horizontal row" id="formEdit" style="max-width: 950px;">
                                <input type="hidden" name="orgId" value="${org.id}">
                                <div class="col-xs-12 col-sm-7">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-right">联系方式： </label>
                                        <div class="col-xs-12 col-sm-8">
                                            <select name="contactType" class="width-100">
                                                <c:forEach items="${contactTypes}" var="item">
                                                    <option value="${item.value}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="space-4"></div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label no-padding-right">沟通内容： </label>
                                        <div class="col-xs-12 col-sm-8">
                                            <textarea type="text" name="content" value="" style="height: 90px;" class="width-100" placeholder="140个字以内" required></textarea>
                                            <div class="help-block tips_box"></div>
                                        </div>
                                    </div>
                                    <div class="form-group" style="text-align: center;">
                                        <button type="submit" class="btn btn-primary">提交记录</button>
                                        <button class="btn btn-info" type="reset">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                <%--</c:if>--%>

                <%--查看创客联系记录--%>
                <c:if test="${detailMaker}">

                    <div class="row">
                        <div class="col-xs-12">
                            <h4 class="header smaller lighter blue">
                                <i class="ace-icon fa fa-book"></i>
                                联系记录
                            </h4>
                            <table class="table table-striped table-bordered table-hover" id="list">
                                <thead>
                                <tr>
                                    <th>联系时间</th>
                                    <th>联系人</th>
                                    <th>联系方式</th>
                                    <th>沟通内容</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty page.results}">
                                    <tr>
                                        <td colspan="7" class="no_data">暂无数据</td>
                                    </tr>
                                </c:if>
                                <c:if test="${not empty page.results}">
                                    <c:forEach items="${page.results}" var="item" >
                                        <tr>
                                            <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>${item.followUp}</td>
                                            <td>${item.contactTypeStr}</td>
                                            <td>${item.content}</td>
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
                </c:if>

            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script type="text/html" id="tplContactItem">
    <tr>
        <td>{{createTime | dateFormat:'yyyy-MM-dd hh:mm:ss'}}</td>
        <td>{{followUp}}</td>
        <td>{{contactTypeStr}}</td>
        <td>{{content}}</td>
    </tr>
</script>
<script>
    seajs.use(["common", "template", "validate", "addMethod"], function (common, template) {
        //菜单高亮
        common.head("_org_list");
        template.helper("dateFormat",common.dateFormat);

        var $formEdit = $("#formEdit");
        var $list = $("#list");

        $formEdit.validate({
            rules: {
                contactType: "required",
                content:{
                    required:true,
                    maxlength:140
                }
            },
            messages: {
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
                    url: "/org/contact/add/save.json",
                    data: $formEdit.serialize(),
                    succback:function (data) {
                        $formEdit[0].reset();
                        common.successMsg("添加成功");
                        var html = template("tplContactItem", data.result);
                        if($list.find(".no_data").length){
                            $list.find("tbody").html(html);
                        }else {
                            $list.find("tbody").prepend(html);
                        }
                    }
                });
                return false;
            }
        });
    });
</script>
</body>
</html>