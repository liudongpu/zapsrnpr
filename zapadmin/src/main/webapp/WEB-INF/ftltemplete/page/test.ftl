<!DOCTYPE html>
<html>
<head>
<#include "../macro/macro_common.ftl" />
<@m_common_html_test />

</head>
<body>
	
	
	<div>
	</div>
	
	<div id="qunit"></div>
  <div id="qunit-fixture"></div>
	
	<script>require(['lib/jquery/jquery-last.min'],function(a){});
	
	test( "hello test", function() {
  ok( 1 == "1", "Passed!" );
});
	
	</script>
	<input type="button" onclick="$('#file_upload').click()"/>
	
	<form action="../upload/product" method="POST" enctype="multipart/form-data">
	<input type="file" name="file" id="file_upload" />
	啊啊
	<input type="submit" value="aa" onclick=""/>
	</form>
	
	
	

</body>
</html>






