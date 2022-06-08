package com.blog.project.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.project.beans.UserBean;

@Controller
public class HomeController {
	
//	@Resource(name = "loginUserBean")
//	@Lazy/xml에서는 서버 가동되면 먼저 주입하려고 해서 private UserBean loginUserBean;

//	private UserBean loginUserBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		
//		System.out.println(loginUserBean);
		//System.out.println(request.getServletContext().getRealPath("/"));
		
		return "redirect:/main";
	}
}

