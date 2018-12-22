
                        <form class="form-horizontal row" id="formEdit">
                            <input type="hidden" name="agentId" value="${agentId}">
                            <div class="col-xs-12 col-sm-6">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right">联系方式： </label>
                                    <div class="col-xs-12 col-sm-6">
                                        <select name="contactType" class="width-100">
                                            <c:forEach items="${contactTypes}" var="item">
                                                <option value="${item.value}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right">客户类型： </label>
                                    <div class="col-xs-12 col-sm-6">
                                        <select name="customerType" class="width-100">
                                            <c:forEach items="${customerTypes}" var="item">
                                                <option value="${item.value > 1 ? item.value : ''}" ${item.value eq 1 ? 'disabled' : ''} <c:if test="${agentVo.customerType eq item.value}"> style="color:red;" selected</c:if>>${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right">客户意向度： </label>
                                    <div class="col-xs-12 col-sm-6">
                                        <select name="customerIntentionType" class="width-100">
                                            <c:forEach items="${customerIntentionTypes}" var="item">
                                                <option value="${item.value}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>

                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right"> 下次联系时间： </label>
                                    <div class="col-xs-12 col-sm-6">
                                        <input type="text" name="nextContactTime" class="width-100" placeholder="选填" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <label class="control-label">沟通内容： </label>
                                        <textarea type="text" name="content" value="" style="height: 90px;" class="width-100" placeholder="100个字以内" required></textarea>
                                    </div>
                                    <div class="help-block col-xs-12 inline tips_box"></div>
                                </div>
                                <div class="form-group" style="text-align: center;">
                                    <button type="submit" class="btn btn-primary">提交记录</button>
                                    <button class="btn btn-info" type="reset">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <h4 class="header smaller lighter blue">
                            <i class="ace-icon fa fa-book"></i>
                            联系记录（客户：${agentVo.linkPersonStr}）
                        </h4>
                        <table class="table table-striped table-bordered table-hover" id="list">
                            <thead>
                            <tr>
                                <th>联系时间</th>
                                <th>联系人</th>
                                <th>联系方式</th>
                                <th>客户意向度</th>
                                <th>下次联系时间</th>
                                <th>客户类型</th>
                                <th>沟通内容</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.items}">
                                <tr>
                                    <td colspan="7" class="no_data">暂无数据</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.items}">
                                <c:forEach items="${page.items}" var="item" >
                                    <tr>
                                        <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${item.followUpName}</td>
                                        <td>${item.contactTypeStr}</td>
                                        <td>${item.customerIntentionTypeStr}</td>
                                        <td><fmt:formatDate value="${item.nextContactTime}" pattern="yyyy-MM-dd"/></td>
                                        <td>${item.customerTypeStr}</td>
                                        <td>${item.content}</td>
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
<%@include file="/inc/followup/script.jsp"%>
<script type="text/html" id="tplContactItem">
    <tr>
        <td>{{createDate | dateFormat:'yyyy-MM-dd hh:mm:ss'}}</td>
        <td>{{followUpName}}</td>
        <td>{{contactTypeStr}}</td>
        <td>{{customerIntentionTypeStr}}</td>
        <td>{{nextContactTime | dateFormat:'yyyy-MM-dd'}}</td>
        <td>{{customerTypeStr}}</td>
        <td>{{content}}</td>
    </tr>
</script>
<script charset="UTF-8" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "template", "validate", "addMethod","daterangepicker"], function (common, template) {
        common.head("myCustomer",1);
        var $formEdit = $("#formEdit");
        var $list = $("#list");

        var date_o = {
            singleDatePicker:true,
            autoUpdateInput: false,
            //timePicker24Hour : true,//设置小时为24小时制 默认false
            //timePicker : true,//可选中时分 默认false
            locale: locale_cn,
        };
        date_o.locale.cancelLabel = "清空";
        var $nextContactTime = $formEdit.find('[name="nextContactTime"]');
        $nextContactTime.daterangepicker(date_o).on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD'));
        }).on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });

        template.helper("dateFormat",common.dateFormat);

        $formEdit.validate({
            rules: {
                contactType: "required",
                customerType: "required",
                customerIntentionType: "required",
                content:{
                    required:true,
                    maxlength:100
                }
            },
            messages: {
                customerType: "客户类型有误",
                content:{
                    required:"请填写沟通内容",
                    maxlength:"100个字以内"
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
                    url: "/followup/uc/customer/agent/contact/add.json",
                    data: $formEdit.serialize(),
                    succback:function (data) {
                        $formEdit[0].reset();
                        common.successMsg("添加成功");
                        var html = template("tplContactItem", data.item);
                        if($list.find(".no_data").length){
                            $list.find("tbody").html(html);
                        }else {
                            $list.find("tbody").prepend(html);
                        }
                    }
                });
                return false;
            }
        });
    });
</script>
</body>
</html>