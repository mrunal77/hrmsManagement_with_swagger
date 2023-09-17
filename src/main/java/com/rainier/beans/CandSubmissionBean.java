package com.rainier.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rainier.entities.User;

public class CandSubmissionBean 
{
	private int id;

	private String firstName;

	private String emailAddress;

	private String cellPhone;

	private String domain;

	private String totalExperince;

	private String immigrationStatus;

	private String middleName;

	private String lastName;

	private int count;

	private Date dateOfBirth;

	private String homePhone;

	private String workPhone;

	private String city;

	private String currentLocation;

	private String needTraining;

	private String reLocation;


	private Date hotListDate;

	private String ssn;

	private String availability;


	private String uploadProfile;

	private String refernce;


	private String comments;

	private boolean deleted;

	private int status;


	private List<User> recruiters;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNeedTraining() {
		return needTraining;
	}

	public void setNeedTraining(String needTraining) {
		this.needTraining = needTraining;
	}

	public String getReLocation() {
		return reLocation;
	}

	public void setReLocation(String reLocation) {
		this.reLocation = reLocation;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<User> getRecruiters() {
		return recruiters;
	}

	public void setRecruiters(List<User> recruiters) {
		this.recruiters = recruiters;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
