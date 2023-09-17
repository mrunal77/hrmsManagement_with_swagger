package com.rainier.businesslogic;

import com.rainier.beans.*;
import com.rainier.dao.HrmsBusinessDao;
import com.rainier.dao.HrmsUserAuthenticate;
import com.rainier.dao.Impl.HrmsBusinessUnits;
import com.rainier.dao.Impl.HrmsUserAuthentication;
import com.rainier.entities.Main_Businessunits;
import com.rainier.entities.Privileges;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BusinessUnits {

    private final static Logger logger = Logger.getLogger(BusinessUnits.class);
    private static final HrmsBusinessDao hbu = new HrmsBusinessUnits();
    private static final HrmsUserAuthenticate user = new HrmsUserAuthentication();

    // View Business Units List with Pagination
    public Main_businessunitsBean getBUDetails(int roleId, int menuId) {
        logger.info("Entered into getBUDetails() ");
        // Response Bean for Api
        Main_businessunitsBean buResponseBean = new Main_businessunitsBean();
        ArrayList<BU_Bean> BusinessAL = new ArrayList<BU_Bean>();
        List<Main_Businessunits> listofBusinessunit = hbu.getBUList();
       
        if (!listofBusinessunit.isEmpty()) {
            for (Main_Businessunits row : listofBusinessunit) {
                BU_Bean buBean = new BU_Bean();
                buBean.setCityId(row.getCity());
                buBean.setCityName(row.getCityName());
                buBean.setStreetAddress1(row.getStreetAddress1());
                buBean.setStreetAddress2(row.getStreetAddress2());
                buBean.setStreetAddress3(row.getStreetAddress3());
                buBean.setName(row.getName());
                buBean.setCode(row.getCode());
                buBean.setId(row.getId());
                buBean.setDescription(row.getDescription());
                buBean.setStartDate(row.getStartDate());
                buBean.setCountryId(row.getCountry());
                buBean.setCountryName(row.getCountryName());
                buBean.setStateId(row.getState());
                buBean.setStateName(row.getStateName());
                buBean.setTimeZoneId(row.getTimezone());
                buBean.setTimeZoneName(row.getTimezoneName());
                // buBean.setUnitHead(row.get);
                buBean.setTimeZoneAbbr(row.getTimeZoneCode());
                /*
                 * buBean.setStreetAddress1(objToString(row[8]));
                 * buBean.setStreetAddress2(objToString(row[9]));
                 * buBean.setStreetAddress3(objToString(row[10]));
                 * buBean.setCountryName(objToString(row[19]));
                 * buBean.setStateName(objToString(row[20]));
                 * buBean.setCityName(objToString(row[21]));
                 * buBean.setName(objToString(row[1])); buBean.setCode(objToString(row[2]));
                 * buBean.setId(objToInteger(row[0]));
                 * buBean.setDescription(objToString(row[3]));
                 * buBean.setStartDate(objToString(row[4]));
                 * buBean.setCountryId(objToInteger(row[5]));
                 * buBean.setStateId(objToInteger(row[6]));
                 * buBean.setCityId(objToInteger(row[7]));
                 * buBean.setTimeZoneId(objToInteger(row[11]));
                 * buBean.setTimeZoneName(objToString(row[22]));
                 * buBean.setUnitHead(objToString(row[12]));
                 * buBean.setTimeZoneAbbr(objToString(row[23]));
                 */
                BusinessAL.add(buBean);
            }
            logger.info("Added BU details to list ");
            buResponseBean.setMessage("Business Unit Details Retrieved successfully");
            buResponseBean.setStatus(true);
        } else {
            logger.info("Not BU details to list ");
            buResponseBean.setMessage("Business Unit Details are not existed");
            buResponseBean.setStatus(false);
        }
        buResponseBean.setBusinessList(BusinessAL);
        List<Privileges> listPrevileges = user.getPrivileges(roleId, menuId);
        buResponseBean.setPrivilegesList(listPrevileges);
        logger.info("Exited from getBUDetails() ");
        return buResponseBean;
    }

    // UPdate BU .

    public Response updateBusinessUnit(BU_RequestBean buRequestBean) {
        // BU_RequestBean buRequestBean = new BU_RequestBean();
        BU_ResponseBean buResponseBean = new BU_ResponseBean();
        // HrmsBusinessUnits hbu = new HrmsBusinessUnits();
        Main_Businessunits entityObject = new Main_Businessunits();
        try {
            BeanUtils.copyProperties(entityObject, buRequestBean);
            entityObject.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            entityObject.setIsactive(1);
            entityObject.setModifiedBy(buRequestBean.getUserId());

            hbu.updateBU(entityObject);

            // hbu.updateBU(entityObject);
            buResponseBean.setMessage(" Updated Successfully");
            buResponseBean.setStatus(true);

            return Response.status(Response.Status.OK).entity(buResponseBean).build();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            // System.out.println("this is exception" + e1);
            buResponseBean.setMessage("Error while updating - " + e1.getMessage());
            buResponseBean.setStatus(false);
            return Response.status(Response.Status.OK).entity(buResponseBean).build();
        } catch (InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            // System.out.println("this is exception" + e1);
            buResponseBean.setMessage("Error while updating - " + e1.getMessage());
            buResponseBean.setStatus(false);
            return Response.status(Response.Status.OK).entity(buResponseBean).build();
        }

    }

    /*
     * public Response updateBUDetails(Main_Businessunits businessUnit) {
     * BU_RequestBean buRequestBean = new BU_RequestBean(); BU_ResponseBean
     * buResponseBean = new BU_ResponseBean();
     *
     * Main_Businessunits entityObject = new Main_Businessunits();
     * entityObject.setId(buRequestBean.getId());
     * entityObject.setName(buRequestBean.getName());
     * entityObject.setCode(buRequestBean.getCode());
     * entityObject.setDescription(buRequestBean.getDescription());
     * entityObject.setStartDate(buRequestBean.getStartdate());
     *
     * entityObject.setCountry(buRequestBean.getCountry());
     * entityObject.setState(buRequestBean.getState());
     * entityObject.setCity(buRequestBean.getCity());
     *
     *
     * entityObject.setCountry(buRequestBean.getCountry());
     * entityObject.setState(buRequestBean.getState());
     * entityObject.setCity(buRequestBean.getCity());
     * entityObject.setStreetAddress1(buRequestBean.getStreetAddress1());
     * entityObject.setStreetAddress2(buRequestBean.getStreetAddress2());
     * entityObject.setStreetAddress3(buRequestBean.getStreetAddress3());
     * entityObject.setTimezone(buRequestBean.getTimezone());
     * entityObject.setIsactive(buRequestBean.getIsactive());
     *
     * HrmsBusinessDao hrmsBusinessDao = new HrmsBusinessUnits();
     * hrmsBusinessDao.updateBU(businessUnit);
     * buResponseBean.setMessage("1 row Updated Successfully");
     * buResponseBean.setStatus(true); buResponseBean.setId(businessUnit.getId());
     *
     * return Response.status(Response.Status.OK).entity(buResponseBean).build(); }
     */

    // Inserting Data into Database
    public Response saveBUDetails(BU_RequestBean bean) {
        Main_Businessunits entityBean = new Main_Businessunits();
        // HrmsBusinessUnits buInsert = new HrmsBusinessUnits();
        EntityStatusBean response = new EntityStatusBean();

        try {
            BeanUtils.copyProperties(entityBean, bean);
            // Timestamp createddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

            entityBean.setCreateddate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            entityBean.setIsactive(1);
            entityBean.setCreatedby(bean.getUserId());

            boolean inserted = hbu.insertBUDetails(entityBean);
            // System.out.println(inserted);
            if (inserted) {
                response.setMessage("Business Unit Details Saved Successfully.");
                response.setStatus(true);
            } else {
                response.setMessage("Please fill all required fields first And don't Use same Name.");
                response.setStatus(false);
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
            response.setMessage("Error while saving BU details - " + e.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();

        }

    }

    public Response deleteBUDetails(int id, int userId) {
        // HrmsBusinessDao ds = new HrmsBusinessUnits();
        EntityStatusBean response = new EntityStatusBean();
        try {
            if (hbu.deleteBU(id, userId) == null) {
                response.setMessage("Have Some Department Under this Businessunit.Cant Be Deleted.");
                response.setStatus(false);
                return Response.status(Response.Status.OK).entity(response).build();
            } else {
                response.setMessage(hbu.deleteBU(id, userId) + "  BusinessUnit Deleted Successfully");
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

    /*
     * // Department unit according to business unit ....
     *
     * public Response getBUDUInfo(int unitid) { BU_DU_ResponseBean responseBean =
     * new BU_DU_ResponseBean(); HrmsBusinessDao hbd = new HrmsBusinessUnits(); try
     * { List<Main_Departments> listOfDT = hbd.getBuDu(unitid); if (listOfDT.size()
     * != 0) { responseBean.setBUDUList(listOfDT); responseBean.
     * setMessage("Department data fetched based on the business unit list");
     * responseBean.setStatus(true); } else { responseBean.setBUDUList(listOfDT);
     * responseBean.setMessage("Such Department not added in any Business Unit");
     * responseBean.setStatus(false); } } catch (Exception e) { e.printStackTrace();
     * responseBean.setMessage("Error while fetching department unit details - " +
     * e.getMessage()); responseBean.setStatus(false); } return
     * Response.status(Response.Status.OK).entity(responseBean).build();
     *
     * }
     */
    /*
     * Converts Object DataType to String Input - Object returns String If Null
     * returns empty string
     */
    public String objToString(Object obj) {

        if (obj != null)
            return obj.toString();

        return "";
    }

    /*
     * Converts Object DataType to Integer Input - Object returns Integer If Null
     * returns 0
     */
    public Integer objToInteger(Object obj) {

        if (obj != null)
            return Integer.parseInt(obj.toString());

        return 0;
    }
}
