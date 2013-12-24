var zs_template_customhtml = {
	temp : {

	},
	// 顶部导航大图
	set : function() {

		var oItem = zs_template.temp.currentItem;

		var aContent = [];
		aContent.push('<div class="zat_template_customhtml w_p_20">');
		aContent
				.push('<div class=""><input type="button" class="btn btn-success" onclick="zs_template_customhtml.success()" value="确认修改" /></div><div class="w_h_10"></div>');

		var sHtml = $(oItem).find('.' + zs_template.up_css('customhtml'))
				.html();

		aContent
				.push('<textarea id="zat_template_customhtml_content"  name="zat_template_customhtml_content">'
						+ (sHtml ? sHtml : '') + '</textarea>');
		aContent.push('<div class="w_clear"></div>');
		aContent.push('</div>');
		zapjs.f.window_box({

			content : aContent.join(''),
			width : 700,
			height : 440
		});

		
	},
	success : function() {
		zs_template.view_html('<div class="' + zs_template.up_css('customhtml')
				+ '">' + $('#zat_template_customhtml_content').val() + '</div>');

		zapjs.f.window_close();
		zs_template.save_html();
	}

};

zs_template.add_extend({
	id : 'customhtml',
	title : '自定义Html',
	settext : '设置Html',
	event : zs_template_customhtml
}

);