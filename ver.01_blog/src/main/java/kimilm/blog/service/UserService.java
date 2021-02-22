package kimilm.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kimilm.blog.domain.UserDomain;
import kimilm.blog.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public UserDomain findByUserNo(String userNo) {
		return userRepository.findByUserNo(Integer.parseInt(userNo));
	}
	
	public UserDomain findByUserNo(int userNo) {
		return userRepository.findByUserNo(userNo);
	}
	
	public UserDomain findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	public UserDomain save(UserDomain user) {
		return userRepository.save(user);
	}
}