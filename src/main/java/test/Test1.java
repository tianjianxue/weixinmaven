package test;

import java.io.File;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.NetWork;
import util.NetWorkFile;

import org.junit.Test;
import util.WeChartConvert;

public class Test1 {
	public static final String ACCESS_TOKEN=WeChartConvert.getAccess_token();
	//public static final String ACCESS_TOKEN="DuhTaIty-wSl8SWJWagpNPQNUdTxYhzyD--YSnO8O2WoIkIC8fvL-bHLSSfs61AKdKoDbRMSdsryVxVyptphA2A62BjIiMWIMn1GRie75EsMHCeAEAHCX";


	@Test
	public void testAddSuCai() {
		NetWorkFile u;
		System.out.println(ACCESS_TOKEN);
		try {
			u = new NetWorkFile(
					"https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+ACCESS_TOKEN+"&type=voice");
			u.addFileParameter("voice", new File("F:/KuGou/Go.mp3"));
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
		String meiid="y8TDx0x6C83pal-9YEe-6Ji1F_WTtOsPeLZD2yEmuB4ygU-8PYG6_hAR8kkIBb0Z";
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
		String sucai="https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token="+ACCESS_TOKEN;
		JSONObject responseText=work.sendByPost(sucai);
		System.out.println(responseText);
	}
	@Test
	public void testHuoquyonghuxinxi()
	{
		String openId="olR-Kt9YUBxx0klj-suYhCuURcZw";
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ACCESS_TOKEN+"&openid="+openId+"&lang=zh_CN";
		NetWork work=new NetWork();
		JSONObject json=work.sendByPost(url);
		System.out.println(json);
	}
	@Test
	public void userlist()
	{
		String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+ACCESS_TOKEN;
		NetWork work=new NetWork();
		JSONObject obj=work.sendByPost(url);
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
		jsonobj.element("scene","{'scene': {'scene_id': 123}}");
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
	@Test
	public  void showaccess()
	{
		String s=WeChartConvert.getAccess_token();
		System.out.println(s);
	}

    @Test
    public void shangchuanlogo()
    {
        String url="http://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+ACCESS_TOKEN+"&type=image";
        NetWorkFile net= null;
        try {
            net = new NetWorkFile(url);
            File f=new File("D://meilelelogo.jpg");
            net.addFileParameter("image",f);

            byte[] by=net.send();
            String json=new String(by);
            System.out.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void haohao()
    {
        try {
            NetWorkFile w=new NetWorkFile("http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");
            w.downLoad("D:/imgs");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Test
	public void benjidizhi()
	{
		try {
			String pa=String.class.getResource("/").getFile()+"file.properties";
			System.out.println(pa.substring(1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//产生二维码
	@Test
	public void chanshengerweima()
	{
		int changjin=2;
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+ACCESS_TOKEN;
		NetWork net=new NetWork();
		String jsonstr="{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+changjin+"}}}";
		JSONObject jsonobj=JSONObject.fromObject(jsonstr);
		String responseText= net.sendByPost(url,jsonobj);
		JSONObject resp=JSONObject.fromObject(responseText);
		String tick=resp.getString("ticket");
		System.out.println("场景:"+changjin+"的二维码图片地址"+resp.getString("url"));
		NetWorkFile f= null;
		try {
			f = new NetWorkFile("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+tick);
			f.downLoad("D:/"+changjin);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

    //测试用来创建卡卷
    @Test
    public void chuangjiankajuan()
    {
        String url="https://api.weixin.qq.com/card/create?access_token="+ACCESS_TOKEN;
        NetWork net=new NetWork();
        String baseinfo="{ " +
                "'card': {" +
                "   'card_type': 'GROUPON'," +
                "   'groupon': {" +
                "       'base_info': {" +
                "           'logo_url': " +
                "'http://mmbiz.qpic.cn/mmbi/fAuxd9pbs034VS1gLDLo9AIX6a4ARRWSRBPHSKyj4mI8GiasVIsMaicicjnjovficM34vphWD338gWHgSiaNEn9Zbgw/0'," +
                "           'brand_name':'海底捞'," +
                "           'code_type':'CODE_TYPE_TEXT'," +
                "           'title': '132元双人火锅套餐'," +
                "           'sub_title': '周末狂欢必备'," +
                "           'color': 'Color010'," +
                "           'notice': '使用时向服务员出示此券'," +
                "           'service_phone': '020-88888888'," +
                "           'description': '不可与其他优惠同享\\如需团购券发票,请在消费时向商户提出\\店内均可使用,仅限堂食'," +
                "           'date_info': {" +
                "               'type': 'DATE_TYPE_FIX_TIME_RANGE'," +
                "               'begin_timestamp': 1397577600 ," +
                "               'end_timestamp': 1422724261" +
                "           }," +
                "           'sku': {" +
                "               'quantity': 500000" +
                "           }," +
                "           'get_limit': 3," +
                "           'use_custom_code': false," +
                "           'bind_openid': false," +
                "           'can_share': true," +
                "         'can_give_friend': true," +
                "           'location_id_list' : [123, 12321, 345345]," +
                "           'center_title':'顶部居中按钮'," +
                "           'center_sub_title':'按钮下方的wording'," +
                "           'center_url':'www.qq.com'," +
                "           'custom_url_name': '立即使用'," +
                "           'custom_url': 'http://www.qq.com'," +
                "           'custom_url_sub_title': '6个汉字tips'," +
                "           'promotion_url_name': '更多优惠'," +
                "         'promotion_url': 'http://www.qq.com'," +
                "           'source': '大众点评'   " +
                "       }," +
                "       'deal_detail': '以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）:\\大锅1份 12元\\小锅2份 16元 '}" +
                " }" +
                "}";

        JSONObject jsonobj= JSONObject.fromObject(baseinfo);

        String response=net.sendByPost(url,jsonobj);
        System.out.print(response);


    }





}
