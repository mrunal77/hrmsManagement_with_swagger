package com.rainier.dao.Impl;

import com.rainier.dao.ProjectDao;
import com.rainier.dto.requestBean.ProjectRequestBean;
import com.rainier.entities.*;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

public class ProjectDaoImpl implements ProjectDao {
	private static final Logger logger = Logger.getLogger(ProjectDaoImpl.class);

	@Override
	public List<Project> getProjectDetails(int cltId) {
		List<Project> listProject;
		logger.info("inside of getProjectDetails method of daoImpl class");
		String hql = "from Project pr where pr.client.id=" + cltId + " and is_active=:isActive";
		// DbConnect dbconnect = new DbConnect();
		// Session session = DbConnect.DbCon();
		try {
			if (cltId == 0)
				hql = "from Project pr where is_active=:isActive";
			DbCon().beginTransaction();
			Query<Project> hqlQuery = DbCon().createQuery(hql, Project.class).setParameter("isActive", true);
			// hqlQuery.setParameter("clientId", cltId);
			listProject = hqlQuery.getResultList();
			DbCon().getTransaction().commit();
			return listProject;
		} catch (Exception e) {
			logger.info("inside catch block og daoImpl class " + e);
			DbCon().getTransaction().rollback();
			return new ArrayList<>();
		} finally {
			logger.debug("Session Status:" + DbCon().isOpen());
			DbCon().clear();
		}
	}

	@Override
	public Boolean saveProjectDetails(ProjectRequestBean projectBean) {
		logger.info("inside of saveProjectDetails of dapImpl ");
		// DbConnect dbConnect = new DbConnect();
		Project project = new Project();
		try {
			DbCon().beginTransaction();
			if (projectBean.getProjectId() > 0)
			project.setProjectId(projectBean.getProjectId());
			project.setProject_Name(projectBean.getProjectName());
			project.setProject_status(projectBean.getProjectStatus());
			// project.setBaseProject(projectBean.getBaseProject());
			project.setDesc(projectBean.getDescription());
			project.setClient(DbCon().get(ClientsEntity.class, projectBean.getClientId()));
			project.setCurrencyId(DbCon().get(Currency.class, projectBean.getCurrencyId()));
			project.setProject_type(projectBean.getProjectType());
			// project.setLead_approve_ts(projectBean.getLeadApproveTs());
			project.setEstimated_hrs(projectBean.getEstimatedHrs());
			project.setStart_date(new SimpleDateFormat("yyyy-MM-dd").parse(projectBean.getStartDate()));
			project.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse(projectBean.getEndDate()));
			// project.setInitiated_date(Timestamp.valueOf(projectBean.getInitiatedDate()));
			// project.setHold_date(Timestamp.valueOf(projectBean.getHold_date()));
			// project.setCompleted_date(Timestamp.valueOf(projectBean.getCompletedDate()));
			project.setIs_active(true);
			project.setCreated_by(projectBean.getCreatedOrModifiedBy());
			project.setCreated_date(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			DbCon().saveOrUpdate(project);
			DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("inside catch block of saveProjectDetails exception:" + e);
			DbCon().getTransaction().rollback();
			return false;
		} finally {
			// DbConnect.DbCon().getTransaction().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session Status:" + DbCon().isOpen());
			DbCon().clear();
		}
		return true;
	}

	@Override
	public boolean setEmployeeForProject(int projId, int[] empId) {
		logger.info("inside of setEmployeeForProject of dapImpl ");
		// Session session = null;
		// DbConnect dbConnect = new DbConnect();
		ProjectEmployeesEntity employees = new ProjectEmployeesEntity();
		try {
			// session = DbConnect.DbCon();
			DbCon().beginTransaction();
			employees.setProjectId(DbCon().get(Project.class, projId));
			for (int id : empId) {
				employees.setEmpId(DbCon().get(User.class, id));
				DbCon().save(employees);
				// session.flush();
				// session.clear();
			}
			DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("inside catch block of setEmployeeForProject exception:" + e);
			DbCon().getTransaction().rollback();
			return false;
		} finally {
			// session.close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session Status:" + DbCon().isOpen());
			DbCon().clear();
		}
		return true;
	}

}
