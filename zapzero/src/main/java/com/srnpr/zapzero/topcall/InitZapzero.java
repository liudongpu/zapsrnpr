package com.srnpr.zapzero.topcall;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapcom.basehelper.IoHelper;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapcom.topdo.TopDir;

public class InitZapzero extends RootInit {

	public boolean init() {
		return initServer();
	}

	/**
	 * 初始化运行模式
	 * @return
	 */
	private boolean initServer() {

		bLogInfo(970212010, bConfig("default.local_server_type"));
		bLogInfo(970212011, bConfig("default.leader_server_address"));
		

		return true;

	}

}
