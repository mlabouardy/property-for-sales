package api;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bean.AdvertBean;
import model.Advert;

@RestController
public class AdvertCtrl {
	
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!bean.AdvertBean")
	private AdvertBean advertBean;
	
	@RequestMapping(value="/adverts", method=RequestMethod.GET, produces="application/json")
	public List<Advert> getTopAdverts(){
		return advertBean.getAdverts();
	}
	
	@RequestMapping(value="/adverts/{id}", method=RequestMethod.GET, produces="application/json")
	public Advert getAdvert(@PathVariable int id){
		return advertBean.findById(id);
	}
	

}
