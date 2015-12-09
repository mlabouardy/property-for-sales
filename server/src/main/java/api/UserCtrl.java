package api;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bean.UserBean;
import model.User;

@RestController
@RequestMapping("/api")
public class UserCtrl {

	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!bean.UserBean")
	private UserBean userBean;
	
	@RequestMapping(value="/users", method=RequestMethod.GET, produces="application/json")
	public List<User> getUsers(){
		return userBean.getUsers();
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.ACCEPTED)
	public void register(@RequestBody final User user){
		userBean.create(user);
	}
}
