var zapadmin_setreplace = {
	temp : {
		box : '_zapadmin_setreplace_box',
		tdtext : '<tr><td>{2}</td><td><input type="text" class="c_setkey" value="{0}"/></td><td><input class="c_setvalue"  type="text" value="{1}"/> </td><td>{3}</td></tr>'
	},

	init : function(sId, sDid) {

		var sFuncName = "set_click";
		if (sDid == "46990319") {
			sFuncName = "component_click";
		}

		$('#' + sId).after('&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn btn-mini" onclick="zapadmin_setreplace.' + sFuncName + '(\'' + sId + '\',\'' + sDid + '\')"  ><i class="icon-tasks"></i></span>');

	},
	//字段设置页面专用调用
	component_click : function(sId, sDid) {
		var sClass = $('#zw_f_source_code').val();

		if (sClass) {
			zapjs.zw.up_json('page_chart_v_zw_define', {
				zw_f_parent_did : 46990319,
				zw_f_define_name : sClass
			}, function(o) {
				if (o.length == 1) {
					zapjs.d(o);
					zapadmin_setreplace.set_click(sId, o[0][0]);
				}
				else
				{
					zapjs.f.message('组件尚未定义');
				}
			});
		} else {
			zapjs.f.message('组件名称为空');
		}

	},

	set_click : function(sId, sDid) {

		zapjs.zw.up_json('page_chart_v_zw_define', {
			zw_f_parent_did : sDid
		}, function(o) {
			zapadmin_setreplace.set_show(sId, o);
		});
	},

	set_show : function(sId, oData) {

		var aHtml = [];
		aHtml.push('<div id="' + sId + zapadmin_setreplace.temp.box + '">');
		aHtml.push('<table class="table">');
		aHtml.push('<thead><tr><td>名称</td><td>设置</td><td>值</td><td>描述</td></tr></thead><tbody>');
		var sVals = $('#' + sId).val().split('&');
		var oTempMap = {};
		for (var i in sVals) {

			if (sVals[i] != "") {
				var sSet = sVals[i].split('=');

				oTempMap[sSet[0]] = sSet[1];

			}
			//aHtml.push(zapjs.f.stringformat(zapadmin_setreplace.temp.tdtext, sSet[0], sSet[1]));

		}

		if (oData && oData.length > 0) {
			for (var i in oData) {
				var oThis = oData[i];
				var sValue = '';
				if (oTempMap[oThis[1]]) {
					sValue = oTempMap[oThis[1]];
					oTempMap[oThis[1]] = '';
				}
				aHtml.push(zapjs.f.stringformat(zapadmin_setreplace.temp.tdtext, oThis[1], sValue, oThis[2], oThis[4]));
			}
		}

		for (var p in oTempMap) {
			if (oTempMap[p] != "") {
				aHtml.push(zapjs.f.stringformat(zapadmin_setreplace.temp.tdtext, p, oTempMap[p], '', ''));
			}
		}

		aHtml.push('</tbody></table>');

		aHtml.push('<div class="w_al_center">设置:<input class="c_add_key" type="text"/>');

		aHtml.push('值：<input class="c_add_value" type="text"/>');

		aHtml.push('<input class="c_add_button btn btn-small" type="button" onclick="zapadmin_setreplace.add_set(\'' + sId + '\',this)" value="添加"/>');

		aHtml.push('</div><div class="w_h_40 w_al_center">');

		aHtml.push('<input class=" btn  btn-success" type="button" onclick="zapadmin_setreplace.show_close(\'' + sId + '\')" value="保存"/>');

		aHtml.push('</div>');

		aHtml.push('</div>');

		zapjs.f.window_box({
			content : aHtml.join(''),
			width : '800',
			id:sId+'_zapadmin_setreplace_box'
		});
	},
	add_set : function(sId, oElm) {
		$('#' + sId + zapadmin_setreplace.temp.box + ' table').append(zapjs.f.stringformat(zapadmin_setreplace.temp.tdtext, $(oElm).prevAll('.c_add_key').val(), $(oElm).prevAll('.c_add_value').val(), '', ''));

		$(oElm).prevAll('.c_add_key').val('');
		$(oElm).prevAll('.c_add_value').val('');

	},
	show_close : function(sId) {

		var aJoin = [];

		$('#' + sId + zapadmin_setreplace.temp.box + '  table tbody').children('tr').each(function(n, e) {

			var sKey = $(e).find('.c_setkey').val();
			var sValue = $(e).find('.c_setvalue').val();

			if (sKey && sValue) {
				aJoin.push(sKey + '=' + sValue);
			}

		});

		$('#' + sId).val(aJoin.join('&'));

		zapjs.f.window_close(sId+'_zapadmin_setreplace_box');
	}
};

if ( typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_setreplace", function() {
		return zapadmin_setreplace;
	});
}
