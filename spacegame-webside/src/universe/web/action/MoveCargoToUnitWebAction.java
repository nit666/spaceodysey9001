package universe.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import universe.data.Faction;
import universe.data.Unit;
import universe.data.unit.CargoHold;
import universe.web.SessionConstants;

public class MoveCargoToUnitWebAction extends AbstractWebAction {

	public void act(PageContext context, JspWriter out) {
		
		try {
			out.print("<h1>Not yet implemented!</h1>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return "MoveCargo";
	}
	
	public boolean isAvailable(PageContext pContext) {
		Unit u = SessionConstants.getCurrentSelectedUnit(pContext.getSession());
		Faction f = SessionConstants.getCurrentFaction(pContext.getSession());
		
		if (u instanceof CargoHold) {
			// need to make sure there is another cargo hold available
			List<Unit> units = SessionConstants.getCurrentViewedUnits(pContext.getSession());
			
			for (Unit otherUnit : units) {
				if (otherUnit.getOwner().getFactionId() == f.getFactionId()) {
					if (otherUnit.getUnitId() != u.getUnitId()) {
						if (otherUnit instanceof CargoHold) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}


}
