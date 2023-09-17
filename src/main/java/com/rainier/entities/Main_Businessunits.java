package com.rainier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "main_businessunits")
public class Main_Businessunits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "unitname", unique=true)
    private String name;

    @Column(name = "unitcode")
    private String code;

    @JsonIgnore
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @Column(name = "startdate")
    private String startDate;

    @JsonIgnore
    @Column(name = "country")
    private Integer country;

    @JsonIgnore
    @Column(name = "state")
    private Integer state;

    @JsonIgnore
    @Column(name = "city")
    private Integer city;

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
    private Integer timezone;

    @JsonIgnore
    @Column(name = "createddate", nullable = false, updatable = false)
    private Timestamp createddate;

    @JsonIgnore
    @Column(name = "createdby", nullable = false, updatable = false)
    private int createdby;

    @JsonIgnore
    @Column(name = "modifiedby")
    private int modifiedBy;

    @JsonIgnore
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @JsonIgnore
    @Column(name = "isactive", nullable = false, columnDefinition = "int default 1")
    private int isactive;

    @JsonIgnore
    @Transient
    private String countryName;

    @JsonIgnore
    @Transient
    private String stateName;

    @JsonIgnore
    @Transient
    private String cityName;

    @JsonIgnore
    @Transient
    private String timezoneName;

    @JsonIgnore
    @Transient
    private String timeZoneCode;

    @Transient
    private List<Main_Departments> subordinates;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
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

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public String getTimeZoneCode() {
        return timeZoneCode;
    }

    public void setTimeZoneCode(String timeZoneCode) {
        this.timeZoneCode = timeZoneCode;
    }

    public List<Main_Departments> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Main_Departments> subordinates) {
        this.subordinates = subordinates;
    }

}
