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
			checked:1,
			source : null
		};

		var s = $.extend({}, defaults, options || {});

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

		

		zapadmin_chartajax.temp.loadcount[s.id] =$('#' + s.id).find('table').datagrid({
			rownumbers : true,
			//singleSelect : false,
			//view:'scrollview',
			autoRowHeight : false,
			//fitColumns:true,
			//nowrap:false,
			pagination : true,
			checkOnSelect:true,
			method:'get',
			pageSize : 10,
			onLoadSuccess:function(data)
			{
				//zapjs.d(data);
				//var opts =zapadmin_chartajax.temp.loadcount[s.id].getChecked();
				//window.console.debug(opts);
				
				
				
			},
			onBeforeLoad:function(param)
			{
				
				if(zapadmin_chartajax.temp.loadcount[s.id]!==undefined)
				{
					var pager = zapadmin_chartajax.temp.loadcount[s.id].datagrid('getChecked');
					zapjs.d(pager);
					$('.datagrid-header-check input').attr('checked',false);
					
				}
			
				
				
				
				
			},

			url : zapjs.zw.api_link('com_srnpr_zapweb_webapi_ChartApi') + "&pagecode=" + s.pagecode
		});

	}
};

if ( typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_chartajax", ["lib/easyui/locale/easyui-lang-zh_CN"], function() {
		return zapadmin_chartajax;
	});
}
