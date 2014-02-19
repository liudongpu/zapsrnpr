package com.srnpr.zapdata.dbsupport;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.srnpr.zapdata.dbdriver.SlaveNamedParameterJdbcTemplate;

public class DbTemplate extends SlaveNamedParameterJdbcTemplate {

	
	/**
	 * 是否可用
	 */
	private int flagEnable=1;
	
	
	public DbTemplate(DataSource dataSource) {
		super(dataSource);
	}


	public int getFlagEnable() {
		return flagEnable;
	}


	public void setFlagEnable(int flagEnable) {
		this.flagEnable = flagEnable;
	}

}
