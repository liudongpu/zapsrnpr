
<#macro m_zapmacro_common_table e_pagedata>

<table  class="table">
	<thead>
	    <tr>
	        <#list e_pagedata.getPageHead() as e_list>
		      	 <th>
		      	 	${e_list}
		      	 </th>
	      </#list>
	    </tr>
  	</thead>
  
	<tbody>
		<#list e_pagedata.getPageData() as e_list>
			<tr>
	  		 <#list e_list as e>
	      		<td>
	      			${e}
	      		</td>
	      	</#list>
	      	</tr>
	 	</#list>
		</tbody>
</table>


</#macro>


<#macro m_zapmacro_common_autofield e_pagedata>

	
	<#list e_pagedata as e>
		<div class="control-group">
	    	<label class="control-label" for="${e.getPageFieldName()}">${e.getFieldNote()}</label>
	    	<div class="controls">
	      		<input type="text" id="${e.getPageFieldName()}">
	    	</div>
	  	</div>
	</#list>

</#macro>

<#macro m_zapmacro_common_field_run e_fieldinfo>

</#macro>

<#macro m_zapmacro_common_web_buttons     e_list_operates >
	<div class="control-group">
    	<div class="controls">
    		<#list e_list_operates as e>
    			<@m_zapmacro_common_web_operate e/>
    		</#list>
    	</div>
	</div>
</#macro>

<#macro m_zapmacro_common_web_operate e_operate>
	<a class="btn btn-success" href="<#if e_operate.getOperateTypeAid()=="116015010">javascript:</#if>${e_operate.getOperateLink()}" >${e_operate.getOperateName()}</a>
</#macro>






















