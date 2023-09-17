package com.rainier.services;

import com.rainier.businesslogic.EmployeeProfessionalDetails;
import com.rainier.businesslogic.OnBoardingBusinessLogic;
import com.rainier.dto.requestBean.EmployeeCurrentAddressBean;
import com.rainier.dto.requestBean.OnBoardingJobDetailsRequestBean;
import com.rainier.dto.requestBean.OnboardingConfirmEmailRequestBean;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// @CrossOrigin(maxAge = 3600, allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/Onboarding")
public class OnBoardingServices {
    private final static Logger logger = Logger.getLogger(OnBoardingServices.class);
    private static EmployeeProfessionalDetails details = new EmployeeProfessionalDetails();
    private static OnBoardingBusinessLogic businessLogic = new OnBoardingBusinessLogic();

    // Contact Info
    @POST
    @Path("/OnBoardContactInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmContactInfo(OnboardingConfirmEmailRequestBean requestBean) {
        logger.info("Inside confirmContactInfo method of Onboarding Services.");
        return details.findOnboardContactInfo(requestBean.getFirstName(), requestBean.getLastName(), requestBean.getEmailCheck(), requestBean.getUserId());
    }

    // Confirm Role Job Title
    @POST
    @Path("/ConfirmJobPosition")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response confirmJobPosition(OnBoardingJobDetailsRequestBean request) {
        logger.info("Inside confirmJobPosition method of Onboarding Services.");
        if (request != null)
            return businessLogic.confirmJobDetails(request);
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    // Current Address Save/Update
    @POST
    @Path("/updateSaveCurrentAddress")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCurrentAddress(@Valid EmployeeCurrentAddressBean request) {
        logger.info("Inside confirmJobPosition method of Onboarding Services.");
        if (request != null)
            return businessLogic.addCurrentAddress(request);
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @Path("/getCurrentAddress/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentAddress(@PathParam("userId") int userId) {
        logger.info("Getting Current Address Of the Employee..");
        return businessLogic.getCurrentAddress(userId);
    }
}
