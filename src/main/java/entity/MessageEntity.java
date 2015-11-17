package entity;

public class MessageEntity {
	private String MsgId;
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	
	
	public MessageEntity() {
		super();		
		ToUserName="";
		FromUserName="";
		CreateTime="";		
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	@Override
	public String toString() {
		return "MessageEntity [MsgId=" + MsgId + ", ToUserName=" + ToUserName + ", FromUserName=" + FromUserName
				+ ", CreateTime=" + CreateTime + ", MsgType=" + MsgType + "]";
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
