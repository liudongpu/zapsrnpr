package com.srnpr.zapweb.webfunc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.ali.config.AlipayConfig;
import com.srnpr.zapweb.ali.util.AlipaySubmit;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 添加
 * 
 * @author hxd
 * 
 */
public class FunConfirmedReturn extends RootFunc {
	
	@Override
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();   
		MWebResult mResult = new MWebResult();
		mResult.setResultType("116018010");
		String notify_url = "/cmanage/notify_url.jsp";
		Date d = new Date();
		Timestamp nousedate = new Timestamp(d.getTime());
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		String idArray = mDataMap.get("sData");
		if(StringUtils.isBlank(idArray))
		{
			mResult.setResultCode(969912004);
			mResult.setResultMessage(bInfo(969912004));
			return mResult;
		}
		List<Map<String, Object>> list = getUidList(idArray);
		boolean flag =false;
		for(int i = 0; i<list.size();i++)
		{
			if("true".equals(list.get(i).get("return_conf")))
				flag = true;
			continue;
		}
		if(flag)
		{
			mResult.setResultCode(969912004);
			return mResult;
		}
		
		String sHtmlText = createData(mDataMap, notify_url, nousedate, df1,idArray);
		mResult.setResultObject("returnMsg('"+ sHtmlText+ "')");
		request.setAttribute("sHtmlText", sHtmlText);		
		
		MDataMap mp = new MDataMap();
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
				mp.put("return_conf", "true"); // 
				mp.put("return_money_code", idArray.split(",")[i]);
				DbUp.upTable("oc_return_money").dataUpdate(mp, "return_conf", "return_money_code");
			}
		}
		return mResult;
	}
	
	
	
	//生成提交数据
	private String createData(MDataMap mDataMap, String notify_url,
			Timestamp nousedate, SimpleDateFormat df1, String idArray) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(new java.util.Date());
		Map<String, String> sParaTemp = new HashMap<String, String>();
		String detail_data = "";
		if(StringUtils.isNotEmpty(mDataMap.get("sData")))
		{
			//idArray = mDataMap.get("sData");
			String[] a = idArray.split(",");
			List<String> stooges = Arrays.asList(a);
			String sql = "";
			for(String m : stooges)
			{
				if(StringUtils.isBlank(sql))
				{
					sql =sql +"return_money_code ='" +m+"'";
				}
				else if( !StringUtils.isBlank(sql))
				{
					sql = sql +" or return_money_code ='"+m+"'";
				}
			}
			String ssql = "select * from oc_return_money_detail where " +sql;
			List<Map<String, Object>> dataSqlList = DbUp.upTable("oc_return_money_detail").dataSqlList(ssql, new MDataMap());
			
			sParaTemp.put("service", bConfig("zapweb.service"));
	        sParaTemp.put("partner", AlipayConfig.partner);
	        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("seller_email", bConfig("zapweb.seller_email"));
			sParaTemp.put("refund_date", nowTime);
			sParaTemp.put("batch_no", df1.format(new Date()).toString() + nousedate.toString().replace(" ", "")
					.replace("-", "")
					.replace(":", "")
					.replace(".", ""));
			
			sParaTemp.put("batch_num", String.valueOf(a.length));
			for(int i=0;i<dataSqlList.size();i++)
			{
				if(StringUtils.isBlank(detail_data)) 
				{
					detail_data = detail_data+dataSqlList.get(i).get("return_seq")+"^"+dataSqlList.get(i).get("return_money") +"^" +"";
				}
				else
				{
					detail_data =detail_data +"#"+dataSqlList.get(i).get("return_seq")+"^"+dataSqlList.get(i).get("return_money") +"^" +"";
				}
				
			}
			sParaTemp.put("detail_data", detail_data);
		}
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认");
		return sHtmlText;
	}
	
	
	// 通过moneyCode获取uid
	private List<Map<String, Object>> getUidList(String code)
	{
		String sSql =  "select * from oc_return_money where ";
		String tmf = getSql(code, "return_money_code");
		return DbUp.upTable("oc_return_money").dataSqlList(sSql+tmf, new MDataMap());
	}
	//拼接sql
	private String getSql(String str,String codeName)
	{
		String sql = "";
		for(int i=0;i<str.split(",").length;i++)
		{
			if(StringUtils.isBlank(sql))
			{
				sql = sql + codeName +" ='" +str.split(",")[i]+"'";
			}
			else
			{
				sql =  sql +" or " +codeName   +" '='" +str.split(",")[i];
			}
		}
		return sql;
	}
	
	
	private String getNowTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(new java.util.Date());
		return nowTime;
	}
}
