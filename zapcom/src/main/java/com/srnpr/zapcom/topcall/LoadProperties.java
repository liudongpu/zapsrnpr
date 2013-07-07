package com.srnpr.zapcom.topcall;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topdo.TopBase;

public class LoadProperties extends TopBase {

	public MStringMap loadMap(String sDir) {

		MStringMap mReturnMap = new MStringMap();

		@SuppressWarnings({ "unchecked" })
		Collection<File> files = FileUtils.listFiles((new File(sDir)),
				new String[]{"properties"}, true);
		try {
			for (File f : files) {
				PropertiesConfiguration pConfiguration = new PropertiesConfiguration();

				FileInputStream fInputStream = FileUtils.openInputStream(f);

				pConfiguration.load(fInputStream, "UTF-8");

				Iterator<String> em = pConfiguration.getKeys();

				String sNameSpace=StringUtils.defaultString(pConfiguration.getString("@namespace"),"");
				
				while (em.hasNext()) {
					String sKeyString = em.next();
					String sValueString = new String(pConfiguration
							.getProperty(sKeyString).toString());

					
					if(StringUtils.isNotEmpty(sNameSpace))
					{
						if(!StringUtils.startsWith(sKeyString, "$")&&!StringUtils.startsWith(sKeyString, sNameSpace))
						{
							sKeyString=sNameSpace+"."+sKeyString;
						}
					}
					
					// String
					// sValueString=pJarConfiguration.getString(sKeyString);
					
					mReturnMap.put(sKeyString, sValueString);

				}

				fInputStream.close();
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return mReturnMap;

	}

}
