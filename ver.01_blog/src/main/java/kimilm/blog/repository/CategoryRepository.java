package kimilm.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kimilm.blog.domain.CategoryDomain;

public interface CategoryRepository extends JpaRepository<CategoryDomain, Integer>{
	CategoryDomain findByCategoryNo(int CategoryNo);
	//CategoryDomain findByCategoryName(String CategoryName);
	
	CategoryDomain findByUser_UserIdAndCategoryNo(String userId, int categoryNo);
	CategoryDomain findByUser_UserIdAndCategoryName(String userId, String categoryName);
	
	CategoryDomain findByUser_UserNoAndCategoryNo(int userNo, int categoryNo);
	CategoryDomain findByUser_UserNoAndCategoryName(int userNo, String categoryName);
	
	List<CategoryDomain> findAll();
	
	List<CategoryDomain> findByUser_UserNo(int userNo);
	List<CategoryDomain> findByUser_UserId(String userId);
	
	void deleteByUser_UserIdAndCategoryName(String userId, String categoryName);
	
	CategoryDomain save(CategoryDomain category);
}
