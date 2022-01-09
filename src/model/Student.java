package model;

import java.sql.*;

public class Student {
	private int studid;
	private String studname;
	private long studic;
	private String studaddress;
	private int studphonenum;
	private String studemail;
	private String studpassword;
	private String studgender;
	private Date studbirthdate;
	private String studbirthcertnum;
	private String studnationality;
	private String studrace;
	private String studreligion;
	private long parentic;
	private String studdocpath;
	private int studsemester;
	private String classid;
	private Date enrolldate;
	
	private String studlivingplace;
	private String transportmode;
	private int studschooldistance;
	private String collegecode;
	private String collegename;
	
	private Parent parent;
	private Program stuprogram;
	private Class stuclass;
	private Subject stusubject;
	
	private int totalbyclass;
	private int totalbysemester;
	
	public int getStudid() {
		return studid;
	}
	public void setStudid(int studid) {
		this.studid = studid;
	}
	public String getStudname() {
		return studname;
	}
	public void setStudname(String studname) {
		this.studname = studname;
	}
	public long getStudic() {
		return studic;
	}
	public void setStudic(long studic) {
		this.studic = studic;
	}
	public String getStudaddress() {
		return studaddress;
	}
	public void setStudaddress(String studaddress) {
		this.studaddress = studaddress;
	}
	public int getStudphonenum() {
		return studphonenum;
	}
	public void setStudphonenum(int studphonenum) {
		this.studphonenum = studphonenum;
	}
	public String getStudemail() {
		return studemail;
	}
	public void setStudemail(String studemail) {
		this.studemail = studemail;
	}
	public String getStudpassword() {
		return studpassword;
	}
	public void setStudpassword(String studpassword) {
		this.studpassword = studpassword;
	}
	public String getStudgender() {
		return studgender;
	}
	public void setStudgender(String studgender) {
		this.studgender = studgender;
	}
	public Date getStudbirthdate() {
		return studbirthdate;
	}
	public void setStudbirthdate(Date studbirthdate) {
		this.studbirthdate = studbirthdate;
	}
	public String getStudbirthcertnum() {
		return studbirthcertnum;
	}
	public void setStudbirthcertnum(String studbirthcertnum) {
		this.studbirthcertnum = studbirthcertnum;
	}
	public String getStudnationality() {
		return studnationality;
	}
	public void setStudnationality(String studnationality) {
		this.studnationality = studnationality;
	}
	public String getStudrace() {
		return studrace;
	}
	public void setStudrace(String studrace) {
		this.studrace = studrace;
	}
	public String getStudreligion() {
		return studreligion;
	}
	public void setStudreligion(String studreligion) {
		this.studreligion = studreligion;
	}
	public long getParentic() {
		return parentic;
	}
	public void setParentic(long parentic) {
		this.parentic = parentic;
	}
	public String getStuddocpath() {
		return studdocpath;
	}
	public void setStuddocpath(String studdocpath) {
		this.studdocpath = studdocpath;
	}
	public int getStudsemester() {
		return studsemester;
	}
	public void setStudsemester(int studsemester) {
		this.studsemester = studsemester;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public Date getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}
	
	public String getStudlivingplace() {
		return studlivingplace;
	}
	public void setStudlivingplace(String studlivingplace) {
		this.studlivingplace = studlivingplace;
	}
	public String getTransportmode() {
		return transportmode;
	}
	public void setTransportmode(String transportmode) {
		this.transportmode = transportmode;
	}
	public int getStudschooldistance() {
		return studschooldistance;
	}
	public void setStudschooldistance(int studschooldistance) {
		this.studschooldistance = studschooldistance;
	}
	public String getCollegecode() {
		return collegecode;
	}
	public void setCollegecode(String collegecode) {
		this.collegecode = collegecode;
	}
	public String getCollegename() {
		return collegename;
	}
	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}
	
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Program getStuprogram() {
		return stuprogram;
	}
	public void setStuprogram(Program stuprogram) {
		this.stuprogram = stuprogram;
	}
	public Class getStuclass() {
		return stuclass;
	}
	public void setStuclass(Class stuclass) {
		this.stuclass = stuclass;
	}
	public Subject getStusubject() {
		return stusubject;
	}
	public void setStusubject(Subject stusubject) {
		this.stusubject = stusubject;
	}
	
	public int getTotalbyclass() {
		return totalbyclass;
	}
	public void setTotalbyclass(int totalbyclass) {
		this.totalbyclass = totalbyclass;
	}
	public int getTotalbysemester() {
		return totalbysemester;
	}
	public void setTotalbysemester(int totalbysemester) {
		this.totalbysemester = totalbysemester;
	}
}