package kimilm.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kimilm.blog.domain.PostDomain;
import kimilm.blog.repository.PostRepository;
@Service
@Transactional
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public Long countByCategory_CategoryNo(int categoryNo) {
		return postRepository.countByCategory_CategoryNo(categoryNo);
	}
//	
//	public Page<PostDomain> findAll(Pageable pageable) {
//		return postRepository.findAll(pageable);
//	}
//	
	public Page<PostDomain> findByCategory_CategoryNo(int categoryNo, Pageable pageable) {
		return postRepository.findByCategory_CategoryNo(categoryNo, pageable);
	}
	
	public Page<PostDomain> findByCategory_CategoryNoAndPostTitleContaining(int categoryNo, String postTitle, Pageable pageable) {
		return postRepository.findByCategory_CategoryNoAndPostTitleContaining(categoryNo, postTitle, pageable);
	}
//	
//	public Page<PostDomain> findByPostTitleContaining(String postTitle, Pageable pageable) {
//		return postRepository.findByPostTitleContaining(postTitle, pageable);
//	}
	
	public Long countByUser_UserNoAndCategory_CategoryNo(int userNo, int categoryNo) {
		return postRepository.countByUser_UserNoAndCategory_CategoryNo(userNo, categoryNo);
	}

	public Page<PostDomain> findByUser_UserNo(int userNo, Pageable pageable) {
		return postRepository.findByUser_UserNo(userNo, pageable);
	}

	public Page<PostDomain> findByUser_UserNoAndCategory_CategoryNo(int userNo, int categoryNo, Pageable pageable) {
		return postRepository.findByUser_UserNoAndCategory_CategoryNo(userNo, categoryNo, pageable);
	}

	public Page<PostDomain> findByUser_UserNoAndPostTitleContaining(int userNo, String postTitle, Pageable pageable) {
		return postRepository.findByUser_UserNoAndPostTitleContaining(userNo, postTitle, pageable);
	}
	
	public Page<PostDomain> findByUser_UserNoAndCategory_CategoryNoAndPostTitleContaining(int userNo, int categoryNo, String postTitle, Pageable pageable) {
		return postRepository.findByUser_UserNoAndCategory_CategoryNoAndPostTitleContaining(userNo, categoryNo, postTitle, pageable);
	}
	
	// -- content -- //
	
	public PostDomain findByPostNo (String postNo) {
		return postRepository.findByPostNo(Integer.parseInt(postNo));
	}

	public PostDomain findByPostNo (int postNo) {
		return postRepository.findByPostNo(postNo);
	}
	
	public PostDomain findFirstByOrderByPostNoDesc() {
		return postRepository.findFirstByOrderByPostNoDesc();
	}
	
	public void deleteByPostNo(String postNo) {
		postRepository.deleteByPostNo(Integer.parseInt(postNo));
	}
	
	public void deleteByPostNo(int postNo) {
		postRepository.deleteByPostNo(postNo);
	}
	
	public PostDomain save(PostDomain post) {
		return postRepository.save(post);
	}
}