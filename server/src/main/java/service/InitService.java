package service;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bean.AdvertBean;
import bean.PictureBean;
import bean.RoleBean;
import bean.UserBean;
import model.Advert;
import model.Picture;
import model.Role;
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
	
	@EJB(mappedName="java:app/property-for-sales/RoleBeanImp!bean.RoleBean")
	private RoleBean roleBean;
	
	@PostConstruct
	public void init(){
		Role role_user=new Role();
		role_user.setName("ROLE_USER");
		roleBean.create(role_user);
		
		Role role_admin=new Role();
		role_admin.setName("ROLE_ADMIN");
		roleBean.create(role_admin);
		
		Picture userPic=new Picture();
		userPic.setLink("1cc126a5f30a1dfd95908d14d908e6501450012301871.png");
		pictureBean.create(userPic);
		
		User user=new User();
		user.setFirstName("Mohamed");
		user.setLastName("Labouardy");
		user.setEmail("mohamed@labouardy.com");
		user.setPassword("lollol");
		user.setAddress("AVENUE COLLEGNO BAT A NUM 509");
		user.setPhone("0664435680");
		user.setRole(role_user);
		user.setPicture(userPic);
		userBean.create(user);
		
		User admin=new User();
		admin.setFirstName("Admin");
		admin.setLastName("Admin");
		admin.setEmail("admin@labouardy.com");
		admin.setPassword("admin");
		admin.setAddress("AVENUE COLLEGNO BAT A NUM 402");
		admin.setPhone("0674435610");
		admin.setRole(role_admin);
		admin.setPicture(userPic);
		userBean.create(admin);
		
		Picture pic1=new Picture();
		pic1.setLink("409bfeafc2ec360225d9a3e0eac466931449847334595.png");
		pictureBean.create(pic1);
		
		Picture pic2=new Picture();
		pic2.setLink("409bfeafc2ec360225d9a3e0eac466931449847334595.png");
		pictureBean.create(pic2);
		
		Advert advert=new Advert();
		advert.setLocation("Rabat");
		advert.setOwner(user);
		advert.setType("T1");
		advert.setDescription("Beautiful T1 near Tram B");
		advert.setPrice(400.5);
		advert.getPictures().add(pic1);
		advert.getPictures().add(pic2);
		advert.setSurface("200");
		advert.setCreated_at(new Date());
		advertBean.createAdvert(advert);
		
	}

}
