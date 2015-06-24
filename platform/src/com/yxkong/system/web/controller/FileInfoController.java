package com.yxkong.system.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yxkong.system.service.IFileInfoService;

@Controller
@RequestMapping("/fileinfo")
public class FileInfoController {
	@Resource
	private IFileInfoService fileInfoService;
	private static final String NAMESPACE="fileinfo/";
	
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", fileInfoService.findList());
		return NAMESPACE+"list";
	}
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(){
		return NAMESPACE+"edit";
	}
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable String id){
		model.addAttribute("item", fileInfoService.findById(id));
		return NAMESPACE+"edit";
	}
	

}
