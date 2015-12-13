package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Role;

@Stateless
public class RoleBeanImp implements RoleBean{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Role role) {
		em.persist(role);
	}

	@Override
	public Role findByName(String name) {
		Query query=em.createQuery("SELECT r FROM Role r WHERE r.name=:name");
		query.setParameter("name", name);
		return (Role) query.getSingleResult();
	}

}
