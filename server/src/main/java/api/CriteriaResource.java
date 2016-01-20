package api;

import javax.ejb.EJB;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.CriteriaBean;
import dao.UserBean;
import entity.Criteria;
import entity.User;

@RestController
public class CriteriaResource {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/CriteriaBeanImp!dao.CriteriaBean")
	private CriteriaBean criteriaBean;
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/api/criteria/update", method=RequestMethod.POST)
	public void updateCriteria(@RequestBody final Criteria criteria){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		criteriaBean.create(criteria);
		User user=userBean.findByEmail(email);
		user.setCriteria(criteria);
		userBean.update(user);
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/api/criteria", method=RequestMethod.GET, produces="application/json")
	public Criteria getCriteria(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		return user.getCriteria();
	}

}
