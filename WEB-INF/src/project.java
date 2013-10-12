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
import javax.sql.rowset.serial.SerialBlob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;


public class project extends HttpServlet
{
	/*public static void main(String args[])
	{
		
	}*/
	Connection con = null;
	PreparedStatement ps = null;
	public void init()
	{
		try
		{
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","sid");
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","abcd");
			String sql1="insert into project1 values(?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql1);
			
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
		
		try
		{
			String  uname= req.getParameter("name");
			String  udevice_id= req.getParameter("id");
			String  umobile= req.getParameter("mobile");
			String  uaddress= req.getParameter("add");
			// old String uimg= (req.getParameter("img"));
			// old byte byte_string[]=uimg.getBytes(); 
			//byte byte_string[]= new String("Dummy").getBytes(); // to be removed later
			//Blob blob= new SerialBlob(byte_string); 
			String  unotes= req.getParameter("verify");
			String  utime= req.getParameter("update");
			Double  ubal= Double.parseDouble(req.getParameter("bal"));
			
			ps.setString(1,uname);
			ps.setString(2,udevice_id);
			ps.setString(3,umobile);
			ps.setString(4,uaddress);
			ps.setString(5,unotes);
			ps.setString(6,utime);
			ps.setDouble(7,ubal);
			
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