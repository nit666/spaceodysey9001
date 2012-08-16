package universe.web.action;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import universe.web.display.DefaultActionLink;

public abstract class AbstractWebAction implements WebAction {

	public boolean isAvailable(PageContext pContext) {
		return true;
	}

	public void render(JspWriter out, String text) throws IOException {
		out.print(new DefaultActionLink(getName(), text).draw());
	}
}
