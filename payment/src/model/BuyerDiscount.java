package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuyerDiscount {

	//A common method to connect to the DB
	private Connection connect() 
	 { 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/foundmanagement", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		return con; 
	 } 
	
	
	
	public String insertBuyerDiscounts( String buyerID, String itemID, String itemPrice, String itemQuantity, String totalPrice, String discount, String totalPayment) 
	 { 
		String output = ""; 
		
		try{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting.";
			} 
			
			// create a prepared statement
			String query = "INSERT INTO `discountforbuyers`(`buyerDiscountID`, `buyerID`, `itemID`, `itemPrice`, `itemQuantity`, `totalPrice`, `discount`, `totalPayment`)"
			 		+ " values (?, ?, ?, ?, ?, ?, ?,?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			//String buyerID = Integer.toString(rs.getInt("buyerID")); 
			preparedStmt.setInt(2, Integer.parseInt(buyerID)); 
			preparedStmt.setInt(3, Integer.parseInt(itemID));
			preparedStmt.setInt(4, Integer.parseInt(itemPrice)); 
			preparedStmt.setInt(5, Integer.parseInt(itemQuantity));
			
			preparedStmt.setInt(6, Integer.parseInt(totalPrice));
			preparedStmt.setInt(7, Integer.parseInt(discount));
			preparedStmt.setInt(8, Integer.parseInt(totalPayment));
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		
		catch (Exception e) { 
		 
			output = "Error while inserting the discount."; 
			System.err.println(e.getMessage()); 
		} 
		
		
	 return output; 
	 
	 
	 } 
	
	
	
	
	 public String readBuyerDiscounts() 
	 { 
		
		String output = ""; 
		
		try { 
			
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			} 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Buyer ID</th><th>Item ID</th>" +
					"<th>Item Price</th>" + 
					"<th>Item Quantity</th>" +
					"<th>Total Price</th>" +
					"<th>Discount</th>" +
					"<th>Total Payment</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from discountforbuyers"; 
			
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()){ 
				
				String buyerDiscountID = Integer.toString(rs.getInt("buyerDiscountID")); 
				String buyerID = Integer.toString(rs.getInt("buyerID")); 
				String itemID  = Integer.toString(rs.getInt("itemID")); 
			    String itemPrice = Integer.toString(rs.getInt("itemPrice")); 
			    String itemQuantity  = Integer.toString(rs.getInt("itemQuantity")); 
			    String totalPrice = Integer.toString(rs.getInt("totalPrice")); 
			    String discount = Integer.toString(rs.getInt("discount")); 
			    String totalPayment = Integer.toString(rs.getInt("totalPayment")); 
				
				// Add into the html table
				output += "<tr><td>" + buyerID + "</td>"; 
				output += "<td>" + itemID + "</td>"; 
				output += "<td>" + itemPrice + "</td>"; 
				output += "<td>" + itemQuantity + "</td>"; 
				output += "<td>" + totalPrice + "</td>"; 
				output += "<td>" + discount + "</td>"; 
				output += "<td>" + totalPayment + "</td>"; 
				
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='buyerDiscounts.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='buyerDiscountID' type='hidden' value='" + buyerDiscountID 
						+ "'>" + "</form></td></tr>"; 
			} 
			
			
			
		con.close(); 
		
		
	 // Complete the html table
	 output += "</table>"; 
	 
		} 
		
		
		catch (Exception e) 
		{ 
			output = "Error while reading the discount."; 
			System.err.println(e.getMessage()); 
		} 
		
			return output; 
			
			
	 }
	 
	 
	 
		public String updateBuyerDiscounts(String buyerDiscountID, String buyerID, String itemID, String itemPrice, String itemQuantity, String totalPrice, String discount, String totalPayment)
		{ 
			 String output = ""; 
			 
			 try
			 	{ 
				 	Connection con = connect(); 
				 	
				 	if (con == null){
				 		return "Error while connecting to the database for updating."; 
				 	} 
				 	
				 	
				 	// create a prepared statement
				 	String query = "UPDATE `discountforbuyers` SET `buyerID`=?,'itemID`=?,`itemPrice`=?,`itemQuantity`=?,`totalPrice`=?,`discount`=?,`totalPayment`=? WHERE `buyerDiscountID`=?"; 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
				 	
				 	
				 	// binding values
				 
					preparedStmt.setInt(1, Integer.parseInt(buyerID)); 
					preparedStmt.setInt(2, Integer.parseInt(itemID)); 
					preparedStmt.setInt(3, Integer.parseInt(itemPrice)); 
					preparedStmt.setInt(4,Integer.parseInt(itemQuantity));
					preparedStmt.setInt(5, Integer.parseInt(totalPrice));
					preparedStmt.setInt(6, Integer.parseInt(discount));
					preparedStmt.setInt(7, Integer.parseInt(totalPayment));
				 	
				 	// execute the statement
				 	preparedStmt.execute(); 
				 	con.close(); 
				 	output = "Updated successfully";
				 	
			 } 
			 
			 catch (Exception e) 
			 { 
				 output = "Error while updating the discount."; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
		 } 
		
		
		public String deleteBuyerDiscounts(String buyerDiscountID)  { 
			String output = ""; 
			
			try { 
				Connection con = connect(); 
				
				if (con == null){
					return "Error while connecting to the database for deleting.";
				} 
				
				// create a prepared statement
				String query = "DELETE FROM `discountforbuyers` WHERE `buyerDiscountID`=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(buyerDiscountID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the discount."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }



	
}
