<!DOCTYPE html>
<html>
<head>
<#include "zapmacro/zapmacro_common.ftl" />
<#include "macro/macro_common.ftl" />
<@m_common_html_js ["resources/lib/jquery/jquery-last.min.js","resources/lib/bootstrap/js/bootstrap.min.js","resources/zapjs/zapjs.js","resources/zapjs/zapjs.zw.js","resources/zapadmin/js/zapadmin.js"]/>
<@m_common_html_css ["resources/lib/bootstrap/css/bootstrap.min.css","resources/zapadmin/css/zab_base.css","resources/zapweb/css/w.css"] />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>zapadmin</title>

</head>
<body class="zab_body">




	<#assign home_menu=b_method.upDataQuery("za_menu","menu_code","")>

	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="">超级管理后台</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
            <a href="javascript:zapjs.zw.func_do('f99848c8f8c011e29b7a000c298b20x')">刷新缓存</a>
              Logged in as <a href="#" class="navbar-link">Username</a>
            </p>
            <ul class="nav">
              		<li class="active"><a href="" onclick="zapadmin.top_menu(this)">后台首页</a></li>
              <#list home_menu as el >
              	<#if el['parent_menu']=='469903130001'>
              	 	<li><a onclick="zapadmin.top_menu(this,${el['menu_code']})">${el['menu_name']}</a></li>
              	 </#if>
              </#list>
              
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
        			<div>
        			<ul>
        	<#list home_menu as el >
              	<#if el['menu_code']?length==16>
              	 	</ul>
              	 	</div>
              	 	 <div id="home_menu_box_${el["menu_code"]})" class="well sidebar-nav">
              	 	 <ul class="nav nav-list">
              	<#elseif el['menu_code']?length==20>
              		<li class="nav-header">${el["menu_name"]}</li>
              	 
              	 <#elseif el['menu_code']?length==24>
              		<li><a  href="${el['menu_link']}" target="main_iframe">${el["menu_name"]}</a></li>
              	 </#if>
              </#list>
              		</ul>
        			</div>
        
        
        
        
        </div><!--/span-->
        <div class="span10">
          <iframe src="page/page_chart_v_zw_view"  id="main_iframe" name="main_iframe"  width="100%" height="100%" frameborder="0"  onload="zapadmin.autoheight('main_iframe')"/>
        </div><!--/span-->
      </div><!--/row-->



    </div><!--/.fluid-container-->
</body>
</html>
