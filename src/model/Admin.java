package model;

public class Admin {
	private int adminid;
	private String adminpassword;
	private String adminname;
	private int adminphonenum;
	private String adminemail;
	private int superviseid;
	private String adminlevel;
	
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public int getAdminphonenum() {
		return adminphonenum;
	}
	public void setAdminphonenum(int adminphonenum) {
		this.adminphonenum = adminphonenum;
	}
	public String getAdminemail() {
		return adminemail;
	}
	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}
	public int getSuperviseid() {
		return superviseid;
	}
	public void setSuperviseid(int superviseid) {
		this.superviseid = superviseid;
	}
	public String getAdminlevel() {
		return adminlevel;
	}
	public void setAdminlevel(String adminlevel) {
		this.adminlevel = adminlevel;
	}
}