package kimilm.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kimilm.blog.domain.PostDomain;

public interface PostRepository extends JpaRepository<PostDomain, Integer>{
	
	Long countByCategory_CategoryNo(int categoryNo);
//	
//	Page<PostDomain> findAll(Pageable pageable);
	Page<PostDomain> findByCategory_CategoryNo(int categoryNo, Pageable pageable);
	Page<PostDomain> findByCategory_CategoryNoAndPostTitleContaining(int categoryNo, String postTitle, Pageable pageable);
//	Page<PostDomain> findByPostTitleContaining(String postTitle, Pageable pageable);
	
	Long countByUser_UserNoAndCategory_CategoryNo(int userNo, int categoryNo);
	
	Page<PostDomain> findByUser_UserNo(int userNo, Pageable pageable);
	Page<PostDomain> findByUser_UserNoAndCategory_CategoryNo(int userNo, int categoryNo, Pageable pageable);
	Page<PostDomain> findByUser_UserNoAndPostTitleContaining(int userNo, String postTitle, Pageable pageable);
	Page<PostDomain> findByUser_UserNoAndCategory_CategoryNoAndPostTitleContaining(int userNo, int categoryNo, String postTitle, Pageable pageable);
	
	PostDomain findByPostNo(int postNo);
	
	PostDomain findFirstByOrderByPostNoDesc();
	
	void deleteByPostNo(int postNo);
	
	PostDomain save(PostDomain post);
}
