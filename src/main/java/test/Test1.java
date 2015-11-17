package test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;





import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entity.TextEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.NetWork;
import util.NetWorkFile;
import util.WeChartConvert;
//import web.WeiXin;
import org.junit.Test;
public class Test1 {
	public static final String ACCESS_TOKEN="vTy_QCm-8OO36PCZFud1eaDzAAXiSUzblDQmy_0bF-mQ9knC8lowPJFSF61vS1B7LU_CrimoRgA-vTJM1jZEn8uhr3zqMTwO_VBfiCB2kF0PFIiAIAUKN";


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




}
