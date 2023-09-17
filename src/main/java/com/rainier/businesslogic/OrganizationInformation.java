package com.rainier.businesslogic;

import com.rainier.beans.CommonResponseBean;
import com.rainier.dao.HrmsOrganizationInfoDao;
import com.rainier.dao.HrmsUserAuthenticate;
import com.rainier.dao.Impl.HrmsOrganizationInfo;
import com.rainier.dao.Impl.HrmsUserAuthentication;
import com.rainier.dto.requestBean.OrganizationInfoRequestBean;
import com.rainier.dto.responseBean.OrganizationHierarchyResponseBean;
import com.rainier.dto.responseBean.OrganizationStructureResponseBean;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.Main_Businessunits;
import com.rainier.entities.OrganizationInfoEntity;
import com.rainier.entities.Privileges;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrganizationInformation {

    private static final Logger logger = Logger.getLogger(HrmsOrganizationInfo.class);

    private final HrmsOrganizationInfoDao db = new HrmsOrganizationInfo();
    private static final HrmsUserAuthenticate user = new HrmsUserAuthentication();
    // Save Update Organization Information
    public Response saveUpdateOrganizationInfo(OrganizationInfoRequestBean bean) {
        logger.info("saveUpdateOrganizationInfo() method in business logic package of Organization Information");
        CommonResponseBean response = new CommonResponseBean();
        if (db.saveUpdateOrganizationInfo(bean)) {
            response.setMessage("Success..!");
            response.setStatus(true);
            return Response.ok().entity(response).build();
        } else {
            response.setMessage("Please Check the Inputs.");
            response.setStatus(false);
            return Response.serverError().entity(response).build();
        }
    }

    // Retrive Organization Information
    public Response getOrganizationInfo(int roleId,int menuId) {
    	CommonResponseBean response = new CommonResponseBean();
    	List<Privileges> listprivilleges = user.getPrivileges(roleId, menuId);
        OrganizationInfoEntity responseBean = db.getOrganizationInfo();
        if (responseBean != null) {
        	
        response.setMessage("Organization Info Data Retrived.");
        response.setStatus(true);
        response.setList(responseBean);
        response.setPrivillegesList(listprivilleges);
        
            return Response.ok().entity(response).build();
        }
        else {
          //  CommonResponseBean response = new CommonResponseBean();
            response.setMessage("Something went wrong!!");
            response.setStatus(false);
            return Response.serverError().entity(response).build();
        }
    }

    // Getting Organiztion Structure
    public Response getOrganizationStructure() {
        List<Main_Businessunits> bu = db.getOrganizationStructure();
        if (bu.isEmpty()) {
            CommonResponseBean response = new CommonResponseBean();
            response.setMessage("Something went wrong!!");
            response.setStatus(false);
            return Response.serverError().entity(response).build();
        } else {
            OrganizationStructureResponseBean response = new OrganizationStructureResponseBean();
            response.setName("Equinox");
            response.setDesignation("IT Catalyst Company");
            response.setSubordinates(bu);
            return Response.ok().entity(response).build();
        }
    }

    public Response getOrganizationHierarchy() {
        List<EmployeeDetailsEntity> organizationHierarchy = db.getOrganizationHierarchy();
        if (organizationHierarchy.isEmpty()) {
            CommonResponseBean response = new CommonResponseBean();
            response.setMessage("Something went wrong!!");
            response.setStatus(false);
            return Response.serverError().entity(response).build();
        } else {
            OrganizationHierarchyResponseBean response = new OrganizationHierarchyResponseBean();
            // Get Root Node
            for (EmployeeDetailsEntity entity : organizationHierarchy) {
                if (entity.getReportingManagerId() == 0) {
                    response.setId(entity.getId());
                    response.setUserId(entity.getUserId());
                    response.setName(entity.getFirstName() + " " + entity.getLastName());
                    response.setDesignation(entity.getJobTitleName());
                    response.setImg(entity.getProfileImg());
                }
            }

            List<Object> subordinates = childWithoutChilds(organizationHierarchy, response);
            // response.setSubordinates(subordinates);
            subordinates.addAll(reportingManagers(organizationHierarchy, response.getUserId()));
            subordinates.sort(new Comparator<Object>() {
                @Override
                public int compare(Object u1, Object u2) {
                    return Integer.compare(((OrganizationHierarchyResponseBean) u2).getUserId(),
                            ((OrganizationHierarchyResponseBean) u1).getUserId());
                }
            }.reversed());
            response.setSubordinates(subordinates);

            // reportingManagers(organizationHierarchy, response.getUserId());
            return Response.ok().entity(response).build();
        }
    }

    private static boolean hasChild(List<EmployeeDetailsEntity> entityList, EmployeeDetailsEntity forNode) {
        boolean returnFlag = false;
        for (EmployeeDetailsEntity entity : entityList) {
            if (entity.getReportingManagerId().equals(forNode.getUserId()))
                returnFlag = true;
        }
        // System.out.println(returnFlag);
        return returnFlag;
    }

    /*
     * private static boolean hasParrent(List<EmployeeDetailsEntity> entityList, int
     * reportingManager) { boolean flag = false; for (EmployeeDetailsEntity entity :
     * entityList) { if (reportingManager == entity.getUserId() &&
     * entity.getReportingManagerId() != 0) { flag = true; } } return flag; }
     */

    private static List<Object> reportingManagers(List<EmployeeDetailsEntity> entityList, int parent) {

        ArrayList<EmployeeDetailsEntity> reportingManagerList = new ArrayList<>();
        List<Integer> repMan = new ArrayList<>();
        List<Object> reportingManagers = new ArrayList<>();

        // Reporting Managers Id
        for (EmployeeDetailsEntity entity : entityList) {
            if (!repMan.contains(entity.getReportingManagerId())) {
                // // System.out.println(entity.getReportingManagerId() + ") " +
                // entity.getReportingManager());
                repMan.add(entity.getReportingManagerId());
            }
        }

        // Reporting Managers List
        for (Integer manager : repMan) {
            OrganizationHierarchyResponseBean response = new OrganizationHierarchyResponseBean();
            if (manager != 0 && manager != 2) {
                for (EmployeeDetailsEntity entity : entityList) {
                    if (entity.getUserId() == manager) {
                        reportingManagerList.add(entity);
                        response.setId(entity.getId());
                        response.setUserId(entity.getUserId());
                        response.setName(entity.getFirstName() + " " + entity.getLastName());
                        response.setDesignation(entity.getJobTitleName());
                        response.setImg(entity.getProfileImg() + "");
                        response.setReportingManagerId(entity.getReportingManagerId());
                    }
                }
                reportingManagers.add(response);
            }
        }
        // Collections.reverse(reportingManagerList);
        Collections.reverse(reportingManagers);

        List<Object> filledReportingManager = new ArrayList<>();
        // Filling Child For entities
        for (Object manager : reportingManagers) {
            OrganizationHierarchyResponseBean manage_r = (OrganizationHierarchyResponseBean) manager;
            // System.out.println(manage_r.getUserId());
            List<Object> childsWithotSubChilds = childWithoutChilds(entityList, manage_r);
            manage_r.setSubordinates(childsWithotSubChilds);
            filledReportingManager.add(manage_r);
        }

        List<Object> operation2 = new ArrayList<>();
        // List<Object> copyListOfOperation1 = filledReportingManager;
        for (Object obj : filledReportingManager) {
            OrganizationHierarchyResponseBean temp = (OrganizationHierarchyResponseBean) obj;
            List<Object> tempList = new ArrayList<>();
            // List<Object> addToOther = new ArrayList<>();
            for (Object obj1 : filledReportingManager) {
                if (((OrganizationHierarchyResponseBean) obj).getUserId() == ((OrganizationHierarchyResponseBean) obj1)
                        .getReportingManagerId()) {
                    tempList.add(obj1);
                    // copyListOfOperation1.remove(obj1);
                }
            }
            tempList.addAll(temp.getSubordinates());
            temp.setSubordinates(tempList);
            if (operation2.contains(temp)) {
                Collections.replaceAll(operation2, obj, temp);
            } else {
                if (temp.getReportingManagerId() == parent)
                    operation2.add(temp);
            }
        }

        return operation2;
    }

    /*
     * private static EmployeeDetailsEntity getParrent(List<EmployeeDetailsEntity>
     * entityList, int reportingManager) { EmployeeDetailsEntity parent = new
     * EmployeeDetailsEntity(); for (EmployeeDetailsEntity entity : entityList) { if
     * (reportingManager == entity.getUserId() && entity.getReportingManagerId() !=
     * 0) { return parent; } } return null; }
     */

    private static List<Object> childWithoutChilds(List<EmployeeDetailsEntity> entityList,
                                                   OrganizationHierarchyResponseBean rootParent) {
        List<Object> subordinates = new ArrayList<>();
        List<Object> noChild = new ArrayList<>();
        for (EmployeeDetailsEntity entity : entityList) {
            if (entity.getReportingManagerId() != 0) {
                if (hasChild(entityList, entity)) {
                    // System.out.println("Have Child");
                } else {
                    OrganizationHierarchyResponseBean response = new OrganizationHierarchyResponseBean();
                    response.setId(entity.getId());
                    response.setUserId(entity.getUserId());
                    response.setName(entity.getFirstName() + " " + entity.getLastName());
                    response.setDesignation(entity.getJobTitleName());
                    response.setImg(entity.getProfileImg() + "");
                    response.setSubordinates(noChild);
                    if (rootParent.getUserId() == entity.getReportingManagerId())
                        subordinates.add(response);
                }
            }
        }
        return subordinates;
    }
}
