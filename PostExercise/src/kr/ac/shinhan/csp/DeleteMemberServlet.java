package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException { 
		
		String key =  req.getParameter("key");
		Long longKey = Long.parseLong(key); 		
		
		PersistenceManager pm = MyPersistentManager.getManager();
		TeamMember tm = pm.getObjectById(TeamMember.class,longKey); 		
		pm.deletePersistent(tm);
			
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		
		resp.getWriter().println("<h1>" +"�����Ϸ�" + "</h1>");
		resp.getWriter().println("<a href='retrieveTeamMember'>" + "�ڷΰ���" + "</a></br>");
		resp.getWriter().println("<a href='index.html'>" + "ó������" + "</a></br>");
	
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
