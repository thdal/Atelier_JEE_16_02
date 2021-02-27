package fr.epsi.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.entite.Idee;
import fr.epsi.entite.User;
import fr.epsi.entite.Vote;
import fr.epsi.service.IdeeService;
import fr.epsi.service.UserService;
import fr.epsi.service.VoteService;


@WebServlet("/idees")
public class IdeeServlet extends HttpServlet{
	
	@EJB
	private IdeeService ideeService;
	
	@EJB
	private VoteService voteService;
	
	@EJB
	private UserService userService;
	
	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		        throws ServletException, IOException
		    {
		 		if(req.getParameter("action").equals("list")) {
		 			Object objSessionId = req.getSession().getAttribute("SessionID");
					 long sessionId =  Long.parseLong( String.valueOf(objSessionId));
		 			req.setAttribute("IdeesList", this.getIdeesList(sessionId));		
			 		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/IdeeInnovante/ListIdee.jsp").forward(req, resp);
		 		}else if(req.getParameter("action").equals("create")) {
			 		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/IdeeInnovante/FormIdee.jsp").forward(req, resp);
		 		}
		 		
		    }
		 	
		 protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			        throws ServletException, IOException
			    {
					 if(req.getParameter("action") != null)
					 {
						 if(req.getParameter("action").equals("vote"))
						 {
							 //id de l'entit� Idee
							 Long ideeId = Long.parseLong(req.getParameter("ideeId"));
							 //l'entier top/flop
							 int vote = Integer.parseInt(req.getParameter("vote"));
							 //l'id de l'utilisateur connect�
							 Object objSessionId = req.getSession().getAttribute("SessionID");
							 long sessionId =  Long.parseLong( String.valueOf(objSessionId));
							 this.setVote(ideeId, sessionId, vote); 
					 		 req.setAttribute("IdeesList", this.getIdeesList(sessionId));		
							 this.getServletContext().getRequestDispatcher("/WEB-INF/pages/IdeeInnovante/ListIdee.jsp").forward(req, resp);
					 	 }
						 else if(req.getParameter("action").equals("create")) {
							 Object objSessionId = req.getSession().getAttribute("SessionID");
							 long sessionId =  Long.parseLong( String.valueOf(objSessionId));
							 this.create(req.getParameter("categorieIdee"), req.getParameter("descriptionIdee"), req.getParameter("imgIdee"),sessionId);	
				 			 req.setAttribute("IdeesList", this.getIdeesList(sessionId));		
				 			 this.getServletContext().getRequestDispatcher("/WEB-INF/pages/IdeeInnovante/ListIdee.jsp").forward(req, resp);
					 	 }
					 }
			   		
			    }
		 
		 public List<Idee> getIdeesList(long userId) {					 
			 	List<Idee> idees=new ArrayList<Idee>();		
			 	idees = ideeService.getIdeesList();		
				return idees;			
			}		 
		 
		 public void create(String categorie, String description, String Image, long userId) {
			 	Idee i=new Idee();
				i.setCategorie(categorie);
				i.setDescription(description);	
				i.setImage(Image);
				i.setDateEmission(new Date());
				i.setUser(userService.getUserOnId(userId)); 
				ideeService.createIdee(i);	
			}
		 
		 public void setVote(Long ideeId, Long sessionID, int vote) {
			 	Idee i = ideeService.getIdee(ideeId);
			 	User u = userService.getUserOnId(sessionID);			 	
			 	Vote v = new Vote();
			 	//vote du param (int)
			 	v.setLabelVote(vote);
			 	v.setUser(u);
			 	v.setIdee(i);
			 	voteService.createVote(v);
			}
}
