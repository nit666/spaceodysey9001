package universe.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import universe.data.Faction;
import universe.web.SessionConstants;
import universe.web.display.HTMLElement;

public class FactionInfoTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		
		Faction theFaction = SessionConstants.getCurrentFaction(pageContext.getSession());
		
		HTMLElement e = new HTMLElement("DIV");
		e.addAttribute("ID", "faction-info")
		 .addAttribute("class", "section")
		 .addChild("SPAN")
			.addAttribute("class", "label")
			.addValue("Faction Name: ");
		e.addChild("SPAN")
			.addAttribute("class", "value")
			.addValue(theFaction.getName());

		try {
			out.print(e.draw());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return SKIP_BODY;
	}
}
