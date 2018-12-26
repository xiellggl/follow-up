<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>跟进人管理</title>
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
                    <li class="active">跟进人管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            跟进人管理
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal" style="max-width: 1000px;">
                        <div class="clearfix maintop">
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-phone"></i>
                                    </span>
                                    <input type="text" name="mobile" class="form-control admin_sea"
                                           value="${param.mobile}" placeholder="手机号"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-cog"></i>
                                    </span>
                                    <select name="myDeptId" class="form-control admin_sea">
                                            <c:forEach items="${deptList}" var="item" >
                                                <option value="${item.id}" ${deptVo.pid eq item.id ? 'selected' : ''}>${item.treeName}</option>
                                            </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 hidden-xs btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-barcode"></i>
                                    </span>
                                    <input type="text" name="inviteCode" class="form-control admin_sea"
                                           value="${inviteCode}" placeholder="邀请码"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="btn-group dropup">
                                    <button type="submit" class="btn btn-xs btn-purple">
                                        <span class="ace-icon fa fa-search"></span>
                                        搜索
                                    </button>
                                    <button data-toggle="dropdown" class="btn btn-xs btn-info dropdown-toggle hidden visible-xs" aria-expanded="false">
                                        <span class="ace-icon fa fa-caret-down icon-only"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="./list">显示全部</a>
                                        </li>
                                    </ul>
                                </div>
                                <a href="./list" class="btn btn-xs btn-info hidden-xs">
                                    <span class="ace-icon fa fa-globe"></span>
                                    显示全部
                                </a>
                                <a class="btn btn-xs btn-success" href="/followup/all/agent/list" >
                                    <span class="ace-icon fa fa-gift"></span>
                                    全部明细
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="space-10"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>跟进人</th>
                                <th class="hidden-xs">邀请码</th>
                                <th>账号</th>
                                <th>所属团队</th>
                                <th>状态</th>
                                <th colspan="2" style="text-align: center;">跟进客户数(代理商/创客)</th>
                                <th class="tr hidden-xs" colspan="2" style="text-align: center;">资金管理规模(代理商/创客)</th>
                                <th class="hidden-sm hidden-xs">创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.results}">
                                <tr>
                                    <td colspan="12" class="no_data">暂无跟进人， 请新增跟进人
                                        <%--<a href="#" data-toggle="modal" data-target="#myModalEditFollowuper">新增跟进人</a>--%>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item">
                                    <tr data-id="${item.id}">
                                        <td>${item.name}</td>
                                        <td class="hidden-xs">${item.inviteCode}</td>
                                        <td>${item.userName}</td>
                                        <td>${item.deptName}</td>
                                        <td>
                                            <a class="state-btn" data-state="${item.disable}" href="#"
                                               data-id="${item.id}" title="已${item.status}">
                                                <span
                                                    class="btn btn-minier ${item.disable eq 1 ? 'btn-yellow':'btn-danger'}">${item.status}</span>
                                            </a>
                                        </td>
                                        <td>${item.agentNum}</td>
                                        <td>${item.orgNum}</td>
                                        <td class="tr hidden-xs">${item.agentFundFm}</td>
                                        <td class="tr hidden-xs">${item.orgFundFm}</td>
                                        <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createTime}"
                                                                                        pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <%--<a class="green" href="#" data-toggle="modal"--%>
                                               <%--data-target="#myModalEditFollowuper" data-id="${item.id}"--%>
                                               <%--data-toggle="tooltip" title="修改">--%>
                                                <%--<i class="ace-icon fa fa-pencil bigger-130"></i>--%>
                                            <%--</a>--%>
                                            <%--<a href="#" data-act="resetpwd" data-toggle="tooltip"--%>
                                               <%--title="重置密码">--%>
                                                <%--<i class="ace-icon fa fa-key bigger-130"></i>--%>
                                            <%--</a>--%>
                                            <a href="/followup/agent/list?followId=${item.id}" data-toggle="tooltip" title="查看明细">
                                                <i class="ace-icon fa  fa-leaf bigger-130"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
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
<%--编辑跟进人模态框（Modal）--%>
<%--<div class="modal fade in" id="myModalEditFollowuper" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"--%>
     <%--aria-hidden="true">--%>
<%--</div>--%>

<%@include file="/inc/followup/script.jsp"%>
<%--<script type="text/html" id="tplEditFlowUp">--%>
    <%--<form class="form-horizontal" autocomplete="off">--%>
        <%--<div class="modal-dialog">--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal"--%>
                            <%--aria-hidden="true">×--%>
                    <%--</button>--%>
                    <%--<h4 class="modal-title" id="myModalLabel">--%>
                        <%--{{id>0?'修改':'添加'}}跟进人--%>
                    <%--</h4>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-xs-12">--%>
                            <%--{{if id > 0}}--%>
                            <%--<input type="hidden" name="id" value="{{id}}">--%>
                            <%--{{/if}}--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label no-padding-right"> 姓名： </label>--%>
                                <%--<div class="col-xs-12 col-sm-6">--%>
                                    <%--<input type="text" name="linkPerson" value="{{linkPerson}}" class="form-control"--%>
                                           <%--required/>--%>
                                <%--</div>--%>
                                <%--<div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>--%>
                            <%--</div>--%>
                            <%--<div class="space-4"></div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label no-padding-right"> 手机号码： </label>--%>
                                <%--<div class="col-xs-12 col-sm-6">--%>
                                    <%--<input type="text" name="mobile" value="{{mobile}}" class="form-control" required/>--%>
                                <%--</div>--%>
                                <%--<div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>--%>
                            <%--</div>--%>
                            <%--<div class="space-4"></div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label no-padding-right"> 所属部门： </label>--%>
                                <%--<div class="col-xs-12 col-sm-6">--%>
                                    <%--<select name="deptId" class="form-control">--%>
                                        <%--<option value="">请选择所属部门</option>--%>
                                        <%--<c:forEach items="${deptList}" var="item">--%>
                                            <%--<option value="${item.id}">${item.treeName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                <%--<div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>--%>
                            <%--</div>--%>
                            <%--<div class="space-4"></div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label no-padding-right inline"> 是否负责人： </label>--%>
                                <%--<div class="col-sm-9 inline align-middle" style="padding-top:5px;">--%>
                                    <%--<label style="margin-right: 15px;"><input type="radio" name="isManager" value="0"--%>
                                                                              <%--{{id==null||isManager==0?'checked':''}}/>--%>
                                        <%--否</label>--%>
                                    <%--<label><input type="radio" name="isManager" value="1"--%>
                                                  <%--{{isManager==1?'checked':''}}/> 是</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="space-4"></div>--%>
                            <%--{{if id==null}}--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label no-padding-right"> 邀请码： </label>--%>
                                <%--<div class="col-xs-12 col-sm-6">--%>
                                    <%--<input type="text" name="inviteCode" class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>--%>
                            <%--</div>--%>
                            <%--<div class="space-4"></div>--%>
                            <%--<input type="password" style="display:none"/>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label no-padding-right"> 账号： </label>--%>
                                <%--<div class="col-xs-12 col-sm-6">--%>
                                    <%--<input type="text" name="userName" class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>--%>
                            <%--</div>--%>
                            <%--<div class="space-4"></div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-2 control-label no-padding-right"> 密码： </label>--%>
                                <%--<div class="col-xs-12 col-sm-6">--%>
                                    <%--<input type="password" name="password" class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>--%>
                            <%--</div>--%>
                            <%--<div class="space-4"></div>--%>
                            <%--{{/if}}--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                    <%--<button type="submit" class="btn btn-primary">--%>
                        <%--提交保存--%>
                    <%--</button>--%>
                    <%--<button class="btn btn-info" type="reset">--%>
                        <%--重置--%>
                    <%--</button>--%>
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">--%>
                        <%--关闭--%>
                    <%--</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</form>--%>
<%--</script>--%>
<script>
    seajs.use(["common", "template", "validate", "addMethod"], function (common, template) {
        common.head();
        // var editFlowUpFn = function (id) {
        //     var id = id || 0;
        //     var data = {};
        //     if (id > 0) {
        //         common.ajax.handle({
        //             url: "/followup/manage/followuper/info/" + id + ".json",
        //             async: false,
        //             succback: function (json) {
        //                 data = json.item;
        //             }
        //         });
        //         if (data.id == null) {
        //             return;
        //         }
        //     }
        //
        //     var $modal = $("#myModalEditFollowuper");
        //     var html = template("tplEditFlowUp", data);
        //     $modal.html(html).show(300);
        //     var $form = $modal.find("form");
        //
        //     var url = "/followup/manage/followuper/add/save.json";
        //     if (id > 0) {
        //         url = "/followup/manage/followuper/update/save.json";
        //     }
        //
        //     var $userName = $form.find('[name="userName"]');
        //     var $inviteCode = $form.find('[name="inviteCode"]');
        //     $form.find('[name="deptId"]').val(data.deptId);
        //     $form.validate({
        //         rules: {
        //             linkPerson: "required",
        //             mobile: {
        //                 required: true,
        //                 isMobile: true
        //             },
        //             deptId: "required",
        //             inviteCode: {
        //                 required: true,
        //                 remote: {
        //                     url: '/followup/manage/followuper/check/inviteCode.json',
        //                     cache: false,
        //                     async: false,
        //                     data: {
        //                         'inviteCode': function () {
        //                             return $inviteCode.val();
        //                         },
        //                         "id": 0
        //                     }
        //                 }
        //             },
        //             userName: {
        //                 required: true,
        //                 letterNum: true,
        //                 remote: {
        //                     url: '/followup/manage/followuper/check/userName.json',
        //                     cache: false,
        //                     async: false,
        //                     data: {
        //                         'userName': function () {
        //                             return $userName.val();
        //                         },
        //                         "id": 0
        //                     }
        //                 }
        //             },
        //             "password": {
        //                 required: true,
        //                 isPassword: true,
        //                 minlength: 8,
        //                 maxlength: 20
        //             }
        //         },
        //         messages: {
        //             linkPerson: "姓名不能为空",
        //             mobile: {
        //                 required: "手机号码不能为空",
        //                 isMobile: "请输入正确的手机号码"
        //             },
        //             deptId: "请选择所属部门",
        //             inviteCode: {required: "邀请码不能为空", remote: "此邀请码已存在"},
        //             userName: {
        //                 required: "账号不能为空",
        //                 letterNum: "账号只能字母和数字",
        //                 remote: "此用户名已存在"
        //             },
        //             "password": {
        //                 required: "请输入密码",
        //                 isPassword: "密码须包含数字、字母或下划线",
        //                 minlength: "请输入8至20位密码",
        //                 maxlength: "请输入8至20位密码"
        //             }
        //         },
        //         errorPlacement: function (error, element) {
        //             var $tipsBox = element.closest(".form-group").find(".tips_box");
        //             if ($tipsBox.length) {
        //                 $tipsBox.html(error);
        //             } else {
        //                 element.after(error);
        //             }
        //         },
        //         errorClass: "field-error",
        //         success: function (label, element) {
        //             label.remove();
        //             return true;
        //         },
        //         submitHandler: function (form) {
        //             common.ajax.handle({
        //                 url: url,
        //                 data: $form.serialize(),
        //             });
        //             return false;
        //         }
        //     });
        // };

        //新增跟进人
        // $('[data-act="addFlowUp"]').on("click", function () {
        //     editFlowUpFn();
        // });

        //修改
        // $('[data-act="modify"]').on("click", function () {
        //     var id = $(this).closest("tr").data("id");
        //     editFlowUpFn(id);
        // });

        // var $editBtn = $('[data-target="#myModalEditFollowuper"]');
        // $editBtn.on("click", function () {
        //     var id = $(this).data('id') || null;
        //     editFlowUpFn(id);
        // });

        //禁用/启用
        $(".state-btn").on("click", function () {
            var state = $(this).data("state");
            var act = state == 0 ? "enable" : "disable";
            var stateStr = act == "enable" ? "启用" : "禁用";
            var className = act == "enable" ? "btn-yellow" : "btn-danger";
            var $btn = $(this);
            var id = $(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定' + stateStr + '此跟进人</p>', {icon: 3, title: "温馨提示"}, function (index) {
                layer.close(index);
                common.ajax.handle({
                    url: "/followup/manage/followuper/" + act + "/" + id + ".json",
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
        // $('[data-act="resetpwd"]').on("click", function () {
        //     var id = $(this).closest("tr").data("id");
        //     var pwd = common.dateFormat(new Date(), "yyyy-MM-dd").replace(/-/g, "");
        //     var html = '<div style="text-align: center;padding-top:20px;">新密码：<input type="text" class="input" name="pwd" value="' + pwd + '"></div>'
        //     layer.open({
        //         type: 1,
        //         title: "重置密码",
        //         btn: ["保存", "取消"],
        //         area: ["300px", "auto"],
        //         content: html,
        //         yes: function (index, $box) {
        //             var $pwd = $box.find('[name="pwd"]');
        //             if ($pwd.val() == "") {
        //                 layer.tips("请输入新密码", $pwd, {tips: 3});
        //                 return false;
        //             }
        //             common.ajax.handle({
        //                 url: "/followup/manage/followuper/resetpwd/" + id + ".json",
        //                 data: {pwd: $pwd.val()},
        //                 succback: function (data) {
        //                     common.successMsg(data.msg);
        //                     layer.close(index);
        //                 }
        //             });
        //         }
        //     });
        //     return false;
        // });
    });
</script>
</body>
</html>