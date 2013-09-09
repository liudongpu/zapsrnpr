<!DOCTYPE html>
<html>
<head>
<#include "../macro/macro_common.ftl" />
<@m_common_html_head />
<@m_common_html_test />


</head>
<body>
	
	<form method="post">
	
	
	
	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Add files...</span>
        <!-- The file input field used as target for the file upload widget -->
        <input id="fileupload" type="file" name="files[]" multiple="" onchange="$(this).parent('form').submit()">
    </span>
	
	
	</form>
	
	
	<script>
	
	$(function()
	{
	$('#file').click();
	});
	
	</script>
	
</body>
</html>






