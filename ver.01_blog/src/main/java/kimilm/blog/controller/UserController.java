package kimilm.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kimilm.blog.domain.CategoryDomain;
import kimilm.blog.domain.UserDomain;
import kimilm.blog.service.CategoryService;
import kimilm.blog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	/* in */
	
	@RequestMapping(value="/signin")
	public String signIn() {
		return "signin";
	}
	
	@RequestMapping(value="/signInCheck", method=RequestMethod.POST)
	public String signInCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if (isUser(id)) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", id);
			
			return "redirect:/" + id;	
		}
		else
			return "redirect:/signin";
	}
	
	public boolean isUser(String id) {
		return userService.findByUserId(id) != null ? true : false;
	}
	
	/* up */
	
	@RequestMapping(value="/signup")
	public String signUp() {
		return "signup";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		if (isUser(id)) {
			return "redirect:/signup";
		}
		else {
			UserDomain user = new UserDomain();
			user.setUserId(id);
			user.setUserName(name);
			user.setUserPwd(password);
			user.setUserEmail(email);
			
			CategoryDomain category = new CategoryDomain();
			category.setCategoryName("default");
			category.setUser(user);
			
			userService.save(user);
			categoryService.save(category);
			
			HttpSession session = request.getSession();
			session.setAttribute("userId", id);
			
			return "redirect:/" + id;	
		}
	}
	
	@RequestMapping(value="/signout")
	public String signOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
