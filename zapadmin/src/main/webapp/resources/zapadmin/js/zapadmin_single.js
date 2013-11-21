var zapadmin_single = {

	temp : {
		opts : {},
		checkdata : {}

	},

	init : function(options) {

		var defaults = {
			pagecode : '',
			id : '',
			buttonflag:true,
			callback:'parent.zapadmin_single.result'
		};

		var s = $.extend({}, defaults, options || {});

		zapadmin_single.temp.opts[s.id] = s;

		if(s.buttonflag)
		{
			$('#' + s.id+"_show_text").parent().append('<div class="w_left"><input class="btn" type="button" value="选择" onclick="zapadmin_single.show_box(\'' + s.id + '\')" /></div>');
		
			$('#' + s.id+"_show_text").parent().append('<div class="w_left w_w_70p"><ul class="zab_js_zapadmin_single_ul" id="' + s.id + '_show_ul"></ul></div><div class="w_clear"></div>');
		}
		zapadmin_single.show_text(s.id);
	},
	show_box : function(sId) {
		var s=zapadmin_single.temp.opts[sId];
		zapjs.f.window_box({
			id : sId + 'zapadmin_single_showbox',
			content : '<iframe src="../show/'+s.source+'?zw_s_iframe_select_source=' + sId + '&zw_s_iframe_select_page=' + s.source + '&zw_s_iframe_max_select='+s.max+'&zw_s_iframe_select_callback='+s.callback+'" frameborder="0" style="width:100%;height:500px;"></iframe>',

			width : '700',
			height : '550'
		});

	},
	close_box:function(sId)
	{
		zapjs.f.window_close(sId + 'zapadmin_single_showbox');
	},
	show_text : function(sId) {

		var sText = $('#' + sId + "_show_text").val();
		$('#' + sId + '_show_ul').html('');

		if (sText) {
			var aT = sText.split(',');
			for (var i in aT) {
				$('#' + sId + '_show_ul').append('<li>' + aT[i] + '</li>');
			}

		}

	},

	result : function(sId, sValues, sTexts) {

		$('#' + sId).val(sValues);
		$('#' + sId + "_show_text").val(sTexts);

		zapjs.f.window_close(sId + 'zapadmin_single_showbox');

		zapadmin_single.show_text(sId);

	}
};

if ( typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_single", ["lib/easyui/locale/easyui-lang-zh_CN"], function() {
		return zapadmin_single;
	});
}
