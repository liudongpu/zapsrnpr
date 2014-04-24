<#-- 系统版本号 -->
<#assign a_macro_common_system_version="2.0.0.1">
<#-- 资源附加后缀版本 -->
<#assign a_macro_common_resources_version="?v="+a_macro_common_system_version >
<#-- 资源文件路径 -->
<#assign a_macro_common_resources_link="../" >
<#-- 项目特殊样式 -->
<#assign a_macro_common_resources_thems_js=["fileconcat/js-autoconcat.js"] >
<#assign a_macro_common_resources_thems_css=["fileconcat/css-autoconcat.css"] >








<#macro m_common_html_js e_list>
	<#list e_list as e>
	<script type="text/javascript" src="${a_macro_common_resources_link}resources/${e}${a_macro_common_resources_version}"></script>
	</#list>
</#macro>
<#macro m_common_html_css e_list >
    <#list e_list as e>
	<link type="text/css" href="${a_macro_common_resources_link}resources/${e}${a_macro_common_resources_version}" rel="stylesheet">
	</#list>
</#macro>

<#macro m_common_html_script  e_info >

	<script type="text/javascript">
		${e_info}
	</script>

</#macro>



<#macro m_common_html_require   >

<@m_common_html_js ["zapjs/zapjs.js","lib/require/require.js"]/>
</#macro>



<#macro m_common_html_test   >

<@m_common_html_css ["lib/qunit/qunit-last.css"] />
<@m_common_html_js ["zapjs/zapjs.js","lib/require/require.js","lib/qunit/qunit-last.js"]/>
</#macro>




<#macro m_common_html_head   >


	<@m_common_html_js a_macro_common_resources_thems_js />
	
	<@m_common_html_css    a_macro_common_resources_thems_css />
	<!--[if lte IE 7]> 
	<@m_common_html_css ["zapadmin/hack/zab_base_ie6.css"] />
	<![endif]-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</#macro>




<#macro m_common_page_head_common  e_title="zapadmin" e_addhead="" e_bodyclass="" >
<!DOCTYPE html>
<html class="zab_home_home_html">
<head>
<@m_common_html_head />
<title>${e_title}</title>
${e_addhead}
</head>
<body <#if e_bodyclass??>class="${e_bodyclass}"</#if>>
</#macro>



<#macro m_common_page_head_base  e_title="zapadmin" e_addhead="" e_bodyclass="" >
<!DOCTYPE html>
<html class="zab_home_home_html">
<head>
<title>${e_title}</title>
${e_addhead}
</head>
<body <#if e_bodyclass??>class="${e_bodyclass}"</#if>>
</#macro>

<#macro m_common_page_foot_base >
</body>
</html>
</#macro>



























