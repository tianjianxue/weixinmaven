package entity;
/**
 * 对应威信过来的文本消息
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
