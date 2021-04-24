package com;

import model.BuyerDiscount;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/BuyerDiscounts")
public class BuyerDiscountService {
	
	BuyerDiscount buyerDiscountObj = new BuyerDiscount(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readBuyerDiscounts() 
	{ 
		return buyerDiscountObj.readBuyerDiscounts(); 
	 }
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	 
	public String insertBuyerDiscounts( @FormParam("buyerID") String buyerID, @FormParam("itemID") String itemID, @FormParam("itemPrice") String itemPrice,  @FormParam("itemQuantity") String itemQuantity, @FormParam("totalPrice") String totalPrice, @FormParam("discount") String discount, @FormParam("totalPayment") String totalPayment)
	{ 
				String output = buyerDiscountObj.insertBuyerDiscounts( buyerID, itemID, itemPrice, itemQuantity, totalPrice, discount, totalPayment  );
				return output;
			}
}
