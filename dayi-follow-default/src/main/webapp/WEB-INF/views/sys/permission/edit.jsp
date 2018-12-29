<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<form id="formPermission" class="form-horizontal" autocomplete="off">
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
                        <input type="hidden" name="id" value="${permission.id}" />

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*功能名称：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" value="${permission.name}" class="form-control" />
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="form-group">

                            <label class="col-sm-2 control-label no-padding-right">所属页面：</label>
                            <div class="col-xs-12 col-sm-6">
                                <select name="parentid" class="chosen-select form-control">
                                    <option value="">模块页面级</option>
                                    <c:forEach items="${parentList}" var="item">
                                        <option value="${item.id}" ${permission.parentid eq item.id ? 'selected' : ''}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">排序号：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="sort" value="${permission.sort}" class="form-control" />
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>


                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*功能路径：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="url" value="${permission.url}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*显示状态：</label>
                            <div class="col-sm-9 inline align-middle" style="padding-top:8px;">
                                <label style="margin-right: 15px;">
                                    <input type="radio" name="displayStatus" value="1" ${permission.displayStatus eq 1 ? "checked" : ""} />
                                    显示
                                </label>
                                <label>
                                    <input type="radio" name="displayStatus" value="0" ${empty permission.id or permission.displayStatus eq 0 ? "checked" : ""} />
                                    隐藏
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">所属模块：</label>
                            <div class="col-xs-12 col-sm-6">
                                <select name="moduleid" class="chosen-select form-control">
                                    <option value="">顶级模块</option>
                                    <c:forEach var="item" items="${menus}">
                                        <option value="${item.id}"  ${permission.moduleid eq item.id ? 'selected' : ''} >${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">备注信息：</label>
                            <div class="col-xs-12 col-sm-6">
                                <textarea name="description" class="form-control" style="height:60px;">${permission.description}</textarea>
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