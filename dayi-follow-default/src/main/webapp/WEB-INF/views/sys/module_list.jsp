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
                                    <th>操作</th>
                                    <th class="hidden-sm hidden-xs">状态</th>
                                    <th>功能路径</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>

                                <tbody id="table" data-toggle="table">

                                <tr>
                                    <td>&nbsp首页</td>
                                    <td class="center"><a class="btn btn-minier btn-purple" data-ac="eye" data-id="30" >展开</a></td>
                                    <td>
                                        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
                                            <span class="btn btn-minier btn-yellow">启用</span>
                                        </a>
                                    </td>
                                    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
                                    <td>管理首页信息</td>
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

                                <tr  class="conceal-t link30">
                                    <td style="padding-left: 3%">&nbsp&nbsp二级模块</td>
                                    <td class="center"><a class="btn btn-minier btn-purple" data-ac="eye" data-id="30" >展开</a></td>
                                    <td>
                                        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
                                            <span class="btn btn-minier btn-yellow">启用</span>
                                        </a>
                                    </td>
                                    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
                                    <td>可查看预约联系时间为今天的代理商</td>
                                    <td>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="解除绑定">
                                            <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                    </td>
                                </tr>

                                <tr class="conceal-t link30">
                                    <td style="padding-left: 6%">这是功能权限3</td>
                                    <td></td>
                                    <td>
                                        <a class="state-btn" data-state="1" href="javascript:;" data-id="30" title="" data-original-title="已启用">
                                            <span class="btn btn-minier btn-yellow">启用</span>
                                        </a>
                                    </td>
                                    <td>http://spotnewuc.fiidee.loc/#/admin/member/user </td>
                                    <td>可查看不同状态下的代理商数量（适用于业务人员）</td>
                                    <td>
                                        <a href="#" data-act="del" data-toggle="tooltip" title="解除绑定">
                                            <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
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
            if (id > 0) {
                url = "/module/edit?id="+id
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
                    module_name:"required",
                    module_id:{

                    },
                    module_sort:{
                        required:true,
                        number:true
                    },
                    module_state:{

                    },
                    module_path:"required"
                },
                messages: {
                    module_name:"模块名称不能为空",
                    module_id:{

                    },
                    module_sort:{
                        required:"模块排序不能为空",
                        number:"请输入数字"
                    },
                    module_state:{

                    },
                    module_path: "功能路径不能为空"

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
                        url: "/module/edit/save.json",
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


        //展开收起交互
        $('[data-ac="eye"]').on('click', function(event) {
            var me = this;
            var id = $(this).data('id');
            var curr = this.innerHTML;
            if(curr === '收起'){
                me.ip = 0;
                // eye(id);
            }else{
                me.ip = 1;
            }
            this.innerHTML = ['展开','收起'][me.ip];
            $('.link'+id)[['fadeOut','fadeIn'][me.ip]]();

        });

        /** 展开收起交互 */
        function eye(fid,eid){

            $('.link'+fid+' [data-ac=eye]').each(function(i,btn){
                if(btn.innerHTML === '收起'&&$(btn).data('id') !== eid){
                    $(btn).click();
                    eye($(btn).data('id'));
                }
            });

        }

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
<%--<script type="text/javascript">--%>
    <%--/** ⚠️ 模版页不要用单行注释(异步请求会把后边的所有内容注释掉) */--%>
    <%--seajs.use('main',function(fn){--%>

        <%--fn.main(function(box,form,layout){--%>

            <%--form.submit();--%>

            <%--/** 展开收起交互 */--%>
            <%--layout.on('click','[data-ac=eye]',function(){--%>

                <%--var me = this, id = $(this).data('id'), curr = this.innerHTML;--%>

                <%--if(curr === '收起'){--%>
                    <%--me.ip = 0;--%>
                    <%--eye(id);--%>
                <%--}else{--%>
                    <%--me.ip = 1;--%>
                <%--}--%>

                <%--this.innerHTML = ['展开','收起'][me.ip];--%>

                <%--layout.find('.p'+id)[['fadeOut','fadeIn'][me.ip]]();--%>

            <%--});--%>

            <%--/** 展开收起交互 */--%>
            <%--function eye(fid,eid){--%>
                <%--$(layout.find('.p'+fid+' [data-ac=eye]')).each(function(i,btn){--%>
                    <%--if(btn.innerHTML === '收起'&&$(btn).data('id') !== eid){--%>
                        <%--$(btn).click();--%>
                        <%--eye($(btn).data('id'));--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>

<%--</script>--%>
</body>
</html>