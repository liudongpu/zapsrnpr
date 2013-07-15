
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



