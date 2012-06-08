package universe.web.action;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public interface WebAction {

	/**
	 * unique name for this action, this is used to map the action template and linking actions in the database.
	 */
	String getName();
	
	/**
	 * Check that the action is available for use on a page, each page has a list of available actions, 
	 * and the current context of the page will determine if an individual action is relevent at a 
	 * particular time. 
	 * @param pContext TODO
	 */
	boolean isAvailable(PageContext pContext);
	
	/**
	 * Draw the link for the action to out using HTML
	 */
	void render(JspWriter out, String name) throws IOException;
	
	/**
	 * Do the action, this generally involves setting some values on the request, then setting the
	 * page name for the next action and forwarding the request. 
	 */
	void act(PageContext context, JspWriter out);
	
}
