package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Subject;
import dao.SubjectDAO;

@WebServlet("/UpdateSubjectServlet")
public class UpdateSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDAO subjdao;
    
    public UpdateSubjectServlet() {
        super();
        subjdao = new SubjectDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Subject updtsubj = new Subject();

		updtsubj.setSubjcode(request.getParameter("subjcode"));
		updtsubj.setSubjname(request.getParameter("subjname"));
		updtsubj.setSubjcredit(Integer.parseInt(request.getParameter("subjcredit")));
		updtsubj.setSubjrequirement(request.getParameter("subjrequirement"));
		
		String status = subjdao.updatesubject(updtsubj);
		request.setAttribute("status", status);
		
		request.setAttribute("subjectinfo", subjdao.viewsubjectbycode(request.getParameter("subjcode")));
		RequestDispatcher view = request.getRequestDispatcher("Admin_update_subject.jsp");
		view.forward(request, response);
	}
}