var zs_template_productlist = {
	temp : {
		productlist : {}
	},
	set : function() {

		zs_template_productlist.temp.productlist = {};

		var oItem = zs_template.temp.currentItem;

		var aSkus = [];

		$(oItem).find('ul li').each(

		function(n, el) {

			var sVal = $(el).find('.c_sku').val();
			if (sVal) {

				// var othis = zs.f.json_parse(zs.f.decode(sVal));

				// zs_template_productlist.temp.productlist[othis["skuCode"]] =
				// othis;

				aSkus.push(sVal);

			}
		}

		);

		zapjs.zw.api_call(
				'com_cmall_productcenter_service_api_ApiGetSkusAndSetImages', {
					skuStrs : aSkus.join(",")
				}, zs_template_productlist.load_product);

	},

	load_product : function(o) {

		for (var i = 0, j = o.skuList.length; i < j; i++) {
			var othis = o.skuList[i];

			zs_template_productlist.temp.productlist[othis["skuCode"]] = othis;
		}

		var aHtml = [];
		aHtml.push('<div class="zat_template_productlist w_p_20">');
		aHtml
				.push('<input type="hidden" id="zat_template_productlist_productids"/>');
		aHtml
				.push('<input type="hidden" id="zat_template_productlist_productids_show_text"/>');
		aHtml
				.push('<input type="button" class="btn" onclick="zapadmin_single.show_box(\'zat_template_productlist_productids\')" value="添加商品"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
		aHtml
				.push('<input type="button" class="btn btn-success" onclick="zs_template_productlist.save_productlist(\'zat_template_productlist_productids\')" value="保存修改"/>');
		aHtml.push('</div>');
		aHtml.push('<div class="zat_template_productlist_show" >');

		aHtml.push('</div>');

		zapjs.f.window_box({
			id : 'zs_template_productlist_add_product_id',
			width : '500',
			height : '500',
			content : aHtml.join('')
		});
		zs.r([ 'zapadmin/js/zapadmin_single' ], function(a) {
			a.init({
				"text" : "",
				buttonflag : false,
				"source" : "page_chart_v_seller_pc_skuinfo",
				"id" : "zat_template_productlist_productids",
				"callback" : "parent.zs_template_productlist.add_product",
				"value" : "",
				"max" : "1"
			});
		});

		zs_template_productlist.show_productlist();

	},

	add_product : function(sId, sVal) {
		zapadmin_single.close_box('zat_template_productlist_productids');
		zapjs.zw.api_call(
				'com_cmall_productcenter_service_api_ApiGetSkusAndSetImages', {
					skuStrs : sVal
				}, zs_template_productlist.add_product_success);

	},
	add_product_success : function(o) {
		var othis = o.skuList[0];

		/*
		var iIndex = 1;
		for ( var p in zs_template_productlist.temp.productlist) {
			iIndex++;
		}
		 */
		zs_template_productlist.temp.productlist[othis["skuCode"]] = othis;

		zs_template_productlist.show_productlist();
		// zapjs.d(o);
	},
	// 显示商品列表
	show_productlist : function() {
		var aHtml = [];

		var aObj = [];
		for ( var p in zs_template_productlist.temp.productlist) {
			if (zs_template_productlist.temp.productlist[p]) {
				aObj.push(zs_template_productlist.temp.productlist[p]);
			}
		}

		aHtml.push('<ul class="w_ul">');
		for (var p = 0, j = aObj.length; p < j; p++) {
			var othis = aObj[p];
			aHtml.push('<li>');
			aHtml
					.push('<div class="c_move">拖动</div>');
			aHtml.push('<input type="hidden" value="'
					+ zs.f.encode(zs.f.json_stringify(othis))
					+ '" class="c_sku"/>');
			aHtml.push('<div class="c_image"><img src="' + othis["skuPicUrl"]
					+ '"/></div>');
			aHtml.push('<div><a class="w_right" href="javascript:zs_template_productlist.del_product(\''+othis["skuCode"]+'\')">删除</a></div>');
			aHtml.push('<div class="c_name w_clear">' + othis["skuName"] + '</div>');
			aHtml.push('<div class="c_price">' + othis["sellPrice"] + '</div>');

			aHtml.push('</li>');
		}
		aHtml.push('</ul>');
		$('.zat_template_productlist_show').html(aHtml.join(''));

		$('.zat_template_productlist_show li').draggable({
			revert : true,
			proxy : 'clone',
			handle : '.c_move'
		}).droppable(
				{
					onDragOver : function(e, source) {
						$(this).addClass(
								'zat_template_productlist_show_li_drag_css');
					},
					onDragLeave : function(e, source) {
						$(this).removeClass(
								'zat_template_productlist_show_li_drag_css');
					},
					onDrop : function(e, source) {
						$(this).removeClass(
								'zat_template_productlist_show_li_drag_css');

						var c = $(source);
						zs.d(c.offset().left);
						zs.d($(this).offset().left);
						zs.d(c.offset().top);
						zs.d($(this).offset().top);

						if (c.offset().top < $(this).offset().top) {
							c.insertAfter(this);
						} else if ((c.offset().left > $(this).offset().left)
								|| (c.offset().top > $(this).offset().top)) {
							c.insertBefore(this);
						} else {
							c.insertAfter(this);
						}
						// $(source).insertAfter(this);

					}
				});

	},
	
	del_product:function(pid)
	{
		zs_template_productlist.temp.productlist[pid]=null;
		zs_template_productlist.show_productlist();
	},
	
	save_productlist : function() {

		var aHtml = [];
		aHtml.push('<div class="' + zs_template.up_css('productlist')
				+ '"><div class="' + zs_template.up_css('productlist_fix')
				+ '"><ul>');

		$('.zat_template_productlist_show li')
				.each(
						function(n, el) {

							var sVal = $(el).find('.c_sku').val();
							var othis = zs.f.json_parse(zs.f.decode(sVal));
							aHtml.push('<li><a target="_blank" href="'
									+ zs_template.temp.customlink.code_sku
									+ othis["skuCode"]
									+ '">');

							/*
							 * aHtml.push('<input type="hidden" value="' +
							 * zs.f.encode(zs.f.json_stringify(othis)) + '"
							 * class="c_sku"/>');
							 */

							aHtml.push('<input type="hidden" value="'
									+ othis["skuCode"] + '" class="c_sku"/>');

							aHtml.push('<div class="c_image"><img src="'
									+ othis["skuPicUrl"] + '"/></div>');
							aHtml.push('<div class="c_info">');
							aHtml.push('<div class="c_name">'
									+ othis["skuName"] + '</div>');
							aHtml.push('<div class="c_price">￥'
									+ othis["sellPrice"] + '</div>');
							aHtml.push('<div class="c_mark">'
									+ othis["marketPrice"] + '</div>');
							aHtml
									.push('<div class="c_buy ctheme_shop_png"></div>');
							aHtml.push('<div>');
							aHtml.push('</a></li>');
						}

				);

		aHtml.push('</ul><div class="' + zs_template.up_css('clear')
				+ '"></div></div></div>');
		zs_template.view_html(aHtml.join(''));

		// zapjs.f.window_close();

		zapjs.f.window_close('zs_template_productlist_add_product_id');

		zs_template.save_html();

	}

};

zs_template.add_extend({
	id : 'productlist',
	title : '商品列表',
	settext : '商品设置',
	event : zs_template_productlist
}

);
