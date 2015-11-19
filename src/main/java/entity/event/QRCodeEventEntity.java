package entity.event;

import entity.event.EventEntity;

/**
 * 扫描二维码对应的事件
 * @author Administrator
 *
 */
public class QRCodeEventEntity extends EventEntity {
	private String Ticket;
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}

	@Override
	public String toString() {
		return "QRCodeEventEntity{" +
				"Ticket='" + Ticket + '\'' +
				'}';
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	

}
