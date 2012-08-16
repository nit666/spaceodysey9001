package universe.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSessionEvent;
import javax.sql.DataSource;

import universe.ControllerContext;
import universe.action.ActionController;
import universe.dao.database.DatabaseControllerContext;
import universe.dao.database.DatasourceConnectionFactory;
import universe.helper.TimeHelper;

public class ActionLoopListener implements ServletContextListener {

	public ControllerContext getControllerContext() throws ServletException, NamingException {
		String dsString = "java:/comp/env/jdbc/univ";
		Context ic = null;
		
		try {
			ic = new InitialContext();
     		DataSource ds = (DataSource) ic.lookup(dsString);
	    
     		return new DatabaseControllerContext(new DatasourceConnectionFactory(ds));
     		
		} finally {
			if (ic != null) {
				ic.close();
			}
		}
	}

	
	public void sessionCreated(HttpSessionEvent ev) {

	}

	public void sessionDestroyed(HttpSessionEvent ev) {

	}

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
	    // This class extends Thread
	    Thread t = new Thread() {
	        // This method is called when the thread runs
	        public void run() {
	    		try {
	    			ControllerContext context = getControllerContext();
	    			
	    			ActionController controller = new ActionController();
	    			while (true) {
	    				controller.doActions(context);
	    				
	    				try {
	    					Thread.sleep((long)(TimeHelper.ONE_SECOND * 30));
	    				} catch (InterruptedException e) {
	    					// who cares right?
	    				}
	    			}
	    			
	    		} catch (ServletException e) {
	    			e.printStackTrace();
	    		} catch (NamingException e) {
	    			e.printStackTrace();
	    		}	        	
	        }
	    };

	    t.start();
	}
}
