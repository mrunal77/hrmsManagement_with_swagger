package com.rainier.entities;

import javax.persistence.*;

@Entity
@Table(name = "rs_emp_currentaddress")
public class EmployeeCurrentAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_Id")
    private Integer userId;

    @Column(name = "current_streetaddress")
    private String currentStreetAddress;

    @ManyToOne
    @JoinColumn(name = "current_country", referencedColumnName = "id", insertable = true, updatable = true)
    private Tbl_CountriesEntity currentCountry;

    @ManyToOne
    @JoinColumn(name = "current_state", referencedColumnName = "id", insertable = true, updatable = true)
    private Tbl_StatesEntity currentState;

    @ManyToOne
    @JoinColumn(name = "current_city", referencedColumnName = "id", insertable = true, updatable = true)
    private Tbl_CitiesEntity currentCity;

    @Column(name = "current_pincode")
    private Integer currentPinCode;

    @Column(name = "set_as_current", columnDefinition = "TINYINT default 1")
    private Short setAsCurrent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCurrentStreetAddress() {
        return currentStreetAddress;
    }

    public void setCurrentStreetAddress(String currentStreetAddress) {
        this.currentStreetAddress = currentStreetAddress;
    }

    public Tbl_CountriesEntity getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(Tbl_CountriesEntity currentCountry) {
        this.currentCountry = currentCountry;
    }

    public Tbl_StatesEntity getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Tbl_StatesEntity currentState) {
        this.currentState = currentState;
    }

    public Tbl_CitiesEntity getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(Tbl_CitiesEntity currentCity) {
        this.currentCity = currentCity;
    }

    public Integer getCurrentPinCode() {
        return currentPinCode;
    }

    public void setCurrentPinCode(Integer currentPinCode) {
        this.currentPinCode = currentPinCode;
    }

    public Short getSetAsCurrent() {
        return setAsCurrent;
    }

    public void setSetAsCurrent(Short setAsCurrent) {
        this.setAsCurrent = setAsCurrent;
    }
}
