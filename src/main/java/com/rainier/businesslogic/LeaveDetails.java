package com.rainier.businesslogic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import com.rainier.beans.EmpLeaveResponseBean;
import com.rainier.beans.EmployeeLeaveTypeBean;
import com.rainier.beans.EmployeeLeaveTypeResponseBean;
import com.rainier.beans.LeaveManagementOptionsResponseBean;
import com.rainier.beans.LeaveRequestBean;
import com.rainier.beans.LeavesResponseBean;
import com.rainier.beans.UpdateEmployeeLeaveDetails;
import com.rainier.beans.UpdateLeaveRequest;
import com.rainier.dao.HrmsEmployeeDao;
import com.rainier.dao.HrmsLeaveRequestDao;
import com.rainier.dao.Impl.HrmsEmployeeDetails;
import com.rainier.dao.Impl.HrmsLeaveRequest;
import com.rainier.dao.Impl.HrmsSelfDetails;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.EmployeeLeaveRequestSummaryEntity;
import com.rainier.entities.EmployeeLeaveTypeEntity;
import com.rainier.entities.LeaveManagementEntity;
import com.rainier.entities.LeaveRequestEntity;
import com.rainier.entities.MailLogEntity;
import com.rainier.entities.MyLeaveRequestEntity;
import com.rainier.utility.HrmsGetDateAndTime;
import com.rainier.utility.SendMail;

public class LeaveDetails {
	private static final Logger logger = Logger.getLogger(LeaveDetails.class);

	private static final HrmsLeaveRequestDao leaves = new HrmsLeaveRequest();
	private final LeavesResponseBean response = new LeavesResponseBean();
	private static final HrmsEmployeeDao db = new HrmsEmployeeDetails();

	// Leaves Details List
	public Response getLeavesDetails(int uId, String leavestatus, String view) {
		logger.info("entered into getLeavesDetails of businessClass");
		int countAll = 0, countPending = 0, countApproved = 0, countRejected = 0, countCancel = 0;
		if (leavestatus.equalsIgnoreCase("pendingforapproval"))
			leavestatus = "pending for approval";
		List<LeaveRequestEntity> listOfLeaves = leaves.listOfLeaves(uId, leavestatus, view);
		LeavesResponseBean response = new LeavesResponseBean();

		if (!listOfLeaves.isEmpty()) {
			response.setMessage("Retrival of Leave Details Successfull.");
			response.setStatus(true);

			if (uId != 0 && "Employee".equalsIgnoreCase(view)) {
				for (LeaveRequestEntity leave : listOfLeaves) {
					countAll++;
					if ("Pending for approval".equalsIgnoreCase(leave.getLeaveStatus()))
						countPending++;
					else if ("Approved".equalsIgnoreCase(leave.getLeaveStatus()))
						countApproved++;
					else if ("Rejected".equalsIgnoreCase(leave.getLeaveStatus()))
						countRejected++;
					else if ("Cancel".equalsIgnoreCase(leave.getLeaveStatus()))
						countCancel++;
					else
						logger.info("Nothing to count.");

				}
				response.setCountAll(countAll);
				response.setCountPending(countPending);
				response.setCountApproved(countApproved);
				response.setCountRejected(countRejected);
				response.setCountCancel(countCancel);
			}

			response.setListOfLeaves(listOfLeaves);
		} else {
			response.setMessage("No Leave Details are available.");
			response.setStatus(false);
			response.setListOfLeaves(listOfLeaves);
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// Leave Management Options
	public Response getLeaveManagementOptions() {
		logger.info("entered into getLeaveManagementOptions of businessClass");

		List<LeaveManagementEntity> listManagementOptions = leaves.listOfLeaveManagementOptions();
		LeaveManagementOptionsResponseBean response = new LeaveManagementOptionsResponseBean();

		if (!listManagementOptions.isEmpty()) {
			response.setMessage("Retrival of Leave Management Options Successfull.");
			response.setStatus(true);

			response.setListOfLeaveManagementOptions(listManagementOptions);
		} else {
			response.setMessage("No Leave Management Options are available.");
			response.setStatus(false);
			response.setListOfLeaveManagementOptions(listManagementOptions);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Adding no of days for user.
	public Response saveEmployeeLeaveData(EmployeeLeaveTypeBean leaveBean) {
		EmployeeLeaveTypeResponseBean leaveAddResponseBean = new EmployeeLeaveTypeResponseBean();
		EmployeeLeaveTypeEntity employeeLeaveEntity = new EmployeeLeaveTypeEntity();
		//
		try {
			BeanUtils.copyProperties(employeeLeaveEntity, leaveBean);

			/*
			 * employeeLeaveEntity.setCreatedDate(new
			 * HrmsGetDateAndTime().GetUTCdatetimeAsString());
			 * employeeLeaveEntity.setModifiedDate(new
			 * HrmsGetDateAndTime().GetUTCdatetimeAsString());
			 * employeeLeaveEntity.setId(leaveBean.getId());
			 * employeeLeaveEntity.setNoOfDays(leaveBean.getNoOfDays());
			 * employeeLeaveEntity.setUserId(leaveBean.getUserId());
			 * employeeLeaveEntity.setYear(leaveBean.getYear());
			 */
			employeeLeaveEntity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			employeeLeaveEntity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			employeeLeaveEntity.setCreatedBy(leaveBean.getUserId());
			employeeLeaveEntity.setModifiedBy(leaveBean.getUserId());
			employeeLeaveEntity.setLeaveType(leaveBean.getLeaveType());
			employeeLeaveEntity.setNoOfDays(leaveBean.getNoOfDays());
			employeeLeaveEntity.setYear(leaveBean.getYear());
			employeeLeaveEntity.setLeaveTypeId(leaveBean.getId());
			// employeeLeaveEntity.setLeaveType(leaveBean.getLeaveTypeId());

			leaves.saveNoOfDays(employeeLeaveEntity);
			// System.out.println(inserted);
			leaveAddResponseBean.setMesssage("Inserted Succesfully.");
			leaveAddResponseBean.setStatus(true);

			return Response.status(Response.Status.OK).entity(leaveAddResponseBean).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			leaveAddResponseBean.setMesssage("Error while saving details - " + e.getMessage());
			leaveAddResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(leaveAddResponseBean).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			leaveAddResponseBean.setMesssage("Error while saving  details - " + e.getMessage());
			leaveAddResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(leaveAddResponseBean).build();

		}

	}

	// 1.update for Employee leaveRequest Summary
	public Response updateAllLeaveSummary(UpdateLeaveRequest updateBean) {

		MyLeaveRequestEntity entityBean = new MyLeaveRequestEntity();
		EmpLeaveResponseBean updateResponseBean = new EmpLeaveResponseBean();

		try {
			List<LeaveRequestBean> leaveBean = updateBean.getLeaveBean();
			// Timestamp modifieddate =new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedBy(updateBean.getModifiedBy());
			for (LeaveRequestBean value : leaveBean) {
				BeanUtils.copyProperties(entityBean, value);

				boolean status = leaves.updateEmployeeLeaveRequestSummary(entityBean);
				if (status) {
					updateResponseBean.setMessage("Leave request updated  successfully!!!!");
					new HrmsSelfDetails().updateEmployeeLeaves(entityBean.getLeaveType(), entityBean.getUserId(),
							entityBean.getDays(), "update", entityBean.getLeaveStatus());
				} else {
					updateResponseBean.setMessage("Failed to update leave request, please try again!!!!");
				}
				updateResponseBean.setStatus(status);
			}
			// updateResponseBean.setMessage(" Employee leave details updated
			// Successfully");

			return Response.status(Response.Status.OK).entity(updateResponseBean).build();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			updateResponseBean.setMessage("Error while updating - " + e1.getMessage());
			updateResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(updateResponseBean).build();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			updateResponseBean.setMessage("Error while updating - " + e1.getMessage());
			updateResponseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(updateResponseBean).build();
		}
	}

	// 2. update Employee leave Summary Status.
	public Response updateEmpLeaveStatus(UpdateEmployeeLeaveDetails empLeaveSummary) {
		boolean message = leaves.updateEmployeeLeaveDetails(empLeaveSummary);
		if (message == true) { 
			    response.setStatus(true);
		    	response.setMessage("Leave Updated SuccessFully");
				
				} 
		else {
				response.setMessage("Employee Official Information Updating failed.");
				response.setStatus(false);
				
			}
		return Response.ok().entity(response).build();
		
		}
}
