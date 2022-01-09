package dao;

import java.sql.*;
import java.util.*;

import database.DatabaseConnection;
import model.Subject;

public class SubjectDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	String subjcode, subjname, subjrequirement;
	int subjcredit;
	
	public String addsubject(Subject newsubj) {
		String addstatus = null;
		
		subjcode = newsubj.getSubjcode();
		subjname = newsubj.getSubjname();
		subjcredit = newsubj.getSubjcredit();
		subjrequirement = newsubj.getSubjrequirement();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM SUBJECT WHERE SUBJCODE = ?");
			ps.setString(1, subjcode);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				addstatus = "Subject code already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO SUBJECT (SUBJCODE, SUBJNAME, SUBJCREDIT, SUBJREQUIREMENT) VALUES(?, ?, ?, ?)");
				ps.setString(1, subjcode);
				ps.setString(2, subjname);
				ps.setInt(3, subjcredit);
				ps.setString(4, subjrequirement);
				
				ps.execute();
				addstatus = "New subject added";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			addstatus = "Add new subject failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return addstatus;
	}
	
	public static List<Subject> viewsubjectlist() {
		List<Subject> subject_list = new ArrayList<Subject>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM SUBJECT");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Subject subject_info = new Subject();
				subject_info.setSubjcode(rs.getString("SUBJCODE"));
				subject_info.setSubjname(rs.getString("SUBJNAME"));
				subject_info.setSubjcredit(rs.getInt("SUBJCREDIT"));
				subject_info.setSubjrequirement(rs.getString("SUBJREQUIREMENT"));
				
				subject_list.add(subject_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return subject_list;
	}
	
	public static Subject viewsubjectbycode(String viewsubjcode) {
		Subject viewsubjectinfo = new Subject();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM SUBJECT WHERE SUBJCODE = ?");
			ps.setString(1, viewsubjcode);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewsubjectinfo.setSubjcode(rs.getString("SUBJCODE"));
				viewsubjectinfo.setSubjname(rs.getString("SUBJNAME"));
				viewsubjectinfo.setSubjcredit(rs.getInt("SUBJCREDIT"));
				viewsubjectinfo.setSubjrequirement(rs.getString("SUBJREQUIREMENT"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return viewsubjectinfo;
	}
	
	public String updatesubject(Subject updtsubj) {
		String updtstatus = null;
		
		subjcode = updtsubj.getSubjcode();
		subjname = updtsubj.getSubjname();
		subjcredit = updtsubj.getSubjcredit();
		subjrequirement = updtsubj.getSubjrequirement();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE SUBJECT SET SUBJNAME = ?, SUBJCREDIT = ?, SUBJREQUIREMENT = ? WHERE SUBJCODE = ?");
			ps.setString(1, subjname);
			ps.setInt(2, subjcredit);
			ps.setString(3, subjrequirement);
			ps.setString(4, subjcode);
			
			ps.execute();
			updtstatus = "Subject information updated";
		}
		catch(Exception e) {
			e.printStackTrace();
			updtstatus = "Update subject information failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return updtstatus;
	}
	
	public String deletesubject(String deletesubjcode) {
		String deletestatus = null;
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM SUBJECT WHERE SUBJCODE = ?");
			ps.setString(1, deletesubjcode);
			
			ps.execute();
			deletestatus = "Subject Deleted";
		}
		catch(Exception e) {
			e.printStackTrace();
			deletestatus = "Delete subject failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return deletestatus;
	}
}