zapjs.zw = {

	func_add : function(oElm) {
		zapjs.f.ajaxsubmit($(oElm).parents("form"), "../func/add/page_add_v_cc_createtask", zapjs.zw.func_success, zapjs.zw.func_error);
	},

	func_success : function(o) {
		alert(o);
	},

	func_error : function(o) {
		alert('错误啊');

	}

};