package com.rainier.services;

import com.rainier.beans.NationalityAddBean;
import com.rainier.businesslogic.NationalityContextAddBL;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/contextcode")
@Api("/contextcode")
@SwaggerDefinition(tags = {@Tag(name = "contextcode", description = "contextcode")})
//Add Nationality Context code Services
public class NationalityContextService {
	public static Logger logger = Logger.getLogger(NationalityContextService.class);
	private static NationalityContextAddBL nationality = new NationalityContextAddBL();
	@Path("/Nationalitycode")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response addNationalityContext(NationalityAddBean bean){
		return nationality.addNationalityContext(bean);
	}
	
	// Fetch Nationality code List Service
		@Path("/RetriveNationalityCode")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin
		public Response retriveAllNationalityCode() {
			logger.info(" Entered into fetchCandidate() method ");
			return nationality.retriveAllNationalityCode();
		}
// Save and updated NationaLity Context Code
		
		@Path("/SaveUpdateNationality")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@CrossOrigin
		public Response saveUpdateNationalityContext(NationalityAddBean bean) {
			return nationality.saveUpdateNationalityContext(bean);

		}
		
		//delete Nationality Context Code

		//Delete Recruiters Details 
			@Path("/DeleteNationalitycode")
			@GET
			@Produces(MediaType.APPLICATION_JSON)
			@CrossOrigin
			public Response deleteNationalityContext(@QueryParam(value = "id") int id) {
				logger.info("Entered in Delete NationalityContext Service Method");
				logger.info("Existed in DeleteNationalityContext Service Method");
				return nationality.deleteNationalityContext(id);

			}
}
	

