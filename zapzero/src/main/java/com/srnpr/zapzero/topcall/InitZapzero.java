package com.srnpr.zapzero.topcall;

import com.srnpr.zapcom.basesupport.JobSupport;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapzero.server.ServerSync;
import com.srnpr.zapzero.support.JmsConnection;

/**
 * 初始化
 * 
 * @author srnpr
 * 
 */
public class InitZapzero extends RootInit {

	@Override
	public boolean onInit() {

		return new ServerSync().initServer();
	}

	@Override
	public boolean onDestory() {

		JobSupport.getInstance().shutDown();

		new JmsConnection().destory();

		return true;
	}

}
