package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Test {
	
	@RequestMapping(value= "/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String TestPrint() {
		System.out.println("TestPrint");
		
		return "/WEB-INF/views/Test.jsp";
	}
	
	@RequestMapping(value= "/writeForm", method= RequestMethod.GET)
	public String TestPrint2() {
		System.out.println("TestPrint2");
		
		return "/WEB-INF/views/Test.jsp";
	}
	
	@RequestMapping(value= "/board/write") // GET, POST 방식 둘다 가능(생략)
	public String TestPrint3() {
		System.out.println("TestPrint3");
		
		return "/WEB-INF/views/Test.jsp";
	}
	
	
	
}