package fr.epsi.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epsi.service.ClassementService;
import fr.epsi.service.IdeeService;
import fr.epsi.service.UserService;
import fr.epsi.service.VoteService;

@WebServlet("/classements")
public class ClassementServlet extends HttpServlet{
	
	@EJB
	private ClassementService classementService;	
	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		        throws ServletException, IOException
		    {
		 		if(req.getParameter("action").equals("list")) {
		 			Object objSessionId = req.getSession().getAttribute("SessionID");
					long sessionId =  Long.parseLong( String.valueOf(objSessionId));
		 			req.setAttribute("topsClassement", classementService.getClassementTops());
		 			req.setAttribute("buzzClassement", classementService.getClassementBuzz());
		 			req.setAttribute("brainsClassement", classementService.getClassementBrains());
			 		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/classement/ListClassement.jsp").forward(req, resp);
		 		}
		 		
		    }

}
