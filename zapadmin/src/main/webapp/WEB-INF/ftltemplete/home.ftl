<!DOCTYPE html>
<html class="zab_home_home_html">
<head>
<#include "zapmacro/zapmacro_common.ftl" /> <#include
"macro/macro_common.ftl" /> <@m_common_html_js
["resources/lib/jquery/jquery-last.min.js","resources/lib/bootstrap/js/bootstrap.min.js","resources/zapjs/zapjs.js","resources/zapjs/zapjs.zw.js","resources/zapadmin/js/zapadmin.js"]/>
<@m_common_html_css
["resources/lib/bootstrap/css/bootstrap.min.css","resources/zapadmin/css/zab_base.css","resources/zapweb/css/w.css"]
/>


<!--[if lte IE 7]> 
<@m_common_html_css ["resources/zapadmin/hack/zab_base_ie6.css"] />
<![endif]-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>zapadmin</title>

</head>
<body class="zab_home_home_body">

	<div class="zab_home_home_box">
	
		<#assign home_menu=b_method.upDataQuery("za_menu","menu_code","left(menu_code,13)='4699031300010'")>

		<div class="zab_home_home_top">
			<div class="w_left c_site">
				<a href=""><i class="icon-home  icon-white"></i>&nbsp;超级管理后台</a>



			</div>
			<div class="w_left c_nav">
				<ul class="w_ul">
					<li class="c_active"><a href=""
						onclick="zapadmin.top_menu(this)">后台首页</a></li> <#list home_menu as el
					> <#if el['parent_menu']=='469903130001'>
					<li><a href="#" onclick="zapadmin.top_menu(this,${el['menu_code']})">${el['menu_name']}</a></li>
					</#if> </#list>
				</ul>
			</div>


			<div class="w_right c_right">
				<a
					href="javascript:zapjs.zw.func_do('f99848c8f8c011e29b7a000c298b20x')"><i class="icon-refresh  icon-white"></i></a>
		管理员
			</div>




		</div>

		<div class="zab_home_home_main">

			<div class=" zab_home_home_left ">
			
				<div class="c_box w_display">
				<input type="text" placeholder="快捷码">
				</div>
			
				<div>
					<ul>
					<#list home_menu as el > <#if el['menu_code']?length==16>
					</ul>
				</div>
				
				<div id="home_menu_box_${el["menu_code"]}" class="c_item  <#if el_index!=0>w_display</#if>  ">
				
					<div class="c_title">${el["menu_name"]}</div>
				
					<ul class="w_ullist">
						<#elseif el['menu_code']?length==20>
						<li class="c_header">+&nbsp;${el["menu_name"]}</li> <#elseif
						el['menu_code']?length==24>
						<li><a  href="${el['menu_link']}" onclick="zapadmin.menu_click(this)" target="main_iframe">${el["menu_name"]}</a></li>
						</#if> </#list>
					</ul>
				</div>


				<div class="c_bottom">CopyRight 2013</div>

			</div>
			<div class="zab_home_home_right">
				<iframe src="page/page_chart_v_zw_view" id="main_iframe"
					name="main_iframe" width="100%" height="100%" frameborder="0"
					 />
			</div>
		</div>



	</div>

	</div>
</body>
</html>
