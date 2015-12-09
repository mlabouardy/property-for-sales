package bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

@Stateless
public class UserBeanImp implements UserBean{

	@PersistenceContext(unitName="db-unit")
	private EntityManager em;
	
	private final String SELECT_USERS="SELECT u FROM User u";
	
	public void create(User user) {
		em.persist(user);
	}

	public void deleteById(int id) {
		User user=findById(id);
		em.remove(user);
	}

	public User findById(int id) {
		return em.find(User.class, id);
	}

	public List<User> getUsers() {
		Query query=em.createQuery(SELECT_USERS);
		return query.getResultList();
	}
	
}
