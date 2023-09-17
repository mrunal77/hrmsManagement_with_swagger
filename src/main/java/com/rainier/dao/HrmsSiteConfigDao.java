package com.rainier.dao;

import com.rainier.entities.Main_CountriesEntity;
import com.rainier.entities.Main_StatesEntity;
import com.rainier.entities.Main_TimezoneEntity;
import com.rainier.entities.Main_citiesEntity;

import java.util.List;

public interface HrmsSiteConfigDao {

	// only country and country code List view.
    List<Object[]> getCountry_CountryCode();

	// country and state Name List.
    List<Object[]> getCountryStateList();

	// delete country.
    String deleteMainCountries(int id);

	// delete main State
    String deleteMainState(int id);

	// country, state and city view lisying.
    List<Object[]> getCountryStateCityView();

	// delete city from main city.
    String deleteMainCity(int id);

	// update main Country list.
    String updateMainStateList(Main_CountriesEntity countryEntity);

	// update main States List.
    boolean updateMainStatesView(Main_StatesEntity statesEntity);

	String updateMainCity(Main_citiesEntity cityEntity);

	// update main city isActive.
    String updateCityMain(int id);

	// update main States isActive.
    String updateStatesMain(int id);

	// update main States isActive.
    String updateCountriesMain(int id);

	// update main States isActive.
    String updateZoneTimeMain(int id);

	// timezone which is not active that List also.
    List<Main_TimezoneEntity> getMainTimezoneviewList();

	// delete main timezone.
    boolean deleteMainTimezone(int id, int actualId);

	// delete main state Table Complete.
    boolean delMainState(int id, int stateId);

	// delete main country Table row Complete.
    boolean delmainCountry(int id, int countryId);

	// delete main city Table row Complete.
    boolean delmainCity(int id, int cityIds);

}
