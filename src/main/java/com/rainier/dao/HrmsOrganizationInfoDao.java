package com.rainier.dao;

import com.rainier.dto.requestBean.OrganizationInfoRequestBean;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.Main_Businessunits;
import com.rainier.entities.OrganizationInfoEntity;

import java.util.List;

public interface HrmsOrganizationInfoDao {
	boolean saveUpdateOrganizationInfo(OrganizationInfoRequestBean bean);

	OrganizationInfoEntity getOrganizationInfo();

	List<Main_Businessunits> getOrganizationStructure();

	List<EmployeeDetailsEntity> getOrganizationHierarchy();
	
}
