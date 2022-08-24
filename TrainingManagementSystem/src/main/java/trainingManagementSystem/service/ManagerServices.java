package trainingManagementSystem.service;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.ManagerDao;
import trainingManagementSystem.model.Report;

@Component
@Service
public class ManagerServices {

	@Autowired
	ManagerDao managerDao;

	// get all report
	public List<Report> getAllReports() {
		try {
			return managerDao.getAllReports();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	// get by id
	public Report getReportById(Integer id, Boolean isLock) {
		try {
			return managerDao.getReportById(id, isLock);
		} catch (Exception e) {
			return null;
		}
	}
}
