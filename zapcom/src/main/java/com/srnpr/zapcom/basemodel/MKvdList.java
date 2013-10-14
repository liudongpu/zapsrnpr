package com.srnpr.zapcom.basemodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MKvdList {

	private List<MKvdModel> childList = new ArrayList<MKvdModel>();

	public List<MKvdModel> getChildList() {
		return childList;
	}

	public void setChildList(List<MKvdModel> childList) {
		this.childList = childList;
	}

	public MKvdModel inElement(String sKey, String sValue) {

		return inElement(sKey, sValue, "");
	}

	public MKvdModel inElement(String sKey, String sValue, String sDescription) {
		MKvdModel mKvdModel = new MKvdModel();
		mKvdModel.setK(sKey);
		mKvdModel.setV(sValue);
		mKvdModel.setD(sDescription);
		this.childList.add(mKvdModel);
		return mKvdModel;
	}
	
	
	public List<Object> upChildObject()
	{
		
		ArrayList< Object> aList=new ArrayList<Object>();
		
		aList.addAll(this.childList);
		return aList;
	}
	
	

}
