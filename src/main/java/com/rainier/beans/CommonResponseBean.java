package com.rainier.beans;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.rainier.dto.responseBean.CandidateResponseObject;
import com.rainier.dto.responseBean.CityStateResponseBean;
import com.rainier.entities.BenchSalesAddCandidateEntity;
import com.rainier.entities.BenchSalesVendorDetailsEntity;
import com.rainier.entities.Tbl_StatesEntity;

public class CommonResponseBean {
	private boolean status;
	private String message;
	private Object privillegesList;
	private Object list;
	private List<BenchSalesVendorDetailsBean> lists;
	private Set<BenchSalesVendorDetailsBean> setOfVendor;
	private List<BenchSalesAddCandidateEntity> lists1;
	private List<BenchSalesVendorDetailsEntity> lists2;
	private List<ArrayList<BigInteger>> list3;
	private List<CandidateResponseObject> majorList = new ArrayList<>();
	private List<CityStateResponseBean>stateList;
	

	public List<CityStateResponseBean> getStateList() {
		return stateList;
	}

	public void setStateList(List<CityStateResponseBean> stateList) {
		this.stateList = stateList;
	}

	public Set<BenchSalesVendorDetailsBean> getSetOfVendor() {
		return setOfVendor;
	}

	public void setSetOfVendor(Set<BenchSalesVendorDetailsBean> setOfVendor) {
		this.setOfVendor = setOfVendor;
	}

	public List<BenchSalesVendorDetailsEntity> getLists2() {
		return lists2;
	}

	public void setLists2(List<BenchSalesVendorDetailsEntity> lists2) {
		this.lists2 = lists2;
	}

	public List<BenchSalesAddCandidateEntity> getLists1() {
		return lists1;
	}

	public void setLists1(List<BenchSalesAddCandidateEntity> lists1) {
		this.lists1 = lists1;
	}

	public List<ArrayList<BigInteger>> getList3() {
		return list3;
	}

	public void setList3(List<ArrayList<BigInteger>> list3) {
		this.list3 = list3;
	}

	public List<CandidateResponseObject> getMajorList() {
		return majorList;
	}

	public void setMajorList(List<CandidateResponseObject> majorList) {
		this.majorList = majorList;
	}

	public List<BenchSalesVendorDetailsBean> getLists() {
		return lists;
	}

	public void setLists(List<BenchSalesVendorDetailsBean> lists) {
		this.lists = lists;
	}

	public Object getPrivillegesList() {
		return privillegesList;
	}

	public void setPrivillegesList(Object privillegesList) {
		this.privillegesList = privillegesList;
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

}
