
var zapadmin_apitree = {
	temp : {
		step : 4,
		data : []
	},

	tree_init : function(oElm) {

		zapadmin_apitree.temp.step = $('#zw_page_tree_zw_s_step').val();




		zapjs.zw.api_call('com_srnpr_zapweb_webapi_ListApi','', function(result) {

			zapadmin_apitree.temp.data = result.resultObject;

			zapadmin_apitree.tree_show(zapadmin_apitree.temp.data);
		});

	},

	tree_window : function(sElm) {

		var sData = zapjs.zw.upExtend(sElm, 'data');

		$.get(sData, function(result) {
			
			var x = zapadmin.tree_data(result,$('#'+sElm).val());
			$('#' + sElm + '_tree_ul').tree({
				data : x,
				checkbox : true,
				lines : true

			});
		});

	},
	init_window : function(sElm) {

		//$('#'+sElm).after('<span id="'+sElm+'_span_show"></span>');
		
	},

	tree_select : function(sElm) {

		var nodes = $('#' + sElm + '_tree_ul').tree('getChecked');
		var s = '';
		
		var aLi=[];
		
		for ( var i = 0; i < nodes.length; i++) {
	
			if (!nodes[i].target.nextSibling) {
				if (s != '')
					s += ',';
				s += nodes[i].id;
				aLi.push(nodes[i].text);
			}
		}
		
		$('#'+sElm).val(s);
	
		$('#'+sElm).nextAll('span').html(aLi.join(','));
	

		zapadmin.window_close();
	},

	tree_show : function(oData) {
				var x = zapadmin.tree_data(oData);

				$('#zw_page_common_tree').tree({
					data : x,
					onClick : function(node) {
						
						zapadmin_apitest.click_func(node);
					}
				});
			},
	
	tree_nodeup : function(oTag) {
		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {
			var k  = 0;
			var oData = zapadmin_apitree.temp.data;
			var nodeData = [];
			for ( var i = 0, j = oData.length; i < j; i++) {
				if(node.id==oData[i][0]){
					nodeData = oData[i];
				}
			}
			for(var i = 0,j=oData.length;i<j;i++){
				var nlen = nodeData[4].length;
				var olen = oData[i][4].length;
				if (olen==nlen && nodeData[2]==oData[i][2] &&parseInt(nodeData[4].substr(nlen-4,nlen))>parseInt(oData[i][4].substr(olen-4,olen))) {
					k++;
				}
			}
			if(k>0){
				zapadmin_apitree.tree_forSortUp(oTag,nodeData,oData);
			}else{
				zapadmin.model_message('不能进行向上移动！');
			}
		} else {
			zapadmin.model_message('请选择后在进行操作！');
		}
	},
	
	tree_forSortUp : function(oTag,node,nodeAll){
		var length = node[4].length;
		var downNodeId = "";
		for(var i=1;i<10000;i++){
			var flag = false;
			var id = (parseInt(node[4].substr(length-4,length))-i).toString();
			for ( var l = 0, m = (4 - id.length); l < m; l++) {
				id = "0" + id;
			}
			var ai = node[4].substr(0,length-4)+id;
			for ( var k = 0, j = nodeAll.length; k < j; k++) {
				if(nodeAll[k][4]!=null&&ai==nodeAll[k][4]){
					downNodeId = ai;
					flag = true;
					break;
				}
			}
			if(flag){break;}
		}
		for ( var i = 0, j = nodeAll.length; i < j; i++) {
			//节点向上(本节点和孩子-1)
			if(node[4]==nodeAll[i][4] || nodeAll[i][4].substr(0,length)==node[4]){
				var sort = downNodeId+nodeAll[i][4].substr(length,nodeAll[i][4].length);
				zapjs.zw.func_do(oTag, null, {zw_f_uid:nodeAll[i][3],zw_f_sort:sort});
			//节点向下(上节点+1)
			}else if(downNodeId==nodeAll[i][4] || nodeAll[i][4].substr(0,length)==downNodeId){
				var sort = node[4]+nodeAll[i][4].substr(length,nodeAll[i][4].length);
				zapjs.zw.func_do(oTag, null, {zw_f_uid:nodeAll[i][3],zw_f_sort:sort});	
			}
		}
	},
	
	tree_nodedown : function(oTag) {
		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {
			var k  = 0;
			var oData = zapadmin_apitree.temp.data;
			var nodeData = [];
			for ( var i = 0, j = oData.length; i < j; i++) {
				if(node.id==oData[i][0]){
					nodeData = oData[i];
				}
			}
			for(var i = 0,j=oData.length;i<j;i++){
				var nlen = nodeData[4].length;
				var olen = oData[i][4].length;
				if (olen==nlen && nodeData[2]==oData[i][2]&& parseInt(nodeData[4].substr(nlen-4,nlen))<parseInt(oData[i][4].substr(olen-4,olen))) {
					k++;
				}
			}
			if(k>0){
				zapadmin_apitree.tree_forSortDown(oTag,nodeData,oData);
			}else{
				zapadmin.model_message('不能进行向下移动！');
			}
		} else {
			zapadmin.model_message('请选择后在进行操作！');
		}
	},
	
	tree_forSortDown : function(oTag,node,nodeAll){
		var length = node[4].length;
		var downNodeId = "";
		for(var i=1;i<10000;i++){
			var flag = false;
			var id = (parseInt(node[4].substr(length-4,length))+i).toString();
			for ( var l = 0, m = (4 - id.length); l < m; l++) {
				id = "0" + id;
			}
			var ai = node[4].substr(0,length-4)+id;
			for ( var k = 0, j = nodeAll.length; k < j; k++) {
				if(nodeAll[k][4]!=null&&ai==nodeAll[k][4]){
					downNodeId = ai;
					flag = true;
					break;
				}
			}
			if(flag){
				break;
			}
		}
		for ( var i = 0, j = nodeAll.length; i < j; i++) {
			//节点向下(本节点和孩子-1)
			if(node[4]==nodeAll[i][4] || nodeAll[i][4].substr(0,length)==node[4]){
				var sort = downNodeId+nodeAll[i][4].substr(length,nodeAll[i][4].length);
				zapjs.zw.func_do(oTag, null, {zw_f_uid:nodeAll[i][3],zw_f_sort:sort});
			//节点向上(上节点+1)
			}else if(downNodeId==nodeAll[i][4] || nodeAll[i][4].substr(0,length)==downNodeId){
				var sort = node[4]+nodeAll[i][4].substr(length,nodeAll[i][4].length);
				zapjs.zw.func_do(oTag, null, {zw_f_uid:nodeAll[i][3],zw_f_sort:sort});	
			}
		}
	},
	
	tree_edit : function(oTag) {
		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {
			$.get($('#zw_page_tree_zw_s_editpage').val() + "?"
					+ $('#zw_page_tree_zw_s_uid').val() + '='
					+ node.attributes.uid, function(result) {
				$('#zw_page_tree_right').html(result);
			});

		} else {
			zapadmin.model_message('请选择后在进行操作！');
		}
	},

	tree_delete : function(oTag) {
		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {

			var bFlag = false;
			var sId = node.id;

			var oData = zapadmin_apitree.temp.data;
			for ( var i = 0, j = oData.length; i < j; i++) {
				if (oData[i][2] == sId) {
					bFlag = true;
				}
			}

			if (bFlag) {
				zapadmin.model_message('有子节点不允许删除！');
			} else {
				zapjs.zw.func_delete(oTag, node.attributes.uid);
			}

		} else {
			zapadmin.model_message('请选择后在进行操作！');
		}
	},

	tree_add : function(oTag) {

		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {
			var sId = node.id;
			var sortmax = 0;//增加排序字段
			var iMax = 0;

			var oData = zapadmin_apitree.temp.data;
			for ( var i = 0, j = oData.length; i < j; i++) {
				if (oData[i][2] == sId && oData[i][0].substr(oData[i][2].length,oData[i][0].length) > iMax) {
					iMax = oData[i][0].substr(oData[i][0].length
							- 4);
				}
			}

			iMax = (parseInt(iMax) + 1).toString();

			for ( var i = 0, j = (4 - iMax.length); i < j; i++) {
				iMax = "0" + iMax;

			}
			for(var i=0,j=oData.length;i<j;i++ ){//获取排序字段
				if(oData[i][0]==sId&&oData[i][4]!=undefined){
					sortmax = oData[i][4].substr(0,sId.length)+iMax;
				}
			}

			iMax = sId + iMax;
			var url= $('#zw_page_tree_zw_s_addpage').val() + "?"
				+ $('#zw_page_tree_zw_s_parent').val() + '=' + sId + "&"
				+ $('#zw_page_tree_zw_s_code').val() + '=' + iMax;
			if($('#zw_page_tree_zw_s_sort').val()!=undefined){
					url+="&"+$('#zw_page_tree_zw_s_sort').val()+'='+sortmax;
			}
			$.get(url,
					function(result) {
						$('#zw_page_tree_right').html(result);
					});

		} else {

			zapadmin.model_message('请选择后在进行操作！');
		}

	}

};

if (typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_apitree",function() {
		return zapadmin_apitree;
	});
}
