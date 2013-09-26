<!DOCTYPE html>
<html class="zab_home_home_html">
	<head>
		<#include "../zapmacro/zapmacro_common.ftl" />
		<#include "../macro/macro_common.ftl" />

		<@m_common_html_head />

		<title>后台登陆界面</title>

	</head>
	<body class="easyui-layout zab_manage_login_body">

		<div class="c_login_box">
			<div class=" w_m_auto c_login_header c_login_width">
				<img src="http://p4.img.cctvpic.com/photoAlbum/page/performance/img/2013/3/7/1362629299085_999.jpg"/>
			</div>
			<div class="c_login_bg">
				<div class=" w_m_auto c_login_center c_login_width">
				<div class="w_h_40"></div>
					<div class="c_login_sign">
						<div class="c_login_fix w_opacity_90">

						</div>
						<div class="c_login_info">

							
							<div>
								
								<div class="c_login_title">
									后台登陆
								</div>
								<div class="w_h_20"></div>
								<form >
									用户名：
									<input type="text" class="c_login_text" id="zw_f_login_name" name="zw_f_login_name" class="input-block-level" placeholder="请输入用户名" value="s">
									<div class="w_h_20"></div>
									密&nbsp;&nbsp;&nbsp;&nbsp;码：
									<input type="password" class="c_login_text" id="zw_f_login_pass" name="zw_f_login_pass" class="input-block-level" placeholder="请输入密码" value="s">
									<div class="w_h_20"></div>
									<input class="btn btn-large btn-success" type="button" zapweb_attr_operate_id="115793e80b38485aaba8223e0ea101b6" onclick="zapjs.zw.func_call(this)" value="登录"/>

									<input type="hidden" id="zapjs_zw_login_sucess_target" name="zapjs_zw_login_sucess_target" value="../manage/home"/>

								</form>
								<div class="c_login_version">
								当前版本：1.0.0.2
								</div>

							</div>

						</div>

					</div>
				</div>
			</div>

		</div>

	</body>
</html>
