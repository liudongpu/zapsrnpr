<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />

<@m_common_page_head_common 
	e_title="systeminfo" 
	 e_bodyclass="zab_page_default_body"
	e_addhead=addhead
/>





<#assign api_model=b_method.upClass("com.srnpr.zapweb.webclass.ShowApiInfo")>
				

<div class="zab_page_default_header">
<div class="zab_page_default_header_title">
system-info
</div>
 <div class="btn-group pull-right">

  </div></div>
  
  API名称：<br/>
  ${api_model.getApiInfo()["api_name"]} <br/>
    API接口：<br/>
  ${api_model.getApiInfo()["class_name"]} 
  <br/>应用输入参数：
  <br/>

 <table class="table  table-condensed table-bordered table-hover">
  <tr>
	    <th>参数名称
	    </th>
	    <th>类型
	    </th>
	    <th>是否必须
	    </th>
	    <th>参数描述
	    </th><th>备注
	    </th>
	    </tr>
 <#assign input_class=api_model.getInputClass()>	
 
 <#assign input_field=input_class.getFields()>
 
	<#list input_field?keys as testKey> 
	    <tr>
	    <td>${testKey} 
	    </td>
	    <td>${input_field[testKey].getType()}
	    </td>
	    <td>${input_field[testKey].getFlagRequire()}
	    </td>
	    <td>${input_field[testKey].getTitle()}
	    </td>
	    <td>${input_field[testKey].getRemark()}
	    </td>
	    </tr>
	</#list>
 </table>

<@m_common_page_foot_base  />
