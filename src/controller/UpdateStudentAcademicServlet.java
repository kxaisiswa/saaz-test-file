package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import dao.StudentDAO;
import dao.ProgramDAO;
import dao.ClassDAO;
import dao.ClassSubjectDAO;

@WebServlet("/UpdateStudentAcademicServlet")
public class UpdateStudentAcademicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
	private ProgramDAO progdao;
	private ClassDAO classdao;
	private ClassSubjectDAO classubjdao;
	HttpSession session;
	
    public UpdateStudentAcademicServlet() {
        super();
        studdao = new StudentDAO();
        progdao = new ProgramDAO();
        classdao = new ClassDAO();
        classubjdao = new ClassSubjectDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student updtstudent = new Student();
		
		session = request.getSession(true);
		String java_session_value = (String)session.getAttribute("session_id");
		updtstudent.setStudid(Integer.parseInt(java_session_value));
		
		updtstudent.setClassid(request.getParameter("classid"));
		
		Date current_date = new Date();
	    java.sql.Date sqlDate = new java.sql.Date(current_date.getTime());
		updtstudent.setEnrolldate(sqlDate);
		
		String status = studdao.updatestudentacademic(updtstudent);
		request.setAttribute("status", status);
		
		Student academicinfo = studdao.viewstudentacademic(Integer.parseInt(java_session_value));
		request.setAttribute("academicinfo", academicinfo);
		if(academicinfo.getStuclass() != null) {
			request.setAttribute("subjectlist", classubjdao.viewclasssubjectnamelist(academicinfo.getStuclass().getClassid()));
		}
		request.setAttribute("programlist", progdao.viewprogramlist());
		request.setAttribute("classlist", classdao.viewclasslist());
		RequestDispatcher view = request.getRequestDispatcher("Student_academic.jsp");
		view.forward(request, response);
	}
}