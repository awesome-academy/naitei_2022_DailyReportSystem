package trainingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.AuthenticationDao;
import trainingManagementSystem.model.User;
import trainingManagementSystem.model.User.Role;
import trainingManagementSystem.system.exception.UserAlreadyExistException;

@Component
@Service
public class AuthenticationServices {
	@Autowired
	AuthenticationDao authenticationDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void registerUser(final User user) throws UserAlreadyExistException {
		if (authenticationDao.isEmailVailible(user.getEmail())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole(Role.USER);
			authenticationDao.register(user);
		} else {
			throw new UserAlreadyExistException();
		}

	}
}
