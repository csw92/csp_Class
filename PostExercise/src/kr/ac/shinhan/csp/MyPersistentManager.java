package kr.ac.shinhan.csp;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

public class MyPersistentManager {	

	
	public static PersistenceManager getManager()
	{
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		return pm;
	}
}
