package com.rainier.dao.Impl;

import com.rainier.dao.ProjectTaskDao;
import com.rainier.entities.DefaultTaskEntity;
import com.rainier.entities.Task;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static com.rainier.dbconfiguration.DbConnect.DbCon;
import static com.rainier.dbconfiguration.DbConnect.dbSessionFactory;

public class ProjectTaskDaoImpl implements ProjectTaskDao {
    private static final Logger logger = Logger.getLogger(ProjectTaskDaoImpl.class);

    @Override
    public List<Task> getProjectDetails(int projectId) {
        logger.info("inside of getProjectDetails method of daoImpl class ");
        String hql = "select pt.task from TmProjectTask pt where pt.project.projectId=:projId";
        // Session session = null;
        List<Task> taskList = new ArrayList<>();
        try {
            // DbConnect dbConnect = new DbConnect();
            // session = DbConnect.DbCon();
            DbCon().beginTransaction();
            Query<Task> query = DbCon().createQuery(hql, Task.class);
            query.setParameter("projId", projectId);
            taskList = query.getResultList();
            DbCon().getTransaction().commit();
        } catch (Exception e) {
            logger.info("inside of catch block of DaoImpl class getProjectDetails method");
            DbCon().getTransaction().rollback();
        } finally {
            sessionStatus();
        }
        return taskList;
    }

    // Adding Task name;
    @Override
    public boolean addTask(DefaultTaskEntity entity) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            DbCon().beginTransaction();
            DbCon().save(entity);
            DbCon().getTransaction().commit();
            logger.info("Default task added Successfully");
            return true;
        } catch (HibernateException e) {
            logger.error("Failed to get Exexute hql query" + e);
            DbCon().getTransaction().rollback();
            return false;
        } finally {
            sessionStatus();
        }

    }

    private void sessionStatus() {
        DbCon().clear();
        logger.debug("Transaction Status" + DbCon().getTransaction().isActive());
        logger.debug("Session Status :" + DbCon().isOpen());
        logger.debug("Session Factory Status:" + dbSessionFactory().isOpen());
    }

    // update default task .
    public boolean updateTask(DefaultTaskEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbCon().beginTransaction();
            DbCon().update(entity);
            DbCon().getTransaction().commit();
            logger.info("Successfully updated.");
            return true;
        } catch (HibernateException e) {
            logger.error("Failed to get Execute Hql query:" + e);
            DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbCon().clear();
        }
    }

    // Delete Task name.
    @Override
    public String deleteTaskname(int id) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "update DefaultTaskEntity set isactive=:is_active where id=:id";
            DbCon().beginTransaction();
            Query query = DbCon().createQuery(hql);
            query.setParameter("is_active", 0);
            query.setParameter("id", id);
            int flag = query.executeUpdate();
            DbCon().getTransaction().commit();
            return Integer.toString(flag);
        } catch (HibernateException e) {
            logger.error("Failed to get Execute Hql query:" + e);
            DbCon().getTransaction().rollback();
            return null;
        } finally {
            sessionStatus();
        }
    }

    // Fetching Default Task
    @Override
    public List<DefaultTaskEntity> getDefaultTask() {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "from DefaultTaskEntity where isactive=1";
            DbCon().beginTransaction();
            Query<DefaultTaskEntity> query = DbCon().createQuery(hql, DefaultTaskEntity.class);
            List<DefaultTaskEntity> defaultTask = query.getResultList();
            DbCon().getTransaction().commit();
            return defaultTask;
        } catch (HibernateException e) {
            logger.error("Failed to get Execute Hql query:" + e);
            DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbCon().clear();
        }
    }

}
