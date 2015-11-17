package entity;

import java.util.List;

public class ImageAndTextEntity extends MessageEntity {
	
	private int ArticleCount;
	private List<ItemEntity> Articles;
	public ImageAndTextEntity() {		
		super();
		this.setMsgType("news");
	}	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<ItemEntity> getArticles() {
		return Articles;
	}
	public void setArticles(List<ItemEntity> articles) {
		Articles = articles;
		if(articles!=null)
			this.ArticleCount=articles.size();
	}	
}
