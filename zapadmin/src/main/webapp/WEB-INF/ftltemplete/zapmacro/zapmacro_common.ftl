<#-- 添加页 -->
<#macro m_zapmacro_common_page_add e_page>

<form class="form-horizontal" method="POST" >
	<@m_zapmacro_common_auto_field e_page.upAddData()   e_page  />
	<@m_zapmacro_common_auto_operate   e_page.getWebPage().getPageOperate()  "116001016" />

</form>

</#macro>

<#-- 列表页 -->
<#macro m_zapmacro_common_page_chart e_page>

	<div class="zab_page_common_inquire">
	<@m_zapmacro_common_page_inquire e_page />
	</div>
	<@m_zapmacro_common_table e_page.upChartData() />
	<@m_zapmacro_common_page_pagination e_page  e_page.upChartData() />
</#macro>

<#-- 查询区域 -->
<#macro m_zapmacro_common_page_inquire e_page>
	<form class="form-horizontal" method="POST" >
		<@m_zapmacro_common_auto_inquire e_page />
		<@m_zapmacro_common_auto_operate   e_page.getWebPage().getPageOperate() "116001009" />
	</form>
</#macro>



<#-- 页面字段的自动输出判断 -->
<#macro m_zapmacro_common_auto_inquire e_page>
	<#list e_page.upInquireData() as e>
	
		<#if e.getQueryTypeAid()=="104009002">
			<@m_zapmacro_common_field_between e  e_page/>
		
	  	<#elseif e.getFieldTypeAid()=="104005008">
	  		
	  	<#elseif  e.getFieldTypeAid()=="104005019">
	  		
	  	<#else>
	  		<@m_zapmacro_common_field_text e/>
	  		
	  	</#if>
	  	
	</#list>

</#macro>




<#-- 分页 -->
<#macro m_zapmacro_common_page_pagination e_page  e_pagedata>

<div class="pagination">
  <ul>
	<#if (e_pagedata.getPageIndex()>1)>
		<li><a href="<@m_zapmacro_common_page_pagination_href  e_page  e_pagedata   e_pagedata.getPageIndex()-1/>">上一页</a></li>
	<#else>
		<li class="disabled"><a>上一页</a></li>
	</#if>

    <#local max=(e_pagedata.getPageIndex()+5)>
    <#if (max>e_pagedata.getPageMax())><#local max=e_pagedata.getPageMax()></#if>
    
    <#local min=(e_pagedata.getPageIndex()-(5+e_pagedata.getPageIndex()-max))>
    <#if (min<1)><#local min=1></#if>
    
	<#list min..max as e>
		<li <#if e==e_pagedata.getPageIndex()> class="active"</#if> ><a href="<@m_zapmacro_common_page_pagination_href  e_page  e_pagedata   e/>">${e}</a></li>
	</#list>

    <#if (e_pagedata.getPageIndex()<e_pagedata.getPageMax())>
		<li><a href="<@m_zapmacro_common_page_pagination_href  e_page  e_pagedata   e_pagedata.getPageIndex()+1/>">下一页</a></li>
	<#else>
		<li class="disabled"><a>下一页</a></li>
	</#if>
    
    
  </ul>
</div>

</#macro>

<#macro m_zapmacro_common_page_pagination_href  e_page  e_pagedata  e_pageindex>
${e_page.upReplaceUrl("",["zapweb_pagination_count="+(e_pagedata.getPageCount()),"zapweb_pagination_index="+(e_pageindex)])}
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
<#macro m_zapmacro_common_auto_field e_pagedata   e_page>
	<#list e_pagedata as e>
		
	  	<#if e.getFieldTypeAid()=="104005008">
	  		<@m_zapmacro_common_field_hidden e/>
	  	<#elseif  e.getFieldTypeAid()=="104005019">
	  		<@m_zapmacro_common_field_select  e  e_page/>
	  		<#elseif  e.getFieldTypeAid()=="104005020">
	  		<@m_zapmacro_common_field_textarea  e />
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


<#-- 字段：输入框 -->
<#macro m_zapmacro_common_field_textarea e_field>
	<div class="control-group">
	    	<label class="control-label" for="${e_field.getPageFieldName()}">${e_field.getFieldNote()}</label>
	    	<div class="controls">
	    		<textarea id="${e_field.getPageFieldName()}" name="${e_field.getPageFieldName()}">${e_field.getPageFieldValue()}</textarea>
	    	
	    	</div>
	  </div>
</#macro>




<#-- 字段：下拉框 -->
<#macro m_zapmacro_common_field_select e_field  e_page>
	<div class="control-group">
	    	<label class="control-label" for="${e_field.getPageFieldName()}">${e_field.getFieldNote()}</label>
	    	<div class="controls">
	      		<select name="${e_field.getPageFieldName()}" id="${e_field.getPageFieldName()}">
	      			<#list e_page.upDataSource(e_field) as e_key>
						<option value="${e_key.getV()}">${e_key.getK()}</option>
					</#list>
	      		</select>
	    	</div>
	  </div>



	
</#macro>







<#-- 字段：文本范围 -->
<#macro m_zapmacro_common_field_between e_field  e_page>
	<div class="control-group">
	    	<label class="control-label" for="${e_field.getPageFieldName()}_from">${e_field.getFieldNote()}</label>
	    	<div class="controls">
	    		从
	      		<input type="text" id="${e_field.getPageFieldName()}_from" name="${e_field.getPageFieldName()}_from" value="${e_field.getPageFieldValue()}">
	      		到
	      		<input type="text" id="${e_field.getPageFieldName()}_from" name="${e_field.getPageFieldName()}_from" value="${e_field.getPageFieldValue()}">
	      		
	      		
	    	</div>
	  </div>
</#macro>






<#-- 页面按钮的自动输出 -->
<#macro m_zapmacro_common_auto_operate     e_list_operates  e_area_type>
	<div class="control-group">
    	<div class="controls">
    		<#list e_list_operates as e>
    			<#if e.getAreaTypeAid()==e_area_type>
    		
	    			<#if e.getOperateTypeAid()=="116015010">
	    				<@m_zapmacro_common_operate_button e/>
	    			<#else>
	    				<@m_zapmacro_common_operate_link e/>
	    			</#if>
    		
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






















