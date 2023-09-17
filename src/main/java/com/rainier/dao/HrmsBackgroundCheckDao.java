package com.rainier.dao;

import com.rainier.beans.BgEmploymentHistoryBean;
import com.rainier.entities.*;

import java.util.List;

public interface HrmsBackgroundCheckDao {

	// save Highest Degree earned .
	// public int addHighestDegreeEarned(BgHighestDegreeEarnedBean bean);

	// correct one...
	boolean saveHighestDegreeEarned(BgHighestDegreeEarnedEntity entity);

	// fetch Hoghest Degree Earned.

	List<BgHighestDegreeEarnedEntity> fetchHighestDegree(int userId);

	// Emop personal Info
	boolean saveEmpPersonalInfo(BgEmpPersonalInfoEntity entity);

	// corrrect one...
	boolean saveEmpPersonalInfo(BgEmploymentHistoryBean bean);

	// Employment History Details Saving.

	boolean saveEmploymentHistory(BgEmploymentHistoryEntity entity);

	boolean saveEmpWorkedHistory(BgEmploymentHistoryEntity entity);

	// professional Reference Saving for particular Employee.
	boolean saveProfessionalreference(BgEmpProfessionalReferneceEntity entity);

	// saving Employment gap ....
	boolean saveEmpEmploymentorEducationGap(BgEmpEmploymentGapEntity entity);

	// fetch Employment Gap ...
	List<BgEmpEmploymentGapEntity> getEmpEmploymentGap(int userId);

	// fetch professional Reference Saving for particular Employee.
	List<BgEmpProfessionalReferneceEntity> getProfessionalReference(int userId);

	// fetch Employment History Details .
	List<BgEmploymentHistoryEntity> getEmploymentHistory(int userId);

	// Emp personal Info fetch
	List<BgEmpPersonalInfoEntity> getEmpPersonalInfo(int userId);

	// upload Experience letter...
	boolean updateExpLetter(String filePath, int companyid);

	/* New Backgrounds Simple Highest Degree...... */

	// save Highest Degree
	boolean saveHighestDegreeBg(BgHighestDegreeEarnedEntity entity);
	
	// fetch Highest Degree
	List<BgHighestDegreeEarnedEntity> getDegree(int userId);
	
	// save Emp Professional  Ref.
	public boolean saveUpdateReferenceList(BgEmpProfessionalReferneceEntity entity); 
	
	// Save Update Emp History
	public boolean  saveUpdateEmpHis(BgEmploymentHistoryEntity entity);

}
