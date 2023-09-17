package com.rainier.entities;

import javax.persistence.*;

@Entity
@Table(name = "rs_bgProfessionalReference")
public class BgEmpProfessionalReferneceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "referredByName")
	private String referredByName;
	@Column(name = "referredByEmail")
	private String referredByEmail;
	@Column(name = "referredByOccupation")
	private String referredByOccupation;
	@Column(name = "referredByPhone")
	private String referredByPhone;
	@Column(name = "referredByState")
	private String referredByState;
	@Column(name = "referredByCity")
	private String referredByCity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReferredByName() {
		return referredByName;
	}

	public void setReferredByName(String referredByName) {
		this.referredByName = referredByName;
	}

	public String getReferredByEmail() {
		return referredByEmail;
	}

	public void setReferredByEmail(String referredByEmail) {
		this.referredByEmail = referredByEmail;
	}

	public String getReferredByOccupation() {
		return referredByOccupation;
	}

	public void setReferredByOccupation(String referredByOccupation) {
		this.referredByOccupation = referredByOccupation;
	}

	public String getReferredByPhone() {
		return referredByPhone;
	}

	public void setReferredByPhone(String referredByPhone) {
		this.referredByPhone = referredByPhone;
	}

	public String getReferredByState() {
		return referredByState;
	}

	public void setReferredByState(String referredByState) {
		this.referredByState = referredByState;
	}

	public String getReferredByCity() {
		return referredByCity;
	}

	public void setReferredByCity(String referredByCity) {
		this.referredByCity = referredByCity;
	}

}
