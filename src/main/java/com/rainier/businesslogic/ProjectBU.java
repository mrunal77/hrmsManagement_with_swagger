package com.rainier.businesslogic;

import com.rainier.beans.ProjectBean;
import com.rainier.beans.TaskBean;
import com.rainier.dao.Impl.ProjectDaoImpl;
import com.rainier.dao.Impl.ProjectTaskDaoImpl;
import com.rainier.dao.ProjectDao;
import com.rainier.dao.ProjectTaskDao;
import com.rainier.dto.requestBean.DefaultTaskBean;
import com.rainier.dto.requestBean.ProjectEmployeesRequestBean;
import com.rainier.dto.requestBean.ProjectRequestBean;
import com.rainier.dto.responseBean.CommonResponseBean;
import com.rainier.entities.DefaultTaskEntity;
import com.rainier.entities.Project;
import com.rainier.entities.Task;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectBU {

    private static final Logger logger = Logger.getLogger(ProjectBU.class);
    private static final ProjectDao projectDaoImpl = new ProjectDaoImpl();

    public CommonResponseBean getProjectDetails(int clientId) {
        logger.info("inside getProjectDetails of business class");

        List<Project> listProject = projectDaoImpl.getProjectDetails(clientId);
        CommonResponseBean resBean = new CommonResponseBean();
        List<ProjectBean> listProjBean = new ArrayList<ProjectBean>();
        if (!listProject.isEmpty()) {

            Iterator<Project> projItr = listProject.iterator();
            while (projItr.hasNext()) {
                Project project = projItr.next();
                ProjectBean projBean = new ProjectBean();
                projBean.setProjectId(project.getProjectId());
                projBean.setProject_Name(project.getProject_Name());
                projBean.setProject_status(project.getProject_status());
                // projBean.setBaseProject(project.getBaseProject());
                projBean.setDesc(project.getDesc());
                projBean.setClients(project.getClient().getId());
                projBean.setClientName(project.getClient().getClientName());
                projBean.setCurrency(project.getCurrencyId().getCurrencyId());
                projBean.setCurrencyName(project.getCurrencyId().getCurrencyName());
                projBean.setProject_type(project.getProject_type());
                // projBean.setLead_approve_ts(project.getLead_approve_ts());
                projBean.setEstimated_hrs(project.getEstimated_hrs());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                projBean.setStart_date(dateFormat.format(project.getStart_date()));
                projBean.setEnd_date(dateFormat.format(project.getEnd_date()));
                // projBean.setInitiated_date(project.getInitiated_date().toString());
                // projBean.setHold_date(project.getHold_date().toString());
                // projBean.setCompleted_date(project.getCompleted_date().toString());
                // projBean.setCreated_by(project.getCreated_by());
                // projBean.setModified_by(project.getModified_by());
                // projBean.setIs_active(project.getIs_active());
                // projBean.setCreated_date(project.getCreated_date().toString());
                // projBean.setModifiedDate(project.getModifiedDate().toString());

                listProjBean.add(projBean);
            }

            resBean.setList(listProjBean);
            resBean.setMessage("successFully got data...");
            resBean.setStatus(true);
        } else {
            resBean.setMessage("successFully got data...");
            resBean.setStatus(true);
        }

        return resBean;

    }

    private static final ProjectTaskDao projectTaskDao = new ProjectTaskDaoImpl();

    public CommonResponseBean getTaskDetailsBasedOnProjectId(int projectId) {
        CommonResponseBean commonResponseBean = new CommonResponseBean();

        List<TaskBean> listTaskBean = new ArrayList<TaskBean>();
        List<Task> listOfTask = projectTaskDao.getProjectDetails(projectId);
        if (listOfTask.size() > 0) {

            Iterator<Task> itr = listOfTask.iterator();
            while (itr.hasNext()) {
                TaskBean taskBean = new TaskBean();
                Task task = itr.next();
                taskBean.setTaskId(task.getTaskId());
                taskBean.setTaskName(task.getTaskName());
                taskBean.setCreated_by(task.getCreated_by());
                taskBean.setModified_by(task.getModified_by());

                taskBean.setCreatedDate(task.getCreatedDate().toString());
                taskBean.setModifiedDate(task.getModifiedDate().toString());
                taskBean.setIs_default(task.getIs_default());
                // taskBean.setIs_active(task.getIs_active());
                listTaskBean.add(taskBean);
            }
            commonResponseBean.setList(listTaskBean);
            commonResponseBean.setMessage("successFullly fetched Data..");
            commonResponseBean.setStatus(true);
        } else {
            commonResponseBean.setMessage("unable to fetch data..");
            commonResponseBean.setStatus(false);
        }
        return commonResponseBean;

    }

    public Response saveProjectDetailsBU(ProjectRequestBean projectRequestBean) {
        // ProjectDao projectDao = new ProjectDaoImpl();
        CommonResponseBean commonResponseBean = new CommonResponseBean();
        Boolean status = projectDaoImpl.saveProjectDetails(projectRequestBean);
        if (status = true) {
            commonResponseBean.setMessage("successfully inserted..");
            commonResponseBean.setStatus(status);
        } else {
            commonResponseBean.setMessage("not successFully inserted..");
            commonResponseBean.setStatus(status);
        }
        return Response.status(Response.Status.OK).entity(commonResponseBean).build();

    }

    // adding Task.
    public Response addDefaultTask(DefaultTaskBean bean) {
        CommonResponseBean response = new CommonResponseBean();
        DefaultTaskEntity entityBean = new DefaultTaskEntity();
        // ProjectTaskDao dao = new ProjectTaskDaoImpl();

        try {
            BeanUtils.copyProperties(entityBean, bean);
            entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            // entityBean.setIs_active(1);
            entityBean.setIsactive(1);

            boolean add = projectTaskDao.addTask(entityBean);
            if (add == true) {
                response.setMessage("Saved Successfully");
                response.setStatus(true);
            }

            return Response.status(Response.Status.OK).entity(response).build();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            logger.error("Error while Saving Task Details:" + e1.getMessage());
            response.setMessage("Error while Saving Task Details:" + e1.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            logger.error("Error while Saving Task Details:" + e2.getMessage());
            response.setMessage("Error while Saving Task Details:" + e2.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();
        }

    }

    // update default task.
    public Response updateDefaultTask(DefaultTaskBean bean) {
        CommonResponseBean response = new CommonResponseBean();
        DefaultTaskEntity entityBean = new DefaultTaskEntity();
        // ProjectTaskDao dao = new ProjectTaskDaoImpl();
        try {
            BeanUtils.copyProperties(entityBean, bean);

            entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

            // entityBean.setIs_active(1);
            entityBean.setIsactive(1);

            boolean updateTask = projectTaskDao.updateTask(entityBean);
            if (updateTask == true) {

                response.setMessage("Updated Successfully");
                response.setStatus(true);
            }
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            logger.error("Error while updating Task Details:" + e1.getMessage());
            response.setMessage("Error while updating Task Details:" + e1.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            logger.error("Error while updating Task Details:" + e2.getMessage());
            response.setMessage("Error while updating Task Details:" + e2.getMessage());
            response.setStatus(false);
            return Response.status(Response.Status.OK).entity(response).build();
        }

    }

    // Delete Default task.
    public Response deleteDefaultTask(int id) {
        CommonResponseBean responseBean = new CommonResponseBean();
        // ProjectTaskDao dao = new ProjectTaskDaoImpl();
        try {
            logger.info(projectTaskDao.deleteTaskname(id));
            responseBean.setMessage("Deleted Successfully");
            responseBean.setStatus(true);
            return Response.status(Response.Status.OK).entity(responseBean).build();

        } catch (Exception e) {
            logger.info("catch block of deleteDefaultTask of business class::" + e);
            responseBean.setMessage("Error while Deleting Default Task.");
            responseBean.setStatus(false);
            return Response.status(Response.Status.CONFLICT).entity(responseBean).build();
        }

    }

    // get Default task
    public Response getDefaulttaskList() {

        CommonResponseBean responseBean = new CommonResponseBean();
        // DefaultTaskEntity entityBean = new DefaultTaskEntity();
        // ProjectTaskDao dao = new ProjectTaskDaoImpl();
        try {
            List<DefaultTaskEntity> taskList = projectTaskDao.getDefaultTask();
            if (!taskList.isEmpty()) {
                responseBean.setMessage("Default Task Retrived.");
                responseBean.setStatus(true);
                responseBean.setList(taskList);
            } else {
                responseBean.setMessage("Default Task not  Retrived.");
                responseBean.setStatus(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setMessage("Error while fetching Task details.. - " + e.getMessage());
            responseBean.setStatus(false);
        }
        return Response.status(Response.Status.OK).entity(responseBean).build();

    }

    public Response updateProjectDetailsBU(ProjectRequestBean projectRequestBean) {
        // ProjectDao projectDao = new ProjectDaoImpl();
        CommonResponseBean commonResponseBean = new CommonResponseBean();
        Boolean status = projectDaoImpl.saveProjectDetails(projectRequestBean);
        if (status = true) {
            commonResponseBean.setMessage("Project details updated successfully.");
            commonResponseBean.setStatus(status);
        } else {
            commonResponseBean.setMessage("Project details updation Failed.");
            commonResponseBean.setStatus(status);
        }
        return Response.status(Response.Status.OK).entity(commonResponseBean).build();
    }

    // Assign Employees for project
    public Response setEmployees(ProjectEmployeesRequestBean bean) {
        int projectId = bean.getProjectId();
        int[] empId = bean.getEmployeeId();
        // ProjectDao projectDao = new ProjectDaoImpl();
        CommonResponseBean commonResponseBean = new CommonResponseBean();
        if (projectDaoImpl.setEmployeeForProject(projectId, empId)) {
            commonResponseBean.setMessage("Employee assigned for project.");
            commonResponseBean.setStatus(true);
        } else {
            commonResponseBean.setMessage("Something went wrong.");
            commonResponseBean.setStatus(false);
        }
        return Response.status(Response.Status.OK).entity(commonResponseBean).build();
    }
}
