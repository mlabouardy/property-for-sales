package api;

import java.util.Set;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bean.UserBean;
import model.Role;
import model.User;
import service.EmailService;

@RestController
public class LoginCtrl {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!bean.UserBean")
	private UserBean userBean;
	
	@Autowired
	private EmailService emailService;
		
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/api/login", method=RequestMethod.GET, produces="application/json")
	public Role login(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Role role=new Role();
		role.setName(authentication.getAuthorities().iterator().next().toString());
		System.out.println("Login");
		return role;
	}
	
	/*
	 * It works fine, you need just authorization from Gmail
	 */
	@RequestMapping(value="/forgotpassword", method=RequestMethod.POST, produces="text/plain")
	public ResponseEntity<String> forgotPassword(@RequestBody String email){
		System.out.println("Email:"+email);
		User user=userBean.findByEmail(email);
		if(user!=null){
			//emailService.send(email, "mohamed.labouardy@gmail.com", "Property for Sales Password", "Your password is :"+user.getPassword());
			return new ResponseEntity("Password sent to email",HttpStatus.ACCEPTED);
		}
		else
			return new ResponseEntity("Email doesnt match to any existing user",HttpStatus.BAD_REQUEST);
	}
}
