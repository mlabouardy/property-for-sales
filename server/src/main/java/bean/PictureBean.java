package bean;

import javax.ejb.Local;

import model.Picture;

@Local
public interface PictureBean {

	public void create(Picture picture);
	
}
