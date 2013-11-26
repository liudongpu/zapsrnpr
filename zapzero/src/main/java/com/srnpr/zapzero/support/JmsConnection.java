package com.srnpr.zapzero.support;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapzero.server.ServerInfo;

/**
 * @author srnpr
 *
 */
public class JmsConnection extends BaseClass {

	static PooledConnection connection = null;

	public static PooledConnection getInstance() {
		if (connection == null) {

			JmsConnection jConnection = new JmsConnection();
			jConnection.initConn();

		}

		return connection;
	}

	private void initConn() {
		String url = bConfig("zapzero.jms_server_conn");
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory
				.setUserName(bConfig("zapzero.jms_server_name"));
		activeMQConnectionFactory
				.setPassword(bConfig("zapzero.jms_server_pass"));
		activeMQConnectionFactory.setBrokerURL(url);

		try {

			PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(
					activeMQConnectionFactory);

			// session数
			int maximumActive = 2000;

			pooledConnectionFactory
					.setMaximumActiveSessionPerConnection(maximumActive);
			pooledConnectionFactory.setIdleTimeout(1000);
			pooledConnectionFactory.setExpiryTimeout(1000);
			pooledConnectionFactory.setMaxConnections(1);
			pooledConnectionFactory.setBlockIfSessionPoolIsFull(true);
			connection = (PooledConnection) pooledConnectionFactory
					.createConnection();

			connection.setClientID(ServerInfo.INSTANCE.getServerCode());

			// 必须start，否则无法接收消息
			connection.start();
		} catch (JMSException e) {

			e.printStackTrace();
		}
	}

}
