package trainingManagementSystem.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Comments")
public class Comment extends BaseEntity {
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reportId")
	private Report report;

	public Comment() {
		super();
	}

	public Comment(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public Report getReport() {
		return report;
	}

	public User getUser() {
		return user;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
