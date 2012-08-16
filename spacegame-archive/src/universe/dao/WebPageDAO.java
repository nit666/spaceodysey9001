package universe.dao;

import java.util.List;

import universe.data.WebPageLink;

public interface WebPageDAO {

	/**
	 * Get the template text for a web page action.
	 */
	String getPageText(String pageName);
	
	/**
	 * Get the names of the potential actions that link from another action.
	 */
	List<WebPageLink> getPageActions(String pageName);
}
