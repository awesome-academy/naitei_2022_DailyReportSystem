package trainingManagementSystem.dao;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import trainingManagementSystem.model.Division;
import trainingManagementSystem.model.Report;

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

	// pagination reports
	public List<Report> paginationReports(int divisionId, int pageNumber, int pageSize) {
		try (Session session = hibernateTemplate.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Report> criteriaQuery = criteriaBuilder.createQuery(Report.class);
			Root<Report> root = criteriaQuery.from(Report.class);
			CriteriaQuery<Report> selectQuery = criteriaQuery.select(root);
			selectQuery = selectQuery
					.where(criteriaBuilder.equal(root.get("user").get("division"), divisionId));

			TypedQuery<Report> typedQuery = session.createQuery(selectQuery);
			typedQuery.setFirstResult(pageNumber * pageSize);
			typedQuery.setMaxResults(pageSize);
			List<Report> reports = typedQuery.getResultList();
			session.close();

			return reports;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

}
