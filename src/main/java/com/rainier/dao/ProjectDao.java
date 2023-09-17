package com.rainier.dao;

import com.rainier.dto.requestBean.ProjectRequestBean;
import com.rainier.entities.Project;

import java.util.List;

public interface ProjectDao {

	List<Project> getProjectDetails(int clientId);

	Boolean saveProjectDetails(ProjectRequestBean projectBean);

	boolean setEmployeeForProject(int projId, int[] empId);
}
