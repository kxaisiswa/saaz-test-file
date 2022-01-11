package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import model.Student;

public class StudentDAO {
	static Connection connect = null;
	static PreparedStatement ps = null;
	
	int studid, studphonenum, studsemester, studschooldistance;
	String studname, studaddress, studemail, studpassword, studgender, studbirthcertnum, studnationality, studrace, studreligion, studdocpath, studlivingplace, transportmode, collegecode, collegename, classid;
	Date studbirthdate, enrolldate;
	long studic, parentic;
	
	public String loginstudent(Student studentinfo) {
		String status = null;
		studid = studentinfo.getStudid();
		studpassword = studentinfo.getStudpassword();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STUDENT WHERE STUDID = ?");
			ps.setInt(1, studid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = connect.prepareStatement("SELECT * FROM STUDENT WHERE STUDID = ? AND STUDPASSWORD = ?");
				ps.setInt(1, studid);
				ps.setString(2, studpassword);
				
				ResultSet secrs = ps.executeQuery();
				
				if(secrs.next()) {
					status = "success";
				}
				else {
					status = "Wrong password";
				}
			}
			else {
				status = "ID number not exist";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return status;
	}
	
	public String addstudent(Student newstudent) {
		String addstatus = null;
		
		studid = newstudent.getStudid();
		studic = newstudent.getStudic();
		studname = newstudent.getStudname();
		studsemester = newstudent.getStudsemester();
		studemail = newstudent.getStudemail();
		studpassword = newstudent.getStudpassword();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STUDENT WHERE STUDID = ?");
			ps.setInt(1, studid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				addstatus = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO STUDENT (STUDID, STUDIC, STUDNAME, STUDSEMESTER, STUDEMAIL, STUDPASSWORD) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setInt(1, studid);
				ps.setLong(2, studic);
				ps.setString(3, studname);
				ps.setInt(4, studsemester);
				ps.setString(5, studemail);
				ps.setString(6, studpassword);
				
				ps.execute();
				addstatus = "New student added";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			addstatus = "Add new student failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return addstatus;
	}
	
	public static Student viewstudent(int viewstudentid) {
		String currentlivingplace = null;
		Student studentinfo = new Student();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STUDENT WHERE STUDID = ?");
			ps.setInt(1, viewstudentid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				studentinfo.setStudid(rs.getInt("STUDID"));
				studentinfo.setStudname(rs.getString("STUDNAME"));
				studentinfo.setStudic(rs.getLong("STUDIC"));
				studentinfo.setStudaddress(rs.getString("STUDADDRESS"));
				studentinfo.setStudphonenum(rs.getInt("STUDPHONENUM"));
				studentinfo.setStudemail(rs.getString("STUDEMAIL"));
				studentinfo.setStudpassword(rs.getString("STUDPASSWORD"));
				studentinfo.setStudgender(rs.getString("STUDGENDER"));
				studentinfo.setStudbirthdate(rs.getDate("STUDBIRTHDATE"));
				studentinfo.setStudbirthcertnum(rs.getString("STUDBIRTHCERTNUM"));
				studentinfo.setStudnationality(rs.getString("STUDNATIONALITY"));
				studentinfo.setStudrace(rs.getString("STUDRACE"));
				studentinfo.setStudreligion(rs.getString("STUDRELIGION"));
				studentinfo.setStuddocpath(rs.getString("STUDDOCPATH"));
				studentinfo.setStudsemester(rs.getInt("STUDSEMESTER"));
				
				currentlivingplace = rs.getString("STUDLIVINGPLACE");
				studentinfo.setStudlivingplace(currentlivingplace);
			}
			
			if(currentlivingplace != null && currentlivingplace.equals("Kolej")) {
				ps = connect.prepareStatement("SELECT * FROM STAYATCOLLEGE WHERE STUDID = ?");
				ps.setInt(1, viewstudentid);
				
				ResultSet collegers = ps.executeQuery();
				
				while(collegers.next()) {
					studentinfo.setCollegecode(collegers.getString("COLLEGECODE"));
					studentinfo.setCollegename(collegers.getString("COLLEGENAME"));
				}
			}
			
			else if(currentlivingplace != null && currentlivingplace.equals("Rumah")) {
				ps = connect.prepareStatement("SELECT * FROM STAYATHOME WHERE STUDID = ?");
				ps.setInt(1, viewstudentid);
				
				ResultSet homers = ps.executeQuery();
				
				while(homers.next()) {
					studentinfo.setTransportmode(homers.getString("TRANSPORTMODE"));
					studentinfo.setStudschooldistance(homers.getInt("STUDSCHOOLDISTANCE"));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return studentinfo;
	}
	
	public static Student viewstudentparent(int viewstudentid) {
		Student studentparentinfo = new Student();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STUDENT S JOIN PARENT P ON S.PARENTIC = P.PARENTIC WHERE S.STUDID = ?");
			ps.setInt(1, viewstudentid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				studentparentinfo.setParent(ParentDAO.viewparent(rs.getLong("PARENTIC")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return studentparentinfo;
	}
	
	public static Student viewstudentacademic(int viewstudentid) {
		Student studentacademicinfo = new Student();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STUDENT S WHERE STUDID = ?");
			ps.setInt(1, viewstudentid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("CLASSID") == null) {
					studentacademicinfo.setStudid(rs.getInt("STUDID"));
					studentacademicinfo.setStudname(rs.getString("STUDNAME"));
					studentacademicinfo.setStudic(rs.getLong("STUDIC"));
				}
				else {
					ps = connect.prepareStatement("SELECT * FROM STUDENT S JOIN CLASS C ON S.CLASSID = C.CLASSID JOIN PROGRAM P ON C.PROGCODE = P.PROGCODE WHERE S.STUDID = ?");
					ps.setInt(1, viewstudentid);
					
					ResultSet secrs = ps.executeQuery();
					
					while(secrs.next()) {
						studentacademicinfo.setStudid(secrs.getInt("STUDID"));
						studentacademicinfo.setStudname(secrs.getString("STUDNAME"));
						studentacademicinfo.setStudic(secrs.getLong("STUDIC"));
						studentacademicinfo.setStuclass(ClassDAO.viewclassbyid(secrs.getString("CLASSID")));
						studentacademicinfo.setStuprogram(ProgramDAO.viewprogrambycode(secrs.getString("PROGCODE")));
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return studentacademicinfo;
	}
	
	public static List<Student> viewstudentacademiclist() {
		List<Student> studentacademicinfolist = new ArrayList<Student>();

		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STUDENT");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student studentacademicinfo = new Student();
				
				if(rs.getString("CLASSID") == null) {
					ps = connect.prepareStatement("SELECT * FROM STUDENT WHERE STUDID = ?");
					ps.setInt(1, rs.getInt("STUDID"));
					
					ResultSet secrs = ps.executeQuery();
					while(secrs.next()) {
						studentacademicinfo.setStudid(secrs.getInt("STUDID"));
						studentacademicinfo.setStudname(secrs.getString("STUDNAME"));
						studentacademicinfo.setStudic(secrs.getLong("STUDIC"));
						studentacademicinfo.setStudsemester(secrs.getInt("STUDSEMESTER"));
					}
				}
				else {
					ps = connect.prepareStatement("SELECT * FROM STUDENT S JOIN CLASS C ON S.CLASSID = C.CLASSID JOIN PROGRAM P ON C.PROGCODE = P.PROGCODE WHERE S.STUDID = ?");
					ps.setInt(1, rs.getInt("STUDID"));
					
					ResultSet secrs = ps.executeQuery();
					while(secrs.next()) {
						studentacademicinfo.setStudid(secrs.getInt("STUDID"));
						studentacademicinfo.setStudname(secrs.getString("STUDNAME"));
						studentacademicinfo.setStudic(secrs.getLong("STUDIC"));
						studentacademicinfo.setStudsemester(secrs.getInt("STUDSEMESTER"));
						studentacademicinfo.setStuclass(ClassDAO.viewclassbyid(secrs.getString("CLASSID")));
						studentacademicinfo.setStuprogram(ProgramDAO.viewprogrambycode(secrs.getString("PROGCODE")));
					}
				}
				
				studentacademicinfolist.add(studentacademicinfo);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return studentacademicinfolist;
	}
	
	public static List<Student> viewstudentbyclass() {
		List<Student> stud_class_list = new ArrayList<Student>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT S.CLASSID, COUNT(C.CLASSID) AS TOTALCLASS FROM STUDENT S JOIN CLASS C ON S.CLASSID = C.CLASSID GROUP BY S.CLASSID");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student stud_info = new Student();
				stud_info.setStuclass(ClassDAO.viewclassbyid(rs.getString("CLASSID")));
				stud_info.setTotalbyclass(rs.getInt("TOTALCLASS"));
				
				stud_class_list.add(stud_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return stud_class_list;
	}
	
	public static List<Student> viewstudentsemester() {
		List<Student> student_list = new ArrayList<Student>();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT STUDSEMESTER, COUNT(STUDSEMESTER) AS TOTALSEM FROM STUDENT WHERE STUDSEMESTER IS NOT NULL GROUP BY STUDSEMESTER");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student stud_info = new Student();
				
				stud_info.setStudsemester(rs.getInt("STUDSEMESTER"));
				stud_info.setTotalbysemester(rs.getInt("TOTALSEM"));
				
				student_list.add(stud_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return student_list;
	}
	
	public String updatestudent(Student updtstud) {
		String updtstatus = null;
		
		studid = updtstud.getStudid();
		studname = updtstud.getStudname();
		studic = updtstud.getStudic();
		studaddress = updtstud.getStudaddress();
		studphonenum = updtstud.getStudphonenum();
		studemail = updtstud.getStudemail();
		studgender = updtstud.getStudgender();
		studbirthdate = updtstud.getStudbirthdate();
		studbirthcertnum = updtstud.getStudbirthcertnum();
		studnationality = updtstud.getStudnationality();
		studrace = updtstud.getStudrace();
		studreligion = updtstud.getStudreligion();
		studdocpath = updtstud.getStuddocpath();
		studsemester = updtstud.getStudsemester();
		studlivingplace = updtstud.getStudlivingplace();
		parentic = updtstud.getParentic();

		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE STUDENT SET STUDNAME=?, STUDIC=?, STUDADDRESS=?, STUDPHONENUM=?, STUDEMAIL=?, STUDGENDER=?, STUDBIRTHDATE=?, STUDBIRTHCERTNUM=?, STUDNATIONALITY=?, STUDRACE=?, STUDRELIGION=?, STUDSEMESTER=?, STUDLIVINGPLACE=? WHERE STUDID=?");
			ps.setString(1, studname);
			ps.setLong(2, studic);
			ps.setString(3, studaddress);
			ps.setInt(4, studphonenum);
			ps.setString(5, studemail);
			ps.setString(6, studgender);
			ps.setDate(7, studbirthdate);
			ps.setString(8, studbirthcertnum);
			ps.setString(9, studnationality);
			ps.setString(10, studrace);
			ps.setString(11, studreligion);
			ps.setInt(12, studsemester);
			ps.setString(13, studlivingplace);
			ps.setInt(14, studid);
			
			ps.execute();
			updtstatus = "Student information updated";
			if(studdocpath != null) {
				ps = connect.prepareStatement("UPDATE STUDENT SET STUDDOCPATH=? WHERE STUDID=?");
				ps.setString(1, studdocpath);
				ps.setInt(2, studid);
				
				ps.execute();
				updtstatus = "Student information updated & file uploaded";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			updtstatus = "Update student information failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return updtstatus;
	}
	
	public String updatestudentparent(Student updtstud) {
		String updtstatus = null;
		
		studid = updtstud.getStudid();
		parentic = updtstud.getParentic();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE STUDENT SET PARENTIC = ? WHERE STUDID = ?");
			ps.setLong(1, parentic);
			ps.setInt(2, studid);
			
			ps.execute();
			updtstatus = "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			updtstatus = "fail";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return updtstatus;
	}
	
	public String updatestudentaccount(Student updtstud) {
		String updtstatus = null;
		
		studid = updtstud.getStudid();
		studname = updtstud.getStudname();
		studpassword = updtstud.getStudpassword();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("UPDATE STUDENT SET STUDNAME = ?, STUDPASSWORD = ? WHERE STUDID = ?");
			ps.setString(1, studname);
			ps.setString(2, studpassword);
			ps.setInt(3, studid);
			
			ps.execute();
			updtstatus = "Account information updated";
		}
		catch(Exception e) {
			e.printStackTrace();
			updtstatus = "Update account information failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return updtstatus;
	}
	
	public String updatestudentacademic(Student updtstudent) {
		String updtstatus = null;
		int currentcap = 0;
		int classcap = 0;
		
		studid = updtstudent.getStudid();
		classid = updtstudent.getClassid();
		enrolldate = updtstudent.getEnrolldate();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT C.CLASSID, C.CLASSCAPACITY, (SELECT COUNT(S.CLASSID) FROM STUDENT S WHERE S.CLASSID = C.CLASSID) AS \"COUNT\" FROM CLASS C WHERE C.CLASSID = ?");
			ps.setString(1, classid);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				currentcap = rs.getInt("COUNT");
				classcap = rs.getInt("CLASSCAPACITY");
			}
			
			if(currentcap < classcap) {
				ps = connect.prepareStatement("UPDATE STUDENT SET CLASSID = ?, ENROLLDATE = ? WHERE STUDID = ?");
				ps.setString(1, classid);
				ps.setDate(2, enrolldate);
				ps.setInt(3, studid);
				
				ps.execute();
				updtstatus = "Program & class information updated";
			}
			else {
				updtstatus = "Class chosen already full";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			updtstatus = "Register program & class failed";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return updtstatus;
	}
	
	public void addupdatecollege(Student updtstudcollege) {
		studid = updtstudcollege.getStudid();
		collegecode = updtstudcollege.getCollegecode();
		collegename = updtstudcollege.getCollegename();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STAYATCOLLEGE WHERE STUDID = ?");
			ps.setInt(1, studid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = connect.prepareStatement("UPDATE STAYATCOLLEGE SET COLLEGECODE = ?, COLLEGENAME = ? WHERE STUDID = ?");
				ps.setString(1, collegecode);
				ps.setString(2, collegename);
				ps.setInt(3, studid);
			}
			else {
				ps = connect.prepareStatement("INSERT INTO STAYATCOLLEGE (STUDID, COLLEGECODE, COLLEGENAME) VALUES (?, ?, ?)");
				ps.setInt(1, studid);
				ps.setString(2, collegecode);
				ps.setString(3, collegename);
			}
			
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
	
	public void addupdatehome(Student updtstudhome) {
		studid = updtstudhome.getStudid();
		transportmode = updtstudhome.getTransportmode();
		studschooldistance = updtstudhome.getStudschooldistance();
		
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM STAYATHOME WHERE STUDID = ?");
			ps.setInt(1, studid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = connect.prepareStatement("UPDATE STAYATHOME SET TRANSPORTMODE = ?, STUDSCHOOLDISTANCE = ? WHERE STUDID = ?");
				ps.setString(1, transportmode);
				ps.setInt(2, studschooldistance);
				ps.setInt(3, studid);
			}
			else {
				ps = connect.prepareStatement("INSERT INTO STAYATHOME (STUDID, TRANSPORTMODE, STUDSCHOOLDISTANCE) VALUES (?, ?, ?)");
				ps.setInt(1, studid);
				ps.setString(2, transportmode);
				ps.setInt(3, studschooldistance);
			}
			
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
	
	public void deletecollegebyid(int deletecollegeid) {
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM STAYATCOLLEGE WHERE STUDID = ?");
			ps.setInt(1, deletecollegeid);
			
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
	
	public void deletehomebyid(int deletehomeid) {
		try {
			connect = DatabaseConnection.getConnection();
			ps = connect.prepareStatement("DELETE FROM STAYATHOME WHERE STUDID = ?");
			ps.setInt(1, deletehomeid);
			
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
}
