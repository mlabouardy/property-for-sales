package builder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entity.User;

public class UserBuilder {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address;
	private String phone;
	
	public UserBuilder id(int id){
		this.id=id;
		return this;
	}
	
	public UserBuilder firstName(String firstName){
		this.firstName=firstName;
		return this;
	}
	
	public UserBuilder lastName(String lastName){
		this.lastName=lastName;
		return this;
	}
	
	public UserBuilder email(String email){
		this.email=email;
		return this;
	}
	
	public UserBuilder password(String password){
		this.password=password;
		return this;
	}
	
	public User build(){
		User user=new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return user;
	}
}
