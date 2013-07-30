/*
 * zapjs
 * 基本功能及基本插件
 * 其中zapjs.f 表示扩展功能
 */

var zapjs = {};

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
	upurl:function()
	{
		return window.location.href;
	},
	
	tourl:function(sUrl)
	{
		location.href=sUrl;
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

		return sUrl.split('?')[0]+"?"+sParams.join("&");
	}

};
