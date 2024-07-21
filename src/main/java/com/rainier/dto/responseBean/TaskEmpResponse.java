package com.rainier.dto.responseBean;

import com.rainier.beans.EmpTaskResponse;

import java.util.ArrayList;

public class TaskEmpResponse {
	String message;
	boolean status;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	ArrayList<EmpTaskResponse> tsakResponse=new ArrayList<>();

	public ArrayList<EmpTaskResponse> getTsakResponse() {
		return tsakResponse;
	}

	public void setTsakResponse(ArrayList<EmpTaskResponse> tsakResponse) {
		this.tsakResponse = tsakResponse;
	}
	
}
