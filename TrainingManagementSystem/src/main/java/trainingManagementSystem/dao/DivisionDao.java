package trainingManagementSystem.dao;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

import org.apache.logging.log4j.spi.LoggerContextKey;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import trainingManagementSystem.model.Division;
import trainingManagementSystem.model.Report;
import trainingManagementSystem.model.User;

@Component
public class DivisionDao {
	@Autowired
	HibernateTemplate hibernateTemplate;

	// get all division
	public List<Division> getAllDivision() {
		return hibernateTemplate.loadAll(Division.class);
	}

	// get division by id
	public Division getById(int divisionId) {
		return hibernateTemplate.get(Division.class, divisionId);
	}

	// save division
	@Transactional
	public void saveDivision(Division division) {
//		User user = hibernateTemplate.get(User.class, userId);
//		division.setManager(user);
		hibernateTemplate.save(division);
	}

	// get report by id
	public Report getReportById(Integer id, Boolean isLock) {
		return hibernateTemplate.get(Report.class, id);
	}

	// get number of reports
	public int getReportsLength(int divisionId, String txtSearch, @Nullable LocalDate reportDateSearch) {
		try (Session session = hibernateTemplate.getSessionFactory().openSession()) {
			Query<Report> query;
			if (reportDateSearch != null) {
				query = session.createNativeQuery(
						"select r.* from Reports r join Users ur on ur.id = r.reviewerId join Users u on u.id = r.userId "
								+ "where ur.divisionId= :divisionId and u.name like :txtSearch and DAY(r.date) = :daySearch"
						, Report.class);
				query.setParameter("divisionId", divisionId);
				query.setParameter("txtSearch", "%" + txtSearch + "%");
				query.setParameter("daySearch", reportDateSearch.getDayOfMonth());
			} else {
				query = session.createNativeQuery(
						"select r.* from Reports r join Users ur on ur.id = r.reviewerId join Users u on u.id = r.userId "
								+ "where ur.divisionId= :divisionId and u.name like :txtSearch",
						Report.class);
				query.setParameter("divisionId", divisionId);
				query.setParameter("txtSearch", "%" + txtSearch + "%");
			}
			List<Report> reports = query.list();
			session.close();

			return reports.size();
		} catch (Exception e) {
			return 0;
		}
	}

	// pagination reports
	public List<Report> paginationReports(int divisionId, String txtSearch, int pageNumber, int pageSize,
			@Nullable LocalDate reportDateSearch) {
		try (Session session = hibernateTemplate.getSessionFactory().openSession()) {
			Query<Report> query;
			if (reportDateSearch != null) {
				query = session.createNativeQuery(
						"select r.* from Reports r join Users ur on ur.id = r.reviewerId join Users u on u.id = r.userId "
								+ "where ur.divisionId= :divisionId and u.name like :txtSearch and DAY(r.date) = :daySearch",
						Report.class);
				query.setParameter("divisionId", divisionId);
				query.setParameter("txtSearch", "%" + txtSearch + "%");
				query.setParameter("daySearch", reportDateSearch.getDayOfMonth());
//				query.setParameter("monthSearch", reportDateSearch.getMonth());
//				query.setParameter("yearSearch", reportDateSearch.getYear());
			} else {
				query = session.createNativeQuery(
						"select r.* from Reports r join Users ur on ur.id = r.reviewerId join Users u on u.id = r.userId "
								+ "where ur.divisionId= :divisionId and u.name like :txtSearch",
						Report.class);
				query.setParameter("divisionId", divisionId);
				query.setParameter("txtSearch", "%" + txtSearch + "%");
			}

			query.setFirstResult(pageNumber * pageSize);
			query.setMaxResults(pageSize);
			List<Report> reports = query.list();

			session.close();

			return reports;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	// delete division
	@Transactional
	public void deleteDivision(Integer id)

	{
		try {
			Session session = hibernateTemplate.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			Division division = (Division) session.get(Division.class, id, LockMode.READ);

			division.setManager(null);
			hibernateTemplate.delete(hibernateTemplate.get(Division.class, id));
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update Division
	@Transactional
	public void updateDivision(Division division) {
		hibernateTemplate.update(division);
	}
}
