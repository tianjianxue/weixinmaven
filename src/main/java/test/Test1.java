package test;

import java.io.File;
import java.nio.channels.NetworkChannel;

import com.sun.org.apache.xpath.internal.SourceTree;
import entity.LinkEntity;
import entity.LocationEventEntity;
import entity.MessageEntity;
import entity.TextEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.NetWork;
import util.NetWorkFile;

import org.junit.Test;
import util.WeChartConvert;

public class Test1 {
	public static final String ACCESS_TOKEN="TDlc-m3BsdIt2Cfwu6tuqE5AgTYztzpxQr5z_yN_Iso2Q7oM9msoqwGWWlZDSl-TQuIZ_KmeTXTxixrncso7f3zPMu9Z8Wj1RtJw9tv9YlEMMVgABASSB";


	@Test
	public void testAddSuCai() {
		NetWorkFile u;
		try {
			u = new NetWorkFile(
					"https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+ACCESS_TOKEN+"&type=voice");
			u.addFileParameter("voice", new File("F:/KuGou/xiaohuangren.mp3"));
			byte[] b = u.send();
			String result = new String(b);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void huoqusucai()
	{
		String meiid="QC1iZU8_4brwxmNjQGpdPflq28a1QZssbHbyogdDJZZO10Xp-Aw-5DU-ogZVqiSv";
		String sucai="https://api.weixin.qq.com/cgi-bin/media/get?access_token="+ACCESS_TOKEN+"&media_id="+meiid;
		NetWorkFile f;
		try {
			f = new NetWorkFile(sucai);
			long size=f.downLoad("D:/myFile");
			System.out.println(size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





	}


	@Test
	public void testSuCaiShu()
	{
		NetWork work=new NetWork();
		String sucai="https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token="+ACCESS_TOKEN+"";
		String responseText=work.sendByPost(sucai);
		System.out.println(responseText);
	}
	@Test
	public void testHuoquyonghuxinxi()
	{
		String openId="olR-Kt9YUBxx0klj-suYhCuURcZw";
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ACCESS_TOKEN+"&openid="+openId+"&lang=zh_CN";
		NetWork work=new NetWork();
		String json=work.sendByPost(url);
		System.out.println(json);
	}
	@Test
	public void userlist()
	{
		String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+ACCESS_TOKEN;
		NetWork work=new NetWork();
		String json=work.sendByPost(url);

		JSONObject obj=JSONObject.fromObject(json);
		JSONObject data=(JSONObject)obj.get("data") ;
		JSONArray array=(JSONArray)(data.get("openid"));
		System.out.println(array.size());
		for (Object object : array) {
			System.out.println(object);
		}
	}

		@Test
		public void sendMsg()
		{
			String msg="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbc0d6ff65d0f13b3&"
					+ "redirect_uri=http://113.106.48.190/weixin/home.jsp&"
					+ "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			String urlString="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+ACCESS_TOKEN;
			NetWork work=new NetWork();
			JSONObject obj=new JSONObject();
			obj.element("touser", "olR-Kt7FuYGUdHVi-T0c43nM2llU");
			obj.element("msgtype", "text");
			obj.element("text", "{'content':'"+msg+"'}");
			System.out.println(obj);
			String response=work.sendByPost(urlString,obj);
			System.out.println(response);
	}

	@Test
	public void testQC_image()
	{
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+ACCESS_TOKEN;
		NetWork work=new NetWork();
		//{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
		JSONObject jsonobj=new JSONObject();
		jsonobj.element("expire_seconds","604800");
		jsonobj.element("action_name","QR_SCENE");
		jsonobj.element("scene","{\"scene\": {\"scene_id\": 123}}");
		String responsetext=work.sendByGet(url,jsonobj);
		System.out.println(responsetext);
		JSONObject ticket=JSONObject.fromObject(responsetext);

		String t=ticket.getString("ticket");
		String erweima="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+t;
		NetWorkFile f= null;
		try {
			f = new NetWorkFile(erweima);
			f.downLoad("D:/file");
		} catch (Exception e) {
			e.printStackTrace();
		}



	}





}
