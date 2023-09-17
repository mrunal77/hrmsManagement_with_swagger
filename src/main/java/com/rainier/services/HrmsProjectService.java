package com.rainier.services;

import com.rainier.businesslogic.ProjectBU;
import com.rainier.dto.requestBean.DefaultTaskBean;
import com.rainier.dto.requestBean.ProjectEmployeesRequestBean;
import com.rainier.dto.requestBean.ProjectRequestBean;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("projectService")
public class HrmsProjectService {
	private static final Logger logger = Logger.getLogger(HrmsProjectService.class);
	private static ProjectBU bu = new ProjectBU();

	@Path("/getProjectDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectDetails(@QueryParam("clientId") int clientId) {
		logger.info("inside getProjectDetails method of service class..");
		return Response.status(Response.Status.OK).entity(bu.getProjectDetails(clientId)).build();

	}

	@Path("getTaskDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTaskDetailsBasedOnProjectId(@QueryParam("projectId") int projectId) {
		logger.info("inside getTaskDetailsBasedOnProjectId of service class ");
		bu.getTaskDetailsBasedOnProjectId(projectId);
		return Response.status(Response.Status.OK).entity(bu.getTaskDetailsBasedOnProjectId(projectId)).build();
	}

	@Path("saveProjectDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveProjectData(ProjectRequestBean projectRequestBean) {
		return bu.saveProjectDetailsBU(projectRequestBean);
	}

	// Update Project Details
	@Path("updateProjectDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProjectData(ProjectRequestBean projectRequestBean) {
		return bu.updateProjectDetailsBU(projectRequestBean);
	}

	// Add task name
	@Path("/AddTaskName")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveDefaulttask(DefaultTaskBean bean) {
		return bu.addDefaultTask(bean);
	}

	// Update task name.
	@Path("/UpdateTaskName")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateDefaultTask(DefaultTaskBean bean) {
		return bu.updateDefaultTask(bean);
	}

	// Delete Task name
	@Path("/DeleteTaskName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deletetDefaulttask(@QueryParam("id") int id) {
		return bu.deleteDefaultTask(id);
	}

	// Fetch task list.
	@Path("/getDefaultTask")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getTaskList() {
		return bu.getDefaulttaskList();
	}

	// Assign Employes For Project
	@Path("setEmployees")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setEmployees(ProjectEmployeesRequestBean bean) {
		return bu.setEmployees(bean);
	}
}
