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

public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		PersistenceManager pm = MyPersistentManager.getManager();
		Query qry = pm.newQuery(UserLoginToken.class);
		List<UserLoginToken> userLoginToken = (List<UserLoginToken>) qry.execute();
		
		boolean success = false;
		
		Cookie[] cookieList = req.getCookies();

		for (Cookie c : cookieList)
		{			
			if (c.getName().equals("token"))
			{
				String token = c.getValue();
				for(UserLoginToken ult : userLoginToken)
				{
					if (ult.getToken().equals(token))
					{
						//토큰이 같을 때 해당항목 지움 그리고 트루 줌 
						pm.deletePersistent(ult);
						success = true;
					}
				}
				c.setValue(null);
				c.setMaxAge(0);
				resp.addCookie(c);				
			}
			if(c.getName().equals("id"))
			{
				c.setValue(null);
				c.setMaxAge(0);
				resp.addCookie(c);				
			}
			if(c.getName().equals("password"))
			{
				c.setValue(null);
				c.setMaxAge(0);
				resp.addCookie(c);				
			}
			
		}
		
		if (success) // 로그아웃되면 로그인페이지 보여줌
		{
			resp.sendRedirect("/login.html");
		}
		
	}
}
