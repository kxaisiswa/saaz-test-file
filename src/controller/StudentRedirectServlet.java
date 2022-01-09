package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Student;
import dao.StudentDAO;
import dao.ProgramDAO;
import dao.ClassDAO;
import dao.ClassSubjectDAO;

@WebServlet("/StudentRedirectServlet")
public class StudentRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
	private ProgramDAO progdao;
	private ClassDAO classdao;
	private ClassSubjectDAO classubjdao;
    HttpSession session;
    
    public StudentRedirectServlet() {
        super();
        studdao = new StudentDAO();
        progdao = new ProgramDAO();
        classdao = new ClassDAO();
        classubjdao = new ClassSubjectDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		RequestDispatcher view;
		
		session = request.getSession(true);
		String java_session_value = (String)session.getAttribute("session_id");
		
		try {
			switch(action) {
				case "info":
					request.setAttribute("studentinfo", studdao.viewstudent(Integer.parseInt(java_session_value)));
					view = request.getRequestDispatcher("Student_update_info.jsp");
					view.forward(request, response);
					break;
				case "parent":
					request.setAttribute("studentinfo", studdao.viewstudentparent(Integer.parseInt(java_session_value)));
					view = request.getRequestDispatcher("Student_update_parent.jsp");
					view.forward(request, response);
					break;
				case "academic":
					Student academicinfo = studdao.viewstudentacademic(Integer.parseInt(java_session_value));
					request.setAttribute("academicinfo", academicinfo);
					if(academicinfo.getStuclass() != null) {
						request.setAttribute("subjectlist", classubjdao.viewclasssubjectnamelist(academicinfo.getStuclass().getClassid()));
					}
					request.setAttribute("programlist", progdao.viewprogramlist());
					request.setAttribute("classlist", classdao.viewclasslist());
					view = request.getRequestDispatcher("Student_academic.jsp");
					view.forward(request, response);
					break;
				case "academicinfo":
					request.setAttribute("programlist", progdao.viewprogramlist());
					request.setAttribute("classlist", classdao.viewclasslist());
					request.setAttribute("classsubjectlist", classubjdao.viewclasssubjectlist());
					request.setAttribute("classtotallist", classdao.viewclasstotallist());
					view = request.getRequestDispatcher("Student_academic_info.jsp");
					view.forward(request, response);
					break;
				case "account":
					request.setAttribute("studentinfo", studdao.viewstudent(Integer.parseInt(java_session_value)));
					view = request.getRequestDispatcher("Student_update_account.jsp");
					view.forward(request, response);
					break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}