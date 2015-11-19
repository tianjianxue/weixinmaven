package entity.event;

public class ClickAndViewEventEntity  extends EventEntity {
	
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	@Override
	public String toString() {
		return "ClickAndViewEventEntity [EventKey=" + EventKey + "] ==>Super "+super.toString();
	}

}
