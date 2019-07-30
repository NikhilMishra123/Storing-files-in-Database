import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class FetchFileFromDB
{
	public static void main(String[] args)
	{
		try
		{
		Connection co = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/jan17", "Nikhil", "1234");
		
		PreparedStatement ps = co.prepareStatement
				("select filedata from file_keeper where filename = ?");
		
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the file name to be fetched from the DB :");
		String filename = s.nextLine();
		
		
	
		ps.setString(1, filename);
		
		
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.next())
		{
			
			InputStream is = rs.getBinaryStream("filedata");
			
			
			FileOutputStream fo = new FileOutputStream
					("d:/db-backup/"+filename);
			
			while(true)
			{
				
				int b = is.read();
				if(b == -1)
				{
					break;
				}
				fo.write(b);
			}
			fo.close();
		}
		else
		{
			System.out.println("file not found inside DB my dear");
		}
		co.close();

			
		}
		 catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
