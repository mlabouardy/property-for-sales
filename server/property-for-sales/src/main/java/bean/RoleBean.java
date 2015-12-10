package bean;

import javax.ejb.Local;

import model.Role;

@Local
public interface RoleBean {

	public void create(Role role);
	
	public Role findByName(String name);
}
