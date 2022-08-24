package trainingManagementSystem.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Reports")
public class Report extends BaseEntity {
	private Date date;
	private String actualTask;
	private String plannedTask;
	private String issue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reviewerId")
	private User reviewer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "report")
	private List<Comment> comments;

	public Report() {
		super();
	}

	public Report(Date date, String actualTask, String plannedTask, String issue) {
		super();
		this.date = date;
		this.actualTask = actualTask;
		this.plannedTask = plannedTask;
		this.issue = issue;
	}

	public String getActualTask() {
		return actualTask;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Date getDate() {
		return date;
	}

	public String getIssue() {
		return issue;
	}

	public String getPlannedTask() {
		return plannedTask;
	}

	public User getReviewer() {
		return reviewer;
	}

	public User getUser() {
		return user;
	}

	public void setActualTask(String actualTask) {
		this.actualTask = actualTask;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public void setPlannedTask(String plannedTask) {
		this.plannedTask = plannedTask;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
