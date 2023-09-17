package com.rainier.dao.Impl;




import java.util.List;

import javax.persistence.Entity;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rainier.beans.NationalityAddBean;
import com.rainier.dao.NationalitySiteConfigdao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.BenchSalesAddEmployeeEntity;
import com.rainier.entities.BenchSalesAddTestimonialsEntity;
import com.rainier.entities.BenchSalesRecruiterEntity;
import com.rainier.entities.NationalityAddEntity;

public class NationalitySiteConfigdaoImpl implements NationalitySiteConfigdao {
	
//Add Nationality Context code
public static Logger logger = Logger.getLogger(NationalitySiteConfigdaoImpl.class);

@Override

public boolean addNationalityContext(NationalityAddBean bean) {
	DbConnect.DbCon().beginTransaction();
	try {
		NationalityAddEntity entity=new NationalityAddEntity();
		entity.setId(bean.getId());
		entity.setNationalitycontext(bean.getNationalitycontext());
		entity.setDescription(bean.getDescription());
		DbConnect.DbCon().saveOrUpdate(entity);
		DbConnect.DbCon().getTransaction().commit();
		logger.info("Nationality context code Successfully");
	return true;
	} catch (HibernateException e) {
		logger.error(".Conflicts  in post NAtionality code..." + e);
		DbConnect.DbCon().getTransaction().rollback();
		return false;
	} finally {
		DbConnect.DbCon().clear();
	}
}

//
//Fetch for Nationality Code Context
@Override
public List<NationalityAddEntity> getAllNationalityCode() {
	
	try {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		String hql = "from NationalityAddEntity";
		Query<NationalityAddEntity> query = DbConnect.DbCon().createQuery(hql,
				NationalityAddEntity.class);
		List<NationalityAddEntity> empList = query.list();
		DbConnect.DbCon().getTransaction().commit();
		logger.info("Retrived Successfully.");
		return empList;
	} catch (HibernateException e) {
		logger.error("Failed to Execute HQL Query:" + e);
		DbConnect.DbCon().getTransaction().rollback();
		return null;
	} finally {
		DbConnect.DbCon().clear();
	}

}
//
//Update for Nationality Code Context
@Override
public boolean saveUpdateNationalityContext(NationalityAddBean bean) {
	Transaction tx = DbConnect.DbCon().beginTransaction();
	try {
		NationalityAddEntity entity = new NationalityAddEntity();
		entity.setId(bean.getId());
		entity.setNationalitycontext(bean.getNationalitycontext());
		entity.setDescription(bean.getDescription());
		DbConnect.DbCon().saveOrUpdate(entity);
		if (tx != null)
			tx.commit();
		logger.info("   Nationality Context Code Updated Successfully");
		return true;

	} catch (HibernateException e) {
		logger.error("  Nationality Context Code Updated  UnSuccessfully");
		tx.rollback();
		return false;
	} finally {
		DbConnect.DbCon().clear();
	}


}

// delete for Nationality Code Context
public boolean deleteNationalityContext(int id) {
	
	Transaction tx=null;
	NationalityAddEntity query=null;
	try {
		tx=DbConnect.DbCon().beginTransaction();
		query=DbConnect.DbCon().load(NationalityAddEntity.class,new Integer(id));
		DbConnect.DbCon().delete(query);
		tx.commit();
		logger.info("Nationality Context Code Deleted  Successfully");
		 return true;
	} catch (Exception e) {
		logger.info("Nationality Context Code Deleted  UnSuccessfully");
		tx.rollback();
		return false;
	} finally {
     DbConnect.DbCon().clear();
	}
	

		

}
}









		

	
	

	

