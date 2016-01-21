package api;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.AdvertBean;
import dao.UserBean;
import entity.Advert;
import entity.User;

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
