var zs_template_centeradv = {
	temp : {

	},
	// 顶部导航大图
	set : function() {

		var oItem = zs_template.temp.currentItem;
		var sImage = $(oItem).find('input').val();
		zs
				.r(
						[ "zapweb/js/zapweb_upload" ],
						function(a) {
							var aHtml = zapweb_upload.upload_html(
									zapjs.c.path_upload, 'zw_f_centeradv',
									sImage ? sImage : '', 'zw_s_number=10');
							var aContent = [];
							aContent
									.push('<div class="zat_template_centeradv w_p_20">');
							aContent
									.push('<div class=""><input type="button" class="btn btn-success" onclick="zs_template_centeradv.success_upload()" value="确认修改" /></div><div class="w_h_10"></div>');
							aContent
									.push('<div><div  class="w_left">顶部大图：</div><div class="w_left" style="width:400px;">');
							aContent.push(aHtml);
							aContent.push('</div></div>');
							aContent.push('<div class="w_clear"></div>');
							aContent.push('</div>');
							zapjs.f.window_box({
								content : aContent.join('')
							});

							zapweb_upload.upload_file('zw_f_centeradv');

						});
	},
	success_upload : function() {
		if ($('#zw_f_centeradv').val() != '') {

			var sUid = zs.f.uuid('uid_');
			var aHtml = [];
			aHtml
					.push('<div class="c_theme_centeradv"><input id="'
							+ sUid
							+ '" type="hidden" value="'
							+ $('#zw_f_centeradv').val()
							+ '"/><script>zs.r(["zs/focus/zs_focus_carousel"],function(a){a.play({id:"'
							+ sUid + '"});})</script></div>');

			zs_template.view_html(aHtml.join(''));

			zapjs.f.window_close();
			zs_template.save_html();

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