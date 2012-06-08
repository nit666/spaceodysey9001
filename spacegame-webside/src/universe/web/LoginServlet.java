package universe.web;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import universe.ControllerContext;
import universe.dao.FactionDAO;
import universe.data.Faction;
import universe.helper.Compare;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
	private void setLoginStatus(HttpSession session, SessionConstants.LOGIN_RESULTS result) {
		session.setAttribute(SessionConstants.USER_AUTH_STATUS_SESSION_KEY, result);
		
	}

	private void backToLoginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String user = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (Compare.isEmpty(user)) {
			setLoginStatus(req.getSession(), SessionConstants.LOGIN_RESULTS.USERNAME_MISSING);
			backToLoginPage(req,resp);
			return;
		}
		if (Compare.isEmpty(password)) {
			setLoginStatus(req.getSession(), SessionConstants.LOGIN_RESULTS.PASSWORD_MISSING);
			backToLoginPage(req, resp);
			return;
		}
		
		ControllerContext ccontext;
		try {
			ccontext = SessionConstants.getControllerContext(req.getSession());
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		
		FactionDAO fd = ccontext.getFactionDAO();
		Faction f = fd.getFactionByName(user);
		
		if (f == null) {
			setLoginStatus(req.getSession(), SessionConstants.LOGIN_RESULTS.USERNAME_NOT_FOUND);
			backToLoginPage(req, resp);
			return;
		}
		
		if (Compare.areSame(f.getPassword(), password)) {
			// if they are the same then login
			req.getSession().setAttribute(SessionConstants.USER_AUTH_STATUS_SESSION_KEY, SessionConstants.LOGIN_RESULTS.AUTHENTICATED);
			req.getSession().setAttribute(SessionConstants.CURRENT_FACTION_SESSION_KEY, f);
		} else {
			setLoginStatus(req.getSession(), SessionConstants.LOGIN_RESULTS.PASSWORD_INCORRECT);
			backToLoginPage(req, resp);
			return;
		}
		
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
}
