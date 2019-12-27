package tw.com.ws;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

import tw.com.dao.CustomerDAO;
import tw.com.entity.Customers;
import tw.com.jpa.CustomerJapDao;
import tw.com.msg.JsonMessage;


@Path("/read")
@Produces(MediaType.APPLICATION_JSON)
public class MyRestful {
	@Inject
	private EntityManager em;
	private final static  String tmpApiKey = "123456";
	private static boolean checkApiKey(String apiKey) {
		Optional<String> apiKeyOption = 
				Optional.ofNullable(apiKey);
		boolean result = false;
		 if (apiKeyOption.isPresent()) {
			 result = 
		    apiKeyOption.get().equalsIgnoreCase(tmpApiKey);
		 }
		return result;
	}
//	@Inject
//	private EntityManager em;
//	@GET
//	@Path("{customerid:\\d+}")
	@POST
	@Path("/customers")
	public  String testId(@HeaderParam("apikey") String api_key) {
		
	Gson gson = new Gson();	
	System.out.println("apikey:"+api_key);
		if (checkApiKey(api_key)) {
			
		CustomerDAO customerDAO = new CustomerJapDao(em);
		String json = gson.toJson(customerDAO.findAll());
		return  gson.toJson(JsonMessage.getCustomerMessage(json));
		}
		
		return gson.toJson(JsonMessage.getApiKeyErrorMessage());
	
	}
	
}
