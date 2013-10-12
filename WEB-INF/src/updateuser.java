import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
//import java.sql.*;
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
import javax.sql.rowset.serial.SerialBlob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

public class updateuser extends HttpServlet
{
	Connection con = null;
	PreparedStatement ps = null;
	PreparedStatement ps1 = null;
	PreparedStatement ps2 = null;
	public void init()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","abcd");
			String sql1="update project1 set bal=? where mobile=?";
			String sql2="insert into updateuser values(?,?,?,?)";
			String sql3="select bal from project1 where mobile=?";
			ps=con.prepareStatement(sql2);
			ps1=con.prepareStatement(sql1);
			ps2=con.prepareStatement(sql3);
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
			String  umob= req.getParameter("phno");
			String  unotes= req.getParameter("cust_notes");
			Double  ubal= Double.parseDouble(req.getParameter("credit"));
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setTimeZone(TimeZone.getDefault());
			String  utime=df.format(date); 
			
			ps.setString(1,umob);
			ps.setString(2,unotes);
			ps.setDouble(3,ubal);
			ps.setString(4,utime);
						
			ps.executeUpdate();
			
			//get balance
			ps2.setString(1,umob);
			ResultSet rs=ps2.executeQuery();
			double currentbal=0;
			while (rs.next()) 
			{  
				currentbal=rs.getDouble(1); 
			}
			
			double newbal=currentbal+ubal;
			
			//update user table
			ps1.setDouble(1,newbal);
			ps1.setString(2,umob);
			ps1.executeUpdate();
			
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