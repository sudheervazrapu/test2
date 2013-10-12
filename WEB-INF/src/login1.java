import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class login1 extends HttpServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		response.setContentType("text/html");
		log("In doPost of login1");
		PrintWriter out = response.getWriter();
		String n=request.getParameter("username");
		String p=request.getParameter("userpass");
		log("Arguments 1:" + n + " 2:"+ p);
		//wrong logins to be recorderd in ledger
		//try to catch the exceptions
		//generate password
		//salt and hash of the password 
		//md5
		
		if(login2.validate(n, p))
		{
			//RequestDispatcher rd=request.getRequestDispatcher("servlet2");
			//rd.forward(request,response);
			out.println("<a href="+"/deploy/adduser.html"+">ADD USERS</a>");
			out.println("<a href="+"/deploy/updateuser.html"+">UPDATE USERS</a>");
			out.println("<a href="+"/deploy/deleteuser.html"+">DELETE USERS</a>");
		}
		else
		{
			//out.print("sorry username or password error");
			//RequestDispatcher rd=request.getRequestDispatcher("login.html");
			//rd.include(request,response);
		}
		
		//out.close();
	}

}
