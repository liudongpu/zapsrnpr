package com.srnpr.zapweb.webmodel;

/**
 * 操作类
 * @author srnpr
 *
 */
public class MWebOperate implements Cloneable {

	private String operateName = "";

	private String operateTypeAid = "";

	private String operateLink = "";

	private String operateUid = "";

	private String operateFunc = "";
	
	
	private String pageCode="";
	
	private String areaTypeAid="";
	

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateTypeAid() {
		return operateTypeAid;
	}

	public void setOperateTypeAid(String operateTypeAid) {
		this.operateTypeAid = operateTypeAid;
	}

	public String getOperateLink() {
		return operateLink;
	}

	public void setOperateLink(String operateLink) {
		this.operateLink = operateLink;
	}

	public String getOperateUid() {
		return operateUid;
	}

	public void setOperateUid(String operateUid) {
		this.operateUid = operateUid;
	}

	public String getOperateFunc() {
		return operateFunc;
	}

	public void setOperateFunc(String operateFunc) {
		this.operateFunc = operateFunc;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getAreaTypeAid() {
		return areaTypeAid;
	}

	public void setAreaTypeAid(String areaTypeAid) {
		this.areaTypeAid = areaTypeAid;
	}

	
	public MWebOperate clone() {

		MWebOperate o = null;
		try {
			o = (MWebOperate) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
}
