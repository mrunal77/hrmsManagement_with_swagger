package com.rainier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_departments")
public class Main_Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "deptname")
    private String name;

    @Column(name = "deptcode")
    private String code;

    @JsonIgnore
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "startdate")
    private String startedOn;

    @JsonIgnore
    @Column(name = "country")
    private int countryId;

    @Transient
    private String countryName;

    @JsonIgnore
    @Column(name = "state")
    private int stateId;

    @Transient
    private String stateName;

    @JsonIgnore
    @Column(name = "city")
    private int cityId;

    @Transient
    private String cityName;

    @JsonIgnore
    @Column(name = "address1")
    private String streetAddress1;

    @JsonIgnore
    @Column(name = "address2")
    private String streetAddress2;

    @JsonIgnore
    @Column(name = "address3")
    private String streetAddress3;

    @JsonIgnore
    @Column(name = "timezone")
    private int timeZoneId;

    @Transient
    private String timeZoneName;

    @Transient
    private String timeZoneAbbr;

    @Column(name = "depthead")
    private String departmentHeadId;

    @JsonIgnore
    @Column(name = "unitid")
    private Integer businessUnitId;

    @JsonIgnore
    @Column(name = "businessUnit")
    private String businessUnitName;
    // private String businessUnit;

    @JsonIgnore
    @Column(name = "createdby", nullable = false, updatable = false)
    private Integer createdBy;

    @JsonIgnore
    @Column(name = "modifiedby")
    private Integer modifiedBy;

    @JsonIgnore
    @Column(name = "createddate", nullable = false, updatable = false)
    private Timestamp createdDate;

    @JsonIgnore
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @JsonIgnore
    @Column(name = "isactive", nullable = false, columnDefinition = "int default 1")
    private int isactive;

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getStreetAddress3() {
        return streetAddress3;
    }

    public void setStreetAddress3(String streetAddress3) {
        this.streetAddress3 = streetAddress3;
    }

    public int getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(int timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getDepartmentHeadId() {
        return departmentHeadId;
    }

    public void setDepartmentHeadId(String departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }

    public Integer getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(Integer businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    public String getTimeZoneAbbr() {
        return timeZoneAbbr;
    }

    public void setTimeZoneAbbr(String timeZoneAbbr) {
        this.timeZoneAbbr = timeZoneAbbr;
    }
    // public String getBusinessUnitName() {
    // return businessUnitName;
    // }
    //
    // public void setBusinessUnitName(String businessUnitName) {
    // this.businessUnitName = businessUnitName;
    // }

    /*
     * public String getBusinessUnit() { return businessUnit; }
     *
     *
     * public void setBusinessUnit(String businessUnit) { this.businessUnit =
     * businessUnit; }
     */

}
