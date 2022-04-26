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

import model.Customer;



@Path("/Customers")

public class CustomerService {

	Customer pay = new Customer();
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readCustomer() 
	{ 
		return pay.readCustomer(); 
	
	}
	
	
	
	
	
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertCustomer(@FormParam("customer_name") String customer_name, 
						@FormParam("email") String email, 
						@FormParam("address") String address, 
						@FormParam("phone") String phone)
						
	
	{ 
	
		String output = pay.insertCustomer(customer_name, email, address, phone ); 
		return output; 

	}









	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateCustomer(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		String cus_id  = itemObject.get("cus_id").getAsString();
		String customer_name    = itemObject.get("customer_name").getAsString();
		String email    = itemObject.get("email").getAsString();
		String address   = itemObject.get("address").getAsString();
		String phone       = itemObject.get("phone").getAsString();
		
	
		String output = pay.updateCustomer(cus_id, customer_name, email, address,phone  );

		return output;
		
}









	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteItem(String itemData)
	{

		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String cus_id = doc.select("cus_id").text();
	
		String output = pay.deleteCustomer(cus_id);
	
		return output;
	}


}