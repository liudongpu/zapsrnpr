package com.srnpr.zapweb.webfunc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.kuaiqian.BillHttp;
import com.srnpr.zapweb.kuaiqian.KqProperties;
import com.srnpr.zapweb.kuaiqian.MD5Util;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmodel.MWebResult;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 添加
 * 
 * @author hxd
 * 
 */
public class Fun99Bill extends RootFunc {
	@Override
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult = new MWebResult();
		MDataMap mWindowMap = mDataMap.upSubMap("window_change_password_");
		String returnMoney_code  = mWindowMap.get("returnMoney_code");
		MDataMap map = DbUp.upTable("oc_return_money_detail").one("return_money_code",returnMoney_code);
		KqProperties pro = new KqProperties();
		//商户编号，线上的话改成你们自己的商户编号的，发到商户的注册快钱账户邮箱的
		pro.setMerchant_id(map.get("merchant_id"));
		//退款接口版本号 目前固定为此值
		pro.setVersion(bConfig("zapweb.version"));
		//操作类型
		pro.setCommand_type(bConfig("zapweb.command_type"));
		//加密所需的key值，线上的话发到商户快钱账户邮箱里
		pro.setMerchant_key(bConfig("zapweb.merchant_key"));
		//原商户订单号
		pro.setOrderid(map.get("order_code"));
		//退款金额，整数或小数，小数位为2位   以人民币元为单位
		pro.setAmount(getAmountByCode(returnMoney_code));  
		//退款提交时间 数字串，一共14位 格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2位]
		pro.setPostdate(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
		//退款流水号  字符串
		pro.setTxOrder(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
		//生成加密签名串
		String macVal = "";
		macVal = appendParam(macVal, "merchant_id", pro.getMerchant_id());
		macVal = appendParam(macVal, "version", pro.getVersion());
		macVal = appendParam(macVal, "command_type", pro.getCommand_type());                                                  
		macVal = appendParam(macVal, "orderid", pro.getOrderid());
		macVal = appendParam(macVal, "amount", pro.getAmount());
		macVal = appendParam(macVal, "postdate",pro.getPostdate());
		macVal = appendParam(macVal, "txOrder", pro.getTxOrder());
		macVal = appendParam(macVal,"merchant_key",pro.getMerchant_key());   
		try {
			pro.setMac(MD5Util.md5Hex(macVal.getBytes("utf-8")).toUpperCase());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//锁表 添加日志
		lockAndlog(returnMoney_code);
		String url="https://sandbox.99bill.com/webapp/receiveDrawbackAction.do";
		Map<String, String> params = new HashMap<String, String>();
		params.put("merchant_id", pro.getMerchant_id());
		params.put("version", pro.getVersion());
		params.put("command_type", pro.getCommand_type());
		params.put("txOrder", pro.getTxOrder());
		params.put("amount", pro.getAmount());
		params.put("postdate", pro.getPostdate());
		params.put("orderid", pro.getOrderid());
		params.put("payeeidsrc", mWindowMap.get("payeeidsrc"));
		params.put("mac", pro.getMac());
		String msg = BillHttp.doPost(url, params, "utf8");
        mResult.setResultMessage(msg.substring(msg.indexOf("<CODE>")+6,msg.indexOf("</CODE>")));
		return mResult;
	}

	private String appendParam(String returns, String paramId, String paramValue) {
		if (returns != "") {
			if (paramValue != "") {

				returns +=  paramId + "=" + paramValue;
			}

		} else {

			if (paramValue != "") {
				returns = paramId + "=" + paramValue;
			}
		}

		return returns;
	}
	
	
	private String getAmountByCode(String moneyCode)
	{
		MDataMap map = DbUp.upTable("oc_return_money").one("return_money_code",moneyCode);
		
		return map.get("online_money");
	}
	
	
	//锁表 添加日志
		private void lockAndlog(String idArray) {
			MDataMap mp = new MDataMap();
			MDataMap mp1 = new MDataMap();
			if(StringUtils.isNotBlank(idArray))
			{
				for(int i=0;i<idArray.split(",").length;i++)
				{
					//锁表
					WebHelper.addLock(idArray.split(",")[i], 60);
					//添加日志
					mp.put("return_money_no", idArray.split(",")[i]);
					mp.put("info", "财务退款确认");
					mp.put("create_time", getNowTime());
					mp.put("create_user", UserFactory.INSTANCE.create().getLoginName());
					DbUp.upTable("lc_return_money_status").dataInsert(mp);
					//更新状态
					mp1.put("return_conf", "是"); // 
					mp1.put("return_money_code", idArray.split(",")[i]);
					DbUp.upTable("oc_return_money").dataUpdate(mp1, "return_conf", "return_money_code");
				}
			}
		}
		
		private String getNowTime()
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = df.format(new java.util.Date());
			return nowTime;
		}
	    /**
         * 执行一个HTTP POST请求，返回请求响应的HTML
         *
         * @param url         请求的URL地址
         * @param params    请求的查询参数,可以为null
         * @param charset 字符集
         * @param pretty    是否美化
         * @return 返回请求响应的HTML
         */
        public static String doPost(String url, Map<String, String> params, String charset, boolean pretty) {
                StringBuffer response = new StringBuffer();
                HttpClient client = new HttpClient();
                HttpMethod method = new PostMethod(url);
                //设置Http Post数据
                if (params != null) {
                        HttpMethodParams p = new HttpMethodParams();
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                                p.setParameter(entry.getKey(), entry.getValue());
                        }
                        method.setParams(p);
                }
                try {
                        client.executeMethod(method);
                        if (method.getStatusCode() == HttpStatus.SC_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
                                String line;
                                while ((line = reader.readLine()) != null) {
                                        if (pretty)
                                                response.append(line).append(System.getProperty("line.separator"));
                                        else
                                                response.append(line);
                                }
                                reader.close();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        method.releaseConnection();
                }
                return response.toString();
        } 
}
