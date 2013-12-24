var zs_template_custominfo = {
	temp : {

	},
	// 顶部导航大图
	set : function() {

		var oItem = zs_template.temp.currentItem;

		var aContent = [];
		aContent.push('<div class="zat_template_custominfo w_p_20">');
		aContent
				.push('<div class=""><input type="button" class="btn btn-success" onclick="zs_template_custominfo.success()" value="确认修改" /></div><div class="w_h_10"></div>');

		var sHtml = $(oItem).find('.' + zs_template.up_css('custominfo'))
				.html();

		aContent
				.push('<textarea class="w_none" id="zw_f_editor"  name="zw_f_editor">'
						+ (sHtml ? sHtml : '') + '</textarea>');
		aContent.push('<div class="w_clear"></div>');
		aContent.push('</div>');
		zapjs.f.window_box({

			content : aContent.join(''),
			width : 700,
			height : 440
		});

		zapjs.zw.editor_show('zw_f_editor', '../upload/');
	},
	success : function() {
		zs_template.view_html('<div class="' + zs_template.up_css('custominfo')
				+ '">' + $('#zw_f_editor').val() + '</div>');

		zapjs.f.window_close();
		zs_template.save_html();
	}

};

zs_template.add_extend({
	id : 'custominfo',
	title : '自定义内容',
	settext : '设置内容',
	event : zs_template_custominfo
}

);