package com.srnpr.zapdata.dbsupport;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.srnpr.zapdata.TestZapdata;

public class TestDataSource extends TestZapdata {

	@Test
	public void testSource() {
		
		
		
		
		
		
		
		ComboPooledDataSource cm =new  ComboPooledDataSource();
		
		try {
			cm.setDriverClass("com.mysql.jdbc.Driver");
			cm.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/zdata?useUnicode=true");
			cm.setUser("root");
			cm.setPassword("");

		} catch (Exception e) {
			
		}
		
		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(cm);
		
	
		
	}

}
