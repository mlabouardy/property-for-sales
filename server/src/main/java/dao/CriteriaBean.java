package dao;

import javax.ejb.Local;

import entity.Criteria;

@Local
public interface CriteriaBean {

	public void create(Criteria criteria);
}
