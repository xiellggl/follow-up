/**
 * 地区选择器
 * @version 1.1.0
 * @author <yangjian102621@gmail.com>
 *
 */
(function(factory) {
	if(typeof define === 'function'&& typeof seajs=="object"){//修复跟echartjs的冲突
		define(factory);
	}else{
		factory();
	}
})(function(require){

	$.fn.JArea = function(options) {

		var obj = {};
		var $container = $(this);
		var areaData;
		if(typeof define === 'function'&& typeof seajs=="object"){//修复跟echartjs的冲突
			areaData = require("./JAreaData").__AREADATA__;  //地区数据
		}else{
			areaData = __AREADATA__;  //地区数据
		}

		//初始化参数
		var defaults = {
			prov : 0, //省
			city : 0, //市
			dist : 0, //区
			name : {
				prov : 'prov',
				city : 'city',
				dist : 'dist',
				detail: 'detail',
				areaJson: 'areaJson'
			},
			defaults:true, //是否设置默认项 -请选择
			wrap:'<span class="css-select">',//select 包裹元素
			selectClassName : 'form-control', //select class名称
			detailClassName : 'detail',  //详细地址
			areaJsonClassName : 'areaJson'  //地址汉字的json字符串

		};

		/* 合并参数 */
		options = $.extend(defaults, options);

		//创建元素
		obj.create = function() {

			obj.province = obj.setDefault(1);
			obj.hideHtml = $('<input type="hidden" name="'+options.name.detail+'" class="'+options.detailClassName+'">');/*详细名称*/
			obj.areaJson = $('<input type="hidden" name="'+options.name.areaJson+'" class="'+options.areaJsonClassName+'">');/*地址汉字的json字符串*/

			//加载所有省级
			$.each(areaData.prov, function(id, name) {
				if ( id == options.prov ) {
					obj.province.append('<option value="'+id+'" selected>'+name+'</option>');
				} else {
					obj.province.append('<option value="'+id+'">'+name+'</option>');
				}
			});

			//绑定选中省级事件
			obj.province.on('change', function() {

				//删除元素
				try {
					obj.city.remove();
					obj.dist.remove();
					obj.city=null;
					obj.dist=null;
				} catch (e) {}

				var pid = $(this).val(); //获取省份id

				if ( areaData.city[pid] && areaData.city[pid].length > 0 ) {

					obj.city = obj.setDefault(2);
					$.each(areaData.city[pid], function(i, item) {
						if ( item.id == options.city ) {
							obj.city.append('<option value="'+item.id+'" selected>'+item.name+'</option>');
						} else {
							obj.city.append('<option value="'+item.id+'">'+item.name+'</option>');
						}
					});

					//切换城市的时候加载地区
					obj.city.on("change", function() {

						try {
							obj.dist.remove();
							obj.dist=null;}
						catch (e) {}
						//console.log(obj.getAreaString());

						var cid = $(this).val();
						if ( areaData.dist[cid] && areaData.dist[cid].length > 0 ) {
							obj.dist = obj.setDefault(3);
							$.each(areaData.dist[cid], function(i, item) {
								if ( item.id == options.dist ) {
									obj.dist.append('<option value="'+item.id+'" selected>'+item.name+'</option>');
								} else {
									obj.dist.append('<option value="'+item.id+'">'+item.name+'</option>');
								}
							});
							obj.insertObj(obj.dist);
							obj.dist.on("change",function(){
									obj.hideHtml.val(obj.getAreaString());//具体汉字塞到隐藏域
									obj.areaJson.val(obj.getAreaJson());//具体汉字json塞到隐藏域
							}).change();
						}else{
							obj.hideHtml.val(obj.getAreaString());//具体汉字塞到隐藏域
							obj.areaJson.val(obj.getAreaJson());//具体汉字json塞到隐藏域
						}
					});
					obj.insertObj(obj.city);
					obj.city.trigger("change"); //自动触发事件

					$container.append(obj.hideHtml);
					$container.append(obj.areaJson);
				}
				else {
					obj.hideHtml.val(obj.getAreaString());//具体汉字塞到隐藏域
					obj.areaJson.val(obj.getAreaJson());//具体汉字json塞到隐藏域
				}

			});
			obj.insertObj(obj.province);
			obj.province.trigger("change"); //自动触发事件

            // 特殊显示 add in 20170623 by Pg
            options.city === -1 && obj.insertObj(obj.city = obj.setDefault(2).attr('disabled','disabled'));
            options.dist === -1 && obj.insertObj(obj.dist = obj.setDefault(3).attr('disabled','disabled'));
		};

		//获取区域id
		obj.getAreaId = function() {
			return {
				prov : obj.province.val(),
				city : obj.city ? obj.city.val() : 0,
				dist : obj.dist ? obj.dist.val() : 0
			};
		};

		//插入元素
		obj.insertObj = function(o){
			var wrap =o;
			if(options.wrap!="") {
				wrap = $(options.wrap).append(o);
				//wrap = o.wrap('<span></span>');


				//console.log(wrap);

			}
			$container.append(wrap);
		};
		//设置默认值
		obj.setDefault = function(type){
			switch(type){
				case 1 :
					if(options.defaults){
						return $('<select class="'+options.selectClassName+'" name="'+options.name.prov+'"><option value="">请选择省</option></select>');
					}else {
						return $('<select class="'+options.selectClassName+'" name="'+options.name.prov+'"></select>');
					}
					break;
				case 2 :
					if(options.defaults) {
						return $('<select class="' + options.selectClassName + '" name="' + options.name.city + '"><option value="">请选择市</option></select>');
					}else {
						return $('<select class="' + options.selectClassName + '" name="' + options.name.city + '"</select>');
					}
					break;
				case 3 :
					if(options.defaults) {
						return $('<select class="' + options.selectClassName + '" name="' + options.name.dist + '"><option value="">请选择区</option></select>');
					}else {
						return $('<select class="' + options.selectClassName + '" name="' + options.name.dist + '"></select>');
					}
					break;
				default :
					return $('<select class="'+options.selectClassName+'" name="'+options.name.prov+'"><option value="">请选择省</option></select>');
			}
		};

		//获取区域字符串
		obj.getAreaString = function() {
			var html="";
			if(obj.province.val()!="") {
				html = obj.province.find("option:selected").html();
				try {
					if (obj.city.val() != "") {
						html += obj.city.find("option:selected").html();
					}
					if (obj.dist.val() != "") {
						html += obj.dist.find("option:selected").html();
					}
				} catch (e) {
				}
			}
			return html;
		};
		//获取区域json字符串
		obj.getAreaJson = function() {
			var prov ="", city="",dist="";
			if(obj.province.val()!=""){
				prov = obj.province.find("option:selected").html();
			try {
				if(obj.city.val()!=""){
					city = obj.city.find("option:selected").html();
				}
				if(obj.dist.val()!=""){
					dist = obj.dist.find("option:selected").html();
				}
			} catch (e) {}
			}
			var json = {prov:prov,city:city,dist:dist};
			json = JSON.stringify(json);
			return json;
		};
		obj.create();
		return obj;

	};
});



