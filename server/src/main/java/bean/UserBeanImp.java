package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.AdvertBean;
import dao.UserBean;
import entity.User;

@Stateless
public class UserBeanImp implements UserBean{

	@PersistenceContext
	private EntityManager em;
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!bean.AdvertBean")
	private AdvertBean advertBean;
	
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

	@Override
	public User getUserById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		Query query=em.createQuery("SELECT u FROM User u WHERE u.email=:email");
		query.setParameter("email", email);
		if(query.getResultList().size()==0)
			return null;
		else
			return (User)query.getSingleResult();
	}

	@Override
	public void update(User user) {
		em.merge(user);
	}

	@Override
	public void delete(User user) {
		user=em.merge(user);
		em.remove(user);
	}

	
}
