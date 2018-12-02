<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<form id="form-id" class="form-horizontal" autocomplete="off">
<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">新增部门</h4>
        </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <input type="hidden" name="id" value="${deptVo.id}" />
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">部门名称：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">上级部门：</label>
                            <div class="col-xs-12 col-sm-6">
                                <select name="pid" class="form-control">
                                    <option></option>
                                    <%--自增序号，注意scope--%>
                                    <c:set var="index" value="0" scope="request" />
                                    <%--记录树的层次，注意scope--%>
                                    <c:set var="level" value="0" scope="request" />
                                    <c:import url="dept_edit_item.jsp" />
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">排序号：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="sortNo" value="${empty deptVo.sortNo?0:deptVo.sortNo}" class="form-control" onkeyup="this.value = this.value.replace(/[^0-9]/g,'')" />
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> </label>
                            <div class="col-xs-12 col-sm-6">
                                <label class="pos-rel" style="margin-top: 8px;">
                                    <input name="checkbox" class="ace" type="checkbox" autocomplete="off" ${deptVo.cityServer eq 1 ? 'checked' : ''}/>
                                    <span class="lbl">是否城市服务商？</span>
                                    <input name="cityServer" type="hidden" value="${empty deptVo.cityServer ? 0 : deptVo.cityServer}" autocomplete="off"/>
                                </label>
                            </div>
                        </div>
                        <div class="form-group" id="cityInviteCodeBox" style="display: none;">
                            <label class="col-sm-2 control-label no-padding-right">邀请码：</label>
                                <div class="col-xs-12 col-sm-6">
                                    <input type="text" name="cityInviteCode" class="form-control" disabled="disabled" autocomplete="off" placeholder="请输入数字"
                                           value="${deptVo.cityInviteCode}" onkeyup="this.value = this.value.replace(/[^0-9]/g,'')"/>
                                </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">部门描述：</label>
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

