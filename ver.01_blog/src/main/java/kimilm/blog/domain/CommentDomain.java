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

@Entity(name = "CommentDomain")
@Table(name = "comment")
public class CommentDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commentNo")
	private Integer commentNo;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "commentDate")
	private Date commentDate;

	@Column(name = "commentContent")
	private String commentContent;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryNo")
	private CategoryDomain category;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "postNo")
	private PostDomain post;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userNo")
	private UserDomain user;

	public Integer getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public CategoryDomain getCategory() {
		return category;
	}

	public void setCategory(CategoryDomain category) {
		this.category = category;
	}

	public PostDomain getPost() {
		return post;
	}

	public void setPost(PostDomain post) {
		this.post = post;
	}

	public UserDomain getUser() {
		return user;
	}

	public void setUser(UserDomain user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		return this.commentNo == ((CommentDomain) obj).getCommentNo();
	}
}
