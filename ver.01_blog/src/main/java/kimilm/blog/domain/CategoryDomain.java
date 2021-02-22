package kimilm.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "CategoryDomain")
@Table(name = "category")
public class CategoryDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryNo")
	private Integer categoryNo;

	@Column(name = "categoryName")
	private String categoryName;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userNo")
	private UserDomain user;

	public Integer getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public UserDomain getUser() {
		return user;
	}

	public void setUser(UserDomain user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		return this.categoryNo == ((CategoryDomain) obj).getCategoryNo();
	}
}
