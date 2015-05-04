package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RetrieveTeamMemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		
		PersistenceManager pm = MyPersistentManager.getManager();
		Query qry = pm.newQuery(TeamMember.class); 
		List<TeamMember> memberList = (List<TeamMember>) qry.execute();	
		
		HttpSession session = req.getSession(false);	
			
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<table border='1'>");				
		resp.getWriter().println("<tr>");
		
		resp.getWriter().println(session.getAttribute("id") + "님 환영합니다");		
		
		resp.getWriter().println("<th> 이름 </th>");
		resp.getWriter().println("<th> 학번 </th>");
		resp.getWriter().println("<th> 전화번호 </th>");
		resp.getWriter().println("<th> 메일 </th>");
		resp.getWriter().println("<th> 카톡아이디 </th>");
		resp.getWriter().println("<th> 팀장여부</th>");
		resp.getWriter().println("<th> GitID </th>");
		resp.getWriter().println("<th> 회원삭제</th>");
		
		resp.getWriter().println("</tr>");
		
		for(TeamMember tm : memberList)
		{
			resp.getWriter().println("<tr>");
			resp.getWriter().println("<td>" +"<a href =/readTeamMemeber?key="+tm.getKey()+">"+ tm.getName() +"</a></td>");
			resp.getWriter().println("<td>" + tm.getSocialNum() +"</td>");
			resp.getWriter().println("<td>" + tm.getTel() +"</td>");
			resp.getWriter().println("<td>" + tm.getMail() +"</td>");
			resp.getWriter().println("<td>" + tm.getKatok() +"</td>");
			if(tm.isCheck() == true)
				resp.getWriter().println("<td>" + "팀장" + "</td>");
			else
				resp.getWriter().println("<td>" + "팀원" + "</td>");
			resp.getWriter().println("<td>" + tm.getGitid() +"</td>");
			resp.getWriter().println("<td>"+"<a href =/deleteMember?key="+tm.getKey()+ ">"+ "삭제"+"</a></td>");
			resp.getWriter().println("</tr>");
		}
	
		resp.getWriter().println("</table>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
	}
}

