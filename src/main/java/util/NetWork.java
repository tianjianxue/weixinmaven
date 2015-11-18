package util;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import net.sf.json.JSONObject;

public class NetWork {


       
	   public  String sendByPost(String urlString,JSONObject json) {
		   return sendHttp(urlString, "POST",json); 
	    }
	   public JSONObject  sendByPost(String urlString) {
		   String json=sendHttp(urlString, "POST",null);
		   JSONObject obj=JSONObject.fromObject(json);
		   return obj;
	   }
	   public  String sendByGet(String urlString,JSONObject json) {
		   return sendHttp(urlString, "GET",json); 
	    }
	   public JSONObject  sendByGet(String urlString) {
		   String jsonstring=sendHttp(urlString, "GET",null);
		   JSONObject obj=JSONObject.fromObject(jsonstring);
		   return obj;
	   }
	   	   
	   private String sendHttp(String urlString,String method,JSONObject json) {
	        try {	   
	            URL url = new URL(urlString);
	            HttpURLConnection connection = (HttpURLConnection) url
	                    .openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod(method);	            
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);	            
	            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");	            	            
	            connection.connect();
	            if(json!=null)
	            {
	                DataOutputStream out = new DataOutputStream(connection.getOutputStream());	            	     
		            out.write(json.toString().getBytes());	            
		            out.flush();
		            out.close();	        
	            }
	            //发送请求完毕接受请求信息
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String lines;
	            StringBuffer sb = new StringBuffer("");
	            while ((lines = reader.readLine()) != null) {
	                lines = new String(lines.getBytes(), "utf-8");
	                sb.append(lines);
	            }	            
	            reader.close();	
	            connection.disconnect();
	            if(sb!=null)
	            	return sb.toString();	            	            	           
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null;
	    }
	   

	   
  
    public static void main(String[] args) {    	
    	String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=VmKDSxHc_NhNXedYEkPI-QndZW4DLKuWHC2tXwFgSIkiSD0fFd08UYhIBHpDSuxjwXe8RleMebqeo4atAOuBVZoCQrz2zI6_mjcFKuAml4wGVFdAHAZOY";
    	String sucai="https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=VmKDSxHc_NhNXedYEkPI-QndZW4DLKuWHC2tXwFgSIkiSD0fFd08UYhIBHpDSuxjwXe8RleMebqeo4atAOuBVZoCQrz2zI6_mjcFKuAml4wGVFdAHAZOY";
    
    	JSONObject data=new JSONObject();    	
    	data.element("touser", "olR-Kt7FuYGUdHVi-T0c43nM2llU");
    	data.element("msgtype", "text");
    	data.element("text",  "{'content':'中文'}" );
    	
    	NetWork work=new NetWork();

    }

	public String getOath2(String code)
	{
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbc0d6ff65d0f13b3&secret=c3f3cc1e2f76715bd9a7f0b27f0791d2&code="+code+"&grant_type=authorization_code";
		return sendByPost(url).toString();
	}
  
} 