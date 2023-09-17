package com.rainier.dao.Impl;

import com.rainier.dao.ClientDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.dto.requestBean.ClientRequestBean;
import com.rainier.dto.responseBean.CommonResponseBean;
import com.rainier.entities.ClientsEntity;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

public class ClientDaoImpl implements ClientDao {
    private static final Logger logger = Logger.getLogger(ClientDaoImpl.class);

    @Override
    public ClientsEntity getClientDetails(int clientId) {
        logger.info("inside of getClientDetails method");
        ClientsEntity client = null;
        try {
            DbConnect.DbCon().beginTransaction();
            client = DbConnect.DbCon().get(ClientsEntity.class, clientId);
            DbConnect.DbCon().getTransaction().commit();
        } catch (Exception e) {
            DbConnect.DbCon().getTransaction().rollback();
            logger.info("inside catch block of getClientDetails method...exception:" + e);
        } finally {
        	DbConnect. DbCon().clear();
        }
        return client;
    }

    @Override
    public CommonResponseBean saveClientDetails(ClientRequestBean clientBean) {
        // DbConnect dbConnect = new DbConnect();
        ClientsEntity client = new ClientsEntity();
        CommonResponseBean commonResponseBean = new CommonResponseBean();
        try {
            DbConnect.DbCon().beginTransaction();
            client.setId(clientBean.getClientId());
            client.setClientName(clientBean.getClientName());
            client.setEmail(clientBean.getEmail());
            client.setPhoneNo(clientBean.getPhoneNo());
            client.setPoc(clientBean.getPoc());
            client.setAddress(clientBean.getAddress());
            client.setCountryId(clientBean.getCountryId());
            client.setStateId(clientBean.getStateId());
            client.setFax(clientBean.getFax());
            // client.setIs_Active(clientBean.getIsActive());
            client.setCreatedBy(clientBean.getCreatedBy());
            client.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            client.setModifiedBy(clientBean.getModifiedBy());
            client.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
            DbConnect.DbCon().save(client);
            DbConnect.DbCon().getTransaction().commit();
            // Filling response bean
            commonResponseBean.setMessage("Client details inserted successfully.");
            commonResponseBean.setStatus(true);
        } catch (Exception e) {
            logger.info("inside catch block of saveClientDetails of daoImpl:" + e);
            DbConnect.DbCon().getTransaction().rollback();
        } finally {
            DbConnect.DbCon().clear();
        }
        return commonResponseBean;
    }

    // fetching all client details.
    @Override
    public List<ClientsEntity> getClients() {
        // DbConnect db = new DbConnect();
        List<ClientsEntity> list;
        try {
            String hql = "From ClientsEntity where is_active=1";
            DbConnect.DbCon().beginTransaction();
            Query<ClientsEntity> query = DbConnect.DbCon().createQuery(hql, ClientsEntity.class);
            list = query.getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Successfully Clients List retrived.");
            return list;
        } catch (Exception e) {
            logger.error("failed to fetch client details:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return new ArrayList<>();
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
            DbCon().clear();
        }
    }

    // update Client Details.
    @Override
    public boolean updateClientInfo(ClientsEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Update Successfully.");
            return true;
        } catch (Exception e) {
            logger.error("Failed to execute Hql Query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
            DbCon().clear();
        }

    }

    // Delete clients.
    @Override
    public String deleteClients(int id) {
        // DbConnect db = new DbConnect();
        String clientName;
        try {
            String hql = "update ClientsEntity ce set ce.is_active=:is_active where ce.id=:id";
            DbConnect.DbCon().beginTransaction();
            String hql1 = "select clientName from ClientsEntity where id=" + id;
            clientName = (String) DbConnect.DbCon().createQuery(hql1).uniqueResult();
            Query query = DbConnect.DbCon().createQuery(hql);
            query.setParameter("is_active", 0);
            query.setParameter("id", id);
            query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Client Deleted Successsfully...");
            return clientName;
        } catch (HibernateException e) {
            logger.error("Failed to get Execute Hql Query" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            //// DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
            DbCon().clear();
        }

    }

    // saving clients
    @Override
    public boolean saveClientsInfo(ClientsEntity entity) {

        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().save(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("clients  added Successfully");
            return true;
        } catch (HibernateException e) {
            logger.error("Failed to get Exexute hql query" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status :" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
            DbCon().clear();
        }

    }
}
