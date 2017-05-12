package com.redisZk.platform.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class test1Controller {
  
	@RequestMapping("sd")
	public String ss(HttpServletRequest request){
		System.out.println("11212");
		System.out.println("54459454548905590");
		return "/1";
	}
	
	@RequestMapping("2")
	public String g(HttpServletRequest request){
		System.out.println("676");
		return "/1";
	}
}
