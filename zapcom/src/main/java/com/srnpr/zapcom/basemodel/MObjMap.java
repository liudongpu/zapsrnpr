package com.srnpr.zapcom.basemodel;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MObjMap<K, V> extends ConcurrentHashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2251639910081587304L;

	@SuppressWarnings("unchecked")
	public K[] upKeys() {

		List<K> lReturnsKs = new ArrayList<K>();
		Enumeration<K> eKey = this.keys();

		while (eKey.hasMoreElements()) {
			lReturnsKs.add(eKey.nextElement());

		}
		return (K[]) lReturnsKs.toArray();

	}

}
