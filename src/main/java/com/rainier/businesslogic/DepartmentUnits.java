package com.rainier.businesslogic;

import com.rainier.beans.*;
import com.rainier.dao.HrmsDepartmentDao;
import com.rainier.dao.Impl.HrmsDepartment;
import com.rainier.dao.Impl.HrmsUserAuthentication;
import com.rainier.entities.Main_Departments;
import com.rainier.entities.Privileges;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentUnits {
    // get Departmentds details.

    private final static Logger logger = Logger.getLogger(DepartmentUnits.class);
    private static final HrmsDepartmentDao hd = new HrmsDepartment();

    public Response getDepartmentList(int roleId, int menuId) {
        logger.info("Entered into getDepartmentList()");
        Main_DepartmentBean duResponseBean = new Main_DepartmentBean();

        ArrayList<DU_Bean> departmentAl = new ArrayList<DU_Bean>();
        List<Main_Departments> listOfDept = hd.getDepartmentList();
        if (listOfDept.size() != 0) {
            for (Main_Departments row : listOfDept) {

                DU_Bean bean = new DU_Bean();
                bean.setBusinessUnitName(row.getBusinessUnitName());
                bean.setBusinessUnitId(row.getBusinessUnitId());
                bean.setCityId(row.getCityId());
                bean.setCityName(row.getCityName());
                bean.setCode(row.getCode());
                bean.setCountryId(row.getCountryId());
                bean.setCountryName(row.getCountryName());
                bean.setDepartmentHeadId(row.getDepartmentHeadId());
                // bean.setDepartmentHead(objToString(row[12]));
                // duRequestBean.setDepartmentHead(objToInteger(row[12]));
                bean.setDescription(row.getDescription());
                bean.setId(row.getId());
                bean.setName(row.getName());
                bean.setStartedOn(row.getStartedOn());
                bean.setStateId(row.getStateId());
                bean.setStateName(row.getStateName());
                bean.setStreetAddress1(row.getStreetAddress1());
                bean.setStreetAddress2(row.getStreetAddress2());
                bean.setStreetAddress3(row.getStreetAddress3());
                bean.setTimeZoneAbbr(row.getTimeZoneAbbr());
                bean.setTimeZoneId(row.getTimeZoneId());
                bean.setTimeZoneName(row.getTimeZoneName());

                departmentAl.add(bean);
            }
            logger.info("Added department details in List.");
            duResponseBean.setMessage("All department list which are Active.");
            duResponseBean.setStatus(true);
            /*
             * duResponseBean.setDeptList(departmentAl); List<Privileges> listPrivileges =
             * new HrmsUserAuthentication().getPrivileges(roleId, menuId);
             * duResponseBean.setPrivilegesList(listPrivileges);
             */
        } else {
            logger.error("Not added  in dept list.");
            duResponseBean.setMessage("Departments  details are not existed.");
            duResponseBean.setStatus(true);
        }
        duResponseBean.setDeptList(departmentAl);
        List<Privileges> listPrivileges = new HrmsUserAuthentication().getPrivileges(roleId, menuId);
        duResponseBean.setPrivilegesList(listPrivileges);
        /*
         * buResponseBean.setBusinessList(BusinessAL);
         *
         * List<Privileges> listPrevileges = new
         * HrmsUserAuthentication().getPrivileges(roleId, menuId);
         * buResponseBean.setPrivilegesList(listPrevileges);
         * logger.info("Exited from getBUDetails() ");
         *
         * return buResponseBean;
         */

        return Response.status(Response.Status.OK).entity(duResponseBean).build();

    }

    /*
     * // Read public Response getDepartmentList(int pageSize,int indexint
     * roleId,int menuId) {
     *
     * Main_DepartmentBean responseBean = new Main_DepartmentBean();
     *
     * // HrmsDepartment hd = new HrmsDepartment(); List<Main_Departments>
     * listOfDeptData = hd.getDepartmentList(pageSize,index); ArrayList<DU_Bean>
     * DepartmentAL = new ArrayList<DU_Bean>(); for (Main_Departments unit :
     * listOfDeptData) { DU_Bean bean = new DU_Bean();
     *
     * bean.setId(unit.getId()); bean.setName(unit.getName());
     * bean.setDescription(unit.getDescription()); bean.setCode(unit.getCode());
     * bean.setCountry(unit.getCountry()); bean.setState(unit.getState());
     * bean.setCity(unit.getCity()); String streetAddress = unit.getStreetAddress1()
     * + ", " + unit.getStreetAddress2() + ", " + unit.getStreetAddress3();
     * bean.setStreetAddress(streetAddress); bean.setIsactive(unit.getIsactive());
     * bean.setTimezone(unit.getTimezone()); bean.setStartdate(unit.getStartdate());
     * bean.setDepthead(unit.getDepthead());
     *
     * DepartmentAL.add(bean);
     *
     * } responseBean.setMessage("Department Details Retrieved successfully");
     * responseBean.setStatus(true); responseBean.setDeptList(DepartmentAL);
     *
     * List<Privileges> listPrivileges=new
     * HrmsUserAuthentication().getPrivileges(roleId, menuId);
     * responseBean.setPrivilegesList(listPrivileges);
     *
     * return Response.status(Response.Status.OK).entity(responseBean).build(); }
     */
    // Delete
    public Response deleteDepartment(int id, int userId) {

        EntityStatusBean response = new EntityStatusBean();
        // HrmsDepartmentDao hdd = new HrmsDepartment();

        try {
            if (hd.deleteDepartmentDetails(id, userId) == null) {
                response.setMessage("Reassign Employee to the Another Department.");
                response.setStatus(false);
                return Response.status(Response.Status.OK).entity(response).build();
                // hdd.deleteDepartmentDetails(id, userId);
            } else {
                response.setMessage(hd.deleteDepartmentDetails(id, userId) + " Department Deleted Successfully.");
                response.setStatus(true);

                return Response.status(Response.Status.OK).entity(response).build();
            }
        } catch (Exception e) {

            // System.out.println("this is exception" + e);
            response.setMessage("Failed to delete record - " + e.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();
        }

    }

    // Insert
    /*
     * public Response saveDepartmentDetails(DU_Bean bean) {
     *
     * Main_Departments dept = new Main_Departments(); // dept.setId(bean.getId());
     * dept.setName(bean.getName());
     *
     * dept.setCode(bean.getCode()); dept.setDescription(bean.getDescription());
     * dept.setStartedOn(bean.getStartedOn());
     * dept.setCountryName(bean.getCountryName());
     * dept.setStateName(bean.getStateName()); dept.setCityName(bean.getCityName());
     *
     * dept.setStreetAddress1(bean.getStreetAddress1());
     * dept.setStreetAddress2(bean.getStreetAddress2());
     * dept.setStreetAddress3(bean.getStreetAddress3());
     *
     * dept.setDepartmentHead(bean.getDepartmentHead());
     *
     *
     * // HrmsDepartmentDao hdd=new HrmsDepartment(); EntityStatusBean response =
     * new EntityStatusBean();
     *
     * try { hd.insertDepartmentDetails(dept);
     * response.setMessage("Department Details Saved Successfully");
     * response.setStatus(true);
     *
     * return Response.status(Response.Status.OK).entity(response).build(); } catch
     * (Exception e) {
     *
     * // System.out.println("this is exception" + e);
     *
     * response.setMessage("Error while saving details - " + e.getMessage());
     * response.setStatus(false); return
     * Response.status(Response.Status.OK).entity(response).build(); } }
     */

    public Response saveDUDetails(DU_RequestBean duRequestBean) {
        Main_Departments entityBean = new Main_Departments();
        // HrmsDepartmentDao dao = new HrmsDepartment();
        EntityStatusBean response = new EntityStatusBean();

        try {
            BeanUtils.copyProperties(entityBean, duRequestBean);

            // Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

            entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

            entityBean.setCreatedBy(duRequestBean.getUserId());
            entityBean.setIsactive(1);

            boolean inserted = hd.insertDepartmentDetails(entityBean);
            // System.out.println(inserted);
            if (inserted) {
                response.setMessage(" Department Unit  Saved Successfully.");
                response.setStatus(true);
            } else {
                response.setMessage("Please add Unique Department Name and  Fill all required fields first.");
                response.setStatus(true);
            }
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setMessage("Error while saving details - " + e.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setMessage("Error while saving DU details - " + e.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();

        }

    }

    // Update
    public Response updateDepartment(DU_RequestBean duRequestBean) {
        Main_Departments dept = new Main_Departments();
        // EntityStatusBean response = new EntityStatusBean();
        Main_DepartmentBean duResponseBean = new Main_DepartmentBean();
        try {
            BeanUtils.copyProperties(dept, duRequestBean);
            dept.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            dept.setModifiedBy(duRequestBean.getUserId());
            dept.setIsactive(1);
            hd.updateDepartmentDetails(dept);
            duResponseBean.setMessage(" Updated Successfully");
            duResponseBean.setStatus(true);

            return Response.status(Response.Status.OK).entity(duResponseBean).build();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            // System.out.println("this is exception" + e1);
            duResponseBean.setMessage("Error while updating - " + e1.getMessage());
            duResponseBean.setStatus(false);
            return Response.status(Response.Status.OK).entity(duResponseBean).build();
        } catch (InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            // System.out.println("this is exception" + e1);
            duResponseBean.setMessage("Error while updating - " + e1.getMessage());
            duResponseBean.setStatus(false);
            return Response.status(Response.Status.OK).entity(duResponseBean).build();
        }

    }

    // Department unit according to business unit ....

    public Response getBUDUInfo(Integer businessUnitId) {
        BU_DU_ResponseBean responseBean = new BU_DU_ResponseBean();
        // HrmsDepartmentDao dao = new HrmsDepartment();
        try {
            List<Main_Departments> listOfDT = hd.getBuDu(businessUnitId);
            if (listOfDT.size() != 0) {
                responseBean.setBUDUList(listOfDT);
                responseBean.setMessage("Department data fetched based on the business unit list");
                responseBean.setStatus(true);
            } else {
                responseBean.setBUDUList(listOfDT);
                responseBean.setMessage("Such Department not added in any Business Unit");
                responseBean.setStatus(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setMessage("Error while fetching department unit details - " + e.getMessage());
            responseBean.setStatus(false);
        }
        return Response.status(Response.Status.OK).entity(responseBean).build();

    }

    /*
     * Converts Object DataType to String Input - Object returns String If Null
     * returns empty string
     */
    private String objToString(Object obj) {

        if (obj != null)
            return obj.toString();

        return "";
    }

    /*
     * Converts Object DataType to Integer Input - Object returns Integer If Null
     * returns 0
     */
    private Integer objToInteger(Object obj) {

        if (obj != null)
            return Integer.parseInt(obj.toString());

        return 0;
    }

    // dept head logic .

    public Response getDeptHead() {
        logger.info("Entered into getDeptHead()");
        Main_DepartmentBean duResponseBean = new Main_DepartmentBean();
        // HrmsDepartmentDao hd = new HrmsDepartment();

        ArrayList<DeptHeadBean> deptheadAl = new ArrayList<DeptHeadBean>();
        List<Object[]> deptheadListdata = hd.getDeptHeadlov();

        if (deptheadListdata.size() != 0) {
            for (Object[] row : deptheadListdata) {
                DeptHeadBean bean = new DeptHeadBean();

                bean.setEmprole(objToInteger(row[1]));
                bean.setUserfullname(objToString(row[0]));

                deptheadAl.add(bean);
            }

            duResponseBean.setMessage("Dept head List  Retrived.");
            duResponseBean.setStatus(true);
            duResponseBean.setDeptHeadList(deptheadAl);
        } else {
            duResponseBean.setMessage("Error while retrieving Dept head  name");
            duResponseBean.setStatus(false);
        }

        return Response.status(Response.Status.OK).entity(duResponseBean).build();

    }

}
