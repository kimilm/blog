package kimilm.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kimilm.blog.domain.CategoryDomain;
import kimilm.blog.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public CategoryDomain findByCategoryNo(String categoryNo) {
		return categoryRepository.findByCategoryNo(Integer.parseInt(categoryNo));
	}
	
	public CategoryDomain findByCategoryNo(int categoryNo) {
		return categoryRepository.findByCategoryNo(categoryNo);
	}
	
//	public CategoryDomain findByCategoryName(String categoryName) {
//		return categoryRepository.findByCategoryName(categoryName);
//	}
//	
	
	public CategoryDomain findByUser_UserIdAndCategoryNo(String userId, String categoryNo) {
		return categoryRepository.findByUser_UserIdAndCategoryNo(userId, Integer.parseInt(categoryNo));
	}

	public CategoryDomain findByUser_UserIdAndCategoryNo(String userId, int categoryNo) {
		return categoryRepository.findByUser_UserIdAndCategoryNo(userId, categoryNo);
	}

	public CategoryDomain findByUser_UserIdAndCategoryName(String userId, String categoryName) {
		return categoryRepository.findByUser_UserIdAndCategoryName(userId, categoryName);
	}
	
	public CategoryDomain findByUser_UserNoAndCategoryNo(int userNo, int categoryNo) {
		return categoryRepository.findByUser_UserNoAndCategoryNo(userNo, categoryNo);
	}
	public CategoryDomain findByUser_UserNoAndCategoryName(int userNo, String categoryName) {
		return categoryRepository.findByUser_UserNoAndCategoryName(userNo, categoryName);
	}
	
	public List<CategoryDomain> findAll() {
		return categoryRepository.findAll();
	}
	
	public List<CategoryDomain> findByUser_UserNo(int userNo) {
		return categoryRepository.findByUser_UserNo(userNo);
	}
	
	public List<CategoryDomain> findByUser_UserId(String userId) {
		return categoryRepository.findByUser_UserId(userId);
	}

	public void deleteByUser_UserIdAndCategoryName(String userId, String categoryName) {
		categoryRepository.deleteByUser_UserIdAndCategoryName(userId, categoryName);
	}
	
	public CategoryDomain save(CategoryDomain category) {
		return categoryRepository.save(category);
	}
}