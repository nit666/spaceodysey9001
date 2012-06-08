package universe.web.display;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HTMLElement {

	String name;
	String value = null;
	List<HTMLElement> children = new LinkedList<HTMLElement>();
	Map<String, String> attributes = new HashMap<String, String>();
	
	public HTMLElement(String name) {
		this.name = name;
	}
	
	public HTMLElement addValue(String value) {
		this.value = value;
		return this;
	}
	
	public HTMLElement addAttribute(String name, String value) {
		attributes.put(name, value);
		return this;
	}
	
	public HTMLElement addChild(String name) {
		HTMLElement e = new HTMLElement(name);
		children.add(e);
		
		return e;
	}
	
	public String draw() {
		StringBuilder html = new StringBuilder(); 
		html.append("<");
		html.append(name);
		for (String key : attributes.keySet()) {
			String value = attributes.get(key);
			html.append(" ");
			html.append(key).append("=\"").append(value).append("\" ");
		}
		if (children.size() > 0 || value != null) {
			html.append(">");
		} else {
			html.append(" />");
		}
		
		// draw children or draw value
		if (children.size() > 0) {
			for (HTMLElement child : children) {
				html.append(child.draw());
			}
			html.append("</");
			html.append(name);
			html.append(">");
		} else if (value != null) {
			html.append(value);
			html.append("</");
			html.append(name);
			html.append(">");
		}
		 
		
		return html.toString();
	}
	
}
