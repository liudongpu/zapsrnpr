package com.srnpr.zapzero.topcall;

import java.util.Observable;
import java.util.Observer;

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

		bLogInfo(970212010, bConfig("default.local_server_type"));
		bLogInfo(970212011, bConfig("default.leader_server_address"));

		ConfigObservable.INSTANCE.addObserver(this);

		// update(null, null);

		return doUpdateConfig();

	}

	private boolean doUpdateConfig() {

		
		
		
		
		
		return true;
	}

	public void update(Observable o, Object arg) {

		doUpdateConfig();

	}

}
