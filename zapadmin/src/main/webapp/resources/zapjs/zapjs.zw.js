/*
 * zapjs.zw

 */

zapjs.zw = {

	temp : {
		regex : {
			r_469923180001 : {
				reg : "",
				name : "无"
			},
			r_469923180002 : {
				reg : "+",
				name : "非空"
			},
			r_469923180003 : {
				reg : "^\d{1,10}$",
				name : "数字1-10位"
			},
			r_469923180004 : {
				reg : "-^[a-zA-Z0-9_\.]+@[a-zA-Z0-9-]+[\.a-zA-Z]+$",
				name : "邮箱地址(可为空)"
			},
			r_469923180005 : {
				reg : "^.{6,20}$",
				name : "6-20位字符"
			},
			r_469923180006 : {
				reg : "^0\.([1-9][0-9]?|[0-9][1-9])$",
				name : "大于0小于1的两位小数"
			},
			r_469923180007 : {
				reg : "+^[0-9]+(.[0-9]{2})?$",
				name : "最多两位小数"
			}
		}
	},

	modal_show : function(oSet) {
		top.zapjs.f.modal(oSet);
	},

	modal_process : function() {
		zapjs.zw.modal_show({
			content : '<div class="w_loading_small"></div>',
			flagbutton : false
		});
	},

	window_show : function(sContent) {
		zapjs.f.window_box({
			content : sContent
		});
	},

	// 添加函数调用
	func_add : function(oElm) {
		zapjs.zw.func_call(oElm);
	},

	// 修改函数调用
	func_edit : function(oElm) {

		zapjs.zw.func_call(oElm);

	},

	// 删除函数调用
	func_delete : function(oElm, sUid) {

		zapjs.zw.func_do(oElm, null, {
			zw_f_uid : sUid
		});

	},

	/*
	 * APi调用类
	 */
	api_call : function(sTarget, oData, fCallBack) {

		var defaults = {
			varsion : 1,
			api_target : sTarget,
			api_key : 'jsapi'
		};

		oData = $.extend({}, defaults, oData || {});

		zapjs.f.ajaxjson("../jsonapi/" + sTarget, oData, function(data) {
			fCallBack(data);
		});

	},

	// 提交操作
	func_call : function(oElm) {
		
		var oForm = $(oElm).parents("form");
		if (zapjs.zw.func_regex(oForm)) {
			zapjs.zw.modal_process();
			zapjs.f.ajaxsubmit(oForm, "../func/" + $(oElm).attr('zapweb_attr_operate_id'), zapjs.zw.func_success, zapjs.zw.func_error);
		}
	},

	//验证form
	func_regex : function(oForm) {
		var bFlag = true;

		$(oForm).find("[zapweb_attr_regex_id]").each(function(n, el) {

			var sRegId = $(el).attr("zapweb_attr_regex_id");

			if (sRegId != "" && sRegId != "469923180001" && sRegId.indexOf('46992318') > -1) {

				var rv = zapjs.zw.temp.regex['r_' + sRegId];
				if (rv) {

					var sRegText = rv.reg;

					var sErrorMsg = "";

					if (bFlag && sRegText) {
						//bFlag = false;

						if (sRegText.indexOf('+') == 0) {
							sRegText = sRegText.substr(1);

							if ($(el).val() == "") {
								bFlag = false;
								sErrorMsg = "不能为空";
							}

						}

					}
					if (bFlag && sRegText) {

						var myregex = new RegExp(sRegText);
						// 创建正则表达式
						if (!myregex.test($(el).val())) {
							bFlag = false;
							sErrorMsg = rv.name;
						}

					}
					
					
					if(!bFlag)
					{
						var sTitle=$(el).parents('.control-group').find('.control-label').text();
						
						zapjs.zw.modal_show({content:'字段【'+sTitle+'】'+sErrorMsg});
						return bFlag;
						
					}
					
					

				}

			}

		});

		return bFlag;
	},

	func_do : function(oElm, sOperate, data) {

		zapjs.zw.modal_process();

		if (!sOperate) {
			sOperate = $(oElm).attr('zapweb_attr_operate_id');
		}

		if (!data) {
			data = {};
		}

		zapjs.f.ajaxjson("../func/" + sOperate, data, function(data) {
			zapjs.zw.func_success(data);
		});
	},

	up_json : function(sPageCode, dataMap, fCallBack) {
		zapjs.f.ajaxjson("../jsonchart/" + sPageCode, dataMap, function(o) {
			fCallBack(o);
		});

	},
	// 执行成功
	func_success : function(o) {

		switch (o.resultType) {
			case "116018010":
				eval(o.resultObject);
				break;
			default:
				// alert(o.resultMessage);

				if (o.resultCode == "1") {

					if (o.resultMessage == "") {
						o.resultMessage = "操作成功";
					}

					zapjs.zw.modal_show({
						content : o.resultMessage,
						okfunc : 'zapjs.f.autorefresh()'
					});

				} else {
					zapjs.zw.modal_show({
						content : o.resultMessage
					});
				}

				break;
		}

	},

	func_export : function() {

		//zapjs.f.tourl(zapjs.f.upurl().replace("/page/", "/export/"));

		var sUrl = zapjs.f.upurl().replace("/page/", "/export/");

		var aHtml = [];
		aHtml.push('<div class="w_p_20">');
		aHtml.push('<a class="btn" target="_blank" href="' + sUrl + '">导出当前页</a>&nbsp;&nbsp;&nbsp;&nbsp;');
		aHtml.push('<a class="btn" target="_blank" href="' + zapjs.f.urlreplace(sUrl, zapjs.c.web_paginaion + 'size', -1) + '">导出所有页</a>');
		aHtml.push('</div>');

		zapjs.f.window_box({
			content : aHtml.join(''),
			width : 400,
			height : 150
		});

	},

	func_error : function(o) {
		alert('系统出现错误，请联系技术，谢谢！');
	},
	func_inquire : function(oElm) {

		var queryString = $(oElm).parents("form").formSerialize();

		zapjs.f.tourl(this.url_inquire(queryString));
		// $(oElm).parents("form").submit();
	},
	url_inquire : function(sQueryString) {
		var sSplit = sQueryString.split('&');

		var sUrl = zapjs.f.upurl();

		for (var i = 0, j = sSplit.length; i < j; i++) {
			var sEq = sSplit[i].split('=');
			if (sEq[1] != "" || sUrl.indexOf(sEq[0]) > -1) {
				sUrl = zapjs.f.urlreplace(sUrl, sEq[0], sEq[1]);
			}

		}

		sUrl = zapjs.f.urlreplace(sUrl, zapjs.c.web_paginaion + 'index', '');
		sUrl = zapjs.f.urlreplace(sUrl, zapjs.c.web_paginaion + 'count', '');
		return sUrl;

	},

	upExtend : function(sId, sStart) {
		return $('#' + sId).attr(zapjs.c.web_extend + sStart);
	},
	// 编辑器提交时调用扩展
	editorsubmit : function() {
		for (instance in CKEDITOR.instances) {
			CKEDITOR.instances[instance].updateElement();
		}
		return true;
	},

	// 编辑器显示
	editor_show : function(sFieldName, sUploadUrl) {

		zapjs.f.setdomain();

		var slib = "lib/ckeditor/";
		if (zapjs.f.browser('ie6') == true) {
			slib = "lib/hack/ckeditor3/";
		}

		zapjs.f.require([slib + 'ckeditor'], function(a) {
			zapjs.f.require([slib + 'adapters/jquery'], function(c) {
				$('#' + sFieldName).ckeditor({
					filebrowserImageUploadUrl : sUploadUrl + 'editor'
				});
			});
		});
		zapjs.e('zapjs_e_zapjs_f_ajaxsubmit_submit', zapjs.zw.editorsubmit);

	},

	// 用户登录成功
	login_sucess : function(oUserInfo) {
		var sCookie = oUserInfo.cookieUser;

		if (sCookie) {
			zapjs.f.cookie(zapjs.c.cookie_user, sCookie);
			zapjs.f.tourl($('#zapjs_zw_login_sucess_target').val());

		}

	},
	login_out : function(sUrl) {
		zapjs.f.cookie(zapjs.c.cookie_user, '');
		zapjs.f.tourl(sUrl);
	}
};

if ( typeof define === "function" && define.amd) {
	define("zapjs/zapjs.zw", function() {
		return zapjs.zw;
	});
}
