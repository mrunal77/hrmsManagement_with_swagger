package com.rainier.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.id.Assigned;

@Entity
@Table(name = "candidate_sales_executive")
public class CandidateSalesExecutiveEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "can_id")
	private int canId;
	@Column(name = "rec_id")
	private int recId;
	@Column
	private int reqReqId;
	@Column
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCanId() {
		return canId;
	}

	public void setCanId(int canId) {
		this.canId = canId;
	}

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public int getReqReqId() {
		return reqReqId;
	}

	public void setReqReqId(int reqReqId) {
		this.reqReqId = reqReqId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
