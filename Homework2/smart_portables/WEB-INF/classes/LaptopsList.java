import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LaptopsList")

public class LaptopsList extends HttpServlet {

	/* Trending Page Displays all the Tablets and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Tablets type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Tablet> hm = new HashMap<String, Tablet>();

		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.tablets);
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("apple")) 
			{	
				for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("apple"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Apple";
			} 
			else if (CategoryName.equals("microsoft"))
			{
				for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("microsoft"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Microsoft";
			} 
			else if (CategoryName.equals("dell")) 
			{
				for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dell"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Dell";
			}
	    }

		/* Header, Left Navigation Bar are Printed.

		All the tablets and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		
		pw.print("<div id='body'><div id='content'><h2>");
		pw.print("<a style='font-size: 24px;'>" + name + " Laptop</a>");
		pw.print("</h2><table>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Tablet> entry : hm.entrySet()) {
			Tablet Tablet = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td>");
			pw.print("<h3>" + Tablet.getName() + "</h3>");
			pw.print("<strong>" + Tablet.getPrice() + "$</strong><ul>");
			pw.print("<li style='list-style-type: none;'><img src='images/tablets/" + Tablet.getImage() + "' alt='' style='width: 100px; display: block; margin-left: auto; margin-right: auto' /></li>");
			pw.print("<li style='list-style-type: none;'><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tablets'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li style='list-style-type: none;'><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tablets'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+Tablet.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li style='list-style-type: none;'><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tablets'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div>");
		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Footer.html");
	}
}
