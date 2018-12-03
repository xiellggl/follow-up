<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <c:set var="pageName" value="financeUcOrgPage" />
    <%@include file="/inc/followup/csslink.jsp"%>
</head>
<body>
<div class="main-container" id="main-container">
    <%@include file="/inc/followup/sidebar.jsp"%>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li class="active">角色管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            角色管理
                        </small>
                    </h1>
                </div>
                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>角色名称</th>
                                <th>角色备注</th>
                                <th>添加时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty topDeptList}">
                                <tr>
                                    <td colspan="6" class="no_data">暂无模块，请<a href="javascript:;" data-act="addDept">新增模块</a></td>
                                </tr>
                            </c:if>
                            <%--自增序号，注意scope--%>
                            <c:set var="index" value="0" scope="request" />
                            <%--记录树的层次，注意scope--%>
                            <c:set var="level" value="0" scope="request" />
                            <c:import url="role_item.jsp" />
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common,validate,template){

    });
</script>
</body>
</html>