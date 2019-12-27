package tw.com.web;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import tw.com.msg.JsonMessage;
import tw.com.tool.html.CustomersHtml;
import tw.com.ws.client.CustomersClient;



@WebServlet(name = "FirstServlet", urlPatterns = "/", asyncSupported = true)
public class FirstServlet extends HttpServlet {
	@Inject
	private Logger logger;
	public final String apikey = "123456";
	public FirstServlet() {
		super();
	}

	private class MyRun implements Runnable {
		AsyncContext ctx;
		public MyRun(AsyncContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			CustomersClient.setAPIKEY(apikey);
			JsonMessage jsonMsg = 
					CustomersClient.findAllCustomers();
			String html = CustomersHtml.toHtml(jsonMsg);
			try {
				ctx.getResponse().getWriter().append(html);
				ctx.complete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Excepion",e);
			}

		}

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF8");
		AsyncContext ctx = request.startAsync();
		new Thread(new MyRun(ctx)).start();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
