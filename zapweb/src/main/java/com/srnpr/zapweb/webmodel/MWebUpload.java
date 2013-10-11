package com.srnpr.zapweb.webmodel;

import org.apache.commons.fileupload.FileItem;

import com.srnpr.zapweb.webface.IWebInput;

public class MWebUpload implements IWebInput {

	
	private String target="";

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public FileItem getFile() {
		return file;
	}

	public void setFile(FileItem file) {
		this.file = file;
	}

	private FileItem file=null;
	
	
	
}
