package com.srnpr.zapcom.topdo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.baseface.IBaseDestory;
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
	public synchronized boolean onInit() {

		initDelete();
		initTop();
		return initClass();

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
		topInitCache(TopConfig.Instance, new TopInfo());
	}

	/**
	 * 初始化加载各个类
	 * 
	 * @return
	 */
	private boolean initClass() {

		boolean bFlagInit = true;

		String sConfigName = "zapcom.initclass";

		MStringMap mStringMap = TopUp.upConfigMap(sConfigName);

		for (String sClassName : mStringMap.values()) {

			if (!StringUtils.isEmpty(sClassName)) {
				try {

					Class<?> cClass = ClassUtils.getClass(sClassName);
					if (cClass != null && cClass.getDeclaredMethods() != null) {
						IBaseInit init = (IBaseInit) cClass.newInstance();
						if (!init.init()) {
							bFlagInit = false;
						}
					}
				} catch (Exception e) {

					bFlagInit = false;
					bLogInfo(967905001, sClassName);
					e.printStackTrace();

				}
			}

		}

		return bFlagInit;
	}

	@Override
	public boolean onDestory() {
		boolean bFlagInit = true;

		String sConfigName = "zapcom.initclass";

		MStringMap mStringMap = TopUp.upConfigMap(sConfigName);

		for (String sClassName : mStringMap.values()) {

			if (!StringUtils.isEmpty(sClassName)) {
				try {

					Class<?> cClass = ClassUtils.getClass(sClassName);
					if (cClass != null && cClass.getDeclaredMethods() != null) {
						IBaseDestory init = (IBaseDestory) cClass.newInstance();
						if (!init.destory()) {
							bFlagInit = false;
						}
					}
				} catch (Exception e) {

					bFlagInit = false;
					bLogInfo(967905001, sClassName);
					e.printStackTrace();

				}
			}

		}

		return bFlagInit;
	}

}
