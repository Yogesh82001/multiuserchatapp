package com.Panchal.chatapp.dto;
// DTO -> data transfer object 
public class UserDTO {
	private String userid;
	private char[] password;
	public UserDTO(String userid ,char [] password) {
		this.userid=userid;
		this.password=password;// we can extend this feilds 
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}

}
