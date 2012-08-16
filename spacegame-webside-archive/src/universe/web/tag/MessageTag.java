package universe.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MessageTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.print("<div id=\"messages\">No new messages</div>");
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e);
		}
		
		return SKIP_BODY;
	}
}
