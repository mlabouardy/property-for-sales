package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Favorite;

@Stateless
public class FavoriteBeanImp implements FavoriteBean{
	@PersistenceContext
	private EntityManager em;

	@Override
	public void update(Favorite favorite) {
		em.merge(favorite);
	}

	@Override
	public void create(Favorite favorite) {
		em.persist(favorite);
	}

}
