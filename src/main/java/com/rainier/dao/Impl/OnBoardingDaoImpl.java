package com.rainier.dao.Impl;

import com.rainier.dao.OnBoardingDaoClass;
import com.rainier.dto.requestBean.EmployeeCurrentAddressBean;
import com.rainier.dto.requestBean.OnBoardingJobDetailsRequestBean;
import com.rainier.entities.*;
import org.apache.log4j.Logger;
import org.glassfish.jersey.internal.guava.Lists;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

public class OnBoardingDaoImpl implements OnBoardingDaoClass {
    private final static Logger logger = Logger.getLogger(OnBoardingDaoImpl.class);

    @Override
    public boolean confirmJobDetails(OnBoardingJobDetailsRequestBean request) {
        try {
            logger.info("Inside confirmJobDetails() of Dao Implementation.");
            DbCon().beginTransaction();
            EmployeeDetailsEntity employee = (EmployeeDetailsEntity) DbCon().createCriteria(EmployeeDetailsEntity.class).add(Restrictions.eq("userId", request.getUserId())).uniqueResult();
            DbCon().getTransaction().commit();
            logger.info("Transaction Committed...");
            return request.getJobTitle().equals(employee.getJobtitle_id()) && request.getReportingManager().equals(employee.getReportingManagerId())
                    && request.getPosition().equals(employee.getPosition_id())
                    && request.getEmploymentType() == Integer.parseInt(employee.getEmp_status_id());
        } catch (HibernateException e) {
            logger.error("Hibernate Exception Occurred:" + e);
            DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbCon().clear();
            logger.debug("Final Block Transaction Status :" + DbCon().getTransaction().isActive());
        }
    }

    // Dao Concrete class method.
    @Override
    public boolean saveUpdateCurrentAddress(EmployeeCurrentAddressBean bean) {
        Transaction tx = DbCon().getTransaction();
        try {
            logger.info("Inside confirmJobDetails() of Concrete DAO Class Implementation.");
            tx.begin();
            EmployeeCurrentAddressEntity entity = new EmployeeCurrentAddressEntity();
            entity.setId(bean.getId());
            entity.setUserId(bean.getUserId());
            entity.setCurrentStreetAddress(bean.getCurrentStreetAddress());
            entity.setCurrentCity((Tbl_CitiesEntity) DbCon().createCriteria(Tbl_CitiesEntity.class).add(Restrictions.eq("id", bean.getCurrentCity())).uniqueResult());
            entity.setCurrentCountry((Tbl_CountriesEntity) DbCon().createCriteria(Tbl_CountriesEntity.class).add(Restrictions.eq("id", bean.getCurrentCountry())).uniqueResult());
            entity.setCurrentState((Tbl_StatesEntity) DbCon().createCriteria(Tbl_StatesEntity.class).add(Restrictions.eq("id", bean.getCurrentState())).uniqueResult());
            entity.setCurrentPinCode(bean.getCurrentPinCode());
            entity.setSetAsCurrent(bean.getSetAsDefaultCurrentAddress());
            DbCon().saveOrUpdate(entity);
            tx.commit();
            logger.info("Transaction Committed, Exiting saveUpdateCurrentAddress...");
            return true;
        } catch (Exception e) {
            logger.error("Hibernate Exception Occurred:" + e);
            tx.rollback();
            return false;
        } finally {
            DbCon().clear();
            logger.debug("Final Block Transaction Status :" + DbCon().getTransaction().isActive());
        }
    }

    // Getting Saved List Current Address Of an employee.
    @Override
    public List<EmployeeCurrentAddressEntity> getCurrentAddresList(int userId) {
        logger.info("getCurrentAddressList() of OnBOarding DAO implemetation.");
        Transaction tx = DbCon().getTransaction();
        try {
            tx.begin();
            List<EmployeeCurrentAddressEntity> returnList = DbCon().createCriteria(EmployeeCurrentAddressEntity.class).add(Restrictions.eq("userId", userId)).list();
            tx.commit();
            Collections.reverse(returnList);
            logger.info("Transaction Committed Returning List");
            return returnList;
        } catch (HibernateException e) {
            tx.rollback();
            logger.error("Transaction Rollback: Exception Occurred -> " + e);
            return null;
        } finally {
            DbCon().clear();
        }
    }
}
