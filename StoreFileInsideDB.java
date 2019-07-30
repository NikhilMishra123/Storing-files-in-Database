import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;

public class StoreFileInsideDB
{
	public static void main(String[] args)
	{
		try
		{
		Connection co = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/jan17", "Nikhil", "1234");
		
		PreparedStatement ps = co.prepareStatement
				("insert into file_keeper values(?,?,?)");
		
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the file path :");
		String filepath = s.nextLine();
		
		int i = filepath.lastIndexOf("/");
		
		String filename = filepath.substring(i + 1); 
		FileInputStream fi = new FileInputStream(filepath);
		int filesize = fi.available();
		ps.setString(1, filename);
		ps.setLong(2, filesize);
		ps.setBinaryStream(3, fi, filesize);
		ps.executeUpdate();
		co.close();
		System.out.println("...done");
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
