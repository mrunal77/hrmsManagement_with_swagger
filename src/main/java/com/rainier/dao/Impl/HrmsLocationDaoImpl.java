package com.rainier.dao.Impl;

import com.rainier.dao.HrmsLocationDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class HrmsLocationDaoImpl implements HrmsLocationDao {
    // Logger Initializing
    private final static Logger logger = Logger.getLogger(HrmsLocationDaoImpl.class);

    // listing Countries
    public List<Tbl_CountriesEntity> listOfCountries() {
        // TODO Auto-generated method stub
        logger.info("entered into listOfCountries method of Dao implementation class");
        // DbConnect db = new DbConnect();
        try {
            String hql = "FROM Tbl_CountriesEntity where isActive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Tbl_CountriesEntity> query = DbConnect.DbCon().createQuery(hql, Tbl_CountriesEntity.class);
            List<Tbl_CountriesEntity> listCountry = query.list();
            DbConnect.DbCon().getTransaction().commit();
            // System.out.println("list values of all country with full details");
            return listCountry;
        } catch (Exception e) {
            logger.info("catch block of listOfCountries method of Dao implementation class::" + e);
            DbConnect.DbCon().getTransaction().rollback();
            // System.out.println("failed to get hql query" + e);
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
        }

    }

    // listing states
    public List<Tbl_StatesEntity> listOfStates(int countryId) {
        logger.info("entered into listOfStates method of Dao implementation class");

        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            // DbConnect sf = new DbConnect();
            String hql = "FROM Tbl_StatesEntity where countryId=:ctryId and isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Tbl_StatesEntity> query = DbConnect.DbCon().createQuery(hql, Tbl_StatesEntity.class);
            query.setParameter("ctryId", countryId);
            List<Tbl_StatesEntity> listState = query.list();
            DbConnect.DbCon().getTransaction().commit();
            // System.out.println("list values of all States with full details");
            return listState;
        } catch (Exception e) {
            logger.info("catch block of listOfStates method of Dao implementation class::" + e);
            DbConnect.DbCon().getTransaction().rollback();
            // System.out.println("failed to get hql query" + e);
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
        }

    }

    public List<Tbl_CitiesEntity> listOfCities(int countryId, int stateId) {
        logger.info("entered into listOfCities method of Dao implementation class");

        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            // String hql="FROM Tbl_CitiesEntity c INNER JOIN Tbl_StatesEntity s ON
            // c.stateId=:stsId where c.stateId=s.id and s.countryId=:ctryId";
            String hql = "select c from Tbl_CitiesEntity c, Tbl_StatesEntity s where "
                    + "c.stateId=:stateId and c.stateId=s.id and s.countryId=:countryId and isActive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Tbl_CitiesEntity> query = DbConnect.DbCon().createQuery(hql, Tbl_CitiesEntity.class);
            query.setParameter("countryId", countryId);
            query.setParameter("stateId", stateId);
            // query.addEntity(Tbl_CitiesEntity.class);
            // query.setParameter("id", id);
            List<Tbl_CitiesEntity> listCity = query.list();
            DbConnect.DbCon().getTransaction().commit();
            // System.out.println("list of cities will display based on state and
            // country...");
            return listCity;
        } catch (Exception e) {
            logger.info("catch block of listOfCities method of Dao implementation class::" + e);
            DbConnect.DbCon().getTransaction().rollback();
            // System.out.println("failed to get hql query" + e);
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status:" + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status:" +
            // DbConnect.dbSessionFactory().isOpen());
            // DbConnect.DbCon().close();
        }

    }
    // listing timezones

    @Override
    public List<Tbl_TimezonesEntity> listOfTimezones() {
        // TODO Auto-generated method stub
        logger.info("entered into listOfTimezones method of Dao implementation class");

        // DbConnect db = new DbConnect();
        try {
            String hql = "from Tbl_TimezonesEntity where isActive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Tbl_TimezonesEntity> query = DbConnect.DbCon().createQuery(hql, Tbl_TimezonesEntity.class);
            List<Tbl_TimezonesEntity> listTimeZone = query.list();
            // System.out.println("list of timeZone retrived.");
            DbConnect.DbCon().getTransaction().commit();
            return listTimeZone;
        } catch (Exception e) {
            logger.info("catch block of listOfTimezones method of Dao implementation class::" + e);
            DbConnect.DbCon().getTransaction().rollback();
            // System.out.println("failed to execute hql query:" + e);
            return null;

        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status:" + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status:" +
            // DbConnect.dbSessionFactory().isOpen());
            // DbConnect.DbCon().close();
        }

    }

    // Hibernate Logic to add country .

    public String addCountry(Main_CountriesEntity countryUnit) {
        // DbConnect db = new DbConnect();
        try {

            String availableCountryName;
            String hql = "select countryName from Main_CountriesEntity where countryIdOrg="
                    + countryUnit.getCountryIdOrg();
            DbConnect.DbCon().beginTransaction();
            availableCountryName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            if (availableCountryName == null) {
                DbConnect.DbCon().save(countryUnit);
                DbConnect.DbCon().getTransaction().commit();
                logger.info("country list added Successfully.!!");
                return null;
            } else {
                logger.info("Country Already Exists.");
                DbConnect.DbCon().getTransaction().rollback();
            }
            /* return "All required fileds are added in tbl_country to main_country."; */
            return availableCountryName;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
        }

    }
    // for saving timezone .

    @Override
    public String addTimezone(Main_TimezoneEntity timezoneUnit) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String availableTimezoneName;
            String hql = "select timeZone from Main_TimezoneEntity where actualId=" + timezoneUnit.getActualId();
            DbConnect.DbCon().beginTransaction();
            availableTimezoneName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            if (availableTimezoneName == null) {
                DbConnect.DbCon().save(timezoneUnit);
                DbConnect.DbCon().getTransaction().commit();
                logger.info("TimeZone Added in  main timezone table. ");
                return null;

            } else {
                logger.info("Timezone Already exists.");
                DbConnect.DbCon().getTransaction().rollback();
            }
            return availableTimezoneName;
        } catch (HibernateException e) {
            logger.error("failed to get hql query" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    @Override
    public String addStates(Main_StatesEntity statesUnit) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {

            String availableStateName;
            String hql = "select stateName from Main_StatesEntity where stateIdOrg=" + statesUnit.getStateIdOrg();

            DbConnect.DbCon().beginTransaction();
            availableStateName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            if (availableStateName == null) {

                DbConnect.DbCon().save(statesUnit);
                DbConnect.DbCon().getTransaction().commit();
                logger.info("States added in  main states table. ");
                return null;
            } else {
                logger.info("State Already Exists.");
                DbConnect.DbCon().getTransaction().rollback();
            }
            return availableStateName;
        } catch (HibernateException e) {
            logger.error("failed to get hql query" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // adding main city .
    @Override
    public String addCities(Main_citiesEntity citiesUnit) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String availableCityName;
            String hql = "select cityName from Main_citiesEntity where cityIdOrg=" + citiesUnit.getCityIdOrg();

            DbConnect.DbCon().beginTransaction();
            availableCityName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            if (availableCityName == null) {
                DbConnect.DbCon().save(citiesUnit);
                DbConnect.DbCon().getTransaction().commit();
                logger.info("Cities added in  main cities table. ");
                return null;
            } else {
                logger.info("City Already Exists.");
                /* return "city Already exists."; */
                DbConnect.DbCon().getTransaction().rollback();
            }
            return availableCityName;
        } catch (HibernateException e) {
            logger.error("failed to get hql query" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // fetching timezone name only.
    @Override
    public List<Main_TimezoneEntity> nameOfTimezone() {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "FROM Main_TimezoneEntity where isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Main_TimezoneEntity> query = DbConnect.DbCon().createQuery(hql, Main_TimezoneEntity.class);
            List<Main_TimezoneEntity> timezoneList = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Added Timezone Listed retrived Succesfully.");
            return timezoneList;
        } catch (HibernateException e) {
            logger.error("failed to execute hql query.: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());

        }
    }

    @Override
    public List<Main_CountriesEntity> nameOfCountry() {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "FROM Main_CountriesEntity where isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Main_CountriesEntity> query = DbConnect.DbCon().createQuery(hql, Main_CountriesEntity.class);
            List<Main_CountriesEntity> countryNameList = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info(" Added Country retrieved SuccessFully...");
            return countryNameList;
        } catch (HibernateException e) {
            logger.error("failed to execute hql query.: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // for added state list based on main country id only.
    @Override
    public List<Main_StatesEntity> nameOfStates(int countryId) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "FROM Main_StatesEntity where countryId=:countryId and isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Main_StatesEntity> query = DbConnect.DbCon().createQuery(hql, Main_StatesEntity.class);
            query.setParameter("countryId", countryId);
            List<Main_StatesEntity> statesNameList = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info(" Added States retrieved SuccessFully...");
            return statesNameList;
        } catch (HibernateException e) {
            logger.error("failed to execute hql query.: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    @Override
    public List<Main_citiesEntity> nameOfCities(int countryId, int stateId) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "FROM Main_citiesEntity where countryId=:countryId and stateId=:stateId and isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Main_citiesEntity> query = DbConnect.DbCon().createQuery(hql, Main_citiesEntity.class);
            query.setParameter("countryId", countryId);
            query.setParameter("stateId", stateId);
            List<Main_citiesEntity> citiesNameList = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info(" Added Cities retrieved SuccessFully...");
            return citiesNameList;
        } catch (HibernateException e) {
            logger.error("failed to execute hql query.: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    @Override
    public List<Main_StatesEntity> fetchStateOnAddedCountry(int countryId) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "FROM Main_StatesEntity where countryId=:countryId and isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Main_StatesEntity> query = DbConnect.DbCon().createQuery(hql, Main_StatesEntity.class);
            query.setParameter("countryId", countryId);
            List<Main_StatesEntity> mainStatesList = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("main States based on countryid retrieved SuccessFully...");
            return mainStatesList;
        } catch (HibernateException e) {
            logger.error("failed to execute hql query.: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    @Override
    public Tbl_CountriesEntity getCountryDetailsBasedOnCountryId(int CountryId) {
        // DbConnect dbConnect = new DbConnect();
        // Session session = null;
        Tbl_CountriesEntity tbl_CountriesEntity;
        try {
            // session = DbConnect.DbCon();
            DbConnect.DbCon().beginTransaction();
            tbl_CountriesEntity = DbConnect.DbCon().get(Tbl_CountriesEntity.class, CountryId);
            DbConnect.DbCon().getTransaction().commit();
        } catch (HibernateException he) {
            logger.info("inside Catch block of getCountryDetailsBasedOnCountryId method of daoImpl :" + he);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // session.close();
            // DbConnect.dbSessionFactory().close();
        }
        return tbl_CountriesEntity;
    }

    @Override
    public Tbl_StatesEntity getStateDetailsBasedOnStateId(int stateId) {
        // DbConnect dbConnect = new DbConnect();
        // Session session = null;
        Tbl_StatesEntity tbl_StatesEntity;
        try {
            // DbConnect.DbCon() = DbConnect.DbCon();
            DbConnect.DbCon().beginTransaction();
            tbl_StatesEntity = DbConnect.DbCon().get(Tbl_StatesEntity.class, stateId);
            DbConnect.DbCon().getTransaction().commit();
        } catch (Exception e) {
            logger.info("inside catch block of getStateDetailsBasedOnStateId method of daoImpl class :" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // session.close();
            // DbConnect.dbSessionFactory().close();
        }

        return tbl_StatesEntity;
    }

}
