package com.rainier.dao.Impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rainier.beans.BenchSalesAddCandidateBean;
import com.rainier.beans.BenchSalesAddEmployeeBean;
import com.rainier.beans.BenchSalesAddTestimonialsBean;
import com.rainier.beans.BenchSalesVendorCandidateMappingBean;
import com.rainier.beans.BenchSalesVendorDetailsBean;
import com.rainier.beans.CandSubmissionBean;
import com.rainier.dao.HrmsBenchSalesDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.dto.requestBean.CandidateSubmissionCount;
import com.rainier.dto.responseBean.BenchSalesVendorDetailsEntityResponse;
import com.rainier.dto.responseBean.CandidateRecruitersResponse;
import com.rainier.dto.responseBean.CandidateResponseObject;
import com.rainier.dto.responseBean.CandidateSalesManagerResponse;
import com.rainier.dto.responseBean.CityStateResponseBean;
import com.rainier.dto.responseBean.VendorSalesExecutiveResponseBean;
import com.rainier.entities.BenchSalesAddCandidateEntity;
import com.rainier.entities.BenchSalesAddEmployeeEntity;
import com.rainier.entities.BenchSalesAddTestimonialsEntity;
import com.rainier.entities.BenchSalesMailLogEntity;
import com.rainier.entities.BenchSalesRecruiterEntity;
import com.rainier.entities.BenchSalesVendorCandidateMappingEntity;
import com.rainier.entities.BenchSalesVendorDetailsEntity;
import com.rainier.entities.CandidateSalesExecutiveEntity;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.Privileges;
import com.rainier.entities.Tbl_CitiesEntity;
import com.rainier.entities.User;
import com.rainier.response.VendorDetailsResponse;
import com.rainier.utility.MD5Generator;

public class HrmsBenchSalesDaoImpl implements HrmsBenchSalesDao {

	public static Logger logger = Logger.getLogger(HrmsBenchSalesDaoImpl.class);

	// Save And Update Employee in BenchSales
	@Override
	public boolean saveUpdateAddEmployee(BenchSalesAddEmployeeBean bean) {
		DbConnect.DbCon().beginTransaction();
		try {
			BenchSalesAddEmployeeEntity entity = new BenchSalesAddEmployeeEntity();
			MD5Generator generator = new MD5Generator();
			entity.setAddress(bean.getAddress());
			entity.setComment(bean.getComment());
			entity.setConfirmPassword(generator.generate(bean.getConfirmPassword()));
			entity.setDob(bean.getDob());
			entity.setFirstName(bean.getFirstName());
			entity.setGender(bean.getGender());
			entity.setId(bean.getId());
			entity.setJoiningDate(bean.getJoiningDate());
			entity.setLastName(bean.getLastName());
			entity.setMaritalStatus(bean.getMaritalStatus());
			entity.setOfficialEmailId(bean.getOfficialEmailId());
			entity.setPassword(generator.generate(bean.getPassword()));
			entity.setPersonalEmailId(bean.getPersonalEmailId());
			entity.setPhoneEmpNo(bean.getPhoneEmpNo());
			entity.setPhoneEmergencyNo(bean.getPhoneEmergencyNo());
			entity.setUploadPic(bean.getUploadPic());
			entity.setRole(bean.getRole());
			entity.setEmergencyName(bean.getEmergencyName());
			entity.setEmployeeNo(bean.getEmployeeNo());
			entity.setIsActive(bean.getIsActive());
			entity.setRelation(bean.getRelation());

			DbConnect.DbCon().saveOrUpdate(entity);

			DbConnect.DbCon().getTransaction().commit();
			logger.info(" Employee Inserted Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("Conflicts in Post Profile...." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// mail log for Bench sales Service.

	@Override
	public void sendingMailByBenchSalesAdmin(BenchSalesMailLogEntity entity) {
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(entity);

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Email Log Insertion Successful.");
		} catch (HibernateException e) {
			logger.error("Conflicts in saving MailLog." + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}

	}

	// fetch Addded Employee List.
	@Override
	public List<BenchSalesAddEmployeeEntity> fetchAddedEmployee() {
		try {
			Transaction tx = DbConnect.DbCon().beginTransaction();
			String hql = "from BenchSalesAddEmployeeEntity";
			Query<BenchSalesAddEmployeeEntity> query = DbConnect.DbCon().createQuery(hql,
					BenchSalesAddEmployeeEntity.class);
			List<BenchSalesAddEmployeeEntity> empList = query.list();
			tx.commit();
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

	// Bench Sales Add Recruiter name.
	@Override
	public boolean addRecruiterName(BenchSalesRecruiterEntity entity) {
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Recruiters Added.");
			return true;

		} catch (HibernateException e) {
			logger.error("Failed to save Recruiter name.");
			DbConnect.DbCon().getTransaction().rollback();
			return false;

		} finally {
			DbConnect.DbCon().clear();

		}

	}

	// Fetch Recruiters Service .
	@Override
	public List<BenchSalesRecruiterEntity> getAllRecruiters() {
		// TODO Auto-generated method stub
		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {

			String hql = "from BenchSalesRecruiterEntity";
			Query<BenchSalesRecruiterEntity> query = DbConnect.DbCon().createQuery(hql,
					BenchSalesRecruiterEntity.class);
			List<BenchSalesRecruiterEntity> empList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Retrived Successfully.");
			tx.commit();
			return empList;
		} catch (HibernateException e) {
			logger.error("Failed to Execute HQL Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Delete For Recruiters Service
	@Override
	public int deleteRecruiters(int id) {
		Transaction tx = null;
		BenchSalesRecruiterEntity query = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().load(BenchSalesRecruiterEntity.class, new Integer(id));
			DbConnect.DbCon().delete(query);
			tx.commit();
			logger.info("Deleted Recruiters Details Successfully");
			return 1;
		} catch (Exception e) {
			logger.info("Deleted Recruiters Details unSuccessfully");
			tx.rollback();
			return 0;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Delete For Employee Service
	@Override
	public String deleteEmployee(int id) {
		Transaction tx = null;
		BenchSalesAddEmployeeEntity query = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().load(BenchSalesAddEmployeeEntity.class, new Integer(id));
			DbConnect.DbCon().delete(query);
			tx.commit();
			logger.info("Deleted Employee Details Successfully");
			return "Deleted SuccessFully";
		} catch (Exception e) {
			logger.info("Deleted Employee Details unSuccessfully");
			tx.rollback();
			return "Deleted unSuccessFully";
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Bench Sales Update Candidate
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveUpdateCandidate(BenchSalesAddCandidateBean bean) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		List<User> salesExecutives = null;
		User salesExecutive = null;
		List<Object[]> recruiterNames = null;
		List<Integer> idList = null;
		String recruiterName = null;
		BenchSalesAddCandidateEntity entity = null;
		try {
			salesExecutives = new ArrayList<>();
			idList = bean.getIdList();
			recruiterNames = DbConnect.DbCon().createQuery("select userfullName from User where Id in(:idList) ")
					.setParameterList("idList", idList).list();
			Iterator<Object[]> rec = recruiterNames.iterator();
			if (bean.getId() > 0) {
				while (rec.hasNext()) {
					Object object = (Object) rec.next();
					recruiterName = (String) object;
					String hql = "from User where userfullName='" + recruiterName + "'";
					salesExecutive = DbConnect.DbCon().createQuery(hql, User.class).getSingleResult();
					salesExecutives.add(salesExecutive);
					entity = new BenchSalesAddCandidateEntity();
					entity.setId(bean.getId());
					entity.setFirstName(bean.getFirstName());
					entity.setCellPhone(bean.getCellPhone());
					entity.setAvailability(bean.getAvailability());
					entity.setCity(bean.getCity());
					entity.setComments(bean.getComments());
					entity.setDateOfBirth(bean.getDateOfBirth());
					entity.setCurrentLocation(bean.getCurrentLocation());
					entity.setEmailAddress(bean.getEmailAddress());
					entity.setHomePhone(bean.getHomePhone());
					entity.setHotListDate(bean.getHotListDate());
					entity.setDomain(bean.getDomain());
					entity.setLastName(bean.getLastName());
					entity.setMiddleName(bean.getMiddleName());
					entity.setRefernce(bean.getRefernce());
					entity.setReLocation(bean.getReLocation());
					entity.setSsn(bean.getSsn());
					entity.setUploadProfile(bean.getUploadProfile());
					entity.setWorkPhone(bean.getWorkPhone());
					entity.setdeleted(bean.isdeleted());
					entity.setRecruiters(salesExecutives);
				}
				DbConnect.DbCon().saveOrUpdate(entity);
			} else {
				while (rec.hasNext()) {
					Object object = (Object) rec.next();
					recruiterName = (String) object;
					String hql = "from User where userfullName='" + recruiterName + "'";
					salesExecutive = DbConnect.DbCon().createQuery(hql, User.class).getSingleResult();
					salesExecutives.add(salesExecutive);
					entity = new BenchSalesAddCandidateEntity();
					entity.setId(bean.getId());
					entity.setFirstName(bean.getFirstName());
					entity.setCellPhone(bean.getCellPhone());
					entity.setAvailability(bean.getAvailability());
					entity.setCity(bean.getCity());
					entity.setComments(bean.getComments());
					entity.setDateOfBirth(bean.getDateOfBirth());
					entity.setCurrentLocation(bean.getCurrentLocation());
					entity.setEmailAddress(bean.getEmailAddress());
					entity.setHomePhone(bean.getHomePhone());
					entity.setHotListDate(bean.getHotListDate());
					entity.setDomain(bean.getDomain());
					entity.setLastName(bean.getLastName());
					entity.setMiddleName(bean.getMiddleName());
					entity.setRefernce(bean.getRefernce());
					entity.setReLocation(bean.getReLocation());
					entity.setSsn(bean.getSsn());
					entity.setUploadProfile(bean.getUploadProfile());
					entity.setWorkPhone(bean.getWorkPhone());
					entity.setdeleted(bean.isdeleted());
					entity.setRecruiters(salesExecutives);
				}
				DbConnect.DbCon().save(entity);
			}
			tx.commit();
			logger.info("Candidate Insertd Successfully");
			return true;
		} catch (HibernateException e) {
			logger.error("Conflict in Post candidate");
			tx.rollback();

			return false;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// BenchSales Fetch CandidateList
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<BenchSalesAddCandidateBean> retriveCandidateList() {
		Transaction tx = null;
		// List<Object[]> query = null;
		List<BenchSalesAddCandidateBean> canList = null;
		List<Object[]> usersMinilist = null;
		String hql2 = null;
		String hql = null;
		try {
			canList = new ArrayList<>();
			tx = DbConnect.DbCon().beginTransaction();
			String hql1 = "select distinct(id) from BenchSalesAddCandidateEntity where deleted='0'";
			List<BenchSalesAddCandidateEntity> objectsIterator = (List<BenchSalesAddCandidateEntity>) DbConnect.DbCon()
					.createQuery(hql1).list();
			Iterator<BenchSalesAddCandidateEntity> iterator1 = objectsIterator.iterator();
			while (iterator1.hasNext()) {
				Object object = (Object) iterator1.next();
				if (object != null) {
					int canId = (int) object;
					if (canId != 0) {
						hql = "select distinct(id),firstName,emailAddress,cellPhone,domain,middleName,lastName,dateOfBirth,homePhone,workPhone,city,currentLocation,reLocation,"
								+ "hotListDate,ssn,availability,uploadProfile,refernce,comments from BenchSalesAddCandidateEntity where id="
								+ canId + " and deleted='0'";
						List<Object[]> query = (List<Object[]>) DbConnect.DbCon().createSQLQuery(hql).getResultList();
						logger.info(query);
						if (query != null) {
							for (Object[] ob : query) {
								List<User> userList = new ArrayList<>();
								BenchSalesAddCandidateBean canBean = new BenchSalesAddCandidateBean();
								int id2 = (Integer) ob[0];
								String firstName = (String) ob[1];
								String emailAddress = (String) ob[2];
								String cellPhone = (String) ob[3];
								String domain = (String) ob[4];
								String middleName = (String) ob[5];
								String lastName = (String) ob[6];
								Date dateOfBirth = (Date) ob[7];
								String homePhone = (String) ob[8];
								String workPhone = (String) ob[9];
								String city = (String) ob[10];
								String currentLocation = (String) ob[11];
								String reLocation = (String) ob[12];
								Date hotListDate = (Date) ob[13];
								String ssn = (String) ob[14];
								String availability = (String) ob[15];
								String uploadProfile = (String) ob[16];
								String refernce = (String) ob[17];
								String comments = (String) ob[18];
								canBean.setId(id2);
								canBean.setFirstName(firstName);
								canBean.setEmailAddress(emailAddress);
								canBean.setCellPhone(cellPhone);
								canBean.setDomain(domain);
								canBean.setMiddleName(middleName);
								canBean.setLastName(lastName);
								canBean.setDateOfBirth(dateOfBirth);
								canBean.setHomePhone(homePhone);
								canBean.setWorkPhone(workPhone);
								canBean.setCity(city);
								canBean.setCurrentLocation(currentLocation);
								canBean.setReLocation(reLocation);
								canBean.setHotListDate(hotListDate);
								canBean.setSsn(ssn);
								canBean.setAvailability(availability);
								canBean.setUploadProfile(uploadProfile);
								canBean.setRefernce(refernce);
								canBean.setComments(comments);
								hql2 = "select distinct(r.id),r.userfullname from main_users as r inner join candidate_sales_executive as cr on r.id=cr.rec_id where cr.can_id="
										+ canId + "";
								usersMinilist = DbConnect.DbCon().createSQLQuery(hql2).list();
								for (Object[] ob2 : usersMinilist) {
									int id = (Integer) ob2[0];
									String userFullName = (String) ob2[1];
									User user = new User();
									user.setId(id);
									user.setUserfullName(userFullName);
									userList.add(user);

									int saleManId = 0;
									int id1 = 0;
									for (User user1 : userList) {
										id1 = user1.getId();
									}
									logger.info(id1);
									String getSaleManIdQuery = "select reporting_manager from main_employees_summary Where user_id=:id1 ";
									saleManId = (int) DbConnect.DbCon().createSQLQuery(getSaleManIdQuery)
											.setParameter("id1", id1).uniqueResult();
									String getSaleManIdName = "select id,userfullname from main_users where id=:saleManId";
									List<Object[]> user1 = DbConnect.DbCon().createSQLQuery(getSaleManIdName)
											.setParameter("saleManId", saleManId).list();
									for (Object[] ob1 : user1) {
										int id3 = (Integer) ob1[0];
										String userName = (String) ob1[1];
										for (BenchSalesAddCandidateBean addSaleManIdName : canList) {
											addSaleManIdName.setSalId(id3);
											addSaleManIdName.setSalName(userName);
										}
									}
								}
								canBean.setRecruiters(userList);
								canList.add(canBean);
							}
						}
					}
				}
			}
			tx.commit();
			logger.info("Retrived Successfully");
			return canList;
		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// BenchSales Delete Candidate
	@Override
	public void deleteCandidate(int id) {
		Transaction tx = null;
		String hql = null;
		Query query = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "UPDATE BenchSalesAddCandidateEntity SET deleted = '1'  WHERE deleted='0' and  id =" + id + "";
			query = DbConnect.DbCon().createQuery(hql);
			query.executeUpdate();
			tx.commit();
			logger.info("Deleted SuccessFully");
		} catch (HibernateException e) {
			logger.error("Deleted UnSuccessfully");
			tx.rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Save And Update Testimonials Service
	@Override
	public boolean saveUpdateTestimonials(BenchSalesAddTestimonialsBean bean) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			BenchSalesAddTestimonialsEntity entity = new BenchSalesAddTestimonialsEntity();
			entity.setId(bean.getId());
			entity.setName(bean.getName());
			entity.setDesignation(bean.getDesignation());
			entity.setDescription(bean.getDescription());
			entity.setIsActive(bean.getIsActive());
			DbConnect.DbCon().saveOrUpdate(entity);
			if (tx != null)
				tx.commit();
			logger.info("Save And Update  Testimonials Successfully");
			return true;

		} catch (HibernateException e) {
			logger.error("Save And Update  Testimonials UnSuccessfully");
			tx.rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Fetch Testimonilas Services
	@Override
	public List<BenchSalesAddTestimonialsEntity> fetchTestimonilasList() {
		Transaction tx = null;
		Query<BenchSalesAddTestimonialsEntity> query = null;
		List<BenchSalesAddTestimonialsEntity> list = null;
		String hql = "from BenchSalesAddTestimonialsEntity";
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().createQuery(hql, BenchSalesAddTestimonialsEntity.class);
			list = query.list();
			tx.commit();

			logger.info("Retrived Successfully");
			return list;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Service For Delete Testimonials
	@Override
	public boolean deleteTestimonials(int id) {
		Transaction tx = null;
		BenchSalesAddTestimonialsEntity query = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().load(BenchSalesAddTestimonialsEntity.class, new Integer(id));
			DbConnect.DbCon().delete(query);
			tx.commit();
			logger.info("Deleted Successfully");
			return true;
		} catch (HibernateException e) {
			logger.error("Deleted UnSuccessfully");
			tx.rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// get Candidate Based On The Recruiters That Are Under On that Recruiters
	@Override
	public Set<CandidateRecruitersResponse> getCandidateList(int recId) {
		Transaction tx = null;
		List<CandidateRecruitersResponse> query = null;
		Set<CandidateRecruitersResponse> setObject = null;
		List<Object[]> query1 = null;
		String hql = null;
		String hql1 = null;
		List<Object[]> recruiters = null;
		try {

			tx = DbConnect.DbCon().beginTransaction();
			query = new ArrayList<>();
			hql1 = "Select distinct(c.id),c.availability,c.cellPhone,c.city,c.comments,c.currentLocation,cr.status,cr.reqReqId,"
					+ "c.dateOfBirth,c.domain,c.emailAddress,c.hotListDate,c.lastName,c.homePhone,c.ssn,c.firstName"
					+ " from BenchSalesAddCandidateEntity as c "
					+ "left join candidate_sales_executive as cr on c.id=cr.can_id and c.deleted='0' where cr.rec_id="
					+ recId + "";
			query1 = DbConnect.DbCon().createSQLQuery(hql1).list();
			for (Object[] ob : query1) {
				CandidateRecruitersResponse benchCan = new CandidateRecruitersResponse();
				List<User> users = new ArrayList<>();
				int id = (int) ob[0];
				String availablity = (String) ob[1];
				String cellPhone = (String) ob[2];
				String city = (String) ob[3];
				String comments = (String) ob[4];
				String currentLocation = (String) ob[5];
				Integer status = (Integer) ob[6];
				Integer reqReqId = (Integer) ob[7];
				Date dateOfBirth = (Date) ob[8];
				String domain = (String) ob[9];
				String emailAddress = (String) ob[10];
				Date hotListDate = (Date) ob[11];
				String lastName = (String) ob[12];
				String homePhone = (String) ob[13];
				String ssn = (String) ob[14];
				String firstName = (String) ob[15];
				benchCan.setId(id);
				benchCan.setAvailability(availablity);
				benchCan.setCellPhone(cellPhone);
				benchCan.setCity(city);
				benchCan.setComments(comments);
				benchCan.setCurrentLocation(currentLocation);
				benchCan.setDateOfBirth(dateOfBirth);
				benchCan.setDomain(domain);
				benchCan.setEmailAddress(emailAddress);
				benchCan.setHotListDate(hotListDate);
				benchCan.setLastName(lastName);
				benchCan.setHomePhone(homePhone);
				benchCan.setSsn(ssn);
				benchCan.setFirstName(firstName);
				benchCan.setStatus(status);
				if (reqReqId != null) {
					benchCan.setReqReqId(reqReqId);
				}
				hql = "select distinct(r.id), r.userfullname from main_users as r inner join candidate_sales_executive as cr on r.id=cr.rec_id where cr.can_id="
						+ id + "";
				recruiters = DbConnect.DbCon().createSQLQuery(hql).list();
				for (Object[] u : recruiters) {
					User u1 = new User();
					int id1 = (int) u[0];
					String recName = (String) u[1];
					u1.setId(id1);
					u1.setUserfullName(recName);
					users.add(u1);

					String getSaleManIdQuery = "select reporting_manager from main_employees_summary Where user_id=:recId ";
					int saleManId = (int) DbConnect.DbCon().createSQLQuery(getSaleManIdQuery)
							.setParameter("recId", recId).uniqueResult();

					String getSaleManIdName = "select distinct(id),userfullname from main_users where id=:saleManId";
					List<Object[]> user1 = DbConnect.DbCon().createSQLQuery(getSaleManIdName)
							.setParameter("saleManId", saleManId).list();
					for (Object[] ob1 : user1) {
						int id2 = (Integer) ob1[0];
						String userName = (String) ob1[1];
						for (CandidateRecruitersResponse addSaleManIdName : query) {
							addSaleManIdName.setSalId(id2);
							addSaleManIdName.setSalName(userName);
						}
					}
				}
				benchCan.setRecruiters(users);
				query.add(benchCan);
				setObject = new HashSet<>(query);
			}

			logger.info("Candidate List Retrived SuccessFully That are  Their In That ReCruiters");
			tx.commit();
			return setObject;

		} catch (HibernateException e) {
			logger.error("Candidate List Retrived UnSuccessFully That are  Their In That ReCruiters");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Candidate List Will Come That Are Not Their In That Recruiters
	@Override
	public Set<CandidateRecruitersResponse> getCandidate(int recId) {
		Transaction tx = null;
		List<CandidateRecruitersResponse> query = null;
		Set<CandidateRecruitersResponse> setObject = null;
		List<Object[]> query1 = null;
		String hql = null;
		String hql1 = null;
		List<Object[]> recruiters = null;
		try {

			tx = DbConnect.DbCon().beginTransaction();
			query = new ArrayList<>();

			hql1 = "select distinct(c.id),c.availability,c.cellPhone,c.city,c.comments,c.currentLocation,cr.status,cr.reqReqId,\r\n"
					+ "				c.dateOfBirth,c.domain,c.emailAddress,c.hotListDate,c.lastName\r\n"
					+ "                ,c.homePhone,c.ssn,c.firstName\r\n"
					+ "					 from BenchSalesAddCandidateEntity as c\r\n"
					+ "right join candidate_sales_executive as cr on c.id=cr.can_id \r\n"
					+ "where c.deleted='0' and c.id not in (select c.id from BenchSalesAddCandidateEntity as c\r\n"
					+ "right join candidate_sales_executive as cr on c.id=cr.can_id  where cr.rec_id=" + recId + ")\r\n"
					+ "\r\n" + "";
			query1 = DbConnect.DbCon().createSQLQuery(hql1).list();

			for (Object[] ob : query1) {

				CandidateRecruitersResponse benchCan = new CandidateRecruitersResponse();
				List<User> users = new ArrayList<>();
				int id = (int) ob[0];
				String availablity = (String) ob[1];
				String cellPhone = (String) ob[2];
				String city = (String) ob[3];
				String comments = (String) ob[4];
				String currentLocation = (String) ob[5];
				Integer status = (Integer) ob[6];
				Integer reqReqId = (Integer) ob[7];
				Date dateOfBirth = (Date) ob[8];
				String domain = (String) ob[9];
				String emailAddress = (String) ob[10];
				Date hotListDate = (Date) ob[11];
				String lastName = (String) ob[12];
				String homePhone = (String) ob[13];
				String ssn = (String) ob[14];
				String firstName = (String) ob[15];
				benchCan.setId(id);
				benchCan.setAvailability(availablity);
				benchCan.setCellPhone(cellPhone);
				benchCan.setCity(city);
				benchCan.setComments(comments);
				benchCan.setCurrentLocation(currentLocation);
				benchCan.setDateOfBirth(dateOfBirth);
				benchCan.setDomain(domain);
				benchCan.setEmailAddress(emailAddress);
				benchCan.setHotListDate(hotListDate);
				benchCan.setLastName(lastName);
				benchCan.setHomePhone(homePhone);
				benchCan.setSsn(ssn);
				benchCan.setFirstName(firstName);
				benchCan.setStatus(status);
				if (reqReqId != null) {
					benchCan.setReqReqId(reqReqId);
				}

				hql = "select distinct(r.id),r.userfullname from main_users as r inner join candidate_sales_executive as cr on r.id=cr.rec_id where cr.can_id="
						+ id + "";
				recruiters = DbConnect.DbCon().createSQLQuery(hql).list();
				for (Object[] u : recruiters) {
					User u1 = new User();
					int id1 = (int) u[0];
					String recName = (String) u[1];
					u1.setId(id1);
					u1.setUserfullName(recName);
					users.add(u1);
					String getSaleManIdQuery = "select reporting_manager from main_employees_summary Where user_id=:id1 ";
					int saleManId = (int) DbConnect.DbCon().createSQLQuery(getSaleManIdQuery).setParameter("id1", id1)
							.uniqueResult();

					String getSaleManIdName = "select id,userfullname from main_users where id=:saleManId";
					List<Object[]> user1 = DbConnect.DbCon().createSQLQuery(getSaleManIdName)
							.setParameter("saleManId", saleManId).list();
					for (Object[] ob1 : user1) {
						int id2 = (Integer) ob1[0];
						String userName = (String) ob1[1];
						for (CandidateRecruitersResponse addSaleManIdName : query) {
							addSaleManIdName.setSalId(id2);
							addSaleManIdName.setSalName(userName);
						}
					}
				}
				benchCan.setRecruiters(users);

				query.add(benchCan);
				setObject = new HashSet<>(query);

			}

			logger.info("Candidate List Retrived SuccessFully That are Not Their In That ReCruiters");
			tx.commit();
			return setObject;

		} catch (HibernateException e) {
			logger.error("Candidate List Retrived UnSuccessFully That are Not Their In That ReCruiters");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Get Privileges For Candidate
	@Override
	public Privileges getPriviligesForCand(int id, int menuId) {
		Transaction tx = null;
		Privileges priv = null;
		String hql = null;
		hql = "from Privileges where role=:role and obj=:obj";
		try {
			tx = DbConnect.DbCon().beginTransaction();
			priv = DbConnect.DbCon().createQuery(hql, Privileges.class).setParameter("obj", menuId)
					.setParameter("role", id).uniqueResult();
			tx.commit();
			return priv;
		} catch (HibernateException e) {
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Save Vendor List
	@Override
	public int saveVendorList(BenchSalesVendorDetailsBean bean) {
		Transaction tx = null;
		BenchSalesVendorDetailsEntity entity = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			entity = new BenchSalesVendorDetailsEntity();
			entity.setVendorName(bean.getVendorName());
			entity.setEmailAddress(bean.getEmailAddress());
			entity.setPhoneNo(bean.getPhoneNo());
			entity.setClientName(bean.getClientName());
			entity.setLocation(bean.getLocation());
			entity.setRate(bean.getRate());
			entity.setDateOfSubmission(bean.getDateOfSubmission());
			entity.setCreatedBy(bean.getCreatedBy());
			DbConnect.DbCon().save(entity);
			tx.commit();
			logger.info("Vendor Details Inserted SuccessFully");
			return 1;
		} catch (HibernateException e) {
			tx.rollback();
			logger.error("Vendor Details Inserted UnSuccessFully " + e);
			return 0;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// get VendorList Service Based On VendorName
	@Override
	public List<BenchSalesVendorDetailsEntity> getVendorList(String venName) {
		Transaction tx = null;
		String hql = "from BenchSalesVendorDetailsEntity where vendorName like '" + venName + "%'";
		Query<BenchSalesVendorDetailsEntity> query = null;
		List<BenchSalesVendorDetailsEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().createQuery(hql, BenchSalesVendorDetailsEntity.class);
			list = query.list();
			tx.commit();
			logger.info("Retrived Successfully");
			return list;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Add Candidate Vendor Service
	@SuppressWarnings({ "deprecation", "null" })
	@Override
	public int addCandidateVendor(BenchSalesVendorDetailsBean bean, int id) {
		Transaction tx = null;
		Set<BenchSalesAddCandidateEntity> entityCandidates = null;
		BenchSalesVendorDetailsEntity entity = null;
		BenchSalesAddCandidateEntity entityCandidate = null;
		String hql = null;
		@SuppressWarnings("rawtypes")
		Query query = null;
		String status1 = bean.getStatus();
		Date date = bean.getDateOfSubmission();
		try {
			tx = DbConnect.DbCon().getTransaction();
			entityCandidates = new HashSet<>();
			entity = new BenchSalesVendorDetailsEntity();
			hql = "from BenchSalesAddCandidateEntity where id=:id";
			entityCandidate = DbConnect.DbCon().createQuery(hql, BenchSalesAddCandidateEntity.class)
					.setParameter("id", id).getSingleResult();
			logger.info(entityCandidate);
			entityCandidates.add(entityCandidate);
			entity.setVendorId(bean.getVendorId());
			entity.setVendorName(bean.getVendorName());
			entity.setEmailAddress(bean.getEmailAddress());
			entity.setPhoneNo(bean.getPhoneNo());
			entity.setClientName(bean.getClientName());
			entity.setLocation(bean.getLocation());
			entity.setRate(bean.getRate());
			entity.setDateOfSubmission(bean.getDateOfSubmission());
			entity.setCreatedBy(bean.getCreatedBy());
			entity.setPayType(bean.getPayType());
			entity.setCandidates(entityCandidates);
			DbConnect.DbCon().save(entity);
			if (!tx.isActive()) {
				tx.commit();
			}
			int vendorId = (int) DbConnect.DbCon()
					.createQuery("select max(vendorId) from BenchSalesVendorCandidateMappingEntity where can_id=:id")
					.setParameter("id", id).uniqueResult();
			logger.info(vendorId);
			query = DbConnect.DbCon().createSQLQuery(
					"Update candidate_vendor  set status=:status1,statussubmissiondate=:date where can_id=:id and vendor_id=:vendorId")
					.setParameter("id", id).setParameter("vendorId", vendorId).setParameter("status1", status1)
					.setParameter("date", date);
			query.executeUpdate();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Vendor Details Inserted  And Status Updated SuccessFully");
			return 1;
		} catch (HibernateException e) {
			tx.rollback();
			logger.error("Vendor Details Inserted UnSuccessFully " + e);
			return 0;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Update Status Of Candidate
	@Override
	public boolean updateStatusCandidate(BenchSalesVendorCandidateMappingBean bean) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			BenchSalesVendorCandidateMappingEntity entity = new BenchSalesVendorCandidateMappingEntity();
			entity.setId(bean.getId());

			DbConnect.DbCon().saveOrUpdate(entity);
			if (tx != null)
				tx.commit();
			logger.info("Update Candidate Status Successfully");
			return true;

		} catch (HibernateException e) {
			logger.error("Update Candidate Status UnSuccessfully");
			tx.rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// fetch vendor Data based on the candidate id
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object[]> listOfVendorBasedonCandidateId(int id) {
		Transaction tx = null;
		String hql = "select v.vendor_id,cv.can_id,v.vendor_name,v.email_address,v.phone_no,v.client_name,v.location,v.rate,v.date_of_submission,v.created_by,v.status,v.statussubmissiondate,v.comments,v.pay_type from vendor_details  as v  left join candidate_vendor  as cv on v.vendor_id=cv.vendor_id where cv.can_id="
				+ id + "";
		List<Object[]> list1 = null;

		try {
			tx = DbConnect.DbCon().beginTransaction();
			list1 = DbConnect.DbCon().createSQLQuery(hql).list();

			tx.commit();
			logger.info(list1);
			logger.info("Retrived Successfully");
			return list1;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Update Vendor Details Based On Vendor id

	@Override
	public boolean updateVendorList(BenchSalesVendorDetailsBean bean, int id) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		BenchSalesVendorCandidateMappingEntity vendorStatusUpdate = null;
		try {
			vendorStatusUpdate = new BenchSalesVendorCandidateMappingEntity();
			vendorStatusUpdate.setCanId(id);
			vendorStatusUpdate.setVendorId(bean.getVendorId());
			vendorStatusUpdate.setStatus(bean.getStatus());
			vendorStatusUpdate.setStatussubmissiondate(bean.getStatusSubDate());
			vendorStatusUpdate.setComments(bean.getComments());
			DbConnect.DbCon().save(vendorStatusUpdate);
			tx.commit();
			logger.info("Vendor Details Updated SuccessFully");
			return true;

		} catch (HibernateException e) {
			logger.error("Updated  UnSuccessfully");
			tx.rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Intelligecie Service Based On Email Executive
	@Override
	public List<BenchSalesVendorDetailsEntity> getvendorBasedOnFirstAlphabet(String name, String exName) {
		Transaction tx = null;
		String hql = "from BenchSalesVendorDetailsEntity where emailAddress like '" + name + "%' and createdBy='"
				+ exName + "'";
		Query<BenchSalesVendorDetailsEntity> query = null;
		List<BenchSalesVendorDetailsEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().createQuery(hql, BenchSalesVendorDetailsEntity.class);
			list = query.list();
			tx.commit();
			logger.info("Retrived Successfully");
			return list;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Based on SaleExecutive We need To Fetch Candidate
	@Override
	public List<BenchSalesAddCandidateEntity> getCandidatesBasedOnExecutive(String name) {
		Transaction tx = null;
		String hql = "from BenchSalesAddCandidateEntity Where recruiterName=:recruiterName ";
		Query<BenchSalesAddCandidateEntity> query = null;
		List<BenchSalesAddCandidateEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().createQuery(hql, BenchSalesAddCandidateEntity.class).setParameter("recruiterName",
					name);
			list = query.list();
			tx.commit();
			logger.info("Retrived All Candidate Successfully");
			return list;

		} catch (HibernateException e) {
			logger.error("Retrived  All Candidate UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Intelligecie Service Based On Email Executive Super Admin
	@Override
	public List<BenchSalesVendorDetailsEntity> getvendorBasedOnFirstAlphabet(String name) {
		Transaction tx = null;
		String hql = "from BenchSalesVendorDetailsEntity where emailAddress like '" + name + "%'";
		Query<BenchSalesVendorDetailsEntity> query = null;
		List<BenchSalesVendorDetailsEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().createQuery(hql, BenchSalesVendorDetailsEntity.class);
			list = query.list();
			tx.commit();
			logger.info("Retrived Successfully");
			return list;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Based On The Candidate Id and Recruiters Name All The Vendor List Come
	@Override
	public List<Object[]> listOfVendorBasedonCandidateIdAndRecName(String recName, int id) {
		Transaction tx = null;
		String hql = "select v.vendor_id,v.vendor_name,v.email_address,v.phone_no,v.client_name,v.location,v.rate,v.date_of_submission,v.created_by,v.status,v.statussubmissiondate,v.comments,v.pay_type  from vendor_details  as v  left join candidate_vendor  as cv on v.vendor_id=cv.vendor_id  where cv.can_id="
				+ id + " and v.created_by='" + recName + "'";
		List<Object[]> list1 = null;

		try {
			tx = DbConnect.DbCon().beginTransaction();
			list1 = DbConnect.DbCon().createSQLQuery(hql).list();

			tx.commit();
			logger.info(list1);
			logger.info("Retrived Successfully");
			return list1;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Recruiters Service Under The Candidate Will Come
	@SuppressWarnings("unchecked")
	@Override
	public List<CandidateResponseObject> getCandidateUnderTheRecruites() {
		Transaction tx = null;
		String hql = null;
		Object object = null;
		List<BenchSalesAddCandidateEntity> miniList = null;
		CandidateResponseObject cro = null;
		List<CandidateResponseObject> listWithName = new ArrayList<>();
		String hql1 = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "select distinct(recruiterName) from BenchSalesAddCandidateEntity";
			List<BenchSalesAddCandidateEntity> objectsIterator = (List<BenchSalesAddCandidateEntity>) DbConnect.DbCon()
					.createQuery(hql).list();
			Iterator<BenchSalesAddCandidateEntity> iterator1 = objectsIterator.iterator();
			while (iterator1.hasNext()) {
				cro = new CandidateResponseObject();
				object = (Object) iterator1.next();
				if (object != null) {
					String recName = (String) object;
					hql1 = "from BenchSalesAddCandidateEntity where recruiterName='" + recName + "'";
					miniList = (List<BenchSalesAddCandidateEntity>) DbConnect.DbCon().createQuery(hql1).list();
					cro.setRecruiterName(recName);
					cro.setCandidList(miniList);
					listWithName.add(cro);
				}
			}

			tx.commit();
			logger.info("Retrived Candidate Successfully");
			return listWithName;
		} catch (HibernateException e) {
			logger.error("Retrived Candidate UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Vendor Search Based On VendorName,Phone,EmailAddress,Location
	@Override
	public List<BenchSalesVendorDetailsEntity> getVendorLists(BenchSalesVendorDetailsBean bean) {
		Transaction tx = null;
		String vendorName = null;
		String phoneNo = null;
		String emailAddress = null;
		String location = null;
		String hql = null;

		Query<BenchSalesVendorDetailsEntity> query = null;
		List<BenchSalesVendorDetailsEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			vendorName = bean.getVendorName();
			phoneNo = bean.getPhoneNo();
			emailAddress = bean.getEmailAddress();
			location = bean.getLocation();
			if (vendorName != null && vendorName.length() != 0 && phoneNo != null && phoneNo.length() != 0
					&& emailAddress != null && emailAddress.length() != 0 && location != null
					&& location.length() != 0) {
				hql = "from BenchSalesVendorDetailsEntity where vendorName='" + vendorName + "' and  phoneNo='"
						+ phoneNo + "' and  emailAddress='" + emailAddress + "' and  location='" + location + "'";
			} else if (vendorName != null && vendorName.length() != 0 && phoneNo != null && phoneNo.length() != 0
					&& emailAddress != null && emailAddress.length() != 0) {
				hql = "from BenchSalesVendorDetailsEntity where vendorName='" + vendorName + "' and  phoneNo='"
						+ phoneNo + "' and  emailAddress='" + emailAddress + "'";
			} else if (vendorName != null && vendorName.length() != 0 && phoneNo != null && phoneNo.length() != 0) {
				hql = "from BenchSalesVendorDetailsEntity where vendorName='" + vendorName + "' and  phoneNo='"
						+ phoneNo + "' ";
			} else if (vendorName != null && vendorName.length() != 0) {
				hql = "from BenchSalesVendorDetailsEntity where vendorName='" + vendorName + "'";
			} else if (phoneNo != null && phoneNo.length() != 0) {
				hql = "from BenchSalesVendorDetailsEntity where phoneNo='" + phoneNo + "'";
			} else if (emailAddress != null && emailAddress.length() != 0) {
				hql = "from BenchSalesVendorDetailsEntity where emailAddress='" + emailAddress + "'";
			} else {
				hql = "from BenchSalesVendorDetailsEntity where location='" + location + "'";
			}

			query = DbConnect.DbCon().createQuery(hql, BenchSalesVendorDetailsEntity.class);
			list = query.list();
			tx.commit();
			logger.info("Retrived Successfully");
			return list;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Candidate Search Based On CandidateName,Phone,EmailAddress,Location
	@Override
	public List<BenchSalesAddCandidateEntity> getCandidateLists(BenchSalesAddCandidateBean bean) {
		Transaction tx = null;
		String candidateName = null;
		String phoneNo = null;
		String emailAddress = null;
		String location = null;
		String hql = null;
		Query<BenchSalesAddCandidateEntity> query = null;
		List<BenchSalesAddCandidateEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			candidateName = bean.getFirstName();
			phoneNo = bean.getCellPhone();
			emailAddress = bean.getEmailAddress();
			location = bean.getCurrentLocation();
			if (candidateName != null && candidateName.length() != 0 && phoneNo != null && phoneNo.length() != 0
					&& emailAddress != null && emailAddress.length() != 0 && location != null
					&& location.length() != 0) {
				hql = "from BenchSalesAddCandidateEntity where firstName='" + candidateName + "' and  cellPhone='"
						+ phoneNo + "' and  emailAddress='" + emailAddress + "' and  currentLocation='" + location
						+ "'";
			} else if (candidateName != null && candidateName.length() != 0 && phoneNo != null && phoneNo.length() != 0
					&& emailAddress != null && emailAddress.length() != 0) {
				hql = "from BenchSalesAddCandidateEntity where firstName='" + candidateName + "' and  cellPhone='"
						+ phoneNo + "' and  emailAddress='" + emailAddress + "'";
			} else if (candidateName != null && candidateName.length() != 0 && phoneNo != null
					&& phoneNo.length() != 0) {
				hql = "from BenchSalesAddCandidateEntity where firstName='" + candidateName + "' and  cellPhone='"
						+ phoneNo + "'";
			} else if (candidateName != null && candidateName.length() != 0) {
				hql = "from BenchSalesAddCandidateEntity where firstName='" + candidateName + "'";
			} else if (phoneNo != null && phoneNo.length() != 0) {
				hql = "from BenchSalesAddCandidateEntity where cellPhone='" + phoneNo + "'";
			} else if (emailAddress != null && emailAddress.length() != 0) {
				hql = "from BenchSalesAddCandidateEntity where emailAddress='" + emailAddress + "'";
			} else {
				hql = "from BenchSalesAddCandidateEntity where currentLocation='" + location + "'";
			}
			query = DbConnect.DbCon().createQuery(hql, BenchSalesAddCandidateEntity.class);
			list = query.list();
			tx.commit();
			logger.info("Retrived Successfully");
			return list;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Get All InActive Candidate In This Service
	@Override
	public List<BenchSalesAddCandidateEntity> getInActiveCandidateList() {
		Transaction tx = null;
		Query<BenchSalesAddCandidateEntity> query = null;
		List<BenchSalesAddCandidateEntity> candidateList = null;
		String hql = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "from BenchSalesAddCandidateEntity where deleted='1'";
			query = DbConnect.DbCon().createQuery(hql, BenchSalesAddCandidateEntity.class);
			candidateList = query.list();
			tx.commit();
			logger.info("InActive Candidate Retrived SuccessFully");
			return candidateList;
		} catch (HibernateException e) {
			tx.rollback();
			logger.error("InActive Candidate Retrived Unsuccessfully");
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Get Candidate Vendor Status And Date Based On VendorId And Candidate Id
	@Override
	public List<Object[]> getStatusDate(int canId, int venId) {
		Transaction tx = null;
		List<Object[]> list1 = null;
		String hql = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "select status,statussubmissiondate from candidate_vendor  where vendor_id=" + venId + " and can_id="
					+ canId + "";
			list1 = DbConnect.DbCon().createSQLQuery(hql).list();
			tx.commit();
			logger.info(" Status And Status Submission Date Retrived Successfully ");
			return list1;
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(" Status And Status Submission Date Retrived UnSuccessfully ");
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Get Vendor Count Based On Candidate Id
	@Override
	public List<ArrayList<BigInteger>> getVendorCount() {
		Transaction tx = null;
		String hql = null;
		Object object = null;
		List<BigInteger> miniList = null;
		List<ArrayList<BigInteger>> majorList = null;
		String hql1 = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			majorList = new ArrayList<>();
			hql = "select distinct(cv.can_id) from vendor_details as v right join candidate_vendor as cv on v.vendor_id=v.vendor_id";
			List<BigInteger> listId = (List<BigInteger>) DbConnect.DbCon().createSQLQuery(hql).list();
			Iterator<BigInteger> iterator1 = listId.iterator();
			while (iterator1.hasNext()) {
				object = (Object) iterator1.next();
				if (object != null) {
					int canId = (int) object;
					hql1 = "select count(*) from vendor_details as v right join candidate_vendor as cv on v.vendor_id=cv.vendor_id where cv.can_id="
							+ canId + "";
					miniList = (List<BigInteger>) DbConnect.DbCon().createSQLQuery(hql1).list();
					majorList.add((ArrayList<BigInteger>) miniList);
				}
			}
			tx.commit();
			logger.info("Vendor Count Successfully");
			return majorList;
		} catch (HibernateException e) {
			logger.error("Vendor Count UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Vendor Client Submission Service
	@SuppressWarnings("unchecked")
	@Override
	public boolean vendorClientCandidateSub(BenchSalesVendorDetailsBean bean) {
		Transaction tx = null;
		List<Integer> reqRecIdList = null;
		Set<BenchSalesAddCandidateEntity> entityCandidates = null;
		BenchSalesVendorDetailsEntity entity = null;
		BenchSalesAddCandidateEntity entityCandidate = null;
		String hql = null;
		int reqId1 = 0;
		Query query = null;
		int status = 0;

		try {
			tx = DbConnect.DbCon().beginTransaction();
			reqRecIdList = bean.getRecIdList();
			int createId = bean.getCreatedBy();
			reqId1 = bean.getReqId();
			int idCan = bean.getId();
			status = bean.getStatus1();
			entityCandidates = new HashSet<>();
			Iterator<Integer> itrReqIdList = reqRecIdList.iterator();
			while (itrReqIdList.hasNext()) {
				Object reqId = (Object) itrReqIdList.next();
				int recid = (int) reqId;
				logger.info(recid);
				if (status == 0) {
					query = DbConnect.DbCon().createQuery(
							"update CandidateSalesExecutiveEntity set reqReqId=:createId ,status=:status where canId=:idCan and recId=:recid")
							.setParameter("recid", recid).setParameter("status", status).setParameter("idCan", idCan)
							.setParameter("createId", createId);
					query.executeUpdate();
					logger.info("Request Has Been SuccessFul");
				} else if (status == 2) {
					query = DbConnect.DbCon().createQuery(
							"update CandidateSalesExecutiveEntity set reqReqId=:reqId1 ,status=:status where canId=:idCan and recId=:recid")
							.setParameter("recid", recid).setParameter("status", status).setParameter("idCan", idCan)
							.setParameter("reqId1", reqId1);
					query.executeUpdate();
					logger.info("Rejected Has Been SuccessFul");
				} else {
					query = DbConnect.DbCon().createQuery(
							"update CandidateSalesExecutiveEntity set reqReqId=:reqId1 ,status=:status where canId=:idCan and recId=:recid")
							.setParameter("reqId1", reqId1).setParameter("status", status).setParameter("idCan", idCan)
							.setParameter("recid", recid);
					query.executeUpdate();
					logger.info(idCan);
					CandidateSalesExecutiveEntity candExe = new CandidateSalesExecutiveEntity();
					candExe.setCanId(idCan);
					candExe.setRecId(reqId1);
					candExe.setReqReqId(reqId1);
					candExe.setStatus(status);
					DbConnect.DbCon().save(candExe);
					logger.info("Approved SuccessFul By The Requested Recruiters");
				}
			}
			hql = "from BenchSalesAddCandidateEntity where id=:idCan";
			entityCandidate = DbConnect.DbCon().createQuery(hql, BenchSalesAddCandidateEntity.class)
					.setParameter("idCan", idCan).getSingleResult();
			logger.info(entityCandidate);
			entityCandidates.add(entityCandidate);
			entity = new BenchSalesVendorDetailsEntity();
			entity.setVendorId(bean.getVendorId());
			entity.setVendorName(bean.getVendorName());
			entity.setEmailAddress(bean.getEmailAddress());
			entity.setPhoneNo(bean.getPhoneNo());
			entity.setClientName(bean.getClientName());
			entity.setLocation(bean.getLocation());
			entity.setRate(bean.getRate());
			entity.setCreatedBy(bean.getCreatedBy());
			entity.setPayType(bean.getPayType());
			entity.setCandidates(entityCandidates);

			DbConnect.DbCon().save(entity);
			tx.commit();
			logger.info("Vendor Client Submission SuccessFully");
			return true;

		} catch (HibernateException e) {
			logger.error("Vendor Client Submission  UnSuccessfully");
			tx.rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Based On The Candidate Id and Recruiters Id All The Vendor List Come
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Set<BenchSalesVendorDetailsBean>listOfVendorBasedonCandidateIdAndRecId(int recId, int id) {
		Transaction tx = null;
		List<Object[]> listOfVendor = null;
		List<Object[]> listOfStatusUpdate = null;
		Set<BenchSalesVendorDetailsBean> setVendorList = null;
		List<BenchSalesVendorDetailsBean> lists = null;
		String queryForMaxId = null;
		String queryForStausComm = null;
		String queryForVendorDetails = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			lists = new ArrayList<BenchSalesVendorDetailsBean>();
			queryForMaxId = "select max(cv.id) from candidate_vendor  as cv  inner join vendor_details  as v on v.vendor_id=cv.vendor_id  where cv.can_id="
					+ id + "" + " and v.created_by=" + recId + "";
			@SuppressWarnings("deprecation")
			int maxId = (int) DbConnect.DbCon().createSQLQuery(queryForMaxId).uniqueResult();
		    queryForStausComm = "select status,comments from candidate_vendor where id=" + maxId + "";
			listOfStatusUpdate = DbConnect.DbCon().createSQLQuery(queryForStausComm).list();
			queryForVendorDetails = "select distinct(v.vendor_id),v.vendor_name,v.email_address,v.phone_no,v.client_name,v.location,v.rate,v.date_of_submission,"
					+ "v.created_by,max(cv.statussubmissiondate),v.pay_type"
					+ "  from vendor_details  as v  left join candidate_vendor  as cv on v.vendor_id=cv.vendor_id  where cv.can_id="
					+ id + "" + " and v.created_by=" + recId + " group by v.vendor_id";

			listOfVendor = DbConnect.DbCon().createSQLQuery(queryForVendorDetails).list();
			for (Object[] ob : listOfVendor) {
				BenchSalesVendorDetailsBean entity1 = new BenchSalesVendorDetailsBean();
				int vendorId = (Integer) ob[0];
				String vendorName = (String) ob[1];
				String emailAddress = (String) ob[2];
				String phoneNo = (String) ob[3];
				String clientName = (String) ob[4];
				String location = (String) ob[5];
				String rate = (String) ob[6];
				Date dateOfSubmission = (Date) ob[7];
				int createdBy = (int) ob[8];
				Date statusSubDate = (Date) ob[9];
				String payType = (String) ob[10];
				entity1.setVendorId(vendorId);
				entity1.setVendorName(vendorName);
				entity1.setEmailAddress(emailAddress);
				entity1.setClientName(clientName);
				entity1.setLocation(location);
				entity1.setRate(rate);
				entity1.setPhoneNo(phoneNo);
				entity1.setDateOfSubmission(dateOfSubmission);
				entity1.setStatusSubDate(statusSubDate);
				entity1.setCreatedBy(createdBy);
				entity1.setPayType(payType);
				lists.add(entity1);
				setVendorList = new HashSet<>(lists);
				for (Object[] statusCom : listOfStatusUpdate) {
					String status = (String) statusCom[0];
					String comments = (String) statusCom[1];
					for (BenchSalesVendorDetailsBean addStatusCom : setVendorList) {
						addStatusCom.setStatus(status);
						addStatusCom.setComments(comments);
					}
				}
			}
			tx.commit();
			logger.info("Retrived Successfully");
			return setVendorList;
		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}
	// Based On The SalesManId The SalesExecutive List Will Come
	@SuppressWarnings("unchecked")
	public List<User> listOfSalesExecutive(int salManId) {
		Transaction tx = null;
		List<User> userList = null;
		List<User>usersLists=null;
		String hql = null;
		try {
			usersLists=new ArrayList<>();
			tx = DbConnect.DbCon().beginTransaction();
			String hql1 = "select userId from EmployeeDetailsEntity where position_id=5 and reportingManagerId=:salManId";
			List<EmployeeDetailsEntity> objectsIterator = (List<EmployeeDetailsEntity>) DbConnect.DbCon()
					.createQuery(hql1).setParameter("salManId", salManId).list();
			Iterator<EmployeeDetailsEntity> iterator1 = objectsIterator.iterator();
			while (iterator1.hasNext()) {
				Object object = (Object) iterator1.next();
				if (object != null) {
					int salesExeId = (int) object;
					if (salesExeId != 0) {
						hql = "from User where Id=" + salesExeId + "";
						userList = DbConnect.DbCon().createQuery(hql).list();
						usersLists.addAll(userList);
					}
				}
			}
			return usersLists;
		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Based On The Candidate Id and Recruiters Id All The Vendor List Come For
	// AuthoriZation
	public List<Object[]> listOfVendorBasedonCandidateIdAndRecIdForAuth(int recId, int id) {
		Transaction tx = null;
		String hql = "select distinct(v.vendor_id),v.vendor_name,v.email_address,v.phone_no,v.client_name,v.location,v.rate,v.date_of_submission,"
				+ "v.created_by,cv.status,cv.statussubmissiondate,v.comments,v.pay_type"
				+ "  from vendor_details  as v  left join candidate_vendor  as cv on v.vendor_id=cv.vendor_id  where cv.can_id="
				+ id + "" + " and v.created_by=" + recId + "";
		List<Object[]> list1 = null;

		try {
			tx = DbConnect.DbCon().beginTransaction();
			list1 = DbConnect.DbCon().createSQLQuery(hql).list();

			tx.commit();
			logger.info(list1);
			logger.info("Retrived Successfully");
			return list1;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Get SalesExecutive Based On Id
	public User getRecruiterEmailById(int Id) {
		User user = null;

		logger.info("entered into getRecruiterEmailById of Dao Implementation class ");
		try {
			String hql = "from User where Id = :Id";

			Transaction tx = DbConnect.DbCon().beginTransaction();
			user = DbConnect.DbCon().createQuery(hql, User.class).setParameter("Id", Id).uniqueResult();

			tx.commit();

			return user;

		} catch (Exception e) {
			logger.info("catch block of  getRecruiterEmailById of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Get List Of SalesExecutive Based On List Of Id
	public List<User> getRecruiterEmailListByIdList(List<Integer> idList) {
		List<User> userList = new ArrayList<>();
		User user = null;

		for (int id : idList) {
			user = getRecruiterEmailById(id);
			userList.add(user);
		}

		return userList;

	}

	// Get Candiadte By Id
	public BenchSalesAddCandidateEntity getCandidateEntityById(int Id) {
		BenchSalesAddCandidateEntity benchSalesAddCandidateEntity = null;

		logger.info("entered into getCandidateEntityById of Dao Implementation class ");
		try {
			String hql = "from BenchSalesAddCandidateEntity where Id = :Id";

			Transaction tx = DbConnect.DbCon().beginTransaction();
			benchSalesAddCandidateEntity = DbConnect.DbCon().createQuery(hql, BenchSalesAddCandidateEntity.class)
					.setParameter("Id", Id).uniqueResult();

			tx.commit();

			return benchSalesAddCandidateEntity;

		} catch (Exception e) {
			logger.info("catch block of  getCandidateEntityById of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Get Super Admin Mail
	public String getSuperAdminEmail() {
		String emailAdd = null;

		logger.info("entered into getSuperAdminEmail of Dao Implementation class ");
		try {
			String hql = "from EmployeeDetailsEntity where Id = 1";
			EmployeeDetailsEntity us = null;
			Transaction tx = DbConnect.DbCon().beginTransaction();
			us = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class).getSingleResult();
			emailAdd = us.getEmail();

			tx.commit();

			return emailAdd;

		}

		catch (Exception e) {
			logger.info("catch block of  getSuperAdminEmail of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// getting email by id from BenchSalesAddCandidateEntity table;
	public String getCandidateEmailById(int id) {
		Transaction tx = null;
		String email = null;
		String hql = "from BenchSalesAddCandidateEntity Where id=:id ";
		Query<BenchSalesAddCandidateEntity> query = null;
		List<BenchSalesAddCandidateEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().createQuery(hql, BenchSalesAddCandidateEntity.class).setParameter("id", id);
			list = query.list();

			for (BenchSalesAddCandidateEntity bsEntity : list) {
				email = bsEntity.getEmailAddress();
			}
			tx.commit();
			logger.info("Retrived All Candidate Successfully");
			return email;

		} catch (HibernateException e) {
			logger.error("Retrived  All Candidate UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// getting email by using createdby from BenchSalesVendorDetailsEntity table
	public String getCandidateEmailByCreatedby(int createdBy) {
		Transaction tx = null;
		String email = null;
		String hql = "from BenchSalesVendorDetailsEntity Where createdBy=:createdBy ";
		Query<BenchSalesVendorDetailsEntity> query = null;
		List<BenchSalesVendorDetailsEntity> list = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			query = DbConnect.DbCon().createQuery(hql, BenchSalesVendorDetailsEntity.class).setParameter("createdBy",
					createdBy);
			list = query.list();

			for (BenchSalesVendorDetailsEntity bsEntity : list) {
				email = bsEntity.getEmailAddress();
			}
			tx.commit();
			logger.info("Retrived All Candidate Successfully");
			return email;

		} catch (HibernateException e) {
			logger.error("Retrived  All Candidate UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// get ReportingManagerId Based On SalesExeId
	@Override
	public String getReportingManagerMail(int salId) {
		Transaction tx = null;
		String hql = "select reporting_manager from main_employees_summary Where user_id=:salId ";
		String hql1 = null;
		int salesExeId = 0;
		String email = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			logger.info(salId);
			salesExeId = (int) DbConnect.DbCon().createSQLQuery(hql).setParameter("salId", salId).getSingleResult();
			logger.info(salesExeId);
			hql1 = "select emailaddress from main_users where id=:salesExeId";
			email = (String) DbConnect.DbCon().createSQLQuery(hql1).setParameter("salesExeId", salesExeId)
					.getSingleResult();
			logger.info(email);
			tx.commit();
			return email;
		} catch (HibernateException e) {
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	public List<CandidateResponseObject> getAllCandidatesAndRecruiters() {
		Transaction tx = null;
		String queryForRecIds = null;
		Object object = null;
		List<BenchSalesAddCandidateEntity> candidateList = null;
		CandidateResponseObject cro = null;
		List<CandidateResponseObject> recWithCandList = new ArrayList<>();
		String queryForCandIds = null;
		String queryForRecName = null;
		String queryCandInfo = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();

			/*
			 * SQLQuery query =
			 * session.createSQLQuery("select emp_id, emp_name, emp_salary from Employee");
			 * List<Object[]> rows = query.list();
			 */

			queryForRecIds = "select distinct rec_id from candidate_sales_executive";
			List<Integer> recIdList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForRecIds).list();

			for (int recruiterId : recIdList) {
				cro = new CandidateResponseObject();
				candidateList = new ArrayList<>();

				queryForCandIds = "select distinct can_id from candidate_sales_executive where rec_id=+'" + recruiterId
						+ "'";

				queryForRecName = "select distinct userfullname from main_users where id=+'" + recruiterId + "'";

				List<Integer> candList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForCandIds).list();

				List<String> recruiterNameList = (List<String>) DbConnect.DbCon().createSQLQuery(queryForRecName)
						.list();

				if (recruiterNameList != null) {
					cro.setRecruiterName(recruiterNameList.get(0));
				}

				for (int candId : candList) {
					queryCandInfo = "from BenchSalesAddCandidateEntity where id=:candId";

					BenchSalesAddCandidateEntity candInfo = (BenchSalesAddCandidateEntity) DbConnect.DbCon()
							.createQuery(queryCandInfo).setParameter("candId", candId).uniqueResult();

					// candInfo.setRecruiters(null);

					candidateList.add(candInfo);
				}
				cro.setCandidList(candidateList);
				recWithCandList.add(cro);
			}

			tx.commit();
			logger.info("Retrived Candidate Successfully");
			return recWithCandList;
		} catch (HibernateException e) {
			logger.error("Retrived Candidate UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	public List<CandSubmissionBean> getCandidateSubmissionDetails(int recId) {
		Transaction tx = null;
		String queryForVendors = null;
		Object object = null;
		List<BenchSalesAddCandidateEntity> candidateList = null;
		List<BenchSalesVendorDetailsEntity> vendorInfoList = null;
		List<CandSubmissionBean> candSubmissionBeanList = new ArrayList<>();
		CandSubmissionBean candSubmissionBean = null;
		String queryForCandIds = null;
		String queryVendorInfo = null;
		String queryCandInfo = null;
		int count = 0;
		try {
			tx = DbConnect.DbCon().beginTransaction();

			queryForCandIds = "select can_id from candidate_sales_executive where rec_id=+'" + recId + "'";
			List<Integer> candList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForCandIds).list();

			for (int candId : candList) {
				candSubmissionBean = new CandSubmissionBean();
				queryForVendors = "select vendor_id from candidate_vendor where can_id=+'" + candId + "'";

				List<Integer> vendorIdList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForVendors).list();

				vendorInfoList = new ArrayList<>();

				// .setParameter("status", "Submitted")

				for (int vendorId : vendorIdList) {
					// and status is NOT NULL
					queryVendorInfo = "from BenchSalesVendorDetailsEntity where vendorId=:vendorId ";
					BenchSalesVendorDetailsEntity vendorInfo = (BenchSalesVendorDetailsEntity) DbConnect.DbCon()
							.createQuery(queryVendorInfo).setParameter("vendorId", vendorId).uniqueResult();
					if (vendorInfo != null) {
						vendorInfoList.add(vendorInfo);
					}
				}

				queryCandInfo = "from BenchSalesAddCandidateEntity where id=:candId";
				BenchSalesAddCandidateEntity candInfo = (BenchSalesAddCandidateEntity) DbConnect.DbCon()
						.createQuery(queryCandInfo).setParameter("candId", candId).uniqueResult();

				if (candInfo != null) {

					count = vendorInfoList.size();
					candSubmissionBean.setId(candInfo.getId());
					candSubmissionBean.setFirstName(candInfo.getFirstName());
					candSubmissionBean.setAvailability(candInfo.getAvailability());
					candSubmissionBean.setCellPhone(candInfo.getCellPhone());
					candSubmissionBean.setCity(candInfo.getCity());
					candSubmissionBean.setComments(candInfo.getComments());
					candSubmissionBean.setCurrentLocation(candInfo.getCurrentLocation());
					candSubmissionBean.setDateOfBirth(candInfo.getDateOfBirth());
					candSubmissionBean.setDomain(candInfo.getDomain());
					candSubmissionBean.setEmailAddress(candInfo.getEmailAddress());
					candSubmissionBean.setLastName(candInfo.getLastName());
					candSubmissionBean.setHomePhone(candInfo.getHomePhone());
					candSubmissionBean.setMiddleName(candInfo.getMiddleName());
					candSubmissionBean.setCount(count);
					candSubmissionBean.setRecruiters(candInfo.getRecruiters());
				}

				candSubmissionBeanList.add(candSubmissionBean);
			}

			tx.commit();
			logger.info("Retrived Candidate Successfully");
			return candSubmissionBeanList;
		} catch (HibernateException e) {
			logger.error("Retrived Candidate UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	public List<CandSubmissionBean> getCandidateSubmissionWithInDates(CandidateSubmissionCount candSubCount) {
		Transaction tx = null;
		String queryForVendors = null;
		Object object = null;
		List<BenchSalesAddCandidateEntity> candidateList = null;
		List<BenchSalesVendorDetailsEntity> vendorInfoList = null;
		List<CandSubmissionBean> candSubmissionBeanList = new ArrayList<>();
		CandSubmissionBean candSubmissionBean = null;
		String queryForCandIds = null;
		String queryVendorInfo = null;
		String queryCandInfo = null;
		int count = 0;
		int recId = candSubCount.getRecId();
		String startDate = candSubCount.getStartDate();
		String endDate = candSubCount.getEndDate();
		try {
			tx = DbConnect.DbCon().beginTransaction();

			queryForCandIds = "select can_id from candidate_sales_executive where rec_id=+'" + recId + "'";
			List<Integer> candList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForCandIds).list();

			for (int candId : candList) {
				queryForVendors = "select vendor_id from candidate_vendor where can_id=+'" + candId + "'";

				List<Integer> vendorIdList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForVendors).list();

				vendorInfoList = new ArrayList<>();

				BenchSalesVendorDetailsEntity vendorInfo = null;

				// .setParameter("status", "Submitted")

				for (int vendorId : vendorIdList) {
					queryVendorInfo = "from BenchSalesVendorDetailsEntity where vendorId=:vendorId and status is NOT NULL and date_format(statussubmissiondate ,'%Y-%m-%d') BETWEEN :startDate AND :endDate ";
					vendorInfo = (BenchSalesVendorDetailsEntity) DbConnect.DbCon().createQuery(queryVendorInfo)
							.setParameter("vendorId", vendorId).setParameter("startDate", startDate)
							.setParameter("endDate", endDate).uniqueResult();
					if (vendorInfo != null) {
						vendorInfoList.add(vendorInfo);
					}
				}

				queryCandInfo = "from BenchSalesAddCandidateEntity where id=:candId";
				BenchSalesAddCandidateEntity candInfo = (BenchSalesAddCandidateEntity) DbConnect.DbCon()
						.createQuery(queryCandInfo).setParameter("candId", candId).uniqueResult();

				if (candInfo != null && vendorInfo != null) {
					candSubmissionBean = new CandSubmissionBean();
					count = vendorInfoList.size();
					candSubmissionBean.setId(candInfo.getId());
					candSubmissionBean.setFirstName(candInfo.getFirstName());
					candSubmissionBean.setAvailability(candInfo.getAvailability());
					candSubmissionBean.setCellPhone(candInfo.getCellPhone());
					candSubmissionBean.setCity(candInfo.getCity());
					candSubmissionBean.setComments(candInfo.getComments());
					candSubmissionBean.setCurrentLocation(candInfo.getCurrentLocation());
					candSubmissionBean.setDateOfBirth(candInfo.getDateOfBirth());
					candSubmissionBean.setDomain(candInfo.getDomain());
					candSubmissionBean.setEmailAddress(candInfo.getEmailAddress());
					candSubmissionBean.setLastName(candInfo.getLastName());
					candSubmissionBean.setHomePhone(candInfo.getHomePhone());
					candSubmissionBean.setMiddleName(candInfo.getMiddleName());
					candSubmissionBean.setCount(count);
					candSubmissionBean.setRecruiters(candInfo.getRecruiters());
					candSubmissionBeanList.add(candSubmissionBean);
				}
			}

			tx.commit();
			logger.info("Retrived Candidate Submission Successfully");
			return candSubmissionBeanList;
		} catch (HibernateException e) {
			logger.error("Retrived Candidate Submission UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	public List<BenchSalesVendorDetailsEntityResponse> getVendorByCandidateId(int candId) {
		Transaction tx = null;
		String queryForVendors = null;
		Object object = null;
		List<BenchSalesAddCandidateEntity> candidateList = null;
		// List<BenchSalesVendorDetailsEntity> vendorInfoList = null;
		List<BenchSalesVendorDetailsEntityResponse> vendorInfoList = null;
		BenchSalesVendorDetailsEntityResponse vendorResponse = null;
		List<VendorDetailsResponse> response = new ArrayList<>();
		String queryForRecName = null;
		String queryVendorInfo = null;
		String queryCandInfo = null;
		int count = 0;
		try {
			tx = DbConnect.DbCon().beginTransaction();

			queryForVendors = "select vendor_id from candidate_vendor where can_id=+'" + candId + "'";

			List<Integer> vendorIdList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForVendors).list();

			vendorInfoList = new ArrayList<>();

			for (int vendorId : vendorIdList) {
				queryVendorInfo = "from BenchSalesVendorDetailsEntity where vendorId=:vendorId";
				BenchSalesVendorDetailsEntity vendorInfo = (BenchSalesVendorDetailsEntity) DbConnect.DbCon()
						.createQuery(queryVendorInfo).setParameter("vendorId", vendorId).uniqueResult();

				vendorResponse = new BenchSalesVendorDetailsEntityResponse();

				int recId = vendorInfo.getCreatedBy();
				queryForRecName = "select userfullname from main_users where id=+'" + recId + "'";
				List<String> recruiterNameList = (List<String>) DbConnect.DbCon().createSQLQuery(queryForRecName)
						.list();

				vendorResponse.setCandidates(vendorInfo.getCandidates());
				vendorResponse.setClientName(vendorInfo.getClientName());
				vendorResponse.setCreatedBy(vendorInfo.getCreatedBy());
				vendorResponse.setDateOfSubmission(vendorInfo.getDateOfSubmission());
				vendorResponse.setEmailAddress(vendorInfo.getEmailAddress());
				vendorResponse.setLocation(vendorInfo.getLocation());
				vendorResponse.setPayType(vendorInfo.getPayType());
				vendorResponse.setPhoneNo(vendorInfo.getPhoneNo());
				vendorResponse.setRate(vendorInfo.getRate());
				vendorResponse.setVendorId(vendorInfo.getVendorId());
				vendorResponse.setVendorName(vendorInfo.getVendorName());
				// vendorResponse.setStatus(vendorInfoStatus.getStatus());

				vendorResponse.setRecruiterName(recruiterNameList.get(0));

				vendorInfoList.add(vendorResponse);

			}

			tx.commit();
			logger.info("Retrived Vendor Successfully");
			return vendorInfoList;
		} catch (HibernateException e) {
			logger.error("Retrived Vendor UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Based On SalesManagerId SalesExecutive And UnderCandidate will Come
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Set<CandidateSalesManagerResponse> retriveCandidateBasedOnSalesManagerAndSalesExecutive(int saleManId) {
		Transaction tx = null;
		List<Object[]> query = null;
		Set<CandidateSalesManagerResponse> canList = null;
		List<Object[]> usersMinilist = null;
		List<User> userList = null;
		String hql2 = null;
		String hql = null;
		try {
			canList = new HashSet<>();
			tx = DbConnect.DbCon().beginTransaction();
			String hql1 = "select userId from EmployeeDetailsEntity where position_id=5 and reportingManagerId=:saleManId";
			@SuppressWarnings("unchecked")
			List<EmployeeDetailsEntity> objectsIterator = (List<EmployeeDetailsEntity>) DbConnect.DbCon()
					.createQuery(hql1).setParameter("saleManId", saleManId).list();
			Iterator<EmployeeDetailsEntity> iterator1 = objectsIterator.iterator();
			while (iterator1.hasNext()) {
				Object object = (Object) iterator1.next();
				if (object != null) {
					int salesExeId = (int) object;
					if (salesExeId != 0) {
						hql2 = "select distinct(id),userfullname from main_users where id=" + salesExeId + "";
						hql = "Select distinct(c.id),c.firstName,c.emailAddress,c.cellPhone,c.domain,\r\n"
								+ "c.middleName,c.lastName,c.dateOfBirth,c.homePhone,c.workPhone,\r\n"
								+ "					c.city,c.currentLocation,cr.status,cr.reqReqId,c.reLocation,c.hotListDate\r\n"
								+ "                    ,c.ssn,c.availability,c.uploadProfile,c.refernce,c.comments\r\n"
								+ "from BenchSalesAddCandidateEntity  as c left join candidate_sales_executive as cr\r\n"
								+ " on cr.can_id=c.id and c.deleted='0' where cr.rec_id=" + salesExeId + " ";
					}

					query = (List<Object[]>) DbConnect.DbCon().createSQLQuery(hql).list();
					usersMinilist = DbConnect.DbCon().createSQLQuery(hql2).list();
					for (Object[] ob2 : usersMinilist) {
						userList = new ArrayList<>();
						int id = (Integer) ob2[0];
						String userFullName = (String) ob2[1];
						User user = new User();
						user.setId(id);
						user.setUserfullName(userFullName);
						userList.add(user);
					}
					if (query != null) {
						for (Object[] ob : query) {
							CandidateSalesManagerResponse canBean = new CandidateSalesManagerResponse();
							int id2 = (Integer) ob[0];
							String firstName = (String) ob[1];
							String emailAddress = (String) ob[2];
							String cellPhone = (String) ob[3];
							String domain = (String) ob[4];
							String middleName = (String) ob[5];
							String lastName = (String) ob[6];
							Date dateOfBirth = (Date) ob[7];
							String homePhone = (String) ob[8];
							String workPhone = (String) ob[9];
							String city = (String) ob[10];
							String currentLocation = (String) ob[11];
							Integer status = (Integer) ob[12];
							Integer reqReqId = (Integer) ob[13];
							String reLocation = (String) ob[14];
							Date hotListDate = (Date) ob[15];
							String ssn = (String) ob[16];
							String availability = (String) ob[17];
							String uploadProfile = (String) ob[18];
							String refernce = (String) ob[19];
							String comments = (String) ob[20];
							canBean.setId(id2);
							canBean.setFirstName(firstName);
							canBean.setEmailAddress(emailAddress);
							canBean.setCellPhone(cellPhone);
							canBean.setDomain(domain);
							canBean.setMiddleName(middleName);
							canBean.setLastName(lastName);
							canBean.setDateOfBirth(dateOfBirth);
							canBean.setHomePhone(homePhone);
							canBean.setWorkPhone(workPhone);
							canBean.setCity(city);
							canBean.setCurrentLocation(currentLocation);
							canBean.setStatus(status);
							if (reqReqId != null) {
								canBean.setReqReqId(reqReqId);
							}
							canBean.setReLocation(reLocation);
							canBean.setHotListDate(hotListDate);
							canBean.setSsn(ssn);
							canBean.setAvailability(availability);
							canBean.setUploadProfile(uploadProfile);
							canBean.setRefernce(refernce);
							canBean.setComments(comments);
							canBean.setRecruiters(userList);
							canList.add(canBean);
						}
					}
				}
			}
			int salesManId = 0;
			int id1 = 0;
			if (userList != null) {
				for (User user1 : userList) {
					id1 = user1.getId();
				}
			}
			logger.info(id1);
			if (id1 != 0) {
				String getSaleManIdQuery = "select reporting_manager from main_employees_summary Where user_id=:id1 ";
				salesManId = (int) DbConnect.DbCon().createSQLQuery(getSaleManIdQuery).setParameter("id1", id1)
						.uniqueResult();
			}
			String getSaleManIdName = "select id,userfullname from main_users where id=:salesManId";
			List<Object[]> user1 = DbConnect.DbCon().createSQLQuery(getSaleManIdName)
					.setParameter("salesManId", salesManId).list();
			for (Object[] ob1 : user1) {
				int id2 = (Integer) ob1[0];
				String userName = (String) ob1[1];
				for (CandidateSalesManagerResponse addSaleManIdName : canList) {
					addSaleManIdName.setSalId(id2);
					addSaleManIdName.setSalName(userName);
				}
			}
			tx.commit();
			logger.info("Retrived Successfully");
			return canList;
		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Get VendorList With Sales Executive Name

	public List<VendorSalesExecutiveResponseBean> getVendorListWithSaleExecutiveName() {
		Transaction tx = null;
		List<VendorSalesExecutiveResponseBean> vendorLists = null;
		List<Object[]> vendorList = null;
		try {
			vendorLists = new ArrayList<>();
			tx = DbConnect.DbCon().beginTransaction();
			String hql = "select vendor_id from vendor_details";
			List<Object[]> objectsIterator = (List<Object[]>) DbConnect.DbCon().createSQLQuery(hql).list();
			Iterator<Object[]> iterator1 = objectsIterator.iterator();
			while (iterator1.hasNext()) {
				Object object = (Object) iterator1.next();
				if (object != null) {
					int vendorId = (int) object;
					if (vendorId != 0) {
						String hql1 = "select vendor_id,vendor_name,email_address,phone_no,client_name,location,rate,date_of_submission,"
								+ "created_by,pay_type from vendor_details where vendor_id=" + vendorId + "";
						vendorList = DbConnect.DbCon().createSQLQuery(hql1).list();
						for (Object[] ob : vendorList) {
							VendorSalesExecutiveResponseBean entity1 = new VendorSalesExecutiveResponseBean();
							int id = (Integer) ob[0];
							String vendorName = (String) ob[1];
							String emailAddress = (String) ob[2];
							String phoneNo = (String) ob[3];
							String clientName = (String) ob[4];
							String location = (String) ob[5];
							String rate = (String) ob[6];
							Date dateOfSubmission = (Date) ob[7];
							int createdBy = (int) ob[8];
							String payType = (String) ob[9];
							entity1.setVendorId(id);
							entity1.setVendorName(vendorName);
							entity1.setEmailAddress(emailAddress);
							entity1.setPhoneNo(phoneNo);
							entity1.setClientName(clientName);
							entity1.setLocation(location);
							entity1.setRate(rate);
							entity1.setDateOfSubmission(dateOfSubmission);
							entity1.setCreatedBy(createdBy);
							entity1.setPayType(payType);
							String hql2 = "select userfullname from main_users where id=:createdBy";
							String salName = (String) DbConnect.DbCon().createSQLQuery(hql2)
									.setParameter("createdBy", createdBy).uniqueResult();
							entity1.setSalExeName(salName);
							vendorLists.add(entity1);

						}
					}
				}
			}
			tx.commit();
			logger.info("Retrived Successfully");
			return vendorLists;

		} catch (HibernateException e) {
			logger.error("Retrived UnSuccessfully");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Through This Service We Are Making InActive Candidate To Active Mode
	@SuppressWarnings({ "deprecation" })
	@Override
	public void activeCandidate(int id) {
		Transaction tx = null;
		String hql = null;
		String hql2 = null;
		String hql3 = null;
		Query query = null;
		Query query1 = null;
		Query query2 = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "UPDATE BenchSalesAddCandidateEntity SET deleted='0' WHERE deleted='1' and  id =" + id + "";
			query = DbConnect.DbCon().createQuery(hql);
			query.executeUpdate();
			DbConnect.DbCon().getTransaction();
			hql2 = "delete from candidate_sales_executive where can_id=" + id + "";
			query1 = DbConnect.DbCon().createSQLQuery(hql2);
			query1.executeUpdate();
			DbConnect.DbCon().getTransaction();
			hql3 = "delete from candidate_vendor where can_id=" + id + "";
			query2 = DbConnect.DbCon().createSQLQuery(hql3);
			query2.executeUpdate();
			DbConnect.DbCon().getTransaction();
			tx.commit();
			logger.info("Active SuccessFully");
		} catch (HibernateException e) {
			logger.error("Active UnSuccessfully");
			tx.rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
	}

//get All States
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<CityStateResponseBean> getAllStates() {
		Transaction tx = null;
		List<Object[]> listsOfState = null;
		List<CityStateResponseBean> listOfStates = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			listOfStates = new ArrayList<>();
			String hql = "select id,state_name from tbl_states";
			listsOfState = DbConnect.DbCon().createSQLQuery(hql).list();
			for (Object[] state : listsOfState) {
				CityStateResponseBean states = new CityStateResponseBean();
				BigInteger id = (BigInteger) state[0];
				String stateName = (String) state[1];
				states.setStateId(id);
				states.setStateName(stateName);
				listOfStates.add(states);
			}
			tx.commit();
			logger.info("States Retrive SuccessFul");
			return listOfStates;
		} catch (HibernateException e) {
			logger.error("States Retrive UnSuccessFul");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

//Get All Cities Based On State Id
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<CityStateResponseBean> getAllCities(int id) {
		Transaction tx = null;
		List<Object[]> listsOfCity = null;
		List<CityStateResponseBean> listOfCities = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			listOfCities = new ArrayList<>();
			String hql = "select id, city_name from tbl_cities where state_id=" + id + "";
			listsOfCity = DbConnect.DbCon().createSQLQuery(hql).list();
			for (Object[] city : listsOfCity) {
				CityStateResponseBean cities = new CityStateResponseBean();
				BigInteger cityId = (BigInteger) city[0];
				String cityName = (String) city[1];
				cities.setCityId(cityId);
				cities.setCityName(cityName);
				listOfCities.add(cities);
			}
			tx.commit();
			logger.info("Retrived Cities SuccessFul");
			return listOfCities;
		} catch (HibernateException e) {
			logger.error("Retrived Cities UnSuccessFul");
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Validation For Candidate Submission With Organisation ClientName And Location
	@SuppressWarnings({ "deprecation", "unlikely-arg-type", "null" })
	@Override
	public int checkWithCandIdThatOrgClieLoc(String organisationName, String clientName, String loc) {
		Transaction tx = null;
		Object canId = null;
		int candId = 0;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			String hql = "select distinct(cv.can_id) from candidate_vendor as cv inner join vendor_details  \r\n"
					+ "as v on v.vendor_id=cv.vendor_id  where v.vendor_name='" + organisationName
					+ "' and v.client_name='" + clientName + "' \r\n" + "and v.location='" + loc + "'";
			canId = DbConnect.DbCon().createSQLQuery(hql).uniqueResult();
			if (canId != null) {
				candId = (int) canId;
			} else if (canId == null) {
				return 0;
			}
			tx.commit();
			logger.info("Retrived CanId SuccessFully");
			return candId;
		} catch (HibernateException e) {
			logger.error("Retrived CanId UnSuccessFully");
			tx.rollback();
			return 0;
		} finally {
			DbConnect.DbCon().clear();
		}
	}
	// get All SalesExceutive And Candidate Based On SalManagerId
		@SuppressWarnings({ "deprecation", "unchecked" })
		public List<CandidateResponseObject> getSaleExeAndCanBasedOnSalManId(int saleManId) {
			Transaction tx = null;
			String queryForRecIds = null;
			List<BenchSalesAddCandidateEntity> candidateList = null;
			CandidateResponseObject cro = null;
			List<CandidateResponseObject> recWithCandList = new ArrayList<>();
			String queryForCandIds = null;
			String queryForRecName = null;
			String queryCandInfo = null;
			try {
				tx = DbConnect.DbCon().beginTransaction();
				queryForRecIds = "select userId from EmployeeDetailsEntity where position_id=5 and reportingManagerId=:saleManId";
				List<Integer> recIdList = (List<Integer>) DbConnect.DbCon().createQuery(queryForRecIds).setParameter("saleManId", saleManId).list();
				for (int recruiterId : recIdList) {
					cro = new CandidateResponseObject();
					candidateList = new ArrayList<>();
					queryForCandIds = "select distinct(can_id) from candidate_sales_executive where rec_id=+'" + recruiterId
							+ "'";
					queryForRecName = "select distinct(userfullname) from main_users where id=+'" + recruiterId + "'";
					List<Integer> candList = (List<Integer>) DbConnect.DbCon().createSQLQuery(queryForCandIds).list();

					List<String> recruiterNameList = (List<String>) DbConnect.DbCon().createSQLQuery(queryForRecName)
							.list();

					if (recruiterNameList != null) {
						cro.setRecruiterName(recruiterNameList.get(0));
					}

					for (int candId : candList) {
						queryCandInfo = "from BenchSalesAddCandidateEntity where id=:candId";

						BenchSalesAddCandidateEntity candInfo = (BenchSalesAddCandidateEntity) DbConnect.DbCon()
								.createQuery(queryCandInfo).setParameter("candId", candId).uniqueResult();
						candidateList.add(candInfo);
					}
					cro.setCandidList(candidateList);
					recWithCandList.add(cro);
				}
				tx.commit();
				logger.info("Retrived Candidate Successfully");
				return recWithCandList;
			} catch (HibernateException e) {
				logger.error("Retrived Candidate UnSuccessfully");
				tx.rollback();
				return null;
			} finally {
				DbConnect.DbCon().clear();
			}
			
		}

}
