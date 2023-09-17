package com.rainier.services;

import com.rainier.beans.MainCitiesBean;
import com.rainier.beans.MainCountriesBean;
import com.rainier.beans.MainStatesBean;
import com.rainier.businesslogic.SiteConfig;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/SiteConfig")
// @CrossOrigin(maxAge = 3600, allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
public class HrmsSiteConfig {

	final static Logger logger = Logger.getLogger(HrmsSiteConfig.class);
	private static SiteConfig config = new SiteConfig();

	// Country and State Name.
	@Path("/Country&StateName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCountryStateName() {
		return config.getCountryState();
	}

	// Country and Country code Name.
	@Path("/Country&CountryCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCountryCode() {
		return config.getCountry_CountryCode();
	}

	// delete main Country.
	@Path("/deleteMainCountry")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteMainCountry(@QueryParam(value = "id") int id) {
		return config.deleteMainCountryList(id);
	}

	// delete main state.
	@Path("/deleteMainState")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteMainState(@QueryParam(value = "id") int id) {
		return config.deleteMainStateList(id);
	}

	// country , state, city view List.
	@Path("/Country_State_city_Name")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCountryStateCityView() {
		return config.viewCountryStateCity();
	}

	// delete main city.
	@Path("/deleteMainCity")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteMainCity(@QueryParam(value = "id") int id) {
		return config.deleteMainCityList(id);
	}

	// delete main timezone.
	@Path("/deleteMainTimezone")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteMainTimezone(@QueryParam(value = "id") int id, @QueryParam(value = "actualId") int actualId) {
		return config.deleteMainTimezoneList(id, actualId);
	}

	// UPDATE main Country.
	@Path("/updateMainCountry")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateMainCountry(MainCountriesBean bean) {
		return config.updateMainCountryList(bean);
	}

	// UPDATE main Country.
	@Path("/updateMainStatesView")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateMainStatesView(MainStatesBean beans) {
		return config.updateMainStatesView(beans);
	}

	@Path("/updateCityDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updatMainCity(MainCitiesBean bean) {
		return config.updateMainCity(bean);
	}

	// update Main cities with query.
	@Path("/UpdateMainCitiesIsactive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateCitiyMain(@QueryParam(value = "id") int id) {
		return config.updateMainCitiesList(id);
	}

	// update Main states with query.
	@Path("/UpdateMainStatesIsactive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateStatesMain(@QueryParam(value = "id") int id) {
		return config.updateMainStatesList(id);
	}

	// update Main countries with query.
	@Path("/UpdateMainCountriesIsactive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateCountriesMain(@QueryParam(value = "id") int id) {
		return config.updateMainCountriesList(id);
	}

	// update Main states with query.
	@Path("/UpdateMainZonesIsactive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateZonesMain(@QueryParam(value = "id") int id) {
		return config.updateMainZonesList(id);
	}

	// Complete MainTimezone Active and InActive List.
	@Path("/getCompleteMainTimezone")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCompleteMainZones() {
		return config.getZoneActiveInactiveList();
	}

	// delete main State. Completely
	@Path("/delmainStateCompleteRow")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response delMainState(@QueryParam(value = "id") int id, @QueryParam(value = "stateId") int stateId) {
		return config.delMainStates(id, stateId);
	}

	// delete main Country. Completely.
	@Path("/delmainCountryCompleteRow")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response delMainCountry(@QueryParam(value = "id") int id, @QueryParam(value = "countryId") int countryId) {
		return config.delMainCountry(id, countryId);
	}

	// delete main City. Completely.
	@Path("/delmainCityCompleteRow")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response delMainCity(@QueryParam(value = "id") int id, @QueryParam(value = "cityIds") int cityIds) {
		return config.delMainCity(id, cityIds);
	}

}
