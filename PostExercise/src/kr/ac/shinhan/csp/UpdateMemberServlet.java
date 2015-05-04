package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateMemberServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String key = req.getParameter("key");
		Long longKey = Long.parseLong(key);

		String name = req.getParameter("name");
		String socialNum = req.getParameter("socialNum");
		String tel = req.getParameter("tel");
		String mail = req.getParameter("mail");
		String katok = req.getParameter("katok");
		boolean check = req.getParameter("check") != null;
		String gitid = req.getParameter("gitid");

		PersistenceManager pm = MyPersistentManager.getManager();
		TeamMember tm = pm.getObjectById(TeamMember.class, longKey);

		tm.setName(name);
		tm.setSocialNum(socialNum);
		tm.setTel(tel);
		tm.setMail(mail);
		tm.setKatok(katok);		
		tm.setCheck(check);;
		tm.setGitid(gitid);
		pm.close();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");		
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>" + "정보가 수정되었습니다" + "</h1>");
		resp.getWriter().println("<table border = 1 >");
		resp.getWriter().println("<tr>"+ "<td> 이름 </td>" +"<td>" + name + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td> 학번 </td>" +"<td>" + socialNum + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td> 전화번호 </td>" +"<td>" + tel + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td> 메일주소 </td>" +"<td>" + mail + "</td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td> 카카오톡 아이디 </td>" +"<td>" + katok + "</td>" + "</tr>");
		if(check != true)
			resp.getWriter().println("<tr>"+ "<td> 팀장여부 </td> <td> 팀원 </td> </tr>");
		else
			resp.getWriter().println("<tr>"+ "<td> 팀장여부 </td> <td> 팀장 </td>" + "</tr>");
		resp.getWriter().println("<tr>"+ "<td> GitHub ID </td> <td>" + gitid + "</td> </tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("<a href= 'retrieveTeamMember'>" + "뒤로가기" + "</a> </br>");
		resp.getWriter().println("<a href= 'index.html'>" + "처음으로" + "</a> </br>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");

	}

}
