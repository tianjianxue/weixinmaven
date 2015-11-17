package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Decript;
import util.WeChartConvert;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import entity.ClickAndViewEventEntity;
import entity.EventEntity;
import entity.ImageAndTextEntity;
import entity.ImageEntity;
import entity.ItemEntity;
import entity.LinkEntity;
import entity.LocationEntity;
import entity.MessageEntity;
import entity.MusicEntity;
import entity.ReplayVoiceEntity;
import entity.ReplayVoiceMediaEntity;
import entity.TextEntity;
import entity.VideoEntity;
import entity.VoiceEntity;

/**
 * Servlet implementation class WeiXin
 */
public class WeiXin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public WeiXin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private void replayTxt(String msg, MessageEntity jieshouxinxi,
			PrintWriter out) {
		TextEntity fasognxinxi = new TextEntity();
		fasognxinxi.setContent(msg);
		fasognxinxi.setToUserName(jieshouxinxi.getFromUserName());
		fasognxinxi.setFromUserName(jieshouxinxi.getToUserName());
		fasognxinxi.setCreateTime(System.currentTimeMillis()+"");
		fasognxinxi.setMsgType("text");
		System.out.println("收到用户信息："+jieshouxinxi);
		System.out.println("发送用户信息："+WeChartConvert.transObjecttoXml(fasognxinxi));
		out.print(WeChartConvert.transObjecttoXml(fasognxinxi));
	}
	private void replayVoice(String musicMediaId, MessageEntity jieshouxinxi,
			PrintWriter out) {
		ReplayVoiceEntity fasognxinxi = new ReplayVoiceEntity();	
		fasognxinxi.setMsgId(null);
		fasognxinxi.setToUserName(jieshouxinxi.getFromUserName());
		fasognxinxi.setFromUserName(jieshouxinxi.getToUserName());
		fasognxinxi.setCreateTime(System.currentTimeMillis()+"");
		ReplayVoiceMediaEntity voice=new ReplayVoiceMediaEntity();
		voice.setMediaId(musicMediaId);
		fasognxinxi.setVoice(voice);		
		System.out.println("收到："+jieshouxinxi);
		System.out.println("发送："+WeChartConvert.transObjecttoXml(fasognxinxi));		
		out.print(WeChartConvert.transObjecttoXml(fasognxinxi));
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		System.out.println("request:" + new Date());
		PrintWriter out = response.getWriter();
		/*
		 * String echostr=request.getParameter("echostr"); out.print(echostr);
		 */
		if (checkSing(request)) {
			String xml = getXml(request); // 接收到微信服务器发送过来的xml数据
			System.out.println(xml);
			String type = WeChartConvert.getElementText(xml, "MsgType"); // 接受用户发送过来的消息类型
			MessageEntity jieshouxinxi = null;
			switch (type) {
			case "text":
				jieshouxinxi = (TextEntity) WeChartConvert.transxmltoObject(
						xml, TextEntity.class);
				String content=((TextEntity)jieshouxinxi).getContent();
				if(content.equals("1"))
				{
					ImageAndTextEntity timg=new ImageAndTextEntity();
					timg.setFromUserName(jieshouxinxi.getToUserName());
					timg.setToUserName(jieshouxinxi.getFromUserName());
					
					timg.setCreateTime(System.currentTimeMillis()+"");
					List<ItemEntity> list=new ArrayList<ItemEntity>();
					for(int i=0;i<5;i++)
					{
						ItemEntity it=new ItemEntity();
						it.setDescription("Testingdfasdfasdfasdf");
						it.setPicUrl("https://www.baidu.com/img/bd_logo1.png");
						it.setUrl("http://www.baidu.com");
						it.setTitle("标题"+i);						
						list.add(it);
					}
					timg.setArticles(list);
					timg.setArticleCount(list.size());
					String replay=WeChartConvert.transObjecttoXml(timg,ItemEntity.class,"item");
					System.out.println(replay);
					out.print(replay);
					
				}else{				
					replayTxt("您给我了文本信息", jieshouxinxi, out);
				}
				break;
			case "image":
				jieshouxinxi = WeChartConvert.transxmltoObject(xml,
						ImageEntity.class);
				replayTxt("您给我了图片信息", jieshouxinxi, out);
				break;
			case "voice":
				jieshouxinxi = WeChartConvert.transxmltoObject(xml,
						VoiceEntity.class);
				replayTxt("您给我了声音信息", jieshouxinxi, out);
				break;
			case "video":
			case "shortvideo":
				jieshouxinxi = WeChartConvert.transxmltoObject(xml,
						VideoEntity.class);
				replayTxt("您给我了小饰品信息", jieshouxinxi, out);

				break;
			case "location":
				jieshouxinxi = WeChartConvert.transxmltoObject(xml,
						LocationEntity.class);
				replayTxt("您给我了地址信息", jieshouxinxi, out);
				
				break;
			case "link":
				jieshouxinxi = WeChartConvert.transxmltoObject(xml,
						LinkEntity.class);
				replayTxt("您给我了连接信息", jieshouxinxi, out);
				break;
			case "event":				
				doEvent(out,xml);				
				break;
				
			}
		} else {
			System.out.println("bad url");
		}
		out.close();

	}
	/**
	 *   专门处理事件的操作的方法
	 * @param out
	 * @param xml
	 */
	private void doEvent(PrintWriter out,String xml) {
		EventEntity event=null;
		/**
		 * 事件有很多种。比如 点击事件。比如关注/取消事件 比如扫描二维码事件。比如上报地址事件。
		 * 所以一下我会现获取对应到底是什么事件，然后在处理操作
		 */
		String type = WeChartConvert.getElementText(xml, "Event"); // 接受用户发送过来的消息类型
		String openID = WeChartConvert.getElementText(xml, "FromUserName"); // 接受用户发送过来的消息类型
		type=type.toUpperCase();
		switch(type)
		{
		case "SUBSCRIBE":
			System.out.println("用户"+openID+"关注了你的公众号 O(∩_∩)");
			break;			
		case "UNSUBSCRIBE":
			System.out.println("用户"+openID+"取消了你的公众号的关注 -_-!");
			break;			
		case "LOCATION":
			System.out.println("用户"+openID+"正在上传他的地址");
			break;			
		case "CLICK":
			System.out.println("用户"+openID+"点击了菜单上的按钮");
			event = WeChartConvert.transxmltoObject(xml,ClickAndViewEventEntity.class);
			switch(((ClickAndViewEventEntity)event).getEventKey())
			{
			case "VV1":
				//4TbdsbkzjdxDPCOzEdX4C6liLcoVpQhTi4nZnD4k-pKOEozoWJcwS16IV0x-IfNR
					replayVoice("h5EwTAZMZ0tDYPbH6Yc_FDnSkXcoWnn21SF86oI640eMR1qUEpKAUeBZdwLMyI2k", event, out);
				break;
			case "VV2":
				replayVoice("NGyqphM_mPOntfFo-KiAMl30KLqSUdq6--oltQGa7KV7sAtF6ouVTcYqXmVWNxGu", event, out);			
				break;		
			}	
			break;
		case "VIEW":
			System.out.println("用户"+openID+"刚刚跳转了页面");
			break;						
		}
		
		
		
					
	}

	

	// 获取微信服务器发送过来的消息
	public String getXml(HttpServletRequest request) throws IOException {
		InputStream is = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		return sb != null ? sb.toString() : null;
	}

	private boolean checkSing(HttpServletRequest request) {
		final String TOKEN = "wuwenjie_server";
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("timestamp");
		String timestamp = request.getParameter("nonce");
		String[] array = { nonce, timestamp, TOKEN };
		if (signature != null) {
			Arrays.sort(array);
			StringBuffer signature2buff = new StringBuffer();
			for (String string : array) {
				signature2buff.append(string);
			}
			String signature2 = Decript.SHA1(signature2buff.toString());
			return signature.equals(signature2);
		} else
			return false;
	}

}
