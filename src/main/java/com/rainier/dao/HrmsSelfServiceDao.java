package com.rainier.dao;


import com.rainier.entities.LeaveRequestEntity;
import com.rainier.entities.MyLeaveRequestEntity;
import com.rainier.entities.TimeSheetEntity;

import java.util.List;

public interface HrmsSelfServiceDao {
	//SelfService->leaves->LeaveRequest
    boolean applyLeaveRequest(MyLeaveRequestEntity leaveRequest);
	
	//selfService->Leaves->MyLeaves->Delete
	
	String myLeaveDelete(int id);
		
	//update Employee Leaves table. 
    void updateEmployeeLeaves(String leaveType, int userId, float days, String operation, String leaveStatus);

	
	// Self service reason Showing and all data..
    List<LeaveRequestEntity> fetchAppliedLeaveRequest(int userId);
  //Save time Card data
  	boolean saveTimeCard(TimeSheetEntity entity);
}
