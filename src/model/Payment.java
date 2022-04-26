package model;

import java.sql.*; 

public class Complaint {

	
	

	public  Connection connect() { 
		
		Connection con = null;
		
		try {
		 
		 	Class.forName("com.mysql.cj.jdbc.Driver");
		 	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		 	//System.out.print("connected");
	 		
		}
	 
	 	catch(Exception e) {
	 		
	 		e.printStackTrace();
	 		//System.out.print("not connected");
	 		}

		return con;

	 
	}
	


	public String insertComplaint( String CustomerName, String AriaOffice, String Address, String ContactNumber, String Email, String ComplaintDiscription) { 
		
		String output = ""; 
	 
		try { 
			Connection con = connect();
	  
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			} 
	 
			String query = " insert into Complaint (`complaint_id`, `customer_name`,`area_office`,`address`,`contact_number`,`email`,`complaint_disription`)" + " values (?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, CustomerName); 
			preparedStmt.setString(3, AreaOffice); 
			preparedStmt.setString(4, Address); 
			preparedStmt.setString(5, ContactNumber); 
			preparedStmt.setString(6, Email); 
			preparedStmt.setString(7, ComplaintDiscription); 
			
	
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the Complaint."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 

	
	public String readComplaint() 
	 { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading.";
				} 
	
			output = "<table border='1'> <tr><th>Complain ID</th>"
					+ "<th>Customer Name</th>"
					+ "<th>Area Office</th>"
					+ "<th>Address</th>"
					+ "<th>Contact Number</th>"
					+ "<th>Email</th>"
					+ "<th>Complain Discription</th>"; 
	 
			String query = "select * from Complaint"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
	 
	
			while (rs.next()) 
			{ 
		 
				String complaint_id = Integer.toString(rs.getInt("complaint_id")); 
				String customer_name = rs.getString("customer_name"); 
				String area_office = rs.getString("area_office"); 
				String address = rs.getString("address "); 
				String contact_number = rs.getString("contact_number"); 
				String email = rs.getString("email "); 
				String complaint_description = rs.getString("complaint_description");  
	 
	
				output += "<tr><td>" + complaint_id + "</td>";
				output += "<td>" + customer_name + "</td>";
				output += "<td>" + area_office + "</td>"; 
				output += "<td>" + address + "</td>"; 
				output += "<td>" + contact_number + "</td>"; 
				output += "<td>" + email  + "</td>"; 
				output += "<td>" + complaint_description + "</td>"; 
				
	 
				 // buttons 		
				   output
						  += "<td><input name='btnUpdate' "
						  + " type='button' value='Update' class='btn btn-secondary' </td>"
				 		  + "<td><form method='post' action='Products.jsp'>"
				 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
				 		  + "<input name='Complaint' type='hidden' " + " value='" + complaint_id + "'>" + "</form></td></tr>";
				 		 
			} 
			
	 con.close(); 
	 
	// Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the Complaint."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateComplaint(String ComplaintID, String CustomerName, String AreaOffice, String Address, String ContactNumber, String Email, String ComplaintDiscription ) { 
		
	 {
		
	 String output = "";
	 
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null) {
			 
			 return "Error while connecting to the database for updating.";
			 }
	 
		 String query = "UPDATE Complaint SET customer_name=?, area_office=?, address=?, contact_number=?, email=?, complain_description=? WHERE complaint_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, CustomerName); 
		 preparedStmt.setString(2, AreaOffice); 
		 preparedStmt.setString(3, Address); 
		 preparedStmt.setString(4, ContactNumber); 
		 preparedStmt.setString(5, Email); 
		 preparedStmt.setString(6, ComplaintDiscription); 
		 preparedStmt.setInt(9, Integer.parseInt(ComplaintID));
	
		 preparedStmt.execute();
		 con.close();
		 
		 
		 output = "Updated successfully";
		
	 }
	 catch (Exception e){
		 
		 output = "Error while updating the Complaint.";
		 System.err.println(e.getMessage());
	 
	 }
	 return output;
	 } 
	
	
	
	}	
	
	
	
	public String deleteComplaint(String complain_id)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	 
			String query = "delete from Complaint where complaint_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);


			preparedStmt.setInt(1, Integer.parseInt(complaint_id));
			
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
	 catch (Exception e) {
		 
		 output = "Error while deleting the Complaint.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	 
}
