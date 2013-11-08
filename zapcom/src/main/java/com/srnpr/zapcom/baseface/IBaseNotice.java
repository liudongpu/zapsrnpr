package com.srnpr.zapcom.baseface;

/**
 * 系统通知类调用
 * 
 * @author srnpr
 * 
 */
public interface IBaseNotice {

	/**
	 * 通知信息
	 * 
	 * @param sNoticeType
	 *            通知类型 调用者与被调用者相互沟通
	 * @param oNoticeObject
	 *            通知对象
	 * @return 通知的执行结果
	 */
	public boolean noticeInfo(String sNoticeType, Object oNoticeObject);

}
