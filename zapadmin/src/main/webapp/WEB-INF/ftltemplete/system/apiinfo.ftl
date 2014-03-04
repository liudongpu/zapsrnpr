<#include "../macro/macro_open.ftl" /> <@m_open_header /> <#assign
api_model=b_method.upClass("com.srnpr.zapweb.webclass.ShowApiInfo")>


<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">

			<a class="navbar-brand" href="?"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;仓颉开放平台</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="?">首页</a></li>
				<li><a href="?">API文档</a></li>
				<li><a href="../manage/apitest" target="_blank">沙箱测试</a></li>
				<li><a href="?">SDK下载</a></li>
				<li><a href="?">技术支持</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container -->
</div>
<!-- /.navbar -->

<div class="container">
	<ol class="breadcrumb">
		<li><a href="?">首页</a></li>
		<#if api_model.getShowType()=="info">
		<li><a href="?apicode=${api_model.getApiInfo()['parent_code']}">${api_model.getApiInfo()["parent_name"]}</a></li>
		</#if>
		<li class="active"><@m_open_format_name
			api_model.getApiInfo()["class_name"]/>
			${api_model.getApiInfo()["api_name"]}</li>
	</ol>
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="col-xs-3 col-sm-3 sidebar-offcanvas" id="sidebar"
			role="navigation">
			<#if api_model.getShowType()=="info">
			<a href="?apicode=${api_model.getApiInfo()['parent_code']}" class="btn btn-warning btn-block">&nbsp;返回</a>
			<hr>
			</#if><div >
			<div class="list-group">
				 <#list
				api_model.getListInfo() as e_list> 
				
				<#if (e_list['api_code']==api_model.getApiInfo()['api_code'])>
				
				<a
					href="?apicode=${e_list['api_code']}"
					class="list-group-item active"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;${e_list["api_name"]}</a>
				
				<#else>
				<a
					href="?apicode=${e_list['api_code']}"
					class="list-group-item">${e_list["api_name"]}</a>
				
				</#if>
				
				
				</#list>


</div>
			</div>
		</div>
		<!--/span-->
		<div class="col-xs-9 col-sm-9">

			<#if api_model.getShowType()=="list">
			
			<a href="#" class="btn btn-warning btn-block">${api_model.getApiInfo()["api_name"]}-API列表</a>
			<div class="w_h_40"></div>
			
			<table class="table">
			
			<#list
				api_model.getListSub() as e_list> 
				<tr>
				<td><@m_open_format_type e_list['api_type_did']/>&nbsp;&nbsp;<a
					href="?apicode=${e_list['api_code']}"
					>
				<@m_open_format_name
				e_list["class_name"]/></a><div class="w_h_10"></div>
				</td>
				<td>
				<a
					href="?apicode=${e_list['api_code']}"
					>${e_list["api_name"]}</a>
					</td>
</tr>
				</#list>
				
				</table>
				
				
				
			
			
			<#else>



			<div>
				
					<@m_open_format_type api_model.getApiInfo()['api_type_did']/>
			</div>
			<div class="w_p_10">${api_model.getApiInfo()["api_note"]}</div>
			<div class="clearfix w_h_20"></div>

			<div class="panel-group clearfix" id="apiinfo_panel_group">

				<div class="panel panel-warning">
					<div class="panel-heading">

						<a data-toggle="collapse" data-toggle="collapse"
							href="#apiinfo_panel_one"> 系统输入参数 </a>


					</div>
					<div id="apiinfo_panel_one" class="panel-collapse collapse in">
						<div class="panel-body">
							<table class="table  table-condensed table-bordered table-hover">
								<tr  class="active">
									<th style="width: 10%;">参数名称</th>
									<th style="width: 8%;">类型</th>
									<th style="width: 10%;">是否必须</th>
									<th style="width: 20%;">参数描述</th>
									<th>备注</th>
								</tr>



								<tr>
									<td>api_key</td>
									<td>String</td>
									<td>1</td>
									<td>系统分配的APPKEY</td>
									<td></td>
								</tr>
								<tr>
									<td>api_timespan</td>
									<td>String</td>
									<td>1</td>
									<td>时间戳</td>
									<td>时间戳，格式为yyyy-MM-dd HH:mm:ss，例如：2012-11-05
										11:15:30。API服务端允许客户端请求时间误差为10分钟。</td>
								</tr>
								<tr>
									<td>api_target</td>
									<td>String</td>
									<td>1</td>
									<td>调用接口名称</td>
									<td><@m_open_format_name
										api_model.getApiInfo()["class_name"]/></td>
								</tr>
								<tr>
									<td>api_secret</td>
									<td>String</td>
									<td>1</td>
									<td>加密认证串</td>
									<td>API输入参数签名结果</td>
								</tr>
								<tr>
									<td>api_input</td>
									<td>String</td>
									<td>1</td>
									<td>输入参数</td>
									<td>输入参数的JSON格式</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="w_h_20"></div>

				<div class="panel  panel-info index_listbox">
					<!-- Default panel contents -->
					<div class="panel-heading">

						<a data-toggle="collapse" data-toggle="collapse"
							href="#apiinfo_panel_two"> 应用输入参数 </a>
					</div>
					<div id="apiinfo_panel_two" class="panel-collapse collapse in">
						<div class="panel-body"><@m_open_show_class
							p_class_model=api_model.getInputClass() p_show_type=0 /></div>
					</div>
				</div>

				<div class="w_h_20"></div>
				<div class="panel  panel-success index_listbox">
					<!-- Default panel contents -->
					<div class="panel-heading">

						<a data-toggle="collapse" data-toggle="collapse"
							href="#apiinfo_panel_three"> 返回结果 </a>
					</div>
					<div id="apiinfo_panel_three" class="panel-collapse collapse in">
						<div class="panel-body"><@m_open_show_class
							p_class_model=api_model.getResultClass() p_show_type=1 /></div>
					</div>

				</div>
				
				
				<#if api_model.getConnClass()??>
				<div class="w_h_20"></div>
				<div class="panel  panel-danger index_listbox">
					<!-- Default panel contents -->
					<div class="panel-heading">

						<a data-toggle="collapse" data-toggle="collapse"
							href="#apiinfo_panel_four"> 结构体 </a>
					</div>
					<div id="apiinfo_panel_four" class="panel-collapse collapse in">
						<div class="panel-body">
						
						
						<#list api_model.getConnClass()?keys as e_key> 
						<div class="w_h_40" id="apiinfo_field_${e_key?replace('.','_')}">
						
						<#assign e_keyscm=e_key?split('.') >
						
						<span class="label label-info">${e_keyscm[e_keyscm?size-1]}</span>
						</div>
						
						
						<@m_open_show_class
							p_class_model=api_model.getConnClass()[e_key] p_show_type=1 />
						
						<hr/>
						</#list>
						
						
						</div>
						
					</div>

				</div>
				
				</#if>
				

			</div>
			</#if>
		</div>
		<!--/span-->
		

	</div>
	<!--/row-->

	<hr>

	<footer>

		<p>&copy; Company 2013</p>
	</footer>

</div>
<!--/.container-->



















<@m_open_footer />
