package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import dao.StudentDAO;

@WebServlet("/UpdateStudentAccountServlet")
public class UpdateStudentAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studdao;
	
    public UpdateStudentAccountServlet() {
        super();
        studdao = new StudentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student updtstudent = new Student();
		
		updtstudent.setStudid(Integer.parseInt(request.getParameter("studid")));
		updtstudent.setStudname(request.getParameter("studname"));
		updtstudent.setStudpassword(request.getParameter("studpassword"));
		
		String status = studdao.updatestudentaccount(updtstudent);
		request.setAttribute("status", status);
		request.setAttribute("studentinfo", studdao.viewstudent(Integer.parseInt(request.getParameter("studid"))));
		RequestDispatcher view = request.getRequestDispatcher("Student_update_account.jsp");
		view.forward(request, response);
	}
}