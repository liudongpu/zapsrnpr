var zs_template_muleimage = {
	temp : {

	},
	// 顶部导航大图
	set : function() {

		var oItem = zs_template.temp.currentItem;

		zs
				.r(
						[ "zapweb/js/zapweb_upload" ],
						function(a) {

							var aImages = [];
							var aLinks = [];

							$(oItem).find('img').each(function(n, el) {

								aImages.push($(el).attr('src'));

								if ($(el).parent().is('a')) {
									aLinks.push($(el).parent().attr('href'));
								} else {
									aLinks.push('');
								}

							}

							);

							var aHtml = zapweb_upload
									.upload_html(zapjs.c.path_upload,
											'zw_f_muleimage',
											aImages.join('|'),
											'zw_s_number=10&zw_s_link=zw_f_muleimage_link');
							var aContent = [];
							aContent
									.push('<div class="zat_template_muleimage w_p_20">');
							aContent
									.push('<div class=""><input type="button" class="btn btn-success" onclick="zs_template_muleimage.success_upload()" value="确认修改" /></div><div class="w_h_10"></div>');
							aContent
									.push('<div><div  class="w_left">多图：</div><div class="w_left" style="width:400px;">');
							aContent
									.push('<input type="hidden" id="zw_f_muleimage_link" name="zw_f_muleimage_link" value="'
											+ aLinks.join('|') + '"/>');

							aContent.push(aHtml);
							aContent.push('</div></div>');
							aContent.push('<div class="w_clear"></div>');
							aContent.push('</div>');
							zapjs.f.window_box({
								id : 'zs_template_muleimage_window',
								content : aContent.join('')
							});

							zapweb_upload.upload_file('zw_f_muleimage');

						});
	},
	success_upload : function() {

		var sVal = $('#zw_f_muleimage').val();

		zapweb_upload.save_link('zw_f_muleimage');

		if (sVal != '') {

			// var sUid = zs.f.uuid('uid_');
			var aHtml = [];
			aHtml.push('<div class="' + zs_template.up_css('muleimage')
					+ '"><div class="' + zs_template.up_css('muleimage_fix')
					+ '"><ul>');
			var aImages = sVal.split('|');

			var aLinks = $('#zw_f_muleimage_link').val().split('|');

			for (var i = 0, j = aImages.length; i < j; i++) {
				aHtml.push('<li>');
				if (aLinks[i]) {
					aHtml.push('<a href="' + aLinks[i] + '">');
				}

				aHtml.push('<img src="' + aImages[i] + '"/>');

				if (aLinks[i]) {
					aHtml.push('</a>');
				}
			}
			aHtml.push('</li>');
			aHtml.push('</ul><div class="ctheme_shop_clear"></div></div>');

			zs_template.view_html(aHtml.join(''));

			zapjs.f.window_close('zs_template_muleimage_window');
			zs_template.save_html();

		} else {
			zapjs.f.modal({
				content : '请先选择图片'
			});
		}
	}

};

zs_template.add_extend({
	id : 'muleimage',
	title : '多个图片',
	settext : '设置图片',
	event : zs_template_muleimage
}

);