package tw.com.msg;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonMessage {
	private int statusCode;
	private String msg ;
	private String content;
	private JsonMessage(int statusCode, String msg,String content) {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
		this.content = content;
	}
	private JsonMessage(int statusCode, String msg) {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
	}
	
	public static JsonMessage getErrorMessage(int errorCode,String msg) {
		  return new JsonMessage(errorCode,msg);
	}
	public static JsonMessage getApiKeyErrorMessage() {
		return new JsonMessage(Response.Status.BAD_REQUEST.getStatusCode(),
				"API Key is a incorrect :apikey default:123456");
	}
	public static JsonMessage getCustomerMessage(String json) {
		return new JsonMessage(Response.Status.OK.getStatusCode(),
				"Customer json",json);
	}
	
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public<R> R contentToObj(TypeToken<R> typeToken) {
		Gson gson =  new Gson();
		java.lang.reflect.Type founderListType = 
				typeToken.
				getType();
	  R obj = gson.fromJson(this.getContent(),
				      founderListType);
		return obj;
	}
	
	public boolean isPass() {
		return this.statusCode == 
				Response.Status.OK.getStatusCode();
	}
	
}
