<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>我的客户-代理商</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/bootstrap/plug/multiselect/bootstrap-multiselect.css" />
    <style>
        .multiselect-native-select .btn-group>.btn>.caret{ border-top-color: #666; margin-top:0;}
        .bank-item label{margin: 0 5px;}
        .green-assign {display: none }
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
                    <li class="active">我的客户-代理商</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <c:set var="pageType" value="my" />
                <%@include file="./agent_list_inc.jsp"%>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","multiselect"],function (common) {
        common.head();
        //多选下拉
        var $bankType = $("#bankType");
        $bankType.multiselect({
            nonSelectedText: '已开通结算银行',
            allSelectedText:"全部选中",
            nSelectedText: '个选中',
            buttonClass: 'btn btn-white',
        });
        var datacount = $('[data-id="pageRecordTotal"]').html();
        if (datacount<20){
            $("#page-import").css('display','none');
            $("#page-button").css('display','none');
        }else {

        }
        var pagego = $("#page-button");
        var pageurl = pagego.attr("data-url");
        $(pagego).click(function(){
            var temp = $('input[name="page-import"]').val();
            if (temp==""){
                alert("请输入页码")
            } else {
                pageurl = pageurl + "&pageNo=" + temp;
                if (pageurl.indexOf("?&") > -1) {//如果找到?&就去掉&
                    pageurl = pageurl.replace("&", "");
                }
                window.location.href = pageurl;
            }
        });
    });
</script>
</body>
</html>