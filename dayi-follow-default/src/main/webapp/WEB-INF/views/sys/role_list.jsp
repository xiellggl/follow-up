<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
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
                    <a href="./edit" class="pull-right">
                        <span class="btn btn-primary">添加角色</span>
                    </a>
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

                            <c:if test="${empty page}">
                                <tr>
                                    <td colspan="6" class="no_data">暂无角色，请
                                        <a href="#" data-toggle="modal"
                                           data-target="#myModalEditRole" title="新增角色">
                                            新增角色
                                        </a>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item">
                                    <tr data-id="${item.id}">
                                        <td>${item.name}</td>
                                        <td>${item.descript}</td>
                                        <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <a class="state-btn" data-state="${item.status}" href="#"
                                               data-id="${item.id}" title="已${item.status eq 1 ? '启用':'禁用'}">
                                            <span class="btn btn-minier ${item.status eq 1 ? 'btn-yellow':'btn-danger'}">
                                                    ${item.status  eq 1 ? '启用':'禁用'}
                                            </span>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="./edit?id=${item.id}" title="修改">
                                                <i class="ace-icon fa fa-pencil bigger-130"></i>
                                            </a>
                                            <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                                <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

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

<%--编辑模块模态框（Modal）--%>
<div class="modal fade in" id="myModalEditRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate"],function(common){
        //菜单高亮
        common.head("system",4);

        //删除
        $('[data-act="del"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否删除该角色？</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/role/delete.json",
                    data:{
                        id:id
                    }
                });
            });
        });

        //禁用/启用
        $(".state-btn").on("click", function () {
            var state = $(this).data("state");
            var act = state == 0 ? 1 : 0;
            var stateStr = act == 1 ? "启用" : "禁用";
            var className = act == 1 ? "btn-yellow" : "btn-danger";
            var $btn = $(this);
            var id = $(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定' + stateStr + '此角色</p>', {icon: 3, title: "温馨提示"}, function (index) {
                layer.close(index);
                common.ajax.handle({
                    url: "/role/enableRole.json",
                    data:{
                        id:id,
                        enable:act
                    },
                    succback: function (data) {
                        var btn = '<span class="btn btn-minier ' + className + '">' + stateStr + '</span>';
                        $btn.data("state", !state).html(btn).attr('data-original-title', "已" + stateStr);
                        return false;
                    }
                });
            });
            return false;
        });

    });

</script>
</body>
</html>