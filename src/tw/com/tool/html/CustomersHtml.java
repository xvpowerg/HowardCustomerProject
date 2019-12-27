package tw.com.tool.html;

import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import tw.com.entity.Customers;
import tw.com.msg.JsonMessage;

public class CustomersHtml {
	private static final String html = "<HTML>\r\n" + "<HEAD>    \r\n"
			+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\r\n"
			+ "<TITLE>Test1</TITLE>\r\n" + "<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=utf-8\">\r\n"
			+ "</HEAD>\r\n" + "<BODY>\r\n" + " <div class=\"container\"> " + "<table class=\"table\">" + " <thead>\r\n"
			+ "    <tr>\r\n" + "      <th scope=\"col\">%s</th>\r\n" + "      <th scope=\"col\">%s</th>\r\n"
			+ "    </tr>\r\n" + "  </thead>" + "<tbody>" + "%s " + "</tbody>" + "</table>" + "</div>" + "</BODY>\r\n"
			+ "</HTML>";

	public static String toHtml(JsonMessage jsonMs) {
		StringBuffer sb = new StringBuffer();
		String head1 = "ID";
		String head2 = "Name";

		if (jsonMs.isPass()) {
			ArrayList<Customers> list = 
					jsonMs.contentToObj(
					new TypeToken<ArrayList<Customers>>(){});
			list.forEach(c->{
				long id = c.getId();
				String name = c.getCustomerName();
				sb.append("<tr>");
				sb.append(String.format("<td>%s</td>", id));
				sb.append(String.format("<td>%s</td>", name));
				sb.append("</tr>");
			});		
		} else {
			head1 = "StateCod";
			head2 = "Message";
			sb.append("<tr>");
			sb.append(String.format("<td>%d</td>", 
					jsonMs.getStatusCode()));
			sb.append(String.format("<td>%s</td>", 
					jsonMs.getMsg()));
			sb.append("</tr>");
		}
		return String.format(html, head1, head2, sb.toString());
	}
}
