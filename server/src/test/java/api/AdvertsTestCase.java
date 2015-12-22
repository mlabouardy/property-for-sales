package api;

import javax.ejb.EJB;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import bean.AdvertBean;
import bean.UserBean;
import builder.UserBuilder;
import model.User;

public class AdvertsTestCase {
	
	private MockMvc mockMvc;
	
	@Mock
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!bean.AdvertBean")
	private AdvertBean advertBean;
	
	@InjectMocks
	private AdvertCtrl advertCtrl;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc=MockMvcBuilders.standaloneSetup(advertCtrl).build();
	}
	
	@Test
	public void getAdverts() throws Exception{
		mockMvc.perform(get("/adverts").accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk())
			   .andDo(print());
	}

}
