
var zapadmin_tree = {
	temp : {

	},
	tree_select:function(oTag)
	{
		//alert($(oTag).attr('zw_e_tree_key'));
		 var node = $('#zw_page_common_tree').tree('getSelected');
         if (node){
             var s = node.text;
             if (node.attributes){
                 s += ","+node.attributes.p1+","+node.attributes.p2;
             }
             alert(s);
         }
		
		
	},
	
	tree_add : function(oTag) {
		
		 var node = $('#zw_page_common_tree').tree('getSelected');
         if (node){
             var s = node.text;
             if (node.attributes){
                 s += ","+node.attributes.p1+","+node.attributes.p2;
             }
             alert(s);
         }
		
		
		$.get($('#zw_page_tree_zw_s_addpage').val(), function(result) {
			$('#zw_page_tree_right').html(result);
		});
	}

};
