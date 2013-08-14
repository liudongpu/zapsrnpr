var zapadmin = {

	temp : {
		// 当前菜单编号
		now_menu_id : '',
		// 页面路径
		iframe_urls : []
	},

	autoheight : function(down) {

		var pTar = null;
		if (document.getElementById) {
			pTar = document.getElementById(down);
		} else {
			eval('pTar = ' + down + ';');
		}
		if (pTar && !window.opera) {
			// begin resizing iframe
			pTar.style.display = "block";
			if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
				// ns6 syntax
				pTar.height = pTar.contentDocument.body.offsetHeight + 20;
				pTar.width = pTar.contentDocument.body.scrollWidth;
			} else if (pTar.Document && pTar.Document.body.scrollHeight) {
				// ie5+ syntax
				pTar.height = pTar.Document.body.scrollHeight;
				pTar.width = pTar.Document.body.scrollWidth;
			}
		}
	},

	load_complate : function(oTarget) {
		var sUrl = oTarget.contentWindow.document.location.href;
		this.temp.iframe_urls.push(sUrl);
	},
	back_url : function() {
		this.temp.iframe_urls.pop();
	
		if (this.temp.iframe_urls.length > 0) {
			var sUrl=this.temp.iframe_urls[this.temp.iframe_urls.length-1];
			
			 document.getElementById(zapjs.c.main_iframe).contentWindow.document.location.href=sUrl;
				this.temp.iframe_urls.pop();
		}

	},

	top_menu : function(eTarget, sMenu) {
		$('.zab_home_home_top .c_nav .c_active').removeClass('c_active');
		$(eTarget).parent('li').addClass('c_active');

		if (sMenu) {
			$('.zab_home_home_left .c_item').hide();

			this.temp.now_menu_id = sMenu;

			// $('#home_menu_box_'+this.temp.now_menu_id).slideDown('slow');
			$('#home_menu_box_' + this.temp.now_menu_id).show();
		}

	},
	menu_click : function(eTarget) {
		$('.zab_home_home_left .c_active').removeClass('c_active');
		$(eTarget).parent('li').addClass('c_active');
	}

};