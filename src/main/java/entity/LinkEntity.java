package entity;
/**
 * ��Ӧ���Ź������ı���Ϣ
 * @author Administrator
 *
 */
public class LinkEntity extends MessageEntity {

	@Override
	public String toString() {
		return "LinkEntity [Title=" + Title + ", Description=" + Description
				+ ", Url=" + Url + "]-->super==>"+super.toString();
	}
	private String Title;
	private String Description;
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	

}
