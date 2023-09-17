package com.rainier.dao;

import com.rainier.beans.UpdateEmployeeLeaveDetails;
import com.rainier.entities.*;

import java.util.List;

public interface HrmsLeaveRequestDao {
	// My Leave, Employee Leave, Employee Leave Summary
    List<LeaveRequestEntity> listOfLeaves(int uId, String leavestatus, String view);

	// Leave Management Options
    List<LeaveManagementEntity> listOfLeaveManagementOptions();

	// for adding leave limit as---------- NO OF DAYS ----------login as a userId.
    String saveNoOfDays(EmployeeLeaveTypeEntity leaveData);

	// for updating leave Request Summary details.
    boolean updateEmployeeLeaveRequestSummary(MyLeaveRequestEntity leaveUpdate);

	// for update Employee Leave Summary.
    boolean updateEmployeeLeaveDetails(UpdateEmployeeLeaveDetails empLeaveSummary);

	// Adding official Holidays By Admin .
    boolean saveOrUpdateHolidaysList(HolidayNamesEntity entity);

	// fetch All Official Holidays List.
    List<HolidayNamesEntity> getAllOfficialHolidays();
    

}
