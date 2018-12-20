<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<form id="formEditRole" class="form-horizontal" autocomplete="off">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">新增角色</h4>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <input type="hidden" name="id" value="${role.id}" />

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">*角色名称：</label>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" value="${role.name}" class="form-control" required/>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">角色备注：</label>
                            <div class="col-xs-12 col-sm-6">
                                <textarea name="descript" class="${role.descript}" style="height:60px;"></textarea>
                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
                        </div>

                        <div class="space-4"></div>
                        <div class="form-group">
                            <style>
                                .conceal-tr{display: none;}
                            </style>
                            <label class="col-sm-2 control-label no-padding-right">权限控制：</label>
                            <div class="col-xs-12 col-sm-9">

                                <dl>
                                    <dd>
                                        <dl>
                                            <dt data-id="1">
                                                <label>
                                                    <input name="form-field-checkbox" type="checkbox" class="ace">
                                                    <span class="lbl">这是模块一</span>
                                                </label>
                                                <a class="btn btn-minier btn-success" data-ac="eye" data-id="1">展开</a>
                                            </dt>
                                            <dd class="last conceal-tr click1" style="">
                                                <label title="这是模块一的第一个功能">

                                                    <input name="form-field-checkbox" type="checkbox" class="ace">

                                                    <span class="lbl">这是模块一的第一个功能</span>
                                                </label>
                                                <label title="这是模块一的所有功能">

                                                    <input name="form-field-checkbox" type="checkbox" class="ace">

                                                    <span class="lbl">这是模块一的所有功能</span>
                                                </label>
                                            </dd>
                                        </dl>
                                    </dd>
                                </dl>

                            </div>
                            <div class="help-block col-xs-12 col-sm-reset inline tips_box"></div>
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
<script>
    //展开收起交互
    $('[data-ac="eye"]').on('click', function(event) {
        var me = this;
        var id = $(this).data('id');
        $(".click"+id).toggle(100);
        var curr = this.innerHTML;
        if(curr === '收起'){
            me.ip = 0;
        }else{
            me.ip = 1;
        }
        this.innerHTML = ['展开','收起'][me.ip];
    });
</script>
</body>
</html>