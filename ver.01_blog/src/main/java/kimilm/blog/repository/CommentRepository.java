package kimilm.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kimilm.blog.domain.CommentDomain;

public interface CommentRepository extends JpaRepository<CommentDomain, Integer>{
	
	Page<CommentDomain> findByPost_PostNo(int postNo, Pageable pageable);
	
	CommentDomain findByCommentNo(int commentNo);
	
	CommentDomain save(CommentDomain comment);
	
	void deleteByCommentNo(int deleteByCommentNo);
}
