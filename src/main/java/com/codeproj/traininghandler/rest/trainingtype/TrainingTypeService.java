package com.codeproj.traininghandler.rest.trainingtype;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
 
@Path("/trainingtype")
public class TrainingTypeService {
 
	@GET
	@Path("/get/")
	public Response getMsg() {
 
		String output = "This is a hello world output for config";
 
		return Response.status(200).entity(output).build();
 
	}
 
}