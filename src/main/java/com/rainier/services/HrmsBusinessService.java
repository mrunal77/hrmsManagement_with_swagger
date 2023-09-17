package com.rainier.services;

import com.rainier.beans.BU_RequestBean;
import com.rainier.beans.Main_businessunitsBean;
import com.rainier.businesslogic.BusinessUnits;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Organization")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
public class HrmsBusinessService {

	final static Logger logger = Logger.getLogger(HrmsBusinessService.class);
	final static BusinessUnits bu = new BusinessUnits();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBUDetails")
	@CrossOrigin
	public Response mainBusiness(@QueryParam(value = "roleId") int roleId, @QueryParam(value = "menuId") int menuId) {
		logger.info("Entered into mainBusiness() service");
		Main_businessunitsBean buResponseBean = bu.getBUDetails(roleId, menuId);
		logger.info("Exited from mainBusiness() service");
		return Response.status(Response.Status.OK).entity(buResponseBean).build();
	}

	@Path("/UpdateBUDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateBU(BU_RequestBean buRequestBean) {
		return bu.updateBusinessUnit(buRequestBean);
	}

	// Business Unit Insertion Service
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addBUDetails")
	@CrossOrigin
	public Response saveBusinessUnit(BU_RequestBean bean) {
		if (bean != null) {
			return bu.saveBUDetails(bean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// deleting busineess unit
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteBudetails")
	@CrossOrigin
	public Response deleteBusinessUnit(@QueryParam(value = "id") int id, @QueryParam(value = "userId") int userId) {
		return bu.deleteBUDetails(id, userId);
	}

}
