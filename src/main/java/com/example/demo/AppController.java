package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {
/*
 ## Import the previous exported broker's certificate into a CLIENT truststore
keytool -import -keystore $JAVA_HOME/jre/lib/security/cacerts -alias broker -file broker_cert
 https://stackoverflow.com/questions/32696121/how-to-activemq-in-ssl
 * 
 * 
 * 
 * 
 * 
 * */
	@Autowired
	MessageSender messageSender;
	
	@Autowired
	Session session;
	
	@GetMapping("/msg")
	public String sendMsg(@RequestParam(name = "id") String id) {
		Map<String, String> msg = new HashMap<>();
		msg.put("orderId", id);
		msg.put("trigger", "CHONG");
		msg.put("afterStatus", "43");
		messageSender.sendMessage(msg);
		return "OK";
	}
	
@GetMapping("/msg2")
	public String sendMsg2(@RequestParam(name = "id") String id) {
		
		return "OK"+" "+id;
	}

@GetMapping("/msg3")
public String sendMsg3(@RequestParam(name = "id") String id) {
	Map<String, String> msg = new HashMap<>();
	msg.put("orderId", id);
	msg.put("trigger", "SSL");
	msg.put("afterStatus", "33");
	messageSender.sendMessageSSL(msg);
	return "OK";
}

@GetMapping("/msg5")
public String sendMsg5(@RequestParam(name = "id") String id) throws JMSException {
	 Destination dest = session.createQueue("sslDemo3");
	    MessageProducer mp = session.createProducer(dest);
	Message msg = session.createTextMessage("Hello SSL!");
	mp.send(msg);
	return "OK";
}

}
