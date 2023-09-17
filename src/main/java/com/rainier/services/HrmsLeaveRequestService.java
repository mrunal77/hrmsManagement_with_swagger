package com.rainier.services;

import com.rainier.beans.EmployeeLeaveTypeBean;
import com.rainier.beans.EmployeeRequestBean;
import com.rainier.beans.LeaveRequestBean;
import com.rainier.beans.UpdateEmployeeLeaveDetails;
import com.rainier.beans.UpdateLeaveRequest;
import com.rainier.businesslogic.LeaveDetails;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("leaves")
public class HrmsLeaveRequestService {

	final static Logger logger = Logger.getLogger(HrmsLeaveRequestService.class);
	private static LeaveDetails crud = new LeaveDetails();

	// My Leaves , Employee Leaves
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getLeaveDetails")
	public Response leaveDetails(@QueryParam(value = "userid") int uId,
			@QueryParam(value = "leavestatus") String leavestatus, @QueryParam(value = "view") String view) {
		logger.info("entered into leaveDetails method of service class...");
		return crud.getLeavesDetails(uId, leavestatus, view);
	}

	// Leave Management Option
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getLeaveManagementOptions")
	public Response leaveManagementOptions() {
		logger.info("entered into leaveManagementOptions method of service class...");
		return crud.getLeaveManagementOptions();
	}

	// add no of days for each employee.
	@Path("/addNoOFDaysForEachUserID")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveEmployeeLeave(EmployeeLeaveTypeBean bean) {
		return crud.saveEmployeeLeaveData(bean);

	}
	// 1. update Employee leave Summary details.

	@Path("/UpdateEmployeeLeave")  
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateEmployeeLeaveSummaryDetails(UpdateLeaveRequest updateBean) {
		return crud.updateAllLeaveSummary(updateBean);
	}

	// 2. update Employee leave Summary
	@Path("/UpdateEmpLeave")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateEmpLeave(UpdateEmployeeLeaveDetails empLeaveSummary) {
		if (empLeaveSummary != null) {
			return crud.updateEmpLeaveStatus(empLeaveSummary);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

}
