package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.CriteriaBean;
import entity.Criteria;


@Stateless
public class CriteriaBeanImp implements CriteriaBean{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Criteria criteria) {
		em.merge(criteria);
		em.flush();
	}

}
