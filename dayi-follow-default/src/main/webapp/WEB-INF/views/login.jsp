<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>系统登录</title>
    <%@include file="/inc/followup/csslink.jsp" %>
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center" style="padding-top:50px;padding-bottom: 20px;">
                        <img src="/static/followup/img/logo.png"/>
                        <h1>
                            <span class="white" id="id-text2">跟进人管理系统</span>
                        </h1>
                    </div>
                    <div class="space-6"></div>
                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        系统登录
                                    </h4>
                                    <div class="space-6"></div>
                                    <form id="login">
                                        <fieldset>
                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                    <input type="text" class="form-control" name="username"
                                                           id="username" value="${cookie.followup_username.value}"
                                                           placeholder="请输入您的账号" required/>
                                                    <i class="ace-icon fa fa-user"></i>
                                                </span>
                                            </label>
                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                    <input type="password" class="form-control" name="password"
                                                           id="password" placeholder="请输入登录密码" required/>
                                                    <i class="ace-icon fa fa-lock"></i>
                                                </span>
                                            </label>
                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input name="rememberme" type="checkbox" checked="checked" class="ace"/>
                                                    <span class="lbl"> 记住账号</span>
                                                </label>

                                                <button type="submit"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">登录</span>
                                                </button>
                                            </div>
                                            <input type="hidden" name="goTo" value="${goTo}">
                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div><!-- /.widget-main -->
                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->
                    </div><!-- /.position-relative -->
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<%@include file="/inc/followup/script.jsp" %>
<script>
    seajs.use(["common", "validate", "addMethod"], function (common) {
        var param_message = "${param.errmsg}";
        if (param_message != "") {
            layer.alert('<div style="text-align: center;color: red; font-size: 16px;">' + param_message + '</div>', {title: "温馨提示"})
        }
        var $form = $('#login');
        $form.find('input').focus(function () {
            $(".sysError").remove();
        });

        <%--var url = "${pageContext.session.getAttribute("goTo")}";--%>
        <%--if (typeof url == "undefined" || url == null || url == "") {--%>
        <%--url="/"--%>
        <%--}--%>

        var url = document.URL;
        var start = url.indexOf("#");
        if (start == -1) {
            url = "/"
        } else {
            url = url.substring(start + 1, url.length);
        }


        $form.validate({
            errorPlacement: function (error, element) {
                element.after(error);
            },
            errorClass: "field-error",
            submitHandler: function (form) {
                var username = $form.find('[name="username"]').val(),
                    isCheck = $form.find('[name="rememberme"]').is(':checked');
                if (isCheck) {
                    common.Cookie.set('followup_username', username, {hours: 7 * 24, secure: false});
                } else {
                    common.Cookie.remove("followup_username");
                }
                common.ajax.handle({
                    url: "/user/login/post",
                    data: $form.serialize(),
                    succback: function (data) {
                        $form[0].reset();
                        common.successMsg(data.msg, function () {
                            window.location.href = url
                        });
                    }
                });
            }
        });
    });
</script>
</body>
</html>