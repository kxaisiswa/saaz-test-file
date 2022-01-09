package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.ClassSubject;
import model.Class;
import dao.ClassSubjectDAO;
import dao.ClassDAO;
import dao.ProgramDAO;
import dao.SubjectDAO;

@WebServlet("/UpdateClassSubjectServlet")
public class UpdateClassSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ClassSubjectDAO clasubjdao;
	private static ClassDAO classdao;
	private static ProgramDAO progdao;
	private static SubjectDAO subjdao;
    
    public UpdateClassSubjectServlet() {
        super();
        clasubjdao = new ClassSubjectDAO();
        classdao = new ClassDAO();
        progdao = new ProgramDAO();
        subjdao = new SubjectDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Class updtclass = new Class();

		updtclass.setClassid(request.getParameter("classid"));
		updtclass.setClassname(request.getParameter("classname"));
		updtclass.setClasscapacity(Integer.parseInt(request.getParameter("classcapacity")));
		updtclass.setProgcode(request.getParameter("progcode"));
		
		String status = classdao.updateclass(updtclass);
		
		clasubjdao.deleteclasssubject(request.getParameter("classid"));
		
		if(request.getParameterValues("subjectlist") != null) {
			String subjectlist[] = request.getParameterValues("subjectlist");
			
			for(int i = 0; i < subjectlist.length; i++) {
				ClassSubject newclasubj = new ClassSubject();
				
				newclasubj.setClassid(request.getParameter("classid"));
				newclasubj.setSubjcode(subjectlist[i]);
				
				clasubjdao.addclasssubject(newclasubj);
			}
		}
		request.setAttribute("status", status);
		
		request.setAttribute("classinfo", classdao.viewclassbyid(request.getParameter("classid")));
		request.setAttribute("programlist", progdao.viewprogramlist());
		request.setAttribute("classsubjectlist", clasubjdao.viewclasssubjectlist(request.getParameter("classid")));
		request.setAttribute("subjectlist", subjdao.viewsubjectlist());
		RequestDispatcher view = request.getRequestDispatcher("Admin_update_class.jsp");
		view.forward(request, response);
	}
}