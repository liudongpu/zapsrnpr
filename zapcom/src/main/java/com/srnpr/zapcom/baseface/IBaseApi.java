package com.srnpr.zapcom.baseface;

/**
 * 系统处理逻辑调用接口
 * 
 * @author srnpr
 * 
 * @param <TResult>
 *            返回结果类型
 * @param <TInput>
 *            输入结果类型
 */
public interface IBaseApi<TResult, TInput> {

	/**
	 * 逻辑处理执行
	 * 
	 * @param inputParam
	 *            输入参数 该参数需要继承RootInput
	 * @return 返回参数 该参数需要继承RootResult
	 */
	public TResult Process(TInput inputParam);

}
