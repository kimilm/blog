package kimilm.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kimilm.blog.domain.CommentDomain;
import kimilm.blog.repository.CommentRepository;
@Service
@Transactional
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	
    public Page<CommentDomain> findByPost_PostNo(int postNo, Pageable pageable) {
    	return commentRepository.findByPost_PostNo(postNo, pageable);
    }
	
	public CommentDomain findByCommentNo (String commentNo) {
		return commentRepository.findByCommentNo(Integer.parseInt(commentNo));
	}
	
	public CommentDomain findByCommentNo(int commentNo) {
		return commentRepository.findByCommentNo(commentNo);
	}
	
	public CommentDomain save(CommentDomain comment) {
		return commentRepository.save(comment);
	}
	
	public void deleteByCommentNo(int commentNo) {
		commentRepository.deleteByCommentNo(commentNo);
	}
}