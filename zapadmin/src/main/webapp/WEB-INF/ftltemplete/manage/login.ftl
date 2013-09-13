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
      
        <legend>后台登陆</legend>
        <div class="control-group">
	    	<label class="control-label" for="login_name">用户名：</label>
	    	<div class="controls">
	    		<input type="text" id="login_name" class="input-block-level" placeholder="用户名" value="s">
	    	</div>
	  	</div>
        <div class="control-group">
	    	<label class="control-label" for="login_pass">密码：</label>
	    	<div class="controls">
	    		<input type="password" id="login_pass" class="input-block-level" placeholder="密码" value="s">
	    	</div>
	  	</div>
        
         <div class="control-group">
         <label class="control-label" for=""></label>
         <div class="controls">
	    	
        	<button class="btn btn-large btn-success" type="submit">登陆</button>
        </div>
	  	</div>
     
        
        
      </form>

    </div> 
	
</body>
</html>
