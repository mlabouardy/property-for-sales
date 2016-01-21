package api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import dao.AdvertBean;

public class AdvertsTestCase {
	
	private MockMvc mockMvc;
	
	@Mock
	@EJB(mappedName="java:app/property-for-sales/AdvertBeanImp!dao.AdvertBean")
	private AdvertBean advertBean;
	
	@InjectMocks
	private AdvertResource advertCtrl;
	
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
