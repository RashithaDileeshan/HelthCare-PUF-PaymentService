package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DBConnection;
import model.Payment;

public class PaymentService {
	
	public String insertPayments(String patientName, Long amount,Date paydate, String address,String contactNo,String email) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payservice (`paymentId`,`patientName`,`amount`,`paydate`,`address`,contactNo,email)"
					+ " values (?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, patientName);
			preparedStmt.setLong(3, amount);
			preparedStmt.setDate(4, paydate);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, contactNo);
			preparedStmt.setString(7, email);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the payment Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayments() {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Patient Name</th><th>Amount</th><th>Pay"
					+ "Date</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payservice";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentId = Integer.toString(rs.getInt("paymentId"));
				String patientName = rs.getString("patientName");
				String amount = Long.toString(rs.getLong("amount"));
				String paydate = rs.getString("paydate");
				String address = rs.getString("address");
				String contactNo = rs.getString("contactNo");
				String email = rs.getString("email");
				
				// Add into the html table
				output += "<tr><td>" +paymentId+ "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + paydate + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + email + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\""
						+ " value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"Payment.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"" + " class=\"btn btn-danger\">"
						+ "<input name=\"paymentId\" type=\"hidden\" value=\"" + paymentId + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment details";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updatePayment(String paymentId,String patientName, Long amount,String paydate, String address,String contactNo,String email) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payservice SET patientName=?,amount=?,paydate=?,address=?,contactNo=?,email=? " 
			+ "WHERE paymentId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, patientName);
			preparedStmt.setLong(2, amount);
			preparedStmt.setString(3, paydate);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, contactNo);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7,paymentId );
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payments details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletepayment(String paymentId) {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from payservice where paymentId = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
//	Display single payment detail
	public static Payment getPaymentDetails(String id) {
		Payment payment = null;
		try {

			Connection con = DBConnection.connect();

			String getSql = "SELECT * FROM payservice WHERE paymentId = ? ";

			PreparedStatement ps_getPaymentDetails = con.prepareStatement(getSql);
			ps_getPaymentDetails.setInt(1, Integer.parseInt(id));

			ResultSet rs = ps_getPaymentDetails.executeQuery();

			while (rs.next()) {

				
				payment = new Payment(Integer.parseInt(id), rs.getString(2), rs.getLong(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payment;
	}
	
	
	
	
}
