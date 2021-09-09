package com.carlease.service.carleaseservice.model;
/**
 * Bean class for user authentocation fields
 * @author Shruthi
 *
 */
public class AuthenticateRequest {
    private  String userName;
    private String password;
    
	public AuthenticateRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
