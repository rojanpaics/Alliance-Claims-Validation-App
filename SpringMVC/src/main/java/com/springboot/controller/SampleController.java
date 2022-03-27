package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.service.SampleService;

// http://localhost:8080/sample
@Controller // if using @Controller, we are returning a view → JSP or HTML
// @RestController // if using @RestController, we are returning data only
@RequestMapping("/sample")
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	// http://localhost:8080/sample/test
	@RequestMapping("/test")
	public String getSampleTest() {
		// this request is for returning static web
		return "SampleFile";
	}
	
	@RequestMapping("/testCustomData")
	public String getTestCustomData(ModelMap map) {
		// this request is for returning custom data
		map.put("name", "Jose Mari Chan");
		map.put("age", "65");
		map.put("zodiac", "Pisces");
		return "SampleCustomData";
	}
	
	@RequestMapping(value="/testdata", method=RequestMethod.POST)
	public String postTestData(HttpServletRequest request, ModelMap map) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String zodiac = request.getParameter("zodiac");
//		name = sampleService.testSampleService(name);
		sampleService.testInsertSampleUserService(name, age, zodiac);
		map.put("name", name);
		map.put("age", age);
		map.put("zodiac", zodiac);
		return "SampleCustomData";
	}
}