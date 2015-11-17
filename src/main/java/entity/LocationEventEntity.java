package entity;

public class LocationEventEntity extends EventEntity{
	private String latitude;
	private String longitude;
	private String precision;

	@Override
	public String toString() {
		return "LocationEventEntity{" +
				"latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", precision='" + precision + '\'' +
				'}'+super.toString();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}
}
