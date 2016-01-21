package api;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.CriteriaBean;
import dao.RoleBean;
import dao.UserBean;
import entity.Criteria;
import entity.Role;
import entity.User;
import service.EmailService;

@RestController
public class AuthenticationResource {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/CriteriaBeanImp!dao.CriteriaBean")
	private CriteriaBean criteriaBean;
	
	@EJB(mappedName="java:app/property-for-sales/RoleBeanImp!dao.RoleBean")
	private RoleBean roleBean;
	
	@Autowired
	private EmailService emailService;
		
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/api/login", method=RequestMethod.GET, produces="application/json")
	public Role login(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Role role=new Role();
		role.setName(authentication.getAuthorities().iterator().next().toString());
		return role;
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.POST)
	public void register(@RequestBody final User user){
		Criteria criteria=new Criteria();
		criteriaBean.create(criteria);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Role role=roleBean.findByName("ROLE_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(role);
		user.setCriteria(criteria);
		userBean.create(user);
	}
	
	@RequestMapping(value="/isconnected", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Role> isConnected(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		Role role=new Role();
		role.setName(authentication.getAuthorities().iterator().next().toString());
		System.out.println("now is connected: "+user+" "+email+" "+role);
		if(user==null)
			return new ResponseEntity(role, HttpStatus.FORBIDDEN);
		return new ResponseEntity(role, HttpStatus.ACCEPTED);
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
