var zapadmin_chartajax = {

	temp : {
		loadcount : {}

	},

	init : function(options) {

		var defaults = {
			pagecode : '',
			id : '',
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

		//判断是否第一次加载
		if (zapadmin_chartajax.temp.loadcount[s.id] === undefined) {
			zapadmin_chartajax.temp.loadcount[s.id] = s.id;

			var aTable = [];
			aTable.push('<table style="height:310px;"><thead><tr>');
			for (var p in oData.pageFields) {
				var othis = oData.pageFields[p];
				aTable.push('<th field="f_' + p + '">' + othis + '</th>');
			}
			aTable.push('</tr></thead></table>');

			$('#' + s.id).append(aTable.join(''));

		}

		$('#' + s.id).children('table').datagrid({
			rownumbers : true,
			singleSelect : true,
			autoRowHeight : false,
			pagination : true,
			method:'get',
			pageSize : 10,

			url : zapjs.zw.api_link('com_srnpr_zapweb_webapi_ChartApi') + "&pagecode=" + s.pagecode
		});

	}
};

if ( typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_chartajax", ["lib/easyui/locale/easyui-lang-zh_CN"], function() {
		return zapadmin_chartajax;
	});
}
