

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



