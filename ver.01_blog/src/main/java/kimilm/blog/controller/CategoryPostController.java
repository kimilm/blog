package kimilm.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kimilm.blog.domain.CategoryDomain;
import kimilm.blog.domain.CommentDomain;
import kimilm.blog.domain.PostDomain;
import kimilm.blog.domain.UserDomain;
import kimilm.blog.service.CategoryService;
import kimilm.blog.service.CommentService;
import kimilm.blog.service.PostService;
import kimilm.blog.service.UserService;

@Controller
@RequestMapping(value = "/{userId}")
public class CategoryPostController {

	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	PostService postService;

	@Autowired
	CommentService commentService;

	/* post */
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(@PathVariable("userId") String userId, HttpServletRequest request, Model model) {
		PostDomain post = postService.findByPostNo(request.getParameter("postNo"));

		Page<CommentDomain> comments = commentService.findByPost_PostNo(post.getPostNo(), PageRequest.of(0, 10));

		model.addAttribute("userId", userId);
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "post";
	}
	
	@RequestMapping(value = "/write")
	public String write(@PathVariable("userId") String userId, Model model) {
		
		List<CategoryDomain> categories =  categoryService.findByUser_UserId(userId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("categories", categories);
		
		return "write";
	}
	
	@RequestMapping(value="/savePost", method = RequestMethod.POST)
	public String savePost(HttpServletRequest request, Model model) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String categoryNo = request.getParameter("categoryNo");
		String userId = (String) request.getSession().getAttribute("userId");
		
		UserDomain user = userService.findByUserId(userId);
		CategoryDomain category = categoryService.findByCategoryNo(categoryNo);
		
		
		PostDomain post = new PostDomain();
		
		post.setUser(user);
		post.setCategory(category);
		post.setPostDate(new Date());
		post.setPostHits(0);
		post.setPostTitle(title);
		post.setPostContent(content);
		
		int postNo = postService.save(post).getPostNo();
		
		return "redirect:/" + userId + "/post?postNo=" + postNo;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updatePost(@PathVariable("userId") String userId, HttpServletRequest request, Model model) {
		PostDomain post = postService.findByPostNo(request.getParameter("postNo"));
		List<CategoryDomain> categories =  categoryService.findByUser_UserId(userId);

		model.addAttribute("userId", userId);
		model.addAttribute("categories", categories);
		model.addAttribute("post", post);
		
		return "update";
	}
	
	@RequestMapping(value="/doUpdatePost", method = RequestMethod.POST)
	public String doUpdatePost(HttpServletRequest request, Model model) {
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String categoryNo = request.getParameter("categoryNo");
		String userId = (String) request.getSession().getAttribute("userId");
		
		UserDomain user = userService.findByUserId(userId);
		CategoryDomain category = categoryService.findByCategoryNo(categoryNo);
		
		
		PostDomain post = new PostDomain();
		
		post.setPostNo(postNo);
		post.setUser(user);
		post.setCategory(category);
		post.setPostDate(new Date());
		post.setPostHits(0);
		post.setPostTitle(title);
		post.setPostContent(content);
		
		postService.save(post);
	
		return "redirect:/" + userId + "/post?postNo=" + postNo;
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deletePost(HttpServletRequest request, Model model) {
		String userId = (String) request.getSession().getAttribute("userId");
		String postNo = request.getParameter("postNo");
		
		postService.deleteByPostNo(postNo);
		
		return "redirect:/" + userId;
	}
	
	
	/* category */
	
	@RequestMapping(value = "")
	public String index(@PathVariable("userId") String userId) {
		return "redirect:/" + userId + "/category/total/1";
	}

	@RequestMapping(value = "/category/total/")
	public String blog(@PathVariable("userId") String userId) {
		return "redirect:/" + userId + "/category/total/1";
	}

	@RequestMapping(value = "/category/total/{page}")
	public String blog(@PathVariable("userId") String userId, @PathVariable("page") int page, Model model) {
		int userNo = userService.findByUserId(userId).getUserNo();
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("postNo").descending());
		
		Page<PostDomain> posts = postService.findByUser_UserNo(userNo, pageable);

		model.addAttribute("userId", userId);
		model.addAttribute("categoryName", "total");
		model.addAttribute("posts", posts);
		model.addAttribute("search", false);

		return "category";
	}
	
	@RequestMapping(value = "/category/total/{page}/search", method = RequestMethod.GET)
	public String blog(@PathVariable("userId") String userId, @PathVariable("page") int page, HttpServletRequest request, Model model) {
		int userNo = userService.findByUserId(userId).getUserNo();
		String search = request.getParameter("search");
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("postNo").descending());
		
		Page<PostDomain> posts = postService.findByUser_UserNoAndPostTitleContaining(userNo, search, pageable);

		model.addAttribute("userId", userId);
		model.addAttribute("categoryName", "total");
		model.addAttribute("posts", posts);
		model.addAttribute("search", true);
		model.addAttribute("searchValue", search);

		return "category";
	}

	@RequestMapping(value = "/category/{categoryName}", method = RequestMethod.GET)
	public String getCategoryList(@PathVariable("userId") String userId,
			@PathVariable("categoryName") String categoryName) {
		return "redirect:/" + userId + "/category/" + categoryName + "/1";
	}

	@RequestMapping(value = "/category/{categoryName}/{page}", method = RequestMethod.GET)
	public String getCategoryList(@PathVariable("userId") String userId,
			@PathVariable("categoryName") String categoryName, @PathVariable("page") int page, Model model) {
		int userNo = userService.findByUserId(userId).getUserNo();
		int categoryNo = categoryService.findByUser_UserNoAndCategoryName(userNo, categoryName).getCategoryNo();
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("postNo").descending());

		Page<PostDomain> posts = postService.findByCategory_CategoryNo(categoryNo, pageable);

		model.addAttribute("userId", userId);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("posts", posts);
		model.addAttribute("search", false);

		return "category";
	}
	
	@RequestMapping(value = "/category/{categoryName}/{page}/search", method = RequestMethod.GET)
	public String getCategoryList(@PathVariable("userId") String userId,
			@PathVariable("categoryName") String categoryName, @PathVariable("page") int page, HttpServletRequest request, Model model) {
		int userNo = userService.findByUserId(userId).getUserNo();
		int categoryNo = categoryService.findByUser_UserNoAndCategoryName(userNo, categoryName).getCategoryNo();
		String search = request.getParameter("search");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("postNo").descending());

		Page<PostDomain> posts = postService.findByCategory_CategoryNoAndPostTitleContaining(categoryNo, search, pageable);

		model.addAttribute("userId", userId);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("posts", posts);
		model.addAttribute("search", true);
		model.addAttribute("searchValue", search);

		return "category";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@PathVariable("userId") String userId, HttpServletRequest request, Model model) {
		return "redirect:/" + userId + "/category/total/1/search?search=" + request.getParameter("search");
	}
	
	/* comment */
	@RequestMapping(value="/saveComment", method = RequestMethod.POST)
	public String saveComment(@PathVariable("userId") String userId, HttpServletRequest request) {
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String loginUserId = (String) request.getSession().getAttribute("userId");
		String commentContent = request.getParameter("comment");
		
		UserDomain user = userService.findByUserId(loginUserId);
		PostDomain post = postService.findByPostNo(postNo);
		CategoryDomain category = post.getCategory();
		CommentDomain comment = new CommentDomain();
		
		comment.setCategory(category);
		comment.setCommentContent(commentContent);
		comment.setCommentDate(new Date());
		comment.setPost(post);
		comment.setUser(user);
		
		commentService.save(comment);
		
		return "redirect:/" + userId + "/post?postNo=" + postNo;
	}
}

