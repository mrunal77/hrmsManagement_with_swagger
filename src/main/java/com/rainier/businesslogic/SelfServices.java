package com.rainier.businesslogic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.rainier.beans.EmpLeaveResponseBean;
import com.rainier.beans.HolidayNamesBean;
import com.rainier.beans.LeaveRequestBean;
import com.rainier.beans.LeaveResponseBean;
import com.rainier.beans.TimeSheet;
import com.rainier.beans.TimeSheetDailyWork;
import com.rainier.dao.HrmsEmployeeDao;
import com.rainier.dao.HrmsLeaveRequestDao;
import com.rainier.dao.HrmsSelfServiceDao;
import com.rainier.dao.HrmsUserAuthenticate;
import com.rainier.dao.Impl.HrmsEmployeeDetails;
import com.rainier.dao.Impl.HrmsLeaveRequest;
import com.rainier.dao.Impl.HrmsSelfDetails;
import com.rainier.dao.Impl.HrmsUserAuthentication;
import com.rainier.dto.responseBean.CommonResponseBean;
import com.rainier.entities.HolidayNamesEntity;
import com.rainier.entities.LeaveRequestEntity;
import com.rainier.entities.MailLogEntity;
import com.rainier.entities.MyLeaveRequestEntity;
import com.rainier.entities.Privileges;
import com.rainier.entities.TimeSheetEntity;
import com.rainier.utility.DaysCalculator;
import com.rainier.utility.HrmsGetDateAndTime;
import com.rainier.utility.SendMail;

public class SelfServices {
    private static final Logger logger = Logger.getLogger(SelfServices.class);
    private static final HrmsSelfServiceDao hssd = new HrmsSelfDetails();
    private static final HrmsEmployeeDao db = new HrmsEmployeeDetails();
    private static final HrmsUserAuthenticate user = new HrmsUserAuthentication();

    public Response saveLeaveRequest(LeaveRequestBean leaverequestBean, int roleId, int menuId) {
        logger.info("entered into saveLeaveRequest of business class ");
        EmpLeaveResponseBean responseBean = new EmpLeaveResponseBean();
        MyLeaveRequestEntity entityBean = new MyLeaveRequestEntity();
        try {
            BeanUtils.copyProperties(entityBean, leaverequestBean);
           entityBean.setIsactive(1);
           entityBean.setAppliedLeavescount(leaverequestBean.getAppliedLeavesCount());
            entityBean.setCreatedBy(leaverequestBean.getUserId());
            entityBean.setCreatedDdate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            entityBean.setModifiedBy(leaverequestBean.getUserId());
            entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            // entityBean.setCreatedddate( new
            // java.sql.Date(Calendar.getInstance().getTime().getTime()));
            entityBean.setLeaveStatus("Pending for approval");
            entityBean.setDays(
                    new DaysCalculator().getDays(leaverequestBean.getFromDate(), leaverequestBean.getToDate()));
            // System.out.println(new
            // DaysCalculator().getDays(leaverequestBean.getFromDate(),
            // leaverequestBean.getToDate())+"setDays"+entityBean.getDays());
            boolean inserted = hssd.applyLeaveRequest(entityBean);
            // System.out.println(inserted);
            if (inserted) {
            	SendMail mail = new SendMail();
    			boolean emailFlag = mail.send(entityBean.getEmail());
    			 emailFlag=mail.send("mobeenasyed888@gmail.com","Super Admin",entityBean.getName());
    			 emailFlag=mail.send("sureshk.ajs@gmail.com","Bharath Kumar",entityBean.getName());
    			MailLogEntity mailLog = new MailLogEntity();
    			mailLog.setToEmails(entityBean.getEmail());
    			mailLog.setToName(entityBean.getName());
    			mailLog.setEmailSubject("EEAccess");
    			mailLog.setIsSent(1);
    			mailLog.setMessage("Message Sent");
    			mailLog.setHeader("Greeting from EEAccess");
    			if (emailFlag) {
    				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
    				db.insertMailLog(mailLog);
    				 responseBean.setMessage("Leave request raised  successfully!!!!");
    				 responseBean.setStatus(inserted);
                hssd.updateEmployeeLeaves(entityBean.getLeaveType(), entityBean.getUserId(), entityBean.getDays(),
                        "save", null);
            } }else {
                responseBean.setMessage("Failed to raise leave request, please try again!!!!");
            
            responseBean.setStatus(inserted);
            List<Privileges> listprivileges = user.getPrivileges(roleId, menuId);
            responseBean.setPrivilegesList(listprivileges);
            return Response.status(Response.Status.OK).entity(responseBean).build();

            }
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseBean.setMessage("Error while saving my leave details - " + e.getMessage());
            responseBean.setStatus(false);
            return Response.status(Response.Status.OK).entity(responseBean).build();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            logger.info("catch block of saveLeaveRequest of business class::" + e);
            e.printStackTrace();
            responseBean.setMessage("Error while saving my leave details - " + e.getMessage());
            responseBean.setStatus(false);
            return Response.status(Response.Status.OK).entity(responseBean).build();
        }
        return Response.status(Response.Status.OK).entity(responseBean).build();


    }

    // selfService->Leaves->MyLeaves->Delete
    public Response deleteMyLeaveDetails(int id) {
        logger.info("entered into deleteMyLeaveDetails method of business class");
        // MyLeaveRequestEntity entityBean = new MyLeaveRequestEntity();
        LeaveResponseBean responseBean = new LeaveResponseBean();
        try {
            hssd.myLeaveDelete(id);
            responseBean.setMessage("Deleted Succesfully::");
            responseBean.setStatus(true);
            return Response.status(Response.Status.OK).entity(responseBean).build();
        } catch (Exception e) {
            logger.info("catch block of deleteMyLeaveDetails of business class::" + e);
            responseBean.setMessage("something went wrong ::");
            responseBean.setStatus(false);
            return Response.status(Response.Status.OK).entity(responseBean).build();
        }
    }

    // history of applied leave Details.
    public Response getHistoryOfAppliedLeaveDetails(int userId,int roleId,int menuId) {
        logger.info("entered into getHistoryOfAppliedLeaveDetails method of business class");
        // HrmsSelfServiceDao dao = new HrmsSelfDetails();
        // MyLeaveRequestEntity entityBean = new MyLeaveRequestEntity();
       
        CommonResponseBean response = new CommonResponseBean();
        List<LeaveRequestEntity> historyAppliedLeaveList = hssd.fetchAppliedLeaveRequest(userId);
        List<Privileges> listOfPrivilleges= new HrmsUserAuthentication().getPrivileges(roleId, menuId);
        if (!historyAppliedLeaveList.isEmpty()) {
            response.setMessage("History of applied leave Details Retrieved Successfully.");
            response.setStatus(true);
            response.setList(historyAppliedLeaveList);
           response.setPrivilleges(listOfPrivilleges);
        } else {
            response.setMessage("Failed to Retrieved applied  Leaves History .");
            response.setStatus(false);
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }

    private static HrmsLeaveRequestDao dao = new HrmsLeaveRequest();

    // Holidays List adding Services.
    public Response saveOrUpdateHolidays(HolidayNamesBean bean) {
        CommonResponseBean response = new CommonResponseBean();

        HolidayNamesEntity entity = new HolidayNamesEntity();
        try {
            BeanUtils.copyProperties(entity, bean);
            entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            entity.setIsactive(1);
            boolean holidayList = dao.saveOrUpdateHolidaysList(entity);
            if (holidayList && bean.getId() > 0) {
                response.setMessage("Holiday  List Updated Successfully.");
                response.setStatus(true);
            } else if (holidayList) {
                response.setMessage("Holiday List Saved Successfully.");
                response.setStatus(true);
            } else {
                response.setMessage("Something Went Wrong.");
                response.setStatus(false);
            }
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setMessage("Error while saving Holidays  details - " + e.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            logger.info("catch block of saveLeaveRequest of business class::" + e);
            e.printStackTrace();
            response.setMessage("Error while saving my leave details - " + e.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();

        }

    }

    // Fetch Official Holidays List,.
    public Response fetchOfficialHolidaysList() {
        CommonResponseBean response = new CommonResponseBean();
        // HrmsLeaveRequestDao dao = new HrmsLeaveRequest();
        List<HolidayNamesEntity> entityList = dao.getAllOfficialHolidays();

        if (!entityList.isEmpty()) {
            response.setMessage("Official Holiday List retrieved for All Employee.");
            response.setStatus(true);
            response.setList(entityList);
        } else {
            response.setMessage(" official Holiday List Not retrieved.");
            response.setStatus(false);

        }
        return Response.status(Response.Status.OK).entity(response).build();

    }

    //Save Timecard data
    
    public Response saveTimeCard(TimeSheetDailyWork bean) {
		CommonResponseBean response = new CommonResponseBean();

		
		for (TimeSheet timesheet : bean.getSheet()) {

			TimeSheetEntity entity = new TimeSheetEntity();

			entity.setId(bean.getId());
			entity.setUserid(bean.getUserid());
			entity.setTime(timesheet.getTime());
            entity.setProjectName(timesheet.getProjectName());
            entity.setTask(timesheet.getTask());
            entity.setDescription(timesheet.getDescription());
			boolean savedEntity = hssd.saveTimeCard(entity);

			if (bean.getId() > 0 && savedEntity == true) {
				response.setMessage("Updated Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Inserted Successfuly.");
				response.setStatus(true);

			}

		}
		return Response.status(Response.Status.OK).entity(response).build();
	

    }}
