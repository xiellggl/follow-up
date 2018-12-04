<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑角色</title>
    <c:set var="pageName" value="financeUcOrgPage" />
    <%@include file="/inc/followup/csslink.jsp"%>
</head>
<body>
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
                            编辑角色
                        </small>
                    </h1>
                </div>
                <div class="col-xs-12">
                    <div class="row">
                        <div class="space-6"></div>

                        <div class="ui_form">
                            <from>
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <h3 class="ui_title">权限配置</h3>
                                        <input type="hidden" name="id" value="${deptVo.id}" />
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">*模块名称：</label>
                                            <div class="col-xs-12 col-sm-6">
                                                <input type="text" name="name" value="${deptVo.name}" class="form-control" required/>
                                            </div>
                                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">备注信息：</label>
                                            <div class="col-xs-12 col-sm-6">
                                                <textarea name="remark" class="form-control" style="height:60px;">${deptVo.remark}</textarea>
                                            </div>
                                        </div>
                                        <style>
                                            .conceal-tr{display: none;}
                                        </style>
                                        <dl>
                                            <dd>
                                                <dl>
                                                    <dt>
                                                        <label><input type="checkbox"><span>会员管理</span></label>
                                                        <i class="fa fa-chevron-dcu fa-chevron-down" data-flag="1"></i>
                                                    </dt>
                                                    <dd class="last conceal-tr click1" style="">
                                                        <label title="在线会员">

                                                            <input type="checkbox" name="permissionId" value="2340ca71006602a1">

                                                            <span>在线会员</span>
                                                        </label>
                                                        <label title="在线会员查询">

                                                            <input type="checkbox" name="permissionId" value="2340ca71006603a1">

                                                            <span>在线会员查询</span>
                                                        </label>
                                                    </dd>
                                                </dl>
                                            </dd>
                                        </dl>
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
                            </from>
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

    });
    //展开收起交互
    $(".fa-chevron-dcu").on('click', function(event) {
        var flag = $(this).attr("data-flag");
        $(".click"+flag).toggle(150);
        var $this=$(this);
        if($this.hasClass('fa-chevron-up')){
            $this.removeClass('fa-chevron-up').addClass('fa-chevron-down');
        }else{
            $this.removeClass('fa-chevron-down').addClass('fa-chevron-up');
        }
    });
</script>
</body>
</html>