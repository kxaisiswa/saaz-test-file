package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Student;
import dao.StudentDAO;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO  studdao;
    
    public AddStudentServlet() {
        super();
        studdao = new StudentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student newstudent = new Student();
		
		newstudent.setStudid(Integer.parseInt(request.getParameter("studid")));
		newstudent.setStudic(Long.parseLong(request.getParameter("studic")));
		newstudent.setStudname(request.getParameter("studname"));
		newstudent.setStudsemester(Integer.parseInt(request.getParameter("studsemester")));
		newstudent.setStudemail(request.getParameter("studemail"));
		newstudent.setStudpassword(request.getParameter("studpassword"));
		
		String status = studdao.addstudent(newstudent);
		request.setAttribute("status", status);
		request.setAttribute("academicinfolist", studdao.viewstudentacademiclist());
		RequestDispatcher view = request.getRequestDispatcher("Admin_add_student.jsp");
		view.forward(request, response);
	}
}