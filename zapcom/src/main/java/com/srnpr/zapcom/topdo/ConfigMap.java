package com.srnpr.zapcom.topdo;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootCache;

public class ConfigMap extends RootCache<String, MStringMap> {

	public synchronized void refresh() {

		TopConfig tConfig = new TopConfig();

		if (tConfig.upKeys().size() == 0) {
			tConfig.refresh();
		}

		for (String s : tConfig.upKeys()) {
			if (StringUtils.indexOf(s, "[") > -1) {
				String sTopKey = StringUtils.substringBefore(s, "[");

				if (!this.containsKey(sTopKey)) {
					this.inElement(sTopKey, new MStringMap());
				}

				String sSubKeyString = StringUtils
						.substringBetween(s, "[", "]");

				this.upValue(sTopKey).put(sSubKeyString, tConfig.upValue(s));

			}
		}

	}

	@Override
	public MStringMap upOne(String k) {

		return null;
	}

}
