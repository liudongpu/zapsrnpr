/*
 * zapjs.zw

 */


zapjs.zw = {

	func_add : function(oElm) {
		zapjs.f.ajaxsubmit($(oElm).parents("form"), "../func/"+$(oElm).attr('zapweb_attr_operate_id'), zapjs.zw.func_success, zapjs.zw.func_error);
	},
	func_success : function(o) {
		alert(o.resultMessage);
	},
	func_error : function(o) {
		alert('系统出现错误，请联系技术，谢谢！');
	},
	func_inquire:function(oElm)
	{
		$(oElm).parents("form").submit();
	}

};