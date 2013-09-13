package com.srnpr.zapcom.baseface;

public interface IBaseApi<TResult, TInput> {

	public TResult Process(TInput r);

}
