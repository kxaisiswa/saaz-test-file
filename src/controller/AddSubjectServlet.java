package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Subject;
import dao.SubjectDAO;

@WebServlet("/AddSubjectServlet")
public class AddSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDAO subjdao;
    
    public AddSubjectServlet() {
        super();
        subjdao = new SubjectDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Subject newsubj = new Subject();

		newsubj.setSubjcode(request.getParameter("subjcode"));
		newsubj.setSubjname(request.getParameter("subjname"));
		newsubj.setSubjcredit(Integer.parseInt(request.getParameter("subjcredit")));
		newsubj.setSubjrequirement(request.getParameter("subjrequirement"));
		
		String status = subjdao.addsubject(newsubj);
		request.setAttribute("status", status);
		
		request.setAttribute("subjectlist", subjdao.viewsubjectlist());
		RequestDispatcher view = request.getRequestDispatcher("Admin_subject.jsp");
		view.forward(request, response);
	}
}