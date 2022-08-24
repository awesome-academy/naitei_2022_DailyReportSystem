package trainingManagementSystem.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import trainingManagementSystem.model.Report;
import trainingManagementSystem.model.User;

@Component
public class ManagerDao {
	private final static Logger logger = LogManager.getFormatterLogger(ManagerDao.class);

	@Autowired
	HibernateTemplate hibernateTemplate;

	// get all Report
	@SuppressWarnings("deprecation")
	public List<Report> getAllReports() {
        @SuppressWarnings("unchecked")
		List<Report> reports = (List<Report>) hibernateTemplate
				.findByNamedParam("select R" + " from Reports R " + "where R.reviewer.id = :id ", "id", 5);
		return reports;
	}
	
	// get report by id
	public Report getReportById(Integer id, Boolean isLock) {
		return hibernateTemplate.get(Report.class, id);
	}

}
