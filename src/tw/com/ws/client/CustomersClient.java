package tw.com.ws.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.google.gson.Gson;

import tw.com.msg.JsonMessage;

public class CustomersClient {
	private  static  String apikey = "123456";
	public static void setAPIKEY( String apikey) {
		CustomersClient.apikey = apikey;
	}
	public static  JsonMessage findAllCustomers() {
		UriBuilder uri = UriBuilder.fromUri("http://localhost:8080/HowardCustomerProject/read/customers");
		 Client client = ClientBuilder.newClient();
		Response rsResponse = client.target(uri).request(MediaType.APPLICATION_JSON_TYPE).header("apikey", apikey)
				.post(Entity.json(""));
		String json = rsResponse.readEntity(String.class);
		Gson gson = new Gson();
		JsonMessage msg = gson.fromJson(json, 
				JsonMessage.class);
		return msg;
	}

	
}
