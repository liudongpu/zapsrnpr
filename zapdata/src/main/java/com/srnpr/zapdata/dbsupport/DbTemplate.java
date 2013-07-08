package com.srnpr.zapdata.dbsupport;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class DbTemplate extends NamedParameterJdbcTemplate {

	public DbTemplate(DataSource dataSource) {
		super(dataSource);
	}

}
