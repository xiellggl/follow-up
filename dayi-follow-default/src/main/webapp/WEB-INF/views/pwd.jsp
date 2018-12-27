<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<div class="modal-dialog">
    <div class="modal-content">
        <form class="form-horizontal" autocomplete="off">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">修改密码</h4>
        </div>
        <div class="modal-body clearfix">
            <div class="row">
                <div class="col-xs-12">
                    <input type="password" style="display:none"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">旧密码： </label>
                        <div class="col-xs-12 col-sm-6">
                            <input type="password" name="oldPwd" class="form-control"/>
                        </div>
                        <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">新密码： </label>
                        <div class="col-xs-12 col-sm-6">
                            <input type="password" name="newPwd" id="newPwd" class="form-control"/>
                        </div>
                        <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">确认密码： </label>
                        <div class="col-xs-12 col-sm-6">
                            <input type="password" name="confirmNewPwd" class="form-control"/>
                        </div>
                        <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                    </div>
                    <div class="space-4"></div>
                </div>

            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary">
                提交
            </button>
            <button class="btn btn-info" type="reset">
                重置
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal">
                关闭
            </button>
        </div>
        </form>
    </div>
</div>