package model;

import java.sql.*; 

public class Bill {

	
	

	public  Connection connect() { 
		
		Connection con = null;
		
		try {
		 
		 	Class.forName("com.mysql.cj.jdbc.Driver");
		 	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/powereg", "root", "");
		 	//System.out.print("connected");
	 		
		}
	 
	 	catch(Exception e) {
	 		
	 		e.printStackTrace();
	 		//System.out.print("not connected");
	 		}

		return con;

	 
	}
	


	public String insertBill( String ElectricityAccNo, String CustomerName, String Address, String Month, String MeterUnits, String UnitPrice, String TotalAmount) { 
		
		String output = ""; 
	 
		try { 
			Connection con = connect();
	  
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			} 
	 
			String query = " insert into Bill (`bill_id`, `ele_Acc_num`,`cus_name`,`address`,`month`,`meter_units`,`unit_price`,`total_amount`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, ElectricityAccNo); 
			preparedStmt.setString(3, CustomerName); 
			preparedStmt.setString(4, Address); 
			preparedStmt.setString(5, Month); 
			preparedStmt.setString(6, MeterUnits); 
			preparedStmt.setString(7, UnitPrice); 
			preparedStmt.setString(8, TotalAmount);
	 
	
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the Bill."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 

	
	public String readBill() 
	 { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading.";
				} 
	
			output = "<table border='1'> <tr><th>Bill ID</th>"
					+ "<th>Electricity Acc No</th>"
					+ "<th>Customer Name</th>"
					+ "<th>Address</th>"
					+ "<th>Month</th>"
					+ "<th>Meter Units</th>"
					+ "<th>Unit_Price</th>"
					+ "<th>Total Amount</th>"; 
	 
			String query = "select * from Bill"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
	 
	
			while (rs.next()) 
			{ 
		 
				String bill_id = Integer.toString(rs.getInt("bill_id")); 
				String ele_Acc_num = rs.getString("ele_Acc_num"); 
				String cus_name = rs.getString("cus_name"); 
				String address = rs.getString("address"); 
				String month = rs.getString("month"); 
				String meter_units = rs.getString("meter_units"); 
				String unit_price = rs.getString("unit_price"); 
				String total_amount = rs.getString("total_amount");  
	 
	
				output += "<tr><td>" + bill_id + "</td>";
				output += "<td>" + ele_Acc_num + "</td>";
				output += "<td>" + cus_name + "</td>"; 
				output += "<td>" + address + "</td>"; 
				output += "<td>" + month + "</td>"; 
				output += "<td>" + meter_units + "</td>"; 
				output += "<td>" + unit_price + "</td>"; 
				output += "<td>" + total_amount + "</td>"; 
	 
				 // buttons 		
				   output
						  += "<td><input name='btnUpdate' "
						  + " type='button' value='Update' class='btn btn-secondary' </td>"
				 		  + "<td><form method='post' action='Products.jsp'>"
				 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
				 		  + "<input name='Bill' type='hidden' " + " value='" + bill_id + "'>" + "</form></td></tr>";
				 		 
			} 
			
	 con.close(); 
	 
	// Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the Bill."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateBill(String BillID, String ElectricityAccNo, String CustomerName, String Address, String Month, String MeterUnits, String UnitPrice, String TotalAmount) { 
		
	 {
		
	 String output = "";
	 
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null) {
			 
			 return "Error while connecting to the database for updating.";
			 }
	 
		 String query = "UPDATE Bill SET ele_Acc_num=?, cus_name=?, address=?, month=?, meter_units=?, unit_price=?, total_amount=? WHERE bill_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, ElectricityAccNo); 
		 preparedStmt.setString(2, CustomerName); 
		 preparedStmt.setString(3, Address); 
		 preparedStmt.setString(4, Month); 
		 preparedStmt.setString(5, MeterUnits); 
		 preparedStmt.setString(6, UnitPrice); 
		 preparedStmt.setString(7, TotalAmount);
		 preparedStmt.setInt(8, Integer.parseInt(BillID));
	
		 preparedStmt.execute();
		 con.close();
		 
		 
		 output = "Updated successfully";
		
	 }
	 catch (Exception e){
		 
		 output = "Error while updating the Bill.";
		 System.err.println(e.getMessage());
	 
	 }
	 return output;
	 } 
	
	
	
	}	
	
	
	
	public String deleteBill(String bill_id)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	 
			String query = "delete from Bill where bill_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);


			preparedStmt.setInt(1, Integer.parseInt(bill_id));
			
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
	 catch (Exception e) {
		 
		 output = "Error while deleting the Bill.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	 
}
