package com.srnpr.zapcom.topdo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootInit;

/**
 * 初始化类
 * 
 * @author srnpr
 */
public class TopInit extends RootInit implements IBaseInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseInit#init()
	 */
	public synchronized void init() {

		initDelete();
		initTop();
		initClass();

	}

	/**
	 * 初始化删除操作
	 */
	private void initDelete() {
		String sZapDirString = new TopDir().upTempDir("");
		try {
			FileUtils.deleteDirectory(new File(sZapDirString));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 初始化顶级配置
	 */
	private void initTop() {
		topInitCache(new TopConfig(),new TopInfo());
	}

	private void initClass() {

		String sConfigName = "zapcom.initclass";

		MStringMap mStringMap = TopUp.upConfigMap(sConfigName);

		for (String sClassName : mStringMap.values()) {

			if (!StringUtils.isEmpty(sClassName)) {
				try {

					Class<?> cClass = ClassUtils.getClass(sClassName);
					if (cClass != null && cClass.getDeclaredMethods() != null) {
						IBaseInit init = (IBaseInit) cClass.newInstance();
						init.init();
					}
				} catch (Exception e) {

					bLogInfo(967905001, sClassName);
					e.printStackTrace();

				}
			}

		}
	}

}
