import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement; 
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.sql.rowset.serial.SerialBlob;


public class deleteuser extends HttpServlet
{
	
	Connection con = null;
	PreparedStatement ps = null;
	public void init()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","abcd");
			String sql1="delete from project1 where mobile=?";
			ps=con.prepareStatement(sql1);
			//System.out.println("Loaded in init Aug 29th 6 pm");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage().toString());//do nothing
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		//PrintWriter pw = res.getWriter();
		//res.setContentType("text/html");
		//System.out.println("doPost invoked for version Aug 29th 6 pm");	
		try
		{
			String  uphno= req.getParameter("phno");
						
			ps.setString(1,uphno);
			ps.executeUpdate();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage().toString());//do nothing
		}
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		//System.out.println("doGet invoked for version Aug 29th 6 pm");	
		doPost(req,res);
	}
	public void destroy()
	{
		//System.out.println("destroy for version Aug 29th 6 pm");	
		try
		{
			if(ps!=null)
			ps.close();//avoid NullPointer Exception
		}
		catch(Exception e)
		{
			//do nothing;
		}
		try
		{
			if(con!=null)
			con.close();
		}
		catch(Exception e)
		{
			//do nothing;
		}
	}
}