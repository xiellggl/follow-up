<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
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
                    <span class="btn btn-primary" data-act="addDept">添加模块</span>
                    </a>
                </div>

                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                        <div>
                            <style>
                                .conceal-t{display: none;}
                            </style>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>模块名称</th>
                                    <th class="hidden-sm hidden-xs">状态</th>
                                    <th>功能路径</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>

                                <tbody>

                                <c:if test="${empty topDeptList}">
                                    <tr>
                                        <td colspan="6" class="no_data">暂无模块，请<a href="javascript:;" data-act="addDept">新增模块</a></td>
                                    </tr>
                                </c:if>

                                <c:forEach var="i" begin="1" end="1">
                                <tr data-id="${item.id}" data-pid="${item.pid}">
                                    <td> <i class="fa fa-chevron-dcu fa-chevron-down" data-flag="1"></i></td>
                                    <td>&nbsp首页</td>
                                    <td>
                                        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
                                            <span class="btn btn-minier btn-yellow">启用</span>
                                        </a>
                                    </td>
                                    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
                                    <td>管理首页信息</td>
                                    <td>
                                        <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                            <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="绑定功能">
                                            <i class="ace-icon fa fa-exchange bigger-130 red"></i></a>
                                    </td>
                                </tr>

                                <tr  class="conceal-t click1">
                                    <td><i class="fa fa-chevron-mcu fa-chevron-down" data-flag="1"></i></td>
                                    <td>&nbsp&nbsp二级模块</td>
                                    <td>
                                        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
                                            <span class="btn btn-minier btn-yellow">启用</span>
                                        </a>
                                    </td>
                                    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
                                    <td>可查看预约联系时间为今天的代理商</td>
                                    <td>
                                        <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                            <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="绑定功能">
                                            <i class="ace-icon fa fa-exchange bigger-130 red"></i></a>
                                    </td>
                                </tr>

                                <tr class="conceal-t link1">
                                    <td><i class="fa" data-flag="1"></i></td>
                                    <td>&nbsp&nbsp&nbsp这是功能权限3</td>
                                    <td>&nbsp</td>
                                    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
                                    <td>可查看不同状态下的代理商数量（适用于业务人员）</td>
                                    <td>
                                        <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
                                            <i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="绑定功能">
                                            <i class="ace-icon fa fa-exchange bigger-130 red"></i></a>
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
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common,validate,template){
        common.head("system",1);
        var editDeptFn = function (id,pid) {
            var id = id || 0, pid = pid||0;
            var url = "/followup/module/add";
            if (id > 0) {
                url = "/followup/module/dept/update/" + id;
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
                        url: "/followup/module/edit/save.json",
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

        //删除
        $('[data-act="del"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">确定删除此部门及下属所有部门</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/followup/module/delete/"+ id + ".json",
                });
            });
        });

        //展开收起交互
        $(".fa-chevron-dcu").on('click', function(event) {
            var flag = $(this).attr("data-flag");
            $(".click"+flag).toggle(100);
            var $this=$(this);
            if($this.hasClass('fa-chevron-up')){
                $this.removeClass('fa-chevron-up').addClass('fa-chevron-down');
            }else{
                $this.removeClass('fa-chevron-down').addClass('fa-chevron-up');
            }
        });

        $(".fa-chevron-mcu").on('click', function(event) {
            var flag = $(this).attr("data-flag");
            $(".link"+flag).toggle(100);
            var $this=$(this);
            if($this.hasClass('fa-chevron-up')){
                $this.removeClass('fa-chevron-up').addClass('fa-chevron-down');
            }else{
                $this.removeClass('fa-chevron-down').addClass('fa-chevron-up');
            }
        });

    });
</script>

</body>
</html>