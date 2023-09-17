package com.rainier.services;

import com.rainier.beans.BgEmpProfessionalReferenceBean;
import com.rainier.beans.HolidayNamesBean;
import com.rainier.beans.LeaveRequestBean;
import com.rainier.beans.TimeSheet;
import com.rainier.beans.TimeSheetAddCalWeekMonthBean;
import com.rainier.beans.TimeSheetDailyWork;
import com.rainier.businesslogic.SelfServices;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// @CrossOrigin(maxAge = 3600, allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/Self")
public class HrmsSelfService {
	final static Logger logger = Logger.getLogger(HrmsSelfService.class);
	private static SelfServices ss = new SelfServices();

	@Path("/ApplyLeaveRequest")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, @QueryParam(value = "roleId") int roleId,
			@QueryParam(value = "menuId") int menuId) {
		logger.info("Entered into getMyLeaveRequest() ");
		logger.error("Existed from getMyLeaveRequest() ");
		return ss.saveLeaveRequest(leaverequestBean, roleId, menuId);
	}

	// delete my leave details.
	@Path("/DeleteMyLeave")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteMyLeave(@QueryParam(value = "id") int id) {
		logger.info("Entered into deleteMyLeave() ");
		logger.error("Existed from deleteMyLeave() ");
		return ss.deleteMyLeaveDetails(id);
	}

	// Applied Leave History Data.
	@Path("/appliedLeavesHistory")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchAppliedLeaveData(@QueryParam(value = "userId") int userId,@QueryParam("roleId")int roleId,@QueryParam("menuId")int menuId ) {
		logger.info("Entered into fetchAppliedLeaveData() ");
		logger.error("Existed from fetchAppliedLeaveData() ");
		return ss.getHistoryOfAppliedLeaveDetails(userId, roleId, menuId);
	}

	// Add Holidays List Officially by admin.
	@Path("/SaveOrUpdateOfficialHolidaysByAdmin")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveOrUpdateHolidaysList(HolidayNamesBean bean) {
		return ss.saveOrUpdateHolidays(bean);
	}

	// Applied Leave History Data.
	@Path("/fetchOfficialHolidayList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchofficialHolidayList() {
		return ss.fetchOfficialHolidaysList();
	}
//Save TimeSheet data
	
		@Path("/SaveTimeSheetDetails")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response saveTimeCard(TimeSheetDailyWork bean) {
			return ss.saveTimeCard(bean);
		}
	

		
}
