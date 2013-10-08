<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />
<#assign addhead="<meta http-equiv=\"Refresh\" content=\"0; url=login\" />" />
<@m_common_page_head_base 
	e_title="后台登陆界面" 
	e_addhead=addhead
/>

<#assign user_support=b_method.upClass("com.srnpr.zapweb.websupport.UserSupport")>
${user_support.logout()}

<@m_common_page_foot_base  />
