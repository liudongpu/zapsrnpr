var zs_focus_carousel = {

	temp : {

		objs : {},
		// 是否已添加过定时器 防止多个调用时的多次添加定时
		flaginterval : false
	},

	play : function(options) {

		zs.f.delay(function() {
			return typeof jQuery === "function";

		}, function() {
			zs.f.ready(function() {
				zs_focus_carousel.do_play(options);
			});
		}, 500);

	},
	// 自动播放
	auto_play : function() {

		zs.f.delay_ready(

		function() {

			$('.ctheme_shop_boxbody .ctheme_shop_centeradv .c_info').each(
					function(n, el) {

						if (!$(el).attr('id')) {
							$(el).attr('id', zs.f.uuid('uid_'));
						}

						zs_focus_carousel.do_play({
							id : $(el).attr('id')
						});
					}

			);
		}

		);

	},

	do_play : function(options) {

		var defaults = {
			id : '',
			pics : [],
			width : 980,
			every : 0,
			size : 0,
			timer : 5000,
			flagauto : true,
			index : 1
		};
		// zs.d($(document.body).width());

		var s = $.extend({}, defaults, options || {});

		var sId = s.id;

		if (zs_focus_carousel.temp.objs[sId]) {
			return false;
		}

		var aImages = [];

		$('#' + sId).find('img').each(function(n, el) {

			aImages.push($('el').attr('src'));

		});

		if (aImages.length == 0) {
			return false;
		}
		s.pics = aImages;
		s.size = s.pics.length;

		

		$('#' + sId).show();

		var eParent = $('#' + sId).parent();
		var sWidth = eParent.find('ul li .c_item').width();
		s.width = parseInt(sWidth);

		var iBodyWidth = parseInt($(document.body).width());

		s.every = (iBodyWidth - s.width) / 2;
		zs_focus_carousel.temp.objs[sId] = s;
		if (iBodyWidth < s.width) {
			eParent.find('.c_nav').hide();
		} else {
			// 如果没有导航信息
			if (eParent.find('.c_nav').size() == 0) {

				eParent
						.append('<div class="c_left c_nav" onclick="zs_focus_carousel.move_to(\''
								+ sId
								+ '\',\'left\')"><div class="c_button ctheme_shop_png"></div></div><div class="c_right c_nav ctheme_shop_png" onclick="zs_focus_carousel.move_to(\''
								+ sId
								+ '\',\'right\')"><div class="c_button ctheme_shop_png"></div></div>');
				
			}
		}
		
		
		eParent.find('.c_nav').width(s.every + 1);

		eParent.hover(function() {
			s.flagauto = false;
			
		}, function() {
			s.flagauto = true;
			
		});

		zs_focus_carousel.left_to(s);

		if (s.timer > 0 && zs_focus_carousel.temp.flaginterval == false) {
			zs_focus_carousel.temp.flaginterval = true;
			setInterval(zs_focus_carousel.interval_do, s.timer);
		}

	},
	interval_do : function() {

		for ( var sId in zs_focus_carousel.temp.objs) {
			var s = zs_focus_carousel.temp.objs[sId];
			if (s.flagauto) {
				
				zs_focus_carousel.move_to(sId, 'right');
			}

		}

	},
	left_to : function(s) {
		$('#' + s.id + ' ul').css('left',
				(0 - (s.index * s.width - s.every)) + 'px');
	},

	move_to : function(sId, sTo) {

		var s = zs_focus_carousel.temp.objs[sId];

		if (sTo == "left") {
			sTo = s.index - 1;
		} else if (sTo == "right") {
			sTo = s.index + 1;
		}
		s.index = sTo;
		var iLeft = 0 - (s.index * s.width - s.every);
		$('#' + sId + ' ul').stop().animate({
			left : iLeft
		}, function() {

			if (s.index <= 0) {
				s.index = s.size - 2;

				zs_focus_carousel.left_to(s);
			} else if (s.index >= (s.size - 1)) {
				s.index = 1;

				zs_focus_carousel.left_to(s);
			}

		});

	}

};

// 调用自动播放代码
zs_focus_carousel.auto_play();

zs.f.define("zs/focus/zs_focus_carousel", [], zs_focus_carousel);
