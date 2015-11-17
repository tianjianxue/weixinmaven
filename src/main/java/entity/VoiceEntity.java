package entity;


public class VoiceEntity extends MessageEntity {
	private String Format;
	private String MediaId;
	private String Recognition;
	
	public VoiceEntity() {
		super();
		super.setMsgType("voice");	
	}

	public String getFormat() {
		return Format;
	}

	public String getMediaId() {
		return MediaId;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	@Override
	public String toString() {
		return "VoiceEntity [MedidId=" + MediaId + ", Format=" + Format
				+ "]-->super==>" + super.toString();
	}

}
