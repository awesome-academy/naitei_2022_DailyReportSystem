package trainingManagementSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.*;
import trainingManagementSystem.model.*;

@Component
@Service
public class DivisionServices {
	@Autowired
	DivisionDao divisionDao;

	// get all division
	public List<Division> getAllDivision() {
		try {
			return divisionDao.getAllDivision();
		} catch (Exception e) {
			throw e;
		}
	}

	// get by id
	public Division getById(Integer id) {
		try {
			return divisionDao.getById(id);
		} catch (Exception e) {
			return null;
		}
	}

	// pagination reports
	public List<Report> paginationReports(int divisionId, int pageNumber, int pageSize) {
		try {
			return divisionDao.paginationReports(divisionId, pageNumber, pageSize);
		} catch (Exception e) {
			return null;
		}
	}

	// save division
	public void saveDivision(Division division) {
		try {
			divisionDao.saveDivision(division);
		} catch (Exception e) {
			throw e;
		}
	}

	// delete division
	public void deleteDivision(Integer id) {
		try {
			divisionDao.deleteDivision(id);
		} catch (Exception e) {
			throw e;
		}
	}

}
