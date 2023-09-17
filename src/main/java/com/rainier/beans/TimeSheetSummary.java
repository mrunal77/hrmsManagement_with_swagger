package com.rainier.beans;

import java.util.Date;
import java.util.List;

import com.rainier.entities.SavingTimeSheet;

public class TimeSheetSummary 
{
	private int employeeId;
	private String employeeFullName;
    private String reportingManagerName;
    private String fromDate;
    private String toDate;
    private int weekNo;
    private int month;
    private int year;
	private float mondayHrs;
    private float tuesdayHrs;
    private float wednesdayHrs;
    private float thursdayHrs;
    private float fridayHrs;
    private float saturdayHrs;
    private float sundayHrs;
    private float totalHours;
    private String status;
    private String approverName;
	private String rejectReason;
	private int sunday;
	private int monday;
	private int tuesday;
	private int wednesday;
	private int thrusday;
	private int friday;
	private int saturday;
	private String monthName;
	private Date leaveFromDate;
	private Date leaveToDate;
	
	
	

	public Date getLeaveFromDate() {
		return leaveFromDate;
	}

	public void setLeaveFromDate(Date leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	public Date getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(Date leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public int getSunday() {
		return sunday;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
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

	List<SavingTimeSheet> timeSheetListForEmployee;
	
    public List<SavingTimeSheet> getTimeSheetListForEmployee() {
		return timeSheetListForEmployee;
	}

	public void setTimeSheetListForEmployee(List<SavingTimeSheet> timeSheetListForEmployee) {
		this.timeSheetListForEmployee = timeSheetListForEmployee;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
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
    
	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getReportingManagerName() {
        return reportingManagerName;
    }

    public void setReportingManagerName(String reportingManagerName) {
        this.reportingManagerName = reportingManagerName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public float getMondayHrs() {
        return mondayHrs;
    }

    public void setMondayHrs(float mondayHrs) {
        this.mondayHrs = mondayHrs;
    }

    public float getTuesdayHrs() {
        return tuesdayHrs;
    }

    public void setTuesdayHrs(float tuesdayHrs) {
        this.tuesdayHrs = tuesdayHrs;
    }

    public float getWednesdayHrs() {
        return wednesdayHrs;
    }

    public void setWednesdayHrs(float wednesdayHrs) {
        this.wednesdayHrs = wednesdayHrs;
    }

    public float getThursdayHrs() {
        return thursdayHrs;
    }

    public void setThursdayHrs(float thursdayHrs) {
        this.thursdayHrs = thursdayHrs;
    }

    public float getFridayHrs() {
        return fridayHrs;
    }

    public void setFridayHrs(float fridayHrs) {
        this.fridayHrs = fridayHrs;
    }

    public float getSaturdayHrs() {
        return saturdayHrs;
    }

    public void setSaturdayHrs(float saturdayHrs) {
        this.saturdayHrs = saturdayHrs;
    }

    public float getSundayHrs() {
        return sundayHrs;
    }

    public void setSundayHrs(float sundayHrs) {
        this.sundayHrs = sundayHrs;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
