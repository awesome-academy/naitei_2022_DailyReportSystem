package trainingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.AuthenticationDao;
import trainingManagementSystem.model.User;

@Component
@Service
public class AuthenticationServices {
	@Autowired
	AuthenticationDao authenticationDao;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	public void registerUser(final User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			authenticationDao.register(user);
		} catch (Exception e) {
			throw e;
		}
	}
}
