package trainingManagementSystem.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.UserDao;
import trainingManagementSystem.model.User;

@Component
@Service
public class UserServices {

	@Autowired
	UserDao userDao;

	// save user
	public void saveUser(User user) {
		try {
			userDao.saveUser(user);
		} catch (Exception e) {
			throw e;
		}
	}

	// get all user
	public List<User> getAllUser() {
		try {
			return userDao.getAllUser();
		} catch (Exception e) {
			throw null;
		}
	}

	// get by id
	public User getById(int id, Boolean isLock) {
		try {
			return userDao.getById(id, isLock);
		} catch (Exception e) {
			throw null;
		}
	}

	// update user
	public void updateUser(User user) {
		try {
			userDao.updateUser(user);
		} catch (Exception e) {
			throw e;
		}
	}

	// delete User
	public void deleteUser(Long id) {
		try {
			userDao.deleteUser(id);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<User> loadUsersNotinManagerID() {
		try {
			return userDao.loadUsersNotinManagerID();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	// [manager] remove user from division
	public void removeUserFromDivision(int id) {
		try {
			userDao.removeUserFromDivision(id);
		} catch (Exception e) {
			throw e;
		}
	}

	// [manager] get user by divisionid
	public List<User> getUserByDivisionId(int divisionId) {
		try {
			return userDao.getUserByDivisionId(divisionId);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	// [manager] get new users (who do not belong to a division)
	public List<User> getNewUsers() {
		try {
			return userDao.getNewUsers();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	// [manager] add a user to a division
	public void addUserToDivision(int divisionId, int userId) {
		try {
			userDao.addUserToDivision(divisionId, userId);
		} catch (Exception e) {
			throw e;
		}
	}
	// [admin], get all users by division id
	public List<User> getAllUserByDivisionId(int divisionId) {
		try {
			return userDao.getAllUserByDivisionId(divisionId);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}
