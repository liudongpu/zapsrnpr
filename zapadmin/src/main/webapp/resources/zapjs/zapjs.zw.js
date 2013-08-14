/*
 * zapjs.zw

 */

zapjs.zw = {

	modal_show : function(oSet) {
		top.zapjs.f.modal(oSet);
	},

	// 添加函数调用
	func_add : function(oElm) {
		zapjs.zw.func_call(oElm);
	},

	// 修改函数调用
	func_edit : function(oElm) {

		zapjs.zw.func_call(oElm);

	},

	// 提交操作
	func_call : function(oElm) {
		zapjs.f.ajaxsubmit($(oElm).parents("form"), "../func/"
				+ $(oElm).attr('zapweb_attr_operate_id'),
				zapjs.zw.func_success, zapjs.zw.func_error);
	},

	func_do : function(sOperate) {
		$.getJSON("func/" + sOperate, function(data) {
			zapjs.zw.func_success(data);
		});
	},

	func_success : function(o) {

		switch (o.resultType) {
		case "116018010":
			eval(o.resultObject);
			break;
		default:
			// alert(o.resultMessage);
			zapjs.zw.modal_show({
				content : o.resultMessage
			});
			break;
		}

	},

	func_export : function() {

		zapjs.f.tourl(zapjs.f.upurl().replace("/page/", "/export/"));
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

		for ( var i = 0, j = sSplit.length; i < j; i++) {
			var sEq = sSplit[i].split('=');
			if (sEq[1] != ""||sUrl.indexOf(sEq[0])>-1) {
				sUrl = zapjs.f.urlreplace(sUrl, sEq[0], sEq[1]);
			}

		}
		
		sUrl=zapjs.f.urlreplace(sUrl, zapjs.c.web_paginaion+'index','');
		sUrl=zapjs.f.urlreplace(sUrl, zapjs.c.web_paginaion+'count','');
		return sUrl;

	}

};