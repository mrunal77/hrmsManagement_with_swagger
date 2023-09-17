package com.rainier.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table()
public class BenchSalesAddCandidateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column
	private String firstName;
	@Column
	private String emailAddress;
	@Column
	private String cellPhone;
	@Column
	private String domain;
	@Column
	private String middleName;
	@Column
	private String lastName;
	@Temporal(TemporalType.DATE)
	@Column
	private Date dateOfBirth;
	@Column
	private String homePhone;
	@Column
	private String workPhone;
	@Column
	private String city;
	@Column
	private String currentLocation;
	@Column
	private String reLocation;
	@Temporal(TemporalType.DATE)
	@Column
	private Date hotListDate;
	@Column
	private String ssn;
	@Column
	private String availability;

	@Column
	private String uploadProfile;
	@Column
	private String refernce;

	@Column
	private String comments;
	@JsonIgnore
	@Column
	private boolean deleted;
	
    
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "candidate_sales_executive", joinColumns = {
			@JoinColumn(name = "can_id", referencedColumnName = "id",unique =false,nullable = true,insertable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "rec_id", referencedColumnName = "id",unique =false,nullable = true,insertable = true) })
	private List<User> recruiters;

	
	
	

	public List<User> getRecruiters() {
		return recruiters;
	}

	public void setRecruiters(List<User> usersMinilist) {
		this.recruiters = usersMinilist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

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

	public boolean isdeleted() {
		return deleted;
	}

	public void setdeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
