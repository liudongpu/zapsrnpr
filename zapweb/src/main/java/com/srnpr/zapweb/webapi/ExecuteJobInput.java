package com.srnpr.zapweb.webapi;

import com.srnpr.zapcom.topapi.RootInput;

public class ExecuteJobInput extends RootInput {

	
	private String jobName="";

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
}
