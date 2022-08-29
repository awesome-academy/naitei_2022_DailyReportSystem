package trainingManagementSystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import trainingManagementSystem.dao.DivisionDao;
import trainingManagementSystem.model.Division;
import trainingManagementSystem.model.Report;

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

	// get by id
	public Report getReportById(Integer id, Boolean isLock) {
		try {
			return divisionDao.getReportById(id, isLock);
		} catch (Exception e) {
			return null;
		}
	}

	// get number of reports
	public int getReportsLength(int divisionId, String txtSearch, @Nullable LocalDate reportDateSearch) {
		try {
			return divisionDao.getReportsLength(divisionId, txtSearch, reportDateSearch);
		} catch (Exception e) {
			return 0;
		}
	}

	// pagination reports
	public List<Report> paginationReports(int divisionId, String txtSearch, int pageNumber, int pageSize,
			@Nullable LocalDate reportDateSearch) {
		try {
			return divisionDao.paginationReports(divisionId, txtSearch, pageNumber, pageSize, reportDateSearch);
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
	
	//update division
	public void updateDivision(Division division) {
		try {
			divisionDao.updateDivision(division);
		} catch (Exception e) {
			throw e;
		}
	}

}
