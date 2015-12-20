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

	@Override
	public void delete(int id) {
		Message message=em.find(Message.class, id);
		message=em.merge(message);
		em.remove(message);
	}

}
