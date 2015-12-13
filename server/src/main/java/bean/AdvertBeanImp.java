package bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

import model.Advert;

@Stateless
public class AdvertBeanImp implements AdvertBean{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Advert> getAdverts() {
		Query query=em.createQuery("SELECT a FROM Advert a");
		return query.getResultList();
	}

	@Override
	public void createAdvert(Advert advert) {
		em.persist(advert);
	}

	@Override
	public Advert findById(int id) {
		return em.find(Advert.class, id);
	}

	@Override
	public List<Advert> getAdvertsByUserId(int id) {
		Query query=em.createQuery("SELECT a FROM Advert a WHERE a.owner.id=:id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(Advert advert) {
		advert=em.merge(advert);
		em.remove(advert);
	}

}
