

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitReview")

public class SubmitReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		storeReview(request, response);
	}
	protected void storeReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
                String productname=request.getParameter("productname");		
                String producttype=request.getParameter("producttype");
				String productprice=request.getParameter("productprice");
                String productmaker=request.getParameter("productmaker");
                String reviewrating=request.getParameter("reviewrating");
                String reviewdate=request.getParameter("reviewdate");
                String reviewtext=request.getParameter("reviewtext");
				String retailerpin=request.getParameter("zipcode");
				String retailercity=request.getParameter("retailercity");
				
				String retailername =request.getParameter("retailername");
				String retailerstate =request.getParameter("retailerstate");
				String productonsale =request.getParameter("productonsale");

				String manufracturerdebate =request.getParameter("manufacturerrebate");
				String userage =request.getParameter("userage");  
				String usergender =request.getParameter("usergender");
				String useroccupation =request.getParameter("useroccupation");
				
		String message=utility.storeReview(productname,producttype,productmaker,reviewrating,reviewdate,
											reviewtext,retailerpin,productprice,retailercity,
											retailername,retailerstate,productonsale,manufracturerdebate,userage,usergender,useroccupation);				     
       		//pw.print(productname);
		utility.printHtml("Header.html");
		
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
                pw.print("<div id='body'><div id='content'><h2>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
      		if(message.equals("Successfull"))
      		pw.print("<h2>Review for &nbsp"+productname+" Stored </h2>");
                else
		pw.print("<h2>Mongo Db is not up and running </h2>");
                
		pw.print("</form></div></div>");		
		utility.printHtml("LeftNavigationBar.html");
		utility.printHtml("Footer.html");
	                     	
                    }
              	catch(Exception e)
		{
                 System.out.println(e.getMessage());
		}  			
       
	 	
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
            }
}
