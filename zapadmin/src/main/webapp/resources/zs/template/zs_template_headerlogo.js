var zs_template_headerlogo = {
	temp : {
		productlist : {}
	},
	// 顶部导航大图
	set : function() {

		zs.r([ "zapweb/js/zapweb_upload" ], function(a) {
			zs_template_headerlogo.show_text();
		});

	},

	show_text : function() {
		var oItem = zs_template.temp.currentItem;

		var sImage = $(oItem).find('img').attr('src');

		var aHtml = zapweb_upload.upload_html(zapjs.c.path_upload,
				'zw_f_headerlogo', sImage ? sImage : '', 'zw_s_description=建议宽度1200px');
		var aContent = [];
		aContent.push('<div class="zat_template_headerlogo w_p_20">');
		aContent
				.push('<div><input type="button" class="btn btn-success" onclick="zs_template_headerlogo.success_headerlogo()" value="确认修改"/></div><div class="w_clear"></div>');
		aContent
				.push('<div class="w_h_10"></div><div><div  class="w_left">顶部大图：</div><div>');

		aContent.push(aHtml);

		aContent.push('</div></div><div class="w_clear"></div>');

		aContent
				.push('<div><div class="w_left">导航菜单：</div><div class="w_left"><table>');
		aContent.push('<tr><td>导航菜单名称</td><td>链接地址</td></tr>');
		aContent
				.push('<tr><td><input id="zs_template_headerlogo_text" type="text" class="w_w_100"/></td><td><input id="zs_template_headerlogo_link" type="text"/></td><td><input class="btn" onclick="zs_template_headerlogo.add_nav(this)" type="button" value="添加"/></td></tr>');
		aContent.push('</table></div></div><div class="w_clear"></div>');

		aContent.push('</div>');
		zapjs.f.window_box({
			content : aContent.join(''),
			width : '700'
		});

		zapweb_upload.upload_file('zw_f_headerlogo');

		$(oItem).find('ul li a').each(function(n, el) {

			var sText = $(el).text();
			var sLink = $(el).attr('href');
			zs_template_headerlogo.add_table(sText, sLink);

		});

	},

	add_nav : function() {

		var sText = $('#zs_template_headerlogo_text').val();
		var sLink = $('#zs_template_headerlogo_link').val();

		if (!(sText && sLink)) {
			zapjs.f.message('不能为空！');
			return false;
		}

		zs_template_headerlogo.add_table(sText, sLink);

		$('#zs_template_headerlogo_text').val('');
		$('#zs_template_headerlogo_link').val('');

	},
	add_table : function(sText, sLink) {
		var aHtml = [];
		aHtml
				.push('<tr><td><input type="text"  class="w_w_100  c_text" value="'
						+ sText
						+ '"/></td><td><input type="text" class="c_link" value="'
						+ sLink + '"/></td><td></td></tr>');

		$('.zat_template_headerlogo table').append(aHtml.join(''));
	},

	success_headerlogo : function() {
		if ($('#zw_f_headerlogo').val() != '') {

			var aHtml = [];

			var sFavLink = "";
			if (zs_template.temp.customlink.code_seller) {
				sFavLink = zs_template.temp.customlink.link_favorite.replace(
						'{0}', zs_template.temp.customlink.code_seller);
			}

			aHtml.push('<div class="' + zs_template.up_css('headerlogo')
					+ '"><div class="c_image"><a href="' + sFavLink
					+ '"><img src="' + $('#zw_f_headerlogo').val()
					+ '"/></a></div>');

			aHtml.push('<div class="c_navbg"><div class="c_navtext"><ul>');

			$('.zat_template_headerlogo table tr').each(
					function(n, el) {
						if (n > 1) {
							var sText = $(el).find('.c_text').val();
							var sLink = $(el).find('.c_link').val();
							if (sText && sLink)
								aHtml.push('<li><a href="' + sLink + '">'
										+ sText + '</a></li>');
						}
					});

			aHtml.push('</ul></div><div class="' + zs_template.up_css('clear')
					+ '"></div></div>');

			aHtml.push('</div>');

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
	id : 'headerlogo',
	title : '整店顶部导航',
	settext : '顶部导航设置',
	event : zs_template_headerlogo
}

);