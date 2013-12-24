var zs_template_centeradv = {
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

							if (aImages.length > 2) {

								aImages.splice(0, 1);
								aImages.pop();
								aLinks.splice(0, 1);
								aLinks.pop();
							}

							var aHtml = zapweb_upload
									.upload_html(zapjs.c.path_upload,
											'zw_f_centeradv',
											aImages.join('|'),
											'zw_s_number=10&zw_s_link=zw_f_centeradv_link');
							var aContent = [];
							aContent
									.push('<div class="zat_template_centeradv w_p_20">');
							aContent
									.push('<div class=""><input type="button" class="btn btn-success" onclick="zs_template_centeradv.success_upload()" value="确认修改" /></div><div class="w_h_10"></div>');
							aContent
									.push('<div><div  class="w_left">轮播广告图：</div><div class="w_left" style="width:400px;">');

							aContent
									.push('<input type="hidden" id="zw_f_centeradv_link" name="zw_f_centeradv_link" value="'
											+ aLinks.join('|') + '"/>');
							aContent.push(aHtml);
							aContent.push('</div></div>');
							aContent.push('<div class="w_clear"></div>');
							aContent.push('</div>');
							zapjs.f.window_box({
								id : 'zs_template_centeradv_window',
								content : aContent.join('')
							});

							zapweb_upload.upload_file('zw_f_centeradv');

						});
	},
	success_upload : function() {

		var sVal = $('#zw_f_centeradv').val();

		if (sVal != '') {

			zapweb_upload.save_link('zw_f_centeradv');

			var sId = zs.f.uuid('uid_');
			var aHtml = [];

			var aImages = sVal.split('|');

			var aLinks = $('#zw_f_centeradv_link').val().split('|');

			aImages.splice(0, 0, aImages[aImages.length - 1]);
			aImages.push(aImages[1]);

			aLinks.splice(0, 0, aLinks[aLinks.length - 1]);
			aLinks.push(aLinks[1]);

			/*
			 * aHtml .push('<div class="' + zs_template.up_css('centeradv') +
			 * '"><input id="' + sUid + '" type="hidden" value="' +
			 * $('#zw_f_centeradv').val() + '"/><script>zs.r(["zs/focus/zs_focus_carousel"],function(a){a.play({id:"' +
			 * sUid + '"});})</script></div>');
			 */

			aHtml.push('<div class="' + zs_template.up_css('centeradv')
					+ '"><div class="c_info" id="' + sId
					+ '"><div class="c_box"><ul>');

			for (var i = 0, j = aImages.length; i < j; i++) {
				aHtml.push('<li><div class="c_item">');
				if (aLinks[i]) {
					aHtml.push('<a href="' + aLinks[i] + '">');
				}

				aHtml.push('<img src="' + aImages[i] + '"/>');
				if (aLinks[i]) {
					aHtml.push('</a>');
				}
				aHtml.push('</div></li>');
			}
			;

			aHtml.push('</ul></div>');

			aHtml
					.push('<div class="c_left c_nav " onclick="zs_focus_carousel.move_to(\''
							+ sId
							+ '\',\'left\')"><div class="c_button ctheme_shop_png"></div></div><div class="c_right c_nav ctheme_shop_png" onclick="zs_focus_carousel.move_to(\''
							+ sId
							+ '\',\'right\')"><div class="c_button ctheme_shop_png"></div></div>');

			aHtml
					.push('</div></div>');

			zs_template.view_html(aHtml.join(''));

			zapjs.f.window_close('zs_template_centeradv_window');
			zs_template.save_html();
			
			zs.r(["zs/focus/zs_focus_carousel"],function(a){a.auto_play();});
			

		} else {
			zapjs.f.modal({
				content : '请先选择图片'
			});
		}
	}

};

zs_template.add_extend({
	id : 'centeradv',
	title : '轮播广告图',
	settext : '设置广告图',
	event : zs_template_centeradv
}

);