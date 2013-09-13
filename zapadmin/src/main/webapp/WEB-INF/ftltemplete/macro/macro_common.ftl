

<#macro m_common_html_js e_list>
	<#list e_list as e>
	<script type="text/javascript" src="../resources/${e}"></script>
	</#list>
</#macro>
<#macro m_common_html_css e_list >
    <#list e_list as e>
	<link type="text/css" href="../resources/${e}" rel="stylesheet">
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

	<@m_common_html_js ["lib/jquery/jquery-last.min.js","lib/require/require.js","zapjs/zapjs.js","zapjs/zapjs.zw.js","zapadmin/js/zapadmin.js"]/>
	<@m_common_html_js ["lib/jquery/jquery-plugins-zap.min.js","lib/bootstrap/js/bootstrap.min.js","lib/easyui/jquery.easyui.min.js"]/>
	<@m_common_html_css ["lib/bootstrap/css/bootstrap.min.css","lib/easyui/themes/bootstrap/easyui.css","zapadmin/css/zab_base.css","zapweb/css/w.css"] />
	

	<!--[if lte IE 7]> 
	<@m_common_html_css ["zapadmin/hack/zab_base_ie6.css"] />
	<![endif]-->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</#macro>
































