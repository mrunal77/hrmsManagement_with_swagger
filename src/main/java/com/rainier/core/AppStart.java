package com.rainier.core;

import com.rainier.dbconfiguration.DbConnect;
import com.rainier.services.*;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/api")
public class AppStart extends Application {
    private static final Logger logger = Logger.getLogger(AppStart.class);
    private static ApplicationContext context;

    // Constructor
    public AppStart() {
        configureSwagger();
        initializeDatabase();
    }

    private void configureSwagger() {
        logger.info("Configuring Swagger...");

        // Setup Swagger info
        Info swaggerInfo = new Info()
                .title("HRMS API")
                .description("API documentation for the HRMS system")
                .version("1.0")
                .contact(new Contact().name("Support Team").email("support@company.com"))
                .license(new License().name("Company License").url("http://company.com/license"));

        Swagger swagger = new Swagger();
        swagger.setInfo(swaggerInfo);

        // JWT Security Definition
        ApiKeyAuthDefinition jwtAuth = new ApiKeyAuthDefinition("Authorization", In.HEADER);
        swagger.addSecurityDefinition("JWT", jwtAuth);

        // Setting content types
        swagger.setConsumes(java.util.Collections.singletonList("application/json"));
        swagger.setProduces(java.util.Collections.singletonList("application/json"));

        // Configure BeanConfig for Swagger
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/HRMS/api");
        beanConfig.setResourcePackage("com.rainier.services");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);

        logger.info("Swagger configuration complete.");
    }

    private void initializeDatabase() {
        logger.info("Initializing database connection...");

        try {
            // Begin transaction
            DbConnect.DbCon().beginTransaction();

            // Test connection with a simple query
            DbConnect.DbCon().createNativeQuery("SELECT 1").getResultList();

            // Commit transaction
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Database initialized successfully.");
        } catch (Exception e) {
            // Rollback the transaction in case of failure
            if (DbConnect.DbCon().getTransaction().isActive()) {
                DbConnect.DbCon().getTransaction().rollback();
            }
            logger.error("Database initialization failed: ", e);
        }
    }

    @Override
    public Set<Class<?>> getClasses() {
        return getRestClasses();
    }

    private Set<Class<?>> getRestClasses() {
        logger.info("Registering REST services...");
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(CORSFilter.class);
        resources.add(JwtFilter.class);
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
        logger.info("REST services registered successfully.");
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.provider.classnames", "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return props;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
