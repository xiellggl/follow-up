<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色${empty role.id ? "添加" : "修改"}-角色管理</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <style>
        .permission-setlist li{list-style: none; padding: 0; margin-left: 2em;}
        .permission-setlist{ position: relative; margin: 0; padding-top:10px; margin-left: -2em;}
        .permission-setlist ul{padding:5px 0;margin: 0;}
        .permission-setlist .last{background-color: #f5f5f5; padding-left: 20px;}
        .permission-setlist li{ clear: both;}
        .permission-setlist .last .option-item{ clear: none; float: left; width: 160px; margin: 0;}
        .permission-setlist .tit{margin-bottom: 5px;color:#933;}
        .permission-setlist .checked{color: #00aeef;}

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
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li class="active">角色管理</li>
                    <li class="active">角色${empty role.id ? "添加" : "修改"}</li>
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
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            角色${empty id ? "添加" : "修改"}
                        </small>
                    </h1>
                    <a href="./list" class="pull-right">
                        <span class="btn btn-xs">返回</span>
                    </a>
                </div>

                <div class="col-xs-12">
                    <form id="formEditRole" class="form-horizontal" autocomplete="off">
                        <input type="hidden" name="id" value="${role.id}" />

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*角色名称：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" value="${role.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">角色备注：</label>
                            <div class="col-xs-12 col-sm-6">
                                <textarea name="descript" class="${role.descript}" style="height:60px;"></textarea>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group" style="position: relative;">
                            <label class="col-sm-2 control-label no-padding-right">权限控制：</label>
                            <div class="col-xs-12 col-sm-10">
                                <ul id="permissionSetlist" class="permission-setlist">
                                    <c:set var="moduleList" value="${menus}" />
                                    <%@ include file="module_role_item.jsp" %>
                                </ul>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box" style="position: absolute; top:5px; left: 0; width: 100%; text-align: center;"></div>
                        </div>
                        <div class="form-group" style="text-align: center;">
                            <button type="submit" data-act="submit" class="btn btn-primary">提交</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--编辑模块模态框（Modal）--%>
<div class="modal fade in" id="myModalEditRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common){
        //菜单高亮
        common.head("system",4);
        var $form = $("#formEditRole");
        var type = "${empty role.id ? "add" : "edit"}";


        var checkFn = function ($o,type) {
            var $p = $o.closest("ul");
            if(!$p.length){
                return;
            }
            var $sp = $p.closest("li");
            var len = $p.find('[data-rel="'+type+'"]').length;
            var checked_len = $p.find('[data-rel="'+type+'"]:checked').length;


            $p.find('[data-rel="permission"]').each(function () {
                if($(this).prop("checked")){
                    $(this).closest("label").addClass("checked");
                }else {
                    $(this).closest("label").removeClass("checked");
                }
            });


            if($sp.length){
                //向上级反馈
                $sp.find('[data-rel="menu"]').prop("checked",len==checked_len);
                checkFn($sp,"menu");
            }
        };

        //功能项
        var $permissionItem = $form.find('[name="permissionId"]');
        $permissionItem.on("change",function () {
            checkFn($(this).closest("li"),"permission");
        });

        //菜单项
        var $menuItem =  $form.find('[data-rel="menu"]');
        $menuItem.on("change",function () {
            //全选/返选子级
            var $sub = $(this).closest("li").find("ul");
            $sub.find('[data-rel="menu"],[name="permissionId"]').prop("checked",$(this).prop("checked"));
            checkFn($(this).closest("li"),"menu");
        });

        $permissionItem.change();

        $form.validate({
            rules: {
                name:"required",
                permissionId:"required"
            },
            messages: {
                name:"角色名称不能为空",
                permissionId:"请选择功能项"
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
                    url: "/role/"+type+"/save.json",
                    data: $form.serialize(),
                    succback:function () {
                        $form[0].reset();
                        common.successMsg("操作成功",function(){
                            location.href="./list"
                        });
                    }
                });
                return false;
            }
        });
    });

</script>
</body>
</html>