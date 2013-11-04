package com.srnpr.zapzero.topcall;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.observable.ConfigObservable;
import com.srnpr.zapcom.rootclass.RootInit;

public class InitZapzero extends RootInit implements Observer {

	public boolean init() {
		return initServer();
	}

	/**
	 * 初始化运行模式
	 * 
	 * @return
	 */
	private boolean initServer() {

		boolean bFlagReturn = true;

		if (bFlagReturn) {
			String sServerType = bConfig("default.local_server_type");
			bLogInfo(970212010, sServerType);

			// 如果加载的是跟随者 则开始连接主服务器的配置
			if (sServerType.equals("follower")) {

				bLogInfo(970212011, bConfig("default.leader_server_address"));

				ConfigObservable.INSTANCE.addObserver(this);

				bFlagReturn = doUpdateConfig();
			}

		}

		return bFlagReturn;

	}

	private boolean doUpdateConfig() {

		return true;
	}

	public void update(Observable o, Object arg) {

		doUpdateConfig();

	}

}
