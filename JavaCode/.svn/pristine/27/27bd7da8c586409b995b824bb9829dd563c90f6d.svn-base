import java.io.Console;
import java.sql.SQLException;

import org.hisand.bible.ExportorManager;
import org.hisand.book.dao.TestDAO;


public class Main {
	public static void main(String[] args) {
		try {
			 Console console = System.console();
			 String command = new String(console.readLine("Enter command:"));
			 if ("create".equals(command)) {
				 System.out.println(command);
			 }
			 else {
				 String p1 = new String(console.readLine("Enter p1:"));
				 String p2 = new String(console.readLine("Enter p2:"));
				 System.out.println(p1 + p2);
			 }
			 //create1();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void create0() {
		try {
			ExportorManager mgr = new ExportorManager();
			mgr.create();
			mgr.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void create1() {
		try {
			TestDAO dao = new TestDAO();
			dao.insertResouce();
			System.out.println("done");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
