package api;

import java.util.Set;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Role;
import model.User;

@RestController
@RequestMapping("/api")
public class LoginCtrl {
		
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/login", method=RequestMethod.GET, produces="application/json")
	public Role login(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Role role=new Role();
		role.setName(authentication.getAuthorities().iterator().next().toString());
		return role;
	}
}
