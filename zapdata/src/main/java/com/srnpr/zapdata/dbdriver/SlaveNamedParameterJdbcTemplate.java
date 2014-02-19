package com.srnpr.zapdata.dbdriver;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import com.srnpr.zapdata.dbdo.DataConst;

public class SlaveNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate {

	public SlaveNamedParameterJdbcTemplate(DataSource dataSource) {
		super(upJdbcTemplate(dataSource));
	}

	private static JdbcOperations upJdbcTemplate(DataSource dataSource) {
		return DataConst.CONST_DATA_RUN_TYPE == 2 ? new SlaveJdbcTemplate(
				dataSource) : new JdbcTemplate(dataSource);

	}

}
