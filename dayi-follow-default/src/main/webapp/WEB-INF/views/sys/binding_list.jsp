<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>功能管理</title>
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
                    <li class="active">功能管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">

                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            功能管理
                        </small>
                    </h1>
                    <a href="#" class="pull-right">
                        <span class="btn btn-primary" data-toggle="modal" data-target="#myModalEditFollowuper">添加功能</span>
                    </a>
                </div>

                <div class="row">
                    <form class="form-horizontal" style="max-width: 800px;">
                        <div class="clearfix maintop">

                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-phone"></i>
                                    </span>
                                    <input type="text" name="name" class="form-control admin_sea"
                                           value="${param.name}" placeholder="功能名称："/>
                                </div>
                            </div>

                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-cog"></i>
                                    </span>
                                    <select name="isBinding" class="form-control admin_sea">
                                        <option value="">绑定状态</option>
                                        <option value="false"  ${isBinding=='false'?"selected":''}>未绑定</option>
                                        <option value="true"  ${isBinding=='true'?"selected":''}>已绑定</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-4 col-sm-4 btn-sespan">
                                <div class="btn-group dropup">
                                    <button type="submit" class="btn btn-xs btn-purple">
                                        <span class="ace-icon fa fa-search"></span>
                                        查询
                                    </button>
                                    <button data-toggle="dropdown" class="btn btn-xs btn-info dropdown-toggle hidden visible-xs" aria-expanded="false">
                                        <span class="ace-icon fa fa-caret-down icon-only"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="./list">绑定功能</a>
                                        </li>
                                        <li>
                                            <a href="">返回列表</a>
                                        </li>
                                    </ul>
                                </div>
                                <a href="#" data-act="binding" class="btn btn-xs btn-pink hidden-xs">
                                    <span class="ace-icon fa fa-globe"></span>
                                    绑定功能
                                </a>
                                <a href="" class="btn btn-xs btn-info hidden-xs">
                                    <span class="ace-icon fa fa-globe"></span>
                                    返回列表
                                </a>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                        <div>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                                        <label class="pos-rel">
                                            <input type="checkbox" id="chkAll" class="ace">

                                            <span class="lbl"></span>
                                        </label>
                                        <input type="hidden" id="checkIds" name="ids">
                                    </th>
                                    <th>功能名称</th>
                                    <th class="hidden-sm hidden-xs">功能路径</th>
                                    <th>添加时间</th>
                                    <th>修改时间</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>


                                <c:if test="${empty page}">
                                    <tr>
                                        <td colspan="7" class="no_data">
                                            暂无功能，请
                                            <a href="#" data-toggle="modal"
                                               data-target="#myModalEditFollowuper" title="新增功能">新增功能</a>
                                        </td>
                                    </tr>
                                </c:if>

                                <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item" >
                                <tr data-id="${item.id}">
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace check" name="id" value="${item.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td>${item.name}</td>
                                    <td>${item.url}</td>
                                    <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${item.description}</td>
                                    <td>
                                        <a href="#" data-id="${item.id}" data-toggle="modal"
                                           data-target="#myModalEditFollowuper"
                                           data-toggle="tooltip" title="修改">
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
</div>

<%--编辑模块模态框（Modal）--%>
<div class="modal fade in" id="myModalEditFollowuper" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common,validate,template){
        //菜单高亮
        common.head("system",1);

        //添加、编辑功能方法
        var editDeptFn = function (id) {
            var id = id || 0;
            var url = "/permission/edit?id="+id
            if (id = null) {
                var url = "/permission/edit";
            }
            var html = "";

            common.ajax.handle({
                type: "get",
                url:url,
                dataType: "html",
                async: false,
                succback: function (data) {
                    html = data;
                }
            });
            var $modal = $("#myModalEditFollowuper");
            $modal.html(html);
            if (id > 0) {
                $(".modal-title").html("修改功能");
            }
            var $form = $("#form-id");
            $form.validate({
                rules: {
                    name:"required",
                    sort:{
                        required:true,
                        number:true
                    },
                    url:"required",
                    description:"required"
                },
                messages: {
                    name:"功能名称不能为空",
                    sort:{
                        required:"功能排序不能为空",
                        number:"请输入数字"
                    },
                    url: "功能路径不能为空",
                    description:"请输入功能描述"

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
                        url: "/permission/add/save.json",
                        data: $form.serialize()
                    });
                    return false;
                }
            });


        };

        //新增、编辑功能按钮
        var $editBtn = $('[data-target="#myModalEditFollowuper"]');
        $editBtn.on("click", function () {
            var id = $(this).data('id') || null;
            editDeptFn(id);
        });

        //删除
        $('[data-act="del"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否删除该功能？</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/permission/delete?id="+id
                });
            });
        });

        var $list = $("#listPan");
        //全选、反选
        common.checkBox($("#chkAll"), $list.find('[name="id"]'), $("#checkIds"));

        //绑定功能
        $('[data-act="action"]').on("click", function () {

        });

    });
</script>

</body>
</html>