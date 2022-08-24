package trainingManagementSystem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import trainingManagementSystem.model.validator.PasswordConstraint;

@Entity(name = "Users")
public class User extends BaseEntity {
	public enum Role {
		USER, MANAGER, ADMIN, SYSTEM
	}

	@NotEmpty(message = "Họ và Tên không thể để trống.")
	private String name;
	@Column(unique = true)
	@NotEmpty(message = "Email không thể để trống.")
	@Email(message = "Email phải hợp lệ.")
	private String email;
	@NotEmpty(message = "Mật khẩu không thể để trống.")
	@Size(min=8, message = "Mật khẩu phải có ít nhất 8 ký tự.")
	@PasswordConstraint
	private String password;

	// Report that are being reviewed by this user
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer")
	private List<Report> reviewingReports;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Report> myReports;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Comment> comments;

	private Role role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "divisionId")
	private Division division;

	@OneToOne(mappedBy = "manager")
	private Division managingDivision;

	public User() {
		super();
	}
	
	public User(String name, String email, String password, Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Division getDivision() {
		return division;
	}

	public String getEmail() {
		return email;
	}

	public Division getManagingDivision() {
		return managingDivision;
	}

	public List<Report> getMyReports() {
		return myReports;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public List<Report> getReviewingReports() {
		return reviewingReports;
	}

	public Role getRole() {
		return role;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setManagingDivision(Division managingDivision) {
		this.managingDivision = managingDivision;
	}

	public void setMyReports(List<Report> myReports) {
		this.myReports = myReports;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setReviewingReports(List<Report> reviewedReports) {
		this.reviewingReports = reviewedReports;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
