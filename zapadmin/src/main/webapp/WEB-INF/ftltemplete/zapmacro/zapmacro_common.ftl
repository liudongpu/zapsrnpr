<#-- 添加页 -->
<#macro m_zapmacro_common_page_add e_page>

<form class="form-horizontal" method="POST" >
	<@m_zapmacro_common_auto_field e_page.upAddData() />
	<@m_zapmacro_common_auto_operate   e_page.getWebPage().getPageOperate() />

</form>

</#macro>

<#-- 列表页 -->
<#macro m_zapmacro_common_page_chart e_page>

	<@m_zapmacro_common_table e_page.upChartData() />
	<@m_zapmacro_common_page_pagination e_page.upChartData() />
</#macro>

<#-- 分页 -->
<#macro m_zapmacro_common_page_pagination e_pagedata>

<div class="pagination">
  <ul>
  
	
	<#if (e_pagedata.getPageIndex()>1)>
		<li><a href="?zapweb_pagination_index=${(e_pagedata.getPageIndex()-1)}">上一页</a></li>
	<#else>
		<li class="disabled"><a>上一页</a></li>
	</#if>
    
    <li class="active"><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>

    <#if (e_pagedata.getPageIndex()<e_pagedata.getPageMax())>
		<li><a href="?zapweb_pagination_index=${(e_pagedata.getPageIndex()+1)}">下一页</a></li>
	<#else>
		<li class="disabled"><a>下一页</a></li>
	</#if>
    
    
  </ul>
</div>

</#macro>

<#-- 列表的自动输出 -->
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

<#-- 页面字段的自动输出判断 -->
<#macro m_zapmacro_common_auto_field e_pagedata>
	<#list e_pagedata as e>
		
	  	<#if e.getFieldTypeAid()=="104005008">
	  		<@m_zapmacro_common_field_hidden e/>
	  	<#elseif  e.getFieldTypeAid()=="104005019">
	  		
	  	<#else>
	  		<@m_zapmacro_common_field_text e/>
	  	</#if>
	  	
	</#list>

</#macro>


<#-- 字段：隐藏域 -->
<#macro m_zapmacro_common_field_hidden e_field>
	<input type="hidden" id="${e_field.getPageFieldName()}" name="${e_field.getPageFieldName()}" value="${e_field.getPageFieldValue()}" />
</#macro>

<#-- 字段：输入框 -->
<#macro m_zapmacro_common_field_text e_field>
	<div class="control-group">
	    	<label class="control-label" for="${e_field.getPageFieldName()}">${e_field.getFieldNote()}</label>
	    	<div class="controls">
	      		<input type="text" id="${e_field.getPageFieldName()}" name="${e_field.getPageFieldName()}" value="${e_field.getPageFieldValue()}">
	    	</div>
	  </div>
</#macro>



<#-- 页面按钮的自动输出 -->
<#macro m_zapmacro_common_auto_operate     e_list_operates >
	<div class="control-group">
    	<div class="controls">
    		<#list e_list_operates as e>
    			<#if e.getOperateTypeAid()=="116015010">
    				<@m_zapmacro_common_operate_button e/>
    			<#else>
    				<@m_zapmacro_common_operate_link e/>
    			</#if>
    		
    			
    		</#list>
    	</div>
	</div>
</#macro>


<#-- 页面按钮 -->
<#macro m_zapmacro_common_operate_button  e_operate>
	
	<input type="button" class="btn btn-success" zapweb_attr_operate_id="${e_operate.getOperateUid()}"  onclick="${e_operate.getOperateLink()}"  value="${e_operate.getOperateName()}" />
</#macro>

<#-- 页面按钮 -->
<#macro m_zapmacro_common_operate_link  e_operate>
	<a class="btn btn-success" href="${e_operate.getOperateLink()}" >${e_operate.getOperateName()}</a>
</#macro>






















