<!DOCTYPE html>
<html>
<head>
<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />

<@m_common_html_head />

<title>zapadmin</title>
</head>
<body class="zab_page_default_body">
<div class="w_h_20 "></div>
<legend>${b_page.getWebPage().getPageName()}
 <div class="btn-group pull-right">


<@m_zapmacro_common_set_operate   b_page.getWebPage().getPageOperate() "116001020"  "btn btn-small" />
  </div></legend>
  <div class="w_h_20 "></div>
<#include b_page.getWebPage().getPageTemplate()+".ftl" />
</body>
</html>






