package entity;
/**
 * 对应威信过来的文本消息
 * @author Administrator
 *
 */
public class VideoEntity extends MessageEntity {
	private String MediaId;
	private String  ThumbMediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	@Override
	public String toString() {
		return "VideoEntity [MediaId=" + MediaId + ", ThumbMediaId=" + ThumbMediaId + "]-->super==>"+super.toString();
	}

	

}
