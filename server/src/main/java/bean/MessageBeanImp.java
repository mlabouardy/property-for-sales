package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Message;

@Stateless
public class MessageBeanImp implements MessageBean{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Message msg) {
		em.persist(msg);
	}

}
