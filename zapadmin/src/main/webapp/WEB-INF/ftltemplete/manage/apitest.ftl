<#include "../zapmacro/zapmacro_common.ftl" />
<#include "../macro/macro_common.ftl" />
<@m_common_page_head_common e_title="API测试接口"  />




<div class="easyui-layout" style="width:100%;height:100%;">






 	<div   data-options="region:'west',split:true" title="West" style="width:200px;">
 	
 	
 	<div class="zw_page_tree_box">
	
	<ul class="easyui-tree" id="zw_page_common_tree" ></ul>
	</div>
 	</div>
	<div      data-options="region:'center'" title="测试吧，少年" >
      <form class="form-horizontal" id="manage_apitest_form">
      
        <legend>API测试</legend>
        
         <div class="control-group">
	    	<label class="control-label" for="api_target">api名称：</label>
	    	<div class="controls">
	    		<input type="text" class="c_md5" style="width:80%;" id="api_target" name="api_target"  placeholder="api名称" value="">
	    	</div>
	  	</div>

	  	
	  	<div class="control-group">
	    	<label class="control-label" for="api_input">Json数据：</label>
	    	<div class="controls">
	    		<textarea class=" c_md5"  style="width:80%;" id="api_input" name="api_input" rows="8" ></textarea>
	    	</div>
	  	</div>
	  	
	  	<div class="control-group">
	    	<label class="control-label" for="api_input">接口描述：</label>
	    	<div class="controls">
	    		<div id="manage_apitest_javadoc"></div>
	    	</div>
	  	</div>
	  	


         <div class="control-group">
	         <label class="control-label" for=""></label>
	         <div class="controls">
		    	
	        	<button class="btn btn-large btn-success" type="button" onclick="zapadmin_apitest.call_api()">调用API</button>
	        </div>
	  	</div>
     
     
     	 <div class="control-group">
	    	<label class="control-label" for="api_key">APIKey：</label>
	    	<div class="controls">
	    		<input type="text" class="c_md5" id="api_key" name="api_key"  placeholder="APIKey" value="testapi">
	    	</div>
	  	</div>
     
         <div class="control-group">
	    	<label class="control-label" for="api_pass">API密码：</label>
	    	<div class="controls">
	    		<input type="text" id="api_pass"  placeholder="密码" value="testpassword">
	    	</div>
	  	</div>
	  	<div class="control-group">
	    	<label class="control-label" for="api_timespan">时间戳：</label>
	    	<div class="controls">
	    		<input type="text"  class="c_md5" id="api_timespan" name="api_timespan"  placeholder="" value="">
	    		<input type="checkbox" checked="checked" id="manage_apitest_timeauto">自动取客户端时间
	    	</div>
	  	</div>
	  	
	  	 <div class="control-group">
	    	<label class="control-label" for="api_secret">验证串：</label>
	    	<div class="controls">
	    		<input type="text"  style="width:60%;" id="api_secret" name="api_secret"  placeholder="" value="">
	    		<input type="checkbox" checked="checked" id="manage_apitest_checkbox_apisecret">自动生成
	    		<span class="help-block">var [验证串]=Md5([api名称]+[APIKey]+[Json数据]+[时间戳]+[API密码]);
	    		<br/>
	    		post数据：api_key=[APIKey]&api_input=[Json数据]&api_target=[api名称]&api_secret=[验证串]&api_timespan=[时间戳]
	    		<br/>
	    		接口描述的链接请只观看【Private 属性】，其他请忽略。
	    		<br/>
	    		标记为公开的API只需要传api_key，api_target，api_input三个参数
	    		</span>
	    	</div>
	  	</div>
        
      </form>

    </div> 
    
    
    <div     data-options="region:'east',split:true" title="模拟请求"  style="width:400px;"> 
    
    	<div style="width:360px;margin:10px;">

		<div>
			<div>请求链接</div>
			<div id="manage_apitest_link"></div>
		</div>
		<div>
			<div>请求内容</div>
			<div id="manage_apitest_submit" >
			
			
			</div>
		</div>
		<div>
			<div>返回信息</div>
			<div >
			<textarea id="manage_apitest_return" rows="20" style="width:100%;">
			</textarea>
			
			</div>
		</div>
    	</div>
    
    </div>
    
    </div>
    
    
	<script type="text/javascript">
		require(['zapadmin/js/zapadmin_apitest'],function(a){a.init();});
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
    
    
	       
    </div>
<@m_common_page_foot_base  />
