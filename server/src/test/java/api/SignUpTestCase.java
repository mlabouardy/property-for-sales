package api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ejb.EJB;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import builder.UserBuilder;
import dao.CriteriaBean;
import dao.RoleBean;
import dao.UserBean;
import entity.User;

public class SignUpTestCase {
private MockMvc mockMvc;
	
	@Mock
	@EJB(mappedName="java:app/property-for-sales/UserBeanImp!dao.UserBean")
	private UserBean userBean;
	
	@Mock
	@EJB(mappedName="java:app/property-for-sales/CriteriaBeanImp!dao.CriteriaBean")
	private CriteriaBean criteriaBean;
	
	@Mock
	@EJB(mappedName="java:app/property-for-sales/RoleBeanImp!dao.RoleBean")
	private RoleBean roleBean;
	
	@InjectMocks
	private UserResource userCtrl;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc=MockMvcBuilders.standaloneSetup(userCtrl).build();
	}
	
	@Test
	public void register() throws Exception{
		User user=new UserBuilder()
				      .firstName("test")
				      .lastName("test")
				      .email("test@labouardy.com")
				      .password("testtet")
				      .build();
		
		ObjectMapper mapper = new ObjectMapper();
		String info=mapper.writeValueAsString(user);
		
		mockMvc.perform(post("/user/create")
						.content(info)
						.contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andDo(print());
	}
}
