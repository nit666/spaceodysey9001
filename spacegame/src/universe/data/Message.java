package universe.data;

import java.util.Date;

public class Message {

	// auto message values
	public static final String CONSTRUCTION_COMPLETE = "construction_complete";
	public static final String MOVING_COMPLETE = "move_complete";
	
	long messageId;
	String title;
	String message;
	boolean read;
	Faction sender;
	Faction reciever;
	Date sentDate;
	
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public Faction getSender() {
		return sender;
	}
	public void setSender(Faction sender) {
		this.sender = sender;
	}
	public Faction getReciever() {
		return reciever;
	}
	public void setReciever(Faction reciever) {
		this.reciever = reciever;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	

}
