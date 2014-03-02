package com.srnpr.zapcom.basehelper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.srnpr.zapcom.baseface.IBaseHelper;

public class NetHelper implements IBaseHelper {

	/**
	 * 获取本机IP
	 * 
	 * @return
	 */
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
