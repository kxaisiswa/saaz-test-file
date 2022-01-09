package dao;

import java.sql.*;
import java.util.*;

import database.DatabaseConnection;
import model.Class;

public class ClassDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	String classid, classname, progcode;
	int classcapacity;
	
	public String addclass(Class newclass) {
		String addstatus = null;
		
		classid = newclass.getClassid();
		classname = newclass.getClassname();
		classcapacity = newclass.getClasscapacity();
		progcode = newclass.getProgcode();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM CLASS WHERE CLASSID = ?");
			ps.setString(1, classid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				addstatus = "Class id already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO CLASS (CLASSID, CLASSNAME, CLASSCAPACITY, PROGCODE) VALUES(?, ?, ?, ?)");
				ps.setString(1, classid);
				ps.setString(2, classname);
				ps.setInt(3, classcapacity);
				ps.setString(4, progcode);
				
				ps.execute();
				addstatus = "New class added";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			addstatus = "Add new class failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return addstatus;
	}
	
	public static List<Class> viewclasslist() {
		List<Class> class_list = new ArrayList<Class>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM CLASS");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Class class_info = new Class();
				class_info.setClassid(rs.getString("CLASSID"));
				class_info.setClassname(rs.getString("CLASSNAME"));
				class_info.setClasscapacity(rs.getInt("CLASSCAPACITY"));
				class_info.setProgcode(rs.getString("PROGCODE"));
				
				class_list.add(class_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return class_list;
	}
	
	public static List<Class> viewclasstotallist() {
		List<Class> class_total_list = new ArrayList<Class>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT C.CLASSID, C.CLASSCAPACITY, (SELECT COUNT(S.CLASSID) FROM STUDENT S WHERE S.CLASSID = C.CLASSID) AS \"COUNT\" FROM CLASS C");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Class class_info = new Class();
				class_info.setClassid(rs.getString("CLASSID"));
				class_info.setClasscapacity(rs.getInt("CLASSCAPACITY"));
				class_info.setTotalstudent(rs.getInt("COUNT"));
				
				class_total_list.add(class_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return class_total_list;
	}
	
	public static Class viewclassbyid(String viewclassid) {
		Class viewclassinfo = new Class();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM CLASS WHERE CLASSID = ?");
			ps.setString(1, viewclassid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewclassinfo.setClassid(rs.getString("CLASSID"));
				viewclassinfo.setClassname(rs.getString("CLASSNAME"));
				viewclassinfo.setClasscapacity(rs.getInt("CLASSCAPACITY"));
				viewclassinfo.setProgcode(rs.getString("PROGCODE"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return viewclassinfo;
	}
	
	public String updateclass(Class updtclass) {
		String updtstatus = null;
		
		classid = updtclass.getClassid();
		classname = updtclass.getClassname();
		classcapacity = updtclass.getClasscapacity();
		progcode = updtclass.getProgcode();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE CLASS SET CLASSNAME = ?, CLASSCAPACITY = ?, PROGCODE = ? WHERE CLASSID = ?");
			ps.setString(1, classname);
			ps.setInt(2, classcapacity);
			ps.setString(3, progcode);
			ps.setString(4, classid);
			
			ps.execute();
			updtstatus = "Class information updated";
		}
		catch(Exception e) {
			e.printStackTrace();
			updtstatus = "Update class information failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return updtstatus;
	}
	
	public String deleteclass(String deleteclassid) {
		String deletestatus = null;
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM CLASS WHERE CLASSID = ?");
			ps.setString(1, deleteclassid);
			
			ps.execute();
			deletestatus = "Class Deleted";
		}
		catch(Exception e) {
			e.printStackTrace();
			deletestatus = "Delete class failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return deletestatus;
	}
}
