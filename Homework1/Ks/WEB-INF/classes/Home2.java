import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home2")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class Home2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		pw.print("<div id='body'><div id='content'><h2>");

		//utility.printHtml("Content.html");
		pw.print("Welcome Sales Manager..!</h2><table>");
		pw.print("<form method='post' action='#'>"
					+"<tr><td><input type='submit' class='btnbuy' value='View Order'></input></td></tr>"
					+"<tr><td><input type='submit' class='btnbuy' value='Add Order'></input></td></tr>"
					+"<tr><td><input type='submit' class='btnbuy' value='Update Order'></input></td></tr>"
					+"<tr><td><input type='submit' class='btnbuy' value='Delete Order'></input></td></tr>");
		pw.print("</table></form></div>");

		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Footer.html");
				
	}

}
