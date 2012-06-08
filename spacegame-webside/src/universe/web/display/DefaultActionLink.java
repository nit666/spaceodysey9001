package universe.web.display;

public class DefaultActionLink extends HTMLElement {

	public DefaultActionLink(String pageName, String label, String ... extraparams) {
		super("A");
		String link = "main.jsp?action=" + pageName; 
			
		for (String param : extraparams) {
			link += "&" + param;	
		}
		
		addAttribute("class", "action-link");
		addAttribute("id", pageName);
		addAttribute("href", link);

		addValue(label);
	}
}
