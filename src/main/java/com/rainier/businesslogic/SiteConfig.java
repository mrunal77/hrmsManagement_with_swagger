package com.rainier.businesslogic;

import com.rainier.beans.*;
import com.rainier.dao.HrmsSiteConfigDao;
import com.rainier.dao.Impl.HrmsSiteConfigDaoImpl;
import com.rainier.entities.Main_CountriesEntity;
import com.rainier.entities.Main_StatesEntity;
import com.rainier.entities.Main_TimezoneEntity;
import com.rainier.entities.Main_citiesEntity;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SiteConfig {

	private final static Logger logger = Logger.getLogger(SiteConfig.class);
	private static final HrmsSiteConfigDao dao = new HrmsSiteConfigDaoImpl();

	public Response getCountryState() {
		logger.info("Entered into getCountryState()");
		LocationResponseBean cs = new LocationResponseBean();
		ArrayList<MainStatesBean> siteAl = new ArrayList<MainStatesBean>();
		List<Object[]> countryStateList = dao.getCountryStateList();

		if (countryStateList.size() != 0) {
			for (Object[] row : countryStateList) {
				MainStatesBean bean = new MainStatesBean();
				bean.setCountryId(objToInteger(row[1]));
				bean.setCountryName(objToString(row[0]));
				bean.setStateName(objToString(row[2]));
				bean.setStateIdOrg(objToInteger(row[3]));
				bean.setId(objToInteger(row[4]));
				siteAl.add(bean);
			}
			cs.setMessage("Country and State List Name Retrived.");
			cs.setStatus(true);
			cs.setCountryStateList(siteAl);
		} else {
			cs.setMessage("Error while retrieving countruy State name");
			cs.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(cs).build();

	}

	/*
	 * Converts Object DataType to String Input - Object returns String If Null
	 * returns empty string
	 */
	private String objToString(Object obj) {

		if (obj != null)
			return obj.toString();

		return "";
	}

	/*
	 * Converts Object DataType to Integer Input - Object returns Integer If Null
	 * returns 0
	 */
	private Integer objToInteger(Object obj) {

		if (obj != null)
			return Integer.parseInt(obj.toString());

		return 0;
	}

	// country and Country code which is also not active.

	public Response getCountry_CountryCode() {
		logger.info("Entered into getCountryState()");
		LocationResponseBean cs = new LocationResponseBean();

		ArrayList<MainCountriesBean> siteAl = new ArrayList<MainCountriesBean>();
		List<Object[]> countryCodeList = dao.getCountry_CountryCode();

		if (countryCodeList.size() != 0) {
			for (Object[] row : countryCodeList) {
				MainCountriesBean bean = new MainCountriesBean();

				bean.setId(objToInteger(row[0]));
				bean.setCountryName(objToString(row[1]));
				bean.setCountryCode(objToString(row[2]));
				bean.setCountryIdOrg(objToInteger(row[3]));

				siteAl.add(bean);
			}

			cs.setMessage("Country & country Code  List Name Retrived.");
			cs.setStatus(true);
			cs.setCountryStateList(siteAl);
		} else {
			cs.setMessage("Error while retrieving countruy State name");
			cs.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(cs).build();

	}

	// delete main country.
	public Response deleteMainCountryList(int id) {
		EntityStatusBean response = new EntityStatusBean();
		try {
			dao.deleteMainCountries(id);
			response.setMessage("Deleted Successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (Exception e) {
			logger.info("catch block of deleteMainCountryList of business class::" + e);

			response.setMessage("Error while Deleting.");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// delete main state.
	public Response deleteMainStateList(int id) {
		EntityStatusBean response = new EntityStatusBean();
		try {
			dao.deleteMainState(id);
			response.setMessage("Deleted Successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (Exception e) {
			logger.info("catch block of deleteMainStateList of business class::" + e);

			response.setMessage("Error while Deleting.");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}
	// country state city list view

	public Response viewCountryStateCity() {
		LocationResponseBean responseBean = new LocationResponseBean();
		logger.info("Entered into viewCountryStateCity()");
		ArrayList<MainCitiesBean> cityAl = new ArrayList<MainCitiesBean>();
		List<Object[]> listOfCountryStateCity = dao.getCountryStateCityView();
		if (listOfCountryStateCity.size() != 0) {
			for (Object[] row : listOfCountryStateCity) {
				MainCitiesBean bean = new MainCitiesBean();
				bean.setId(objToInteger(row[6]));
				bean.setCountryId(objToInteger(row[1]));
				bean.setCountryName(objToString(row[0]));
				bean.setState_id_org(objToInteger(row[2]));
				bean.setStateName(objToString(row[3]));
				bean.setCityIdOrg(objToInteger(row[5]));
				bean.setCityName(objToString(row[4]));
				cityAl.add(bean);
			}
			responseBean.setMessage("Country State and View  list.");
			responseBean.setStatus(true);
			responseBean.setCountry_State_city_viewList(cityAl);
		} else {
			responseBean.setMessage("Country , State and View List not Retrived.");
			responseBean.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	// delete main city.
	public Response deleteMainCityList(int id) {
		EntityStatusBean response = new EntityStatusBean();
		try {
			dao.deleteMainCity(id);
			response.setMessage("Deleted Successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (Exception e) {
			logger.info("catch block of deleteMainCityList of business class::" + e);

			response.setMessage("Error while Deleting .");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// update Main Countrry List.

	public Response updateMainCountryList(MainCountriesBean countryBean) {
		LocationResponseBean responseBean = new LocationResponseBean();
		Main_CountriesEntity countryEntity = new Main_CountriesEntity();

		try {
			BeanUtils.copyProperties(countryEntity, countryBean);

			// countryEntity.setCountryIdOrg(countryBean.getId());
			countryEntity.setCountryIdOrg(countryBean.getCountryIdOrg());

			countryEntity.setIsactive(1);
			// Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			countryEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			countryEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			dao.updateMainStateList(countryEntity);
			// System.out.println(updated);
			responseBean.setMessage("Updated Successfully");
			responseBean.setStatus(true);
			return Response.status(Response.Status.OK).entity(responseBean).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			responseBean.setMessage("Error while saving details - " + e.getMessage());
			responseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseBean.setMessage("Error while saving country details - " + e.getMessage());
			responseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();

		}

	}

	// update main states list logic.
	/*
	 * public Response updateMainStatesViews(MainStatesBean statesBean) {
	 * LocationResponseBean responseBean = new LocationResponseBean();
	 * Main_StatesEntity stateEntity = new Main_StatesEntity(); HrmsSiteConfigDao
	 * dao= new HrmsSiteConfigDaoImpl(); try { BeanUtils.copyProperties(stateEntity,
	 * statesBean); stateEntity.setId(statesBean.getId());
	 * //stateEntity.setStateIdOrg(statesBean.getId());
	 * stateEntity.setStateIdOrg(statesBean.getStateIdOrg());
	 * stateEntity.setIsactive(1);
	 * 
	 * Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
	 * Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
	 * stateEntity.setCreatedDate(new
	 * HrmsGetDateAndTime().GetUTCdatetimeAsString());
	 * stateEntity.setModifiedDate(new
	 * HrmsGetDateAndTime().GetUTCdatetimeAsString()); String updated
	 * =dao.updateMainStatesView(stateEntity); // System.out.println(updated);
	 * 
	 * responseBean.setMessage("Updated Successfully");
	 * responseBean.setStatus(true);
	 * 
	 * return Response.status(Response.Status.OK).entity(responseBean).build(); }
	 * catch (IllegalAccessException e) { e.printStackTrace();
	 * responseBean.setMessage("Error while saving details - " + e.getMessage());
	 * responseBean.setStatus(false); return
	 * Response.status(Response.Status.OK).entity(responseBean).build(); } catch
	 * (InvocationTargetException e) { // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * responseBean.setMessage("Error while saving country details - " +
	 * e.getMessage()); responseBean.setStatus(false); return
	 * Response.status(Response.Status.OK).entity(responseBean).build();
	 * 
	 * }
	 * 
	 * }
	 */

	public Response updateMainStatesView(MainStatesBean stateBean) {
		LocationResponseBean responseBean = new LocationResponseBean();
		Main_StatesEntity stateEntity = new Main_StatesEntity();
		//
		try {
			BeanUtils.copyProperties(stateEntity, stateBean);

			stateEntity.setStateIdOrg(stateBean.getStateIdOrg());
			stateEntity.setCountryId(stateBean.getCountryId());
			stateEntity.setIsactive(1);

			stateEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			stateEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			dao.updateMainStatesView(stateEntity);
			// System.out.println("updatyed Successfully");
			logger.info("updated");
			responseBean.setMessage("Updated Successfully");
			responseBean.setStatus(true);
			return Response.status(Response.Status.OK).entity(responseBean).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			responseBean.setMessage("Error while saving details - " + e.getMessage());
			responseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseBean.setMessage("Error while saving country details - " + e.getMessage());
			responseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();

		}

	}

	public Response updateMainCity(MainCitiesBean bean) {
		LocationResponseBean response = new LocationResponseBean();
		Main_citiesEntity city = new Main_citiesEntity();
		//
		try {
			BeanUtils.copyProperties(city, bean);

			city.setIsactive(1);
			// city.setCityIdOrg(bean.getCityIdOrg());
			city.setCountryId(bean.getCountryId());

			city.setCityName(bean.getCityName());
			city.setCityIdOrg(bean.getCityIdOrg());
			city.setId(bean.getId());
			city.setStateId(bean.getStateId());

			city.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			city.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			dao.updateMainCity(city);
			response.setMessage("Success updated.");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while saving details - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			response.setMessage("Error while saving country details - " + e2.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// update main City isActive
	public Response updateMainCitiesList(int id) {
		EntityStatusBean response = new EntityStatusBean();

		try {

			dao.updateCityMain(id);
			response.setMessage("Updated Successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (Exception e) {
			logger.info("catch block of updateMainCitiesList of business class::" + e);

			response.setMessage("Error while updating.");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// update main State isActive

	public Response updateMainStatesList(int id) {
		EntityStatusBean response = new EntityStatusBean();

		try {

			dao.updateStatesMain(id);
			response.setMessage("Updated Successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (Exception e) {
			logger.info("catch block of updateMainStatesList of business class::" + e);

			response.setMessage("Error while updating.");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// update main Country isActive

	public Response updateMainCountriesList(int id) {
		EntityStatusBean response = new EntityStatusBean();

		try {

			dao.updateCountriesMain(id);
			response.setMessage("Updated Successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (Exception e) {
			logger.info("catch block of updateMainCountriesList of business class::" + e);

			response.setMessage("Error while updating.");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// update main Zone isActive

	public Response updateMainZonesList(int id) {
		EntityStatusBean response = new EntityStatusBean();

		try {

			dao.updateZoneTimeMain(id);
			response.setMessage("Updated Successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (Exception e) {
			logger.info("catch block of updateMainZonesList of business class::" + e);

			response.setMessage("Error while updating.");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// timezone which is active and inactive complete list.
	public Response getZoneActiveInactiveList() {
		LocationResponseBean FetchedResponseBean = new LocationResponseBean();

		// Main_TimezoneEntity entity = new Main_TimezoneEntity();
		List<Main_TimezoneEntity> tblCompleteZone = dao.getMainTimezoneviewList();
		try {
			ArrayList<MainTimezoneBean> tblZoneAl = new ArrayList<>();
			if (tblCompleteZone.size() != 0) {
				for (Main_TimezoneEntity site : tblCompleteZone) {

					MainTimezoneBean bean = new MainTimezoneBean();

					bean.setId(site.getId());
					bean.setActualId(site.getActualId());
					bean.setDescription(site.getDescription());
					bean.setTimeZone(site.getTimeZone());
					bean.setTimeZoneAbbr(site.getTimeZoneAbbr());
					bean.setIsActive(site.getIsactive());

					tblZoneAl.add(bean);

				}
				FetchedResponseBean.setMessage("Complete Main Timezone List Retrived.");
				FetchedResponseBean.setStatus(true);
				FetchedResponseBean.setSelectedTimezoneNames(tblZoneAl);
			} else {
				FetchedResponseBean.setMessage("Complete Main Timezone List Not Retrived.");
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

	///

	// Delete Completely.
	public Response delMainStates(int id, int stateId) {
		LocationResponseBean response = new LocationResponseBean();
		// Main_StatesEntity stateEntity = new Main_StatesEntity();

		try {

			if (dao.delMainState(id, stateId) == true) {

				response.setMessage("Deleted Successfully.");
				response.setStatus(true);
				return Response.status(Response.Status.OK).entity(response).build();
			} else {
				response.setMessage("State Can't Delete, It is in Use With Some BusinessUnit OR Departments.");
				response.setStatus(false);
				return Response.status(Response.Status.OK).entity(response).build();
			}

		} catch (Exception e) {

			// System.out.println("this is exception" + e);
			response.setMessage("Failed to delete record - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// deleting Complete row for maon country.
	public Response delMainCountry(int id, int countryId) {
		LocationResponseBean response = new LocationResponseBean();
		// Main_CountriesEntity countryEntity = new Main_CountriesEntity();

		try {

			if (dao.delmainCountry(id, countryId)) {

				response.setMessage("Deleted Main Country Successfully.");
				response.setStatus(true);
				return Response.status(Response.Status.OK).entity(response).build();
			} else {
				response.setMessage("Country Can't Delete, It is in Use With Some BusinessUnit OR Departments.");
				response.setStatus(false);
				return Response.status(Response.Status.OK).entity(response).build();
			}
		} catch (Exception e) {
			// System.out.println("this is exception" + e);
			response.setMessage("Failed to delete record - " + e.getMessage());
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// deleting Complete row for maon city.
	public Response delMainCity(int id, int cityIds) {
		LocationResponseBean response = new LocationResponseBean();
		// Main_citiesEntity cityEntity = new Main_citiesEntity();
		try {
			if (dao.delmainCity(id, cityIds) == true) {

				response.setMessage("Deleted Main City Successfully.");
				response.setStatus(true);
				return Response.status(Response.Status.OK).entity(response).build();
			} else {
				response.setMessage("City Can't Delete, It is in Use With Some BusinessUnit OR Departments.");
				response.setStatus(false);
				return Response.status(Response.Status.OK).entity(response).build();
			}
		} catch (Exception e) {
			// System.out.println("this is exception" + e);
			response.setMessage("Failed to delete record - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// delete main Timezone.
	public Response deleteMainTimezoneList(int id, int actualId) {
		EntityStatusBean response = new EntityStatusBean();

		try {

			/* dao.deleteMainTimezone(id); */

			boolean result = dao.deleteMainTimezone(id, actualId);

			if (result == false) {

				response.setMessage("TimeZone Can't Delete, It is in Use With Some BusinessUnit OR Departments.");
				response.setStatus(false);
				return Response.status(Response.Status.OK).entity(response).build();

			} else {
				response.setMessage("Deleted Successfully.");
				response.setStatus(true);
				return Response.status(Response.Status.OK).entity(response).build();

			}

		} catch (Exception e) {
			logger.info("catch block of deleteMainTimezoneList of business class::" + e);

			response.setMessage("Error while Deleting .");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}
}
