package com.srnpr.zapweb.webface;

import com.srnpr.zapweb.webmodel.MWebResult;

public interface IWebNotice {

	public MWebResult noticeEvent(String sEventName, IWebInput input);

}
