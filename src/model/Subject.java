package model;

public class Subject {
	private String subjcode;
	private String subjname;
	private int subjcredit;
	private String subjrequirement;
	
	public String getSubjcode() {
		return subjcode;
	}
	public void setSubjcode(String subjcode) {
		this.subjcode = subjcode;
	}
	public String getSubjname() {
		return subjname;
	}
	public void setSubjname(String subjname) {
		this.subjname = subjname;
	}
	public int getSubjcredit() {
		return subjcredit;
	}
	public void setSubjcredit(int subjcredit) {
		this.subjcredit = subjcredit;
	}
	public String getSubjrequirement() {
		return subjrequirement;
	}
	public void setSubjrequirement(String subjrequirement) {
		this.subjrequirement = subjrequirement;
	}
}