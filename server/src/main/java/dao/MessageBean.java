package dao;

import javax.ejb.Local;

import entity.Message;

@Local
public interface MessageBean {

	public void create(Message msg);

	public void delete(int id);
	
}
