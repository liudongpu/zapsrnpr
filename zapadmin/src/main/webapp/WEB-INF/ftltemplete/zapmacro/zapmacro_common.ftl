
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
<form class="form-horizontal">
	
	<#list e_pagedata as e>
		<div class="control-group">
	    	<label class="control-label" for="${e.getPageFieldName()}">${e.getFieldNote()}</label>
	    	<div class="controls">
	      		<input type="text" id="${e.getPageFieldName()}">
	    	</div>
	  	</div>
	</#list>
</form>
</#macro>

<#macro m_zapmacro_common_field_run >

</#macro>


























