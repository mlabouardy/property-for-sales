package bean;

import javax.ejb.Local;

import model.Message;

@Local
public interface MessageBean {

	public void create(Message msg);

	public void delete(int id);
	
}
