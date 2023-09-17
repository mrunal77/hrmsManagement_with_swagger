package com.rainier.dto.requestBean;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@XmlRootElement
public class EmployeeCurrentAddressBean {
    private int id;

    @NotNull
    private Integer userId;

    @NotNull(message = "Send User Id")
    private String currentStreetAddress;

    @NotNull
    private Integer currentCountry;

    @NotNull
    private Integer currentState;

    @NotNull
    private Integer currentCity;

    @NotNull
    private Integer currentPinCode;

    @NotNull
    private Short setAsDefaultCurrentAddress;

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

    public Integer getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(Integer currentCountry) {
        this.currentCountry = currentCountry;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    public Integer getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(Integer currentCity) {
        this.currentCity = currentCity;
    }

    public Integer getCurrentPinCode() {
        return currentPinCode;
    }

    public void setCurrentPinCode(Integer currentPinCode) {
        this.currentPinCode = currentPinCode;
    }

    public Short getSetAsDefaultCurrentAddress() {
        return setAsDefaultCurrentAddress;
    }

    public void setSetAsDefaultCurrentAddress(Short setAsDefaultCurrentAddress) {
        this.setAsDefaultCurrentAddress = setAsDefaultCurrentAddress;
    }
}
