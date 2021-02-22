package kimilm.blog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kimilm.blog.service.PostService;


@Controller
public class blogController {
	@Autowired
	PostService postService;
	
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/")
	public String blog() {
		return "redirect:/user1/category/total/1";
	}
	
	@RequestMapping(value="/favicon")
	public ResponseEntity<Map<String, Object>> favicon() {
		return null;
	}
}
