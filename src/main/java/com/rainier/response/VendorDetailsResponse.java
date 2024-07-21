package com.rainier.response;

import com.rainier.dto.responseBean.BenchSalesVendorDetailsEntityResponse;

import java.util.ArrayList;
import java.util.List;

public class VendorDetailsResponse 
{
	private boolean status;
	private String message;
	private Object privillegesList;

	List<BenchSalesVendorDetailsEntityResponse> vendorDetailsEntities = new ArrayList<BenchSalesVendorDetailsEntityResponse>();

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

	public Object getPrivillegesList() {
		return privillegesList;
	}

	public void setPrivillegesList(Object privillegesList) {
		this.privillegesList = privillegesList;
	}

	public List<BenchSalesVendorDetailsEntityResponse> getVendorDetailsEntities() {
		return vendorDetailsEntities;
	}

	public void setVendorDetailsEntities(List<BenchSalesVendorDetailsEntityResponse> vendorDetailsEntities) {
		this.vendorDetailsEntities = vendorDetailsEntities;
	}
	

}
