package bean;

import javax.ejb.Local;

import model.Favorite;

@Local
public interface FavoriteBean {

	public void update(Favorite favorite);

	public void create(Favorite favorite);
	
}
