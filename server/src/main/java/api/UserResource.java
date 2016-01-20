package api;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dao.AdvertBean;
import dao.CriteriaBean;
import dao.FavoriteBean;
import dao.MessageBean;
import dao.PictureBean;
import dao.RoleBean;
import dao.UserBean;
import entity.Advert;
import entity.Contact;
import entity.Criteria;
import entity.Favorite;
import entity.Message;
import entity.Picture;
import entity.Role;
import entity.User;
import service.EmailService;

@RestController
public class UserResource {

	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!dao.AdvertBean")
	private AdvertBean advertBean;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/api/users", method=RequestMethod.GET, produces="application/json")
	public List<User> getUsers(){
		return userBean.getUsers();
	}
	
	@Secured("ROLE_ADMIN")
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
