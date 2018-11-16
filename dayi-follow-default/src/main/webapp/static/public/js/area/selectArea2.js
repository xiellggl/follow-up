
/*!
 * 功能：地区联动
 * boxId:省市数据josn文件路径
 * prov:默认省份
 * city:默认城市
 * dist:默认地区（县）
 * nodata:无数据状态
 * required:必选项
 * distAll:全市范围选项
*/
var selectArea=function(options){
	var defaults={
		boxId:"areaBox",
		prov:null,
		city:null,
		dist:null,
		nodata:null,
		hasSuffix:true,
		fv:"id",
		required:true,
		distAll:false,
		callback:function(){}
	};
	var settings = $.extend({}, defaults, options);
	var box_obj=$("#"+settings.boxId);
	var prov_obj=box_obj.find(".prov");
	var city_obj=box_obj.find(".city");
	var dist_obj=box_obj.find(".dist");
	var title="name";
	var defText="请选择";
	if(typeof resumeLang === "string" && resumeLang=="en"){
		title="en";
		defText="select"
	}

	var dataCity=datajson.City;
	var dist=datajson.town;
	
	var suffix="";
	var suffixV="";
	
	if(settings.fv=="name"){
		settings.fv=title;
	}else{
		settings.fv="id";
	}
	
	var getSuffix=function(s){
		var suffix="";
		if(settings.hasSuffix){
			suffix=s;
			if(typeof resumeLang === "string"){
				suffix=resumeLang=="cn"?s:"";
			}
		}
		return suffix;
	};
	
	//获取地区全称，主要是兼容新旧数据处理
	var getAllName = function(v,arrSuff){
		var hasSuff=false;
		var allName=v;
		if(v==null || v==""){
			return "";
		}
		//判断是否有后缀
		/*for(var i in arrSuff){
			if(v.indexOf(arrSuff[i])!=-1){
				hasSuff=true;
				break;
			}
		}*/
		//无后缀时找出其后缀
		if(!hasSuff){
			for(var key in dataCity){
				if(dataCity[key].name==v){
					allName=v+dataCity[key].s;
					break;
				}
			}
		}
		return allName;
	};

	function defaultOption(tal){

	    if(settings.required){
            return ''
        }else{
            if(typeof resumeLang === "string" && resumeLang=="en"){
                title="en";
            }else{
                return '<option value="">'+(defText+tal)+'</option>'
            }
        }

    }
	
	var init=function(){
		// 遍历赋值省份下拉列表
		temp_html= defaultOption('省');
		for(k=0; k<dataCity.length; k++){
			if(dataCity[k].id.toString().substr(2,2) == "00" && dataCity[k].s!=""){
				temp_html+='<option value="'+dataCity[k][settings.fv]+'" data-id="'+dataCity[k].id+'">'+dataCity[k][title]+'</option>';
			}
		}
		prov_obj.html(temp_html);

        // 若有传入省份与市级的值，则选中。（setTimeout为兼容IE6而设置）
        t=setTimeout(function(){
            if(settings.prov!=null){
                prov_obj.val(settings.prov);
                cityStart();
                setTimeout(function(){
                    if(settings.city!=null){
                        var defCity=getAllName(settings.city,["市","地区","州"]);
                        city_obj.val(defCity);
                        distStart();
                        setTimeout(function(){
                            if(settings.dist!=null){
                                dist_obj.val(settings.dist);
                                settings.callback(box_obj);
                            }
                        },1);
                    }
                },1);
            }
        },1);
	
		// 选择省份时发生事件
		prov_obj.unbind("change",cityStart);
		prov_obj.bind("change",cityStart);

		// 选择市级时发生事件
		city_obj.unbind("change",distStart);
		city_obj.bind("change",distStart);

	};
	
	// 赋值市级函数
	var cityStart=function(){
		var prov_id=getSelectedAttr(prov_obj,"data-id") || 0;
		city_obj.empty().attr("disabled",true);
		dist_obj.empty().attr("disabled",true);
		if(prov_id <= 0){
			if(settings.nodata=="none"){
				city_obj.closest('span').css("display","none");
				dist_obj.closest('span').css("display","none");
			}else if(settings.nodata=="hidden"){
				city_obj.closest('span').css("visibility","hidden");
				dist_obj.closest('span').css("visibility","hidden");
            }
            return;
		}
		//common.log(prov_id)
		// 遍历赋值市级下拉列表
		temp_html=defaultOption('市');
		if( parseInt(prov_id.substr(0,4),10) >=4100 || parseInt(prov_id.substr(0,4),10) <=1300){
			for(var key in dataCity){
				if(dataCity[key].id ==prov_id && dataCity[key].s!=""){
					temp_html+='<option value="'+dataCity[key][settings.fv]+getSuffix(dataCity[key].s)+'" data-id="'+dataCity[key].id+'">'+dataCity[key][title]+getSuffix(dataCity[key].s)+'</option>';
				}
			}
		}else{
			var i=0;
			//for(var key in dataCity){
			for(k=0; k<dataCity.length; k++) {
				if(dataCity[k].id.toString().substr(0,2) == prov_id.toString().substr(0,2) && dataCity[k].id !=prov_id && dataCity[k].s!=""){
					temp_html+='<option value="'+dataCity[k][settings.fv]+getSuffix(dataCity[k].s)+'" data-id="'+dataCity[k].id+'">'+dataCity[k][title]+getSuffix(dataCity[k].s)+'</option>';
					i++;
				}
			}
			if(i==0){
				if(settings.nodata=="none"){
					city_obj.closest('span').css("display","none");
					dist_obj.closest('span').css("display","none");
				}else if(settings.nodata=="hidden"){
					city_obj.closest('span').css("visibility","hidden");
					dist_obj.closest('span').css("visibility","hidden");
				}
			}		
		}
		
		city_obj.html(temp_html).attr("disabled",false).closest('span').css({"display":"","visibility":""});
		distStart();
	};
	// 赋值地区（县）函数
	var distStart=function(){
		var prov_id=getSelectedAttr(prov_obj,"data-id");
		var city_id=getSelectedAttr(city_obj,"data-id");

		dist_obj.empty().attr("disabled",true);

		if(!prov_id>0||!city_id>0){
			if(settings.nodata=="none"){
				dist_obj.closest('span').css("display","none");
			}else if(settings.nodata=="hidden"){
				dist_obj.closest('span').css("visibility","hidden");
            }
            return;
        }
        // 遍历赋值市级下拉列表
		temp_html=defaultOption('区');
		var i=0;
		if(settings.distAll){
			temp_html = '<option value="'+city_id+'" data-id="'+city_id+'">全市范围</option>';
		}
		for(k=0; k<dist.length; k++) {
			if(dist[k].id.toString().substr(0,4) == city_id.toString().substr(0,4)){
				temp_html+='<option value="'+dist[k][settings.fv]+'" data-id="'+dist[k].id+'">'+dist[k][title]+'</option>';
				i++;
			}
		}
		if(i==0){
			if(settings.nodata=="none"){
				dist_obj.closest('span').css("display","none");
			}else if(settings.nodata=="hidden"){
				dist_obj.closest('span').css("visibility","hidden");
            }
            return;
		}
		dist_obj.html(temp_html).attr("disabled",false).closest('span').css({"display":"","visibility":""});
//		dist_obj.val(settings.dist);
	};
	init();
};
/*
 * 功能：获select下拉单的选中属性值
 * $oselect下拉单对象，attrName属性名称，可以是自定义的属性。
 */
getSelectedAttr = function($o,attrName){
	var id=null;
	$o.find('option').each(function(){
		if($(this).prop("selected")){
			id=$(this).attr(attrName);
		}
	});
	return id;
};

function getAreaItem(city){
	var resultList = [];
	var index = 0;
	$(datajson.City).each(function(i,el){
		if(el['s']=='市' &&
			( el['name'].indexOf(city) != -1 ||
			el['en'].indexOf(city) != -1 ||
			el['f'].indexOf(city) != -1 )){
			resultList[index] = el;
			index++;
		}
	});
	return resultList;
}