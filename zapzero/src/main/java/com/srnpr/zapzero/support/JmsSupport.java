package com.srnpr.zapzero.support;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapzero.face.IJmsListener;

public class JmsSupport extends BaseClass implements IBaseInstance {

	private final static JmsSupport jmsSupport = new JmsSupport();

	public static JmsSupport getInstance() {

		return jmsSupport;

	}

	/**
	 * 
	 * @param sTpoic
	 *            主题名称 该字段尽量定义为46991020**** 以保证系统唯一性
	 * @param sMsg
	 *            消息内容
	 */
	public void sendToTopic(String sTpoic, String sMsg,MDataMap mPropMap) {
		try {

			Session session = JmsConnection.getInstance().createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createTopic(sTpoic);

			MessageProducer producer = session.createProducer(destination);
			// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage(sMsg);
			
			for(String sKey:mPropMap.keySet())
			{
				message.setStringProperty(sKey, mPropMap.get(sKey));
			}
			
			producer.send(message);

		} catch (JMSException e) {

			bLogError(970205031, sTpoic, sMsg);

			WebHelper.errorMessage(sTpoic, "jmserror", 1,
					"com.srnpr.zapzero.support.JmsSupport.sendToTopic", sTpoic
							+ WebConst.CONST_SPLIT_LINE + sMsg, e);

			e.printStackTrace();

		}
	}

	/**
	 * 添加监听
	 * 
	 * @param sTpoic
	 *            主题名称 该字段尽量定义为46991020**** 以保证系统唯一性
	 * @param sSubName
	 *            持久化标记 该字段可谓空 留空时标记不持久化 否则请输入事件的名称
	 * @param listener
	 *            监听事件对象
	 */
	public void addTopicLisense(String sTpoic, String sSubName,
			IJmsListener listener) {
		try {
			Session session = JmsConnection.getInstance().createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createTopic(sTpoic);

			MessageConsumer consumer = null;

			if (StringUtils.isNotBlank(sSubName)) {
				consumer = session.createDurableSubscriber(
						session.createTopic(sTpoic), sSubName);
			} else {
				consumer = session.createConsumer(destination);
			}

			consumer.setMessageListener(listener);

		} catch (JMSException e) {

			bLogError(970205032, sTpoic, sSubName);

			WebHelper.errorMessage(sTpoic, "jmserror", 1,
					"com.srnpr.zapzero.support.JmsSupport.addTopicLisense",
					sTpoic + WebConst.CONST_SPLIT_LINE + sSubName, e);

			e.printStackTrace();

		}

	}

}
