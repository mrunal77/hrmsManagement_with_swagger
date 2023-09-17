package com.rainier.beans;

import java.util.Date;
import java.util.List;

import com.rainier.entities.SavingTimeSheet;

public class TimeSheetsMonthlyBean {
	private int userId;
	private String employeeName;
	private int repManId;
    private String reportingManagerName;
    private String startDate;
    private String endDate;
    private int calWeek;
    private int month;
    private int year;
	private Date monDate;
	private float monTotalHours;
	private int monday;
	private Date tueDate;
    private float tueTotalHours;
	private int tuesday;
	private Date wedDate;
    private float wedTotalHours;
	private int wednesday;
	private Date thursDate;
    private float thursTotalHours;
	private int thrusday;
	private Date friDate;
    private float friTotalHours;
	private int friday;
	private Date satDate;
    private float satTotalHours;
	private int saturday;
	private Date sunDate;
    private float sunTotalHours;
	private int sunday;
	private String monthName;
	private float totalHour;
	private String status;
    private String approverName;
    private String rejectReason;
    List<SavingTimeSheet> timeSheetListForEmployee;
    
	public int getRepManId() {
		return repManId;
	}
	public void setRepManId(int repManId) {
		this.repManId = repManId;
	}
	public List<SavingTimeSheet> getTimeSheetListForEmployee() {
		return timeSheetListForEmployee;
	}
	public void setTimeSheetListForEmployee(List<SavingTimeSheet> timeSheetListForEmployee) {
		this.timeSheetListForEmployee = timeSheetListForEmployee;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getReportingManagerName() {
		return reportingManagerName;
	}
	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	public int getCalWeek() {
		return calWeek;
	}
	public void setCalWeek(int calWeek) {
		this.calWeek = calWeek;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getMonDate() {
		return monDate;
	}
	public void setMonDate(Date monDate) {
		this.monDate = monDate;
	}
	public float getMonTotalHours() {
		return monTotalHours;
	}
	public void setMonTotalHours(float monTotalHours) {
		this.monTotalHours = monTotalHours;
	}
	public int getMonday() {
		return monday;
	}
	public void setMonday(int monday) {
		this.monday = monday;
	}
	public Date getTueDate() {
		return tueDate;
	}
	public void setTueDate(Date tueDate) {
		this.tueDate = tueDate;
	}
	public float getTueTotalHours() {
		return tueTotalHours;
	}
	public void setTueTotalHours(float tueTotalHours) {
		this.tueTotalHours = tueTotalHours;
	}
	public int getTuesday() {
		return tuesday;
	}
	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}
	public Date getWedDate() {
		return wedDate;
	}
	public void setWedDate(Date wedDate) {
		this.wedDate = wedDate;
	}
	public float getWedTotalHours() {
		return wedTotalHours;
	}
	public void setWedTotalHours(float wedTotalHours) {
		this.wedTotalHours = wedTotalHours;
	}
	public int getWednesday() {
		return wednesday;
	}
	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}
	public Date getThursDate() {
		return thursDate;
	}
	public void setThursDate(Date thursDate) {
		this.thursDate = thursDate;
	}
	public float getThursTotalHours() {
		return thursTotalHours;
	}
	public void setThursTotalHours(float thursTotalHours) {
		this.thursTotalHours = thursTotalHours;
	}
	public int getThrusday() {
		return thrusday;
	}
	public void setThrusday(int thrusday) {
		this.thrusday = thrusday;
	}
	public Date getFriDate() {
		return friDate;
	}
	public void setFriDate(Date friDate) {
		this.friDate = friDate;
	}
	public float getFriTotalHours() {
		return friTotalHours;
	}
	public void setFriTotalHours(float friTotalHours) {
		this.friTotalHours = friTotalHours;
	}
	public int getFriday() {
		return friday;
	}
	public void setFriday(int friday) {
		this.friday = friday;
	}
	public Date getSatDate() {
		return satDate;
	}
	public void setSatDate(Date satDate) {
		this.satDate = satDate;
	}
	public float getSatTotalHours() {
		return satTotalHours;
	}
	public void setSatTotalHours(float satTotalHours) {
		this.satTotalHours = satTotalHours;
	}
	public int getSaturday() {
		return saturday;
	}
	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}
	public Date getSunDate() {
		return sunDate;
	}
	public void setSunDate(Date sunDate) {
		this.sunDate = sunDate;
	}
	public float getSunTotalHours() {
		return sunTotalHours;
	}
	public void setSunTotalHours(float sunTotalHours) {
		this.sunTotalHours = sunTotalHours;
	}
	public int getSunday() {
		return sunday;
	}
	public void setSunday(int sunday) {
		this.sunday = sunday;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public float getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(float totalHour) {
		this.totalHour = totalHour;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
    
	
}
