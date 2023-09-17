package com.rainier.dto.requestBean;

public class ForgetPasswordRequest {
private String emailAddress;
private String forgetPassWordUrl;
public String getEmailAddress() {
	return emailAddress;
}
public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}
public String getForgetPassWordUrl() {
	return forgetPassWordUrl;
}
public void setForgetPassWordUrl(String forgetPassWordUrl) {
	this.forgetPassWordUrl = forgetPassWordUrl;
}

}
