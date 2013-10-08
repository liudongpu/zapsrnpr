<#assign manage_home_title="超级管理后台">
<#assign manage_home_menu="467703130001">
<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />
<#assign user_support=b_method.upClass("com.srnpr.zapweb.websupport.UserSupport")>
<#if user_support.checkLogin()>
	<#assign user_info=user_support.getUserInfo()>
<#else>
	<#assign manage_home_addhead="<meta http-equiv=\"Refresh\" content=\"0; url=login\" />" />
</#if>
<@m_common_page_head_common e_title=manage_home_title e_bodyclass="easyui-layout" e_addhead=manage_home_addhead?default("") />


	
		<#assign home_menu= user_support.upUserMenu(manage_home_menu+"0")>

		<div   data-options="border:false,region:'north',maxHeight:40,minHeight:40" class="zab_home_home_top">
			<div class="w_left c_site">
				<a href="home"><i class="icon-home  icon-white"></i>&nbsp;${manage_home_title}</a>



			</div>
			<div class="w_left c_nav">
				<ul class="w_ul">
					 <#list home_menu as el
					> <#if el['parent_menu']==manage_home_menu>
					<li <#if el_index==0>class="c_active"</#if> ><a href="#" onclick="zapadmin.top_menu(this,${el['menu_code']})">${el['menu_name']}</a></li>
					</#if> </#list>
				</ul>
			</div>


			<div class="w_right c_right">
			<a href="javascript:zapadmin.back_url()"><i class="icon-user  icon-white"></i></a>&nbsp;
			
				<a
					href="javascript:zapjs.zw.func_do(this,'f99848c8f8c011e29b7a000c298b20x')">
		<#if user_info??>${user_info.getRealName()?default("")}</#if></a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:zapjs.zw.login_out('../manage/logout')">退出系统</a>
			</div>




		</div>

	

			<div  data-options="maxWidth:180,border:false,region:'west'"   class=" zab_home_home_left  ">
			
				<div class="c_box w_display">
				<input type="text" placeholder="快捷码">
				</div>
			
				<div>
					<ul>
					<#list home_menu as el > 
						<#if el['menu_code']?length==16>
							</ul>
						</div>
						
						<div id="home_menu_box_${el["menu_code"]}" class="c_item  <#if el_index!=0>w_display</#if>  ">
		
							<div class="c_title">${el["menu_name"]}</div>
							<ul class="w_ullist">
						<#elseif el['menu_code']?length==20>
						
						<#if el_index==1>
						<li class="c_header">+&nbsp;后台系统</li>
						<li class="c_active"><a  href="home" >后台首页</a></li>
						</#if>
						
						
								<li class="c_header">+&nbsp;${el["menu_name"]}</li> 
						<#elseif el['menu_code']?length==24>
								<li><a  href="../${el['menu_link']}" onclick="zapadmin.menu_click(this)" target="main_iframe">${el["menu_name"]}</a></li>
						</#if> 
								
					</#list>
					</ul>
				</div>


				<div class="c_bottom">CopyRight 2013</div>

			</div>
			<div  data-options="border:false,region:'center'"   class="zab_home_home_right">
			<#if user_support.checkLogin()>
				<iframe src="../page/page_zapadmin_index_center" id="main_iframe"
					name="main_iframe" width="100%" height="100%" frameborder="0" onload="zapadmin.load_complate(this)"
					 ></iframe>
			</#if>
			</div>
		
<@m_common_page_foot_base  />
