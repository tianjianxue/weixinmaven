package entity;
/**
 * ��Ӧ���Ź������ı���Ϣ
 * @author Administrator
 *
 */
public class TextEntity extends MessageEntity {

	private String Content="";

	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}
	
	

	@Override
	public String toString() {
		
		return "TextEntity [Content=" + Content + "] Supe==>"+super.toString();
	}

	
	

}
