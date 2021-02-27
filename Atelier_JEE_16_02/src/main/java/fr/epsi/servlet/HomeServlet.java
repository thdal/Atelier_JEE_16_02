package fr.epsi.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.entite.Roles;
import fr.epsi.entite.User;
import fr.epsi.entite.UserRole;
import fr.epsi.service.RolesService;
import fr.epsi.service.UserRoleService;
import fr.epsi.service.UserService;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

	@EJB
	private UserService userService;

	@EJB
	private RolesService rolesService;

	@EJB
	private UserRoleService userRoleService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		this.initUserList();
		// On cr�e un compte administrateur (version dev)
		if (!userService.getUserExist("root", "root")) {			
			int role = rolesService.getRole(userRoleService.getRoleId(userService.getUserId("root", "root")));
			if (role == 1) {
				session.setAttribute("Admin", true);
				session.setAttribute("SessionID", userService.getUserId("root", "root"));
			}
		}

		if (session.getAttribute("connected") != null) {
			if ((Boolean) session.getAttribute("connected")) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/PageHome.jsp").forward(req, resp);
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp").forward(req,
						resp);
			}
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp").forward(req,
					resp);
		}
	}

	/*******************************************
	 * FONCTIONS DE DEBUG *
	 *****************************************/

	public void initUserList() {
		//On fait un utilisateur root il sera admin dans l'app
		User u = new User();
		u.setMail("root");
		u.setPassword("root");
		u.setValidated(false);
		u.setStatus(true);
		userService.createUser(u);
		Long user_id = u.getId();
		// On cr�e un role
		Roles r = new Roles();
		r.setRoleDescription("Admin");
		r.setRoleNumeric(1);
		rolesService.createRole(r);
		Long role_id = r.getId();
		// On cr�e une association Role-User
		UserRole ur = new UserRole();
		ur.setRolesId(role_id);
		ur.setUserId(user_id);
		userRoleService.createUserRole(ur);
		// Faux users
		if (userService.getUsersList().size() < 3) {
			this.createUserList("Dupont@epsi.fr", "dupont01");
			this.createUserList("Alan@epsi.fr", "alan01");
			this.createUserList("Jonathan@epsi.fr", "jonathan01");
		}
	}

	public void createUserList(String mail, String password) {
		User u = new User();
		u.setMail(mail);
		u.setPassword(password);
		u.setValidated(false);
		u.setStatus(true);
		userService.createUser(u);
	}

}