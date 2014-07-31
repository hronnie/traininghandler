package com.codeproj.traininghandler.rest.trainingtype;
 
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;
 
@Path("/trainingtype")
public class TrainingTypeService {
 
	@GET
	@Path("/get/")
	public Response getMsg() {
 
		String output = "This is a hello world output for config";
 
		return Response.status(200).entity(output).build();
 
	}
	
	
	@POST
	@Path("/create/")
	@Produces("application/json; charset=UTF-8")
	public Response create(
			@FormParam("name") String name,
			@FormParam("levelNo") String levelNo,
			@FormParam("description") String description) throws ValidationException {
		
		
		name = ValidatorBaseUtility.stripXSS(name);
		levelNo = ValidatorBaseUtility.stripXSS(levelNo);
		description = ValidatorBaseUtility.stripXSS(description);
		TrainingTypeServiceValidator.create(name, levelNo, description);

		String output = "name: " + name + ", levelNo: " + levelNo + ", description: " + description;
		
		return Response.status(200).entity(output).build();
		
	}
 
}