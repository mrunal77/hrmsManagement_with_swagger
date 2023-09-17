package com.rainier.dao.Impl;

import com.rainier.dao.HrmsOrganizationInfoDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.dto.requestBean.OrganizationInfoRequestBean;
import com.rainier.entities.*;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class HrmsOrganizationInfo implements HrmsOrganizationInfoDao {
	// Implementing Logger
	private static final Logger logger = Logger.getLogger(HrmsOrganizationInfo.class);

	// Save Organization Information
	@Override
	public boolean saveUpdateOrganizationInfo(OrganizationInfoRequestBean bean) {
		logger.info("inside of saveUpdateOrganizationInfo of dapImpl ");
		// DbConnect dbConnect = new DbConnect();
		OrganizationInfoEntity entity = new OrganizationInfoEntity();
		try {
			DbConnect.DbCon().beginTransaction();
			if (bean.getId() > 0)
				entity.setId(bean.getId());
			entity.setOrganizationName(bean.getOrganizationName());
			entity.setOrgImage(bean.getOrgImage());
			entity.setDomain(bean.getDomain());
			entity.setWebsite(bean.getWebsite());
			entity.setOrgDescription(bean.getOrgDescription());
			entity.setTotalEmployees(bean.getTotalEmployees());
			entity.setRegistrationNumber(bean.getRegistrationNumber());
			entity.setOrgStartDate(bean.getOrgStartDate());
			entity.setPhoneNumber(bean.getPhoneNumber());
			entity.setSecondaryPhone(bean.getSecondaryPhone());
			entity.setEmail(bean.getEmail());
			entity.setSecondaryEmail(bean.getSecondaryEmail());
			entity.setFaxNo(bean.getFaxNo());
			entity.setCountry(DbConnect.DbCon().get(Tbl_CountriesEntity.class, bean.getCountry()));
			entity.setState(DbConnect.DbCon().get(Tbl_StatesEntity.class, bean.getState()));
			entity.setCity(DbConnect.DbCon().get(Tbl_CitiesEntity.class, bean.getCity()));
			entity.setAddress1(bean.getAddress1());
			entity.setAddress2(bean.getAddress2());
			entity.setAddress3(bean.getAddress3());
			entity.setDescription(bean.getDescription());
			entity.setOrganizationHead(bean.getOrganizationHead());
			entity.setDesignation(bean.getDesignation());
			entity.setBillingAddress(bean.getBillingAddress());
			entity.setBranchAddress(bean.getBranchAddress());
			entity.setCompanyFEINNo(bean.getCompanyFEINNo());
			entity.setCompanyOfficers(bean.getCompanyOfficers());
			entity.setHeadQuarterAddress(bean.getHeadQuarterAddress());
			entity.setNaicsCode(bean.getNaicsCode());
			entity.setStateOfIncorporation(bean.getStateOfIncorporation());
			entity.setCreatedBy(1);
			entity.setModifiedBy(1);
			entity.setCreated_Date(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsActive((short) 1);
		//  DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().update(entity);
			DbConnect.DbCon().getTransaction().commit();

			return true;
		} catch (HibernateException e) {
			logger.info("inside catch block of saveUpdateOrganizationInfo exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
			// DbConnect.dbSessionFactory().close();
		}
	}

	// Getting Organization Information
	@Override
	public OrganizationInfoEntity getOrganizationInfo() {
		logger.info("inside of getOrganizationInfo of daoImpl ");
		// Session session = null;
		// DbConnect dbConnect = new DbConnect();
		OrganizationInfoEntity entity = new OrganizationInfoEntity();
		try {
			// DbConnect.DbCon() = DbConnect.DbCon();
			DbConnect.DbCon().beginTransaction();
			String hql = "from OrganizationInfoEntity where isActive = 1";
			entity = (OrganizationInfoEntity) DbConnect.DbCon().createQuery(hql).uniqueResult();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("organization info fetched...");
			return entity;
		} catch (Exception e) {
			logger.info("inside catch block of getOrganizationInfo exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return entity;
		} finally {
			DbConnect.DbCon().clear();
			// session.close();
			// DbConnect.dbSessionFactory().close();
		}
	}

	// Getting Organiztion Structure Tree
	@Override
	public List<Main_Businessunits> getOrganizationStructure() {
		logger.info("inside of getOrganizationStructure of dapImpl ");
		// Session session = null;
		List<Main_Businessunits> listBusiness = new ArrayList<>();
		// DbConnect dbConnect = new DbConnect();
		try {
			// DbConnect.DbCon() = DbConnect.DbCon();
			DbConnect.DbCon().beginTransaction();
			String hqlBu = "from Main_Businessunits mbu where mbu.isactive = 1";
			List<Main_Businessunits> tempListBu = DbConnect.DbCon().createQuery(hqlBu, Main_Businessunits.class)
					.getResultList();
			for (Main_Businessunits entityBu : tempListBu) {
				String hqlDept = "from Main_Departments md where md.isactive = 1 and md.businessUnitId = "
						+ entityBu.getId();
				List<Main_Departments> listDept = DbConnect.DbCon().createQuery(hqlDept, Main_Departments.class)
						.getResultList();
				entityBu.setSubordinates(listDept);
				listBusiness.add(entityBu);
			}
			DbConnect.DbCon().getTransaction().commit();
			return listBusiness;
		} catch (Exception e) {
			logger.info("inside catch block of getOrganizationStructure exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return listBusiness;
		} finally {
			DbConnect.DbCon().clear();
			// session.close();
			// DbConnect.dbSessionFactory().close();
		}
	}

	// Getting Organization Hierarchy Tree
	@Override
	public List<EmployeeDetailsEntity> getOrganizationHierarchy() {
		logger.info("inside of getOrganizationHierarchy of dapImpl ");
		// Session session = null;
		List<EmployeeDetailsEntity> listHierarchy = new ArrayList<>();
		// DbConnect dbConnect = new DbConnect();
		try {
			/// session = DbConnect.DbCon();
			DbConnect.DbCon().beginTransaction();
			listHierarchy = DbConnect.DbCon()
					.createQuery("from EmployeeDetailsEntity where isactive = 1", EmployeeDetailsEntity.class)
					.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			return listHierarchy;
		} catch (Exception e) {
			logger.info("inside catch block of getOrganizationHierarchy exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return listHierarchy;
		} finally {
			DbConnect.DbCon().clear();
			// session.close();
			// DbConnect.dbSessionFactory().close();
		}
	}

}
