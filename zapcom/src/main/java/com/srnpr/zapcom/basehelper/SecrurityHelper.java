package com.srnpr.zapcom.basehelper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecrurityHelper {

	/**
	 * MD5加密
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * MD5加密自定义
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5Customer(String s) {
		char hexDigits[] = { 'S', 'R', 'N', 'P', 'A', 'L', 'I', 'U', 'D', 'O',
				'B', 'G', 'C', 'D', '3', '1' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
    /*
     * 返回32位小写的MD5码
     */
     public static String getEncoderByMd5(String sessionid)
     throws NoSuchAlgorithmException, UnsupportedEncodingException {

     StringBuffer hexString = null;
     byte[] defaultBytes = sessionid.getBytes();
     try {
         MessageDigest algorithm = MessageDigest.getInstance("MD5");
         algorithm.reset();
         algorithm.update(defaultBytes);
         byte messageDigest[] = algorithm.digest();

         hexString = new StringBuffer();
         for (int i = 0; i < messageDigest.length; i++) {
             if (Integer.toHexString(0xFF & messageDigest[i]).length() == 1) {
                 hexString.append(0);
             }
             hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
         }
         messageDigest.toString();
         sessionid = hexString + "";
     } catch (NoSuchAlgorithmException nsae) {

     }
     return hexString.toString().toLowerCase();

    }

}
