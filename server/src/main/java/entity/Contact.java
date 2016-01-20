package entity;

public class Contact {
	
	private int idReceiver;
	private int idAdvert;
	private String nameSender;
	private String emailSender;
	private String phoneSender;
	private String msgSender;
	
	public Contact(){}
	
	public int getIdReceiver() {
		return idReceiver;
	}
	public void setIdReceiver(int idReceiver) {
		this.idReceiver = idReceiver;
	}
	public int getIdAdvert() {
		return idAdvert;
	}
	public void setIdAdver(int idAdver) {
		this.idAdvert = idAdver;
	}
	public String getNameSender() {
		return nameSender;
	}
	public void setNameSender(String nameSender) {
		this.nameSender = nameSender;
	}
	public String getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}
	public String getPhoneSender() {
		return phoneSender;
	}
	public void setPhoneSender(String phoneSender) {
		this.phoneSender = phoneSender;
	}
	public String getMsgSender() {
		return msgSender;
	}
	public void setMsgSender(String msgSender) {
		this.msgSender = msgSender;
	}
	
	

}
