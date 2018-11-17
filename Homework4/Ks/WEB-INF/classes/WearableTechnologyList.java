import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WearableTechnologyList")

public class WearableTechnologyList extends HttpServlet {

	/* Console Page Displays all the Consoles and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Console> hm = new HashMap<String, Console>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.consoles);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("fitnesswatches"))
		   {
			 for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("fitnesswatches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "fitnesswatches";
		   }
		   else if(CategoryName.equals("smartwatches"))
		    {
			for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("smartwatches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "smartwatches";
			}
			else if(CategoryName.equals("headphones"))
			{
				for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("headphones"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "headphones";
			}
			else if(CategoryName.equals("virtualreality"))
			{
				for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("virtualreality"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "virtualreality";
			}
			else if(CategoryName.equals("pettracker"))
			{
				for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("pettracker"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "pettracker";
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the Console and Console information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		
		pw.print("<div id='body'><div id='content'><h2>");
		pw.print("<a style='font-size: 24px;'>"+name+" Consoles</a>");
		pw.print("</h2><table>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Console> entry : hm.entrySet())
		{
			Console console = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td>");
			pw.print("<h3>"+console.getName()+"</h3>");
			pw.print("<strong>$"+console.getPrice()+"</strong><ul>");
			pw.print("<li style='list-style-type: none;''><img src='images/consoles/"+console.getImage()+"' alt='' style='width: 100px; display: block; margin-left: auto; margin-right: auto' /></li>");
			
			pw.print("<li style='list-style-type: none;''><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<listyle='list-style-type: none;''><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='hidden' name='price' value='"+console.getPrice()+"'>"+ 
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li style='list-style-type: none;'><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div>");
   		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Footer.html");
		
	}
}
