<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公海设置</title>
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
                    <li class="active">公海设置</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            公海设置
                        </small>
                    </h1>
                </div>
                <div class="row" style="margin-top: 30px;">
                    <form class="form-horizontal" >
                        <div class="clearfix maintop">
                            <div class="col-xs-4 col-sm-6 hidden-xs bank-item">
                                <div class="input-group">
                                    <span class="fs16">公海范围：</span>
                                    <select name="deptIds" id="bankType" multiple="multiple" class="ml30" style="height: 30px">
                                        <c:forEach items="${list}" var="item">
                                            <c:set var="selected" value="" />
                                            <c:forEach items="${bankTypesArr}" var="type">
                                                <c:if test="${type eq item.value and empty selected}">
                                                    <c:set var="selected" value="selected" />
                                                </c:if>
                                            </c:forEach>
                                            <option value="${item.value}" ${selected}>${item.mark}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix maintop" style="margin:30px auto">
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="txt">私海客户数量上限：</span>
                                    <input type="number" name="num" class="form-control admin_sea" value="" style="left: 180px;top:-10px"/>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix maintop">
                            <div class="col-sm-3 hidden-xs btn-sespan assignDateDiv">
                                <button type="button" class="btn btn-primary" id="save">保存</button>
                                <a class="btn btn-default ml30" href="/highsea/list">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<%@include file="/inc/followup/script.jsp"%>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "multiselect"], function (common) {
        //菜单高亮
        common.head();

        //多选下拉
        var $bankType = $("#bankType");
        $bankType.multiselect({
            nonSelectedText: '选中的全部部门',
            allSelectedText:"全部选中",
            nSelectedText: '个选中',
            buttonClass: 'btn btn-white'
        });


        $("#save").on("click",function () {
            console.log(2555);

            //ajax
            common.ajax.handle({
                url: "/agent/assign/save/batch.json",
                data: {
                    followId: id,
                    agentIds: ids
                },
                succback: function (data) {
                    $myModal.modal("hide");
                    common.successMsg(data.msg, "reload");
                }
            });
        });
    });
</script>
</body>
</html>