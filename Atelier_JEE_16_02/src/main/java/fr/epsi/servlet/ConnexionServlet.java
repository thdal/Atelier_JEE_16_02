package fr.epsi.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.entite.Idee;
import fr.epsi.entite.Roles;
import fr.epsi.entite.User;
import fr.epsi.entite.UserRole;
import fr.epsi.entite.Vote;
import fr.epsi.service.IdeeService;
import fr.epsi.service.RolesService;
import fr.epsi.service.UserRoleService;
import fr.epsi.service.UserService;
import fr.epsi.service.VoteService;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {

	@EJB
	private UserService userService;
	@EJB
	private RolesService rolesService;
	@EJB
	private UserRoleService userRoleService;
	@EJB
	private IdeeService ideeService;
	@EJB
	private VoteService voteService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("action") != null) {
			if (req.getParameter("action").equals("register")) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormRegister.jsp").forward(req,
						resp);
			}

		} // On Appelle la vue pour la premi�re fois
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp").forward(req,
					resp);
		}
	}

	// Pas de controle sur le format du
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if (req.getParameter("action") != null) {
			// CONNEXION
			if (req.getParameter("action").equals("connect")) {
				String mail = req.getParameter("mail") == null ? "" : req.getParameter("mail");
				String password = req.getParameter("password") == null ? "" : req.getParameter("password");
				if ((mail.isEmpty() || mail == null) || (password.isEmpty() || password == null)) {
					req.setAttribute("Erreur", true);
					this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp")
							.forward(req, resp);
				} else {
					Boolean exist = userService.getUserExist(mail, password);
					// Pas d'erreur on se connecte :
					if (exist == true) {
						HttpSession session = req.getSession();
						session.setAttribute("connected", true);
						try {
							Boolean activate = userService.getUserStatus(userService.getUserId(mail, password));
							if (!activate) {
								req.setAttribute("ErreurStatus", true);
								this.getServletContext()
										.getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp")
										.forward(req, resp);
								return;
							}
							long userId = userService.getUserId(mail, password);
							long roleId = userRoleService.getRoleId(userId);
							int role = rolesService.getRole(roleId);
							if (role == 1) {
								session.setAttribute("Admin", true);
							} else {
								session.setAttribute("Admin", false);
							}
						} catch (Exception e) {
							session.setAttribute("Admin", false);
							System.out.println("ErreurConnexionServlet82");
						}
						Long userId = userService.getUserId(mail, password);
						session.setAttribute("SessionID", userId);
						this.initIdeeList(userId);
						this.getServletContext().getRequestDispatcher("/WEB-INF/pages/PageHome.jsp").forward(req, resp);
					}
					// Identifiants incorrects
					else {
						req.setAttribute("Erreur", false);
						req.setAttribute("ErreurInvalid", true);
						this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp")
								.forward(req, resp);
					}
				}
			}
			// REGISTER
			else if (req.getParameter("action").equals("create")) {
				String mail = req.getParameter("mail") == null ? "" : req.getParameter("mail");
				String password = req.getParameter("password") == null ? "" : req.getParameter("password");
				String passwordbis = req.getParameter("passwordbis") == null ? "" : req.getParameter("passwordbis");

				if ((mail.isEmpty() || mail == null) || (password.isEmpty() || password == null)
						|| (passwordbis.isEmpty() || passwordbis == null)) {
					req.setAttribute("Erreur", true);
					this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormRegister.jsp")
							.forward(req, resp);
				}
				// Pas d'erreur on enregistre + on redirige page de log
				else {
					Matcher matcher = pattern.matcher(mail);
					if (!matcher.matches()) {
						req.setAttribute("ErreurMail", true);
						this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormRegister.jsp")
								.forward(req, resp);
					}
					if (!password.equals(passwordbis)) {
						req.setAttribute("ErreurPW", true);
						this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormRegister.jsp")
								.forward(req, resp);
					} else {
						this.create(mail, password);
						this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp")
								.forward(req, resp);
					}

				}

			}
		}
		// On renvoie sur la page de login
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/connexion/FormConnexion.jsp").forward(req,
					resp);
		}
	}	

	/*******************************************
	 * FONCTIONS DE DEBUG *
	 *****************************************/
	
	public void create(String mail, String password) {
		User u = new User();
		u.setMail(mail);
		u.setPassword(password);
		userService.createUser(u);
		Long user_id = u.getId();
		// On cr�e un role
		Roles r = new Roles();
		r.setRoleDescription("Membre");
		r.setRoleNumeric(2);
		rolesService.createRole(r);
		Long role_id = r.getId();
		// On cr�e une association Role-User
		UserRole ur = new UserRole();
		ur.setRolesId(role_id);
		ur.setUserId(user_id);
		userRoleService.createUserRole(ur);
	}

	public void initIdeeList(Long userId) {
		if (ideeService.getIdeesList().isEmpty()) {
			// Je dois r�cup�rer les images depuis internet je rencontre des difficult�s
			// pour
			// les afficher en local
			// La ligne suivante retourne un id al�atoire dans nos utilisateurs
			// (Long)users.get(new Random().nextInt(users.size())).getId()
			List<User> users = userService.getUsersList();
			this.createIdeeList("Business", "Monter une exploitation mini�re d'ast�ro�des",
					"https://www.smallbusinessact.com/wp-content/uploads/2018/12/idees-business-futur-2.jpg",
					(Long) users.get(new Random().nextInt(users.size())).getId());
			this.createIdeeList("Technologie", "Inventer un robot habile",
					"https://www.sciencesetavenir.fr/assets/img/2019/08/22/cover-r4x3w1000-5d5df9f4a7588-78c308834b92d3d3bbffc1c941221f4bcbe52d7d-jpg.jpg",
					(Long) users.get(new Random().nextInt(users.size())).getId());
			this.createIdeeList("Fun", "Ouvrir un th��tre holographique",
					"https://www.smallbusinessact.com/wp-content/uploads/2018/12/idees-business-futur-5.jpg",
					(Long) users.get(new Random().nextInt(users.size())).getId());
			this.createIdeeList("Business", "Agence de tonte de pelouse par des ch�vres",
					"https://cdn.radiofrance.fr/s3/cruiser-production/2020/10/c332e868-80c6-4edd-9724-353ef453c51c/1200x680_chevre.jpg",
					(Long) users.get(new Random().nextInt(users.size())).getId());
			this.createIdeeList("Sant�", "Inventer un m�dicament anti-�ge",
					"https://portjolio.net/wp-content/uploads/2019/07/2-2.jpg",
					(Long) users.get(new Random().nextInt(users.size())).getId());
			this.createIdeeList("Futur", "Coloniser la lune",
					"https://i.insider.com/59ff5c7c4d05ac802d8b64e6?width=1119&format=jpeg",
					(Long) users.get(new Random().nextInt(users.size())).getId());
		}
		this.setVote();

	}

	public void createIdeeList(String categorie, String description, String Image, long userId) {
		Idee i = new Idee();
		i.setCategorie(categorie);
		i.setDescription(description);
		i.setImage(Image);
		i.setDateEmission(new Date());
		i.setUser(userService.getUserOnId(userId));
		ideeService.createIdee(i);
	}

	// On init des votes top ou flop sur chaques id�es lors de l'init
	public void setVote() {
		List<Idee> idees = ideeService.getIdeesList();
		List<User> users = userService.getUsersList();
		for (Idee i : idees) {
			for (User u : users) {
				if (new Random().nextBoolean()) {
					Vote v = new Vote();
					// vote du param (int)
					v.setLabelVote((int) (Math.random() * 2 + 1));
					v.setUser(u);
					v.setIdee(i);
					voteService.createVote(v);
				}
			}

		}
	}

}