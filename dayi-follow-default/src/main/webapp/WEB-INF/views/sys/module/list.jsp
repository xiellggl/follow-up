<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="addModulePermission" value="false" scope="request" />
<c:set var="updateModulePermission" value="false" scope="request" />
<c:set var="deleteModulePermission" value="false" scope="request" />
<c:set var="enableModulePermission" value="false" scope="request" />
<c:set var="bindModulePermission" value="false" scope="request" />
<c:set var="untyingModulePermission" value="false" scope="request" />
<c:forEach items="${permissions}" var="item">
    <c:if test="${item.url eq '/module/add/save'}">
        <c:set var="addModulePermission" value="true" scope="request" />
    </c:if>
    <c:if test="${item.url eq '/module/edit/save'}">
        <c:set var="updateModulePermission" value="true" scope="request" />
    </c:if>
    <c:if test="${item.url eq '/module/delete'}">
        <c:set var="deleteModulePermission" value="true" scope="request" />
    </c:if>
    <c:if test="${item.url eq '/module/enableModule'}">
        <c:set var="enableModulePermission" value="true" scope="request" />
    </c:if>
    <c:if test="${item.url eq '/permission/bind/save'}">
        <c:set var="bindModulePermission" value="true" scope="request" />
    </c:if>
    <c:if test="${item.url eq '/module/untying'}">
        <c:set var="untyingModulePermission" value="true" scope="request" />
    </c:if>
</c:forEach>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>模块管理</title>
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
                        <a href="/">首页</a>
                    </li>
                    <li>系统管理</li>
                    <li class="active">模块管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">

                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            模块管理
                        </small>
                    </h1>
                    <c:if test="${addModulePermission}">
                        <a href="#" class="pull-right">
                            <span class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModalEditModule">添加模块</span>
                        </a>
                    </c:if>
                </div>

                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                            <table class="table table-striped table-bordered table-hover">

                                <thead>
                                    <tr>
                                        <th width="30"></th>
                                        <th>模块名称</th>
                                        <th>模块状态</th>
                                        <th>功能路径</th>
                                        <th class="hidden-xs">排序</th>
                                        <c:if test="${addModulePermission or updateModulePermission or deleteModulePermission or bindModulePermission or untyingModulePermission}">
                                        <th>操作</th>
                                        </c:if>
                                    </tr>
                                </thead>

                                <tbody>
                                <c:if test="${empty menus}">
                                    <tr>
                                        <td colspan="7" class="no_data">
                                            暂无模块<c:if test="${addModulePermission}">，请
                                            <a href="#" data-toggle="modal"
                                               data-target="#myModalEditModule">新增模块</a></c:if>
                                        </td>
                                    </tr>
                                </c:if>

                                <c:if test="${not empty menus}">
                                    <c:set var="moduleList" value="${allMenus}" />
                                    <%@ include file="item.jsp" %>
                                </c:if>

                                </tbody>
                            </table>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<%--编辑模块模态框（Modal）--%>
<div class="modal fade in" id="myModalEditModule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<script type="text/html" id="tplEditModule">
    <form id="form-id" class="form-horizontal" autocomplete="off">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">{{id?'修改':'添加'}}模块</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <input type="hidden" name="id" value="{{id}}" />
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">*模块名称：</label>
                                <div class="col-xs-12 col-sm-6">
                                    <input type="text" name="name" value="{{name}}" class="form-control"/>
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>

                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">样式名称：</label>
                                <div class="col-xs-12 col-sm-6">
                                    <input type="text" name="cssName" value="{{cssName}}" class="form-control"/>
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline">只针对顶级栏目有效</div>
                                <span class="col-sm-2"></span>
                                <span class="col-sm-10 col-xs-12" style="font-size:12px; color:#999; margin-top:5px;">预留样式：fa-tachometer ， fa-folder ， fa-list ， fa-list-alt ， fa-calendar</span>
                            </div>

                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">模块排序：</label>
                                <div class="col-xs-12 col-sm-6">
                                    <input type="text" name="sort" value="{{sort}}" class="form-control"/>
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>

                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">*模块状态：</label>
                                <div class="col-sm-9 inline align-middle" style="padding-top:8px;">
                                    <label style="margin-right: 15px;">
                                        <input type="radio" name="status" value="1" {{id==null||status==1?'checked':''}}/>
                                        启用
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="0" {{status==0?'checked':''}}/>
                                        禁用
                                    </label>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">功能路径：</label>
                                <div class="col-xs-12 col-sm-6">
                                    <input type="text" name="url" value="{{url}}" class="form-control"/>
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>

                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">备注信息：</label>
                                <div class="col-xs-12 col-sm-6">
                                    <textarea name="description" class="form-control" style="height:60px;">{{description}}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">
                        提交保存
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        关闭
                    </button>
                </div>
            </div>
        </div>
    </form>
</script>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template",],function(common,validate,template){
        //菜单高亮
        common.head();

        //添加、编辑模块方法
        var editModuleFn = function (id,pid) {
            var id = id || null;
            var type = "add";
            var data = {};

            if (id) {
                type = "edit";
                common.ajax.handle({
                    url: "/module/getModuleById.json",
                    data:{id:id},
                    async: false,
                    succback: function (json) {
                        data = json.result;
                    }
                });
                if (data.id==null) {
                    return false;
                }

            }

            var $modal = $("#myModalEditModule");

            var html = template("tplEditModule", data);

            $modal.html(html);
            var $form = $("#form-id");
            if(id) {
                $(".modal-title").html("修改模块");
                if(data.parentid!=""){
                    $form.find('[name="parentid"]').val(data.parentid);
                }
            }

            $form.validate({
                rules: {
                    name:"required",
                },
                messages: {
                    name:"模块名称不能为空",
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
                        url: "/module/"+type+"/save.json",
                        data: $form.serialize()
                    });
                    return false;
                }
            });


        };

        //新增、编辑功能按钮
        var $editBtn = $('[data-target="#myModalEditModule"]');
        $editBtn.on("click", function () {
            var id = $(this).data('id') || null;
            var pid = $(this).data('pid') || null;
            editModuleFn(id,pid);
        });

        //删除
        $('[data-act="del"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否删除该模块？</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/module/delete.json",
                    data:{id:id}
                });
            });
        });

        //解绑
        $('[data-act="unbind"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否解绑该功能？</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/permission/untying/save.json",
                    data:{
                        id:id
                    }
                });
            });
        });


        //菜单展开
        $('[data-act="togglePermissions"]').on("click",function () {
            var cur_id =  $(this).data("id");
            $('[data-pid="'+cur_id+'"]').toggle();
            $(this).find(".fa").toggle();
        });

        <c:if test="${enableModulePermission}">
        //禁用/启用
        $(".state-btn").on("click", function () {
            var state = $(this).data("state");
            var act = state == 0 ? 1 : 0;
            var stateStr = act == 1 ? "启用" : "禁用";
            var className = act == 1 ? "btn-yellow" : "btn-danger";
            var $btn = $(this);
            var id = $(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定' + stateStr + '此模块</p>', {icon: 3, title: "温馨提示"}, function (index) {
                layer.close(index);
                common.ajax.handle({
                    url: "/module/enableModule.json",
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
        </c:if>

    });

</script>
</body>
</html>