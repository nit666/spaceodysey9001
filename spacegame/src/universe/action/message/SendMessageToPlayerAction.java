package universe.action.message;

import universe.action.DatabaseAction;
import universe.action.ActionContext;
import universe.action.ActionException;
import universe.dao.KnowsAboutDAO;
import universe.data.ActionData;
import universe.data.Faction;
import universe.rule.faction.FactionKnowsAboutRule;

/**
 * this class will send a message from another player to a player
 */
public class SendMessageToPlayerAction extends DatabaseAction {


	@Override
	public void initiateRules(ActionContext context) {
		Faction reciever = (Faction) context.getParameter("recieving-faction", Faction.class);
		addRule(new FactionKnowsAboutRule(reciever, KnowsAboutDAO.KnowsAboutTypes.FACTION));
	}
	
	@Override
	public ActionStatus doAction(ActionContext context) throws ActionException {
		
		Faction sender = context.getCurrentFaction();
		Faction reciever = (Faction) context.getParameter("recieving-faction", Faction.class);
		if (reciever == null) {
			throw new ActionException("The reciever is not set for message");
		}
		String message = context.getParameter("message-text");
		if (message == null || "".equals(message.trim())) {
			throw new ActionException("The message cannot be blank");
		}
		// make sure the sender actually knows the reciever
		boolean knowsTarget = context.getKnowsAboutDAO().getFactionKnowsAbout(sender, reciever.getFactionId(), KnowsAboutDAO.KnowsAboutTypes.FACTION);
		if (!knowsTarget) {
			throw new ActionException("Trying to send a message to an illegal faction");
		}
		
		context.getMessageDAO().sendMessageToFaction(sender, reciever, message);
		
		return ActionStatus.COMPLETE;
	}

	@Override
	public ActionData getActionData(Faction faction, Object... params) {
		ensureParamLength(2, params);
		ActionData data = new ActionData();
		data.setAction(this);
		data.setFaction(faction);
		data.addParameter("recieving-faction", params[0], Faction.class);
		data.addParameter("message-text", params[1], String.class);
		return data;
	}

}
