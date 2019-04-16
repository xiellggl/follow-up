<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公海设置</title>
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
                    <form class="form-horizontal">
                        <c:forEach items="${list}" var="item">

                            <c:set var="flag" value="false"></c:set>

                            <c:if test="${ 'HS_RANGE' eq item.mark }"><%--判断标识--%>
                                <div class="clearfix maintop">
                                    <div class="col-xs-4 col-sm-6 hidden-xs bank-item">
                                        <div class="input-group">
                                            <span class="fs16 mr50">公海范围：</span>
                                            <select name="deptIds" id="bankType" multiple="multiple" class="ml30"
                                                    style="height: 30px;display: none">
                                                <c:forEach items="${deptTree}" var="item1">
                                                    <c:forEach items="${item.values}" var="item2">
                                                        <c:if test="${item1.id eq item2}">
                                                            <c:set var="flag" value="true"></c:set>
                                                        </c:if>
                                                    </c:forEach>
                                                    <option value="${item1.id}" ${ flag eq true? 'selected':''}>${item1.treeName}</option>
                                                    <c:set var="flag" value="false"></c:set>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${ 'PS_NUM' eq item.mark }">
                                <div class="clearfix maintop" style="margin:30px auto">
                                    <div class="col-xs-4 col-sm-2 btn-sespan">
                                        <div class="input-group">
                                            <span class="txt">私海客户数量上限：</span>
                                            <input type="number" name="num" class="form-control" value="${item.value}"
                                                   style="left: 180px;top:-10px"/>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                        </c:forEach>

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

<%@include file="/inc/followup/script.jsp" %>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "multiselect"], function (common) {
        //菜单高亮
        common.head();

        //多选下拉
        var $bankType = $("#bankType");
        var selectedIds = [];   //选中的部门ID

        $bankType.multiselect({
            nonSelectedText: '全部部门',
            allSelectedText: "全部选中",
            nSelectedText: '个选中',
            buttonClass: 'btn btn-white'
        });


        //提交公海设置参数
        $("#save").on("click", function () {
            var departments = $('#bankType option:selected');
            $(departments).each(function (index, department) {
                selectedIds.push([$(this).val()]);
            });

            var private_num = $("input[name='num']").val();
            if (private_num < 0 || private_num == 0) {
                layer.alert("私海客户数量上限需要大于0");
                return
            }

            var arr = [
                {mark: 'HS_RANGE', value: selectedIds.toString()},
                {mark: 'PS_NUM', value: private_num}
            ];

            common.ajax.handle({
                url: "/highsea/setconfig",
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(arr),
                succback: function (data) {
                    common.successMsg(data.msg, "reload");
                }
            });
        });
    });
</script>
</body>
</html>