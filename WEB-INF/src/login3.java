import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class login3 extends HttpServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String n=request.getParameter("username");
		String p=request.getParameter("userpass");
		out.print("Welcome "+n+ "and your password is"+p);
		out.close();
	}
}
