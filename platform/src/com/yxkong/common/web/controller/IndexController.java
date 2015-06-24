package com.yxkong.common.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("/error")
	public String error(){
		return "error";
	}
	@RequestMapping("tologin")
	public String tologin(){
		return "login";
	}
	@RequestMapping("/")
	public String main(){
		return "index";
	}
}
