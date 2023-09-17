package com.rainier.dto.responseBean;

import java.math.BigInteger;

public class CityStateResponseBean {
private	BigInteger stateId;
private String stateName;
private BigInteger cityId;
private String cityName;

public BigInteger getCityId() {
	return cityId;
}
public void setCityId(BigInteger cityId) {
	this.cityId = cityId;
}
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}
public BigInteger getStateId() {
	return stateId;
}
public void setStateId(BigInteger stateId) {
	this.stateId = stateId;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}

	

}
