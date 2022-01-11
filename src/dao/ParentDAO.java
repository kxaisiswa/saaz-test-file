package dao;

import java.sql.*;

import database.DatabaseConnection;
import model.Parent;

public class ParentDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	long parentic;
	String parentname, parentnationality, parentrace, parentreligion, parentjob, parenttaxnumber, parentrelation;
	Double parentincome;
	int parentphonenum, parentnumdependent;
	
	public String addparent(Parent newparent) {
		String addstatus = null;
		
		parentic = newparent.getParentic();
		parentname = newparent.getParentname();
		parentnationality = newparent.getParentnationality();
		parentrace = newparent.getParentrace();
		parentreligion = newparent.getParentreligion();
		parentphonenum = newparent.getParentphonenum();
		parentnumdependent = newparent.getParentnumdependent();
		parentjob = newparent.getParentjob();
		parenttaxnumber = newparent.getParenttaxnumber();
		parentincome = newparent.getParentincome();
		parentrelation = newparent.getParentrelation();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM PARENT WHERE PARENTIC = ?");
			ps.setLong(1, parentic);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = connect.prepareStatement("UPDATE PARENT SET PARENTNAME=?, PARENTNATIONALITY=?, PARENTRACE=?, PARENTRELIGION=?, PARENTPHONENUM=?, PARENTNUMDEPENDENT=?, PARENTJOB=?, PARENTTAXNUMBER=?, PARENTINCOME=?, PARENTRELATION=? WHERE PARENTIC=?");
				ps.setString(1, parentname);
				ps.setString(2, parentnationality);
				ps.setString(3, parentrace);
				ps.setString(4, parentreligion);
				ps.setInt(5, parentphonenum);
				ps.setInt(6, parentnumdependent);
				ps.setString(7, parentjob);
				ps.setString(8, parenttaxnumber);
				ps.setDouble(9, parentincome);
				ps.setString(10, parentrelation);
				ps.setLong(11, parentic);
			}
			else {
				ps = connect.prepareStatement("INSERT INTO PARENT (PARENTIC, PARENTNAME, PARENTNATIONALITY, PARENTRACE, PARENTRELIGION, PARENTPHONENUM, PARENTNUMDEPENDENT, PARENTJOB, PARENTTAXNUMBER, PARENTINCOME, PARENTRELATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1, parentic);
				ps.setString(2, parentname);
				ps.setString(3, parentnationality);
				ps.setString(4, parentrace);
				ps.setString(5, parentreligion);
				ps.setInt(6, parentphonenum);
				ps.setInt(7, parentnumdependent);
				ps.setString(8, parentjob);
				ps.setString(9, parenttaxnumber);
				ps.setDouble(10, parentincome);
				ps.setString(11, parentrelation);
			}
			
			ps.execute();
			addstatus = "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			addstatus = "fail";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return addstatus;
	}
	
	public static Parent viewparent(long viewparentic) {
		Parent parentinfo = new Parent();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM PARENT WHERE PARENTIC = ?");
			ps.setLong(1, viewparentic);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				parentinfo.setParentic(rs.getLong("PARENTIC"));
				parentinfo.setParentname(rs.getString("PARENTNAME"));
				parentinfo.setParentnationality(rs.getString("PARENTNATIONALITY"));
				parentinfo.setParentrace(rs.getString("PARENTRACE"));
				parentinfo.setParentreligion(rs.getString("PARENTRELIGION"));
				parentinfo.setParentphonenum(rs.getInt("PARENTPHONENUM"));
				parentinfo.setParentnumdependent(rs.getInt("PARENTNUMDEPENDENT"));
				parentinfo.setParentjob(rs.getString("PARENTJOB"));
				parentinfo.setParenttaxnumber(rs.getString("PARENTTAXNUMBER"));
				parentinfo.setParentincome(rs.getDouble("PARENTINCOME"));
				parentinfo.setParentrelation(rs.getString("PARENTRELATION"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return parentinfo;
	}
}
