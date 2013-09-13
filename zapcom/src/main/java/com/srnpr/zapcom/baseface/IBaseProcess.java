package com.srnpr.zapcom.baseface;

public interface IBaseProcess<TResult, TInput> {

	public TResult Process(TInput r);

}
