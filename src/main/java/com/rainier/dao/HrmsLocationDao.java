package com.rainier.dao;

import com.rainier.entities.*;

import java.util.List;

public interface HrmsLocationDao {
	
	List<Tbl_CountriesEntity> listOfCountries();

	List<Tbl_StatesEntity> listOfStates(int countryId);

	List<Tbl_CitiesEntity> listOfCities(int countryId, int stateId);

	// for timezone
    List<Tbl_TimezonesEntity> listOfTimezones();
	

/*	-------------------------------------------------------------------------------------------------*/

	// FOR Adding country to main_timezone tbl
    String addTimezone(Main_TimezoneEntity timezoneUnit);

	// FOR Adding country to main_country tbl
    String addCountry(Main_CountriesEntity countryUnit);
	
	// FOR Adding states to main_country tbl
    String addStates(Main_StatesEntity statesUnit);
	
	// FOR Adding cities to main_cities tbl
    String addCities(Main_citiesEntity citiesUnit);

/*	-------------------------------------------------------------------------------------------------*/
	// for fetching selected timezone name
List<Main_TimezoneEntity> nameOfTimezone();
	
	// for fetching  selected Country name
    List<Main_CountriesEntity> nameOfCountry();
	
	// for fetching Selected State name.
    List<Main_StatesEntity> nameOfStates(int countryId);
	
	//for fetching Selected City Names.
    List<Main_citiesEntity> nameOfCities(int countryId, int stateId);
	

/*	-------------------------------------------------------------------------------------------------*/
	// based on main countryId fetching State List. 
List<Main_StatesEntity> fetchStateOnAddedCountry(int countryid);
	
	/*	-------------------------------------------------------------------------------------------------*/
    Tbl_CountriesEntity getCountryDetailsBasedOnCountryId(int CountryId);
	
	Tbl_StatesEntity getStateDetailsBasedOnStateId(int stateId);
	
	
	

}
