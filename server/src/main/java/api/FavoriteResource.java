package api;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.AdvertBean;
import dao.FavoriteBean;
import dao.UserBean;
import entity.Advert;
import entity.Favorite;
import entity.User;

@RestController
public class FavoriteResource {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!dao.AdvertBean")
	private AdvertBean advertBean;
	
	@EJB(mappedName="java:app/property-for-sales/FavoriteBeanImp!dao.FavoriteBean")
	private FavoriteBean favoriteBean;
	
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/api/user/favorites/create/{id}", method=RequestMethod.GET, produces="text/plain")
	public ResponseEntity<String> addFavorite(@PathVariable int id){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		Advert advert=advertBean.findById(id);
		if(advert==null){
			return new ResponseEntity<>("Advert not found !",HttpStatus.BAD_REQUEST);
		}
		Favorite favorite=user.getFavorite();
		if(favorite==null){
			favorite=new Favorite();
			List<Advert> adverts=new ArrayList<>();
			adverts.add(advert);
			favorite.setAdverts(adverts);
			favoriteBean.create(favorite);
			System.out.println("list of :"+adverts);
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
		return new ResponseEntity<>("Successfuly added to favorites !",HttpStatus.OK);
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/api/user/favorites", method=RequestMethod.GET, produces="application/json")
	public Favorite getFavorites(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		return user.getFavorite();
	}
	
	
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/api/user/favorites/{id}/delete", method=RequestMethod.GET, produces="text/plain")
	public ResponseEntity<String> deleteFavorite(@PathVariable int id){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		Favorite favorite=user.getFavorite();
		if(favorite!=null){
			List<Advert> adverts=favorite.getAdverts();
			int select=-1;
			int i;
			for(i=0;i<adverts.size();i++){
				if(adverts.get(i).getId()==id){
					select=i;
					break;
				}
			}
			if(select!=-1){
				adverts.remove(i);
				favorite.setAdverts(adverts);
				favoriteBean.update(favorite);
				return new ResponseEntity<>("Successfuly removed !",HttpStatus.OK);
			}else{
				return new ResponseEntity<>("Advert not found !",HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<>("Something went wrong !",HttpStatus.BAD_REQUEST);
		}
	}

}
