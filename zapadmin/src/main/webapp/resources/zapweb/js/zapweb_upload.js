/*
 * zapweb_upload
 * 文件上传类
 */

var zapweb_upload = {

	// 上传显示
	upload_html : function(sTargetUpload, sId, sValue, sSet) {

		if (!sSet) {
			sSet = '';
		}

		return '<input type="hidden" zapweb_attr_target_url="' + sTargetUpload + '"  zapweb_attr_set_params="' + sSet + '"  id="' + sId + '" name="' + sId + '" value="' + sValue + '"><span class="control-upload_iframe"></span><span class="control-upload_process"></span><span class="control-upload"></span>';

	},

	clear_upload : function() {

		$('form .control-upload_iframe').html('');

	},

	// 上传文件
	upload_file : function(sFieldName, sUploadUrl) {

		//zapjs.f.setdomain();
		// sUploadUrl = "../upload/";
		zapjs.e('zapjs_e_zapjs_f_ajaxsubmit_submit', zapweb_upload.clear_upload);
		zapweb_upload.upload_show(sFieldName);

	},

	is_image : function(sFix) {

		var sImageFile = ".jpg;.png;.jpeg;.bmp;.gif;";

		return sImageFile.indexOf('.' + sFix + ';') > -1;

	},

	// 上传文件结果
	upload_result : function(o) {
		//zapjs.f.setdomain();
		zapweb_upload.upload_success(o, zapjs.f.urlget('zw_s_source'));
	},
	// 上传展示
	upload_show : function(sField) {

		var bFlagMul = false;

		var sNumber = zapjs.f.upset($('#' + sField).attr(zapjs.c.field_attr + "set_params"), 'zw_s_number');

		if (sNumber != "") {

			var iMax = parseInt(sNumber);
			var iNow = $('#' + sField).val().split(zapjs.c.split).length;

			if (iNow < iMax) {

				bFlagMul = true;
			}

		}

		if ($('#' + sField).val() == "" || bFlagMul) {

			if ($('#' + sField).nextAll('.control-upload_iframe').html() == "") {
				var sUploadUrl = $('#' + sField).attr(zapjs.c.field_attr + "target_url");

				//$('#' + sField).nextAll('.control-upload_iframe').html('<iframe src="' + sUploadUrl + 'upload?zw_s_source=' + sField + '" class="zw_page_upload_iframe" frameborder="0"></iframe>');

				$('#' + sField).nextAll('.control-upload_iframe').html('<div class="control-upload_div"><span class="btn btn-info fileinput-button"><i class="glyphicon glyphicon-plus"/><span>选择文件</span><input onchange="zapweb_upload.upload_upload(\'' + sField + '\')" id="' + sField + '_zapweb_upload_field" name="' + sField + '_zapweb_upload_field" type="file"/></span></div>');
			}
		} else {
			$('#' + sField).nextAll('.control-upload_iframe').html('');
		}

		if ($('#' + sField).val() != "") {
			var sFiles = $('#' + sField).val().split(zapjs.c.split);
			if ($('#' + sField).val() != "" && sFiles.length > 0) {

				var aHtml = [];

				aHtml.push('<ul>');

				for (var i in sFiles) {

					var aFname = sFiles[i].split('.');

					var sFix = aFname[aFname.length - 1];

					var sShowHex = zapweb_upload.is_image(sFix) ? ('<img src="' + sFiles[i] + '" />') : sFix;

					aHtml.push('<li><div class="control-upload-image"><a href="' + sFiles[i] + '" target="_blank">' + sShowHex + '</a></div><div class="control-upload-delete"><span class="btn btn-mini " onclick="zapweb_upload.upload_delete(\'' + sField + '\',' + i + ')"><i class="icon-trash "></i>&nbsp;&nbsp;删除</span></div></li>');
				}

				aHtml.push('</ul>');

				$('#' + sField).nextAll('.control-upload').html(aHtml.join(''));
			}
		} else {

			$('#' + sField).nextAll('.control-upload').html('');
		}
	},
	// 删除上传文件
	upload_delete : function(sField, iIndex) {

		var sFiles = $('#' + sField).val().split(zapjs.c.split);

		sFiles.splice(iIndex, 1);

		$('#' + sField).val(sFiles.join(zapjs.c.split));

		zapweb_upload.upload_show(sField);
	},
	// 上传成功
	upload_success : function(o, sField) {
		// alert(sField);

		$('#' + sField + '_zapweb_upload_field').parents('.control-upload_iframe').show();
		$('#' + sField + '_zapweb_upload_field').parents('.control-upload_iframe').next('.control-upload_process').html('');

		if (o.resultCode == 1) {
			if (o.resultObject) {

				var sVal = $('#' + sField).val();
				if (sVal != "") {
					sVal = sVal + zapjs.c.split;
				}
				sVal = sVal + o.resultObject;

				$('#' + sField).val(sVal);

				zapweb_upload.upload_show(sField);
			}
		} else {
			alert(o.resultMessage);
		}

		$('#' + sField).parents('form').attr("enctype", "application/x-www-form-urlencoded");

	},
	// 上传提交
	upload_upload : function(sField) {

		var sVal = $('#' + sField + '_zapweb_upload_field').val();
		if (sVal == "") {
			return false;
		}

		//$('form').hide();

		//$('#'+sField+'_zapweb_upload_field').parents('.control-upload_iframe').hide();

		$('#' + sField).next('.control-upload_iframe').hide();
		$('#' + sField).nextAll('.control-upload_process').html('<span class="panel-loading">正在上传，请稍后……</span>');
		//$('#'+sField+'_zapweb_upload_field').parents('.control-upload_iframe').next('.control-upload_process').html('<span class="panel-loading">正在上传，请稍后……</span>');
		//$('#formsubmit').click();

		var sBaseUpload = 'upload';

		//判断指向
		var sUp = zapjs.f.upset($('#' + sField).attr(zapjs.c.field_attr + "set_params"), 'zw_s_target');
		if (sUp) {
			sBaseUpload = sUp;
		}

		var sAction = $('#' + sField).attr(zapjs.c.field_attr + "target_url") + sBaseUpload;

		var options = {
			url : sAction,
			type : "post",
			dataType : 'json',
			success : function(o) {

				zapweb_upload.upload_success(o, sField);
			},
			error : function(o) {
				alert('上传失败');
				zapweb_upload.upload_show(sField);
			}
		};
		$('#' + sField).parents('form').attr("enctype", "multipart/form-data");

		setTimeout(function() {
			$('#' + sField).parents('form').ajaxSubmit(options);
		}, 100);

		//$('#'+sField).parents('form').ajaxSubmit(options);

	}
};

if ( typeof define === "function" && define.amd) {
	define("zapweb/js/zapweb_upload", function() {
		return zapweb_upload;
	});
}

