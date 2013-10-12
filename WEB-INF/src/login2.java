import java.sql.*;

public class login2 
{

	public static boolean validate(String name,String pass)
	{
		Connection con = null;
		PreparedStatement ps = null;
		boolean status=false;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","abcd");
				
			ps=con.prepareStatement("select * from login where username=? and pwd=?");
			ps.setString(1,name);
			ps.setString(2,pass);
	
			ResultSet rs=ps.executeQuery();
			status=rs.next();
	
	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
	}
}
