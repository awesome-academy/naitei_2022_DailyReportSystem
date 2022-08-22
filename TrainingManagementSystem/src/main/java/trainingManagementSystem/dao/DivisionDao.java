package trainingManagementSystem.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import trainingManagementSystem.model.Division;

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
	
}
