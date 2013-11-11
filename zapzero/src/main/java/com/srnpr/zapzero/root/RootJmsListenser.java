package com.srnpr.zapzero.root;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapzero.face.IJmsListener;

/**
 * 系统jms接受注册监听
 * 
 * @author srnpr
 * 
 */
public abstract class RootJmsListenser extends BaseClass implements
		IJmsListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {

		String sMsg = "";

		try {

			TextMessage textMessage = (TextMessage) message;

			sMsg = textMessage.getText();

			MDataMap mPropMap = new MDataMap();

			while (textMessage.getPropertyNames().hasMoreElements()) {
				String sPkey = textMessage.getPropertyNames().nextElement()
						.toString();
				mPropMap.put(sPkey, textMessage.getStringProperty(sPkey));

			}

			if (!onReceiveText(sMsg, mPropMap)) {
				bLogError(970205034, sMsg);
			}

		} catch (Exception e) {

			bLogError(970205033, sMsg);

			WebHelper.errorMessage("jmslistensererror", "jms", 0, this
					.getClass().getName() + "onMessage", message.toString(),
					null);
		}

	}
}
