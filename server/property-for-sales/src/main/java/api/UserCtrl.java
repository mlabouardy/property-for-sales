package api;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bean.RoleBean;
import bean.UserBean;
import model.Role;
import model.User;

@RestController
public class UserCtrl {

	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!bean.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/RoleBeanImp!bean.RoleBean")
	private RoleBean roleBean;
	
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
}
