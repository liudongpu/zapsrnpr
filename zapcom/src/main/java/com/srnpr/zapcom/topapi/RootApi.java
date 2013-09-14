package com.srnpr.zapcom.topapi;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseApi;

/**
 * 系统处理逻辑调用
 * 
 * @author srnpr
 * 
 * @param <TResult>
 *            返回结果类型
 * @param <TInput>
 *            输入结果类型
 */
public abstract class RootApi<TResult, TInput> extends BaseClass implements
		IBaseApi<TResult, TInput> {

}
