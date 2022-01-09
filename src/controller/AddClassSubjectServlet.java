package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.ClassSubject;
import dao.ClassSubjectDAO;
import dao.ClassDAO;
import dao.SubjectDAO;

@WebServlet("/AddClassSubjectServlet")
public class AddClassSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ClassSubjectDAO clasubjdao;
	private static ClassDAO classdao;
	private static SubjectDAO subjdao;
    
    public AddClassSubjectServlet() {
        super();
        clasubjdao = new ClassSubjectDAO();
        classdao = new ClassDAO();
        subjdao = new SubjectDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subjectlist[] = request.getParameterValues("subjectlist");
		String status = null;
		
		clasubjdao.deleteclasssubject(request.getParameter("classid"));
		
		for(int i = 0; i < subjectlist.length; i++) {
			ClassSubject newclasubj = new ClassSubject();
			
			newclasubj.setClassid(request.getParameter("classid"));
			newclasubj.setSubjcode(subjectlist[i]);
			
			status = clasubjdao.addclasssubject(newclasubj);
		}
		
		request.setAttribute("status", status);
		request.setAttribute("classlist", classdao.viewclasslist());
		request.setAttribute("subjectlist", subjdao.viewsubjectlist());
		RequestDispatcher view = request.getRequestDispatcher("Admin_class_subject.jsp");
		view.forward(request, response);
	}
}