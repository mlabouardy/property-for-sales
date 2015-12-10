package bean;

import java.util.List;

import javax.ejb.Local;

import model.Advert;

@Local
public interface AdvertBean {

	public void createAdvert(Advert advert);
	public List<Advert> getAdverts();
	public Advert findById(int id);
	public List<Advert> getAdvertsByUserId(int id);
	
}
