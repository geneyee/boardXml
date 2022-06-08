package com.blog.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.project.beans.UserBean;
import com.blog.project.service.UserService;
import com.blog.project.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//RootAppContext에서 주입받은 빈(로그인 여부 확인용)
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;

	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail,
						Model model) {
		
		model.addAttribute("fail", fail);
	
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean")UserBean tempLoginUserBean, BindingResult result, HttpServletResponse response) throws IOException {
		
		if(result.hasErrors()) {
			return "user/login_fail";
		}
		
		userService.getLoginUserInfo(tempLoginUserBean);
		
		if(loginUserBean.isUserLogin() == true) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 되었습니다.');</script>");
			out.flush();
			return "user/login_success";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인에 실패하였습니다.');</script>");
			out.flush();
			return "user/login_fail";
		}
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUserInfo(joinUserBean);
		
		return "user/join_success";
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		
		userService.getModifyUserInfo(modifyUserBean);
		
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		
		if(result.hasErrors()) {//유효성 검사 실패
			return "user/modify";
		}
		
		userService.modifyUserInfo(modifyUserBean);
		
		return "user/modify_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		return "user/not_login";
	}
	
	//비밀번호 일치하는지 유효성검사 validator 만든거
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
}








