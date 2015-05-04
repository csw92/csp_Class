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

public class EntryServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{			
			String token = null;			
			String id = null;				
			boolean success = false;
			
			PersistenceManager pm = MyPersistentManager.getManager();
			Query qry = pm.newQuery(UserLoginToken.class);
			List<UserLoginToken> userLoginToken = (List<UserLoginToken>) qry.execute();
					
			//��Ű�� �н��ϴ�
			Cookie[] cookieList = req.getCookies();			
			// ��Ű�� ������� �α���ȭ������
			if (cookieList == null || cookieList.length == 0)
			{
				resp.sendRedirect("/login.html");
			}		
			// ����� ��Ű�� �ִ� ���
			else 
			{	
				for (Cookie c : cookieList) 
				{
					if (c.getName().equals("token")) 
					{
						token = c.getValue();
					}					
				}					
				
				// DataStore�� token�� �ش��ϴ� id�� ����ϴ� 
				for (UserLoginToken ult : userLoginToken)
				{
					if (ult.getToken().equals(token)) 
					{
						//���̵� ������
						id = ult.getUserAccount();						
						//��ū�� ����
						token = UUID.randomUUID().toString();
						ult.setToken(token);
						//��Ű��ū�� ����
						Cookie cookieToken = new Cookie("token", token);
						cookieToken.setMaxAge(60*60*24*30);
						resp.addCookie(cookieToken);
						success = true;	
					}				
				}							
			}					
			if (success) // id�� �� �������, ���ǿ� id�� ����ϰ� index.html�� �����մϴ�.
			{
				HttpSession session = req.getSession();
				session.setAttribute("id", id);
				resp.sendRedirect("/index.html");
			}
		}	
}




