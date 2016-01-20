package api;

import javax.ejb.EJB;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.PictureBean;
import dao.UserBean;
import entity.Picture;
import entity.User;

@RestController
public class ProfileResource {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/PictureBeanImp!dao.PictureBean")
	private PictureBean pictureBean;
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/api/profile", method=RequestMethod.GET, produces="application/json")
	public User getProfile(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		return user;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/api/profile/update", method=RequestMethod.POST)
	public void update(@RequestBody final User user){
		userBean.update(user);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/api/profile/picture/update", method=RequestMethod.POST)
	public void updateProfilePicture(@RequestBody final Picture picture){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		pictureBean.create(picture);
		User user=userBean.findByEmail(email);
		user.setPicture(picture);
		userBean.update(user);
	}
	

}
