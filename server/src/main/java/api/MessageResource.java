package api;

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
import org.springframework.web.bind.annotation.RestController;

import dao.AdvertBean;
import dao.MessageBean;
import dao.UserBean;
import entity.Advert;
import entity.Contact;
import entity.Message;
import entity.User;
@RestController
public class MessageResource {
	
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@EJB(mappedName="java:app/property-for-sales/MessageBeanImp!dao.MessageBean")
	private MessageBean msgBean;
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!dao.AdvertBean")
	private AdvertBean advertBean;
	
	
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
	@RequestMapping(value="/api/user/messages/count", method=RequestMethod.GET, produces="application/json")
	public Integer countMessages(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=userBean.findByEmail(email);
		if(user==null)
			return -1;
		return user.getMessages().size();
	}

}
