package com.rainier.services;

import com.rainier.businesslogic.EmployeeProfessionalDetails;
import com.rainier.utility.FileUploader;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("professionalDetails")
public class HrmsEmployeeProfessionalDetailsService {

    final static Logger logger = Logger.getLogger(HrmsEmployeeDetailsService.class);
    private static EmployeeProfessionalDetails business = new EmployeeProfessionalDetails();
    private static FileUploader upload = new FileUploader();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @CrossOrigin
    @Path("/getSelectedDocuments")
    public Response getSelectedDocuments(@QueryParam(value = "userId") int userId) {
        logger.info("entered into getEmpList service class method..");
        return business.getSelectedVisaDocuments(userId);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("uploadProfilePhoto")
    public Response uploadPhoto(@QueryParam(value = "userId") int userId,
                                @FormDataParam("filename") InputStream uploadInputStream,
                                @FormDataParam("filename") FormDataContentDisposition fileDetails) {
        String fileExtension = fileDetails.getFileName().substring(fileDetails.getFileName().lastIndexOf("."));
        if (fileExtension.equalsIgnoreCase(".jpg") || fileExtension.equalsIgnoreCase(".png") || fileExtension.equalsIgnoreCase(".jpeg")) {
            String fileName = "profile_" + userId + fileExtension;
            // FileUploader upload = new FileUploader();
            String uploadedFileLocation = upload.getUploadPath(userId, fileName, uploadInputStream);
            if (!uploadedFileLocation.equals(""))
                return business.uploadProfilePhoto(userId, uploadedFileLocation);
            else
                return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Only JPG, PNG, JPEGFiles Accepted.").build();
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uploadDocuments")
    public Response uploadDocuments(@NotNull @QueryParam(value = "userId") int userId,
                                    @NotNull @QueryParam(value = "documentId") int documentId,
                                    @FormDataParam("filename") InputStream uploadInputStream,
                                    @FormDataParam("filename") FormDataContentDisposition fileDetails) {

        String fileExtension = fileDetails.getFileName().substring(fileDetails.getFileName().lastIndexOf("."));
        if (fileExtension.equalsIgnoreCase(".jpg") || fileExtension.equalsIgnoreCase(".png")
                || fileExtension.equalsIgnoreCase(".jpeg") || fileExtension.equalsIgnoreCase(".pdf")
                || fileExtension.equalsIgnoreCase(".doc") || fileExtension.equalsIgnoreCase(".docx")) {

            String fileName = "Document_" + documentId + "_" + userId + fileExtension;
            String uploadedFileLocation = upload.getUploadPath(userId, fileName, uploadInputStream);
            if (!uploadedFileLocation.equalsIgnoreCase("")) {
                logger.info("File Path:" + uploadedFileLocation);
                return business.uploadDocuments(userId, documentId, uploadedFileLocation);
            } else
                return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Only JPG,JPEG,PNG, DOC, DOCX, PDF accepted").build();
        }
    }

    // Parsing String to SQL Date
    private static Date getDocDate(String dateStr) {
        try {
            Date date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime());
            return date;
        } catch (ParseException e) {
            // e.printStackTrace();
            return null;
        }
    }
}
