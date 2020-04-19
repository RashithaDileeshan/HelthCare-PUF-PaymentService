package resources;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;
import service.PaymentService;



@Path("/Payments")
public class PaymentResources {
	
	Payment payment = new Payment();

	PaymentService payService = new PaymentService();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems(){
			return payService.readPayments();
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayments(@FormParam("patientName") String patientName, @FormParam("amount") Long amount,
				@FormParam("paydate") Date paydate, @FormParam("address") String address,@FormParam("contactNo") String contactNo,@FormParam("email")String email) {
			String output = payService.insertPayments(patientName, amount, paydate, address,contactNo,email);
			return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayments(String paymentData) {
			// Convert the input string to a JSON object
			JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
			// Read the values from the JSON object
			String paymentId = paymentObject.get("paymentId").getAsString();
			String patientName = paymentObject.get("patientName").getAsString();
			Long amount = paymentObject.get("amount").getAsLong();
			String paydate = paymentObject.get("paydate").getAsString();
			String address = paymentObject.get("address").getAsString();
			String contactNo = paymentObject.get("contactNo").getAsString();
			String email = paymentObject.get("email").getAsString();
			String output = payService.updatePayment(paymentId, patientName, amount, paydate, address, contactNo, email);
			return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentData) {
			// Convert the input string to an XML document
			Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

			String paymentId = doc.select("paymentId").text();
			String output = payService.deletepayment(paymentId);
			return output;
		}


	
			@GET
			@Path("/{paymentId}")
			@Produces(MediaType.APPLICATION_JSON)
			public Payment getPaymentDetails(@PathParam("paymentId") String id) {
				Payment payment = payService.getPaymentDetails(id);
				
				return payment;
			}
}
