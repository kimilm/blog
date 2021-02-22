package kimilm.blog.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "PostDomain")
@Table(name = "post")
public class PostDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "postNo")
	private Integer postNo;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "postDate")
	private Date postDate;

	@Column(name = "postTitle")
	private String postTitle;

	@Column(name = "postHits")
	private Integer postHits;
	
	@Column(name = "postContent")
	private String postContent;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryNo")
	private CategoryDomain category;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userNo")
	private UserDomain user;

	public CategoryDomain getCategory() {
		return category;
	}

	public void setCategory(CategoryDomain category) {
		this.category = category;
	}

	public Integer getPostNo() {
		return postNo;
	}

	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Integer getPostHits() {
		return postHits;
	}

	public void setPostHits(Integer postHits) {
		this.postHits = postHits;
	}
	
	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public UserDomain getUser() {
		return user;
	}

	public void setUser(UserDomain user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		return this.postNo == ((PostDomain) obj).getPostNo();
	}
}
