<!DOCTYPE html>
<html>
<head>
<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />
<@m_common_html_js ["../resources/lib/jquery/jquery-last.min.js","../resources/lib/datepicker/WdatePicker.js","../resources/lib/jquery/jquery-plugins-zap.min.js","../resources/lib/bootstrap/js/bootstrap.min.js"]/>


<@m_common_html_css ["../resources/lib/bootstrap/css/bootstrap.min.css","../resources/zapadmin/css/zab_base.css","../resources/zapweb/css/w.css"] />
<@m_common_html_js ["../resources/zapjs/zapjs.js","../resources/zapjs/zapjs.zw.js"]/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>zapadmin</title>
</head>
<body>
<div class="w_h_20 "></div>
<#include b_page.getWebPage().getPageTemplate()+".ftl" />
</body>
</html>






