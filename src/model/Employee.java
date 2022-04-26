package model;

import java.sql.*; 

public class Employee {

	
	

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
	


	public String insertEmployee( String EmpName, String Position, String Salary, String EmpType, String Phone) { 
		
		String output = ""; 
	 
		try { 
			Connection con = connect();
	  
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			} 
	 
			String query = " insert into Employee (`emp_id`, `emp_name`,`position`,`salary`,`emp_type`,`phone`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, EmpName); 
			preparedStmt.setString(3, Position); 
			preparedStmt.setString(4, Salary); 
			preparedStmt.setString(5, EmpType ); 
			preparedStmt.setString(6, Phone); 
			
	 
	
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the Employee."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 

	
	public String readEmployee() 
	 { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading.";
				} 
	
			output = "<table border='1'> <tr><th>Emp ID</th>"
					+ "<th>Holder Name</th>"
					+ "<th>Card Position</th>"
					+ "<th>Card Salary</th>"
					+ "<th>Type</th>"
					+ "<th>Phone</th></tr>"; 
	 
			String query = "select * from Employee"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
	 
	
			while (rs.next()) 
			{ 
		 
				String emp_id = Integer.toString(rs.getInt("emp_id")); 
				String emp_name = rs.getString("emp_name"); 
				String position = rs.getString("position"); 
				String salary = rs.getString("salary"); 
				String emp_type = rs.getString("emp_type"); 
				String phone = rs.getString("phone"); 
				 
	 
	
				output += "<tr><td>" + emp_id + "</td>";
				output += "<td>" + emp_name + "</td>";
				output += "<td>" + position + "</td>"; 
				output += "<td>" + salary + "</td>"; 
				output += "<td>" + emp_type + "</td>"; 
				output += "<td>" + phone + "</td>"; 
				 
	 
				 // buttons 		
				   output
						  += "<td><input name='btnUpdate' "
						  + " type='button' value='Update' class='btn btn-secondary' </td>"
				 		  + "<td><form method='post' action='Products.jsp'>"
				 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
				 		  + "<input name='Employee' type='hidden' " + " value='" + emp_id + "'>" + "</form></td></tr>";
				 		 
			} 
			
	 con.close(); 
	 
	// Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the Employee."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateEmployee(String EmployeeID, String EmployeeName, String Position, String Salary, String Type, String Phone) { 
		
	 {
		
	 String output = "";
	 
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null) {
			 
			 return "Error while connecting to the database for updating.";
			 }
	 
		 String query = "UPDATE Employee SET emp_name=?, position=?, salary=?, emp_type=?, phone=? WHERE pay_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, EmployeeName); 
		 preparedStmt.setString(2, Position); 
		 preparedStmt.setString(3, Salary); 
		 preparedStmt.setString(4, Type); 
		 preparedStmt.setString(5, Phone); 
		 preparedStmt.setInt(9, Integer.parseInt(EmployeeID));
	
		 preparedStmt.execute();
		 con.close();
		 
		 
		 output = "Updated successfully";
		
	 }
	 catch (Exception e){
		 
		 output = "Error while updating the Employee.";
		 System.err.println(e.getMessage());
	 
	 }
	 return output;
	 } 
	
	
	
	}	
	
	
	
	public String deleteEmpolyee(String emp_id)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	 
			String query = "delete from Employee where emp_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);


			preparedStmt.setInt(1, Integer.parseInt(emp_id));
			
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
	 catch (Exception e) {
		 
		 output = "Error while deleting the Employee.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	 
}
