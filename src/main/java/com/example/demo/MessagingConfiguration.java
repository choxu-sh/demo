package com.example.demo;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfiguration {

	private static final String DEFAULT_BROKER_URL = "failover:(tcp://10.124.135.6:61616)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1";
			//"failover:(tcp://slcq071mqm001.slcq071.com:61616,tcp://slcq071mqs001.slcq071.com:61616)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1";
			//"tcp://slcq055mqm001.slcq055.com:61616";

	// <!--
	// failover:(tcp://dedicated-bus.mq.slcq072.com:61617?wireFormat.maxInactivityDuration=0)?randomize=false&maxReconnectAttempts=5
	// -->
	// <!--
	// failover:(tcp://slcq055mqm001.slcq055.com:61617,tcp://slcq055mqs001.slcq055.com:61617)?randomize=false&jms.redeliveryPolicy.maximumRedeliveries=99&jms.redeliveryPolicy.initialRedeliveryDelay=600000&jms.prefetchPolicy.all=1
	// -->

	private static final String ORDER_QUEUE = "domain.accountmanagement.orderLifeCycle.approved";

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		// connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(ORDER_QUEUE);
		return template;
	}

}
