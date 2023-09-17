package com.rainier.dto.requestBean;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class OnBoardingJobDetailsRequestBean {
    @NotNull
    private int userId;
    @NotNull
    private Integer jobTitle;
    @NotNull
    private Integer position;
    @NotNull
    private Date hireDate;
    @NotNull
    private Integer employmentType;
    @NotNull
    private Integer reportingManager;
    @NotNull
    private Integer directDepartment;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(Integer jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(Integer employmentType) {
        this.employmentType = employmentType;
    }

    public Integer getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(Integer reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Integer getDirectDepartment() {
        return directDepartment;
    }

    public void setDirectDepartment(Integer directDepartment) {
        this.directDepartment = directDepartment;
    }
}
