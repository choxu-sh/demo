package com.example.demo;

import java.util.Arrays;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfiguration {

	@Value("${sender.broker:10.124.135.6}")
	private String senderBroker;
	
	private String trustStore = "";
	private String trustStorePassword = "";
	private String keyStore = "";
	private String keyStorePassword = "";
				//"failover:(tcp://slcq071mqm001.slcq071.com:61616,tcp://slcq071mqs001.slcq071.com:61616)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1";
			//"tcp://slcq055mqm001.slcq055.com:61616";

	// <!--
	// failover:(tcp://dedicated-bus.mq.slcq072.com:61617?wireFormat.maxInactivityDuration=0)?randomize=false&maxReconnectAttempts=5
	// -->
	// <!--
	// failover:(tcp://slcq055mqm001.slcq055.com:61617,tcp://slcq055mqs001.slcq055.com:61617)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1
	// -->

	@Value("${sender.queue.name:domain.accountmanagement.orderLifeCycle.approved}")
	private String ORDER_QUEUE;// = "domain.accountmanagement.orderLifeCycle.approved";

	@Bean("senderConnectionFactory")
	public ActiveMQConnectionFactory connectionFactorySender() {
		 String DEFAULT_BROKER_URL = "failover:(tcp://dedicated-bus.mq.slcq045.com:61617?wireFormat.maxInactivityDuration=0)?randomize=false&maxReconnectAttempts=5";
				 
				 
				 //"failover:(tcp://"+senderBroker+":61616)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1";

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		
		// connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
		return connectionFactory;
	}
	
	@Bean("senderConnectionFactorySSL")
	public ActiveMQSslConnectionFactory connectionFactorySenderSSL() throws Exception {
		String DEFAULT_BROKER_URL_SSL = "ssl://dedicated-bus.mq.slcq045.com:61620";
				
				//"failover:(ssl://dedicated-bus.mq.slcq045.com:61620?wireFormat.maxInactivityDuration=0)?randomize=false&maxReconnectAttempts=5"; 
				 //"failover:(tcp://"+senderBroker+":61616)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1";

		 ActiveMQSslConnectionFactory connectionFactory = new ActiveMQSslConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL_SSL);
		
		/*connectionFactory.setTrustStore(trustStore);
		connectionFactory.setTrustStorePassword(trustStorePassword);
		connectionFactory.setKeyStore(keyStore);
		connectionFactory.setKeyStorePassword(keyStorePassword);*/
		// connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
		return connectionFactory;
	}

	@Bean("jsmTemplateSender")
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactorySender());
		template.setDefaultDestinationName(ORDER_QUEUE);
		return template;
	}
	
	@Bean("jsmTemplateSenderSSL")
	public JmsTemplate jmsTemplateSSL() throws Exception {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactorySenderSSL());
		template.setDefaultDestinationName(ORDER_QUEUE);
		return template;
	}
	
	@Bean
	public Session createProducer() throws JMSException, Exception
	{
	    Connection conn = connectionFactorySenderSSL().createConnection();
	    conn.start();
	    Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
	   
	    return session;
	}



}
