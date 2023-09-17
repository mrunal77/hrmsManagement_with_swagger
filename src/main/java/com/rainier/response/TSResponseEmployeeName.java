package com.rainier.response;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.rainier.dto.responseBean.SaveTimeSheetResponseClass;
import com.rainier.entities.SavingTimeSheet;

public class TSResponseEmployeeName 
{  private int userId;
	private String employeeName;
	private int repManId;
	private Short weekNo;
	private Date startDate;
	private Date endDate;
	private Double totalHour;
	private String totalHours;
	private String status;
	private Short calWeek;
	private Double monTotalHours;
	private Date monDate;
	private int monday;
	private Double tueTotalHours;
	private Date tueDate;
	private int tuesday;
	private Double wedTotalHours;
	private Date wedDate;
	private int wednesday;
	private Double thursTotalHours;
	private Date thursDate;
	private int thrusday;
	private Double friTotalHours;
	private Date friDate;
	private int friday;
	private Double satTotalHours;
	private Date satDate;
	private int saturday;
	private Double sunTotalHours;
	private Date sunDate;
	private int sunday;
	private String TotalWeekHours;
	private String monthName;
	private int year;
	private short month;
	
	
	
	
	public short getMonth() {
		return month;
	}
	public void setMonth(short month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRepManId() {
		return repManId;
	}
	public void setRepManId(int repManId) {
		this.repManId = repManId;
	}
	public int getMonday() {
		return monday;
	}
	public void setMonday(int monday) {
		this.monday = monday;
	}
	public int getTuesday() {
		return tuesday;
	}
	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}
	public int getWednesday() {
		return wednesday;
	}
	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}
	public int getThrusday() {
		return thrusday;
	}
	public void setThrusday(int thrusday) {
		this.thrusday = thrusday;
	}
	public int getFriday() {
		return friday;
	}
	public void setFriday(int friday) {
		this.friday = friday;
	}
	public int getSaturday() {
		return saturday;
	}
	public void setSaturday(int saturday) {
		this.saturday = saturday;
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
	List<SavingTimeSheet> timeSheetListForEmployee;
	Set<SaveTimeSheetResponseClass> timeSheetListForEmployees;
	
	public Set<SaveTimeSheetResponseClass> getTimeSheetListForEmployees() {
		return timeSheetListForEmployees;
	}
	public void setTimeSheetListForEmployees(Set<SaveTimeSheetResponseClass> timeSheetListForEmployees) {
		this.timeSheetListForEmployees = timeSheetListForEmployees;
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
	public Short getWeekNo() {
		return weekNo;
	}
	public void setWeekNo(Short weekNo) {
		this.weekNo = weekNo;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(Double totalHour) {
		this.totalHour = totalHour;
	}
	public String getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Short getCalWeek() {
		return calWeek;
	}
	public void setCalWeek(Short calWeek) {
		this.calWeek = calWeek;
	}
	public Double getMonTotalHours() {
		return monTotalHours;
	}
	public void setMonTotalHours(Double monTotalHours) {
		this.monTotalHours = monTotalHours;
	}
	public Date getMonDate() {
		return monDate;
	}
	public void setMonDate(Date monDate) {
		this.monDate = monDate;
	}
	public Double getTueTotalHours() {
		return tueTotalHours;
	}
	public void setTueTotalHours(Double tueTotalHours) {
		this.tueTotalHours = tueTotalHours;
	}
	public Date getTueDate() {
		return tueDate;
	}
	public void setTueDate(Date tueDate) {
		this.tueDate = tueDate;
	}
	public Double getWedTotalHours() {
		return wedTotalHours;
	}
	public void setWedTotalHours(Double wedTotalHours) {
		this.wedTotalHours = wedTotalHours;
	}
	public Date getWedDate() {
		return wedDate;
	}
	public void setWedDate(Date wedDate) {
		this.wedDate = wedDate;
	}
	public Double getThursTotalHours() {
		return thursTotalHours;
	}
	public void setThursTotalHours(Double thursTotalHours) {
		this.thursTotalHours = thursTotalHours;
	}
	public Date getThursDate() {
		return thursDate;
	}
	public void setThursDate(Date thursDate) {
		this.thursDate = thursDate;
	}
	public Double getFriTotalHours() {
		return friTotalHours;
	}
	public void setFriTotalHours(Double friTotalHours) {
		this.friTotalHours = friTotalHours;
	}
	public Date getFriDate() {
		return friDate;
	}
	public void setFriDate(Date friDate) {
		this.friDate = friDate;
	}
	public Double getSatTotalHours() {
		return satTotalHours;
	}
	public void setSatTotalHours(Double satTotalHours) {
		this.satTotalHours = satTotalHours;
	}
	public Date getSatDate() {
		return satDate;
	}
	public void setSatDate(Date satDate) {
		this.satDate = satDate;
	}
	public Double getSunTotalHours() {
		return sunTotalHours;
	}
	public void setSunTotalHours(Double sunTotalHours) {
		this.sunTotalHours = sunTotalHours;
	}
	public Date getSunDate() {
		return sunDate;
	}
	public void setSunDate(Date sunDate) {
		this.sunDate = sunDate;
	}
	public String getTotalWeekHours() {
		return TotalWeekHours;
	}
	public void setTotalWeekHours(String totalWeekHours) {
		TotalWeekHours = totalWeekHours;
	}
	public List<SavingTimeSheet> getTimeSheetListForEmployee() {
		return timeSheetListForEmployee;
	}
	public void setTimeSheetListForEmployee(List<SavingTimeSheet> timeSheetListForEmployee) {
		this.timeSheetListForEmployee = timeSheetListForEmployee;
	}

	
	
	
}
