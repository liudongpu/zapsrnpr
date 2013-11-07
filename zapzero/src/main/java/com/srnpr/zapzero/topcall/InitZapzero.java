package com.srnpr.zapzero.topcall;

import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapzero.server.ServerSync;

/**
 * 初始化
 * 
 * @author srnpr
 * 
 */
public class InitZapzero extends RootInit {

	public boolean init() {

		return new ServerSync().initServer();
	}

}
