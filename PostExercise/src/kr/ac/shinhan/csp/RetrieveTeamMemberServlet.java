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
		
		String token = null;
		
		PersistenceManager pm = MyPersistentManager.getManager();
		Query qry = pm.newQuery(TeamMember.class); 
		List<TeamMember> memberList = (List<TeamMember>) qry.execute();		
		
		Query qry2 = pm.newQuery(UserLoginToken.class);
		List<UserLoginToken> userLoginToken = (List<UserLoginToken>) qry2.execute();
		Cookie[] cookieList = req.getCookies();
		
		for (Cookie c : cookieList) 
		{
			if (c.getName().equals("token")) 
			{
				token = c.getValue();
			}			
		}
		HttpSession session = req.getSession(false);
		session.getAttribute("id");
		
			
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<table border='1'>");				
		
		resp.getWriter().println(session.getAttribute("id")+"�� ȯ���մϴ�.");
		for (UserLoginToken ult : userLoginToken)
		{
			resp.getWriter().println("</br>db��ū : " + ult.getToken() + "</br>");		
		}
		resp.getWriter().println("</br>��Ű��ū : " + token + "</br>");
		resp.getWriter().println("</br>��Ȥ ��ū �ٲ�°� ���� �� �����Ƿ� �ٸ��� �������� �ٸ��ٸ� ���ΰ�ħ �ѹ�!"+ "</br>");
		resp.getWriter().println("<tr>");
		
		resp.getWriter().println("<th> �̸� </th>");
		resp.getWriter().println("<th> �й� </th>");
		resp.getWriter().println("<th> ��ȭ��ȣ </th>");
		resp.getWriter().println("<th> ���� </th>");
		resp.getWriter().println("<th> ī����̵� </th>");
		resp.getWriter().println("<th> ���忩��</th>");
		resp.getWriter().println("<th> GitID </th>");
		resp.getWriter().println("<th> ȸ������</th>");
		
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
				resp.getWriter().println("<td>" + "����" + "</td>");
			else
				resp.getWriter().println("<td>" + "����" + "</td>");
			resp.getWriter().println("<td>" + tm.getGitid() +"</td>");
			resp.getWriter().println("<td>"+"<a href =/deleteMember?key="+tm.getKey()+ ">"+ "����"+"</a></td>");
			resp.getWriter().println("</tr>");
		}
	
		resp.getWriter().println("</table>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
	}
}

