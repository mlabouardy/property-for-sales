package dao;

import javax.ejb.Local;

import entity.Favorite;

@Local
public interface FavoriteBean {

	public void update(Favorite favorite);

	public void create(Favorite favorite);
	
}
