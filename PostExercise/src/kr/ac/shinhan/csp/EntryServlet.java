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
					
			//쿠키를 읽습니다
			Cookie[] cookieList = req.getCookies();			
			// 쿠키가 없을경우 로그인화면으로
			if (cookieList == null || cookieList.length == 0)
			{
				resp.sendRedirect("/login.html");
			}		
			// 저장된 쿠키가 있는 경우
			else 
			{	
				for (Cookie c : cookieList) 
				{
					if (c.getName().equals("token")) 
					{
						token = c.getValue();
					}					
				}					
				
				// DataStore에 token에 해당하는 id를 물어봅니다 
				for (UserLoginToken ult : userLoginToken)
				{
					if (ult.getToken().equals(token)) 
					{
						//아이디 얻어오기
						id = ult.getUserAccount();						
						//토큰값 변경
						token = UUID.randomUUID().toString();
						ult.setToken(token);
						//쿠키토큰값 변경
						Cookie cookieToken = new Cookie("token", token);
						cookieToken.setMaxAge(60*60*24*30);
						resp.addCookie(cookieToken);
						success = true;	
					}				
				}							
			}					
			if (success) // id를 잘 얻었으면, 세션에 id를 등록하고 index.html을 연결합니다.
			{
				HttpSession session = req.getSession();
				session.setAttribute("id", id);
				resp.sendRedirect("/index.html");
			}
		}	
}




