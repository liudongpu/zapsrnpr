var zapadmin_apitest = {

	init : function() {
		require([ 'zapadmin/js/zapadmin_tree' ], function(a) {
			zapadmin_tree.tree_show = function(oData) {
				var x = zapadmin.tree_data(oData);

				$('#zw_page_common_tree').tree({
					data : x,
					onClick : function(node) {
						alert('');
					}

				});
			};

			a.tree_init();
		});
	}

};

if (typeof define === "function" && define.amd) {
	define("zapadmin/js/zapadmin_apitest", function() {
		return zapadmin_apitest;
	});
}