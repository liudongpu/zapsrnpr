package com.srnpr.zapcom.testconfig;

import static org.junit.Assert.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.junit.Test;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.topdo.TopDir;

public class TestConfig extends BaseClass {

	
	public void test() {

		TopDir topDir = new TopDir();

		bLogInfo(0, topDir.upCustomPath("config/"));

		String sConfig = bConfig("baby");
		bLogInfo(0, sConfig);

		bLogInfo(0, getLocalIP());
	}

	public static String getLocalIP() {
		String address = "";
		try {
			Enumeration<?> allNetInterfaces = NetworkInterface
					.getNetworkInterfaces();
			InetAddress ine = null;
			while (allNetInterfaces.hasMoreElements() && address.equals("")) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
						.nextElement();
				if (!netInterface.isVirtual()) {
					Enumeration<?> addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements() && address.equals("")) {
						ine = (InetAddress) addresses.nextElement();
						if (ine != null && ine instanceof Inet4Address) {
							if (!ine.getHostAddress().equals("127.0.0.1")
									&& !netInterface.isVirtual()) {
								address = ine.getHostAddress();
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address.trim();
	}

}
