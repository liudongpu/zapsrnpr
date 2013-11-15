var zs_template_set = {
	//顶部导航大图
	set_headerlogo : function() {

		var oItem = zs_template.temp.currentItem;

		zs.r(["zapweb/js/zapweb_upload"], function(a) {
			var aHtml = zapweb_upload.upload_html(zapjs.c.path_upload, 'zw_f_headerlogo', '', '');
			var aContent = [];
			aContent.push('<div class="zat_template_headerlogo w_p_20">');
			aContent.push(aHtml);
			aContent.push('<div class="w_clear"></div><a class="l-btn" href="javascript:zs_template_set.success_headerlogo()"><span class="l-btn-left">确认</span></a>');
			aContent.push('</div>');
			zapjs.f.window_box({
				content : aContent.join('')
			});

			zapweb_upload.upload_file('zw_f_headerlogo');

		});

	},
	success_headerlogo : function() {
		if ($('#zw_f_headerlogo').val() != '') {

			zs_template.view_html('<img src="' + $('#zw_f_headerlogo').val() + '"/>');

			zapjs.f.window_close();
			zs_template.save_html();

		} else {
			zapjs.f.modal({
				content : '请先选择图片'
			});
		}

	},
	//商品列表
	set_productlist : function() {

		var aHtml = [];
		aHtml.push('<div class="zat_template_productlist w_p_20">');
		aHtml.push('<input type="hidden" id="zat_template_productlist_productids"/>');
		aHtml.push('<input type="hidden" id="zat_template_productlist_productids_show_text"/>');
		//aHtml.push('<a class="l-btn" href="javascript:zs_template_set.add_product()"><span class="l-btn-left">选择商品</span></a>');
		aHtml.push('</div>');
		zapjs.f.window_box({
			id:'zs_template_set_add_product_id',
			content : aHtml.join('')
		});
		zs.r(['zapadmin/js/zapadmin_single'],function(a){a.init({"text":"","source":"page_chart_v_seller_pc_skuinfo","id":"zat_template_productlist_productids","callback":"parent.zs_template_set.add_product","value":"","max":"0"});});
	},
	add_product : function(sId,sVal) {

		zapjs.f.window_close('zs_template_set_add_product_id');
		//alert(sId);
		
		zapjs.zw.api_call('com_cmall_productcenter_service_api_ApiGetSkus',{skuStrs:sVal},zs_template_set.add_product_success);
		
		

	},
	add_product_success:function(o)
	{
		
		zapjs.d(o);
	},
	
	close_product:function()
	{
		zapjs.f.window_close('zs_template_set_add_product_id');
	}
};

zs.f.define("zs/template/zs_template_set", [], zs_template_set);

