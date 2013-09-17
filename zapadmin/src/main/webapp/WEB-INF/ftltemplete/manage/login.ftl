<!DOCTYPE html>
<html class="zab_home_home_html">
<head>
<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" /> 

<@m_common_html_head />



<title>后台登陆界面</title>

</head>
<body class="easyui-layout zab_manage_login_body">

 	<div class="container">

      <form class="zab_manage_login_signin  form-horizontal">
      <img src="http://p4.img.cctvpic.com/photoAlbum/page/performance/img/2013/3/7/1362629299085_999.jpg"/>
        <legend>后台登陆</legend>
        <div class="control-group">
	    	<label class="control-label" for="zw_f_login_name">用户名：</label>
	    	<div class="controls">
	    		<input type="text" id="zw_f_login_name" name="zw_f_login_name" class="input-block-level" placeholder="用户名" value="s">
	    	</div>
	  	</div>
        <div class="control-group">
	    	<label class="control-label" for="zw_f_login_pass">密码：</label>
	    	<div class="controls">
	    		<input type="password" id="zw_f_login_pass" name="zw_f_login_pass" class="input-block-level" placeholder="密码" value="s">
	    	</div>
	  	</div>
        
         <div class="control-group">
         <label class="control-label" for=""></label>
         <div class="controls">
	    	
        	<button class="btn btn-large btn-success" type="button" zapweb_attr_operate_id="115793e80b38485aaba8223e0ea101b6" onclick="zapjs.zw.func_call(this)">登陆</button>
        </div>
	  	</div>
     
        
       
      </form>

    </div> 
	
</body>
</html>
