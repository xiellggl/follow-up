<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>模块管理</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <style>
        .conceal-t{display: none;}
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
                    <span class="btn btn-primary" data-toggle="modal" data-target="#myModalEditFollowuper">添加模块</span>
                    </a>
                </div>

                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                            <table class="table table-striped table-bordered table-hover">

                                <thead>
                                <tr>
                                    <th>模块名称</th>
                                    <th>状态</th>
                                    <th>菜单状态</th>
                                    <th>功能路径</th>
                                    <th>排序</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>

                                <tbody>


                                <c:if test="${empty page.results}">
                                    <tr>
                                        <td colspan="7" class="no_data">
                                            暂无模块，请
                                            <a href="#" data-toggle="modal"
                                               data-target="#myModalEditFollowuper">新增模块</a>
                                        </td>
                                    </tr>
                                </c:if>


                                <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item">

                                <c:forEach items="${item.menus}" var="item">

                                <tr id="${item.id} - ${item.parentId}">
                                    <td>${item.name}</td>
                                    <td>
                                        <a data-id="${item.id}" data-level="{$v.level}" href="{:url('admin/Sys/admin_menu_list',['pid'=>$v.id])}"
                                           style="cursor:pointer;" class="rule-list">
                                        <span class="fa {if condition='$v.level egt 4'}fa-minus{else /}fa-plus{/if} blue"></span>
                                        </a>
                                    </td>
                                    <td class="center">
                                        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
                                            <span class="btn btn-minier btn-yellow">启用</span>
                                        </a>
                                    </td>
                                    <td>${item.status}</td>
                                    <td>${item.url}</td>
                                    <td>${item.order}</td>
                                    <td>${item.description}</td>
                                    <td>
                                        <a href="#" data-id="1" data-toggle="modal"
                                           data-target="#myModalEditFollowuper"
                                           data-toggle="tooltip" title="修改">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                        </a>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                            <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="绑定功能">
                                            <i class="ace-icon fa fa-exchange bigger-130 red"></i></a>
                                    </td>
                                </tr>

                                </c:forEach>

                                </c:forEach>
                                </c:if>

                                </tbody>
                            </table>

                        <c:if test="${not empty page.results}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page2 url="${pageUrl}" type="3"/>
                            </div>
                        </c:if>

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
    seajs.use(["common","validate","template",],function(common,validate,template){
        //菜单高亮
        common.head("system",1);

        //添加、编辑模块方法
        var editDeptFn = function (id) {
            var id = id || 0;
            var url = "/module/edit";
            var keepurl = "/module/add/save.json";
            if (id > 0) {
                url = "/module/edit?id="+id;
                keepurl = "/module/add/edit.json";
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
                $(".modal-title").html("修改模块");
            }
            var $form = $("#form-id");
            $form.validate({
                rules: {
                    name:"required",
                    // module_id:{
                    //
                    // },
                },
                messages: {
                    name:"模块名称不能为空",
                    // module_id:{
                    //
                    // },

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
                        url: keepurl,
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
            layer.confirm('<p class="tc">是否删除该模块？</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/module/delete?id="+id
                });
            });
        });


        //打开子菜单
        $('body').on('click','.rule-list',function () {

            var $a=$(this),$tr=$a.parents('tr');
            var pid=$tr.attr('id');

            if($a.find('span').hasClass('fa-minus')){
                $("tr[id^='"+pid+"-']").attr('style','display:none');
                $a.find('span').removeClass('fa-minus').addClass('fa-plus');
            }else{
                if($("tr[id^='"+pid+"-']").length>0){
                    $("tr[id^='"+pid+"-']").attr('style','');
                    $a.find('span').removeClass('fa-plus').addClass('fa-minus');
                }else{
                    var url = this.href,id=$a.data('id'),level=$a.data('level');
                    $.post(url,{pid:id,level:level,id:pid}, function (data) {
                        if (data) {
                            $a.find('span').removeClass('fa-plus').addClass('fa-minus');
                            $tr.after(data);
                        }else{
                            $a.find('span').removeClass('fa-plus').addClass('fa-minus');
                        }
                        return false;
                    }, "json");
                }
            }
            return false;
        });

        //展开收起交互
        $('[data-ac="eye"]').on('click', function(event) {
            var me = this;
            var id = $(this).data('id');
            var curr = this.innerHTML;
            if(curr === '收起'){
                me.ip = 0;

            }else{
                me.ip = 1;
            }
            this.innerHTML = ['展开','收起'][me.ip];
            $('.link'+id)[['fadeOut','fadeIn'][me.ip]]();

        });


        //禁用/启用
        $(".state-btn").on("click", function () {
            var state = $(this).data("state");
            var act = state == 0 ? "true" : "false";
            var stateStr = act == "true" ? "启用" : "禁用";
            var className = act == "true" ? "btn-yellow" : "btn-danger";
            var $btn = $(this);
            var id = $(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定' + stateStr + '此模块</p>', {icon: 3, title: "温馨提示"}, function (index) {
                layer.close(index);
                common.ajax.handle({
                    url: "/module/enableModule?id="+id,
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