package com.rainier.services;

import com.rainier.beans.DU_RequestBean;
import com.rainier.businesslogic.DepartmentUnits;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Organization")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
public class HrmsDepartmentService {

	final static Logger logger = Logger.getLogger(HrmsDepartmentService.class);
	final static DepartmentUnits du = new DepartmentUnits();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDepartmentsDetails")
	public Response viewDepartment(@QueryParam(value = "roleId") int roleId, @QueryParam(value = "menuId") int menuId) {
		logger.info("Entered into viewDepartment() ");
		return du.getDepartmentList(roleId, menuId);

	}

	// INSERT
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/InsertDUData")
	@CrossOrigin
	public Response insertDepartment(DU_RequestBean duRequestBean) {
		try {
		// System.out.println("Request Got");
			logger.info("Entered into updateDepartment() and Request Got. ");
			return du.saveDUDetails(duRequestBean);
		} catch (Exception e) {
			logger.error("No Request Body.");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

	// Update
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateDepartmentDetails")
	@CrossOrigin
	public Response updateDepartment(DU_RequestBean duRequestBean) {
		try {
			// // // // System.out.println("Request Got");
			logger.info("Entered into updateDepartment() and Request Got. ");
			return du.updateDepartment(duRequestBean);
		} catch (Exception e) {
			logger.error("No Request Body.");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

	// Delete
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deleteDepartmentDetails")
	@CrossOrigin
	public Response Delete(@QueryParam(value = "id") int id, @QueryParam(value = "userId") int userId) {
		logger.info("Entered into Delete() and deleted. ");
		return du.deleteDepartment(id, userId);

	}

	// Department fetched service based on Business Units
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDUbasedOnBu")
	@CrossOrigin
	public Response fetchDUbasedBu(@QueryParam(value = "businessUnitId") Integer businessUnitId) {
		logger.info("Entered into fetchDUbasedBu()");
		return du.getBUDUInfo(businessUnitId);
	}

	// dept head lov.

	@Path("/getDeptHead")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response headOfDepartment() {
		logger.info("Entered into headOfDepartment()");
		return du.getDeptHead();
	}

}
