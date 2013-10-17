/*
 * zapjs
 * 基本功能及基本插件
 * 其中zapjs.f 表示扩展功能
 */

var zapjs = {
	// 配置
	c : {
		web_paginaion : 'zw_p_',
		web_field : 'zw_f_',
		web_extend : 'zw_e_',
		field_attr : 'zapweb_attr_',
		main_iframe : 'main_iframe',
		split : '|',
		path_resources : '../resources/',
		cookie_base : 'cookie-zw-',
		cookie_user : 'userinfo',
		extend : {},
		// 是否调试模式
		debug : true
	},
	// 注册函数专用调用 注册该函数的方法需要返回true/false
	// 调用注册方法为zapjs.f.callextend(sId);该参数会返回true/false
	e : function(sId, fCall) {
		if (!zapjs.c.extend[sId]) {
			zapjs.c.extend[sId] = [];
		}

		zapjs.c.extend[sId].push(fCall);

	},
	// 调试模式的话则输出日志
	d : function(oLog) {
		if (zapjs.c.debug) {

			if (window.console && window.console.log) {
				console.log(zapjs.f.tojson(oLog));
			}

		}
	}
};

zapjs.fn = zapjs.prototype = {};

window.zapjs = zapjs;

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
			type : "post",
			success : function(o) {
				fSucceess(o);
			},
			error : function(o) {
				fError(o);
			}
		};

		zapjs.f.callextend("zapjs_e_zapjs_f_ajaxsubmit_submit");

		$(oElment).ajaxSubmit(options);

	},
	ajaxjson : function(sTarget, data, fCallBack) {
		
		
		var options = {
			
			dataType: "json",
		  url: sTarget,
		  type: "POST",
		  data: data,
		  success: fCallBack,
		   error: function (msg) {
		  	
		              
						zapjs.f.message('系统异步调用出现错误，请联系技术，谢谢！');
						
		            }
		};
		
		
				$.ajax(options);

		
		/*
		$.getJSON(sTarget, data, fCallBack).fail(function() {
			alert('系统异步调用出现错误，请联系技术，谢谢！');
		});
		*/
	},

	setdomain : function() {

	},

	// Cookie操作
	cookie : function(key, value, options) {

		// 判断如果是写操作 写到根目录
		if (value !== undefined) {
			if (!options) {
				options = {};
			}

			options.path = "/";
			if (!options.expires)
				options.expires = 30;
		}

		return $.cookie(zapjs.c.cookie_base + key, value, options);
	},

	ready : function(f) {
		$(f);
	},

	require : function(aNeeds, f) {

		require(aNeeds, f);

	},
	// 获取当前主域
	updomain : function(url) {

		var host = "null";
		if ( typeof url == "undefined" || null == url)
			url = window.location.href;
		var regex = /.*\:\/\/([^\/|:]*).*/;
		var match = url.match(regex);
		if ( typeof match != "undefined" && null != match) {
			host = match[1];
		}
		if ( typeof host != "undefined" && null != host) {
			var strAry = host.split(".");
			if (strAry.length > 1) {
				host = strAry[strAry.length - 2] + "." + strAry[strAry.length - 1];
			}
		}

		return host;

	},
	// 调用扩展
	callextend : function(sId) {
		var bReturn = true;

		if (zapjs.c.extend[sId]) {
			for (var p in zapjs.c.extend[sId]) {
				var bFlag = zapjs.c.extend[sId][p]();
				if (!bFlag) {
					bReturn = false;
				}
			}
		}
		;

		return bReturn;
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

	/*
	 * 自动刷新  如果有子页面  则刷新iframe
	 */
	autorefresh : function() {
		if (zapjs.f.exist('main_iframe')) {
			document.getElementById("main_iframe").contentWindow.zapjs.f.tourl();
		} else {
			zapjs.f.tourl();
		}
	},

	replace : function(sSource, sOld, sNew) {

		sSource = sSource.replace(sOld, sNew);

		return sSource;

	},

	stringformat : function(sInput) {

		var args = arguments;

		if (args && args.length >= 2) {
			for (var i in args) {
				if (i > 0) {
					sInput = zapjs.f.replace(sInput, '{' + (i - 1) + '}', args[i]);
				}
			}
		}

		return sInput;

	},

	tourl : function(sUrl) {

		if (!sUrl) {
			sUrl = location.href;
		}

		location.href = sUrl;
	},
	// 判断是否存在元素
	exist : function(sId) {
		return document.getElementById(sId) ? true : false;

	},
	// 浏览器信息
	browser : function(sBrowser) {
		var userAgent = navigator.userAgent.toLowerCase();
		var bs = {
			version : (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [
			0, '0' ])[1],
			safari : /webkit/.test(userAgent),
			opera : /opera/.test(userAgent),
			msie : /msie/.test(userAgent) && !/opera/.test(userAgent),
			mozilla : /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
		};

		if (sBrowser) {

			if (sBrowser == "ie6") {
				try {
					return bs.msie && bs.version == "6.0";
				} catch (ex) {

				}

			}

		}

		return bs;

	},
	window_close : function(sId) {

		zapjs.f.window_box({
			id : sId,
			close : true
		});

	},
	window_box : function(options) {
		var defaults = {
			id : 'zapjs_f_id_window_box',
			content : '',
			width : 600,
			height : 400,
			title : '&nbsp;',
			close : false
		};

		var s = $.extend({}, defaults, options || {});

		if (s.close) {

			$('#' + s.id).window('close');

			return;
		}

		if (!zapjs.f.exist(s.id)) {

			var sText = '<div id="' + s.id + '"  class="easyui-window" title="' + s.title + '"  data-options="iconCls:\'icon-save\',modal:true"></div>';

			$(document.body).append(sText);
		}

		$('#' + s.id).html(s.content);

		$('#' + s.id).window({
			width : s.width,
			height : s.height,
			modal : true,
			closed : false
		});

		if (s.url) {
			$('#' + s.id).window('refresh', s.url);
		}

	},

	//提示消息
	message : function(sContent, okfunc) {
		zapjs.f.modal({
			content : sContent,
			okfunc : okfunc
		});
	},

	modal : function(options) {

		var defaults = {
			title : '提示消息',
			content : '',
			flagbutton : true,
			oktext : '确认',
			canceltext : '取消',
			okfunc : '',
			width : 400,
			id : 'zapjs_f_id_modal_box',
			cancelfunc : ''
		};
		var s = $.extend({}, defaults, options || {});

		/*
		 if (!($('#zapjs_f_id_modal_box').length > 0)) {
		 var sModel = '<div id="zapjs_f_id_modal_box" class="modal hide fade"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + '<h3>' + s.title + '</h3></div><div class="modal-body">' + '<p>' + '</p>' + '</div>' + '<div class="modal-footer">' + '</div></div>';
		 $(document.body).append(sModel);

		 }

		 $('#zapjs_f_id_modal_box h3').html(s.title);
		 $('#zapjs_f_id_modal_box p').html(s.content);

		 if (s.flagbutton) {
		 var aFuncHtml = [];
		 if (s.okfunc) {
		 aFuncHtml.push('<a  class="btn btn-primary" data-dismiss="modal" onclick="' + s.okfunc + '" aria-hidden="true">确认</a>');

		 }

		 aFuncHtml.push('<a  class="btn btn-primary" data-dismiss="modal" onclick="' + s.cancelfunc + '" aria-hidden="true">关闭</a>');

		 $('#zapjs_f_id_modal_box .modal-footer').html(aFuncHtml.join(''));

		 }

		 $('#zapjs_f_id_modal_box').modal('show');

		 */

		if (zapjs.f.exist(s.id)) {

			$('#' + s.id).dialog('close');
			$('#' + s.id).remove();
		}

		var sModel = '<div id="' + s.id + '" ></div>';
		$(document.body).append(sModel);

		$('#' + s.id).html('<div class="w_p_20">' + s.content + '</div>');

		var aButtons = [];

		if (s.flagbutton) {
			aButtons.push({
				text : s.oktext,
				handler : function() {

					$('#' + s.id).dialog('close');
					$('#' + s.id).remove();
					if (s.okfunc) {
						eval(s.okfunc);

					}

				}
			});
		}

		$('#' + s.id).dialog({
			title : s.title,
			width : s.width,
			resizable : true,
			closed : false,
			cache : false,
			modal : true,
			buttons : aButtons
		});

	},

	upset : function(sSet, sKey) {
		return zapjs.f.urlget(sKey, '?' + sSet);
	},

	/*
	 * zapjs 基本功能及基本插件 其中zapjs.f 表示扩展功能 @param sKey Url参数 @param sUrl Url链接
	 * 如果传空或者不传时为当前链接 @return 参数的值
	 */
	urlget : function(sKey, sUrl) {

		var sReturn = "";

		if (!sUrl) {
			sUrl = zapjs.f.upurl();
			if (sUrl.indexOf('?') < 1) {
				sUrl = sUrl + "?";
			}
		}

		var sParams = sUrl.split('?')[1].split('&');

		for (var i = 0, j = sParams.length; i < j; i++) {

			var sKv = sParams[i].split("=");
			if (sKv[0] == sKey) {
				sReturn = sKv[1];
				break;
			}
		}

		return sReturn;

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

		for (var i = 0, j = sParams.length; i < j; i++) {

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
			sParams.splice(bFlagCon, 1);
		}

		return sUrl.split('?')[0] + "?" + sParams.join("&");
	}
};

if ( typeof define === "function" && define.amd) {
	define("zapjs/zapjs", [], function() {
		return zapjs;
	});
}
