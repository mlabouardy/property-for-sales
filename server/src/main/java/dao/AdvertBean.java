package dao;

import java.util.List;

import javax.ejb.Local;

import entity.Advert;

@Local
public interface AdvertBean {

	public void createAdvert(Advert advert);
	public List<Advert> getAdverts();
	public Advert findById(int id);
	public List<Advert> getAdvertsByUserId(int id);
	public void remove(Advert advert);
	public List<Advert> getMostRecents();
	
}
