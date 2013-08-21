<br/>

<#assign a_page_field=b_page.upReqValue('zw_s_fieldname')>


<ul class="easyui-tree" id="${a_page_field}_tree_ul" ></ul>

<@m_common_html_script "require(['zapadmin/js/zapadmin_tree'],function(a){a.tree_window('"+a_page_field+"');});" />

<script>







</script>

<div class="zab_page_windowtree_buttons">

<input type="button" class="btn btn-smalll" value="确认选择" onclick="zapadmin_tree.tree_select('${a_page_field}')" />

</div>


