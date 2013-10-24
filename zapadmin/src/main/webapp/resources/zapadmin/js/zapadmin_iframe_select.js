var zapadmin_iframe_select = {
	init : function() {

		zapadmin_iframe_select.product_show();
		$('.window_iframe_box').show();
		
		$('.window_iframe_btn').click(zapadmin_iframe_select.product_ok);

	},
	product_show:function()
	{
		$('.zw_page_common_data table tr').each(function(n, el) {

			if (n == 0) {

			} else {
				var oThis = $(el).children().eq(0);
				var sVal = oThis.text().trim();

				oThis.html('<input type="checkbox" value="' + sVal + '"/>');
			}

		});
	},
	product_check:function()
	{
		
	},
	product_ok:function()
	{
		parent.zs_template_set.close_product();
	}
	
	
	
};

if ( typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_iframe_select", ["zapadmin/js/zapadmin_chartajax"],function() {
		return zapadmin_iframe_select;
	});
}
