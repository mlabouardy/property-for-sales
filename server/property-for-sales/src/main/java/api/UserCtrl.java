package api;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bean.AdvertBean;
import bean.PictureBean;
import bean.RoleBean;
import bean.UserBean;
import model.Advert;
import model.Role;
import model.User;

@RestController
public class UserCtrl {

	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!bean.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/RoleBeanImp!bean.RoleBean")
	private RoleBean roleBean;
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!bean.AdvertBean")
	private AdvertBean advertBean;
	
	
	@RequestMapping(value="/api/users", method=RequestMethod.GET, produces="application/json")
	public List<User> getUsers(){
		return userBean.getUsers();
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.POST)
	public void register(@RequestBody final User user){
		Role role=roleBean.findByName("ROLE_USER");
		user.setRole(role);
		userBean.create(user);
	}
	
	@RequestMapping(value="/api/profile", method=RequestMethod.GET, produces="application/json")
	public User getProfile(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		return user;
	}
	
	@RequestMapping(value="/api/profile/update", method=RequestMethod.POST)
	public void update(@RequestBody final User user){
		userBean.update(user);
	}
	
	@RequestMapping(value="/api/user/{id}/delete", method=RequestMethod.GET, produces="application/json")
	public void deleteUser(@PathVariable int id){
		User user=userBean.findById(id);
		List<Advert> adverts=advertBean.getAdvertsByUserId(user.getId());
		System.out.println(adverts);
		for(Advert a: adverts)
			advertBean.remove(a);
		userBean.delete(user);
	}
}
