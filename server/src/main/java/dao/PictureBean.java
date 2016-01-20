package dao;

import javax.ejb.Local;

import entity.Picture;

@Local
public interface PictureBean {

	public void create(Picture picture);
	
}
