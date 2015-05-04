package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadTeamMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		
		String key = req.getParameter("key");
		Long longKey = Long.parseLong(key);		
		
		PersistenceManager pm = MyPersistentManager.getManager();
		TeamMember tm = pm.getObjectById(TeamMember.class,longKey);
		
		String name = tm.getName();
		String socialNum = tm.getSocialNum();
		String tel = tm.getTel();
		String mail = tm.getMail();
		String katok = tm.getKatok();
		boolean check = tm.isCheck();
		String gitid = tm.getGitid();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		
		resp.getWriter().println("<form method =" + "'POST'" + "action = /updateMember?key="+ tm.getKey() +">");
		resp.getWriter().println("<table border= 1 >");
		resp.getWriter().println("<tr>"+"<td> 이름 </td>"
										+"<td> <input type = 'text' name = 'name' value=" + name + "> </td> </tr>");
		resp.getWriter().println("<tr>"+"<td> 학번 </td>"
										+"<td> <input type = 'text' name = 'socialNum' value=" +socialNum+ "> </td> </tr>");
		resp.getWriter().println("<tr>"+"<td> 전화번호 </td>"
										+"<td> <input type = 'text' name = 'tel' value=" + tel + "> </td> </tr>");
		resp.getWriter().println("<tr>"+"<td> 메일주소 </td>"
										+"<td> <input type = 'text' name = 'mail' value=" + mail + "> </td> </tr>");
		resp.getWriter().println("<tr>"+"<td> 카카오톡 아이디 </td>"
										+"<td> <input type = 'text' name = 'katok' value= "+ katok +  "> </td> </tr>");
		resp.getWriter().println("<tr>"+"<td> 팀장여부 </td>"
										+"<td> <input type='checkbox' name='check' value='true'> </td> </tr>");
		resp.getWriter().println("<tr>"+"<td> GitHub Id </td>"
										+"<td> <input type = 'text' name= 'gitid' value= "+ gitid +  "> </td> </tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("<input type="+"'submit'" + "value="+"'update'"+  ">");
		resp.getWriter().println("</form>");		
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
				
	}
}
