package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_empmedicalclaims")
public class MedicalClaimEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "injury_type")
	private int medicalClaimType;

	@Column(name = "disability_type")
	private String disabilityType;

	@Column(name = "injured_date")
	private Timestamp injuryDate;

	@Column(name = "injury_name")
	private String injuryName;

	@Column(name = "injury_severity")
	private int severityType;

	@Column(name = "injury_description")
	private String injuryDescription;

	@Column(name = "medical_insurer_name")
	private String medicalInsurerName;

	@Column(name = "expected_date_join")
	private Timestamp joiningDate;

	@Column(name = "leavebyemployeer_from_date")
	private Timestamp appliedleaveStartDate;

	@Column(name = "leavebyemployeer_to_date")
	private Timestamp appliedleaveEndDate;

	@Column(name = "leavebyemployeer_days")
	private float noOfDaysApplied;

	@Column(name = "hospital_name")
	private String hospitalName;
	@Column(name = "hospital_address")
	private String hospitalAddress;
	@Column(name = "room_number")
	private String hospitalRoomNo;
	@Column(name = "concerned_physician_name")
	private String nameOFConcernedGP;
	@Column(name = "treatment_details")
	private String treatmentDetails;
	@Column(name = "total_cost")
	private double totalCost;
	@Column(name = "amount_claimed_for")
	private double amountClaimed;
	@Column(name = "amount_approved")
	private double amountApproved;

	@Column(name = "leaveappliedbyemployee_from_date")
	private Timestamp approvedleaveStartDate;
	@Column(name = "leaveappliedbyemployee_to_date")
	private Timestamp approvedleaveEndDate;
	@Column(name = "leaveappliedbyemployee_days")
	private float noOfDaysApproved;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "isactive")
	private int isactive;

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

	public String getDisabilityType() {
		return disabilityType;
	}

	public void setDisabilityType(String disabilityType) {
		this.disabilityType = disabilityType;
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

	public float getNoOfDaysApproved() {
		return noOfDaysApproved;
	}

	public void setNoOfDaysApproved(float f) {
		this.noOfDaysApproved = f;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

}
