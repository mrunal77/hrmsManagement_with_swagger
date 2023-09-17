package com.rainier.beans;

public class LocationResponseBean {

    private String message;
    private boolean status;
    private Object countryList;
    private Object stateList;
    private Object citiesList;
    private Object timezoneList;
    private Object selectedTimezoneNames;
    private Object selectedCountryNames;
    private Object selectedStateNames;
    private Object selectedCityNames;
    private Object basedOnCountry_StateList;
    private Object basedOnCountryState_CitiesList;
    private Object countryStateList;
    private Object country_State_city_viewList;

    public Object getCountryStateList() {
        return countryStateList;
    }

    public void setCountryStateList(Object countryStateList) {
        this.countryStateList = countryStateList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getCountryList() {
        return countryList;
    }

    public void setCountryList(Object countryList) {
        this.countryList = countryList;
    }

    public Object getStateList() {
        return stateList;
    }

    public void setStateList(Object stateList) {
        this.stateList = stateList;
    }

    public Object getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(Object citiesList) {
        this.citiesList = citiesList;
    }

    public Object getTimezoneList() {
        return timezoneList;
    }

    public void setTimezoneList(Object timezoneList) {
        this.timezoneList = timezoneList;
    }

    public Object getSelectedTimezoneNames() {
        return selectedTimezoneNames;
    }

    public void setSelectedTimezoneNames(Object selectedTimezoneNames) {
        this.selectedTimezoneNames = selectedTimezoneNames;
    }

    public Object getSelectedCountryNames() {
        return selectedCountryNames;
    }

    public void setSelectedCountryNames(Object selectedCountryNames) {
        this.selectedCountryNames = selectedCountryNames;
    }

    public Object getSelectedStateNames() {
        return selectedStateNames;
    }

    public void setSelectedStateNames(Object selectedStateNames) {
        this.selectedStateNames = selectedStateNames;
    }

    public Object getSelectedCityNames() {
        return selectedCityNames;
    }

    public void setSelectedCityNames(Object selectedCityNames) {
        this.selectedCityNames = selectedCityNames;
    }

    public Object getBasedOnCountry_StateList() {
        return basedOnCountry_StateList;
    }

    public void setBasedOnCountry_StateList(Object basedOnCountry_StateList) {
        this.basedOnCountry_StateList = basedOnCountry_StateList;
    }

    public Object getBasedOnCountryState_CitiesList() {
        return basedOnCountryState_CitiesList;
    }

    public void setBasedOnCountryState_CitiesList(Object basedOnCountryState_CitiesList) {
        this.basedOnCountryState_CitiesList = basedOnCountryState_CitiesList;
    }

    public Object getCountry_State_city_viewList() {
        return country_State_city_viewList;
    }

    public void setCountry_State_city_viewList(Object country_State_city_viewList) {
        this.country_State_city_viewList = country_State_city_viewList;
    }
}
