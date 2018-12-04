<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<form id="form-id" class="form-horizontal" autocomplete="off">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <input type="hidden" name="id" value="${deptVo.id}" />

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*用户姓名 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*账号 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="sortNo" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*邀请码 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="sortNo" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*密码 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="sortNo" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*手机号码 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="sortNo" value="${deptVo.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">角色 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <select name="pid" class="form-control">
                                    <c:forEach items="${deptList}" var="item" >
                                        <option value="${item.id}" ${deptVo.pid eq item.id ? 'selected' : ''}>${item.treeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">部门 ：</label>
                            <div class="col-xs-12 col-sm-6">
                                <select name="pid" class="form-control">
                                    <c:forEach items="${deptList}" var="item" >
                                        <option value="${item.id}" ${deptVo.pid eq item.id ? 'selected' : ''}>${item.treeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right inline"> 是否负责人： </label>
                            <div class="col-sm-9 inline align-middle" style="padding-top:5px;">
                                <label style="margin-right: 15px;">
                                    <input type="radio" name="isManager" value="0"
                                           {{id==null||isManager==0?'checked':''}}/>否</label>
                                <label>
                                    <input type="radio" name="isManager" value="1"
                                           {{isManager==1?'checked':''}}/> 是</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right inline"> 二级资产开关： </label>
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

