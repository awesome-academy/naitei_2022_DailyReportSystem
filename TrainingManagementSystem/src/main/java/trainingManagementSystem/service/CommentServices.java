package trainingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.CommentDao;
import trainingManagementSystem.model.Comment;

@Component
@Service
public class CommentServices {
	@Autowired
	CommentDao commentDao;

	// save comment
	public void saveComment(Comment comment) {
		try {
			commentDao.saveComment(comment);
		} catch (Exception e) {
			throw e;
		}
	}

	public String deleteComment(int id) {
		try {
			commentDao.deleteComment(id);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
}
