package trainingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.AuthenticationDao;
import trainingManagementSystem.model.User.Role;

@Service
public class UserDetailsServices implements UserDetailsService {
	@Autowired
	public AuthenticationDao authenticationDao;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException  {
    	try {
        	final trainingManagementSystem.model.User user = authenticationDao.getUsersByEmail(email).get(0);
        	UserDetails userDetails = User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(roleToString(user.getRole())).build();
        	return userDetails;
    	}
    	catch (Exception e) {
			throw new UsernameNotFoundException(email);
		}
    }
	
	public static String roleToString(Role role) {
		switch (role) {
		case USER:
			return "USER";
		case ADMIN:
			return "ADMIN";
		case MANAGER:
			return "MANAGER";
		case SYSTEM:
			return "SYSTEM";
		default:
			return "USER";
		}
	}
}