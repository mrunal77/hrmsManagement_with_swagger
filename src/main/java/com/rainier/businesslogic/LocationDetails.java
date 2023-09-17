package com.rainier.businesslogic;

import com.rainier.beans.*;
import com.rainier.dao.HrmsLocationDao;
import com.rainier.dao.Impl.HrmsLocationDaoImpl;
import com.rainier.entities.*;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class LocationDetails {
	private final static Logger logger = Logger.getLogger(LocationDetails.class);

	private static final HrmsLocationDao hlocation = new HrmsLocationDaoImpl();

	public Response getCountryList() {

		LocationResponseBean responseBean = new LocationResponseBean();
		// Tbl_CountriesEntity lstCountry = new Tbl_CountriesEntity();
		try {
			List<Tbl_CountriesEntity> countryTbl = hlocation.listOfCountries();
			ArrayList<CountriesBean> countryArrayList = new ArrayList<CountriesBean>();
			if (countryTbl.size() != 0) {
				for (Tbl_CountriesEntity tbl : countryTbl) {
					CountriesBean bean = new CountriesBean();
					bean.setId(tbl.getId());
					bean.setCountryCode(tbl.getCountryCode());
					bean.setCountryCode2(tbl.getCountryCode2());
					bean.setCountryName(tbl.getCountryName());

					countryArrayList.add(bean);

				}
				responseBean.setMessage("All country listed here");
				responseBean.setStatus(true);
				responseBean.setCountryList(countryArrayList);
			} else {
				responseBean.setMessage("country doesnt added !!");
				responseBean.setStatus(true);
				responseBean.setCountryList(countryArrayList);
			}
		} catch (Exception e) {
			logger.info("logging into catch clock of getCountryList method of business class:" + e);
			e.printStackTrace();
			responseBean.setMessage("Error while fetching countries details - " + e.getMessage());
			responseBean.setStatus(false);
			// return Response.status(Response.Status.OK).entity(responseBean).build();
		}
		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	// for state fetching ....
	public Response getStateList(int countryId) {
		logger.info("entered into getStateList of business class ");

		LocationResponseBean responseBean = new LocationResponseBean();
		// Tbl_StatesEntity lstStates = new Tbl_StatesEntity();
		// HrmsLocationDao hlt = new HrmsLocationDaoImpl();
		try {
			List<Tbl_StatesEntity> stateTbl = hlocation.listOfStates(countryId);
			ArrayList<StatesBean> stateArrayList = new ArrayList<StatesBean>();
			if (stateTbl.size() != 0) {
				for (Tbl_StatesEntity tbl : stateTbl) {
					StatesBean bean1 = new StatesBean();
					bean1.setId(tbl.getId());
					bean1.setCountryId(tbl.getCountryId());
					bean1.setIsactive(tbl.getIsactive());
					bean1.setStateName(tbl.getStateName());
					stateArrayList.add(bean1);

				}
				responseBean.setMessage("All States listed here");
				responseBean.setStatus(true);
				responseBean.setStateList(stateArrayList);
			} else {
				responseBean.setMessage("states Doesnt added!!");
				responseBean.setStatus(false);
				responseBean.setStateList(stateArrayList);
			}
		} catch (Exception e) {
			logger.info("logging,exception occured in etStateList of business class:::" + e);
			e.printStackTrace();
			responseBean.setMessage("Error while fetching Sates details - " + e.getMessage());
			responseBean.setStatus(false);

		}
		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	// for city fetching ....

	public Response getCityList(int countryId, int stateId) {
		logger.info("entered into getCityList of business class ");

		// CitiesResponseBean responseBean = new CitiesResponseBean();
		LocationResponseBean responseBean = new LocationResponseBean();
		// Tbl_CitiesEntity lstcities = new Tbl_CitiesEntity();
		// HrmsLocationDao hlt = new HrmsLocationDaoImpl();
		try {
			List<Tbl_CitiesEntity> cityTbl = hlocation.listOfCities(countryId, stateId);

			ArrayList<CitiesBean> cityArrayList = new ArrayList<CitiesBean>();
			if (cityTbl.size() != 0) {
				for (Tbl_CitiesEntity tbl : cityTbl) {
					CitiesBean cityBean = new CitiesBean();
					cityBean.setId(tbl.getId());
					cityBean.setCityName(tbl.getCityName());
					cityBean.setIsActive(tbl.getIsActive());
					cityBean.setStateId(tbl.getStateId());

					cityArrayList.add(cityBean);
				}

				responseBean.setMessage("All cities retrived from table... !!!");
				responseBean.setStatus(true);
				responseBean.setCitiesList(cityTbl);
			} else {
				responseBean.setMessage("city not Added till now or look in other State!!");
				responseBean.setStatus(false);
				responseBean.setCitiesList(cityTbl);
			}

		} catch (Exception e) {
			logger.info("logging,exception occured in getCityList of business class:::" + e);

			e.printStackTrace();
			responseBean.setMessage("Error while fetching Cities details - " + e.getMessage());
			responseBean.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(responseBean).build();

	}

	// for timezone fetching....
	public Response getTimeZoneList() {
		logger.info("entered into getTimeZoneList of business class ");

		LocationResponseBean responseBean = new LocationResponseBean();
		// Tbl_TimezonesEntity timeEntity = new Tbl_TimezonesEntity();
		// HrmsLocationDao hlt = new HrmsLocationDaoImpl();
		List<Tbl_TimezonesEntity> timezoneTbl = hlocation.listOfTimezones();
		try {
			ArrayList<TimeZoneRequestBean> timezoneAl = new ArrayList<TimeZoneRequestBean>();
			if (timezoneTbl.size() != 0) {
				for (Tbl_TimezonesEntity timezone : timezoneTbl) {
					TimeZoneRequestBean requestBean = new TimeZoneRequestBean();
					requestBean.setId(timezone.getId());
					requestBean.setIsActive(timezone.getIsActive());
					requestBean.setOffsetValue(timezone.getOffsetValue());
					requestBean.setTimeZone(timezone.getTimeZone());
					requestBean.setTimeZoneAbbr(timezone.getTimeZoneAbbr());
					timezoneAl.add(requestBean);

				}
				responseBean.setMessage("TimeZone list retrieved.");
				responseBean.setStatus(true);
				responseBean.setTimezoneList(timezoneAl);
			} else {
				responseBean.setMessage("TimeZone list not retrieved.");
				responseBean.setStatus(false);

			}
		} catch (Exception e) {
			logger.info("logging,exception occured in getTimeZoneList of business class:::" + e);

			e.printStackTrace();
			responseBean.setMessage("Error while fetching TimeZone details - " + e.getMessage());
			responseBean.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	// for Adding country list to main_country table logic.
	public Response addCountryList(MainCountriesBean countryBean) {
		LocationResponseBean addCountryResponseBean = new LocationResponseBean();
		Main_CountriesEntity countryEntity = new Main_CountriesEntity();
		//// HrmsLocationDao dao = new HrmsLocationDaoImpl();

		try {
			BeanUtils.copyProperties(countryEntity, countryBean);

			countryEntity.setCountryIdOrg(countryBean.getId());
			countryEntity.setIsactive(1);
			// Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			countryEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			countryEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			/*
			 * countryEntity.setCreatedBy(countryBean.getCreatedBy());
			 * countryEntity.setModifiedBy(countryBean.getModifiedBy());
			 * countryEntity.setCreatedBy(rbean.getUserId());
			 * countryEntity.setModifiedBy(rbean.getUserId());
			 */

			/*
			 * String inserted = dao.addCountry(countryEntity); //
			 * System.out.println(inserted);
			 */

			if (hlocation.addCountry(countryEntity) != null) {

				addCountryResponseBean.setMessage("Country Already Exist.");
				addCountryResponseBean.setStatus(true);

			} else {
				addCountryResponseBean.setMessage("Country Added Suessfully.");
				addCountryResponseBean.setStatus(true);
			}
			return Response.status(Response.Status.OK).entity(addCountryResponseBean).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			addCountryResponseBean.setMessage("Error while saving details - " + e.getMessage());
			addCountryResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addCountryResponseBean).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addCountryResponseBean.setMessage("Error while saving country details - " + e.getMessage());
			addCountryResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addCountryResponseBean).build();

		}

	}

	// for Adding Timezone list to main_timezone table logic.
	public Response addTimezoneList(MainTimezoneBean timezoneBean) {

		LocationResponseBean addTimezoneresponseBean = new LocationResponseBean();
		Main_TimezoneEntity timezoneEntity = new Main_TimezoneEntity();
		//// HrmsLocationDao dao = new HrmsLocationDaoImpl();

		try {

			BeanUtils.copyProperties(timezoneEntity, timezoneBean);
			timezoneEntity.setActualId(timezoneBean.getId());
			timezoneEntity.setIsactive(1);
			// Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			timezoneEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			timezoneEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			if (hlocation.addTimezone(timezoneEntity) != null) {

				addTimezoneresponseBean.setMessage("Timezone Already Exists.");
				addTimezoneresponseBean.setStatus(true);

			} else {
				addTimezoneresponseBean.setMessage("Timezone added Successfully...");
				addTimezoneresponseBean.setStatus(true);

			}

			return Response.status(Response.Status.OK).entity(addTimezoneresponseBean).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			addTimezoneresponseBean.setMessage("Error while saving details - " + e.getMessage());
			addTimezoneresponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addTimezoneresponseBean).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addTimezoneresponseBean.setMessage("Error while saving timezone  details - " + e.getMessage());
			addTimezoneresponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addTimezoneresponseBean).build();

		}

	}

	// for Adding States list to main_states table logic.
	public Response addStatesList(MainStatesBean stateBean) {
		LocationResponseBean addStaesResponseBean = new LocationResponseBean();
		Main_StatesEntity stateEntity = new Main_StatesEntity();
		//// HrmsLocationDao dao = new HrmsLocationDaoImpl();
		try {
			BeanUtils.copyProperties(stateEntity, stateBean);
			stateEntity.setStateIdOrg(stateBean.getId());
			stateEntity.setIsactive(1);

			// Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			stateEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			stateEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			/*
			 * String inserted = dao.addStates(stateEntity); //
			 * System.out.println(inserted);
			 */
			if (hlocation.addStates(stateEntity) != null) {
				addStaesResponseBean.setMessage("States Already Exists.");
				addStaesResponseBean.setStatus(true);
			} else {
				addStaesResponseBean.setMessage("States Added Successfully.");
				addStaesResponseBean.setStatus(true);
			}

			return Response.status(Response.Status.OK).entity(addStaesResponseBean).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			addStaesResponseBean.setMessage("Error while saving details - " + e.getMessage());
			addStaesResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addStaesResponseBean).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addStaesResponseBean.setMessage("Error while saving States  details - " + e.getMessage());
			addStaesResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addStaesResponseBean).build();

		}

	}

	// for Adding cities list to main_cities table logic.
	public Response addCitiesList(MainCitiesBean cityBean) {
		LocationResponseBean addCityResponseBean = new LocationResponseBean();
		Main_citiesEntity cityEntity = new Main_citiesEntity();
		//// HrmsLocationDao dao = new HrmsLocationDaoImpl();
		try {
			BeanUtils.copyProperties(cityEntity, cityBean);
			// Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			cityEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			cityEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			cityEntity.setCityIdOrg(cityBean.getId());
			cityEntity.setCountryId(cityBean.getCountryId());
			cityEntity.setStateId(cityBean.getStateId());
			cityEntity.setIsactive(1);
			/*
			 * String inserted = dao.addCities(cityEntity); // System.out.println(inserted);
			 */
			if (hlocation.addCities(cityEntity) == null) {
				addCityResponseBean.setMessage("Cities Added Successfully.");
				addCityResponseBean.setStatus(true);
			} else {
				addCityResponseBean.setMessage("Cities Already Exist.");
				addCityResponseBean.setStatus(true);
			}

			return Response.status(Response.Status.OK).entity(addCityResponseBean).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			addCityResponseBean.setMessage("Error while saving details - " + e.getMessage());
			addCityResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addCityResponseBean).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addCityResponseBean.setMessage("Error while saving States  details - " + e.getMessage());
			addCityResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(addCityResponseBean).build();

		}

	}

	// for fetching timezone name.

	public Response ListOfTimezoneNames() {
		LocationResponseBean FetchedResponseBean = new LocationResponseBean();
		// Main_TimezoneEntity timezoneEntity = new Main_TimezoneEntity();
		//// HrmsLocationDao dao = new HrmsLocationDaoImpl();
		List<Main_TimezoneEntity> zoneTbl = hlocation.nameOfTimezone();
		try {
			ArrayList<MainTimezoneBean> zoneAl = new ArrayList<MainTimezoneBean>();
			if (zoneTbl.size() != 0) {
				for (Main_TimezoneEntity timezone : zoneTbl) {
					MainTimezoneBean requestBean = new MainTimezoneBean();
					requestBean.setId(timezone.getId());
					requestBean.setActualId(timezone.getActualId());
					requestBean.setTimeZone(timezone.getTimeZone());
					requestBean.setTimeZoneAbbr(timezone.getTimeZoneAbbr());
					zoneAl.add(requestBean);
				}
				FetchedResponseBean.setMessage("TimeZone selected  list retrieved.");
				FetchedResponseBean.setStatus(true);
				FetchedResponseBean.setSelectedTimezoneNames(zoneAl);
			} else {
				FetchedResponseBean.setMessage("TimeZone selected list not retrieved.");
				FetchedResponseBean.setStatus(false);
			}
		} catch (Exception e) {
			logger.info("logging,exception occured in ListOfTimezoneNames of business class:::" + e);
			e.printStackTrace();
			FetchedResponseBean.setMessage("Error while fetching TimeZone details - " + e.getMessage());
			FetchedResponseBean.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(FetchedResponseBean).build();
	}

	// for Fetching Added Country List.
	public Response ListOfCountryNames() {
		LocationResponseBean countryResponseBean = new LocationResponseBean();
		// Main_CountriesEntity countryEntity = new Main_CountriesEntity();
		//// HrmsLocationDao dao = new HrmsLocationDaoImpl();
		List<Main_CountriesEntity> countryAddedListTbl = hlocation.nameOfCountry();
		try {
			ArrayList<MainCountriesBean> mainCountryAl = new ArrayList<MainCountriesBean>();
			if (countryAddedListTbl.size() != 0) {
				for (Main_CountriesEntity selected : countryAddedListTbl) {

					MainCountriesBean mainCountryRequestBean = new MainCountriesBean();
					mainCountryRequestBean.setId(selected.getId());
					mainCountryRequestBean.setCountryCode(selected.getCountryCode());
					mainCountryRequestBean.setCountryIdOrg(selected.getCountryIdOrg());
					mainCountryRequestBean.setCountryName(selected.getCountryName());

					mainCountryAl.add(mainCountryRequestBean);
				}

				countryResponseBean.setMessage("country selected  list retrieved.");
				countryResponseBean.setStatus(true);
				countryResponseBean.setSelectedCountryNames(mainCountryAl);

			} else {
				countryResponseBean.setMessage("country selected  list not  retrieved.");
				countryResponseBean.setStatus(false);
			}
		} catch (Exception e) {
			logger.info("logging,exception occured in ListOfCountryNames of business class:::" + e);
			e.printStackTrace();
			countryResponseBean.setMessage("Error while fetching Country details - " + e.getMessage());
			countryResponseBean.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(countryResponseBean).build();
	}

	// for Fetching Added States List.
	public Response ListOfSatesNames(int countryId) {
		LocationResponseBean addedStateResponseBean = new LocationResponseBean();
		// Main_StatesEntity stateEntity = new Main_StatesEntity();
		// HrmsLocationDao dao = new HrmsLocationDaoImpl();
		List<Main_StatesEntity> statesAddedListTbl = hlocation.nameOfStates(countryId);
		try {
			ArrayList<MainStatesBean> mainSattesAl = new ArrayList<MainStatesBean>();
			if (statesAddedListTbl.size() != 0) {
				for (Main_StatesEntity selected : statesAddedListTbl) {
					MainStatesBean addStateRequestBean = new MainStatesBean();
					addStateRequestBean.setId(selected.getId());
					addStateRequestBean.setCountryId(selected.getCountryId());
					addStateRequestBean.setStateName(selected.getStateName());
					addStateRequestBean.setStateIdOrg(selected.getStateIdOrg());

					mainSattesAl.add(addStateRequestBean);

				}
				addedStateResponseBean.setMessage("Sates selected  list retrieved.");
				addedStateResponseBean.setStatus(true);
				addedStateResponseBean.setSelectedStateNames(mainSattesAl);

			} else {
				addedStateResponseBean.setMessage("Sates selected  list not  retrieved.");
				addedStateResponseBean.setStatus(false);

			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("logging,exception occured in ListOfSatesNames of business class:::" + e);
			e.printStackTrace();
			addedStateResponseBean.setMessage("Error while fetching States details - " + e.getMessage());
			addedStateResponseBean.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(addedStateResponseBean).build();
	}

	// for Fetching Added Cities List.
	public Response listOfCitiesNames(int countryId, int stateId) {
		LocationResponseBean addedCityResponseBean = new LocationResponseBean();
		// Main_citiesEntity cityEntity = new Main_citiesEntity();
		// HrmsLocationDao dao = new HrmsLocationDaoImpl();
		List<Main_citiesEntity> addedCityList = hlocation.nameOfCities(countryId, stateId);
		try {
			ArrayList<MainCitiesBean> addedCityAl = new ArrayList<MainCitiesBean>();
			if (addedCityList.size() != 0) {
				for (Main_citiesEntity selected : addedCityList) {
					MainCitiesBean addedCityRequestBean = new MainCitiesBean();
					addedCityRequestBean.setId(selected.getId());
					addedCityRequestBean.setCityName(selected.getCityName());
					addedCityRequestBean.setCityIdOrg(selected.getCityIdOrg());
					addedCityRequestBean.setStateId(selected.getStateId());
					addedCityRequestBean.setCountryId(selected.getCountryId());

					addedCityAl.add(addedCityRequestBean);

				}
				addedCityResponseBean.setMessage("Cities selected  list retrieved.");
				addedCityResponseBean.setStatus(true);
				addedCityResponseBean.setSelectedCityNames(addedCityAl);
			} else {
				addedCityResponseBean.setMessage("Cities selected  list not retrieved.");
				addedCityResponseBean.setStatus(false);
			}
		} catch (Exception e) {
			logger.info("logging,exception occured in listOfCitiesNames of business class:::" + e);
			e.printStackTrace();
			addedCityResponseBean.setMessage("Error while fetching Cities details - " + e.getMessage());
			addedCityResponseBean.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(addedCityResponseBean).build();
	}

	// for Main state list fetching based on the main_country id.
	public Response getMainStateBasedOnMainCountry(int countryId) {
		LocationResponseBean mainStateResponseBean = new LocationResponseBean();
		// Main_StatesEntity stateEntity = new Main_StatesEntity();
		// HrmsLocationDao dao = new HrmsLocationDaoImpl();
		List<Main_StatesEntity> basedOnMainCountryIdList = hlocation.fetchStateOnAddedCountry(countryId);
		try {
			ArrayList<MainStatesBean> stateCountryIdAl = new ArrayList<MainStatesBean>();
			if (basedOnMainCountryIdList.size() != 0) {
				for (Main_StatesEntity basedOn : basedOnMainCountryIdList) {
					MainStatesBean stateCountryIdRequestBean = new MainStatesBean();
					stateCountryIdRequestBean.setId(basedOn.getId());
					stateCountryIdRequestBean.setCountryId(basedOn.getCountryId());
					stateCountryIdRequestBean.setStateName(basedOn.getStateName());
					stateCountryIdRequestBean.setStateIdOrg(basedOn.getStateIdOrg());

					stateCountryIdAl.add(stateCountryIdRequestBean);

				}
				mainStateResponseBean.setMessage("States Listed based on the main country list");
				mainStateResponseBean.setStatus(true);
				mainStateResponseBean.setBasedOnCountry_StateList(stateCountryIdAl);

			} else {
				mainStateResponseBean
						.setMessage("States Listed based on the main country list SomeThing went wrong ...");
				mainStateResponseBean.setStatus(true);
			}

		} catch (Exception e) {
			logger.info("logging,exception occured in getMainStateBasedOnMainCountry of business class:::" + e);
			e.printStackTrace();
			mainStateResponseBean.setMessage("Error while fetching States details - " + e.getMessage());
			mainStateResponseBean.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(mainStateResponseBean).build();

	}
}
