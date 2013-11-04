package com.srnpr.zapcom.rootclass;

import java.util.Observable;

public class RootObservable extends Observable {

	/**
	 * 更新调用
	 * 
	 * @param o
	 */
	public void doUpdate(Object o) {
		if (true) {
			super.setChanged();
		}
		notifyObservers(o);
	}

}
