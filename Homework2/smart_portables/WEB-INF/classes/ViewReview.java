

import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {           
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to view Review");
			response.sendRedirect("Login");
			return;
		}
		 String productName=request.getParameter("name");		 
		HashMap<String, ArrayList<Review>> hm= MongoDBDataStoreUtilities.selectReview();
		String userName = "";
		String reviewRating = "";
		String reviewDate;
		String reviewText = "";	
		String price = "";
		String city ="";
			
                utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
	
                pw.print("<div id='content' style='margin-top:-746px;'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
			
			//if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
		if(hm==null)
		{
		pw.println("<h2>Mongo Db server is not up and running</h2>");
		}
		else
		{
                if(!hm.containsKey(productName)){
				pw.println("<h2>There are no reviews for this product.</h2>");
			}else{
		for (Review r : hm.get(productName)) 
				 {		
		pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Product Name: </td>");
				productName = r.getProductName();
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +productName+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Product Type: </td>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +r.getProductType()+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> userName: </td>");
				userName = r.getUserName();
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +userName+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> userAge: </td>");
				
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> " +r.getuserage()+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> userGender: </td>");
				
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +r.getusergender()+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> userOccupation: </td>");
				
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +r.getuseroccupation()+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> price: </td>");
				price = r.getPrice();
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +price+ "</td>");
				pw.print("</tr>");  
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Product on Sale: </td>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +r.getproductonsale()+ "</td>");
				pw.print("</tr>");

				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Manufracturer Rebate: </td>");  
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +r.getmanufracturerrebate()+ "</td>");   
				pw.print("</tr>");

				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Retailer Name: </td>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +r.getretailername()+ "</td>");
				pw.print("</tr>");

				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Retailer City: </td>");
				city = r.getRetailerCity();
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +city+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Retailer State: </td>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +r.getretailerstate()+ "</td>");
				pw.print("</tr>");
				pw.println("<tr>");
				pw.println("<td style='border:1px solid;padding: 5px;width:250px;'> Review Rating: </td>");
				reviewRating = r.getReviewRating().toString();
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +reviewRating+ "</td>");
				pw.print("</tr>");
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Review Date: </td>");
				reviewDate = r.getReviewDate().toString();
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +reviewDate+ "</td>");
				pw.print("</tr>");			
				pw.print("<tr>");
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'> Review Text: </td>");
				reviewText = r.getReviewText();
				pw.print("<td style='border:1px solid;padding: 5px;width:250px;'>" +reviewText+ "</td>");
				pw.print("</tr>");
				pw.println("</table>");
				}					
							
		}
		}	       
                pw.print("</div></div></div>");		
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
