/*
 * zapjs
 * 基本功能及基本插件
 * 其中zapjs.f 表示扩展功能
 */

var zapjs = {};

zapjs.fn = zapjs.prototype = {};

window.zapjs = zapjs;

// 配置
zapjs.c = {
		
		web_paginaion:'zw_p_',
		main_iframe:'main_iframe'
		
};

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
			title : '提示消息',
			content : '',
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

			aFuncHtml
					.push('<a  class="btn btn-primary" data-dismiss="modal" onclick="'
							+ s.cancelfunc + '" aria-hidden="true">关闭</a>');

			$('#zapjs_f_id_modal_box .modal-footer').html(aFuncHtml.join(''));

		}

		$('#zapjs_f_id_modal_box').modal('show');

	},

	// url替换 如果没有的话会自动添加 如果值为空则自动删除
	urlreplace : function(sUrl, sKey, sValue) {
		if (sUrl == "") {
			sUrl = zapjs.f.upurl();
		}
		if (sUrl.indexOf('?') < 1) {
			sUrl = sUrl + "?";
		}

		var sParams = sUrl.split('?')[1].split('&');
		var bFlagCon = -1;

		var sAddStr = sKey + "=" + sValue;

		for ( var i = 0, j = sParams.length; i < j; i++) {

			var sKv = sParams[i].split("=");
			if (sKv[0] == sKey) {
				bFlagCon = i;
				sParams[i] = sAddStr;
				break;
			}
		}

		if (bFlagCon == -1 && sValue != "") {
			sParams.push(sAddStr);
		} else if (bFlagCon > -1 && sValue == "") {
			sParams.splice(bFlagCon,1);
		}

		return sUrl.split('?')[0] + "?" + sParams.join("&");
	}

};
