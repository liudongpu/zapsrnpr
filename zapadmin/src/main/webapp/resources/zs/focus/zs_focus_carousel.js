var zs_focus_carousel = {

	temp : {

		objs : {},
		//是否已添加过定时器 防止多个调用时的多次添加定时
		flaginterval:false
	},
	play : function(options) {

		// alert(sId);
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

		s.every = (parseInt($(document.body).width()) - s.width) / 2;

		var sId = s.id;

		var aImages = $('#' + sId).val().split('|');

		if (aImages.length == 0) {
			return false;
		}

		aImages.splice(0, 0, aImages[aImages.length - 1]);
		aImages.push(aImages[1]);
		s.pics = aImages;
		s.size = s.pics.length;
		zs_focus_carousel.temp.objs[sId] = s;
		var aHtml = [];

		aHtml.push('<div class="c_info"><div class="c_box"><ul id="' + sId
				+ '_ul">');
		for ( var p in aImages) {
			aHtml.push('<li id="' + sId + '_li_' + p
					+ '"><div class="c_item"><img src="' + aImages[p]
					+ '"/></div></li>');
		}
		aHtml
				.push('</ul></div><div class="c_left c_nav" onclick="zs_focus_carousel.move_to(\''
						+ sId
						+ '\',\'left\')"><div class="c_button"></div></div><div class="c_right c_nav" onclick="zs_focus_carousel.move_to(\''
						+ sId
						+ '\',\'right\')"><div class="c_button"></div></div></div>');

		var eParent = $('#' + sId).parent();

		eParent.find('.c_info').remove();

		$('#' + sId).after(aHtml.join(''));

		eParent.find('.c_nav').width(s.every);

		eParent.find('.c_info').hover(function() {
			s.flagauto = false;
		}, function() {
			s.flagauto = true;
		});

		// zs_focus_carousel.move_to(sId,1);
		$('#' + sId + '_ul').css('left',
				(0 - (s.index * s.width - s.every)) + 'px');

		if (s.timer > 0&&zs_focus_carousel.temp.flaginterval==false) {
			zs_focus_carousel.temp.flaginterval=true;
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

	move_to : function(sId, sTo) {

		var s = zs_focus_carousel.temp.objs[sId];

		if (sTo == "left") {
			sTo = s.index - 1;
		} else if (sTo == "right") {
			sTo = s.index + 1;
		}
		s.index = sTo;
		var iLeft = 0 - (s.index * s.width - s.every);
		$('#' + sId + '_ul').stop().animate(
				{
					left : iLeft
				},
				function() {

					if (s.index <= 0) {
						s.index = s.size - 2;
						$('#' + sId + '_ul').css('left',
								(0 - (s.index * s.width - s.every)) + 'px');
					} else if (s.index >= (s.size - 1)) {
						s.index = 1;
						$('#' + sId + '_ul').css('left',
								(0 - (s.index * s.width - s.every)) + 'px');
					}

				});

	}

};

zs.f.define("zs/focus/zs_focus_carousel", [], zs_focus_carousel);
