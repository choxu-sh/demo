package com.example.demo;

import java.util.Map;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	@Qualifier("jsmTemplateSender")
	JmsTemplate jmsTemplate;

	public void sendMessage(final Map message) {

		jmsTemplate.convertAndSend(message);
	}
	
	@Autowired
	@Qualifier("jsmTemplateSenderSSL")
	JmsTemplate jmsTemplateSSL;

	public void sendMessageSSL(final Map message) {

		jmsTemplateSSL.convertAndSend(message);
	}

}
