package com.rainier.core;


import com.rainier.dbconfiguration.DbConnect;
import com.rainier.services.*;
import org.apache.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationPath(value = "hrms")
public class AppStart extends Application {
    private static final Logger logger = Logger.getLogger(AppStart.class);

    // Constructor
    public AppStart() {
        logger.info("Application Starting......");
        try {
            logger.info("Initializing Database Connection...");
           DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().createNativeQuery("select 1");
            // System.out.println(DbConnect.dbSessionFactory().getCurrentSession().isOpen());
            DbConnect.DbCon().getTransaction().commit();
        } catch (Exception e) {
            // System.out.println("Faild to Execute Query.");
            logger.error("Initializing Database Connection Failed..");
        }
    }

    @Override
    public Set<Class<?>> getClasses() {
        return getRestClasses();
    }

    private Set<Class<?>> getRestClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(CORSFilter.class);
        resources.add(HrmsLoginService.class);
        resources.add(HrmsBusinessService.class);
        resources.add(HrmsDepartmentService.class);
        resources.add(HrmsEmployeeDetailsService.class);
        resources.add(HrmsLocationService.class);
        resources.add(HrmsLeaveRequestService.class);
        resources.add(HrmsSelfService.class);
        resources.add(HrmsCommonService.class);
        resources.add(HrmsEmpTimeSheetService.class);
        resources.add(HrmsClientService.class);
        resources.add(HrmsProjectService.class);
        resources.add(HrmsEmployeeProfessionalDetailsService.class);
        resources.add(HrmsPersonalInfoService.class);
        resources.add(HrmsDashboardService.class);
        resources.add(HrmsSiteConfig.class);
        resources.add(HrmsOrganizationInfoService.class);
        resources.add(HrmsBackgroundVerificationService.class);
        resources.add(OnBoardingServices.class);
        resources.add(RecruitmentService.class);
        resources.add(HrmsPostProfileService.class);
        resources.add(HrmsBenchSalesService.class);
        resources.add(NationalityContextService.class);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.provider.classnames", "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return props;
    }
}
