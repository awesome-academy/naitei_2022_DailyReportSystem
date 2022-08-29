package trainingManagementSystem.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import trainingManagementSystem.model.Comment;

@Component
public class CommentDao {
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Transactional
	public void saveComment(Comment comment) {
		hibernateTemplate.save(comment);
	}

	@Transactional
	public void deleteComment(int id)

	{
		hibernateTemplate.delete(hibernateTemplate.get(Comment.class, id));
	}
}
