package com.rainier.beans;

import java.sql.Timestamp;

public class MedicalClaimsInjuryBean {
	
	private int id;
	private int userId;
	private int medicalClaimType;
	private Timestamp injuryDate;
	private String injuryName;
	private String disabilityType;
	private int severityType;
	private String injuryDescription;
	private String medicalInsurerName;
	private Timestamp joiningDate;
	private Timestamp appliedleaveStartDate;
	private Timestamp appliedleaveEndDate;
	private float noOfDaysApplied;
	
	private String hospitalName;
	private String hospitalAddress;
	private String hospitalRoomNo;
	private String nameOFConcernedGP;
	private String treatmentDetails;
	private double totalCost;
	private double amountClaimed;
	private double amountApproved;
	
	
	private Timestamp approvedleaveStartDate;
	private Timestamp approvedleaveEndDate;
	private int noOfDaysApproved;
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
	public int getMedicalClaimType() {
		return medicalClaimType;
	}
	public void setMedicalClaimType(int medicalClaimType) {
		this.medicalClaimType = medicalClaimType;
	}
	public Timestamp getInjuryDate() {
		return injuryDate;
	}
	public void setInjuryDate(Timestamp injuryDate) {
		this.injuryDate = injuryDate;
	}
	public String getInjuryName() {
		return injuryName;
	}
	public void setInjuryName(String injuryName) {
		this.injuryName = injuryName;
	}
	
	public int getSeverityType() {
		return severityType;
	}
	public void setSeverityType(int severityType) {
		this.severityType = severityType;
	}
	public String getInjuryDescription() {
		return injuryDescription;
	}
	public void setInjuryDescription(String injuryDescription) {
		this.injuryDescription = injuryDescription;
	}
	public String getMedicalInsurerName() {
		return medicalInsurerName;
	}
	public void setMedicalInsurerName(String medicalInsurerName) {
		this.medicalInsurerName = medicalInsurerName;
	}
	public Timestamp getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Timestamp getAppliedleaveStartDate() {
		return appliedleaveStartDate;
	}
	public void setAppliedleaveStartDate(Timestamp appliedleaveStartDate) {
		this.appliedleaveStartDate = appliedleaveStartDate;
	}
	public Timestamp getAppliedleaveEndDate() {
		return appliedleaveEndDate;
	}
	public void setAppliedleaveEndDate(Timestamp appliedleaveEndDate) {
		this.appliedleaveEndDate = appliedleaveEndDate;
	}
	
	public float getNoOfDaysApplied() {
		return noOfDaysApplied;
	}
	public void setNoOfDaysApplied(float noOfDaysApplied) {
		this.noOfDaysApplied = noOfDaysApplied;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalAddress() {
		return hospitalAddress;
	}
	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	public String getHospitalRoomNo() {
		return hospitalRoomNo;
	}
	public void setHospitalRoomNo(String hospitalRoomNo) {
		this.hospitalRoomNo = hospitalRoomNo;
	}
	public String getNameOFConcernedGP() {
		return nameOFConcernedGP;
	}
	public void setNameOFConcernedGP(String nameOFConcernedGP) {
		this.nameOFConcernedGP = nameOFConcernedGP;
	}
	public String getTreatmentDetails() {
		return treatmentDetails;
	}
	public void setTreatmentDetails(String treatmentDetails) {
		this.treatmentDetails = treatmentDetails;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getAmountClaimed() {
		return amountClaimed;
	}
	public void setAmountClaimed(double amountClaimed) {
		this.amountClaimed = amountClaimed;
	}
	public double getAmountApproved() {
		return amountApproved;
	}
	public void setAmountApproved(double amountApproved) {
		this.amountApproved = amountApproved;
	}
	public Timestamp getApprovedleaveStartDate() {
		return approvedleaveStartDate;
	}
	public void setApprovedleaveStartDate(Timestamp approvedleaveStartDate) {
		this.approvedleaveStartDate = approvedleaveStartDate;
	}
	public Timestamp getApprovedleaveEndDate() {
		return approvedleaveEndDate;
	}
	public void setApprovedleaveEndDate(Timestamp approvedleaveEndDate) {
		this.approvedleaveEndDate = approvedleaveEndDate;
	}
	public int getNoOfDaysApproved() {
		return noOfDaysApproved;
	}
	public void setNoOfDaysApproved(int noOfDaysApproved) {
		this.noOfDaysApproved = noOfDaysApproved;
	}
	public String getDisabilityType() {
		return disabilityType;
	}
	public void setDisabilityType(String disabilityType) {
		this.disabilityType = disabilityType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
