/*
* zapjs
* 基本功能及基本插件
* 其中zapjs.f 表示扩展功能
*/



var zapjs = {};

zapjs.fn = zapjs.prototype = {};

window.zapjs = zapjs;


/*
* jQuery Form Plugin; v20130711
* http://jquery.malsup.com/form/
* Copyright (c) 2013 M. Alsup; Dual licensed: MIT/GPL
* https://github.com/malsup/form#copyright-and-license
*/
zapjs.f = {

	// 提交参数
	ajaxsubmit : function(oElment, sAction, fSucceess, fError) {

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
	logdebug:function(o)
	{
		console.log(o);
	}

};
