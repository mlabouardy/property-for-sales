package dao;

import java.util.List;

import javax.ejb.Local;

import entity.User;

@Local
public interface UserBean {

	public void create(User user);
	
	public void deleteById(int id);
	
	public User findById(int id);
	
	public List<User> getUsers();

	public User getUserById(int id);

	public User findByEmail(String email);

	public void update(User user);

	public void delete(User user);
	

}
