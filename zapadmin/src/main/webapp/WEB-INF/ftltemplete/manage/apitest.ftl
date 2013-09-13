<!DOCTYPE html>
<html class="zab_home_home_html">
<head>
<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" /> 

<@m_common_html_head />

<title>API测试接口</title>

</head>
<body class="easyui-layout">

 	<div class="span4">
 	
 	
 	<div class="zw_page_tree_box">
	
	<ul class="easyui-tree" id="zw_page_common_tree" ></ul>
	</div>
 	</div>
	<div class="span7">
      <form class="form-horizontal" class="span8">
      
        <legend>API测试</legend>
        
         <div class="control-group">
	    	<label class="control-label" for="api_target">api名称：</label>
	    	<div class="controls">
	    		<input type="text" class="span8" id="api_target"  placeholder="APITarge" value="com.srnpr.zapweb.webfactory.ListApi">
	    	</div>
	  	</div>
        
        <div class="control-group">
	    	<label class="control-label" for="api_key">APIKey：</label>
	    	<div class="controls">
	    		<input type="text" id="api_key"  placeholder="APIKey" value="testapi">
	    	</div>
	  	</div>
        <div class="control-group">
	    	<label class="control-label" for="api_pass">API密码：</label>
	    	<div class="controls">
	    		<input type="text" id="api_pass"  placeholder="密码" value="testpassword">
	    	</div>
	  	</div>
	  	
	  	<div class="control-group">
	    	<label class="control-label" for="api_input">Json数据：</label>
	    	<div class="controls">
	    		<textarea class="span8" id="api_input" name="api_input" rows="12" ></textarea>
	    	</div>
	  	</div>
	  	
	  	
	  	<div class="control-group">
	    	<label class="control-label" for="api_timespan">时间：</label>
	    	<div class="controls">
	    		<input type="text" id="api_timespan" name="api_timespan"  placeholder="" value="">
	    	</div>
	  	</div>
	  	
	  	
	  	 <div class="control-group">
	    	<label class="control-label" for="api_secret">验证串：</label>
	    	<div class="controls">
	    		<input type="text" id="api_secret" name="api_secret"  placeholder="" value="">
	    	</div>
	  	</div>
	  	
        
         <div class="control-group">
         <label class="control-label" for=""></label>
         <div class="controls">
	    	
        	<button class="btn btn-large btn-success" type="button">测试</button>
        </div>
	  	</div>
     
        
        
      </form>

    </div> 
    
    
    
    
	<script type="text/javascript">
		require(['zapadmin/js/zapadmin_tree'],function(a){a.tree_init();});
	</script>








<input type="hidden" name="zw_page_tree_zw_s_uid" id="zw_page_tree_zw_s_uid" value="zw_f_uid"    />
<input type="hidden" name="zw_page_tree_zw_s_step" id="zw_page_tree_zw_s_step" value="4"    />
<input type="hidden" name="zw_page_tree_zw_p_size" id="zw_page_tree_zw_p_size" value="0"    />
<input type="hidden" name="zw_page_tree_zw_s_code" id="zw_page_tree_zw_s_code" value="zw_f_menu_code"    />
<input type="hidden" name="zw_page_tree_zw_p_sort" id="zw_page_tree_zw_p_sort" value="api_code"    />
<input type="hidden" name="zw_page_tree_zw_s_bookpage" id="zw_page_tree_zw_s_bookpage" value="../show/page_book_v_za_menu"    />
<input type="hidden" name="zw_page_tree_zw_s_addpage" id="zw_page_tree_zw_s_addpage" value="../show/page_add_v_za_menu"    />
<input type="hidden" name="zw_page_tree_zw_s_editpage" id="zw_page_tree_zw_s_editpage" value="../show/page_edit_v_za_menu"    />
<input type="hidden" name="zw_page_tree_zw_s_parent" id="zw_page_tree_zw_s_parent" value="zw_f_parent_menu"    />
<input type="hidden" name="zw_page_tree_zw_s_jsonchart" id="zw_page_tree_zw_s_jsonchart" value="../jsonchart/page_chart_v_za_apiinfo"    />
    
    
	
</body>
</html>
