package com.rainier.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rainier.entities.User;

public class BenchSalesAddCandidateBean {
	private int id;
	private int salId;
	private int status;
	private Object status1;
	//private int venCount;
	private String firstName;
	private String emailAddress;
	private String cellPhone;
	private String domain;
	private String totalExperince;
	private String payRate;
	private String immigrationStatus;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private String homePhone;
	private String workPhone;
	private String city;
	private String currentLocation;
	private String locationPreference;
	private String needTraining;
	private String salesPerson;
	private String reLocation;
	private String payType;
	private Date hotListDate;
	private String ssn;
	private String availability;
	private String skypeId;
	private String uploadProfile;
	private String refernce;
	private List<Integer> idList;
	private List<User>recruiters;
	private String recruiterName;
	private String comments;
	private String salName;
	@JsonIgnore
	private boolean deleted;

	
	
	public int getSalId() {
		return salId;
	}
	public void setSalId(int salId) {
		this.salId = salId;
	}
	public String getSalName() {
		return salName;
	}
	public void setSalName(String salName) {
		this.salName = salName;
	}
	public Object getStatus1() {
		return status1;
	}
	public void setStatus1(Object status1) {
		this.status1 = status1;
	}
	public List<User> getRecruiters() {
		return recruiters;
	}
	public void setRecruiters(List<User> recruiters) {
		this.recruiters = recruiters;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	

	/*
	 * public int getVenCount() { return venCount; } public void setVenCount(int
	 * venCount) { this.venCount = venCount; }
	 */
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*
	 * public String getUserId() { return userId; } public void setUserId(String
	 * userId) { this.userId = userId; }
	 */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getTotalExperince() {
		return totalExperince;
	}
	public void setTotalExperince(String totalExperince) {
		this.totalExperince = totalExperince;
	}
	public String getPayRate() {
		return payRate;
	}
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	public String getImmigrationStatus() {
		return immigrationStatus;
	}
	public void setImmigrationStatus(String immigrationStatus) {
		this.immigrationStatus = immigrationStatus;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getLocationPreference() {
		return locationPreference;
	}
	public void setLocationPreference(String locationPreference) {
		this.locationPreference = locationPreference;
	}
	public String getNeedTraining() {
		return needTraining;
	}
	public void setNeedTraining(String needTraining) {
		this.needTraining = needTraining;
	}
	public String getSalesPerson() {
		return salesPerson;
	}
	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	public String getReLocation() {
		return reLocation;
	}
	public void setReLocation(String reLocation) {
		this.reLocation = reLocation;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getHotListDate() {
		return hotListDate;
	}
	public void setHotListDate(Date hotListDate) {
		this.hotListDate = hotListDate;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getSkypeId() {
		return skypeId;
	}
	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}
	public String getUploadProfile() {
		return uploadProfile;
	}
	public void setUploadProfile(String uploadProfile) {
		this.uploadProfile = uploadProfile;
	}
	public String getRefernce() {
		return refernce;
	}
	public void setRefernce(String refernce) {
		this.refernce = refernce;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isdeleted() {
		return deleted;
	}
	public void setdeleted(boolean deleted) {
		this.deleted = deleted;
	}




}
