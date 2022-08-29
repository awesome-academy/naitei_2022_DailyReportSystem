package trainingManagementSystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import trainingManagementSystem.model.User;

@Component
public class AuthenticationDao {
	@Autowired
	HibernateTemplate hibernateTemplate;

	// save user
	@Transactional
	public void register(User user) {
		hibernateTemplate.save(user);
	}

	// Check if email is availible
	@SuppressWarnings("unchecked")
	public boolean isEmailVailible(String email) {
		List<User> users = (List<User>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(User.class).add(Restrictions.eq("email", email)));
		if (users.size() > 0) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByEmail(String email) {
		List<User> users = (List<User>) hibernateTemplate
				.findByCriteria(DetachedCriteria.forClass(User.class).add(Restrictions.eq("email", email)));
		return users;
	}
	
}
