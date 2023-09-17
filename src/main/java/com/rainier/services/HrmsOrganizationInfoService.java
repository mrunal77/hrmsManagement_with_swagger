package com.rainier.services;

import com.rainier.businesslogic.OrganizationInformation;
import com.rainier.dto.requestBean.OrganizationInfoRequestBean;
import com.rainier.utility.FileUploader;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

// @CrossOrigin(maxAge = 3600, allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("Organization")
public class HrmsOrganizationInfoService {
    final static Logger logger = Logger.getLogger(HrmsOrganizationInfoService.class);

    private static OrganizationInformation db = new OrganizationInformation();

    @Path("saveUpdateOrganizationInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
    public Response saveUpdateOrganizationInfo(@NotNull @FormDataParam("orgInfo") FormDataBodyPart jsonData,
                                               @NotNull @FormDataParam("filename") InputStream uploadInputStream,
                                               @NotNull @FormDataParam("filename") FormDataContentDisposition fileDetails) {
        logger.info("Inside saveUpdateOrganizationInfo()");
        jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);
        OrganizationInfoRequestBean bean = jsonData.getValueAs(OrganizationInfoRequestBean.class);
        if (fileDetails != null) {
            String fileName = fileDetails.getFileName();
            FileUploader upload = new FileUploader();
            String uploadedFilePath = upload.getUploadPath(0, fileName, uploadInputStream);
            if (!uploadedFilePath.equalsIgnoreCase("")) {
                bean.setOrgImage(uploadedFilePath);
            }
            return db.saveUpdateOrganizationInfo(bean);
        } else
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
    }

    @Path("getOrganizationInfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizationInfo(@QueryParam(value = "roleId") int roleId,
			@QueryParam(value = "menuId") int menuId) {
        logger.info("Inside getOrganizationInfo()");
        return db.getOrganizationInfo(roleId, menuId);
    }

    @Path("getOrganizationStructure")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizationStructure() {
        logger.info("Inside getOrganizationStructure()");
        return db.getOrganizationStructure();
    }

    @Path("getOrganizationHierarchy")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizationHierarchy() {
        logger.info("Inside getOrganizationHierarchy()");
        return db.getOrganizationHierarchy();
    }
}
