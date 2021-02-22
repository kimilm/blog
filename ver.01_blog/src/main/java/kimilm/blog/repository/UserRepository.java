package kimilm.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kimilm.blog.domain.UserDomain;

public interface UserRepository extends JpaRepository<UserDomain, Integer>{
	UserDomain findByUserIdAndUserPwd(String userId, String userPwd);
	UserDomain findByUserNo(int userNo);
	UserDomain findByUserId(String userId);
	
	UserDomain save(UserDomain userDomain);
}
