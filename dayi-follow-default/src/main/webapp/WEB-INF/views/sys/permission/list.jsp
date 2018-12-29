<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="addPermission" value="false" />
<c:set var="updatePermission" value="false" />
<c:set var="deletePermission" value="false" />
<c:set var="bindPermission" value="false" />
<c:set var="displacePermission" value="false" />
<c:forEach items="${permissions}" var="item">
    <c:if test="${item.url eq '/permission/add/save'}">
        <c:set var="addPermission" value="true" />
    </c:if>
    <c:if test="${item.url eq '/permission/edit/save'}">
        <c:set var="updatePermission" value="true" />
    </c:if>
    <c:if test="${item.url eq '/permission/delete'}">
        <c:set var="deletePermission" value="true" />
    </c:if>
    <c:if test="${item.url eq '/permission/bind/save'}">
        <c:set var="bindPermission" value="true" />
    </c:if>
    <c:if test="${item.url eq '/permission/displace'}">
        <c:set var="displacePermission" value="true" />
    </c:if>
</c:forEach>
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
                        <a href="/index">首页</a>
                    </li>
                    <li>系统管理</li>
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
                    <c:if test="${addPermission}">
                        <a href="#" class="pull-right">
                            <span class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModalEditPermission">添加功能</span>
                        </a>
                    </c:if>

                </div>

                <div class="row">
                    <form class="form-horizontal" style="max-width: 1000px;">
                        <c:if test="${not empty param.bindModuleId and bindPermission}">
                            <input type="hidden" name="bindModuleId" value="${param.bindModuleId}" />
                        </c:if>
                        <div class="clearfix maintop">
                            <div class="col-xs-12 col-sm-3 btn-sespan maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-cog"></i>
                                    </span>
                                    <select name="parentId" class="form-control admin_sea">
                                        <option value="">所属页面</option>
                                        <c:forEach items="${parentList}" var="item">
                                            <option value="${item.id}" ${param.parentId eq item.id ? 'selected' : ''}>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3 btn-sespan maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-cog"></i>
                                    </span>
                                    <select name="moduleId" class="form-control admin_sea">
                                        <option value="">所属模块</option>
                                        <c:forEach var="item" items="${menus}">
                                            <option value="${item.id}"  ${param.moduleid eq item.id ? 'selected' : ''} >${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-12 col-sm-2 btn-sespan maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-link"></i>
                                    </span>
                                    <select name="binding" class="form-control admin_sea">
                                        <option value="">绑定状态</option>
                                        <option value="false"  ${not empty param.binding and not param.binding?"selected":''}>未绑定</option>
                                        <option value="true"  ${param.binding?"selected":''}>已绑定</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-xs-12 col-sm-4 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                                    <input type="text" name="name" class="form-control admin_sea"
                                           value="${param.name}" placeholder="功能名称："/>
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-xs btn-purple">
                                            <span class="ace-icon fa fa-search"></span>
                                            查询
                                        </button>
                                        <a href="?" class="btn btn-xs btn-info">
                                            <span class="ace-icon fa fa-globe"></span>
                                            显示全部
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </form>
                </div>

                <div class="col-xs-12">
                    <div class="row" id="listPan">
                        <div class="space-6"></div>
                        <div>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <c:if test="${not empty param.bindModuleId and bindPermission}">
                                    <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                                        <label class="pos-rel">
                                            <input type="checkbox" id="chkAll" class="ace">
                                            <span class="lbl"></span>
                                        </label>
                                        <input type="hidden" id="checkIds" name="ids" value="">
                                    </th>
                                    </c:if>
                                    <th>功能名称</th>
                                    <th>显示状态</th>
                                    <th class="hidden-sm hidden-xs">功能路径</th>
                                    <th>所属页面</th>
                                    <th>所属模块</th>
                                    <th class="hidden-sm hidden-xs">添加时间</th>
                                    <th class="hidden-sm hidden-xs">修改时间</th>
                                    <th class="hidden-sm hidden-xs">备注</th>
                                    <c:if test="${updatePermission or deletePermission}">
                                    <th>操作</th>
                                    </c:if>
                                </tr>
                                </thead>
                                <tbody>


                                <c:if test="${empty page}">
                                    <tr>
                                        <td colspan="7" class="no_data">
                                            暂无功能<c:if test="${addPermission}">，请
                                            <a href="#" data-toggle="modal"
                                               data-target="#myModalEditPermission" title="新增功能">新增功能</a></c:if>
                                        </td>
                                    </tr>
                                </c:if>

                                <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item" >
                                <tr data-id="${item.id}">
                                    <c:if test="${not empty param.bindModuleId and bindPermission}">
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace check" name="id" value="${item.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    </c:if>
                                    <td>${item.name}</td>
                                    <td>
                                        <a class="state-btn" data-state="${item.displayStatus}" href="#"
                                           data-id="${item.id}" title="已${item.displayStatus eq 1 ? '显示':'隐藏'}">
                                            <span class="btn btn-minier ${item.displayStatus eq 1 ? 'btn-yellow':'btn-danger'}">
                                                    ${item.displayStatus  eq 1 ? '显示':'隐藏'}
                                            </span>
                                        </a>
                                    </td>
                                    <td class="hidden-sm hidden-xs">${item.url}</td>
                                    <td>${item.parent}</td>
                                    <td>${item.moduleName}</td>
                                    <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td class="hidden-sm hidden-xs">${item.description}</td>
                                    <c:if test="${updatePermission or deletePermission}">
                                    <td>
                                        <c:if test="${updatePermission}">
                                            <a href="#" data-id="${item.id}" data-toggle="modal"
                                               data-target="#myModalEditPermission"
                                               data-toggle="tooltip" title="修改">
                                                <i class="ace-icon fa fa-pencil bigger-130"></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${deletePermission}">
                                            <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                                <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                        </c:if>
                                    </td>
                                    </c:if>
                                </tr>
                                </c:forEach>
                                </c:if>

                                </tbody>
                            </table>
                            <c:if test="${not empty param.bindModuleId and bindPermission}">
                            <div class="center">
                                <a href="#" data-act="binding" class="btn btn-xs btn-pink">
                                    <span class="ace-icon fa fa-globe"></span>
                                    绑定功能
                                </a>
                                <a href="/module/list" class="btn btn-xs btn-info">
                                    <span class="ace-icon fa fa-globe"></span>
                                    返回列表
                                </a>
                            </div>
                            </c:if>
                            <c:if test="${not empty page.results}">
                                <div class="pagerBar" id="pagerBar">
                                    <common:page url="${pageUrl}" type="3"/>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--编辑模块模态框（Modal）--%>
<div class="modal fade in" id="myModalEditPermission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common){
        var bindModuleId = "${param.bindModuleId}";
        //菜单高亮
        common.head();

        //添加、编辑功能方法
        var editDeptFn = function (id) {
            var id = id || null;
            var type = "add";
            var html = "";
            common.ajax.handle({
                type: "get",
                url:"/permission/edit",
                data:{
                    id:id
                },
                dataType: "html",
                async: false,
                succback: function (data) {
                    html = data;
                }
            });
            var $modal = $("#myModalEditPermission");
            $modal.html(html);

            var $form = $("#formPermission");
            if (id !=null) {
                type = "edit";
                $(".modal-title").html("修改功能");
            }


            $form.validate({
                rules: {
                    name:"required",
                    sort:{
                        number:true
                    },
                    url:"required",
                },
                messages: {
                    name:"功能名称不能为空",
                    sort:{
                        number:"请输入数字"
                    },
                    url: "功能路径不能为空",

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
                        url: "/permission/"+type+"/save.json",
                        data: $form.serialize()
                    });
                    return false;
                }
            });


        };

        //新增、编辑功能按钮
        var $editBtn = $('[data-target="#myModalEditPermission"]');
        $editBtn.on("click", function () {
            var id = $(this).data('id') || null;
            editDeptFn(id);
        });

        //删除
        $('[data-act="del"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否删除该功能？</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/permission/delete.json",
                    data:{
                        id:id
                    }
                });
            });
        });

        <c:if test="${displacePermission}">
        //显示/隐藏
        $(".state-btn").on("click", function () {
            var state = $(this).data("state");
            var act = state == 0 ? 1 : 0;
            var stateStr = act == 1 ? "显示" : "隐藏";
            var className = act == 1 ? "btn-yellow" : "btn-danger";
            var $btn = $(this);
            var id = $(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定设置为' + stateStr + '</p>', {icon: 3, title: "温馨提示"}, function (index) {
                layer.close(index);
                common.ajax.handle({
                    url: "/permission/displace.json",
                    data:{
                        id:id,
                        displace:act
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


        var $list = $("#listPan");
        //全选、反选
        common.checkBox($("#chkAll"), $list.find('[name="id"]'), $("#checkIds"));

        //绑定功能
        $('[data-act="binding"]').on("click", function () {
            var permissionIds = $("#checkIds").val();
            common.ajax.handle({
                url:"/permission/bind/save.json",
                data:{
                    moduleId:bindModuleId,
                    permissionIds:permissionIds
                }
            });
        });

    });
</script>

</body>
</html>