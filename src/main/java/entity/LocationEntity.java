package entity;
/**
 * 对应威信过来的文本消息
 * @author Administrator
 *
 */
public class LocationEntity extends MessageEntity {

	@Override
	public String toString() {
		return "LocationEntity [Location_X=" + Location_X + ", Location_Y="
				+ Location_Y + ", Scale=" + Scale + ", Label=" + Label + "]-->super==>"+super.toString();
	}
	private String Location_X;
	private String Location_Y;
	private String Scale;
	private String Label;
	
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}


}
