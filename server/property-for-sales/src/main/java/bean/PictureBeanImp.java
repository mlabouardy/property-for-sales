package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Picture;

@Stateless
public class PictureBeanImp implements PictureBean{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(Picture picture) {
		em.persist(picture);
	}

}
