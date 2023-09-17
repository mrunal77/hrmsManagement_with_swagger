package com.rainier.dao;

import com.rainier.dto.requestBean.EmployeeCurrentAddressBean;
import com.rainier.dto.requestBean.OnBoardingJobDetailsRequestBean;
import com.rainier.entities.EmployeeCurrentAddressEntity;

import java.util.List;

public interface OnBoardingDaoClass {
    // Job Details Confirmation
    boolean confirmJobDetails(OnBoardingJobDetailsRequestBean request);

    // Current Address Save/Update
    boolean saveUpdateCurrentAddress(EmployeeCurrentAddressBean entity);

    // Current Address List
    List<EmployeeCurrentAddressEntity> getCurrentAddresList(int userId);
}
