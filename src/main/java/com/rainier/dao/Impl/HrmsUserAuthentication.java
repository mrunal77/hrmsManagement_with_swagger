package com.rainier.dao.Impl;

import com.rainier.dao.HrmsUserAuthenticate;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.dto.requestBean.PassWordUpadateRequest;
import com.rainier.entities.MenMenu1;
import com.rainier.entities.Privileges;
import com.rainier.entities.UserLoginLogEntity;
import com.rainier.entities.UserRole;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;



import static com.rainier.dbconfiguration.DbConnect.DbCon;

public class HrmsUserAuthentication implements HrmsUserAuthenticate {

	private final static Logger logger = Logger.getLogger(HrmsUserAuthentication.class);

	@Override
	public List<UserRole> UserData(String email, String hashedPassword) {
		logger.info("entered into UserData method of Dao implementation class");
		Transaction tx=null;
		String hql=null;
		Query<UserRole> query=null;
		try {
			tx=DbConnect.DbCon().beginTransaction();
			hql = "select ur from UserRole ur inner join ur.employee u where u.userName=:uname "
					+ "or u.employeeId=:uname and u.password=:passwd and  u.isActive=1";
			query = DbCon().createQuery(hql, UserRole.class);
			query.setParameter("uname", email);
			query.setParameter("passwd", hashedPassword);
			List<UserRole> listUserRole = query.getResultList();
			tx.commit();
			return listUserRole;
		} catch (HibernateException e) {
			logger.info("catch block of UserData method of Dao implementation class::" + e);
			tx.rollback();
			return null;
		} finally {
			logger.debug("Session status: " + DbCon().isOpen());
			DbCon().clear();

		}
	}

	@Override
	public List<Privileges> Objet(int roleId) {
		logger.info("entered into Objet method of Dao implementation class");
		// DbConnect sf = new DbConnect();
		try {
			String hql = "from Privileges where role=:roleId and isActive=1";
			DbCon().beginTransaction();
			Query<Privileges> query = DbCon().createQuery(hql, Privileges.class);
			query.setParameter("roleId", roleId);
			List<Privileges> listObject = query.getResultList();
			DbCon().getTransaction().commit();
			return listObject;
		} catch (HibernateException e) {
			logger.info("catch block of Objet method of Dao implementation class::" + e);
			DbCon().getTransaction().rollback();
			// System.out.println("Error in Fetching Records " + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Transaction status: " + DbCon().getTransaction().isActive());
			DbCon().clear();
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
		}
	}

	@Override
	public List<Privileges> getPrivileges(int roleId, int menuId) {
		logger.info("entered into getPrivileges method of Dao implementation class");
		// DbConnect sf = new DbConnect();
		try {
			String hql = "from Privileges as p where p.role=:roleId and p.obj=:menuId and isActive=1";
			DbCon().beginTransaction();
			Query<Privileges> query = DbCon().createQuery(hql, Privileges.class);
			query.setParameter("roleId", roleId);
			query.setParameter("menuId", menuId);
			List<Privileges> listObject = query.getResultList();
			DbCon().getTransaction().commit();
			logger.info("Priveleges for roleID:" + roleId + " successfully retrieved");
			return listObject;
		} catch (HibernateException e) {
			logger.error("Error in Fetching Records " + e);
			DbCon().clear();
			DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Transaction status: " + DbCon().getTransaction().isActive());
			DbCon().clear();
		}
	}

	/*
	 * public List<MainMenu> MainMenu(ArrayList<Integer> lstPrivileges, int menuId)
	 * { logger.info("entered into MainMenu method of Dao implementation class"); //
	 * DbConnect sf = new DbConnect(); try { String hql =
	 * "from MainMenu where id in (:lst) and parent=:prnt and isactive=1";
	 * DbConnect.DbCon().beginTransaction(); Query<MainMenu> query =
	 * DbConnect.DbCon().createQuery(hql, MainMenu.class); query.setParameter("lst",
	 * lstPrivileges); query.setParameter("prnt", menuId); List<MainMenu> listObject
	 * = query.list(); DbConnect.DbCon().getTransaction().commit(); return
	 * listObject; } catch (HibernateException e) {
	 * logger.info("catch block of MainMenu method of Dao implementation class::" +
	 * e); DbConnect.DbCon().getTransaction().rollback(); // TODO: handle exception
	 * // System.out.println("Error in Fetching Records.: " + e); return null; }
	 * finally { // DbConnect.DbCon().close(); //
	 * DbConnect.dbSessionFactory().close(); //
	 * System.out.println("Session status: " + DbConnect.DbCon().isOpen()); //
	 * System.out.println("Session Factory Status: " + //
	 * DbConnect.dbSessionFactory().isOpen()); } }
	 */

	@Override
	public List<MenMenu1> MainMenu(ArrayList<Integer> lst) {
		logger.info("entered into MainMenu1 method of Dao implementation class");
		// DbConnect sf = new DbConnect();
		try {
			List<MenMenu1> listObject = null;
			if(lst.size() > 0)
			{
				String hql = "from MenMenu1 where id in (:lst) and isActive=1";
				DbCon().beginTransaction();
				Query<MenMenu1> query = DbCon().createQuery(hql, MenMenu1.class);
				//            query.setParameter("lst", lst);
				query.setParameterList("lst", lst);
				listObject = query.list();
				DbCon().getTransaction().commit();
			} 
			return listObject;
		}
		catch (HibernateException e) 
		{
			logger.error("catch block of MainMenu1 method of Dao implementation class::" + e);
			DbCon().getTransaction().rollback();
			// System.out.println("Error in Fetching Records.: " + e);
			return null;
		} 
		finally
		{
			logger.debug("Transaction status: " + DbCon().getTransaction().isActive());
			DbCon().clear();
		}
	}

	/*@Override
    public boolean saveUserLoginlog(UserLoginLogEntity logEntity) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        boolean flagFirstLogin;
        try {
            String hql = "from UserLoginLogEntity ull where ull.userId = " + logEntity.getUserId();
            DbCon().beginTransaction();
            flagFirstLogin = DbCon().createQuery(hql).getResultList().isEmpty();
            DbCon().save(logEntity);
            DbCon().getTransaction().commit();
            return flagFirstLogin;
        } catch (HibernateException e) {
            logger.error("catch block of saveUserLoginlog method of Dao implementation class::" + e);
            DbCon().getTransaction().rollback();
            return false;
        } finally {
            logger.debug("Transaction Status" + DbCon().getTransaction().isActive());
            DbCon().clear();
        }
    }*/
	@Override
	public boolean saveUserLoginlog(UserLoginLogEntity logEntity) {
		boolean flagFirstLogin=false;
		Transaction tx=DbConnect.DbCon().beginTransaction();
		try {
			String hql = "from UserLoginLogEntity ull where ull.userId = " + logEntity.getUserId();

			flagFirstLogin = DbCon().createQuery(hql).getResultList().isEmpty();
			DbCon().save(logEntity);
			if(tx!=null)
				tx.commit();
			return flagFirstLogin;
		} catch (HibernateException e) {
			logger.error("catch block of saveUserLoginlog method of Dao implementation class::" + e);
			if(tx!=null)
				tx.rollback();
			return false;
		} finally {
			logger.debug("Transaction Status" + DbCon().getTransaction().isActive());
			DbCon().clear();
		}
	}

	@Override
	public boolean updateCurrentPassword(String oldPassword, String newPassword ,int id) {
		logger.info("entered into updateCurrentPassword method of Dao implementation class::");
		Transaction tx=null;
		try {
			tx=DbConnect.DbCon().beginTransaction();
			logger.info(oldPassword);
			logger.info(newPassword);
			logger.info(id);
			String hqlUpdatePassword = "update User u set u.password = :newPassword where u.password = :oldPassword and u.Id="+id+"";
			 DbConnect.DbCon().createQuery(hqlUpdatePassword)
			.setParameter("oldPassword", oldPassword).setParameter("newPassword", newPassword).executeUpdate();
		     tx.commit();
			logger.info("Password Changed...");
			return true;
		} catch (HibernateException e) {
			logger.error("catch block of updateCurrentPassword method of Dao implementation class::" + e);
			tx.rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
			
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String checkEmailIsSame(String email) {
		String hql=null;
		String employeeEmail=null;
		try{
			hql="Select distinct(emailaddress) from main_users where emailaddress=:email";
			 employeeEmail=(String) DbConnect.DbCon().createSQLQuery(hql).setParameter("email", email).uniqueResult();
			 return employeeEmail;
		}catch(HibernateException e) {
			return null;
		}finally {
			DbConnect.DbCon().clear();
		}
	}
	
	public String getLastNameOfEmp(String email) {
		String hql=null;
		String employeeLastName=null;
		try{
			hql="Select lastname from main_users where emailaddress=:email";
			employeeLastName=(String) DbConnect.DbCon().createSQLQuery(hql).setParameter("email", email).uniqueResult();
			 return employeeLastName;
		}catch(HibernateException e) {
			return null;
		}finally {
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public boolean updatePassword(String email,String newPassWord) {
		logger.info("Eneterd Into updatePassword() in HrmsUserAuthentication class"  );
		Transaction tx=null;
		String hql=null;
		try {
			tx=DbConnect.DbCon().beginTransaction();
			hql="update User u set u.password =:newPassWord where u.userName='"+email+"'";
			 DbConnect.DbCon().createQuery(hql)
				.setParameter("newPassWord", newPassWord).executeUpdate();
			 tx.commit();
			return true;
		}catch(HibernateException e)
		{
		tx.rollback();
		return false;
		}finally {
			DbConnect.DbCon().clear();
		}
	
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer getUserIdOfEmp(String email) {
		String hql=null;
		Integer id=null;
		try{
			hql="Select id from main_users where emailaddress=:email";
			id=(Integer) DbConnect.DbCon().createSQLQuery(hql).setParameter("email", email).uniqueResult();
			 return id;
		}catch(HibernateException e) {
			return null;
		}finally {
			DbConnect.DbCon().clear();
		}
	}
}
