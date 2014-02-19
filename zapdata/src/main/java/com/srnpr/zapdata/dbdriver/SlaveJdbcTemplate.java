package com.srnpr.zapdata.dbdriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

public class SlaveJdbcTemplate extends JdbcTemplate {

	public SlaveJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}

	/*
	 * public <T> T query(PreparedStatementCreator psc, final
	 * PreparedStatementSetter pss, final ResultSetExtractor<T> rse) throws
	 * DataAccessException {
	 * 
	 * Assert.notNull(rse, "ResultSetExtractor must not be null");
	 * logger.debug("Executing prepared SQL query");
	 * 
	 * return executeReadOnly(psc, new PreparedStatementCallback<T>() { public T
	 * doInPreparedStatement(PreparedStatement ps) throws SQLException {
	 * ResultSet rs = null; try { if (pss != null) { pss.setValues(ps); } rs =
	 * ps.executeQuery(); ResultSet rsToUse = rs;
	 * 
	 * return rse.extractData(rsToUse); } finally {
	 * JdbcUtils.closeResultSet(rs); if (pss instanceof ParameterDisposer) {
	 * ((ParameterDisposer) pss).cleanupParameters(); } } } }); }
	 * 
	 * //
	 * -------------------------------------------------------------------------
	 * // Methods dealing with prepared statements //
	 * -------------------------------------------------------------------------
	 * 
	 * public <T> T executeReadOnly(PreparedStatementCreator psc,
	 * PreparedStatementCallback<T> action) throws DataAccessException {
	 * 
	 * Assert.notNull(psc, "PreparedStatementCreator must not be null");
	 * Assert.notNull(action, "Callback object must not be null"); if
	 * (logger.isDebugEnabled()) { String sql = getSql(psc);
	 * logger.debug("Executing prepared SQL statement" + (sql != null ? " [" +
	 * sql + "]" : "")); }
	 * 
	 * Connection con = DataSourceUtils.getConnection(getDataSource());
	 * PreparedStatement ps = null; try { Connection conToUse = con;
	 * conToUse.setReadOnly(true); ps = psc.createPreparedStatement(conToUse);
	 * applyStatementSettings(ps); PreparedStatement psToUse = ps;
	 * 
	 * T result = action.doInPreparedStatement(psToUse); handleWarnings(ps);
	 * return result; } catch (SQLException ex) { // Release Connection early,
	 * to avoid potential connection pool // deadlock // in the case when the
	 * exception translator hasn't been initialized // yet. if (psc instanceof
	 * ParameterDisposer) { ((ParameterDisposer) psc).cleanupParameters(); }
	 * String sql = getSql(psc); psc = null; JdbcUtils.closeStatement(ps); ps =
	 * null; DataSourceUtils.releaseConnection(con, getDataSource()); con =
	 * null; throw getExceptionTranslator().translate(
	 * "PreparedStatementCallback", sql, ex); } finally { if (psc instanceof
	 * ParameterDisposer) { ((ParameterDisposer) psc).cleanupParameters(); }
	 * JdbcUtils.closeStatement(ps); DataSourceUtils.releaseConnection(con,
	 * getDataSource()); } }
	 */

	// -------------------------------------------------------------------------
	// Methods dealing with prepared statements
	// -------------------------------------------------------------------------

	@Override
	public <T> T execute(PreparedStatementCreator psc,
			PreparedStatementCallback<T> action) throws DataAccessException {

		Assert.notNull(psc, "PreparedStatementCreator must not be null");
		Assert.notNull(action, "Callback object must not be null");

		String sql = getSql(psc);

		if (logger.isDebugEnabled()) {

			logger.debug("Executing prepared SQL statement"
					+ (sql != null ? " [" + sql + "]" : ""));
		}

		Connection con = DataSourceUtils.getConnection(getDataSource());
		PreparedStatement ps = null;
		try {
			Connection conToUse = con;
			conToUse.setReadOnly(sql.startsWith("select ") ? true : false);
			ps = psc.createPreparedStatement(conToUse);
			applyStatementSettings(ps);
			PreparedStatement psToUse = ps;

			T result = action.doInPreparedStatement(psToUse);
			handleWarnings(ps);
			return result;
		} catch (SQLException ex) {
			// Release Connection early, to avoid potential connection pool
			// deadlock
			// in the case when the exception translator hasn't been initialized
			// yet.
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			// String sql = getSql(psc);
			psc = null;
			JdbcUtils.closeStatement(ps);
			ps = null;
			DataSourceUtils.releaseConnection(con, getDataSource());
			con = null;
			throw getExceptionTranslator().translate(
					"PreparedStatementCallback", sql, ex);
		} finally {
			if (psc instanceof ParameterDisposer) {
				((ParameterDisposer) psc).cleanupParameters();
			}
			JdbcUtils.closeStatement(ps);
			DataSourceUtils.releaseConnection(con, getDataSource());
		}
	}

	/**
	 * Determine SQL from potential provider object.
	 * 
	 * @param sqlProvider
	 *            object that's potentially a SqlProvider
	 * @return the SQL string, or {@code null}
	 * @see SqlProvider
	 */
	private static String getSql(Object sqlProvider) {
		if (sqlProvider instanceof SqlProvider) {
			return ((SqlProvider) sqlProvider).getSql();
		} else {
			return null;
		}
	}

}
