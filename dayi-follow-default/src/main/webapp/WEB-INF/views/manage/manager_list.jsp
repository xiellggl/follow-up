<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>管理员管理</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/bootstrap/plug/multiselect/bootstrap-multiselect.css" />
    <style>
        .multiselect-native-select .btn-group>.btn>.caret{ border-top-color: #666; margin-top:0;}

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
                    <li class="active">管理员管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            管理员管理
                        </small>
                    </h1>

                    <a href="#" class="pull-right" data-act="addDept" data-toggle="modal" data-target="#myModalEditFollowuper">
                        <span class="btn btn-xs btn-danger" type="button" data-act="addAdmin">新增管理员</span>
                    </a>
                </div>
                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>
                        <table class="table table-striped table-bordered table-hover dataList_table">
                            <thead>
                            <tr>
                                <th>管理员名称</th>
                                <th>账号</th>
                                <th>状态</th>
                                <th>负责部门</th>
                                <th class="hidden-sm hidden-xs">创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.items}">
                                <tr>
                                    <td colspan="6" class="no_data">暂无管理员， 请<a href="javascript:;" data-act="addAdmin">新增管理员</a></td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.items}">
                                <c:forEach items="${page.items}" var="item" >
                                    <tr data-id="${item.id}">
                                        <td>${item.linkPerson}</td>
                                        <td>${item.userName}</td>
                                        <td>
                                            <a class="state-btn" data-state="${item.disable}" href="javascript:;"
                                               data-id="${item.id}" title="已${item.statusStr}">
                                                <span class="btn btn-minier ${item.disable eq 1 ? 'btn-yellow':'btn-danger'}">${item.statusStr}</span>
                                            </a>
                                        </td>
                                        <td>${item.chargeDeptName}</td>
                                        <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="modify" data-toggle="tooltip" title="修改">
                                                <i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                            <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                                <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty page.items}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page url="${pageUrl}" type="3" />
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
<div class="modal fade in" id="myModalEditFollowuper" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
</div>
<%@include file="/inc/followup/script.jsp"%>
<%--js引用块--%>
<script type="text/html" id="tplEditAdmin">
    <form id="form-id" class="form-horizontal" autocomplete="off">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">新增管理员</h4>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <input type="hidden" name="id" value="{{id}}" />

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right" >管理员名称</label>
                                <div class="col-xs-12 col-sm-6">
                                    <input type="text" name="linkPerson" value="{{linkPerson}}" class="form-control" />
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">手机号码</label>
                                <div class="col-xs-12 col-sm-6">
                                    <input type="text" name="mobile" value="{{mobile}}" class="form-control" />
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">负责部门</label>
                                <div class="col-xs-12 col-sm-6">
                                    <select name="chargeDeptId" multiple="multiple">
                                        <c:forEach items="${deptList}" var="item">
                                            <c:if test="${empty item.subDeptList}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:if>
                                            <c:if test="${not empty item.subDeptList}">
                                                <optgroup label="${item.name}">
                                                <c:forEach items="${item.subDeptList}" var="item2">
                                                    <option value="${item2.id}">${item2.name}</option>
                                                </c:forEach>
                                                </optgroup>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>

                            {{if id==null}}
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">账号</label>
                                <div class="col-xs-12 col-sm-6"><input type="text" name="userName" value="" class="form-control" /></div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">密码</label>
                                <div class="col-xs-12 col-sm-6"><input type="password" name="password" class="form-control" /></div>
                                <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                            </div>
                            {{/if}}
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
<%--页面js入口--%>
<script>
    seajs.use(["common","template","multiselect","validate","addMethod"],function(common,template){
        common.head("manageManager");

        var editAdminFn=function (id,pid) {

            var id=id||0,pid=pid||0;
            var data={};
            if(id>0){
                common.ajax.handle({
                    url:"/followup/manage/manager/info/" + id + ".json",
                    async:false,
                    succback: function (json) {
                        data = json.item;
                    }
                });
                if(data.id==null){
                    return;
                }
            }
            var $modal = $("#myModalEditFollowuper");
            var html=template("tplEditAdmin",data);
            $modal.html(html).show();
            var $form = $("#form-id");
            var $userName = $form.find('[name="userName"]');
            var $chargeDeptId = $form.find('[name="chargeDeptId"]');
            if (id > 0) {
                $(".modal-title").html("修改管理员");
                var chargeDeptId = data.chargeDeptId;
                if(chargeDeptId!=null){
                    var chargeDeptIdArr = chargeDeptId.split(",");
                    $chargeDeptId.find('option').each(function(i,content){
                        if($.inArray($.trim(content.value),chargeDeptIdArr)>=0){
                            this.selected=true;
                        }
                    });
                }

            }

            //多选下拉
            $chargeDeptId.multiselect({
                nonSelectedText: '请选择负责部门',
                allSelectedText:"全部选中",
                nSelectedText: '个选中',
                buttonClass: 'btn btn-white',
            });

            $form.validate({
                rules: {
                    linkPerson:"required",
                    mobile:{
                        required:true,
                        isMobile:true
                    },
                    chargeDeptId:"required",
                    userName:{
                        required:true,
                        letterNum:true,
                        remote: {
                            url: '/followup/manage/manager/check/userName.json',
                            cache: false,
                            async: false,
                            data: {
                                'userName': function(){
                                    return $userName.val();
                                },
                                "id":0
                            }
                        }
                    },
                    "password":{
                        required:true,
                        isPassword:true,
                        minlength:8,
                        maxlength:20
                    }
                },
                messages: {
                    linkPerson:"姓名不能为空",
                    mobile:{
                        required:"手机号码不能为空",
                        isMobile:"请输入正确的手机号码"
                    },
                    chargeDeptId:"请选择负责部门",
                    userName:{
                        required:"账号不能为空",
                        letterNum:"账号只能字母和数字",
                        remote:"此用户名已存在"
                    },
                    "password":{
                        required:"请输入密码",
                        isPassword:"密码须包含数字、字母或下划线",
                        minlength:"请输入8至20位密码",
                        maxlength:"请输入8至20位密码"
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
                        url:"/followup/manage/manager/save.json",
                        data: $form.serialize()
                    });
                    return false;
                }
            });

        };

        //新增管理员
        $('[data-act="addAdmin"]').on("click",function () {
            editAdminFn();
        });

        //修改
        $('[data-act="modify"]').on("click",function () {
            var id=$(this).closest("tr").data("id");
            editAdminFn(id);
        });

        //禁用/启用
        $(".state-btn").on("click", function () {
            var state = $(this).data("state");
            var act = state == 0 ? "enable" : "disable";
            var stateStr = act == "enable" ? "启用" : "禁用";
            var className = act == "enable" ? "btn-yellow" : "btn-danger";
            var $btn = $(this);
            var id = $(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定'+(act=="enable" ? "启用" : "禁用")+'此管理员</p>', {icon: 3, title: "温馨提示"}, function (index) {
                layer.close(index);
                common.ajax.handle({
                    url:"/followup/manage/manager/"+act+"/"+ id + ".json",
                    succback: function (data) {
                        var btn = '<span class="btn btn-minier ' + className + '">' + stateStr + '</span>';
                        $btn.data("state", !state).html(btn).attr('data-original-title', "已" + stateStr);
                        return false;
                    }
                });
            });
            return false;
        });

        //删除
        $('[data-act="del"]').on("click",function () {
            var act=$(this).data("act");
            var id=$(this).closest("tr").data("id");
            layer.confirm('<p class="tc">是否确定删除此管理员</p>',{title:"温馨提示"},function () {
                common.ajax.handle({
                    url:"/followup/manage/manager/del/"+ id + ".json"
                });
            });
        });
    });
</script>
</body>
</html>