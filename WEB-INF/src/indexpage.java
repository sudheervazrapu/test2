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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class indexpage extends HttpServlet
{
	Connection con = null;
	PreparedStatement ps=null;
	public void init()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","abcd");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage().toString());//do nothing
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		try
		{
			String act = req.getParameter("act");
			if (act.equals("add")) 
			{
				RequestDispatcher rd=req.getRequestDispatcher("add_user");
				rd.forward(req,res);
			} 
			else if (act.equals("update")) 
			{
				RequestDispatcher rd=req.getRequestDispatcher("update_user");
				rd.forward(req,res);
			} 
			else if (act.equals("delete")) 
			{
				RequestDispatcher rd=req.getRequestDispatcher("delete_user");
				rd.forward(req,res);
			} 
			else if (act == null) 
			{
				//no button has been selected
			} 
			else 
			{
				//someone has altered the HTML and sent a different value!
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage().toString());//do nothing
		}
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		doPost(req,res);
	}
	public void destroy()
	{
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