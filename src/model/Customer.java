package model;

import java.sql.*; 

public class Customer {

	
	

	public  Connection connect() { 
		
		Connection con = null;
		
		try {
		 
		 	Class.forName("com.mysql.cj.jdbc.Driver");
		 	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/powereg", "root", "1234");
		 	//System.out.print("connected");
	 		
		}
	 
	 	catch(Exception e) {
	 		
	 		e.printStackTrace();
	 		//System.out.print("not connected");
	 		}

		return con;

	 
	}
	


	public String insertCustomer( String CustomerName, String Email, String Address, String Phone) { 
		
		String output = ""; 
	 
		try { 
			Connection con = connect();
	  
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			} 
	 
			String query = " insert into Customer (`cus_id`, `customer_name`,`email`,`address`,`phone`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, CustomerName); 
			preparedStmt.setString(3, Email); 
			preparedStmt.setString(4, Address); 
			preparedStmt.setString(5, Phone); 
			
			
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the Customer."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 

	
	public String readCustomer() 
	 { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading.";
				} 
	
			output = "<table border='1'> <tr><th>Cus ID</th>"
					+ "<th>Customer Name</th>"
					+ "<th>Email</th>"
					+ "<th>Address</th>"
					+ "<th>Phone</th></tr>";
				 
	 
			String query = "select * from Customer"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
	 
	
			while (rs.next()) 
			{ 
		 
				String cus_id = Integer.toString(rs.getInt("cus_id")); 
				String customer_name = rs.getString("Customer_name"); 
				String email = rs.getString("email"); 
				String address = rs.getString("address"); 
				String phone = rs.getString("phone"); 
				 
	 
	
				output += "<tr><td>" + cus_id + "</td>";
				output += "<td>" + customer_name + "</td>";
				output += "<td>" + email + "</td>"; 
				output += "<td>" + address + "</td>"; 
				output += "<td>" + phone + "</td>"; 
				 
	 
				 // buttons 		
				   output
						  += "<td><input name='btnUpdate' "
						  + " type='button' value='Update' class='btn btn-secondary' </td>"
				 		  + "<td><form method='post' action='Products.jsp'>"
				 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
				 		  + "<input name='Customer' type='hidden' " + " value='" + cus_id + "'>" + "</form></td></tr>";
				 		 
			} 
			
	 con.close(); 
	 
	// Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the Customer."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateCustomer(String CustomerID, String CustomerName, String Email, String Address, String Phone ) { 
		
	 {
		
	 String output = "";
	 
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null) {
			 
			 return "Error while connecting to the database for updating.";
			 }
	 
		 String query = "UPDATE Customer SET Customer_name=?, email=?, address=?, phone=? WHERE cus_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, CustomerName); 
		 preparedStmt.setString(2, Email); 
		 preparedStmt.setString(3, Address); 
		 preparedStmt.setString(4, Phone); 
		
		 preparedStmt.setInt(9, Integer.parseInt(CustomerID));
	
		 preparedStmt.execute();
		 con.close();
		 
		 
		 output = "Updated successfully";
		
	 }
	 catch (Exception e){
		 
		 output = "Error while updating the Customer.";
		 System.err.println(e.getMessage());
	 
	 }
	 return output;
	 } 
	
	
	
	}	
	
	
	
	public String deleteCustomer(String cus_id)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	 
			String query = "delete from Customer where cus_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);


			preparedStmt.setInt(1, Integer.parseInt(cus_id));
			
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
	 catch (Exception e) {
		 
		 output = "Error while deleting the Customer.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	 
}
