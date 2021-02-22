package kimilm.blog.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kimilm.blog.domain.CategoryDomain;
import kimilm.blog.domain.UserDomain;
import kimilm.blog.service.CategoryService;
import kimilm.blog.service.CommentService;
import kimilm.blog.service.PostService;
import kimilm.blog.service.UserService;

@Controller
@RequestMapping("/rest/{userId}")
public class BlogRestController {

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;

//	@RequestMapping(value="/category", method=RequestMethod.GET)
//	public ResponseEntity<CategoryDomain> getCategory(HttpServletRequest request) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("result", categoryService.findByCategoryNo(request.getParameter("categoryNo")));
//		return new ResponseEntity(result, HttpStatus.OK);
//	}

	@RequestMapping(value = "")
	public ResponseEntity<UserDomain> getUser(@PathVariable("userId") String userId) {
		Map<String, Object> result = new HashMap<String, Object>();

		UserDomain user = userService.findByUserId(userId);

		result.put("user", user);

		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteCategory/{categoryName}", method = RequestMethod.DELETE)
	public ResponseEntity<List<CategoryDomain>> deleteCategory(@PathVariable("userId") String userId,
			@PathVariable("categoryName") String categoryName) {
		Map<String, Object> result = new HashMap<String, Object>();

		categoryService.deleteByUser_UserIdAndCategoryName(userId, categoryName);

		result.put("result", true);

		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/UpdateCategory/{categoryName}/{categoryNo}", method = RequestMethod.PUT)
	public ResponseEntity<List<CategoryDomain>> updateCategory(@PathVariable("userId") String userId,
			@PathVariable("categoryName") String categoryName, @PathVariable("categoryNo") int categoryNo) {
		Map<String, Object> result = new HashMap<String, Object>();

		UserDomain user = userService.findByUserId(userId);
		
		
		CategoryDomain category = new CategoryDomain();
		category.setCategoryNo(categoryNo);
		category.setCategoryName(categoryName);
		category.setUser(user);
		
		System.out.println("########"+category);
		
		categoryService.save(category);

		result.put("result", true);

		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/UpdateCategory/{categoryName}", method = RequestMethod.PUT)
	public ResponseEntity<List<CategoryDomain>> updateCategory(@PathVariable("userId") String userId,
			@PathVariable("categoryName") String categoryName) {
		Map<String, Object> result = new HashMap<String, Object>();

		UserDomain user = userService.findByUserId(userId);
		
		CategoryDomain category = new CategoryDomain();
		category.setCategoryName(categoryName);
		category.setUser(user);
		
		System.out.println("########"+category);
		
		categoryService.save(category);

		result.put("result", true);

		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDomain>> getCategoryList(@PathVariable("userId") String userId) {
		Map<String, Object> result = new HashMap<String, Object>();

		List<CategoryDomain> list = categoryService.findByUser_UserId(userId);
		List<Long> count = new ArrayList<>();

		Long total = 0L;
		Long temp;

		for (CategoryDomain domain : list) {
			temp = postService.countByCategory_CategoryNo(domain.getCategoryNo());

			count.add(temp);
			total += temp;
		}

		result.put("name", list);
		result.put("count", count);
		result.put("total", total);

		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteComment/{commentNo}", method = RequestMethod.DELETE)
	public ResponseEntity<List<CategoryDomain>> getCategoryList(@PathVariable("userId") String userId, @PathVariable("commentNo") int commentNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		commentService.deleteByCommentNo(commentNo);
		
		result.put("result", true);
		return new ResponseEntity(result, HttpStatus.OK);
	}

//	@RequestMapping(value="/category/{categoryName}", method=RequestMethod.GET)
//	public ResponseEntity<List<CategoryDomain>> getCategoryList(@PathVariable String categoryName) {
//		int categoryNo = categoryService.findByCategoryName(categoryName).getCategoryNo();
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("post", postService.findByCategory_CategoryNo(categoryNo, PageRequest.of(0, 10, Sort.by("postNo").descending())));
//		return new ResponseEntity(result, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/category/{categoryName}/{page}", method=RequestMethod.GET)
//	public ResponseEntity<List<CategoryDomain>> getCategoryList(@PathVariable String categoryName, @PathVariable int page) {
//		int categoryNo = categoryService.findByCategoryName(categoryName).getCategoryNo();
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("post", postService.findByCategory_CategoryNo(categoryNo, PageRequest.of(page - 1, 10, Sort.by("postNo").descending())));
//		return new ResponseEntity(result, HttpStatus.OK);
//	}

//	@RequestMapping(value="/post/top", method=RequestMethod.GET)
//	public ResponseEntity<PostDomain> getPostTop() {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("post", postService.findFirstByOrderByPostNoDesc());
//		return new ResponseEntity(result, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/post", method=RequestMethod.GET)
//	public ResponseEntity<PostDomain> getPost(HttpServletRequest request) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("post", postService.findByPostNo(request.getParameter("postNo")));
//		return new ResponseEntity(result, HttpStatus.OK);
//	}
}
