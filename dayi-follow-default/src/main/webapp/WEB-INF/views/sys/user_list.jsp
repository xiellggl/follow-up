<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户管理</title>
    <c:set var="pageName" value="financeUcOrgPage" />
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
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li class="active">用户管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">

                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            用户管理
                        </small>
                    </h1>
                    <a href="#" class="pull-right">
                        <span class="btn btn-primary" data-act="addDept">新增用户</span>
                    </a>
                </div>

                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>账号</th>
                                <th>邀请码</th>
                                <th>角色</th>
                                <th>部门</th>
                                <th>状态</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%--<c:if test="${empty topDeptList}">--%>
                            <%--<tr>--%>
                                <%--<td colspan="6" class="no_data">暂无用户，请<a href="javascript:;" data-act="addDept">新增用户</a></td>--%>
                            <%--</tr>--%>
                            <%--</c:if>--%>

                            <c:forEach var="i" begin="1" end="5">
                            <tr>
                                <td>老陈</td>
                                <td>laochen</td>
                                <td>1314</td>
                                <td>资产管理中心总经理</td>
                                <td>资产管理中心</td>
                                <td>
                                    <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
                                        <span class="btn btn-minier btn-yellow">启用</span>
                                    </a>
                                </td>
                                <td>2018-09-28 14：25</td>
                                <td>
                                    <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                    <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                        <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                    <a href="#" data-act="resetpwd" data-toggle="tooltip"
                                       title="重置密码">
                                        <i class="ace-icon fa fa-key bigger-130"></i>
                                    </a>
                                </td>
                            </tr>
                            </c:forEach>

                            </tbody>
                        </table>

                        <%--<c:if test="${not empty page.items}">--%>
                            <%--<div class="pagerBar" id="pagerBar">--%>
                                <%--<common:page2 url="${pageUrl}" type="3"/>--%>
                            <%--</div>--%>
                        <%--</c:if>--%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common,validate,template){
        common.head("system",2);
    });
</script>
</body>
</html>