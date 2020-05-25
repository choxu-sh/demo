package com.example.demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

//@Configuration
public class MessagingListnerConfiguration {

	private String trustStore = "";
	private String trustStorePassword = "";
	private String keyStore = "";
	private String keyStorePassword = "";
	
	private static final String DEFAULT_BROKER_URL = "failover:(tcp://dedicated-bus.mq.slcq045.com:61617?wireFormat.maxInactivityDuration=0)?randomize=false&maxReconnectAttempts=5";
	
	private static final String DEFAULT_BROKER_URL_SSL = "failover:(ssl://dedicated-bus.mq.slcq045.com:61617?wireFormat.maxInactivityDuration=0)?randomize=false&maxReconnectAttempts=5";
	//"failover:(tcp://10.124.135.5:61616)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1";
	//"tcp://dedicated-bus.mq.slcq055.com:61617?wireFormat.maxInactivityDuration=0";

	// <!--
	// failover:(tcp://dedicated-bus.mq.slcq072.com:61617?wireFormat.maxInactivityDuration=0)?randomize=false&maxReconnectAttempts=5
	// -->
	// <!--
	// failover:(tcp://slcq055mqm001.slcq055.com:61617,tcp://slcq055mqs001.slcq055.com:61617)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1
	// -->

	private static final String ORDER_QUEUE = "ecomm.to.gcp";

    @Bean("listenerConnectionFactory")
	public ActiveMQConnectionFactory connectionFactoryListener() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		// connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
		return connectionFactory;
	}
    
 /*   @Bean("listenerConnectionFactorySSL")
   	public ActiveMQSslConnectionFactory connectionFactoryListenerSSL() throws Exception {
   	 ActiveMQSslConnectionFactory connectionFactory = new ActiveMQSslConnectionFactory();
   		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL_SSL);
   		//connectionFactory.setTrustStore(trustStore);
   		//connectionFactory.setTrustStorePassword(trustStorePassword);
   		//connectionFactory.setKeyStore(keyStore);
   		//connectionFactory.setKeyStorePassword(keyStorePassword);
   		// connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
   		return connectionFactory;
   	}*/

/*	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactoryListener());
		template.setDefaultDestinationName(ORDER_QUEUE);
		return template;
	}*/

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactoryListener());
		factory.setConcurrency("1-1");
		return factory;
	}

}
