package trainingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.ManagerDao;

@Component
@Service
public class ManagerServices {

	@Autowired
	ManagerDao managerDao;
}
