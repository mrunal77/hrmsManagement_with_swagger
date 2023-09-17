package com.rainier.dao;

import com.rainier.dto.requestBean.PassWordUpadateRequest;
import com.rainier.entities.MenMenu1;
import com.rainier.entities.Privileges;
import com.rainier.entities.UserLoginLogEntity;
import com.rainier.entities.UserRole;

import java.util.ArrayList;
import java.util.List;

public interface HrmsUserAuthenticate {
    // Use Data Fetch
    List<UserRole> UserData(String email, String hashedPassword);

    // Privileges List
    List<Privileges> Objet(int roleId);

    List<Privileges> getPrivileges(int roleId, int menuId);
    // Old Logic Main Menu
    // public List<MainMenu> MainMenu(ArrayList<Integer> lst, int menuId);

    // for userLogin log save details.
    boolean saveUserLoginlog(UserLoginLogEntity logEntity);

    // Update Current Password
    boolean updateCurrentPassword(String oldPassword, String newPassword,int id);

    // Main Menu
    List<MenMenu1> MainMenu(ArrayList<Integer> lst);
    
	
    //Check For The Mail
    public String checkEmailIsSame(String email);
    //For Last Name
	public String getLastNameOfEmp(String email);
	
	//Get UserId By email
	public Integer getUserIdOfEmp(String email);
	//For Updating Password
	public boolean updatePassword(String  email,String newPassWord);
	
	

}
