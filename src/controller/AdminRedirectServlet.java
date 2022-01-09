package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Student;
import dao.AdminDAO;
import dao.ProgramDAO;
import dao.ClassDAO;
import dao.SubjectDAO;
import dao.ClassSubjectDAO;
import dao.StudentDAO;

@WebServlet("/AdminRedirectServlet")
public class AdminRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO admindao;
	private ProgramDAO progdao;
	private ClassDAO classdao;
	private SubjectDAO subjdao;
	private ClassSubjectDAO classubjdao;
	private StudentDAO studdao;
	HttpSession session;
    
    public AdminRedirectServlet() {
        super();
        admindao = new AdminDAO();
        progdao = new ProgramDAO();
        classdao = new ClassDAO();
        subjdao = new SubjectDAO();
        studdao = new StudentDAO();
        classubjdao = new ClassSubjectDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		RequestDispatcher view;
		String status = null;
		
		session = request.getSession(true);
		String java_session_value = (String)session.getAttribute("session_id");
		
		try {
			switch(action) {
				case "student":
					request.setAttribute("academicinfolist", studdao.viewstudentacademiclist());
					view = request.getRequestDispatcher("Admin_add_student.jsp");
					view.forward(request, response);
					break;
				case "program":
					request.setAttribute("programlist", progdao.viewprogramlist());
					view = request.getRequestDispatcher("Admin_program.jsp");
					view.forward(request, response);
					break;
				case "class":
					request.setAttribute("programlist", progdao.viewprogramlist());
					request.setAttribute("classlist", classdao.viewclasslist());
					view = request.getRequestDispatcher("Admin_class.jsp");
					view.forward(request, response);
					break;
				case "subject":
					request.setAttribute("subjectlist", subjdao.viewsubjectlist());
					view = request.getRequestDispatcher("Admin_subject.jsp");
					view.forward(request, response);
					break;
				case "classsubject":
					request.setAttribute("classlist", classdao.viewclasslist());
					request.setAttribute("subjectlist", subjdao.viewsubjectlist());
					view = request.getRequestDispatcher("Admin_class_subject.jsp");
					view.forward(request, response);
					break;
				case "admin":
					request.setAttribute("adminlist", admindao.viewadminlist());
					view = request.getRequestDispatcher("Admin_add_admin.jsp");
					view.forward(request, response);
					break;
				case "account":
					request.setAttribute("superviselist", admindao.viewadminlist());
					request.setAttribute("admininfo", admindao.viewadmin(Integer.parseInt(java_session_value)));
					view = request.getRequestDispatcher("Admin_update_account.jsp");
					view.forward(request, response);
					break;
				case "statistic":
					request.setAttribute("studentclasslist", studdao.viewstudentbyclass());
					request.setAttribute("programtotal", progdao.viewtotalprogram());
					request.setAttribute("semestertotal", studdao.viewstudentsemester());
					view = request.getRequestDispatcher("Admin_statistic.jsp");
					view.forward(request, response);
					break;
				case "deleteprogram":
					status = progdao.deleteprogram(request.getParameter("deleteid"));
					request.setAttribute("status", status);
					request.setAttribute("programlist", progdao.viewprogramlist());
					view = request.getRequestDispatcher("Admin_program.jsp");
					view.forward(request, response);
					break;
				case "deleteclass":
					classubjdao.deleteclasssubject(request.getParameter("deleteid"));
					status = classdao.deleteclass(request.getParameter("deleteid"));
					request.setAttribute("status", status);
					request.setAttribute("programlist", progdao.viewprogramlist());
					request.setAttribute("classlist", classdao.viewclasslist());
					view = request.getRequestDispatcher("Admin_class.jsp");
					view.forward(request, response);
					break;
				case "deletesubject":
					status = subjdao.deletesubject(request.getParameter("deleteid"));
					request.setAttribute("status", status);
					request.setAttribute("subjectlist", subjdao.viewsubjectlist());
					view = request.getRequestDispatcher("Admin_subject.jsp");
					view.forward(request, response);
					break;
				case "deleteadmin":
					status = admindao.deleteadmin(request.getParameter("deleteid"));
					request.setAttribute("status", status);
					request.setAttribute("adminlist", admindao.viewadminlist());
					view = request.getRequestDispatcher("Admin_add_admin.jsp");
					view.forward(request, response);
					break;
				case "updateclass":
					request.setAttribute("classinfo", classdao.viewclassbyid(request.getParameter("updateid")));
					request.setAttribute("programlist", progdao.viewprogramlist());
					request.setAttribute("classsubjectlist", classubjdao.viewclasssubjectlist(request.getParameter("updateid")));
					request.setAttribute("subjectlist", subjdao.viewsubjectlist());
					view = request.getRequestDispatcher("Admin_update_class.jsp");
					view.forward(request, response);
					break;
				case "updatesubject":
					request.setAttribute("subjectinfo", subjdao.viewsubjectbycode(request.getParameter("updateid")));
					view = request.getRequestDispatcher("Admin_update_subject.jsp");
					view.forward(request, response);
					break;
				case "updatestudent":
					request.setAttribute("studentinfo", studdao.viewstudent(Integer.parseInt(request.getParameter("updateid"))));
					request.setAttribute("studentparentinfo", studdao.viewstudentparent(Integer.parseInt(request.getParameter("updateid"))));
					request.setAttribute("studentaccountinfo", studdao.viewstudent(Integer.parseInt(request.getParameter("updateid"))));
					view = request.getRequestDispatcher("Admin_update_student.jsp");
					view.forward(request, response);
					break;
				case "updateadmin":
					request.setAttribute("admininfo", admindao.viewadmin(Integer.parseInt(request.getParameter("updateid"))));
					view = request.getRequestDispatcher("Admin_update_admin.jsp");
					view.forward(request, response);
					break;
				case "detailstudent":
					request.setAttribute("studentinfo", studdao.viewstudent(Integer.parseInt(request.getParameter("studid"))));
					request.setAttribute("studentparentinfo", studdao.viewstudentparent(Integer.parseInt(request.getParameter("studid"))));
					Student academicinfo = studdao.viewstudentacademic(Integer.parseInt(request.getParameter("studid")));
					request.setAttribute("academicinfo", academicinfo);
					if(academicinfo.getStuclass() != null) {
						request.setAttribute("subjectlist", classubjdao.viewclasssubjectnamelist(academicinfo.getStuclass().getClassid()));
					}
					view = request.getRequestDispatcher("Admin_detail_student.jsp");
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