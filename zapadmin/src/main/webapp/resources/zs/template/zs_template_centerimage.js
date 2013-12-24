var zs_template_centerimage = {
	temp : {
		productlist : {}
	},
	// 顶部导航大图
	set : function() {

		var oItem = zs_template.temp.currentItem;
		var sImage = $(oItem).find('img').attr('src');
		zs
				.r(
						[ "zapweb/js/zapweb_upload" ],
						function(a) {
							var aHtml = zapweb_upload.upload_html(
									zapjs.c.path_upload, 'zw_f_centerimage', sImage ? sImage : '',
									'zw_s_description=建议宽度980px');
							var aContent = [];
							aContent
									.push('<div class="zat_template_centerimage w_p_20">');
							aContent.push('<div class=""><input type="button" class="btn btn-success" onclick="zs_template_centerimage.success_centerimage()" value="确认修改" /></div><div class="w_h_10"></div>');
							
							aContent.push(aHtml);
							aContent
									.push('<div class="w_clear"></div>');
							aContent.push('</div>');
							zapjs.f.window_box({
								id:'zs_template_centerimage_window',
								content : aContent.join('')
							});

							zapweb_upload.upload_file('zw_f_centerimage');

						});

	},
	success_centerimage : function() {
		if ($('#zw_f_centerimage').val() != '') {

			zs_template.view_html('<div class="'+zs_template.up_css('centerimage')+'"><img src="'
					+ $('#zw_f_centerimage').val() + '"/></div>');

			zapjs.f.window_close('zs_template_centerimage_window');
			zs_template.save_html();

		} else {
			zapjs.f.modal({
				content : '请先选择图片'
			});
		}

	}

};

zs_template.add_extend({
	id : 'centerimage',
	title : '中部大图',
	settext : '设置图片',
	event : zs_template_centerimage
}

);