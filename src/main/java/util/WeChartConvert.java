package util;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entity.MessageEntity;


public class WeChartConvert {
	private static Properties pro=null;

	public static  String getAccess_token()
	{
		if(pro==null)
		{
			try {
				pro=new Properties();
				String pa=String.class.getResource("/").getFile()+"file.properties";
				FileInputStream input=new FileInputStream(pa);
				pro.load(input);
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//获取文件中保存的token值和上次请求时间
		long ptime=Long.parseLong(pro.getProperty("time"));
		String access_token=pro.getProperty("access_token");

		//获取当前时间
		long now=System.currentTimeMillis();
		//如果距离上次获取时间大于7000秒则重新请求微信服务器获取新的access_token
		if((now-ptime)>7000000)
		{
			System.out.print("开始获取新的access_token");
			NetWork nt=new NetWork();
			JSONObject obj=nt.sendByGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxbc0d6ff65d0f13b3&secret=c3f3cc1e2f76715bd9a7f0b27f0791d2");
			//新的access_token值
			String new_access_token=obj.getString("access_token");
			pro.setProperty("access_token",new_access_token);
			pro.setProperty("time",String.valueOf(System.currentTimeMillis()));
			FileWriter w= null;
			try {
				w = new FileWriter("E:\\Intellitestproject\\weixinmaven\\src\\main\\java\\util\\file.properties");
				pro.store(w,"ceshi");
				w.close();
				pro=null;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new_access_token;
		}
		else
		{
			return access_token;
		}

	}
	
	public static String transObjecttoXml(Object obj)
	{
		XStream xstream = new XStream(new DomDriver());
		String xml=xstream.toXML(obj);
		xml=xml.replace("<"+obj.getClass().getName()+">", "<xml>");
		xml=xml.replace("</"+obj.getClass().getName()+">", "</xml>");
		return xml;
	}
	public static String transObjecttoXml(Object obj,Class types,String alias)
	{
		XStream xstream = new XStream(new DomDriver());
		String xml=xstream.toXML(obj);
		xml=xml.replace("<"+obj.getClass().getName()+">", "<xml>");
		xml=xml.replace("</"+obj.getClass().getName()+">", "</xml>");
		xml=xml.replace("<"+types.getName()+">", "<"+alias+">");
		xml=xml.replace("</"+types.getName()+">", "</"+alias+">");
		return xml;
	}
	
	public static <T> T transxmltoObject(String xml,Class<T> types)
	{
		XStream xstream = new XStream(new DomDriver());
		String className=types.getName();
		StringBuffer buff=new StringBuffer(xml);
		if(xml.startsWith("<xml>") && xml.endsWith("</xml>") )
		{
			buff.replace(xml.length()-6,xml.length(),"</"+className+">");
			buff.replace(0,5,"<"+className+">");
			   T t=(T)xstream.fromXML(buff.toString());
			   return t;
		}
		else
			return null;				
	}
	
	public static void main(String[] args) {
		String s="<xml><ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				+ "<CreateTime>1348831860</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[this is a test]]></Content>"
				+ "<MsgId>1234567890123456</MsgId>"
				+ "</xml>";
		
		MessageEntity xml=transxmltoObject(s,MessageEntity.class);
		System.out.println(xml);
		
		
		
	}
	 public static String getElementText(String protocolXML,String elementName) {            
	        try {   
	             DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
	             DocumentBuilder builder = factory.newDocumentBuilder();   
	             Document doc = builder.parse(new InputSource(new StringReader(protocolXML)));   	  
	             Element root = doc.getDocumentElement();   	             
	             NodeList nodelist= root.getElementsByTagName(elementName);
	             if(nodelist!=null && nodelist.getLength()==1)
	            	 return nodelist.item(0).getTextContent();	               	               
	         } catch (Exception e) {   
	             e.printStackTrace();   
	         }   
	        return null;
	     }  


}
