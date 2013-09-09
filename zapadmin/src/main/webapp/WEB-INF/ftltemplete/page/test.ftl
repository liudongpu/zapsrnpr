<!DOCTYPE html>
<html>
<head>
<#include "../macro/macro_common.ftl" />
<@m_common_html_head />
<@m_common_html_test />

<!--
<@m_common_html_js ["lib/fileupload/js/vendor/jquery.ui.widget.js","lib/fileupload/js/jquery.iframe-transport.js","lib/fileupload/js/jquery.fileupload.js"]/>
-->
</head>
<body>
	
	
	<div>
	</div>
	
	<div id="qunit"></div>
  <div id="qunit-fixture"></div>
	<script type="text/javascript">
		require(['lib/fileupload/js/vendor/jquery.ui.widget','lib/fileupload/js/jquery.iframe-transport','lib/fileupload/js/jquery.fileupload'],function(a,b,c){
		
		
		
		
		$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = window.location.hostname === 'blueimp.github.io' ?
                '//jquery-file-upload.appspot.com/' : '../upload/product';
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        done: function (e, data) {
        
        alert(data.resultObject);
        /*
            $.each(data.result.files, function (index, file) {
                $('<p/>').text(file.name).appendTo('#files');
            });
            */
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});
		
		
		
		});
	</script>
	<script>

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
	
	 <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Add files...</span>
        <!-- The file input field used as target for the file upload widget -->
        <input id="fileupload" type="file" name="files[]" multiple>
    </span>
	
 <!-- The fileinput-button span is used to style the file input field as button -->
    <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Select files...</span>
        <!-- The file input field used as target for the file upload widget -->
        <input id="fileupload" type="file" name="files[]" multiple>
    </span>
    <br>
    <br>
    <!-- The global progress bar -->
    <div id="progress" class="progress">
        <div class="progress-bar progress-bar-success"></div>
    </div>
    <!-- The container for the uploaded files -->
    <div id="files" class="files"></div>
    <br>
</body>
</html>






