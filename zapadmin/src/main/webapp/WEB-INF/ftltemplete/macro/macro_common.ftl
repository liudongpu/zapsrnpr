

<#macro m_common_html_js e_list>
	<#list e_list as e>
	<script type="text/javascript" src="${e}"></script>
	</#list>
</#macro>
<#macro m_common_html_css e_list >
    <#list e_list as e>
	<link type="text/css" href="${e}" rel="stylesheet">
	</#list>
</#macro>

<#macro m_common_html_script  e_info >

	<script type="text/javascript">
		${e_info}
	</script>

</#macro>



<#macro m_common_html_head   >

	<@m_common_html_js ["../resources/lib/jquery/jquery-last.min.js","../resources/lib/datepicker/WdatePicker.js","../resources/lib/jquery/jquery-plugins-zap.min.js","../resources/lib/bootstrap/js/bootstrap.min.js"]/>
	<@m_common_html_css ["../resources/lib/bootstrap/css/bootstrap.min.css","../resources/zapadmin/css/zab_base.css","../resources/zapweb/css/w.css"] />
	<@m_common_html_js ["../resources/zapjs/zapjs.js","../resources/zapjs/zapjs.zw.js","../resources/zapadmin/js/zapadmin.js"]/>

	<!--[if lte IE 7]> 
	<@m_common_html_css ["../resources/zapadmin/hack/zab_base_ie6.css"] />
	<![endif]-->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</#macro>
































