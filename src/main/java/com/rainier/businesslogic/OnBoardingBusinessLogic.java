package com.rainier.businesslogic;

import com.rainier.beans.CommonResponseBean;
import com.rainier.dao.Impl.OnBoardingDaoImpl;
import com.rainier.dao.OnBoardingDaoClass;
import com.rainier.dto.requestBean.EmployeeCurrentAddressBean;
import com.rainier.dto.requestBean.OnBoardingJobDetailsRequestBean;
import com.rainier.dto.responseBean.OnboardingConfirmEmailResponseBean;
import com.rainier.entities.EmployeeCurrentAddressEntity;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Response;
import java.util.List;

public class OnBoardingBusinessLogic {
    private final static Logger logger = Logger.getLogger(OnBoardingBusinessLogic.class);
    private static final OnBoardingDaoClass board = new OnBoardingDaoImpl();

    public Response confirmJobDetails(OnBoardingJobDetailsRequestBean request) {
        logger.info("confirmJobDetails() Method of OnBoarding Business Logic");
        OnboardingConfirmEmailResponseBean response = new OnboardingConfirmEmailResponseBean();
        if (board.confirmJobDetails(request)) {
            response.setMessage("Success");
            response.setStatus(true);
            response.setActiveTab("offerDetails");
        } else {
            response.setMessage("Failed..");
            response.setStatus(false);
        }
        logger.info("Exiting confirmJobDetails() method...");
        return Response.ok().entity(response).build();
    }

    public Response addCurrentAddress(EmployeeCurrentAddressBean request) {
        logger.info("addCurrentAddress() of OnBoarding Business Logic");
        CommonResponseBean response = new CommonResponseBean();
        if (board.saveUpdateCurrentAddress(request)) {
            response.setMessage("Your Address Saved successfully...");
            if (request.getId() > 0)
                response.setMessage("Your address updated successfully...");
            response.setStatus(true);
        } else {
            response.setMessage("Please Check inputs are valid..");
            response.setStatus(false);
        }
        logger.info("Exiting addCurrentAddress() method...");
        return Response.status(Response.Status.OK).entity(response).build();
    }

    public Response getCurrentAddress(int userId) {
        logger.info("getCurrentAddress() of OnBoarding Business Logic.");
        List<EmployeeCurrentAddressEntity> listCurrentAddress = board.getCurrentAddresList(userId);
        JSONObject response = new JSONObject();
        if (!listCurrentAddress.isEmpty()) {
            response.put("status", true);
            response.put("message", "Current Address Fetched Successfully.");
            response.put("currentAddressList", listCurrentAddress);
            return Response.ok().entity(response).build();
        } else {
            response.put("status", false);
            response.put("message", "Current Address Not Found For your account.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
    }
}
