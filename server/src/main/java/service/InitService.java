package service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bean.AdvertBean;
import bean.PictureBean;
import bean.UserBean;
import model.Advert;
import model.Picture;
import model.User;

@Service
@Transactional
public class InitService {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!bean.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!bean.AdvertBean")
	private AdvertBean advertBean;
	
	@EJB(mappedName="java:app/property-for-sales/PictureBeanImp!bean.PictureBean")
	private PictureBean pictureBean;
	
	@PostConstruct
	public void init(){
		User user=new User();
		user.setFirstName("Mohamed");
		user.setLastName("Labouardy");
		user.setEmail("mohamed@labouardy.com");
		user.setPassword("lollol");
		userBean.create(user);
		
		Picture picture=new Picture();
		picture.setLink("http://c.visuels.poliris.com/bigs/c/4/c/9/c4c9ba70-46a6.jpg");
		pictureBean.create(picture);
		
		Picture picture2=new Picture();
		picture2.setLink("http://d.visuels.poliris.com/bigs/d/5/2/1/d521a74f-3632.jpg");
		pictureBean.create(picture2);
		
		Advert advert=new Advert();
		advert.setContact("phone");
		advert.setLocation("Rabat");
		advert.setOwner(user);
		advert.setPrice(400.5);
		advert.getPictures().add(picture);
		advert.getPictures().add(picture2);
		advert.setSurface("200");
		advertBean.createAdvert(advert);
		
	}

}
