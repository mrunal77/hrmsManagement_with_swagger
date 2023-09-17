package com.rainier.services;

import com.rainier.beans.MainCitiesBean;
import com.rainier.beans.MainCountriesBean;
import com.rainier.beans.MainStatesBean;
import com.rainier.beans.MainTimezoneBean;
import com.rainier.businesslogic.LocationDetails;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/Location")
public class HrmsLocationService {

	private static final Logger logger = Logger.getLogger(HrmsLocationService.class);
	private static LocationDetails lcsc = new LocationDetails();

	// country service
	@Path("/GetListOfCountries")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	@GET
	public Response countrydata() {
		logger.info("entered into countrydata method of service class..");
		return lcsc.getCountryList();
	}

	// state service
	@Path("/GetListOfStates")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@CrossOrigin
	public Response stateData(@QueryParam(value = "countryId") int countryId) {

		logger.info("entered into stateData method of service class..");
		return lcsc.getStateList(countryId);

	}

	// city service
	@Path("/GetListOfCities")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@CrossOrigin
	public Response cityData(@QueryParam(value = "countryId") int countryId,
			@QueryParam(value = "stateId") int stateId) {
		logger.info("entered into cityData method of service class..");
		return lcsc.getCityList(countryId, stateId);
	}

	// TimeZone service
	@Path("/GetListOfTimeZones")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response timeZoneData() {
		logger.info("entered into timeZoneData method of service class..");
		return lcsc.getTimeZoneList();
	}

	// add country service.
	@Path("/AddCountry")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response addCountryData(MainCountriesBean countryBean) {
		logger.info("entered into addCountryData method of service class..");
		if (countryBean != null) {
			return lcsc.addCountryList(countryBean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// add timezone service.
	@Path("/AddTimezone")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response addTimezoneData(MainTimezoneBean timezonebean) {
		logger.info("entered into addTimezoneData method of service class..");
		if (timezonebean != null) {
			return lcsc.addTimezoneList(timezonebean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// add states service.
	@Path("/AddStates")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response addStateData(MainStatesBean statebean) {
		logger.info("entered into addStateData method of service class..");
		if (statebean != null) {
			return lcsc.addStatesList(statebean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// add Cities service
	@Path("/AddCities")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response addCitiesData(MainCitiesBean cityBean) {
		logger.info("entered into addCitiesData method of service class..");
		if (cityBean != null) {
			return lcsc.addCitiesList(cityBean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// fetching selected timezone list.
	@Path("/SelectedTimezoneList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response selectedTimezoneList() {
		logger.info("entered into selectedTimezoneList method of service class..");
		return lcsc.ListOfTimezoneNames();
	}

	// fetching selected Country list.
	@Path("/SelectedCountryList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response selectedCountryNameList() {
		logger.info("entered into selectedCountryNameList method of service class..");
		return lcsc.ListOfCountryNames();
	}

	// fetching selected States List ..
	@Path("/SelectedStatesList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin

	public Response SelectedStatesNameList(@QueryParam(value = "countryId") int countryId) {
		logger.info("entered into SelectedStatesNameList method of service class..");
		return lcsc.ListOfSatesNames(countryId);
	}

	// fetching selected Cities List ..
	@Path("/SelectedCityList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response SelectedCityNameList(@QueryParam(value = "countryId") int countryId,
			@QueryParam(value = "stateId") int stateId) {
		logger.info("entered into SelectedCityNameList method of service class..");
		return lcsc.listOfCitiesNames(countryId, stateId);
	}

	// fetching State based on the main country....

	@Path("/GetMainStatesList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response stateListofMaincountry(@QueryParam(value = "countryId") int countryId) {
		logger.info("entered into stateListofMaincountry method of service class..");
		return lcsc.getMainStateBasedOnMainCountry(countryId);
	}

}
