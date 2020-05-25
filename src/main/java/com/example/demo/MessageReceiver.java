package com.example.demo;

import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//@Component
public class MessageReceiver {

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private static final String ORDER_RESPONSE_QUEUE = "ecomm.to.gcp";

	@JmsListener(destination = ORDER_RESPONSE_QUEUE)
	public void receiveMessage(final Map message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		message.forEach((k, v) -> {
			LOG.info("key={}, value={}",k.toString(), v.toString());
		});
		/*LOG.info("orderId={}", message.get("orderId"));
		LOG.info("trigger={}", message.get("trigger"));
		LOG.info("afterStatus={}", message.get("trigger"));*/
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

}
