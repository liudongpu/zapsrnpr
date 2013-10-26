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

		return '<input type="hidden" zapweb_attr_target_url="' + sTargetUpload + '"  zapweb_attr_set_params="' + sSet + '"  id="' + sId + '" name="' + sId + '" value="' + sValue + '"><span class="control-upload_iframe"></span><span class="control-upload"></span>';

	},

	// 上传文件
	upload_file : function(sFieldName, sUploadUrl) {

		//zapjs.f.setdomain();
		// sUploadUrl = "../upload/";

		zapweb_upload.upload_show(sFieldName);

	},

	is_image : function(sFix) {

		var sImageFile = ".jpg;.png;.jpeg;.bmp;.gif;";

		return sImageFile.indexOf('.' + sFix + ';') > -1;

	},

	// 上传文件结果
	upload_result : function(o) {
		//zapjs.f.setdomain();
		parent.zapweb_upload.upload_success(o, zapjs.f.urlget('zw_s_source'));
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

		var sUploadTarget = zapjs.f.upset($('#' + sField).attr(zapjs.c.field_attr + "set_params"), 'zw_s_target');
		if (!sUploadTarget) {
			sUploadTarget = "upload";
		}

		if ($('#' + sField).val() == "" || bFlagMul) {

			if ($('#' + sField).nextAll('.control-upload_iframe').html() == "") {
				var sUploadUrl = $('#' + sField).attr(zapjs.c.field_attr + "target_url");

				$('#' + sField).nextAll('.control-upload_iframe').html('<iframe src="' + sUploadUrl + sUploadTarget + '?zw_s_source=' + sField + '" class="zw_page_upload_iframe" frameborder="0"></iframe>');
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

		// alert(o.resultObject);

		if (o.resultCode == 1) {
			if (o.resultObject) {

				var sVal = $('#' + sField).val();
				if (sVal != "") {
					sVal = sVal + zapjs.c.split;
				}
				sVal = sVal + o.resultObject;

				$('#' + sField).val(sVal);

				zapweb_upload.upload_show(sField);

				if (zapjs.f.callextend("zapjs_e_zapweb_upload_upload_success")) {

				}

			}
		} else {
			alert(o.resultMessage);
		}

	},
	// 上传提交
	upload_upload : function(oElm) {

		$('form').hide();
		$('body').append('<span class="panel-loading">正在上传，请稍后……</span>');
		$('#formsubmit').click();
	}
};

if ( typeof define === "function" && define.amd) {
	define("zapweb/js/zapweb_upload", function() {
		return zapweb_upload;
	});
}

