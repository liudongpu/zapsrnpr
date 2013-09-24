var zapadmin_setreplace = {
	temp : {
		box : '_zapadmin_setreplace_box',
		tdtext : '<tr><td>{0}</td><td><input type="text" value="{1}" </td><td>{2}</td></tr>'
	},

	init : function(sId) {

		$('#' + sId).next('.btn').click(function() {
			zapadmin_setreplace.set_show(sId);
		});

	},

	set_show : function(sId) {

		var aHtml = [];

		aHtml.push('<div id="' + sId + zapadmin_setreplace.temp.box + '">');

		aHtml.push('<table>');

		var sVals = $('#' + sId).val().split('&');

		for (var i in sVals) {

			var sSet = sVals[i].split('=');

			aHtml.push(zapjs.f.stringformat(zapadmin_setreplace.temp.tdtext, sSet[0], sSet[1]));
			/*
			 aHtml.push('<tr><td>' + sSet[0] + '</td>');

			 aHtml.push('<td><input type="text" value="' + sSet[1] + '" /></td></tr>');*/
		}

		aHtml.push('</table>');

		aHtml.push('<div>设置:<input class="c_add_key" type="text"/>');

		aHtml.push('值：<input class="c_add_value" type="text"/>');

		aHtml.push('<input class="c_add_button btn btn-small" type="button" onclick="zapadmin_setreplace.add_set(\'' + sId + '\',this)" value="添加"/>');

		aHtml.push('</div><div>');

		aHtml.push('<input class=" btn  btn-success" type="button"  value="保存"/>');

		aHtml.push('</div>');

		aHtml.push('</div>');

		zapjs.f.window_box({
			content : aHtml.join(''),
			width : '800',
			height : '600'
		});
	},
	add_set : function(sId, oElm) {
		$('#' + sId + zapadmin_setreplace.temp.box + ' table').append(zapjs.f.stringformat(zapadmin_setreplace.temp.tdtext, $(oElm).prevAll('.c_add_key').val(), $(oElm).prevAll('.c_add_value').val(),''));
	},
	show_close:function(sId)
	{
		
	}
	
	
};

if ( typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_setreplace", function() {
		return zapadmin_setreplace;
	});
}
