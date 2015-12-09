package bean;

import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface UserBean {

	public void create(User user);
	
	public void deleteById(int id);
	
	public User findById(int id);
	
	public List<User> getUsers();
}
