package com.example.demo;

import java.util.HashMap;
import java.util.Map;

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

	@Autowired
	MessageSender messageSender;

	
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

}
