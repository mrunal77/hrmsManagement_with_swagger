package com.rainier.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "rs_bgEmploymentHistory")
public class BgEmploymentHistoryEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "joiningDateC1")
    private Date joiningDateC1;

    @Column(name = "leaveDateC1")
    private Date leaveDateC1;

    @Column(name = "jobTitleC1")
    private String jobTitleC1;

    @Column(name = "employerWebsiteC1")
    private String employerWebsiteC1;

    @Column(name = "employerAddressC1")
    private String employerAddressC1;

    @Column(name = "employerPhoneC1")
    private String employerPhoneC1;

    @Column(name = "employerStateC1")
    private String employerStateC1;

    @Column(name = "employerCityC1")
    private String employerCityC1;

    @Column(name = "employerZipcodeC1")
    private String employerZipcodeC1;

    @Column(name = "employerTypeC1")
    private String employerTypeC1;

    @Column(name = "employerSupervisorNameC1")
    private String employerSupervisorNameC1;

    @Column(name = "employerSupervisorContactNoC1")
    private String employerSupervisorContactNoC1;

    @Column(name = "employerSupervisorEmailC1")
    private String employerSupervisorEmailC1;

    @Column(name = "employerDutiesC1")
    private String employerDutiesC1;

    @Column(name = "employerReasonForLeaveC1")
    private String employerReasonForLeaveC1;

    @Column(name = "expletter")
    private String expLetter;


    public String getExpLetter() {
        return expLetter;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExpLetter(String expLetter) {
        this.expLetter = expLetter;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getJoiningDateC1() {
        return joiningDateC1;
    }

    public void setJoiningDateC1(Date joiningDateC1) {
        this.joiningDateC1 = joiningDateC1;
    }

    public Date getLeaveDateC1() {
        return leaveDateC1;
    }

    public void setLeaveDateC1(Date leaveDateC1) {
        this.leaveDateC1 = leaveDateC1;
    }

    public String getJobTitleC1() {
        return jobTitleC1;
    }

    public void setJobTitleC1(String jobTitleC1) {
        this.jobTitleC1 = jobTitleC1;
    }

    public String getEmployerWebsiteC1() {
        return employerWebsiteC1;
    }

    public void setEmployerWebsiteC1(String employerWebsiteC1) {
        this.employerWebsiteC1 = employerWebsiteC1;
    }

    public String getEmployerAddressC1() {
        return employerAddressC1;
    }

    public void setEmployerAddressC1(String employerAddressC1) {
        this.employerAddressC1 = employerAddressC1;
    }

    public String getEmployerPhoneC1() {
        return employerPhoneC1;
    }

    public void setEmployerPhoneC1(String employerPhoneC1) {
        this.employerPhoneC1 = employerPhoneC1;
    }

    public String getEmployerStateC1() {
        return employerStateC1;
    }

    public void setEmployerStateC1(String employerStateC1) {
        this.employerStateC1 = employerStateC1;
    }

    public String getEmployerCityC1() {
        return employerCityC1;
    }

    public void setEmployerCityC1(String employerCityC1) {
        this.employerCityC1 = employerCityC1;
    }

    public String getEmployerZipcodeC1() {
        return employerZipcodeC1;
    }

    public void setEmployerZipcodeC1(String employerZipcodeC1) {
        this.employerZipcodeC1 = employerZipcodeC1;
    }

    public String getEmployerTypeC1() {
        return employerTypeC1;
    }

    public void setEmployerTypeC1(String employerTypeC1) {
        this.employerTypeC1 = employerTypeC1;
    }

    public String getEmployerSupervisorNameC1() {
        return employerSupervisorNameC1;
    }

    public void setEmployerSupervisorNameC1(String employerSupervisorNameC1) {
        this.employerSupervisorNameC1 = employerSupervisorNameC1;
    }

    public String getEmployerSupervisorContactNoC1() {
        return employerSupervisorContactNoC1;
    }

    public void setEmployerSupervisorContactNoC1(String employerSupervisorContactNoC1) {
        this.employerSupervisorContactNoC1 = employerSupervisorContactNoC1;
    }

    public String getEmployerSupervisorEmailC1() {
        return employerSupervisorEmailC1;
    }

    public void setEmployerSupervisorEmailC1(String employerSupervisorEmailC1) {
        this.employerSupervisorEmailC1 = employerSupervisorEmailC1;
    }

    public String getEmployerDutiesC1() {
        return employerDutiesC1;
    }

    public void setEmployerDutiesC1(String employerDutiesC1) {
        this.employerDutiesC1 = employerDutiesC1;
    }

    public String getEmployerReasonForLeaveC1() {
        return employerReasonForLeaveC1;
    }

    public void setEmployerReasonForLeaveC1(String employerReasonForLeaveC1) {
        this.employerReasonForLeaveC1 = employerReasonForLeaveC1;
    }

	/*@Column(name = "joiningDateC2")
	private Date joiningDateC2;

	@Column(name = "leaveDateC2")
	private Date leaveDateC2;

	@Column(name = "jobTitleC2")
	private String jobTitleC2;

	@Column(name = "employerWebsiteC2")
	private String employerWebsiteC2;

	@Column(name = "employerAddressC2")
	private String employerAddressC2;

	@Column(name = "employerPhoneC2")
	private String employerPhoneC2;

	@Column(name = "employerStateC2")
	private String employerStateC2;

	@Column(name = "employerCityC2")
	private String employerCityC2;

	@Column(name = "employerZipcodeC2")
	private String employerZipcodeC2;

	@Column(name = "employerTypeC2")
	private String employerTypeC2;

	@Column(name = "employerSupervisorNameC2")
	private String employerSupervisorNameC2;

	@Column(name = "employerSupervisorContactNoC2")
	private String employerSupervisorContactNoC2;

	@Column(name = "employerSupervisorEmailC2")
	private String employerSupervisorEmailC2;

	@Column(name = "employerDutiesC2")
	private String employerDutiesC2;

	@Column(name = "employerReasonForLeaveC2")
	private String employerReasonForLeaveC2;

	@Column(name = "joiningDateC3")
	private Date joiningDateC3;

	@Column(name = "leaveDateC3")
	private Date leaveDateC3;

	@Column(name = "jobTitleC3")
	private String jobTitleC3;

	@Column(name = "employerWebsiteC3")
	private String employerWebsiteC3;

	@Column(name = "employerAddressC3")
	private String employerAddressC3;

	@Column(name = "employerPhoneC3")
	private String employerPhoneC3;

	@Column(name = "employerStateC3")
	private String employerStateC3;

	@Column(name = "employerCityC3")
	private String employerCityC3;

	@Column(name = "employerZipcodeC3")
	private String employerZipcodeC3;

	@Column(name = "employerTypeC3")
	private String employerTypeC3;

	@Column(name = "employerSupervisorNameC3")
	private String employerSupervisorNameC3;

	@Column(name = "employerSupervisorContactNoC3")
	private String employerSupervisorContactNoC3;

	@Column(name = "employerSupervisorEmailC3")
	private String employerSupervisorEmailC3;

	@Column(name = "employerDutiesC3")
	private String employerDutiesC3;

	@Column(name = "employerReasonForLeaveC3")
	private String employerReasonForLeaveC3;

	@Column(name = "joiningDateC4")
	private Date joiningDateC4;

	@Column(name = "leaveDateC4")
	private Date leaveDateC4;

	@Column(name = "jobTitleC4")
	private String jobTitleC4;

	@Column(name = "employerWebsiteC4")
	private String employerWebsiteC4;

	@Column(name = "employerAddressC4")
	private String employerAddressC4;

	@Column(name = "employerPhoneC4")
	private String employerPhoneC4;

	@Column(name = "employerStateC4")
	private String employerStateC4;

	@Column(name = "employerCityC4")
	private String employerCityC4;

	@Column(name = "employerZipcodeC4")
	private String employerZipcodeC4;

	@Column(name = "employerTypeC4")
	private String employerTypeC4;

	@Column(name = "employerSupervisorNameC4")
	private String employerSupervisorNameC4;

	@Column(name = "employerSupervisorContactNoC4")
	private String employerSupervisorContactNoC4;

	@Column(name = "employerSupervisorEmailC4")
	private String employerSupervisorEmailC4;

	@Column(name = "employerDutiesC4")
	private String employerDutiesC4;

	@Column(name = "employerReasonForLeaveC4")
	private String employerReasonForLeaveC4;

	@Column(name = "joiningDateC5")
	private Date joiningDateC5;

	@Column(name = "leaveDateC5")
	private Date leaveDateC5;

	@Column(name = "jobTitleC5")
	private String jobTitleC5;

	@Column(name = "employerWebsiteC5")
	private String employerWebsiteC5;

	@Column(name = "employerAddressC5")
	private String employerAddressC5;

	@Column(name = "employerPhoneC5")
	private String employerPhoneC5;

	@Column(name = "employerStateC5")
	private String employerStateC5;

	@Column(name = "employerCityC5")
	private String employerCityC5;

	@Column(name = "employerZipcodeC5")
	private String employerZipcodeC5;

	@Column(name = "employerTypeC5")
	private String employerTypeC5;

	@Column(name = "employerSupervisorNameC5")
	private String employerSupervisorNameC5;

	@Column(name = "employerSupervisorContactNoC5")
	private String employerSupervisorContactNoC5;

	@Column(name = "employerSupervisorEmailC5")
	private String employerSupervisorEmailC5;

	@Column(name = "employerDutiesC5")
	private String employerDutiesC5;

	@Column(name = "employerReasonForLeaveC5")
	private String employerReasonForLeaveC5;

	@Column(name = "joiningDateC6")
	private Date joiningDateC6;

	@Column(name = "leaveDateC6")
	private Date leaveDateC6;

	@Column(name = "jobTitleC6")
	private String jobTitleC6;

	@Column(name = "employerWebsiteC6")
	private String employerWebsiteC6;

	@Column(name = "employerAddressC6")
	private String employerAddressC6;

	@Column(name = "employerPhoneC6")
	private String employerPhoneC6;

	@Column(name = "employerStateC6")
	private String employerStateC6;

	@Column(name = "employerCityC6")
	private String employerCityC6;

	@Column(name = "employerZipcodeC6")
	private String employerZipcodeC6;

	@Column(name = "employerTypeC6")
	private String employerTypeC6;

	@Column(name = "employerSupervisorNameC6")
	private String employerSupervisorNameC6;

	@Column(name = "employerSupervisorContactNoC6")
	private String employerSupervisorContactNoC6;

	@Column(name = "employerSupervisorEmailC6")
	private String employerSupervisorEmailC6;

	@Column(name = "employerDutiesC6")
	private String employerDutiesC6;

	@Column(name = "employerReasonForLeaveC6")
	private String employerReasonForLeaveC6;

	@Column(name = "joiningDateC7")
	private Date joiningDateC7;

	@Column(name = "leaveDateC7")
	private Date leaveDateC7;

	@Column(name = "jobTitleC7")
	private String jobTitleC7;

	@Column(name = "employerWebsiteC7")
	private String employerWebsiteC7;

	@Column(name = "employerAddressC7")
	private String employerAddressC7;

	@Column(name = "employerPhoneC7")
	private String employerPhoneC7;

	@Column(name = "employerStateC7")
	private String employerStateC7;

	@Column(name = "employerCityC7")
	private String employerCityC7;

	@Column(name = "employerZipcodeC7")
	private String employerZipcodeC7;

	@Column(name = "employerTypeC7")
	private String employerTypeC7;

	@Column(name = "employerSupervisorNameC7")
	private String employerSupervisorNameC7;

	@Column(name = "employerSupervisorContactNoC7")
	private String employerSupervisorContactNoC7;

	@Column(name = "employerSupervisorEmailC7")
	private String employerSupervisorEmailC7;

	@Column(name = "employerDutiesC7")
	private String employerDutiesC7;

	@Column(name = "employerReasonForLeaveC7")
	private String employerReasonForLeaveC7;
*/


}
