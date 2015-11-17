package util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entity.MessageEntity;


public class WeChartConvert {
	
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
