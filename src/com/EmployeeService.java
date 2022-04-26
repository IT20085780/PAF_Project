package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Employee;



@Path("/employees")

public class EmployeeService {

	Employee pay = new Employee();
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readEmployee() 
	{ 
		return pay.readEmployee(); 
	
	}
	
	
	
	
	
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertEmployee(@FormParam("emp_name") String emp_name, 
						@FormParam("position") String position, 
						@FormParam("salary") String salary, 
						@FormParam("emp_type") String emp_type,
						@FormParam("phone") String phone)
						
						
	
	{ 
	
		String output = pay.insertEmployee(emp_name, position, salary, emp_type, phone ); 
		return output; 

	}









	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateEmployee(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		String emp_id  = itemObject.get("emp_id").getAsString();
		String emp_name    = itemObject.get("emp_name").getAsString();
		String position    = itemObject.get("position").getAsString();
		String salary   = itemObject.get("salary").getAsString();
		String emp_type       = itemObject.get("emp_type").getAsString();
		String phone     = itemObject.get("phone").getAsString();
		
	
		String output    = pay.updateEmployee(emp_id, emp_name, position,salary , emp_type, phone );

		return output;
		
}









	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteItem(String itemData)
	{

		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String emp_id = doc.select("emp_id").text();
	
		String output = pay.deleteEmpolyee(emp_id);
	
		return output;
	}


}