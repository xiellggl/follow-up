<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<form id="form-id" class="form-horizontal" autocomplete="off">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">添加功能</h4>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <input type="hidden" name="id" value="${deptVo.id}" />

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*添加功能：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*排序号：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="sortNo" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*功能路径：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="path" value="${deptVo.name}" class="form-control"/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">备注信息：</label>
                            <div class="col-xs-12 col-sm-6">
                                <textarea name="remark" class="form-control" style="height:60px;">${deptVo.remark}</textarea>
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
