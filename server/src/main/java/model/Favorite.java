package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Favorite {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Advert> adverts;
	
	public Favorite(){
		adverts=new ArrayList<>();
	}

	public List<Advert> getAdverts() {
		return adverts;
	}

	public void setAdverts(List<Advert> favorites) {
		this.adverts = favorites;
	}
}
