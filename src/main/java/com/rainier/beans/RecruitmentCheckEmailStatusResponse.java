package com.rainier.beans;

public class RecruitmentCheckEmailStatusResponse {
	private boolean status;
    private String message;
  private String candidateName;
  
   private Object list;

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String getCandidateName() {
	return candidateName;
}

public void setCandidateName(String candidateName) {
	this.candidateName = candidateName;
}

public Object getList() {
	return list;
}

public void setList(Object list) {
	this.list = list;
}
   

}
