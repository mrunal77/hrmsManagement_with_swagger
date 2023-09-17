package com.rainier.services;

import com.rainier.beans.CurrentWeekDatesResponse;
import com.rainier.beans.CurrentWeekRequest;
import com.rainier.beans.SaveTimesheetRequestBean;
import com.rainier.beans.TimeSheetAddCalWeekMonthBean;
import com.rainier.beans.TimeSheetResponse;
import com.rainier.businesslogic.TimeSheetDetails;
import com.rainier.dto.requestBean.TimeSheetApprovalEntity;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/TimeSheet")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
public class HrmsEmpTimeSheetService 
{
	final static Logger logger = Logger.getLogger(HrmsEmpTimeSheetService.class);
	private static TimeSheetDetails impl = new TimeSheetDetails();

	@Path("/getMonthlyTSDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTimeSheetDetails(@QueryParam("year") int year,@QueryParam("month") int month,@QueryParam("userid") int userid)
	{
		logger.info("entered into getTimeSheetDetails method of HrmsEmpTimeSheetService class");
		TimeSheetResponse timeSheetResponse = impl.getMonthlyTimeSheetDetails(year,month, userid);
		return Response.status(Response.Status.OK).entity(timeSheetResponse).build();
	}

	@Path("/saveTimeSheet")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveTimeSheet(SaveTimesheetRequestBean saveReq)
	{
		logger.info("entered into saveTimeSheet method of HrmsEmpTimeSheetService class");
		return impl.saveTimeSheet(saveReq);
	}

	@Path("/getWeeklyTSDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTimeSheetWeeklyDetails(@QueryParam("userId") int userId, @QueryParam("year") int year,@QueryParam("month") int month, @QueryParam("calenderWeek") int cal_Week) 
	{
		logger.info("entered into getTimeSheetDetails method of HrmsEmpTimeSheetService class");
		return impl.getTimeSheetDetails(userId, year, month, cal_Week);

	}

	@Path("/getWeekDates")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public CurrentWeekDatesResponse getWeekDates(CurrentWeekRequest currentWeekRequest) 
	{
		logger.info(
				"entered into getWeekDates method of HrmsEmpTimeSheetService class" + currentWeekRequest.getTdate());
		return impl.getWeekDates(currentWeekRequest.getTdate());
	}

	@Path("/getEmpUserListByReportingManagerId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReportingManagerNameById(@QueryParam("repId") int repId) 
	{
		logger.info("entered into getEmpUserIdListByReportingManagerId method of HrmsEmpTimeSheetService class");
		return impl.getEmpUserListByReportingManagerId(repId);
	}


	@Path("/getEmplTimeSheetDetailsByReportingManagerId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTimeSheetDetailsByReportingManagerId(@QueryParam("repId") int repId , @QueryParam("status") String status) 
	{
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of HrmsEmpTimeSheetService class");
		return impl.getEmplTimeSheetDetailsByReportingManagerId(repId, status);
	}

	@Path("/getEmployeesByReportingManagerId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeesByReportingManagerId(@QueryParam("repId") int repId ) 
	{
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of HrmsEmpTimeSheetService class");
		return impl.getEmployeesByReportingManagerId(repId);
	}

	
	@Path("/getTimeSheetDetailsByUserId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTimeSheetDetailsByUserId(@QueryParam("userId") int userId  ) 
	{
		logger.info("entered into getTimeSheetDetails method of HrmsEmpTimeSheetService class");
		return impl.getTimeSheetDetailsByUserId(userId );
	}

	@Path("/timeSheetApproval")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response timeSheetApproval(TimeSheetApprovalEntity timeSheetApprovalEntity) 
	{
		logger.info("entered into getTimeSheetDetails method of HrmsEmpTimeSheetService class");
		return impl.timeSheetApproval(timeSheetApprovalEntity );
	}

	
	@Path("/getLeaveStatusByUserId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLeaveStatusByUserId(@QueryParam("repId") int repId,@QueryParam("status") String status)
	{
		logger.info("entered into getLeaveStatusByUserId method of HrmsEmpTimeSheetService class");
		return impl.getEmplTimeSheetDetailsByReportingManagerId(repId,status);
	}

	@Path("/getTSDetailsByUserId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTSDetailsByUserId(@QueryParam("userId") int userId,@QueryParam("status") String status)
	{
		logger.info("entered into getLeaveStatusByUserId method of HrmsEmpTimeSheetService class");
		return impl.getTSDetailsByUserId(userId,status);
	}

	
/*	@Path("/getLeaveStatusByUserId")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeaveStatusByUserId(@QueryParam("userId") int userId) 
	{
		logger.info("entered into getLeaveStatusByUserId method of HrmsEmpTimeSheetService class");
		return impl.getLeaveStatusByUserId(userId );
	}*/
	
	@Path("/getReportingManagerIdName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReportingManagerIdName() 
	{
		logger.info("entered into getReportingManagerIdName method of HrmsEmpTimeSheetService class");
		return impl.getAllReportingManagerIdName();

	}

	@Path("/getEmplDetailsByReportingManagerId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpDetailsByReportingManagerId(@QueryParam("repId") int repId,@QueryParam("calWeek")short calWeek ) 
	{
		logger.info("entered into getEmpDetailsByReportingManagerId method of HrmsEmpTimeSheetService class");
		return impl.getEmplDetailsByReportingManagerId(repId,calWeek);
	}
	@Path("/getEmployeeDetailsByReportingManagerId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeDetailsByReportingManagerId(@QueryParam("repId") int repId) 
	{
		logger.info("entered into getEmployeeDetailsByReportingManagerId method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeDetailsByReportingManagerId(repId);
	}
	@Path("/getEmployeeWorkingReportsByUserIdStatusCalweekYear")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeWorkingReportsByUserIdStatusCalweekYear(@QueryParam("repId") int repId , @QueryParam("status") String status,@QueryParam("calWeek")short calWeek,@QueryParam("year")int year) 
	{
		logger.info("entered into getEmployeeWorkingReportsByUserIdStatusCalweekYear method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeWorkingReportsByUserIdStatusCalweekYear(repId, status,calWeek,year);
	}
	
	@Path("/getStatusCountInTimeSheetOfEmp")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusCountInTimeSheetOfEmp(@QueryParam("repId") int repId,@QueryParam("calWeek")short calWeek )
	{
		logger.info("entered into getReportingManagerIdName method of HrmsEmpTimeSheetService class");
		return impl.getStatusEmployeeStatusInTimeSheet(repId,calWeek);

	}
	@Path("/getStatusCountInTimeSheetOfEmpBasedOnYearEmpId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusCountInTimeSheetOfEmpBasedOnYear(@QueryParam("repId") int repId,@QueryParam("year")int year,@QueryParam("empId") int empId )
	{
		logger.info("entered into getStatusCountInTimeSheetOfEmpBasedOnYear method of HrmsEmpTimeSheetService class");
		return impl.getStatusCountInTimeSheetOfEmpBasedOnYear(repId,year,empId);

	}
	@Path("/getStatusCountInTimeSheetOfEmpBasedOnYearMonthEmpId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusCountInTimeSheetOfEmpBasedOnYearMonthEmpId(@QueryParam("repId") int repId,@QueryParam("year")int year,@QueryParam("empId") int empId,@QueryParam("month") short month)
	{
		logger.info("entered into getStatusCountInTimeSheetOfEmpBasedOnYearMonthEmpId method of HrmsEmpTimeSheetService class");
		return impl.getStatusCountInTimeSheetOfEmpBasedOnYearMonthEmpId(repId,year,empId,month);

	}
	@Path("/getStatusCount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusCount(@QueryParam("repId") int repId,@QueryParam("year")int year,@QueryParam("empId") int empId,@QueryParam("month") short month )
	{
		logger.info("entered into getStatusCount method of HrmsEmpTimeSheetService class");
		return impl.getStatusCount(repId,year,empId,month);

	}
	
	@Path("/getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus
	(@QueryParam("repId") int repId,@QueryParam("calWeek")short calWeek,@QueryParam("status")String status,@QueryParam("empId") int empId,@QueryParam("year") int year,@QueryParam("month") short month ) 
	{
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(repId, empId,year, month,calWeek,status);
	}
	@Path("/getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus
	(@QueryParam("repId") int repId,@QueryParam("status")String status,@QueryParam("empId") int empId,@QueryParam("year") int year,@QueryParam("month") short month ) 
	{
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus(repId, empId,year, month,status);
	}
	
	@Path("/getStatusCountByrepIdYearEmpIdMonthStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatusCountByrepIdYearCalWeekMonth(@QueryParam("repId") int repId,@QueryParam("year")int year,@QueryParam("empId") int empId,@QueryParam("month") short month,@QueryParam("status") String status )
	{
		logger.info("entered into getStatusCount method of HrmsEmpTimeSheetService class");
		return impl.getStatusCountByrepIdYearEmpIdMonthStatus(repId, empId,year, month,status);

	}
	@Path("/getEmployeeDetailBasedOnRepIdEmpIdYearMonth")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeDetailBasedOnRepIdEmpIdYearMonth
	(@QueryParam("repId") int repId,@QueryParam("empId") int empId,@QueryParam("year") int year,@QueryParam("month") short month ) 
	{
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonth method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeDetailBasedOnRepIdEmpIdYearMonth(repId, empId,year, month);
	}
	
	@Path("/getCalWeekMonthNameByMonthId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCalWeekByMonth(@QueryParam("monthId")short month )
	{
		logger.info("entered into getCalWeekByMonth method of HrmsEmpTimeSheetService class");
		return impl.getWeekMonthNameByMonthId(month);

	}
	@Path("/saveCalWeekMonthIdName")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveCalWeekMonthIdName(TimeSheetAddCalWeekMonthBean bean) {
		return impl.saveWeekMonthidNameInTimeSheet(bean);
	}
	
	@Path("/getEmplDetailsByReportingManagerIdMonthYear")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmplDetailsByReportingManagerIdMonthYear
	(@QueryParam("repId") int repId,@QueryParam("year") int year,@QueryParam("month") short month ) 
	{
		logger.info("entered into getEmplDetailsByReportingManagerIdMonthYear method of HrmsEmpTimeSheetService class");
		return impl.getEmplDetailsByReportingManagerIdMonthYear(repId,year, month);
	}
	@Path("/getEmplDetailsByReportingManagerIdMonthYearEmpId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmplDetailsByReportingManagerIdMonthYearEmpId
	(@QueryParam("repId") int repId,@QueryParam("year") int year,@QueryParam("month") short month,@QueryParam("empId")int empId ) 
	{
		logger.info("entered into getEmplDetailsByReportingManagerIdMonthYearEmpId method of HrmsEmpTimeSheetService class");
		return impl.getEmplDetailsByReportingManagerIdMonthYearEmpId(repId,year, month,empId);
	}
	@Path("/getMonth")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMonth()
	{
		logger.info("entered into getMonth method of HrmsEmpTimeSheetService class");
		return impl.getMonthAndId();
	}
	
	@Path("/getTaskOfEmp")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTaskOfEmp(@QueryParam("repId") int repId,@QueryParam("calWeek")short calWeek,@QueryParam("empId") int empId )
	{
		logger.info("entered into getTaskOfEmp method of HrmsEmpTimeSheetService class");
		return impl.getEmpTaskDetail(repId,empId,calWeek);

	}
	
	
	@Path("/getEmplDetailsByReportinManagerIdEmpIdCalweek")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmplDetailsByReportinManagerIdEmpIdCalweek
	(@QueryParam("repId") int repId,@QueryParam("empId") int empId,@QueryParam("calWeek") short calWeek ) 
	{
		logger.info("entered into getEmplDetailsByReportinManagerIdEmpIdCalweek method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeDetailsBasedOnEmpIdRepIdCalWeek(repId,empId, calWeek);
	}
	
	@Path("/getEmplDetailsByReportinManagerIdEmpIdCalweekMonthYear")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmplDetailsByReportinManagerIdEmpIdCalweekMonthYear
	(@QueryParam("repId") int repId,@QueryParam("empId") int empId,@QueryParam("calWeek") short calWeek,@QueryParam("year") int year,@QueryParam("month") short month  ) 
	{
		logger.info("entered into getEmplDetailsByReportinManagerIdEmpIdCalweekMonthYear method of HrmsEmpTimeSheetService class");
		return impl.getEmplDetailsByReportinManagerIdEmpIdCalweekMonthYear(repId,empId, calWeek,year,month);
	}
	
	@Path("/getEmployeeDetailBasedOnRepIdEmpIdYearMonthCal")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeDetailBasedOnRepIdEmpIdYearMonthCal
	(@QueryParam("repId") int repId,@QueryParam("calWeek")short calWeek,@QueryParam("empId") int empId,@QueryParam("year") int year,@QueryParam("month") short month ) 
	{
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthCal method of HrmsEmpTimeSheetService class");
		return impl.getEmpDetailsByEmpIdCalweekMonthYear(repId, empId,year, month,calWeek);
	}
	
	@Path("/getEmpDetailsByEmpIdYear")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpDetailsByEmpIdYear
	(@QueryParam("repId") int repId,@QueryParam("empId") int empId,@QueryParam("year") int year ) 
	{
		logger.info("entered into getEmpDetailsByEmpIdYear method of HrmsEmpTimeSheetService class");
		return impl.getEmpDetailsByEmpIdYear(repId,empId, year);
	}
	@Path("/getEmployeeDetailsBasedOnCanIdYearStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response	getEmployeeDetailsBasedOnCanIdYearStatus(@QueryParam("repId") int repId,@QueryParam("empId") int empId,@QueryParam("year") int year,@QueryParam("status") String status) {
		logger.info("entered into getEmployeeDetailsBasedOnCanIdYearStatus method of HrmsEmpTimeSheetService class");
		return impl.getEmployeeDetailsBasedOnCanIdYearStatus(repId,empId, year,status);
	}
}
