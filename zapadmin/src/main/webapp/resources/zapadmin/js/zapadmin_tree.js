var zapadmin_tree = {
	temp : {
		step : 4,
		data : []
	},

	tree_init : function(oElm) {

		zapadmin_tree.temp.step = $('#zw_page_tree_zw_s_step').val();

		$.get($('#zw_page_tree_zw_s_jsonchart').val(), function(result) {

			zapadmin_tree.temp.data = result;

			zapadmin_tree.tree_show(result);
		});

	},

	tree_show : function(oData) {
		/*
		var x = [];
		var step = [];
		var iStart = oData[0][0].length / zapadmin_tree.temp.step;
		x.push({
			id : oData[0][0],
			text : oData[0][1],
			attributes : {
				uid : oData[0][3]
			}
		});
		step[0] = x[0];
		for ( var i = 1, j = oData.length; i < j; i++) {
			var oEvery = oData[i];
			var iDept = oEvery[0].length / zapadmin_tree.temp.step - iStart;
			if (!step[iDept - 1].hasOwnProperty("children")) {
				step[iDept - 1].children = [];
			}
			if (step[iDept - 1].children) {
				step[iDept - 1].children.push({
					id : oEvery[0],
					text : oEvery[1],
					attributes : {
						uid : oEvery[3]
					}
				});
			}
			step[iDept] = step[iDept - 1].children[step[iDept - 1].children.length - 1];

		}*/
		var x=zapadmin.tree_data(oData);

		$('#zw_page_common_tree').tree(
				{
					data : x,
					onClick : function(node) {
						$.get($('#zw_page_tree_zw_s_bookpage').val() + "?"
								+ $('#zw_page_tree_zw_s_uid').val() + '='
								+ node.attributes.uid, function(result) {
							$('#zw_page_tree_right').html(result);
						});
					}

				});

	},
	tree_edit : function(oTag) {
		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {
			$.get($('#zw_page_tree_zw_s_editpage').val() + "?"
					+ $('#zw_page_tree_zw_s_uid').val() + '='
					+ node.attributes.uid, function(result) {
				$('#zw_page_tree_right').html(result);
			});

		} else {
			zapadmin.model_message('请选择后在进行操作！');
		}
	},

	tree_delete : function(oTag) {
		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {

			var bFlag = false;
			var sId = node.id;

			var oData = zapadmin_tree.temp.data;
			for ( var i = 0, j = oData.length; i < j; i++) {
				if (oData[i][2] == sId) {
					bFlag = true;
				}
			}

			if (bFlag) {
				zapadmin.model_message('有子节点不允许删除！');
			} else {
				zapjs.zw.func_delete(oTag, node.attributes.uid);
			}

		} else {
			zapadmin.model_message('请选择后在进行操作！');
		}
	},

	tree_add : function(oTag) {

		var node = $('#zw_page_common_tree').tree('getSelected');
		if (node) {
			var sId = node.id;

			var iMax = 0;

			var oData = zapadmin_tree.temp.data;
			for ( var i = 0, j = oData.length; i < j; i++) {
				if (oData[i][2] == sId && oData[i][0] > iMax) {
					iMax = oData[i][0].substr(oData[i][0].length
							- zapadmin_tree.temp.step);
				}
			}

			iMax = (parseInt(iMax) + 1).toString();

			for ( var i = 0, j = (zapadmin_tree.temp.step - iMax.length); i < j; i++) {
				iMax = "0" + iMax;

			}

			iMax = sId + iMax;

			$.get($('#zw_page_tree_zw_s_addpage').val() + "?"
					+ $('#zw_page_tree_zw_s_parent').val() + '=' + sId + "&"
					+ $('#zw_page_tree_zw_s_code').val() + '=' + iMax,
					function(result) {
						$('#zw_page_tree_right').html(result);
					});

		} else {

			zapadmin.model_message('请选择后在进行操作！');
		}

	}

};
