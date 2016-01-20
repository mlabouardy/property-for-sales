package api;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.AdvertBean;
import dao.PictureBean;
import dao.UserBean;
import entity.Advert;
import entity.Picture;
import entity.User;

@RestController
public class AdvertResource {
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!dao.AdvertBean")
	private AdvertBean advertBean;
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/PictureBeanImp!dao.PictureBean")
	private PictureBean pictureBean;
	
	@RequestMapping(value="/adverts", method=RequestMethod.GET, produces="application/json")
	public List<Advert> getTopAdverts(){
		return advertBean.getAdverts();
	}
	
	@RequestMapping(value="/adverts/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Advert> getAdvert(@PathVariable int id){
		Advert advert=advertBean.findById(id);
		if(advert!=null){
			return new ResponseEntity(advert,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/user/{id}/adverts", method=RequestMethod.GET, produces="application/json")
	public List<Advert> getUserAdverts(@PathVariable int id){
		return advertBean.getAdvertsByUserId(id);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/api/user/adverts", method=RequestMethod.GET, produces="application/json")
	public List<Advert> getAdvertsOfUser(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		return advertBean.getAdvertsByUserId(user.getId());
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/api/user/adverts/{id}/delete", method=RequestMethod.GET, produces="application/json")
	public void deleteAdvert(@PathVariable int id){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		
		List<Advert> adverts=advertBean.getAdvertsByUserId(user.getId());
		Advert advert=null;
		for(Advert a:adverts){
			if(a.getId()==id){
				advert=a;
				break;
			}
		}
		advertBean.remove(advert);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/api/user/{id_user}/adverts/{id_advert}/delete", method=RequestMethod.GET, produces="application/json")
	public void adminDeleteAdvert(@PathVariable int id_user, @PathVariable int id_advert){
		User user=userBean.findById(id_user);
		List<Advert> adverts=advertBean.getAdvertsByUserId(user.getId());
		Advert advert=null;
		for(Advert a:adverts){
			if(a.getId()==id_advert){
				advert=a;
				break;
			}
		}
		advertBean.remove(advert);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/api/adverts/create", method=RequestMethod.POST)
	public void createAdvert(@RequestBody final Advert advert){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		
		List<Picture> pictures=advert.getPictures();
		for(Picture picture:pictures)
			pictureBean.create(picture);
		
		User user=userBean.findByEmail(email);
		advert.setOwner(user);
		advertBean.createAdvert(advert);
	}
	
	@RequestMapping(value="/recents", method=RequestMethod.GET, produces="application/json")
	public List<Advert> mostRecents(){
		return advertBean.getMostRecents();
	}

}
