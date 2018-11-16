var bankselect={};
bankselect.bankUrlJsonList=[
    {id: "1", name: "工商银行", site: "http://www.icbc.com.cn/icbc/"},
    {id: "3", name: "中国银行", site: "http://www.boc.cn/"},
    {id: "4", name: "建设银行", site: "http://www.ccb.com/cn/home/indexv3.html"},
    {id: "2", name: "农业银行", site: "http://www.abchina.com/cn/"},
    {id: "8", name: "交通银行", site: "http://www.bankcomm.com/BankCommSite/default.shtml"},
    {id: "9", name: "邮储银行", site: "http://www.psbc.com/cn/index.html"},
    {id: "16", name: "招商银行", site: "http://www.cmbchina.com/"},
    {id: "15", name: "平安银行", site: "http://one.pingan.com/"},
    {id: "13", name: "民生银行", site: "http://www.cmbc.com.cn/"},
    {id: "11", name: "光大银行", site: "http://www.cebbank.com/"},
    {id: "12", name: "华夏银行", site: "http://www.hxb.com.cn/home/cn/"},
    {id: "10", name: "中信银行", site: "http://bank.ecitic.com/"},
    {id: "18", name: "浦东发展银行", site: "http://www.spdb.com.cn/"},
    {id: "14", name: "广发银行", site: "http://www.cgbchina.com.cn/"},
    {id: "17", name: "兴业银行", site: "http://www.cib.com.cn/cn/index.html"},
    {id: "19", name: "东莞银行", site: "http://www.dongguanbank.cn/Site/Home/CN"},
    {id:"26",name:"东莞农村商业银行",site:"http://www.drcbank.com/"}
];

/**
 * 获取银行信息
 * @param id
 * @returns {*}
 */
bankselect.getBankInfo=function (id) {
    var list=this.bankUrlJsonList;
    var len = list.length;
    for (var i = 0; i < len; i++) {
        if (id == list[i].id) {
            return list[i];
        }
    }
    return null;
};




