package com.rainier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "main_organisationinfo")
public class OrganizationInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "organisationname")
    private String OrganizationName;

    @Column(name = "org_image")
    private String orgImage;

    @Column(name = "domain")
    private String domain;

    @Column(name = "website")
    private String website;

    @Column(name = "orgdescription")
    private String orgDescription;

    @Column(name = "totalemployees")
    private int totalEmployees;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "org_startdate")
    private Date orgStartDate;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "secondaryphone")
    private String secondaryPhone;

    @Column(name = "email")
    private String email;

    @Column(name = "secondaryemail")
    private String SecondaryEmail;

    @Column(name = "faxnumber")
    private String faxNo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country", referencedColumnName = "id")
    private Tbl_CountriesEntity country;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "state", referencedColumnName = "id")
    private Tbl_StatesEntity state;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "city", referencedColumnName = "id")
    private Tbl_CitiesEntity city;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "address3")
    private String address3;

    @Column(name = "description")
    private String description;

    @Column(name = "orghead")
    private String OrganizationHead;

    @Column(name = "designation")
    private String designation;

    @Column(name = "company_FEIN_No")
    private String companyFEINNo;

    @Column(name = "NAICS_code")
    private String naicsCode;

    @Column(name = "stateOfIncorporation")
    private String stateOfIncorporation;

    @Column(name = "company_officers")
    private String companyOfficers;

    @Column(name = "headquarter_Address")
    private String headQuarterAddress;

    @Column(name = "branch_Address")
    private String branchAddress;

    @Column(name = "billing_Address")
    private String billingAddress;

    @JsonIgnore
    @Column(name = "createddate", updatable = false)
    private Timestamp created_Date;

    @JsonIgnore
    @Column(name = "createdby", updatable = false)
    private int createdBy;

    @JsonIgnore
    @Column(name = "modifiedby")
    private int modifiedBy;

    @JsonIgnore
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @JsonIgnore
    @Column(name = "isactive")
    private Short isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOrgImage() {
        return orgImage;
    }

    public void setOrgImage(String orgImage) {
        this.orgImage = orgImage;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getOrgStartDate() {
        return orgStartDate;
    }

    public void setOrgStartDate(Date orgStartDate) {
        this.orgStartDate = orgStartDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondaryEmail() {
        return SecondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        SecondaryEmail = secondaryEmail;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public Tbl_CountriesEntity getCountry() {
        return country;
    }

    public void setCountry(Tbl_CountriesEntity country) {
        this.country = country;
    }

    public Tbl_StatesEntity getState() {
        return state;
    }

    public void setState(Tbl_StatesEntity state) {
        this.state = state;
    }

    public Tbl_CitiesEntity getCity() {
        return city;
    }

    public void setCity(Tbl_CitiesEntity city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizationHead() {
        return OrganizationHead;
    }

    public void setOrganizationHead(String organizationHead) {
        OrganizationHead = organizationHead;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompanyFEINNo() {
        return companyFEINNo;
    }

    public void setCompanyFEINNo(String companyFEINNo) {
        this.companyFEINNo = companyFEINNo;
    }

    public String getNaicsCode() {
        return naicsCode;
    }

    public void setNaicsCode(String naicsCode) {
        this.naicsCode = naicsCode;
    }

    public String getStateOfIncorporation() {
        return stateOfIncorporation;
    }

    public void setStateOfIncorporation(String stateOfIncorporation) {
        this.stateOfIncorporation = stateOfIncorporation;
    }

    public String getCompanyOfficers() {
        return companyOfficers;
    }

    public void setCompanyOfficers(String companyOfficers) {
        this.companyOfficers = companyOfficers;
    }

    public String getHeadQuarterAddress() {
        return headQuarterAddress;
    }

    public void setHeadQuarterAddress(String headQuarterAddress) {
        this.headQuarterAddress = headQuarterAddress;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Timestamp getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Timestamp created_Date) {
        this.created_Date = created_Date;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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

    public Short getIsActive() {
        return isActive;
    }

    public void setIsActive(Short isActive) {
        this.isActive = isActive;
    }

}
