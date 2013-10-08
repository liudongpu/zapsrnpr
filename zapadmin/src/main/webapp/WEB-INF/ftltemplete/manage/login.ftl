<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />

<@m_common_page_head_common e_title="后台登陆界面" e_bodyclass="easyui-layout zab_manage_login_body" />

	<div class="c_login_box">
		<div class=" w_m_auto c_login_header c_login_width">

			<div class="w_fl w_mt_15">

				<div class="c_login_logo"></div>

			</div>
			<div class="w_fr w_mt_30">
				<ul class="w_ul">
					<li><a href="">商城首页</a></li>
					<li><a href="">央视网</a></li>
					<li><a href="">中国好物产</a></li>
				</ul>
			</div>
		</div>
		<div class="c_login_bg">
			<div class=" w_m_auto c_login_center c_login_width">
				<div class="w_h_40"></div>
				<div class="c_login_sign">
					<div class="c_login_fix w_opacity_90"></div>
					<div class="c_login_info">

						<div>
							<div class="c_login_pos">
								<div class="c_login_title">后台登陆</div>
								<div class="w_h_20"></div>

								<form>
									用户名： <input type="text" class="c_login_text"
										id="zw_f_login_name" name="zw_f_login_name"
										class="input-block-level" placeholder="请输入用户名" value="">
									<div class="w_h_20"></div>
									密&nbsp;&nbsp;&nbsp;&nbsp;码： <input type="password"
										class="c_login_text" id="zw_f_login_pass"
										name="zw_f_login_pass" class="input-block-level"
										placeholder="请输入密码" value="">
									<div class="w_h_20"></div>
									<div class="w_al_center">
										<input class="btn btn-large btn-danger" type="button"
											zapweb_attr_operate_id="115793e80b38485aaba8223e0ea101b6"
											onclick="zapjs.zw.func_call(this)" value="登录"/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>新商户申请</a>
									</div>
									<input type="hidden" id="zapjs_zw_login_sucess_target"
										name="zapjs_zw_login_sucess_target" value="../manage/home" />

								</form>
								<div class="w_h_20"></div>
								<div class="w_al_center c_login_desc">招商客服电话：010-82001009
									/1139/1129</div>
							</div>
							<div class="c_login_version">当前系统版本：2.0.0.1</div>

						</div>

					</div>

				</div>
			</div>
		</div>



		<div class=" w_m_auto c_login_footer c_login_width">

			<div class="w_fl w_mt_15">中国中央电视台 中国网络电视台 版权所有</div>
			<div class="w_fr w_mt_15">
				<ul class="w_ul">
					<li><a href="http://cctvenchiridion.cctv.com/index.shtml"
						target="_blank">关于CCTV</a></li>
					<li><a
						href="http://www.cctv.com/program/20110130/column_header/index.shtml"
						target="_blank">联系CCTV</a></li>
					<li><a href="http://www.cntv.cn/cntv/01/index.shtml"
						target="_blank">CNTV介绍</a></li>
					<li><a
						href="http://www.cctv.com/program/C15676/15/index.shtml"
						target="_blank">联系我们</a></li>
					<li><a
						href="http://www.cctv.com/program/C15676/15/index.shtml"
						target="_blank">帮助中心</a></li>
				</ul>
			</div>
		</div>
	</div>


<@m_common_page_foot_base  />
