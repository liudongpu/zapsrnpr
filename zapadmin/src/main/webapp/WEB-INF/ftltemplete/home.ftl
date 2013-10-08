<#include "zapmacro/zapmacro_common.ftl" />
<#include "macro/macro_common.ftl" />
<#assign user_support=b_method.upClass("com.srnpr.zapweb.websupport.UserSupport")>
<#if user_support.checkLogin()>
	<#assign home_addhead="<meta http-equiv=\"Refresh\" content=\"0; url=manage/home\" />" />
<#else>
	<#assign home_addhead="<meta http-equiv=\"Refresh\" content=\"0; url=manage/login\" />" />
</#if>
<@m_common_page_head_base e_title=manage_home_title e_bodyclass="easyui-layout" e_addhead=home_addhead?default("") />


<@m_common_page_foot_base  />
