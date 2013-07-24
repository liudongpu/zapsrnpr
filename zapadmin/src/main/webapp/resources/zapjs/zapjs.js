/*
 * zapjs
 * 基本功能及基本插件
 * 其中zapjs.f 表示扩展功能
 */

var zapjs = {};

zapjs.fn = zapjs.prototype = {};

window.zapjs = zapjs;

/*
 *f
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
	}

};
