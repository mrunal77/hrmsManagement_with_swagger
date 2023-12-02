package com.rainier.core;


import com.rainier.dbconfiguration.DbConnect;
import com.rainier.services.*;
import io.swagger.jaxrs.config.BeanConfig;
import org.apache.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/api")
public class AppStart extends Application {
    private static final Logger logger = Logger.getLogger(AppStart.class);

    // Constructor
    public AppStart() {
        logger.info("Swagger Configuration......");
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/HRMS/api");
        beanConfig.setResourcePackage("com.rainier.services");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
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
        logger.info("Classes Starting......");
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
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.provider.classnames", "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return props;
    }
}
