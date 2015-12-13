package bean;

import javax.ejb.Local;

import model.Criteria;

@Local
public interface CriteriaBean {

	public void create(Criteria criteria);
}
