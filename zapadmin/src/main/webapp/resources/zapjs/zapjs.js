/*
 * zapjs
 * 基本功能及基本插件
 * 其中zapjs.f 表示扩展功能
 */

var zapjs = {};

zapjs.fn = zapjs.prototype = {};

window.zapjs = zapjs;

// 配置
zapjs.c = {};

/*
 * f
 */
zapjs.f = {

	// 提交参数
	ajaxsubmit : function(oElment, sAction, fSucceess, fError) {

		if (sAction == "") {

		}
		var options = {
			url : sAction,
			success : function(o) {
				fSucceess(o);
			},
			error : function(o) {
				fError(o);
			}
		};

		$(oElment).ajaxSubmit(options);

	},
	// 转成json格式
	tojson : function(oObj) {
		return $.toJSON(oObj);
	},
	// 执行json
	evaljson : function(sJson) {
		return $.evalJSON(sJson);
	},
	logdebug : function(o) {
		console.log(o);
	},
	upurl : function() {
		return window.location.href;
	},

	tourl : function(sUrl) {
		location.href = sUrl;
	},

	modal : function(options) {

		var defaults = {
			title:'提示消息',
			content:'',
			flagbutton : true,
			oktext : '确认',
			canceltext : '取消',
			okfunc : '',
			cancelfunc : ''
		};
		var s = $.extend({}, defaults, options || {});

		if (!($('#zapjs_f_id_modal_box').length > 0)) {
			var sModel = '<div id="zapjs_f_id_modal_box" class="modal hide fade"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
					+ '<h3>'
					+ s.title
					+ '</h3></div><div class="modal-body">'
					+ '<p>'
					+ s.content
					+ '</p>'
					+ '</div>'
					+ '<div class="modal-footer">' + '</div></div>';
			$(document.body).append(sModel);

		}

		$('#zapjs_f_id_modal_box h3').html(s.title);
		$('#zapjs_f_id_modal_box p').html(s.content);

		if (s.flagbutton) {
			var aFuncHtml = [];
			if (s.okfunc) {
				aFuncHtml
						.push('<a  class="btn btn-primary" data-dismiss="modal" onclick="'
								+ s.okfunc + '" aria-hidden="true">确认</a>');

			}

			aFuncHtml.push('<a  class="btn" data-dismiss="modal" onclick="'
					+ s.cancelfunc + '" aria-hidden="true">关闭</a>');

			$('#zen_page_model_box .modal-footer').html(aFuncHtml.join(''));

		}

		$('#zapjs_f_id_modal_box').modal('show');

	},

	// url替换 如果没有的话会自动添加
	urlreplace : function(sUrl, sKey, sValue) {
		if (sUrl == "") {
			sUrl = zapjs.f.upurl();
		}
		if (sUrl.indexOf('?') < 1) {
			sUrl = sUrl + "?";
		}

		var sParams = sUrl.split('?')[1].split('&');
		var bFlagCon = false;

		var sAddStr = sKey + "=" + sValue;

		for ( var i = 0, j = sParams.length; i < j; i++) {

			var sKv = sParams[i].split("=");
			if (sKv[0] == sKey) {
				bFlagCon = true;
				sParams[i] = sAddStr;
				break;
			}
		}

		if (!bFlagCon) {
			sParams.push(sAddStr);
		}

		return sUrl.split('?')[0] + "?" + sParams.join("&");
	}

};
