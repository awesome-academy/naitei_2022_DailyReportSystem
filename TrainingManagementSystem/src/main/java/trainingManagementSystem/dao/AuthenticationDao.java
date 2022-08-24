package trainingManagementSystem.dao;

import javax.transaction.Transactional;

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
	
	
}
