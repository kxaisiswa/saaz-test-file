package dao;

import java.sql.*;
import java.util.*;

import database.DatabaseConnection;
import model.Program;

public class ProgramDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;

	String progcode, progname;
	
	public String addprogram(Program newprog) {
		String addstatus = null;
		
		progcode = newprog.getProgcode();
		progname = newprog.getProgname();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM PROGRAM WHERE PROGCODE = ?");
			ps.setString(1, progcode);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				addstatus = "Program code already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO PROGRAM (PROGCODE, PROGNAME) VALUES(?, ?)");
				ps.setString(1, progcode);
				ps.setString(2, progname);
				
				ps.execute();
				addstatus = "New program added";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			addstatus = "Add new program failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return addstatus;
	}
	
	public static List<Program> viewprogramlist() {
		List<Program> program_list = new ArrayList<Program>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM PROGRAM");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Program program_info = new Program();
				program_info.setProgcode(rs.getString("PROGCODE"));
				program_info.setProgname(rs.getString("PROGNAME"));
				
				program_list.add(program_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return program_list;
	}
	
	public static Program viewprogrambycode(String viewprogcode) {
		Program viewprograminfo = new Program();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM PROGRAM WHERE PROGCODE = ?");
			ps.setString(1, viewprogcode);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewprograminfo.setProgcode(rs.getString("PROGCODE"));
				viewprograminfo.setProgname(rs.getString("PROGNAME"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return viewprograminfo;
	}
	
	public static List<Program> viewtotalprogram() {
		List<Program> program_list = new ArrayList<Program>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT P.PROGNAME, COUNT(P.PROGNAME) AS TOTALPROG FROM STUDENT S JOIN CLASS C ON S.CLASSID = C.CLASSID JOIN PROGRAM P ON C.PROGCODE = P.PROGCODE GROUP BY P.PROGNAME");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Program program_info = new Program();
				
				program_info.setProgname(rs.getString("PROGNAME"));
				program_info.setTotalbyprogram(rs.getInt("TOTALPROG"));
				
				program_list.add(program_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return program_list;
	}
	
	public void updateprogram(Program updtprog) {
		progcode = updtprog.getProgcode();
		progname = updtprog.getProgname();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE PROGRAM SET PROGNAME = ? WHERE PROGCODE = ?");
			ps.setString(1, progname);
			ps.setString(2, progcode);
			
			ps.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
	}
	
	public String deleteprogram(String deleteprogcode) {
		String deletestatus = null;
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM PROGRAM WHERE PROGCODE = ?");
			ps.setString(1, deleteprogcode);
			
			ps.execute();
			deletestatus = "Program Deleted";
		}
		catch(Exception e) {
			e.printStackTrace();
			deletestatus = "Delete program failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return deletestatus;
	}
}
