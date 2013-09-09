/*
 * zapjs.zw

 */

zapjs.zw = {

	modal_show : function(oSet) {
		top.zapjs.f.modal(oSet);
	},

	// 添加函数调用
	func_add : function(oElm) {
		zapjs.zw.func_call(oElm);
	},

	// 修改函数调用
	func_edit : function(oElm) {

		zapjs.zw.func_call(oElm);

	},

	// 删除函数调用
	func_delete : function(oElm, sUid) {

		zapjs.zw.func_do(oElm, null, {
			zw_f_uid : sUid
		});

	},

	// 提交操作
	func_call : function(oElm) {
		zapjs.f.ajaxsubmit($(oElm).parents("form"), "../func/"
				+ $(oElm).attr('zapweb_attr_operate_id'),
				zapjs.zw.func_success, zapjs.zw.func_error);
	},

	func_do : function(oElm, sOperate, data) {

		if (!sOperate) {
			sOperate = $(oElm).attr('zapweb_attr_operate_id');
		}

		if (!data) {
			data = {};
		}

		$.getJSON("../func/" + sOperate, data, function(data) {
			zapjs.zw.func_success(data);
		});
	},

	func_success : function(o) {

		switch (o.resultType) {
		case "116018010":
			eval(o.resultObject);
			break;
		default:
			// alert(o.resultMessage);
			zapjs.zw.modal_show({
				content : o.resultMessage
			});
			break;
		}

	},

	func_export : function() {

		zapjs.f.tourl(zapjs.f.upurl().replace("/page/", "/export/"));
	},

	func_error : function(o) {
		alert('系统出现错误，请联系技术，谢谢！');
	},
	func_inquire : function(oElm) {

		var queryString = $(oElm).parents("form").formSerialize();

		zapjs.f.tourl(this.url_inquire(queryString));
		// $(oElm).parents("form").submit();
	},
	url_inquire : function(sQueryString) {
		var sSplit = sQueryString.split('&');

		var sUrl = zapjs.f.upurl();

		for ( var i = 0, j = sSplit.length; i < j; i++) {
			var sEq = sSplit[i].split('=');
			if (sEq[1] != "" || sUrl.indexOf(sEq[0]) > -1) {
				sUrl = zapjs.f.urlreplace(sUrl, sEq[0], sEq[1]);
			}

		}

		sUrl = zapjs.f.urlreplace(sUrl, zapjs.c.web_paginaion + 'index', '');
		sUrl = zapjs.f.urlreplace(sUrl, zapjs.c.web_paginaion + 'count', '');
		return sUrl;

	},

	upExtend : function(sId, sStart) {
		return $('#' + sId).attr(zapjs.c.web_extend + sStart);
	},
	// 编辑器提交时调用扩展
	editorsubmit : function() {
		for (instance in CKEDITOR.instances) {
			CKEDITOR.instances[instance].updateElement();
		}
		return true;
	},

	editor_show : function(sFieldName, sUploadUrl) {

		zapjs.f.setdomain();
		require([ 'lib/ckeditor/ckeditor' ], function(a) {
			require([ 'lib/ckeditor/adapters/jquery' ], function(c) {
				$('#' + sFieldName).ckeditor({
					filebrowserImageUploadUrl : sUploadUrl + 'editor'
				});
			});
		});
		zapjs.e('zapjs_e_zapjs_f_ajaxsubmit_submit', zapjs.zw.editorsubmit);

	},

	upload_file : function(sFieldName, sUploadUrl) {

		zapjs.f.setdomain();
		// sUploadUrl = "../upload/";
		if ($('#' + sFieldName).val() == "") {
			$('#' + sFieldName)
					.after(
							'<span><iframe src="'
									+ sUploadUrl
									+ 'upload?zw_s_source='
									+ sFieldName
									+ '" class="zw_page_upload_iframe" frameborder="0"></iframe></span>');
		}

		zapjs.zw.upload_show(sFieldName);

	},

	upload_result : function(o) {
		zapjs.f.setdomain();
		parent.zapjs.zw.upload_success(o, zapjs.f.urlget('zw_s_source'));
	},

	upload_show : function(sField) {

		if (1 == 1) {
			var sFiles = $('#' + sField).val().split(zapjs.c.split);

			if ($('#' + sField).val()!=""&&sFiles.length > 0) {

				var aHtml = [];

				aHtml.push('<ul>');

				for ( var i in sFiles) {
					aHtml
							.push('<li><div class="control-upload-image"><img src="'
									+ sFiles[i]
									+ '" /></div><div class="control-upload-delete"><span class="btn btn-mini " onclick="zapjs.zw.upload_delete(\''
									+ sField
									+ '\','
									+ i
									+ ')"><i class="icon-trash "></i>&nbsp;&nbsp;删除</span></div></li>');
				}

				aHtml.push('</ul>');

				$('#' + sField).nextAll('.control-upload').html(aHtml.join(''));
			} else {
				$('#' + sField).nextAll('.control-upload').html('');
			}
		}
	},

	upload_delete : function(sField, iIndex) {

		var sFiles = $('#' + sField).val().split(zapjs.c.split);

		sFiles.splice(iIndex, 1);

		$('#' + sField).val(sFiles.join(zapjs.c.split));

		zapjs.zw.upload_show(sField);
	},

	upload_success : function(o, sField) {
		// alert(sField);

		// alert(o.resultObject);

		var sVal = $('#' + sField).val();
		if (sVal != "") {
			sVal = sVal + zapjs.c.split;
		}
		sVal = sVal + o.resultObject;

		$('#' + sField).val(sVal);

		zapjs.zw.upload_show(sField);

	},

	upload_upload : function(oElm) {
		$('#formsubmit').click();

	}

};

if (typeof define === "function" && define.amd) {
	define("zapjs/zapjs.zw", [ "zapjs/zapjs" ], function() {
		return zapjs.zw;
	});
}
