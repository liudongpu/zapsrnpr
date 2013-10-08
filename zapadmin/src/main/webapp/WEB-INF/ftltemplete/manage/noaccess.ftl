<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />

<@m_common_page_head_base 
	e_title="没有权限" 
/>

<script  type="text/javascript">


if(top.zapjs)
{
	top.zapjs.zw.login_out('../manage/logout');
}
else
{
	top.location.href="../manage/logout";
}

</script>
	权限验证失败，如果有疑问请联系管理员！
<@m_common_page_foot_base  />
