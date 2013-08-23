

(function($) { 
	
	$.fn.comboboxN = function(options,dataKeys) {
		
		var combox={
				option:{},
				//id,name,data,level,parentid
				dataKey:[],
				createNextLevel:function (node)
				{
					if(node.data!=undefined && node.data.length>0){
						combox.create(node,node.data);
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
					
				},
				create:function(node,json){
					if(json.length >0)
					{
						if(node.parentid!="")
							$("#"+node.parentid).next().after("<input id='"+node.id+"' name='"+node.name+"' value='' >");
						
						var dataForSearch=[];
						dataForSearch.push({"id":"",text:combox.option.firstViewText});
						$.each(json,function(i,n){
							if(node.currentSelectValue == n.id)
								n.selected=true;
							dataForSearch.push(n);
						});
						
			    		$("#"+node.id).combobox({
				   	        data:dataForSearch,
				   	        valueField:'id',
				   	        textField:'text',
				   	        onSelect:combox.valueSelect//选中的时候触发
				   	    });
			    		
						//如果不能输入,直接把 keypress keydown keyup 等事件 处理掉。 
						if(!combox.option.iscaninput)
							$($('#'+node.id).next().children()[0]).keypress(function(){return false;}).keydown(function(){return false;}).keyup(function(){return false;})
						else{
					      	//如果可以输入的话 
				      		$($('#'+node.id).next().children()[0]).blur(function () 
				      		{ 
				      			//取得当前的值 ,当前变化的 id，如果在 数据表中不存在，则为当前的值 
				      			var valueC = $("input[name='"+node.name+"']").val();
				      			//$("#hhhh").html(valueC);
				    			var exist = false;
				    			$.each(dataForSearch,function(i,n){
				    				if(valueC == n.id)
				    					exist = true;
				    			});
				    			
				    			//如果没有找到，则赋值。 
				    			if(!exist){
				    				//把当前的select的 InputValue 设置为 空 
				    				$("input[name='"+node.name+"']").val("");
				    				//恢复默认值 ，如 为 请选择，或者 全部 
				    				$(this).val(combox.option.firstViewText);
				    			}
				    			else{
				    				//如果存在同时 为空  恢复默认值 ，如 为 请选择，或者 全部 
				    				if(valueC == "")
				    					$(this).val(combox.option.firstViewText);
				    			}
				    			//触发  选中事件 ,传入当前的 Id
				    			combox.valueSelectForId(node.id);
				          	});
						}
					}
					else{
						if(combox.option.mustbelast){
							$("#"+combox.option.selectValueName).val($("input[name='"+combox.dataKey[combox.dataKey.length-2].name+"']").val())
						}
					}
				},
				valueSelectForId:function (currentId){
					
					/*var tempDataKey = [];
					
					$.each(combox.dataKey,function(i,n){
						tempDataKey.push(n);
					})*/
					
					$.each(combox.dataKey,function(i,n){
						// 找到当前的Node
						if(n.id == currentId)
						{
							var currentValue = $("input[name='"+n.name+"']").val();
							//把当前选中的值存入临时 变量 
							if($("input[name='"+n.name+"']").val() == ""){
								n.currentSelectValue="";
							}
							else{
								if(n.currentSelectValue == $("input[name='"+n.name+"']").val())	
									return;
								n.currentSelectValue = $("input[name='"+n.name+"']").val();
							}
							
							
							if(combox.option.ismultilevel){
								//清空当前级别之后的所有数据
								$.each(combox.dataKey,function(j,k){
									if(k.level>n.level)
									{
										$("#"+k.id).next().remove();
										$("#"+k.id).remove();
										k.data=[];
									}
								})
								//如果必须选中的话，那么在创建的时候赋值，有孩子的话，为空值，否则为 当前选中的值，赋值在 创建函数里面
								if(combox.option.mustbelast){
									$("#"+combox.option.selectValueName).val("");
									$("#"+combox.option.secondSelectValueName).val(currentValue==""?n.searchid:currentValue);
								}
								else{
									if(n.level ==1){
										$("#"+combox.option.selectValueName).val(currentValue);
										$("#"+combox.option.secondSelectValueName).val(currentValue);
									}
									else{
										$("#"+combox.option.selectValueName).val(currentValue==""?n.searchid:currentValue);
										$("#"+combox.option.secondSelectValueName).val(currentValue==""?n.searchid:currentValue);
									}
								}
							}
							else{
								
								if(combox.option.mustbelast){
									$("#"+combox.option.selectValueName).val($("input[name='"+n.name+"']").val());
									$("#"+combox.option.secondSelectValueName).val($("input[name='"+n.name+"']").val());
								}
								else{
									$("#"+combox.option.selectValueName).val($("input[name='"+n.name+"']").val());
									$("#"+combox.option.secondSelectValueName).val($("input[name='"+n.name+"']").val());
								}
							}
							
							//如果当前选中的是非空节点
							if(currentValue != ""){
								//如果是多级的，创建子节点
								if(combox.option.ismultilevel){
									var exist = false;
									var node = "";
									$.each(combox.dataKey,function(j,k){
										if(k.level==(n.level+1)){
											exist = true;
											node= k;
										}
									})
									
									//如果以前没有创建过
									if(!exist){
										var levelone  = parseInt(n.level)+1;
										var name = n.name+levelone;
										var id =  n.id+levelone;
										combox.dataKey.push({"id":id,"name":name,"level":levelone,"parentid":n.id,"searchid":$("input[name='"+n.name+"']").val(),"currentSelectValue":""});
										
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
						}
					})
					$("#hhh").html("====="+$("#"+combox.option.selectValueName).val()+"=====");
					
				},
				valueSelect:function (){
			    	var currentId = this.id;
			    	combox.valueSelectForId(currentId);
			    }
		}
		
		
		combox.option = $.extend({}, $.fn.comboboxN.defaults, options); 
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
	* iscaninput 是否能输入 ， true，false 
	* firstViewText 如果查询，则为 全部，否则，请为 请选择  
	* mustbelast 必须选择到最后一级 
	*/
	// 插件的defaults    
	$.fn.comboboxN.defaults = {    
			"type": 1,    //类型 1 查询，2 添加 3 修改
			"ismultilevel": true,//是否存在多级  true ，false
			"iscaninput":true,//是否能输入 ， true，false 
			"firstViewText":"全部",//果查询，则为 全部，否则，请为 请选择  
			"mustbelast":true,//必须选择到最后一级 
			"selectValueName":"Key",//当前控件的hidden 的默认值,如果多控件，此值必须唯一
			"url":""
	}; 
	$.fn.comboboxN.dataKey = {
			"id":"dd",//当前的Id
			"name":"deptdd",//当前的 名字
			"level":1,//当前的级别
			"currentSelectValue":"",//当前选中的值
			"parentid":"",//当前的父 ID
			"searchid":""//
	}; 
// 闭包结束  
})(jQuery);    












