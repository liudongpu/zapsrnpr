package com.srnpr.zapweb.webface;

import com.srnpr.zapweb.webmodel.MWebResult;

public interface IWebFace<T,R> {

	public R DoProcess(T t);

}
