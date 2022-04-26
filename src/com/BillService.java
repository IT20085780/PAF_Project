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

import model.Bill;



@Path("/bills")

public class BillService {

	Bill pay = new Bill();
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readBill() 
	{ 
		return pay.readBill(); 
	
	}
	
	
	
	
	
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 

	public String insertBill(@FormParam("ele_Acc_num") String ele_Acc_num,
						@FormParam("cus_name") String cus_name, 
						@FormParam("address") String address,
						@FormParam("month") String month,
						@FormParam("meter_units") String meter_units,
						@FormParam("unit_price") String unit_price,
						@FormParam("total_amount") String total_amount)
	
	{ 
	
		String output = pay.insertBill(ele_Acc_num, cus_name, address, month, meter_units, unit_price, total_amount ); 
		return output; 

	}









	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateBill(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		String bill_id  = itemObject.get("bill_id").getAsString();
		String ele_Acc_num    = itemObject.get("ele_Acc_num").getAsString();
		String cus_name    = itemObject.get("cus_name").getAsString();
		String address   = itemObject.get("address").getAsString();
		String month       = itemObject.get("month").getAsString();
		String meter_units     = itemObject.get("meter_units").getAsString();
		String unit_price      = itemObject.get("unit_price").getAsString();
		String total_amount      = itemObject.get("total_amount").getAsString();
	
		String output    = pay.updateBill(bill_id, ele_Acc_num, cus_name, address, month, meter_units, unit_price, total_amount );

		return output;
		
}









	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteBill(String itemData)
	{

		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String bill_id = doc.select("bill_id").text();
	
		String output = pay.deleteBill(bill_id);
	
		return output;
	}


}