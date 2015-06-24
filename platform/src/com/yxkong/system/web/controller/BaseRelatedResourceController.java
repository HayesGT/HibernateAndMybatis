package com.yxkong.system.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxkong.system.dao.IBaseRelatedResourceDao;

@Controller
@RequestMapping("baserelatedresc")
@SuppressWarnings("unused")
public class BaseRelatedResourceController {
	@Resource
	private IBaseRelatedResourceDao baseRelatedResourceDao;
}
