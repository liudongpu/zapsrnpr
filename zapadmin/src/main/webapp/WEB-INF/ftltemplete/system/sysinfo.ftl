<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />

<@m_common_page_head_common 
	e_title="systeminfo" 
	 e_bodyclass="zab_page_default_body"
	e_addhead=addhead
/>

<#assign sys_info=b_method.upClass("com.srnpr.zapweb.webclass.SysInfo").upSysInfo()>

<div class="zab_page_default_header">
<div class="zab_page_default_header_title">
system-info
</div>
 <div class="btn-group pull-right">


  

  </div></div>

 <table class="table  table-condensed table-bordered table-hover">
	<#list sys_info?keys as testKey>
	
	     
	      
	    <tr>
	    <td>${testKey} 
	    </td>
	    <td>${sys_info[testKey]}
	    </td>
	    </tr>
	
	             
	
	    
	
	</#list>
 </table>

<@m_common_page_foot_base  />
