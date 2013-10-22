var zs_template = {
	temp : {

		currentItem : null,
		currentType : ''

	},

	init : function() {

		zs_template.read_html();
		zs_template.init_drag();
		zs_template.init_event();

	},
	//删除元素
	del_elm : function(el) {
		$(el).remove();
	},
	//初始化各种按钮操作
	init_event : function() {

		$(".zat_target_box").delegate(".zat_dragable_btn_del", "click", function(e) {
			e.preventDefault();
			$(this).parents('.zat_dragable_item').remove();
			zs_template.save_html();
		});
		$(".zat_target_box").delegate(".zat_dragable_btn_set", "click", function(e) {
			e.preventDefault();
			//$(this).parents('.zat_dragable_item').remove();
			var oItem = $(this).parents('.zat_dragable_item');
			zs_template.temp.currentItem = oItem;
			var oSet = oItem.find('.zat_dragable_view').attr('zat_template_type');
			zs_template.temp.currentType = oSet;
			zs_template_set["set_"+oSet]();
			//zs_template.save_html();
		});

	},

	

	view_html : function(sHtml) {
		$(zs_template.temp.currentItem).find('.zat_dragable_view').html(sHtml);
	},

	//初始化拖拽
	init_drag : function() {
		var dragset = {
			revert : true,
			proxy : 'clone',
			handle : '.zat_dragable_move'
		};
		var dropset = {

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
		if (!$('.zat_target_inline').html()) {
			$('.zat_target_inline').html('<div class="zat_dragable_item" style="position:absolute;width:100%;height:300px;"></div>');
			zs_template.init_drag();

		}

	},

	save_html : function() {

		//清空绝对定位元素
		$('.zat_target_box .zat_dragable_item').each(function(n, el) {
			if ($(el).css('position') == "absolute") {
				$(el).remove();
			}
		});
		//zs_template.init_event();

		zs_template.check_html();

		var sSave = $('.zat_target_inline').html();

		zs_storage.save('zs_template_storage', sSave);

	},
	read_html : function() {
		var sHtml = zs_storage.read('zs_template_storage');

		if (sHtml) {
			$('.zat_target_inline').html(sHtml);
		}

		zs_template.check_html();

	}
};

zs.f.define("zs/template/zs_template", ["lib/easyui/jquery.easyui.min", "zs/template/zs_template_set", "zs/storage/zs_storage", "zapjs/zapjs"], zs_template);

