package com.srnpr.zapzero.support;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;

public class JmsSupport extends BaseClass implements IBaseInstance {

	private final static JmsSupport jmsSupport = new JmsSupport();

	public static JmsSupport getInstance() {

		return jmsSupport;

	}

	public void sendToTopic(String sTpoic, String sMsg) {
		try {

			Session session = JmsConnection.getInstance().createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createTopic(sTpoic);

			MessageProducer producer = session.createProducer(destination);
			// producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage(sMsg);
			producer.send(message);

		} catch (JMSException e) {

			WebHelper.errorMessage(sTpoic, "jmserror", 1,
					"com.srnpr.zapzero.support.JmsSupport.sendToTopic", sTpoic
							+ WebConst.CONST_SPLIT_LINE + sMsg, e);

			e.printStackTrace();

		}
	}

}
