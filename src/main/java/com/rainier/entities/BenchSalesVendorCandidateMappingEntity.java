package com.rainier.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "candidate_vendor")
public class BenchSalesVendorCandidateMappingEntity implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "can_id")
	private int canId;
	@Column(name = "vendor_id")
	private int vendorId;
	@Column(name="status")
	private String status;
	@Column(name="statussubmissiondate")
	@Temporal(TemporalType.DATE)
	private Date statussubmissiondate;
    @Column(name = "comments")
    private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatussubmissiondate() {
		return statussubmissiondate;
	}

	public void setStatussubmissiondate(Date statussubmissiondate) {
		this.statussubmissiondate = statussubmissiondate;
	}

	public int getCanId() {
		return canId;
	}

	public void setCanId(int canId) {
		this.canId = canId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
