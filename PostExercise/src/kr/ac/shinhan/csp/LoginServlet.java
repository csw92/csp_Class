package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");					
		boolean remember = req.getParameter("remember") !=null;
		boolean success = false;
		
		HttpSession session = req.getSession();
		session.setAttribute("id",id);	
		
		PersistenceManager pm = MyPersistentManager.getManager();				
		Query qry = pm.newQuery(UserAccount.class);
		qry.setFilter("userID == idParam");
		qry.declareParameters("String idParam");

		List<UserAccount> userAccount = (List<UserAccount>) qry.execute(id);

		if (userAccount.size() == 0) {
			success = false;
		}

		else if (userAccount.get(0).getPassword().equals(password)) {
			success = true;
			if (remember == true) // 체크박스 체크 시 쿠키생성 및 토큰생성
			{
				Cookie cookieId = new Cookie("id", id);
				Cookie cookiePass = new Cookie("password", password);
				
				String token = UUID.randomUUID().toString();						
				Cookie cookieToken = new Cookie("token", token);
				
				cookieId.setMaxAge(60*60*24*30);
				cookiePass.setMaxAge(60*60*24*30);
				cookieToken.setMaxAge(60*60*24*30);
				String expireDate = Integer.toString(cookieId.getMaxAge());	
				
				resp.addCookie(cookieId);
				resp.addCookie(cookiePass);
				resp.addCookie(cookieToken);
				
				UserLoginToken ult = new UserLoginToken(token, id, expireDate);
				pm.makePersistent(ult);
			}
		}

		else {
			success = false;
		}

		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");

		if (!success) {
			resp.getWriter().println("Fail to login");
			resp.getWriter().println("<a href='login.html'>Login Again</a>");
		}

		if (success) {
			resp.sendRedirect("/index.html");
		}

		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");

	}
}
