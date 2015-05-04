package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		boolean check = true;
				
		PersistenceManager pm = MyPersistentManager.getManager();
		Query qry = pm.newQuery(UserAccount.class);
		List<UserAccount> userList = (List<UserAccount>) qry.execute();

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		for (UserAccount u : userList) {
			if (id.equals(u.getUserID())) {
				check = false;
			}
		}
		if (check != true) {
			resp.getWriter().println("<html>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("<h1>이미 사용중인 아이디 입니다.<h1>");
			resp.getWriter().println("<a href='signup.html'>뒤로가기</a>");
			resp.getWriter().println("</html>");
			resp.getWriter().println("</body>");
			
		} 
		else
		{
			UserAccount ua = new UserAccount(id, password, name);
			pm.makePersistent(ua);
			resp.sendRedirect("login.html");
		}

	}
}
