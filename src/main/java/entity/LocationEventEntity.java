package entity;

public class LocationEventEntity extends EventEntity{
	private String Latitude;
	private String Longitude;
	private String Precision;
	
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	@Override
	public String toString() {
		return "LocationEventEntity [Latitude=" + Latitude + ", Longitude=" + Longitude + ", Precision=" + Precision
				+ "] ==> super"+super.toString();
	}
	
	
	

}
