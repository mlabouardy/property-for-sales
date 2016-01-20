package dao;

import javax.ejb.Local;

import entity.Role;

@Local
public interface RoleBean {

	public void create(Role role);
	
	public Role findByName(String name);
}
