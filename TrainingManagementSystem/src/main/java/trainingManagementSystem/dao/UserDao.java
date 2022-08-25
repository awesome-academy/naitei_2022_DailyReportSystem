package trainingManagementSystem.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import trainingManagementSystem.model.Division;
import trainingManagementSystem.model.User;

@Component
public class UserDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	@Autowired
	private SessionFactory sessionFactory;

	// save user
	@Transactional
	public void saveUser(User user) {
		hibernateTemplate.save(user);
	}

	// get all User
	public List<User> getAllUser() {
		return hibernateTemplate.loadAll(User.class);
	}

	// get user by id
	public User getById(int id, Boolean isLock) {
		return hibernateTemplate.get(User.class, id);
	}

	// update user
	@Transactional
	public void updateUser(User user) {
		hibernateTemplate.update(user);
	}

	public List<User> getUserByDivisionId(int divisionId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		CriteriaQuery<User> selectQuery = criteriaQuery.select(root);
		selectQuery = selectQuery.where(criteriaBuilder.equal(root.get("division"), divisionId));
		selectQuery = selectQuery.where(criteriaBuilder.equal(root.get("role"), 1));
		TypedQuery<User> typedQuery = session.createQuery(selectQuery);
		List<User> users = typedQuery.getResultList();

		session.close();
		return users;
	}

	// get users in a division
	public List<User> getAllUserByDivisionId(int divisionId) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Query<User> query = session.createNativeQuery("select * from users u where u.divisionId= :divisionId",
				User.class);
		query.setParameter("divisionId", divisionId);
		List<User> users = query.list();
		session.close();
		return users;
	}

	// [manager] remove user from a division
	public void removeUserFromDivision(int id) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		User user = (User) session.get(User.class, id);

		user.setDivision(null);
		session.saveOrUpdate(user);

		tx.commit();
		session.close();
	}

	// [manager] add a user to a division
	public void addUserToDivision(int divisionId, int userId) {
		try {
			Session session = hibernateTemplate.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			User user = (User) session.get(User.class, userId);
			Division division = (Division) session.get(Division.class, divisionId);

			user.setDivision(division);
			session.saveOrUpdate(user);

			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// [manager] get new users (who do not belong to a division)
	@SuppressWarnings("unchecked")
	public List<User> getNewUsers() {
		try {
			Session session = hibernateTemplate.getSessionFactory().openSession();
			// Criteria Query
//			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//			Root<User> root = criteriaQuery.from(User.class);
//			CriteriaQuery<User> selectQuery = criteriaQuery.select(root);
//			selectQuery = selectQuery.where(criteriaBuilder.isNull(root.get("division")));
//			TypedQuery<User> typedQuery = session.createQuery(selectQuery);
//			List<User> users = typedQuery.getResultList();

			Query<User> query = session.createNativeQuery("select * from users u where u.divisionId is null and role=1",
					User.class);
			List<User> users = query.list();

			System.out.println(users);

			session.close();
			return users;
		} catch (Exception e) {
			throw e;
		}
	}

	// delete user
	@Transactional
	public void deleteUser(Long id)

	{
		hibernateTemplate.delete(hibernateTemplate.get(User.class, id));
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<User> loadUsersNotinManagerID() {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Query<User> query = session
				.createNativeQuery("select * FROM Users u WHERE u.id NOT IN (SELECT d.managerId FROM Divisions d)"
						+ "AND (u.role=0 OR u.role=1)", User.class);
		List<User> users = query.list();
		return users;
	}

}
