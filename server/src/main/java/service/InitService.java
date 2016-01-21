package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdvertBean;
import dao.FavoriteBean;
import dao.MessageBean;
import dao.PictureBean;
import dao.RoleBean;
import dao.UserBean;
import entity.Advert;
import entity.Favorite;
import entity.Message;
import entity.Picture;
import entity.Role;
import entity.User;

@Service
@Transactional
public class InitService {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!dao.AdvertBean")
	private AdvertBean advertBean;
	
	@EJB(mappedName="java:app/property-for-sales/PictureBeanImp!dao.PictureBean")
	private PictureBean pictureBean;
	
	@EJB(mappedName="java:app/property-for-sales/RoleBeanImp!dao.RoleBean")
	private RoleBean roleBean;
	
	@EJB(mappedName="java:app/property-for-sales/FavoriteBeanImp!dao.FavoriteBean")
	private FavoriteBean favoriteBean;
	
	@EJB(mappedName="java:app/property-for-sales/MessageBeanImp!dao.MessageBean")
	private MessageBean msgBean;
	
	private String[] cities={"Bordeaux", "Paris", "Nantes","Rennes","Toulouse","Nice","Lyon"};
	private String[] type={"T1","Flat","T2","T4","Studio"};
	
	@PostConstruct
	public void init(){
		//Create roles
		Role role_user=createRole("ROLE_USER");
		Role role_admin=createRole("ROLE_ADMIN");
		
		//Create profile pictures
		Picture rootPic=createPicture("admin.png");
		Picture user1Pic=createPicture("user1.png");
		Picture user2Pic=createPicture("user2.png");
		
		//Create users
		User root=createUser(role_admin, rootPic, "Root", "Admin", "root@labouardy.com", "rootroot", "AVENUE COLLEGNO BAT A NUM 509", "0664435680");
		User mohamed=createUser(role_user, user1Pic, "Mohamed", "Labouardy", "mohamed@labouardy.com", "labouardy", "AVENUE COLLEGNO BAT A NUM 509", "0664435680");
		User yazid=createUser(role_user, user2Pic, "Yazid", "Hassaine", "yazid@hassaine.com", "hassaine", "AVENUE COLLEGNO BAT G NUM 01", "0661542675");
		User user=createUser(role_user, user2Pic, "User", "Test", "user@user.com", "useruser", "AVENUE COLLEGNO BAT G NUM 01", "0763242675");
		
		//Create adverts and set them to Mohamed
		for(int i=0;i<6;i++){
			createAdvert(mohamed);
			System.out.println("Advert "+i+" created");
		}
		
		//Create adverts and set them to Yazid
		for(int i=0;i<3;i++){
			Advert advert=createAdvert(yazid);
			System.out.println("Advert "+i+" created");
			//Add Ads to Mohamed favorite list
			addToFav(mohamed, advert);
		}
		
		//Send Message to Mohamed
		sendMsg(mohamed);
		
	}
	
	private Role createRole(String name){
		Role role=new Role();
		role.setName(name);
		roleBean.create(role);
		return role;
	}
	
	private Picture createPicture(String path){
		Picture pic=new Picture();
		pic.setLink(path);
		pictureBean.create(pic);
		return pic;
	}
	
	private User createUser(Role role, Picture picture, String firstName, String lastName, String email, String password, String address, String phone){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user=new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setAddress(address);
		user.setPhone(phone);
		user.setRole(role);
		user.setPicture(picture);
		userBean.create(user);
		return user;
	}
	
	private void sendMsg(User receiver){
		List<Advert> adverts=advertBean.getAdverts();
		Message msg=new Message();
		msg.setAdvert(adverts.get(0));
		msg.setEmail("yazid@hassaine.com");
		msg.setMessage("Hello, I'm interested in your offer");
		msg.setName("Hassaine Yazid");
		msg.setPhone("0664435308");
		msgBean.create(msg);
		List<Message> messages=receiver.getMessages();
		if(messages==null)
			messages=new ArrayList<>();
		messages.add(msg);
		receiver.setMessages(messages);
		userBean.update(receiver);
		System.out.println("Message has been sent !");	
	}
	
	private Advert createAdvert(User owner){
		Advert advert=new Advert();
		advert.setLocation(cities[randInt(cities.length-1,0)]);
		advert.setOwner(owner);
		advert.setType(type[randInt(type.length-1,0)]);
		advert.setDescription("Some description goes here, beautiful flat bla bla bla");
		advert.setPrice(randInt(2400,1));
		for(int j=0;j<5;j++){
			String name="advert"+randInt(1, 50)+".jpg";
			Picture pic=new Picture();
			pic.setLink(name);
			pictureBean.create(pic);
			advert.getPictures().add(pic);
			System.out.println("Picture created");
		}
		advert.setSurface(String.valueOf(randInt(450,1)));
		advert.setCreated_at(new Date());
		advertBean.createAdvert(advert);
		return advert;
	}
	
	private void addToFav(User user, Advert advert){
		Favorite favorite=user.getFavorite();
		if(favorite==null){
			favorite=new Favorite();
			List<Advert> adverts=new ArrayList<>();
			adverts.add(advert);
			favorite.setAdverts(adverts);
			favoriteBean.create(favorite);
		}else{
			List<Advert> adverts=favorite.getAdverts();
			if(adverts==null)
				adverts=new ArrayList<>();
			adverts.add(advert);
			favorite.setAdverts(adverts);
			favoriteBean.update(favorite);
		}
		user.setFavorite(favorite);
		userBean.update(user);
	}
	
	private int randInt(int min, int max) {
		int randomNum=min + (int)(Math.random() * ((max - min) + 1));
	    return randomNum;
	}
}
