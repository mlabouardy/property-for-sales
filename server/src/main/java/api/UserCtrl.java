package api;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import bean.CriteriaBean;
import bean.MessageBean;
import bean.PictureBean;
import bean.RoleBean;
import bean.UserBean;
import model.Advert;
import model.Contact;
import model.Criteria;
import model.Message;
import model.Picture;
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
	
	@EJB(mappedName="java:app/property-for-sales/PictureBeanImp!bean.PictureBean")
	private PictureBean pictureBean;
	
	@EJB(mappedName="java:app/property-for-sales/CriteriaBeanImp!bean.CriteriaBean")
	private CriteriaBean criteriaBean;
	
	@EJB(mappedName="java:app/property-for-sales/MessageBeanImp!bean.MessageBean")
	private MessageBean msgBean;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/api/users", method=RequestMethod.GET, produces="application/json")
	public List<User> getUsers(){
		return userBean.getUsers();
	}
	
	@RequestMapping(value="/user/create", method=RequestMethod.POST)
	public void register(@RequestBody final User user){
		Criteria criteria=new Criteria();
		criteriaBean.create(criteria);
		Role role=roleBean.findByName("ROLE_USER");
		user.setRole(role);
		user.setCriteria(criteria);
		userBean.create(user);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/api/profile", method=RequestMethod.GET, produces="application/json")
	public User getProfile(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		return user;
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/api/profile/update", method=RequestMethod.POST)
	public void update(@RequestBody final User user){
		userBean.update(user);
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
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/api/profile/picture/update", method=RequestMethod.POST)
	public void updateProfilePicture(@RequestBody final Picture picture){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		pictureBean.create(picture);
		User user=userBean.findByEmail(email);
		user.setPicture(picture);
		userBean.update(user);
	}
	
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
	
	@RequestMapping(value="/api/user/advert/contact", method=RequestMethod.POST, produces="text/plain")
	public ResponseEntity<String> sendMsg(@RequestBody Contact contact){
		User user=userBean.findById(contact.getIdReceiver());
		if(user==null){
			return new ResponseEntity("User not found",HttpStatus.BAD_REQUEST);
		}else{
			Advert advert=advertBean.findById(contact.getIdAdvert());
			if(advert!=null){
				Message msg=new Message();
				msg.setAdvert(advert);
				msg.setEmail(contact.getEmailSender());
				msg.setMessage(contact.getMsgSender());
				msg.setName(contact.getNameSender());
				msg.setPhone(contact.getPhoneSender());
				msgBean.create(msg);
				user.getMessages().add(msg);
				userBean.update(user);
				return new ResponseEntity("Message successfuly sent !",HttpStatus.OK); 
			}
			return new ResponseEntity("Advert not found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/api/user/messages", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Message>> getMessages(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		if(email.compareTo("anonymousUser")==0){
			return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
		}else{
			User user=userBean.findByEmail(email);
			return new ResponseEntity(user.getMessages(), HttpStatus.OK);
		}
		
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping(value="/api/user/messages/{id}/delete", method=RequestMethod.GET, produces="text/plain")
	public ResponseEntity<String> deleteMessage(@PathVariable int id){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		List<Message> messages=user.getMessages();
		int toremove=-1;
		int i=0;
		for(Message msg:messages){
			if(msg.getId()==id){
				toremove=i;
				break;
			}
			i++;
		}
		if(toremove==-1){
			return new ResponseEntity<>("Message doesnt found !",HttpStatus.BAD_REQUEST);
		}
		messages.remove(toremove);
		user.setMessages(messages);
		userBean.update(user);
		msgBean.delete(id);
		return new ResponseEntity<>("Message successfuly deleted !",HttpStatus.OK);
	}
}
