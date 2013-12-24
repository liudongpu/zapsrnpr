var zs_template = {
	temp : {

		currentItem : null,
		currentType : '',
		extendlist : {},
		customlink : {
			// 收藏链接
			link_favorite : '/user/favorite/add/code/{0}/type/1 ',
			// 商家编号
			code_seller : '',
			//SKU最终页地址
			code_sku:''
		},
		basecss : 'ctheme_shop_'

	},

	init : function() {

		var aExtendList = [ "zs/template/zs_template_productlist",
				"zs/template/zs_template_headerlogo",
				"zs/template/zs_template_centerimage",
				"zs/template/zs_template_centeradv",
				"zs/template/zs_template_muleimage",
				"zs/template/zs_template_custominfo" ,
				"zs/template/zs_template_customhtml"];

		zs.r(aExtendList, function() {
			zs_template.begin();

		}

		);

	},
	begin : function() {
		zs_template.read_html();

	},

	up_css : function(sName) {
		return zs_template.temp.basecss + sName;
	},

	add_extend : function(s) {

		zs_template.temp.extendlist[s.id] = s;

		var aHtml = '<div class="zat_dragable_item  zat_drag_prev">'
				+ '<div class="zat_dragable_priview">'
				+ '<span class="zat_dragable_title">'
				+ s.title
				+ '</span> <span '
				+ 'class="zat_dragable_button zat_dragable_move">拖动</span> <span '
				+ 'class="zat_dragable_options"><span '
				+ 'class="zat_dragable_button  zat_dragable_btn_del">删除</span> <span '
				+ 'class="zat_dragable_button zat_dragable_btn_set">'
				+ s.settext + '</span> </span>' + '</div>'
				+ '<div class="zat_dragable_view" zat_template_type="' + s.id
				+ '">' + '<div class="w_h_20"></div></div></div>';

		$('#zat_tabs_extend_list').append(aHtml);

	},
	// 删除元素
	del_elm : function(el) {

		$(el).remove();
	},
	// 初始化各种按钮操作
	init_event : function() {

		$(".zat_target_box").delegate(".zat_dragable_btn_del", "click",
				function(e) {
					e.preventDefault();
					if (confirm('确定要删除吗？')) {
						$(this).parents('.zat_dragable_item').remove();
						zs_template.save_html();
					}
				});
		$(".zat_target_box").delegate(
				".zat_dragable_btn_set",
				"click",
				function(e) {
					e.preventDefault();
					// $(this).parents('.zat_dragable_item').remove();
					var oItem = $(this).parents('.zat_dragable_item');
					zs_template.temp.currentItem = oItem;
					var oSet = oItem.find('.zat_dragable_view').attr(
							'zat_template_type');
					zs_template.temp.currentType = oSet;
					// zs_template_set["set_" + oSet]();
					// zs_template.save_html();
					zs_template.temp.extendlist[oSet].event.set();

				});

	},

	view_html : function(sHtml) {
		$(zs_template.temp.currentItem).find('.zat_dragable_view').html(sHtml);
	},

	// 初始化拖拽
	init_drag : function() {
		var dragset = {
			revert : true,
			proxy : 'clone',
			handle : '.zat_dragable_move'
		};
		var dropset = {
			accept : '.zat_dragable_item',
			onDragOver : function(e, source) {

				$(this).addClass('zat_dragable_border');
			},
			onDragLeave : function(e, source) {
				$(this).removeClass('zat_dragable_border');
			},
			onDrop : function(e, source) {
				$(this).removeClass('zat_dragable_border');

				var c = $(source);
				if (c.hasClass('zat_drag_prev')) {
					c = c.clone().removeClass('zat_drag_prev');
					c.draggable(dragset).droppable(dropset);
				} else if ($(this).hasClass('zat_drag_prev')) {
					return false;
				}

				if ($(this).hasClass('zat_dragset_top')) {
					c.insertBefore(this);
				}
				if (c.offset().top > $(this).offset().top) {
					c.insertBefore(this);
				} else {
					c.insertAfter(this);
				}
				zs_template.save_html();

			}
		};

		$('.zat_dragable_item').draggable(dragset).droppable(dropset);
	},

	check_html : function() {

		// zs.d($('.zat_target_inline').find('.zat_dragable_item').size());

		if (!$('.zat_target_inline').html()
				|| $('.zat_target_inline').find('.zat_dragable_item').size() == 0) {
			$('.zat_target_inline')
					.html(
							'<div class="'
									+ zs_template.up_css('boxbody')
									+ '"><div class="zat_dragable_item" style="position:absolute;width:100%;height:300px;">拖拽到此处</div></div>');
			zs_template.init_drag();

		}

	},

	// 生成并格式化预览的元素
	priview_html : function() {
		var ePreview = $('.zat_target_priview');

		ePreview.html($('.zat_target_inline').html());

		// 将所有的实际展示元素提前
		ePreview.find('.zat_dragable_item').each(function(n, el) {
			$(el).find('.zat_dragable_view').insertAfter($(el));
			$(el).remove();
		}

		);

		// 隐藏所有轮播图
		ePreview.find('.ctheme_shop_centeradv .c_info').each(function(n, el) {
			$(el).hide();
		}

		);
		
		
		// 隐藏所有轮播图的左右按钮
		ePreview.find('.ctheme_shop_centeradv .c_info .c_nav').each(function(n, el) {
			$(el).remove();
		}

		);
		

		// 将第一个元素标记为所有页面通用调用
		ePreview.find('.zat_dragable_view').first().append(
				'<div class="ctheme_shop_top"></div>');

		/*
		 * ePreview.find('.ctheme_shop_productlist .c_sku').each(function(n, el) {
		 * 
		 * $(el).remove(); } );
		 */

		return ePreview.html();
	},

	save_html : function() {

		zs_template.do_save(2);

	},
	do_save : function(iType) {
		// 清空绝对定位元素
		$('.zat_target_box .zat_dragable_item').each(function(n, el) {
			if ($(el).css('position') == "absolute") {
				$(el).remove();
			}
		});
		// zs_template.init_event();

		zs_template.check_html();

		var sSave = $('.zat_target_inline').html();

		var sPreview = zs_template.priview_html();

		var sUid = zapjs.f.urlget('zw_f_uid');
		if (sUid) {
			var oData = {
				callType : iType,
				uid : sUid,
				preview : sPreview,
				content : sSave
			};
			zapjs.zw.api_call(
					"com_cmall_usercenter_template_ApiGetShopTemplate", oData,
					function(oResult) {
						if (iType == 3) {
							zapjs.f.message('操作成功');
						}
					});
		} else {
			zs.d('test modal');
			zs_storage.save('zs_template_storage', sSave);

		}
	},

	read_html : function() {

		var sUid = zapjs.f.urlget('zw_f_uid');

		if (sUid) {

			$('#manage_template_priview_link').html(
					'<a class="btn btn-success" href="preview?uid=' + sUid
							+ '" target="_blank"> 预览</a>');

			var oData = {
				callType : 1,
				uid : sUid
			};
			zapjs.zw.api_call(
					"com_cmall_usercenter_template_ApiGetShopTemplate", oData,
					zs_template.read_back);

		} else {
			zs.d('test modal');
			var sHtml = zs_storage.read('zs_template_storage');
			zs_template.do_show(sHtml);

		}

	},
	read_back : function(o) {
		var sHtml = o.resultObject;
		zs_template.do_show(sHtml);

	},

	do_show : function(sHtml) {
		if (sHtml)
			$('.zat_target_inline').html(sHtml);
		zs_template.check_html();

		zs_template.init_drag();
		zs_template.init_event();

		// 调用自动播放代码
		zs.r([ "zs/focus/zs_focus_carousel" ], function() {
			zs_focus_carousel.auto_play();
		});

	}

};

zs.f.define("zs/template/zs_template", [ "lib/easyui/jquery.easyui.min",
		"zs/storage/zs_storage", "zapjs/zapjs", "zapjs/zapjs.zw",
		"lib/less/less-1.5.0.min" ], zs_template);
