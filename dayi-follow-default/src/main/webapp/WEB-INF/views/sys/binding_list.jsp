<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>绑定模块</title>
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
                    <a href="#" class="pull-right">
                        <span class="btn btn-primary">添加模块</span>
                    </a>
                </div>

                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                        <div>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>功能名称</th>
                                    <th class="hidden-sm hidden-xs">功能路径</th>
                                    <th>添加时间</th>
                                    <th>修改时间</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${empty topDeptList}">
                                    <tr>
                                        <td colspan="5" class="no_data">暂无模块，请<a href="javascript:;" data-act="addDept">新增模块</a></td>
                                    </tr>
                                </c:if>
                                <%--自增序号，注意scope--%>
                                <c:set var="index" value="0" scope="request" />
                                <%--记录树的层次，注意scope--%>
                                <c:set var="level" value="0" scope="request" />
                                <c:import url="binding_item.jsp" />
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common,validate,template){
        common.head("system",1);
        var editDeptFn = function (id,pid) {
            var id = id || 0, pid = pid||0;
            var url = "/followup/manage/dept/add";
            if (id > 0) {
                url = "/followup/manage/dept/update/" + id;
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
                $(".modal-title").html("修改部门");
            }
            var $form = $("#form-id");
            $form.validate({
                rules: {
                    name:"required",
                    sortNo:{
                        number:true
                    }
                },
                messages: {
                    name:"模块名称不能为空",
                    sortNo:{
                        number:"请输入数字"
                    }
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
                        url: "/followup/manage/dept/dept/save.json",
                        data: $form.serialize(),
                    });
                    return false;
                }
            });


        };

        //新增模块
        $('[data-act="addDept"]').on("click",function () {
            editDeptFn();
        });

        //修改
        $('[data-act="edit"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            editDeptFn(id);
        });

        // 是否城市服务商勾选框
        $('body').on('change','[name="checkbox"]',function () {
            var mi = this, ckType = mi.checked?1:0;
            $(mi).siblings('[name="cityServer"]').val(ckType);
            var $cityInviteCodeBox = $("#cityInviteCodeBox");
            if(ckType){
                $cityInviteCodeBox.show().find('input').removeAttr('disabled');
            }else{
                $cityInviteCodeBox.hide().find('input').attr('disabled',"disabled");
            }
        });

        //删除
        $('[data-act="del"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">确定删除此部门及下属所有部门</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/followup/manage/dept/dept/del/"+ id + ".json",
                });
            });
        });

    });
</script>
</body>
</html>