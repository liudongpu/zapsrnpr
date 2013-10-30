<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />
<@m_common_page_head_common e_title=b_page.getWebPage().getPageName() e_bodyclass="zab_page_default_body" />

<div class="w_h_20 "></div>
<div class="zab_page_default_header">
<div class="zab_page_default_header_title">
${b_page.getWebPage().getPageName()}
</div>
 <div class="btn-group pull-right">


<@m_zapmacro_common_set_operate   b_page.getWebPage().getPageOperate() "116001020"  "btn btn-small" />
  

  </div></div>
  <div class="w_h_20 "></div>
<#include b_page.getWebPage().getPageTemplate()+".ftl" />
  <div class="w_h_40"></div>
  
<@m_common_page_foot_base  />






