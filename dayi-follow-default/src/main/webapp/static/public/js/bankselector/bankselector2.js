//银行选择器
var bankselector={};
bankselector.list=[
    {id:"1",name:"工商银行",site:"http://www.icbc.com.cn/icbc/"},
    {id:"2",name:"农业银行",site:"http://www.abchina.com/cn/"},
    {id:"3",name:"中国银行",site:"http://www.boc.cn/"},
    {id:"4",name:"建设银行",site:"http://www.ccb.com/cn/home/indexv3.html"},
    {id:"8",name:"交通银行",site:"http://www.bankcomm.com/BankCommSite/default.shtml"},
    {id:"9",name:"邮储银行",site:"http://www.psbc.com/cn/index.html"},
    {id:"10",name:"中信银行",site:"http://bank.ecitic.com/"},
    {id:"11",name:"光大银行",site:"http://www.cebbank.com/"},
    {id:"12",name:"华夏银行",site:"http://www.hxb.com.cn/home/cn/"},
    {id:"13",name:"民生银行",site:"http://www.cmbc.com.cn/"},
    {id:"14",name:"广发银行",site:"http://www.cgbchina.com.cn/"},
    {id:"15",name:"平安银行",site:"http://one.pingan.com/"},
    {id:"16",name:"招商银行",site:"http://www.cmbchina.com/"},
    {id:"17",name:"兴业银行",site:"http://www.cib.com.cn/cn/index.html"},
    {id:"18",name:"浦东发展银行",site:"http://www.spdb.com.cn/"},
    {id:"19",name:"东莞银行",site:"http://www.dongguanbank.cn/Site/Home/CN"},
    {id:"26",name:"东莞农村商业银行",site:"http://www.drcbank.com/"}
];
bankselector.getBankInfo=function (id) {
    var list=bankselector.list;
    var len=list.length;
    for(var i=0;i<len;i++){
        if(id==list[i].id){
            return list[i];
        }
    }
    return null;
};
bankselector.box=function(callback,bankId){
    var bankData=null,provinceData=null;
    $.ajax({
        url: "/api/bank/get/bank/all.json",
        dataType: "json",
        async:false,
        success: function (data) {
            bankData=data;
        }
    });
    $.ajax({
        url: "/api/bank/get/province/all.json",
        dataType: "json",
        async:false,
        success: function (data) {
            provinceData=data;
        }
    });
    if(bankData==null || provinceData==null){
        layer.alert('<p style="text-align:center;">数据获取出错</p>',"温馨提示");
        return;
    }

    var boxHtml='<form class="bank_select" onsubmit="return false">'
        +'<input type="hidden" name="bankId" value="'+ bankId +'">'
        +'<input type="hidden" name="bankCodeId" value="">'
        +'<table class="form_table box_noborder">'
        +'</tr>'
        +'<tr>'
        +'<td id="selectCity">'
        +'<select name="prov" class="inptStyle">'
        +'<option value="">请选择省</option>'
        +'</select>'
        +'<select name="areaId" class="inptStyle" style="margin-left:10px;">'
        +'<option value="">请选择市</option>'
        +'</select>'
        +'</td>'
        +'</tr>'
        +'<tr>'
        +'<td>'
        +'<input type="text" name="bankName" class="inptStyle" placeholder="输入支行关键字" style="margin-right:10px;" />'
        +'<button type="button" class="btn_style1" data-act="seacher">查询</button>'
        +'<p class="gray fz12">输入支行关键字查询，并在查询结果列表中选择支行</p>'
        +'</td>'
        +'</tr>'
        +'</table>'
        +'</form>'
        +'<div data-rel="branchList" class="branchList"></div>';

    var getCity=function (province) {
        var cityData=null;
        $.ajax({
            url: "/api/bank/get/area/all.json",
            dataType: "json",
            data:{province:province},
            async:false,
            success: function (data) {
                cityData=data;
            }
        });
        if(cityData==null){
            layer.alert('<p style="text-align:center;">数据获取出错</p>',"温馨提示");
        }
        return cityData;
    };
    layer.open({
        type:1,
        title:"银行卡支行信息",
        area:["444px","500px"],
        content:boxHtml,
        btn:['提交'],
        yes:function(index,$box){
            var bankCodeId = $box.find('[name=bankCodeId]').val();
            if(bankCodeId === ""){
                layer.msg('<p style="text-align:center;">你还没选择支行</p>',"温馨提示");
            }else{
                callback(bankCodeId);
            }
        },
        success:function ($box,index) {
            var searchType=0;
            var $prov=$box.find('[name="prov"]');
            var $city=$box.find('[name="areaId"]');
            var $bankSelect=$box.find('[name="bankId"]');
            var $branchInput=$box.find('[name="bankName"]');
            var $btnSearch=$box.find('[data-act="seacher"]');
            var $branchList=$box.find('[data-rel="branchList"]');
            //银行下拉列表
            var bankOption='';
            var bankList=bankselector.list;
            var bankLen=bankList.length;
            for(var i=0;i<bankLen;i++){
                if((!!bankselector.finance) && bankList[i].id==26){//用于去掉东莞农商银行
                    continue;
                }
                bankOption +='<option value="'+bankList[i].id+'">'+bankList[i].name+'</option>';
            }
            $bankSelect.append(bankOption);

            //省份下拉列表
            var provOption='';
            var provList=provinceData.items;
            var provLen=provList.length;
            for(var i=0;i<provLen;i++){
                provOption +='<option value="'+provList[i].province+'">'+provList[i].province+'</option>';
            }
            $prov.append(provOption);

            var checkSelected=function ($sel,tips,$this) {
                if($sel.val()==""){
                    $this.val("");
                    layer.tips(tips,$sel);
                    $sel.focus();
                    return false;
                }
                return true
            };

            //选择银行
            $bankSelect.on("change",function () {
                if($city.val()!=""){
                    $city.change();
                }
            });

            //选择省时提示【请先选择银行】
            $prov.on("change",function () {
                var html='<option value="">请选择市</option>';
                var prov=$(this).val();
                if(prov==""){
                    $city.html(html);
                    return;
                }
                var cityData=getCity(prov);
                if(cityData==null){
                    return;
                }
                var list=cityData.items;
                var len=list.length;
                var html='<option value="">请选择市</option>';
                for(var i=0;i<len;i++){
                    html +='<option value="'+list[i].id+'">'+list[i].city+'</option>';
                }
                $city.html(html);
            });
            $city.on("change",function () {
                if($city.val()!=""){
                    //执行搜索
                    searchType=1;
                    $btnSearch.click();
                }
            });
            $branchInput.on("keydown",function () {
                checkSelected($city,"请先选择城市",$(this));
            });

            // 關鍵字搜索 綁定回车查询
            $branchInput.keyup(function(e){
                if(e.keyCode === 13){
                    $btnSearch.click();
                }
            });

            //清空支行搜索框及搜索数据
            $bankSelect.on("change",function () {
                if($(this).val()==""){
                    $branchInput.val('');
                }
            });

            $btnSearch.on("click",function () {
                if(searchType==0 && $branchInput.val()=="" && $city.val()==""){
                    layer.tips("输入支行关键字",$branchInput);
                    return false;
                }

                var form = $box.find("form");

                $.ajax({
                    url: "/api/bank/get/bankCode/all.json",
                    dataType: "json",
                    data:form.serialize(),
                    async:false,
                    success: function (data) {
                        if(data.fail){
                            layer.alert('<p style="text-align:center;color:red;">'+data.msg+'</p>',"温馨提示");
                        }
                        var bankId=$bankSelect.val();
                        var bank=$bankSelect.find(':selected').text();
                        var list=data.items;
                        var len=list.length;
                        var prov = $prov.find(":selected").html();
                        var city = $city.find(":selected").html();
                        var html='<ul style="padding:0 20px;overflow: auto;height:200px;">';
                        for(var i=0;i<len;i++){
                            html +='<li><label><input type="radio" name="branch" value="'+list[i].number+'" data-name="'+list[i].name+'" data-bankid="'+bankId+'" data-bank="'+bank+'"  data-prov="'+prov+'" data-city="'+city+'" data-id="'+list[i].id+'"/> '+list[i].name+'</label></li>';
                        }
                        html +='</ul>';
                        $branchList.html(html);
                        $branchList.find('[name="branch"]').on("click",function () {
                            form.find('[name=bankCodeId]').val($(this).data('id'));
                        });
                    }
                });
                searchType=0;
            });
        }
    });
};
