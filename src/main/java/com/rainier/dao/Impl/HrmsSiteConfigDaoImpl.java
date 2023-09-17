package com.rainier.dao.Impl;

import com.rainier.dao.HrmsSiteConfigDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.Main_CountriesEntity;
import com.rainier.entities.Main_StatesEntity;
import com.rainier.entities.Main_TimezoneEntity;
import com.rainier.entities.Main_citiesEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class HrmsSiteConfigDaoImpl implements HrmsSiteConfigDao {
    private final static Logger logger = Logger.getLogger(HrmsDashboardDaoImpl.class);

    // country and country code List.
    @Override
    public List<Object[]> getCountry_CountryCode() {
        // DbConnect db = new DbConnect();
        try {
            String hql = "select id,country,countrycode,country_id_org from main_countries";
            DbConnect.DbCon().beginTransaction();
            Query<Object[]> query = DbConnect.DbCon().createNativeQuery(hql);
            List<Object[]> addedCountry_Code = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Country name & country Code List Retrrived");
            return addedCountry_Code;
        } catch (Exception e) {
            logger.error("failed to get Execute Sql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // country State List.
    public List<Object[]> getCountryStateList() {
        // DbConnect db = new DbConnect();
        try {
            /*
             * String sql =
             * "SELECT t.countryName ,ms.countryId,state,stateIdOrg from main_states ms,tbl_countries t \r\n"
             * + "where  ms.countryId=t.id and isactive=1 ORDER BY ms.countryId asc";
             */
            /*
             * String hql =
             * "SELECT mc.country ,ms.countryid,state,state_id_org from main_states ms,main_countries mc\r\n"
             * + " where  ms.countryid=mc.country_id_org\r\n" +
             * " and mc.country_id_org=ms.countryid and mc.isactive=1";
             */

            String hql = "select  mc.country ,ms.countryid,state,state_id_org,ms.id from main_states ms,main_countries mc\r\n"
                    + " where ms.countryid=mc.country_id_org and mc.country_id_org=ms.countryid";
            DbConnect.DbCon().beginTransaction();
            Query query = DbConnect.DbCon().createNativeQuery(hql);
            List<Object[]> addedCountryState = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Country name  and State name  List Retrrived");
            return addedCountryState;
        } catch (Exception e) {
            logger.error("failed to get Execute Sql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // delete main country list.
    @Override
    public String deleteMainCountries(int id) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "update Main_CountriesEntity set isactive=:isactive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);
            query.setParameter("isactive", 0);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Country Deleted Successfully");
            return "Deleted Successfully";
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // delete main State List.
    @Override
    public String deleteMainState(int id) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "update Main_StatesEntity set isactive=:isactive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);
            query.setParameter("isactive", 0);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("State Deleted Successfully");
            return "Deleted Successfully";

        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    @Override
    public List<Object[]> getCountryStateCityView() {
        // DbConnect db = new DbConnect();
        try {

            /*
             * String hql =
             * "select mc.country,mc.country_id_org,ms.state_id_org,ms.state, mcc.city,city_org_id, mcc.id\r\n"
             * + "from main_cities mcc, main_states ms,\r\n" +
             * "  main_countries mc where\r\n" + "   mcc.countryid=mc.country_id_org\r\n" +
             * "   and ms.countryid=mc.country_id_org\r\n" + "   and mcc.isactive=1";
             */

            String hql = "select mc.country,mc.country_id_org,ms.state_id_org,ms.state, mcc.city,city_org_id, mcc.id \r\n"
                    + "from main_cities mcc, main_states ms,  main_countries mc where\r\n"
                    + " mcc.countryid=mc.country_id_org  and ms.countryid=mc.country_id_org  and \r\n"
                    + " ms.state_id_org=mcc.state";
            DbConnect.DbCon().beginTransaction();
            Query query = DbConnect.DbCon().createNativeQuery(hql);
            List<Object[]> addedCountryStateCity = query.getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Country name, State name, City name  List Retrrived");
            return addedCountryStateCity;

        } catch (Exception e) {
            logger.error("failed to get Execute Sql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // delete city

    @Override
    public String deleteMainCity(int id) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "update Main_citiesEntity set isactive=:isactive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);

            query.setParameter("isactive", 0);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("City Deleted Successfully");
            return "Deleted Successfully";

        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // Update of main Country List.

    public String updateMainStateList(Main_CountriesEntity countryEntity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(countryEntity);
            logger.info("Main Country Updated Successfully.");
            DbConnect.DbCon().getTransaction().commit();
            return "Updated Successfully";
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    @Override
    public boolean updateMainStatesView(Main_StatesEntity statesEntity) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(statesEntity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Updated Successfully.");
            return true;

        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    @Override
    public String updateMainCity(Main_citiesEntity cityEntity) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(cityEntity);
            logger.info("city updated Successfully");
            DbConnect.DbCon().getTransaction().commit();
            logger.info("updated.");
            return "Updated Successfully.";
        } catch (HibernateException e) {
            logger.error("exception failed to get execute." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    /*
     * // update main States list.
     *
     * @Override public String updateMainStatesView(Main_StatesEntity statesEntity)
     * { //DbConnect db = new DbConnect(); try { Transaction tx =
     * db.DbCon().beginTransaction(); db.DbCon().update(statesEntity);
     * logger.info("Main States Updated Successfully.");
     * DbConnect.DbCon().getTransaction().commit(); return "Updated Successfully"; }
     * catch (HibernateException e) {
     * logger.error("failed to get Execute hql query:" + e); return null; } finally
     * { db.DbCon().close(); db.dbSessionFactory().close();
     * logger.debug("Session status:" + db.DbCon().isOpen());
     * logger.debug("Session Factory Status:" + db.dbSessionFactory().isOpen()); }
     *
     * }
     */

    @Override
    public String updateCityMain(int id) {
        // DbConnect db = new DbConnect();
        try {
            String hql = "update Main_citiesEntity set isactive=:isactive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);
            query.setParameter("isactive", 1);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Main Cities  Updated Successfully");
            return "Updated Successfully";
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // update main state isActive.

    @Override
    public String updateStatesMain(int id) {
        // DbConnect db = new DbConnect();
        try {
            String hql = "update Main_StatesEntity set isactive=:isactive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);

            query.setParameter("isactive", 1);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Main States Updated Successfully");
            return "Updated Successfully";
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // update main country isActive.
    @Override
    public String updateCountriesMain(int id) {
        // DbConnect db = new DbConnect();
        try {
            String hql = "update Main_CountriesEntity set isactive=:isactive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);

            query.setParameter("isactive", 1);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Main Countries Updated Successfully");
            return "Updated Successfully";
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // update main zone isActive.
    @Override
    public String updateZoneTimeMain(int id) {
        // DbConnect db = new DbConnect();
        try {
            String hql = "update Main_TimezoneEntity set isactive=:isactive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);
            query.setParameter("isactive", 1);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Main Zone Updated Successfully");
            return "Updated Successfully";
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // timezone which is also not active.
    @Override
    public List<Main_TimezoneEntity> getMainTimezoneviewList() {
        // DbConnect db = new DbConnect();
        try {
            String hql = "from Main_TimezoneEntity";
            DbConnect.DbCon().beginTransaction();
            Query<Main_TimezoneEntity> query = DbConnect.DbCon().createQuery(hql, Main_TimezoneEntity.class);
            List<Main_TimezoneEntity> zoneIsActiveList = query.getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Timezone list which is Inactive and Active.");
            return zoneIsActiveList;
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    /// deleteing complete main State .
    public boolean delMainState(int id, int stateId) {
        logger.info("Inside delMainState method");
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "from Main_Departments where stateId=" + stateId;
            String hql2 = "from Main_Businessunits where state=" + stateId;

            List<Object[]> respectiveStateList1 = DbConnect.DbCon().createQuery(hql).list();

            List<Object[]> respectiveStateList12 = DbConnect.DbCon().createQuery(hql2).list();

            if (respectiveStateList1.isEmpty() && respectiveStateList12.isEmpty()) {

                Main_StatesEntity rowOfMainState = DbConnect.DbCon().byId(Main_StatesEntity.class)
                        .load(id);
                DbConnect.DbCon().delete(rowOfMainState);
                DbConnect.DbCon().getTransaction().commit();
                logger.info("Deleted Successfully.");
                return true;
            } else {
                logger.info("State Can't Delete, It is in Use With Some BusinessUnit and Departments.");
                DbConnect.DbCon().getTransaction().rollback();
                return false;
            }

        } catch (HibernateException e) {
            logger.error("Exception occured while deleting delMainState " + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } catch (Exception e) {
            logger.error("failed to get Execute Sql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        }

    }

    // delete main country Table row Complete.
    public boolean delmainCountry(int id, int countryId) {
        // DbConnect db = new DbConnect();
        try {

            DbConnect.DbCon().beginTransaction();

            String hql = "from Main_Departments where countryId=" + countryId;
            String hql2 = "from Main_Businessunits where country=" + countryId;

            List<Object[]> respectiveCountryList1 = DbConnect.DbCon().createQuery(hql).list();

            List<Object[]> respectiveCountryList12 = DbConnect.DbCon().createQuery(hql2).list();

            if (respectiveCountryList1.isEmpty() && respectiveCountryList12.isEmpty()) {
                Main_CountriesEntity rowMaincountry = DbConnect.DbCon().byId(Main_CountriesEntity.class).load(id);
                DbConnect.DbCon().delete(rowMaincountry);
                DbConnect.DbCon().getTransaction().commit();
                logger.info("Deleted Successfully.");
                return true;
            } else {
                logger.info("Country Can't Delete, It is in Use With Some BusinessUnit and Departments.");
                DbConnect.DbCon().getTransaction().rollback();
                return false;
            }
        } catch (HibernateException e) {
            logger.error("Exception Occured while Deleting Main Country.");
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;

        } catch (Exception e) {
            logger.error("failed to get Execute Sql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // delete main city Table row Complete.
    @Override
    public boolean delmainCity(int id, int cityIds) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();

            String hql = "from Main_Departments where cityId=" + cityIds;
            String hql2 = "from Main_Businessunits where city=" + cityIds;

            List<Object[]> respectiveCityList1 = DbConnect.DbCon().createQuery(hql).list();

            List<Object[]> respectiveCityList12 = DbConnect.DbCon().createQuery(hql2).list();

            if (respectiveCityList1.isEmpty() && respectiveCityList12.isEmpty()) {
                Main_citiesEntity rowMainCity = DbConnect.DbCon().byId(Main_citiesEntity.class).load(id);
                DbConnect.DbCon().delete(rowMainCity);
                DbConnect.DbCon().getTransaction().commit();
                logger.info("Deleted Successfully.");
                return true;
            } else {
                logger.info("City Can't Delete, It is in Use With Some BusinessUnit and Departments.");
                DbConnect.DbCon().getTransaction().rollback();
                return false;
            }
        } catch (HibernateException e) {
            logger.error("Exception Occured while Deleting Main City.");
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } catch (Exception e) {
            logger.error("failed to get Execute Sql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    /*
     * // delete timezone
     *
     * @Override public boolean deleteMainTimezone(int id, int actualId) { DbConnect
     * db = new DbConnect(); Main_Businessunits t = new Main_Businessunits();
     *
     * List respectiveZone; try {
     *
     * String hql =
     * "update Main_TimezoneEntity set isactive=:isactive where id=:id";
     *
     * db.DbCon().beginTransaction();
     *
     * String hql2 = "from Main_Businessunits where timeZone=" + actualId;
     * respectiveZone = db.DbCon().createQuery(hql2).list();
     *
     * if (respectiveZone.isEmpty()) { Query query = db.DbCon().createQuery(hql);
     *
     * query.setParameter("isactive", 0); query.setParameter("id", id); int i =
     * query.executeUpdate(); DbConnect.DbCon().getTransaction().commit();
     * logger.info("Main Timezone Deleted Successfully"); return
     * "Deleted Successfully";
     *
     * return true; } else { logger.
     * info("Timezone Can't Delete, It is in Use With Some BusinessUnit and Departments."
     * ); return false;
     *
     * }
     *
     * } catch (HibernateException e) {
     *
     * logger.error("failed to get Execute hql query:" + e); return false; } finally
     * { db.DbCon().close(); db.dbSessionFactory().close();
     * logger.debug("Session status:" + db.DbCon().isOpen());
     * logger.debug("Session Factory Status:" + db.dbSessionFactory().isOpen()); } }
     */

    // delete timezone

    @Override
    public boolean deleteMainTimezone(int id, int actualId) {
        // DbConnect db = new DbConnect();

        try {
            DbConnect.DbCon().beginTransaction();
            String hql1 = "from Main_Businessunits where timezone=" + actualId;
            String hql2 = "from Main_Departments where timeZoneId=" + actualId;
            List<?> respectiveZoneList1 = DbConnect.DbCon().createQuery(hql1).list();
            List<?> respectiveZoneList2 = DbConnect.DbCon().createQuery(hql2).list();
            if (respectiveZoneList1.isEmpty() && respectiveZoneList2.isEmpty()) {
                Main_TimezoneEntity deleteZoneCompleteRow = DbConnect.DbCon().byId(Main_TimezoneEntity.class).load(id);
                DbConnect.DbCon().delete(deleteZoneCompleteRow);

                DbConnect.DbCon().getTransaction().commit();
                logger.info("Main Timezone Deleted Successfully");

                return true;
            } else {
                logger.info("Timezone Can't Delete, It is in Use With Some BusinessUnit and Departments.");
                DbConnect.DbCon().getTransaction().rollback();
                return false;
            }
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } catch (Exception e) {
            logger.error("failed to get Execute Sql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

}
