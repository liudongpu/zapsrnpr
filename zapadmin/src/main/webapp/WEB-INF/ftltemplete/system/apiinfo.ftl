<#include "../macro/macro_open.ftl" /> <@m_open_header /> <#assign
api_model=b_method.upClass("com.srnpr.zapweb.webclass.ShowApiInfo")>


<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">

			<a class="navbar-brand" href="#">CCTVMALL开放平台</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">首页</a></li>
				<li><a href="#about">API文档</a></li>
				<li><a href="#contact">沙箱测试</a></li>
				<li><a href="#contact">SDK下载</a></li>
				<li><a href="#contact">技术支持</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container -->
</div>
<!-- /.navbar -->

<div class="container">
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li class="active"><@m_open_format_name
			api_model.getApiInfo()["class_name"]/>
			${api_model.getApiInfo()["api_name"]}</li>
	</ol>
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="col-xs-3 col-sm-3 sidebar-offcanvas" id="sidebar"
			role="navigation">
			<a href="#" class="btn btn-warning btn-block">返回</a>
			<hr>
			<div class="list-group">
				 <#list
				api_model.getListInfo() as e_list> <a
					href="?apicode=${e_list['api_code']}"
					class="list-group-item <#if (e_list['api_code']==api_model.getApiInfo()['api_code'])> active</#if>">${e_list["api_name"]}</a>

				</#list>



			</div>
		</div>
		<!--/span-->
		<div class="col-xs-9 col-sm-9">





			<div>
				<span class="label label-danger"><span
					class="glyphicon glyphicon-star"></span>&nbsp;私有&nbsp;&nbsp;</span>
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
								<tr>
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

			</div>
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
