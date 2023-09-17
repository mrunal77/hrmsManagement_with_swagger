package com.rainier.beans;

import java.sql.Date;
import java.util.List;

public class BgHighestDegreeEarnedBean {
	
	private int id;
	private int userId;
	private String masterDegree;
	private String bachlorsDegree;
	private String diploma_HsscDegree;
	private String sscDegree;
	
	private String masterDegreeInstituteName;
	private String bachlorsDegreeInstituteName;
	private String diploma_HsscDegreeInstituteName;
	private String sscDegreeInstituteName;
	
	private Date  masterDegreeYOP;
	private Date  bachlorsDegreeYOP;
	private Date  diploma_HsscDegreeYOP;
	private Date  sscDegreeYOP;
	private Date otherDegreeYOP;

	
	
	private String  masterDegreeNOY;
	private String  bachlorsDegreeNOY;
	private String diploma_HsscDegreeNOY;
	
	private List<OtherHighestDegree> otherHighestDegreeName;

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

	public List<OtherHighestDegree> getOtherHighestDegreeName() {
		return otherHighestDegreeName;
	}

	public void setOtherHighestDegreeName(List<OtherHighestDegree> otherHighestDegreeName) {
		this.otherHighestDegreeName = otherHighestDegreeName;
	}

	
	
	
	
	
	
	
	
}
