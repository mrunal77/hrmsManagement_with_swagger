package com.rainier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "vendor_details")
public class BenchSalesVendorDetailsEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id")
	private int vendorId;
	@Column(name = "vendor_name")
	private String vendorName;
	@Column(name = "email_address")
	private String emailAddress;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "client_name")
	private String clientName;
	@Column(name="location")
	private String location;
	@Basic
	private String rate;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_submission")
	private Date dateOfSubmission;
	@Column(name = "created_by")
	private int createdBy;
//	/*
//	 * @Column(name = "status") private String status;
//	 * 
//	 * @Temporal(TemporalType.DATE)
//	 * 
//	 * @Column(name = "statussubmissiondate") private Date statusSubDate;
//	 */
	/*
	 * @Column(name = "comments") private String comments;
	 */
	@Column(name="pay_type")
	private String payType;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "candidate_vendor", joinColumns = { @JoinColumn(name = "vendor_id",unique =false,nullable = true,insertable = true) }, inverseJoinColumns = {
			@JoinColumn(name = "can_id",referencedColumnName = "id",unique =false,nullable = true,insertable = true) })
	private Set<BenchSalesAddCandidateEntity> candidates;

	/*
	 * public String getStatus() { return status; }
	 * 
	 * public void setStatus(String status) { this.status = status; }
	 * 
	 * public Date getStatusSubDate() { return statusSubDate; }
	 * 
	 * public void setStatusSubDate(Date statusSubDate) { this.statusSubDate =
	 * statusSubDate; }
	 */
	/*
	 * public String getComments() { return comments; }
	 * 
	 * public void setComments(String comments) { this.comments = comments; }
	 */

	public BenchSalesVendorDetailsEntity() {
		candidates = new HashSet<>();
	}

	public Set<BenchSalesAddCandidateEntity> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<BenchSalesAddCandidateEntity> candidates) {
		this.candidates = candidates;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Date getDateOfSubmission() {
		return dateOfSubmission;
	}

	public void setDateOfSubmission(Date dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	
}
