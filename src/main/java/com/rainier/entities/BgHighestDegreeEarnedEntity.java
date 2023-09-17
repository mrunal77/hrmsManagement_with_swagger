package com.rainier.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "rs_bgHighestDegreeEarned")
public class BgHighestDegreeEarnedEntity {

	@Id
	// @GeneratedValue(strategy =
	// GenerationType.IDENTITY)(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "master_degree")
	private String masterDegree;

	@Column(name = "bachlors_degree")
	private String bachlorsDegree;

	@Column(name = "diploma_Hssc")
	private String diploma_HsscDegree;

	@Column(name = "ssc")
	private String sscDegree;

	@Column(name = "other")
	private String other;

	@Column(name = "masterDegreeInstituteName")
	private String masterDegreeInstituteName;

	@Column(name = "bachlorsDegreeInstituteName")
	private String bachlorsDegreeInstituteName;

	@Column(name = "diplomaHsscDegreeName")
	private String diploma_HsscDegreeInstituteName;

	@Column(name = "sscDegreeName")
	private String sscDegreeInstituteName;

	@Column(name = "otherDegreeName")
	private String otherDegreeInstituteName;

	@Column(name = "masterDegreeYOP")
	private Date masterDegreeYOP;

	@Column(name = "bachlorsDegreeYOP")
	private Date bachlorsDegreeYOP;

	@Column(name = "diploma_HsscDegreeYOP")
	private Date diploma_HsscDegreeYOP;

	@Column(name = "sscDegreeYOP")
	private Date sscDegreeYOP;

	@Column(name = "otherDegreeYOP")
	private Date otherDegreeYOP;

	@Column(name = "masterDegreeNOY")
	private String masterDegreeNOY;

	@Column(name = "bachlorsDegreeNOY")
	private String bachlorsDegreeNOY;

	@Column(name = "diploma_hsscDegreeNOY")
	private String diploma_HsscDegreeNOY;

	@Column(name = "otherDegreeNOY")
	private String otherDegreeNOY;

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

	public String getMasterDegree() {
		return masterDegree;
	}

	public void setMasterDegree(String masterDegree) {
		this.masterDegree = masterDegree;
	}

	public String getBachlorsDegree() {
		return bachlorsDegree;
	}

	public void setBachlorsDegree(String bachlorsDegree) {
		this.bachlorsDegree = bachlorsDegree;
	}

	public String getDiploma_HsscDegree() {
		return diploma_HsscDegree;
	}

	public void setDiploma_HsscDegree(String diploma_HsscDegree) {
		this.diploma_HsscDegree = diploma_HsscDegree;
	}

	public String getSscDegree() {
		return sscDegree;
	}

	public void setSscDegree(String sscDegree) {
		this.sscDegree = sscDegree;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getMasterDegreeInstituteName() {
		return masterDegreeInstituteName;
	}

	public void setMasterDegreeInstituteName(String masterDegreeInstituteName) {
		this.masterDegreeInstituteName = masterDegreeInstituteName;
	}

	public String getBachlorsDegreeInstituteName() {
		return bachlorsDegreeInstituteName;
	}

	public void setBachlorsDegreeInstituteName(String bachlorsDegreeInstituteName) {
		this.bachlorsDegreeInstituteName = bachlorsDegreeInstituteName;
	}

	public String getDiploma_HsscDegreeInstituteName() {
		return diploma_HsscDegreeInstituteName;
	}

	public void setDiploma_HsscDegreeInstituteName(String diploma_HsscDegreeInstituteName) {
		this.diploma_HsscDegreeInstituteName = diploma_HsscDegreeInstituteName;
	}

	public String getSscDegreeInstituteName() {
		return sscDegreeInstituteName;
	}

	public void setSscDegreeInstituteName(String sscDegreeInstituteName) {
		this.sscDegreeInstituteName = sscDegreeInstituteName;
	}

	public String getOtherDegreeInstituteName() {
		return otherDegreeInstituteName;
	}

	public void setOtherDegreeInstituteName(String otherDegreeInstituteName) {
		this.otherDegreeInstituteName = otherDegreeInstituteName;
	}

	public Date getMasterDegreeYOP() {
		return masterDegreeYOP;
	}

	public void setMasterDegreeYOP(Date masterDegreeYOP) {
		this.masterDegreeYOP = masterDegreeYOP;
	}

	public Date getBachlorsDegreeYOP() {
		return bachlorsDegreeYOP;
	}

	public void setBachlorsDegreeYOP(Date bachlorsDegreeYOP) {
		this.bachlorsDegreeYOP = bachlorsDegreeYOP;
	}

	public Date getDiploma_HsscDegreeYOP() {
		return diploma_HsscDegreeYOP;
	}

	public void setDiploma_HsscDegreeYOP(Date diploma_HsscDegreeYOP) {
		this.diploma_HsscDegreeYOP = diploma_HsscDegreeYOP;
	}

	public Date getSscDegreeYOP() {
		return sscDegreeYOP;
	}

	public void setSscDegreeYOP(Date sscDegreeYOP) {
		this.sscDegreeYOP = sscDegreeYOP;
	}

	public Date getOtherDegreeYOP() {
		return otherDegreeYOP;
	}

	public void setOtherDegreeYOP(Date otherDegreeYOP) {
		this.otherDegreeYOP = otherDegreeYOP;
	}

	public String getMasterDegreeNOY() {
		return masterDegreeNOY;
	}

	public void setMasterDegreeNOY(String masterDegreeNOY) {
		this.masterDegreeNOY = masterDegreeNOY;
	}

	public String getBachlorsDegreeNOY() {
		return bachlorsDegreeNOY;
	}

	public void setBachlorsDegreeNOY(String bachlorsDegreeNOY) {
		this.bachlorsDegreeNOY = bachlorsDegreeNOY;
	}

	public String getDiploma_HsscDegreeNOY() {
		return diploma_HsscDegreeNOY;
	}

	public void setDiploma_HsscDegreeNOY(String diploma_HsscDegreeNOY) {
		this.diploma_HsscDegreeNOY = diploma_HsscDegreeNOY;
	}

	public String getOtherDegreeNOY() {
		return otherDegreeNOY;
	}

	public void setOtherDegreeNOY(String otherDegreeNOY) {
		this.otherDegreeNOY = otherDegreeNOY;
	}

	
	
	
}
