package kr.ac.shinhan.csp;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class PostExerciseServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getParameter("name");
		String socialNum = req.getParameter("socialNum");
		String tel = req.getParameter("tel");
		String mail = req.getParameter("mail");
		String katok = req.getParameter("katok");
		String gitid = req.getParameter("gitid");		
		boolean check = req.getParameter("leader") !=null;
		
		//database insert
		TeamMember tm = new TeamMember(name,socialNum,tel,mail,katok,check,gitid);
		PersistenceManager pm = MyPersistentManager.getManager();
		pm.makePersistent(tm);	
		
		
		//response
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
	
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("�̸� : " + name +"</br> ");
		resp.getWriter().println("�й� : " + socialNum +"</br>");
		resp.getWriter().println("��ȭ��ȣ : " + tel +"</br>");
		resp.getWriter().println("�����ּ� : " + mail +"</br>");
		resp.getWriter().println("īī���� ���̵� : " + katok +"</br>");
		if(check != true)
			resp.getWriter().println("����ƴ�" +"<br>");
			else
			resp.getWriter().println("����"  +"<br>");
		resp.getWriter().println("GitHub ID : " + gitid +"</br>");
		
		resp.getWriter().println("\n�� ��ϵǾ����ϴ�."+"</br>");
		resp.getWriter().println("<a href='index.html'>ó������</a>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
