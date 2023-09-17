package com.rainier.dto.requestBean;

public class PassWordUpadateRequest {
private String email;
private String newPassWord;
private String confirmPassword;


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getNewPassWord() {
	return newPassWord;
}
public void setNewPassWord(String newPassWord) {
	this.newPassWord = newPassWord;
}
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}

}
