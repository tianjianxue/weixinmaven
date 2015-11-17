package entity;

public class EventEntity extends MessageEntity {
	private String Event;
		
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	@Override
	public String toString() {
		return "EventEntity [Event=" + Event + "] ==> Super :"+super.toString();
	}
	
	
	

}
