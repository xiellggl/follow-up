<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
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
                    <a href="#" class="pull-right" data-toggle="modal" data-target="#myModalEditFollowuper">
                        <span class="btn btn-primary">新增用户</span>
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

                            <c:if test="${empty page.results}">
                            <tr>
                                <td colspan="6" class="no_data">
                                    暂无用户，请
                                    <a href="javascript:;" data-toggle="modal"
                                       data-target="#myModalEditFollowuper">新增用户</a>
                                </td>
                            </tr>
                            </c:if>

                            <c:if test="${not empty page.results}">
                            <c:forEach items="${page.results}" var="item">
                            <tr data-id="${item.id}">
                                <td>${item.name}</td>
                                <td>${item.userName}</td>
                                <td>${item.inviteCode}</td>
                                <td>${item.rolesName}</td>
                                <td>${item.department.name}</td>
                                <td>
                                    <a class="state-btn" data-state="${item.disable}" href="#"
                                       data-id="${item.id}" title="已${item.status}">
                                                <span
                                                        class="btn btn-minier ${item.disable eq 1 ? 'btn-yellow':'btn-danger'}">${item.status}</span>
                                    </a>
                                </td>
                                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                                <td>
                                    <a class="green" href="#" data-toggle="modal"
                                       data-target="#myModalEditFollowuper" data-id="${item.id}"
                                       data-toggle="tooltip" title="修改">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                    <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                        <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                    <a href="#" data-act="resetpwd" data-toggle="tooltip"
                                       title="重置密码">
                                        <i class="ace-icon fa fa-key bigger-130"></i>
                                    </a>
                                </td>
                            </tr>
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

<%--编辑跟进人模态框（Modal）--%>
<div class="modal fade in" id="myModalEditFollowuper" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
</div>

<%@include file="/inc/followup/script.jsp"%>
<script type="text/html" id="tplEditFlowUp">
<form class="form-horizontal" autocomplete="off">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    {{id>0?'修改':'添加'}}用户
                </h4>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        {{if id > 0}}
                        <input type="hidden" name="id" value="{{id}}">
                        {{/if}}

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3  control-label no-padding-right">*用户姓名 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" value="{{name}}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        {{if id==null}}
                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">*账号 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="userName" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">*邀请码 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="inviteCode" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">*密码 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="password" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>
                        {{/if}}

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">*手机号码 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="mobile"  value="{{mobile}}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">角色 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <select name="pid" class="form-control">

                                </select>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">部门 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <select name="deptId" class="form-control">
                                    <option value="">请选择所属部门</option>
                                    <c:forEach items="${deptList}" var="item">
                                        <option value="${item.id}">${item.treeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right inline">是否负责人： </label>
                            <div class="col-sm-9 inline align-middle" style="padding-top:5px;">
                                <label style="margin-right: 15px;">
                                    <input type="radio" name="isManager" value="0"
                                           {{id==null||isManager==0?'checked':''}}/>否</label>
                                <label>
                                    <input type="radio" name="isManager" value="1"
                                           {{isManager==1?'checked':''}}/> 是</label>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right inline">二级资产开关： </label>
                            <div class="col-sm-9 inline align-middle" style="padding-top:5px;">
                                <label style="margin-right: 15px;">
                                    <input type="radio" name="isManager" value="0"
                                           {{id==null||isManager==0?'checked':''}}/>开启</label>
                                <label>
                                    <input type="radio" name="isManager" value="1"
                                           {{isManager==1?'checked':''}}/> 关闭</label>
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
<script>
    seajs.use(["common", "template", "validate", "addMethod"], function (common, template) {
        //菜单高亮
        common.head("system",2);

        var editDeptFn = function (id) {
            var id = id || 0;
            var data = {};
            if (id > 0) {
                common.ajax.handle({
                    url: "/user/get/" + id + ".json",
                    // url: "/dept/getEditList/" + id + ".json",
                    async: false,
                    succback: function (json) {
                        data = json.result;
                    }
                });
                if (data.id==null) {
                    return false;
                }

            }
            var $modal = $("#myModalEditFollowuper");
            var html = template("tplEditFlowUp", data);
            $modal.html(html).show(300);
            var $form = $modal.find("form");

            var url ="/user/add/save.json";
            if (id > 0) {
                var url ="/user/update/save.json";
            }

            var $userName = $form.find('[name="userName"]');
            var $inviteCode = $form.find('[name="inviteCode"]');
            $form.find('[name="deptId"]').val(data.deptId);
            $form.validate({
                rules: {
                    //用户姓名：
                    name:"required",
                    //账号：
                    userName: {
                        required: true,
                        letterNum: true,
                        remote: {
                            url: '/user/check/userName.json',
                            cache: false,
                            async: false,
                            data: {
                                'userName': function () {
                                    return $userName.val();
                                },
                                "id": 0
                            }
                        }
                    },
                    //邀请码：
                    inviteCode: {
                        required: true,
                        remote: {
                            url: '/user/check/inviteCode.json',
                            cache: false,
                            async: false,
                            data: {
                                'inviteCode': function () {
                                    return $inviteCode.val();
                                },
                                "id": 0
                            }
                        }
                    },
                    //密码
                    "password": {
                        required: true,
                        isPassword: true,
                        minlength: 8,
                        maxlength: 20
                    },
                    //手机号码
                    mobile: {
                        required: true,
                        isMobile: true
                    }
                },
                messages: {
                    name:"用户姓名不能为空",
                    inviteCode: {required: "邀请码不能为空", remote: "此邀请码已存在"},
                    userName: {
                        required: "账号不能为空",
                        letterNum: "账号只能字母和数字",
                        remote: "此用户名已存在"
                    },
                    "password": {
                        required: "请输入密码",
                        isPassword: "密码须包含数字、字母或下划线",
                        minlength: "请输入8至20位密码",
                        maxlength: "请输入8至20位密码"
                    },
                    mobile: {
                        required: "手机号码不能为空",
                        isMobile: "请输入正确的手机号码"
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
                        url: url,
                        data: $form.serialize(),
                    });
                    return false;
                }
            });


        };


        //新增用户、修改用户
        var $editBtn = $('[data-target="#myModalEditFollowuper"]');
        $editBtn.on("click", function () {
            var id = $(this).data('id') || null;
            editDeptFn(id);
        });

        //删除
        $('[data-act="del"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">确定删除此用户</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/user/delete/"+ id + ".json",
                });
            });
        });

        //禁用/启用
        $(".state-btn").on("click", function () {
            var state = $(this).data("state");
            var act = state == 0 ? "enable" : "disable";
            var stateStr = act == "enable" ? "启用" : "禁用";
            var className = act == "enable" ? "btn-yellow" : "btn-danger";
            var $btn = $(this);
            var id = $(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定' + stateStr + '此用户</p>', {icon: 3, title: "温馨提示"}, function (index) {
                layer.close(index);
                common.ajax.handle({
                    url: "/user/disable/" + act + "/" + id + ".json",
                    succback: function (data) {
                        var btn = '<span class="btn btn-minier ' + className + '">' + stateStr + '</span>';
                        $btn.data("state", !state).html(btn).attr('data-original-title', "已" + stateStr);
                        return false;
                    }
                });
            });
            return false;
        });

        //重置密码
        $('[data-act="resetpwd"]').on("click", function () {
            var id = $(this).closest("tr").data("id");
            var pwd = common.dateFormat(new Date(), "yyyy-MM-dd").replace(/-/g, "");
            var html = '<div style="text-align: center;padding-top:20px;">新密码：<input type="text" class="input" name="pwd" value="' + pwd + '"></div>'
            layer.open({
                type: 1,
                title: "重置密码",
                btn: ["保存", "取消"],
                area: ["300px", "auto"],
                content: html,
                yes: function (index, $box) {
                    var $pwd = $box.find('[name="pwd"]');
                    if ($pwd.val() == "") {
                        layer.tips("请输入新密码", $pwd, {tips: 3});
                        return false;
                    }
                    common.ajax.handle({
                        url: "/user//resetpwd/" + id + ".json",
                        data: {pwd: $pwd.val()},
                        succback: function (data) {
                            common.successMsg(data.msg);
                            layer.close(index);
                        }
                    });
                }
            });
            return false;
        });


    });
</script>
</body>
</html>