package com.rainier.dao;

import com.rainier.entities.Main_Departments;

import java.util.List;

public interface HrmsDepartmentDao {

    /* public List<Main_Departments> getDepartmentList(int pageSize,int index); */

    List<Main_Departments> getDepartmentList();

    boolean insertDepartmentDetails(Main_Departments md);

    String updateDepartmentDetails(Main_Departments md);

    String deleteDepartmentDetails(int id, int userId);

    // for department List based on the businessUnit id.

    List<Main_Departments> getBuDu(Integer businessUnitId);

    // dept head lov.
    List<Object[]> getDeptHeadlov();

}
