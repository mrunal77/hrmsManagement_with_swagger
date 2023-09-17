package com.rainier.businesslogic;

import com.rainier.beans.SelectedDocumentResponseBean;
import com.rainier.beans.SuccessResponseBean;
import com.rainier.dao.HrmsEmployeeProfessionalDetailsDao;
import com.rainier.dao.Impl.HrmsEmployeeProfessionalDetails;
import com.rainier.dto.responseBean.OnboardingConfirmEmailResponseBean;
import com.rainier.entities.EmployeeDocumentsEntity;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProfessionalDetails {
    private static final Logger logger = Logger.getLogger(EmployeeProfessionalDetails.class);
    private static final HrmsEmployeeProfessionalDetailsDao dao = new HrmsEmployeeProfessionalDetails();

    // Get Selected Documents For Perticular User Id.
    public Response getSelectedVisaDocuments(int userId) {
        logger.info("getSelectedVisaDocuments() business logic");
        // Getting List of Visa Doc Ids
        String visaDocIds = dao.getSelectedVisaDocumentsId(userId);
        // System.out.println(visaDocIds);
        SelectedDocumentResponseBean responseDocuments = new SelectedDocumentResponseBean();
        if (!this.idSeperation(visaDocIds).isEmpty()) {
            List<EmployeeDocumentsEntity> list = dao.getSelectedVisaDocuments(this.idSeperation(visaDocIds), userId);
            responseDocuments.setMessage("Visa Document List.");
            responseDocuments.setStatus(true);
            responseDocuments.setListOfDocuments(list);
        } else {
            responseDocuments.setMessage("No Document Found");
            responseDocuments.setStatus(false);
            responseDocuments.setListOfDocuments(new ArrayList<>());
        }
        return Response.ok().entity(responseDocuments).build();
    }

    // Visa Document Ids Seperation
    private List<Integer> idSeperation(String visaDocIds) {
        logger.info("idSeperation() business logic");
        List<Integer> visaDocList = new ArrayList<>();
        if (visaDocIds != null && !visaDocIds.isEmpty()) {
            String[] visaDoc;
            try {
                visaDoc = visaDocIds.split(",");
            } catch (Exception e) {
                // visaDoc[0] = visaDocIds;
                visaDoc = new String[]{visaDocIds};
                // System.out.println("Visa documents");
            }
            for (String item : visaDoc) {
                // System.out.println(item);
                visaDocList.add(Integer.parseInt(item));
            }
        }
        return visaDocList;
    }

    // Profile Image Upload
    public Response uploadProfilePhoto(int userId, String uploadPath) {
        logger.info("uploadProfilePhoto() business logic");
        SuccessResponseBean response = new SuccessResponseBean();
        if (dao.uploadProfilePhoto(userId, uploadPath)) {
            response.setMessage("Profile Picture Upload Successful");
            response.setUploadURL(uploadPath);
            response.setStatus(true);
        } else {
            response.setMessage("Profile Picture Upload Failed");
            response.setUploadURL("Sorry Check the file you are uploading.");
            response.setStatus(false);
        }
        return Response.ok().entity(response).build();
    }

// --Commented out by Inspection START (26-Nov-18 11:07 AM):
//    // Get Profile Photo
//    public Response getProfilePhoto(int userId) {
//        logger.info("getProfilePhoto() business logic");
//        String filePath = dao.getProfileImagePath(userId);
//        if (filePath == null || filePath.equalsIgnoreCase("")) {
//            return Response.noContent().build();
//        } else {
//            File file = new File(filePath);
//            return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
//                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"").build();
//        }
//    }
// --Commented out by Inspection STOP (26-Nov-18 11:07 AM)

    // Upload Documents
    public Response uploadDocuments(int userId, int documentId, String uploadPath) {
        logger.info("uploadDocuments() business logic");
        SuccessResponseBean response = new SuccessResponseBean();
        if (dao.uploadDocument(userId,documentId,uploadPath)) {
            response.setMessage("Document Upload Successful");
            response.setUploadURL(uploadPath);
            response.setStatus(true);
        } else {
            response.setMessage("Document Upload Failed");
            response.setUploadURL("Sorry Check the file you are uploading.");
            response.setStatus(false);
        }
        return Response.ok().entity(response).build();
    }


    // OnBoarding Contact Info.
    public Response findOnboardContactInfo(String firstName, String lastName, String email, int userId) {
        OnboardingConfirmEmailResponseBean response = new OnboardingConfirmEmailResponseBean();
        try {
            if (dao.onBoardingContactInfo(firstName, lastName, email, userId)) {
                response.setMessage("Success.");
                response.setStatus(true);
                response.setActiveTab("contoctinfo");
            } else {
                response.setMessage("Email Not Exist.");
                response.setStatus(false);
            }
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            logger.info("looging exception occured in findOnboardContactInfo method of business class:: " + e);
            e.printStackTrace();
            response.setMessage("Error while fetching budu based employee details - " + e.getMessage());
            response.setStatus(false);
            return Response.ok().entity(response).build();
        }
    }


}
