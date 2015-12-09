package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.User;

@RestController
@RequestMapping("/api")
public class LoginCtrl {

	@RequestMapping(value="/login", method=RequestMethod.GET, produces="application/json")
	public User login(){
		User user=new User();
		return user;
	}
}
