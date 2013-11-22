package com.srnpr.zapcom.basehelper;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseHelper;
import com.srnpr.zapcom.basemodel.MDataMap;

public class MapHelper implements IBaseHelper {

	
	public static boolean isNotEmpty(MDataMap mDataMap)
	{
		return mDataMap!=null&&mDataMap.size()>0;
	}
	
	
	
}
