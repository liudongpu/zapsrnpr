/**
 * 本地临时存储
 */

var zs_storage = {

	temp : {
		flag : -1
	},
	/*
	 * 判断是否本地存储可用
	 */
	flag_enable : function() {
		if (zs_storage.temp.flag == -1) {
			if ( typeof window.localStorage == 'object')
				zs_storage.temp.flag = 1;
			else
				zs_storage.temp.flag = 0;
		}
		return zs_storage.temp.flag == 1;
	},
	save : function(sName, oTarget) {
		if (!zs_storage.flag_enable()) {
			return false;
		}

		
		localStorage.setItem(sName, oTarget);

	},
	read : function(sName) {
		if (!zs_storage.flag_enable()) {
			return false;
		}
		return localStorage.getItem(sName);
	}
};

zs.f.define("zs/storage/zs_storage", [], zs_storage);

