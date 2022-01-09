package model;

public class ClassSubject {
	private String classid;
	private String subjcode;
	private Subject subject;
	
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getSubjcode() {
		return subjcode;
	}
	public void setSubjcode(String subjcode) {
		this.subjcode = subjcode;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}