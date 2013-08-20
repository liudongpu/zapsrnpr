
<@m_common_html_js ["zapadmin/js/zapadmin_tree.js"] />

<div class="w_left zw_page_tree_left">


<@m_common_html_script "$(document).ready(function(){zapadmin_tree.tree_init()});" />


<@m_zapmacro_common_format_map_hidden  b_page.getReqMap() "zw_page_tree_" />

<@m_zapmacro_common_set_operate   b_page.getWebPage().getPageOperate() "116001016"  "btn btn-small" />
  

	<div class="zw_page_tree_box">
	
	<ul class="easyui-tree" id="zw_page_common_tree" ></ul>
	</div>
</div>



<div class="zw_page_tree_right" id="zw_page_tree_right">





</div>

