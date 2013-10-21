

(function($) { 
	
	$.fn.comboboxC = function(options,dataKeys) {
		
		var combox={
				option:{},
				//id,name,data,level,parentid
				dataKey:[],
				tempNode:{},
				createNextLevel:function (node)
				{
					if(node.level>combox.option.maxlevel)
						return;
					
					if(node.data!=undefined && node.data.length>0){
						combox.create(node,node.data);
					}
					else{
						
						if(combox.option.api!=""){
							var option={};
							//{"version":1,"pid":"449716030001","sellerCode":"","level":3}
							option.version=1;
							option.pid=node.searchid;
							option.level=node.level;
							option.sellerCode="";
							
							combox.tempNode=node;
							
							zapjs.zw.api_call(combox.option.api,option,combox.apiCallBack);
						}
						else{
							$.getJSON(zapjs.f.urlreplace(combox.option.url,combox.option.parentReplaceName,node.searchid), function(json){
								
								var size = json.length;
								
								var ary=[];
								for(var i=0;i<size;i++){
									var item={};
									item.id=json[i][0];
									item.text=json[i][1];
									ary.push(item);
								}
								
								combox.create(node,ary);
							});
						}
						
						
					}
				},
				apiCallBack:function(json){
					if(json.resultCode == 1){
						
						var size = json.list.length;
						
						var ary=[];
						for(var i=0;i<size;i++){
							var item={};
							item.id=json.list[i].categoryCode;
							item.text=json.list[i].categoryName;
							ary.push(item);
						}
						
						combox.create(combox.tempNode,ary);
					}
					else{
						alert(json.resultMessage);
					}
					
					
				},
				create:function(node,json){
					
					var selectHtml="";
					//$("#"+node.parentid).next().after()
					
					selectHtml+="<select id=\""+node.id+"\" size=10 >";
					if(json.length >0)
					{
						for(var i=0;i<json.length;i++){
							selectHtml+="<option  value=\""
							+ json[i].id + "\"";
							
							if(json[i].id == node.currentSelectValue)
								selectHtml+=" selected " ; 
							
							selectHtml+=">"
							+ json[i].text+"</option>";
						}
					}
					selectHtml+="</select>";
					if(node.parentid == "")
						$("#"+combox.option.selectValueName).after(selectHtml);
					else
						$("#"+node.parentid).after(selectHtml);
					
					$("#"+node.id).change(combox.valueSelect);
					$("#"+node.id).width(combox.option.width);
					$("#"+node.id).height(combox.option.height);
					
					if(combox.option.classid!="")
						$("#"+node.id).addClass(combox.option.classid);
					
				},
				valueSelectForId:function (currentId,currentValue){
					
					$.each(combox.dataKey,function(i,n){
						// 找到当前的Node
						if(n.id == currentId)
						{
							if(n.level == combox.option.maxlevel){
								$("#"+combox.option.selectValueName).val(currentValue);
								
							}
							else{
								$("#"+combox.option.selectValueName).val("");
							}
							
							n.currentSelectValue=currentValue;
							
							if(combox.option.ismultilevel){
								//清空当前级别之后的所有数据
								$.each(combox.dataKey,function(j,k){
									if(k.level>n.level)
									{
										//$("#"+k.id).prev.remove();
										$("#"+k.id).remove();
										k.data=[];
									}
								});
							}
						
							if(n.level<combox.option.maxlevel){
								//如果是多级的，创建子节点
								if(combox.option.ismultilevel){
									var exist = false;
									var node = "";
									$.each(combox.dataKey,function(j,k){
										if(k.level==(n.level+1)){
											exist = true;
											node= k;
										}
									});
									
									//如果以前没有创建过
									if(!exist){
										var levelone  = parseInt(n.level)+1;
										var name = n.name+levelone;
										var id =  n.id+levelone;
										combox.dataKey.push({"id":id,"name":name,"level":levelone,"parentid":n.id,"searchid":currentValue,"currentSelectValue":""});
										
										var currentNode = combox.dataKey[combox.dataKey.length-1];
										currentNode.currentSelectValue="";
										combox.createNextLevel(currentNode);
									}
									else{
										node.searchid = currentValue;
										node.currentSelectValue = "";
										combox.createNextLevel(node);
									}
								}	
							}
							if(combox.option.onchange!=undefined && combox.option.onchange!="")
								combox.option.onchange(n,combox.dataKey);
						}					
					});			
				},
				valueSelect:function (){
			    	var currentId = this.id;
			    	combox.valueSelectForId(currentId,$("#"+currentId).val());
			    }
		}
		combox.option = $.extend({}, $.fn.comboboxC.defaults, options); 
		combox.dataKey= dataKeys;
		
		$.ajaxSetup({
			  async: false
		});
		$.each(combox.dataKey,function(i,n){
			combox.createNextLevel(n);
		})
	};
	
	/*
	* type 类型 1 查询，2 添加 3 修改
	* ismultilevel 是否存在多级  true ，false
	* mustbelast 必须选择到最后一级 
	*/
	// 插件的defaults    
	$.fn.comboboxC.defaults = {    
			"type": 1,    //类型 1 查询，2 添加 3 修改
			"ismultilevel": true,//是否存在多级  true ，false
			//"iscaninput":true,//是否能输入 ， true，false 
			"selectValueName":"Key",//当前控件的hidden 的默认值,如果多控件，此值必须唯一
			"maxlevel":3,//最大深度
			"width":200,//宽度
			"height":200,//高度
			"classid":"",
			"onchange":function(node,datakeys){},
			"url":"",
			"api":""
	}; 
	$.fn.comboboxC.dataKey = {
			"id":"dd",//当前的Id
			"name":"deptdd",//当前的 名字
			"level":1,//当前的级别
			"currentSelectValue":"",//当前选中的值
			"parentid":"",//当前的父 ID
			"searchid":""//
	}; 
// 闭包结束  
})(jQuery);    








