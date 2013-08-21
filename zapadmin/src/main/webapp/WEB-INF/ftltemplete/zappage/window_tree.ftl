<ul class="easyui-tree" id="${b_page.getWebPage().getPageCode()}" ></ul>
<script>

$.get("../jsonchart/page_chart_v_za_role", function(result) {
	var x=zapadmin.tree_data(result);
			$('#${b_page.getWebPage().getPageCode()}').tree(
				{
					data : x,
					checkbox:true,
					lines:true
					
				});
		});

</script>


<input type="button" class="btn btn-smalll" value="确认选择" onclick="" />
