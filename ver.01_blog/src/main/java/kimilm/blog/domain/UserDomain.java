package kimilm.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="UserDomain")
@Table(name="user")
public class UserDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userNo")
	private Integer userNo;
	
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "userPwd")
	private String userPwd;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "userEmail")
	private String userEmail;
	
	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.userNo == ((UserDomain)obj).getUserNo();
	}
}
