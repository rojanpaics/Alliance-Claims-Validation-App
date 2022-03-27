package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.service.IndexService;

@Controller
public class IndexController {

	// inject via application.properties
//	@Value("${welcome.message:test}")
	private String message = "Hello World and beyond";
//
//	@RequestMapping("/")
//	public String welcome(ModelMap model) {
//		model.put("message", this.message);
//		return "index";
//	}
//
//	@RequestMapping("/test")
//	public String loadTest(ModelMap model) {
//		model.put("message", this.message);
//		return "test";
//	}
	
	@Autowired
	private IndexService indexService;
	
	@RequestMapping("index/welcome")
	public String loadWelcome(ModelMap model) {
		model.put("message", this.message);
		return "HelloWorld";
	}
	
	@RequestMapping("index/insert")
	public String insertSimpleValue(HttpServletRequest request) {
		indexService.insertSampleLoad("This is my first entry");
		return "HelloWorld";
	}
	
	@RequestMapping("index/update")
	public String updateSimpleValue(HttpServletRequest request) {
		indexService.updateSampleLoad(2, "This is my first entry [edited]");
		return "HelloWorld";
	}
}