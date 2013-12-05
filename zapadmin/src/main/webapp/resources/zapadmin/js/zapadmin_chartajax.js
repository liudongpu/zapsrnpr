var zapadmin_chartajax = {

	temp : {
		loadcount : {},
		checkdata:{}

	},

	init : function(options) {

		var defaults = {
			pagecode : '',
			id : '',
			height:'310px',
			width:'100%',
			//是否有选择框
			checked:1,
			//最大选择数量
			maxnum:1,
			source : null
		};

		var s = $.extend({}, defaults, options || {});
		
		
		zapadmin_chartajax.temp.checkdata[s.id]={};
		
		var sValues=parent.$('#'+s.id).val();
		var sTexts=parent.$('#'+s.id+"_show_text").val();
		
		
		if(s.maxnum==1)
		{
		}
		else if(sValues)
		{
			var aV=sValues.split(',');
			var aT=sTexts.split(',');
			
			for(var i=0,j=aV.length;i<j;i++)
			{
				
				zapadmin_chartajax.temp.checkdata[s.id][aV[i]]={key:aV[i],text:aT[i]};
			}
			
		}
		
		

		zapjs.zw.api_call('com_srnpr_zapweb_webapi_ChartApi', {
			pageCode : s.pagecode
		}, function(oData) {
			zapadmin_chartajax.upData(oData, s);

		});
	},

	upData : function(oData, s) {

		

			var aTable = [];
			aTable.push('<div style="height:'+s.height+';width:'+s.width+';" class="w_clear">');
			aTable.push('<table style="height:'+s.height+';"><thead><tr>');
			for (var p in oData.pageFields) {
				var othis = oData.pageFields[p];
				aTable.push('<th data-options="field:\'f_' + p + '\''+(s.checked>0&&p==0?',checkbox:true':'')+'">' + othis + '</th>');
			}
			aTable.push('</tr></thead></table>');
			
			aTable.push('</div>');
			$('#' + s.id).append(aTable.join(''));
			
			
			
			var sBaseUrl=zapjs.f.upurl();
			
			var sDataUrl=zapjs.zw.api_link('com_srnpr_zapweb_webapi_ChartApi') + "&pagecode=" + s.pagecode+"&"+sBaseUrl.split('?')[1];


		zapadmin_chartajax.temp.loadcount[s.id] =$('#' + s.id).find('table').datagrid({
			rownumbers : true,
			autoRowHeight : false,
			pagination : true,
			checkOnSelect:true,
			singleSelect:(s.maxnum==1?true:false),
			method:'get',
			pageSize : 10,
			onLoadSuccess:function(tableData)
			{
				//zapjs.d(data);
				//var opts =zapadmin_chartajax.temp.loadcount[s.id].getChecked();
				//window.console.debug(opts);
				
				var odata=tableData.rows;
				
				var tempcheck=zapadmin_chartajax.temp.checkdata[s.id];
				//zapjs.d(odata);
				for(var p in odata)
				{
					var othis=odata[p];
					if(tempcheck[othis["f_0"]])
					{
						tempcheck[othis["f_0"]]=null;
						
						zapadmin_chartajax.temp.loadcount[s.id].datagrid('checkRow',p);
					}
					
					
				}
				
			},
			onBeforeLoad:function(param)
			{
				zapadmin_chartajax.reset_checked(s.id);
			},

			url : sDataUrl
		});
		

	},
	reset_checked:function(sId)
	{
		if(zapadmin_chartajax.temp.loadcount[sId]!==undefined)
				{
					var checked = zapadmin_chartajax.temp.loadcount[sId].datagrid('getChecked');
					
					var tempcheck=zapadmin_chartajax.temp.checkdata[sId];
					
					for(var p in checked)
					{
						var othis=checked[p];
						tempcheck[othis["f_0"]]={key:othis["f_0"],text:othis["f_1"]};
						
					}

					$('.datagrid-header-check input').attr('checked',false);
					
				}
	},
	ok_value:function(sFncName,sParentId,sBoxId)
	{
		zapadmin_chartajax.reset_checked(sBoxId);
		
		var tempcheck=zapadmin_chartajax.temp.checkdata[sBoxId];
		
		var aValues=[];
		var aTexts=[];
		
		for(var p in tempcheck)
		{
			var othis=tempcheck[p];
			if(othis)
			{
				aValues.push(othis["key"]);
				aTexts.push(othis["text"]);
			}
			
			
		}
		
		
		
		eval( sFncName+'("'+ sParentId +'","'+ aValues.join(',') +'","'+ aTexts.join(',') +'")' );
		
	}
	
	
};

if ( typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_chartajax", ["lib/easyui/locale/easyui-lang-zh_CN"], function() {
		return zapadmin_chartajax;
	});
}
