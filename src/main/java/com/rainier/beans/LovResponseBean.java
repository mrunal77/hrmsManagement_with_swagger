package com.rainier.beans;

import java.util.List;
import java.util.Set;

import com.rainier.dto.responseBean.CandidateRecruitersBenchSalesResponse;
import com.rainier.dto.responseBean.VendorSalesExecutiveResponseBean;
import com.rainier.entities.BenchSalesVendorDetailsEntity;

public class LovResponseBean {
	private boolean status;
	private String message;
	private Object list;
	private BenchSalesVendorDetailsEntity vendorEntity;
	private Object privillegeslist;
	private Set<CandidateRecruitersBenchSalesResponse>canList=null;
	List<VendorSalesExecutiveResponseBean> venExeList=null;
	
	

	public List<VendorSalesExecutiveResponseBean> getVenExeList() {
		return venExeList;
	}

	public void setVenExeList(List<VendorSalesExecutiveResponseBean> venExeList) {
		this.venExeList = venExeList;
	}

	public Set<CandidateRecruitersBenchSalesResponse> getCanList() {
		return canList;
	}

	public void setCanList(Set<CandidateRecruitersBenchSalesResponse> canList) {
		this.canList = canList;
	}

	public Object getPrivillegeslist() {
		return privillegeslist;
	}

	public void setPrivillegeslist(Object privillegeslist) {
		this.privillegeslist = privillegeslist;
	}

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

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	public BenchSalesVendorDetailsEntity getVendorEntity() {
		return vendorEntity;
	}

	public void setVendorEntity(BenchSalesVendorDetailsEntity vendorEntity) {
		this.vendorEntity = vendorEntity;
	}

}
