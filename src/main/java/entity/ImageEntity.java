package entity;
/**
 * ��Ӧ���Ź������ı���Ϣ
 * @author Administrator
 *
 */
public class ImageEntity extends MessageEntity {

		private String PicUrl;
		private String MediaId;
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		@Override
		public String toString() {
			return "ImageEntity [PicUrl=" + PicUrl + ", MediaId=" + MediaId + "]:-->super==>"+super.toString();
		}

}
