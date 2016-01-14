package com.example.me.websample;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Xml;

public class InternetTools {
    static final  String IPADDRESS="http://111.186.110.163";  //开发机ip，请根据实际情况修改
	static final String AUTHORITY=IPADDRESS+":8080/ServerForAndroid/";
	public static Bitmap getImage(String path) throws Exception{
		path=AUTHORITY+"android.png" ;
		Log.i("address",path);
		URL url=new URL(path);		
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");			
		    if(conn.getResponseCode()==200){
		    	Log.i("200", "good");
		    	InputStream inStream=conn.getInputStream();
		    	Bitmap bitmap=BitmapFactory.decodeStream(inStream);
		    	//save as a file
		    	/*
		    	 byte[] data=WebSampleTools.read(inStream);
		    	 File file=new File("icon.jpg");
		    	 FileOutputStream outputstream=new FileOutputStream(file);
		    	 outputstream.write(data);
		    	 outputstream.close();
		    	*/
		    	 
		    	return bitmap;
		    }
		
	    return null;
	}
	
	public static String	getHtml(String path) throws Exception{
				path=AUTHORITY+"GetHtmlTest.jsp" ;
		URL url=new URL(path);		
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");			
		    if(conn.getResponseCode()==200){		    	
		    	InputStream inStream=conn.getInputStream();
		    	byte[] data=WebSampleTools.read(inStream);
		    	return new String(data);
		    }		
	    return null;
	}
	
	
	public static List<News> getNews(String path) throws Exception{
		//a debug connection string
		//path=AUTHORITY+"NewsXmlServlet" ;
		path=AUTHORITY+"NewsJsonServlet" ;
		URL url=new URL(path);		
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");			
		    if(conn.getResponseCode()==200){		    	
		    	InputStream inStream=conn.getInputStream();
		    	//return parseXML(inStream);
		    	return parseJSON(inStream);
		    }		
	    return null;
	}
	
	private static List<News> parseXML(InputStream xml) throws Exception{
		List<News> newslist=null;
		News news=null;
		XmlPullParser pullParser=Xml.newPullParser();
		pullParser.setInput(xml,"UTF-8");
		int event=pullParser.getEventType();
		while(event !=XmlPullParser.END_DOCUMENT){
			switch(event){
			case XmlPullParser.START_DOCUMENT:
				newslist=new ArrayList<News>();
				break;
			
			case XmlPullParser.START_TAG:
				if("news".equals(pullParser.getName())){
					int id=new Integer(pullParser.getAttributeValue(0));
					news=new News();
					news.setId(id);
				}
				
				if("title".equals(pullParser.getName())){
					news.setTitle(pullParser.nextText());
				}
				
				if("timelength".equals(pullParser.getName())){
					news.setTimelength(new Integer(pullParser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if("news".equals(pullParser.getName())){
					newslist.add(news);
					news=null;					
				}
				break;
			}
			event=pullParser.next();
		}
		return newslist;
	}
	
	private static List<News> parseJSON(InputStream jsonStream) throws Exception{
		List<News> newslist=new ArrayList<News>();
		byte[] data=WebSampleTools.read(jsonStream);
		String json=new String(data);
		JSONArray jsonArray=new JSONArray(json);
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			int id =jsonObject.getInt("id");
			String title=jsonObject.getString("title");
			int timelength=jsonObject.getInt("timelength");
			newslist.add(new News(id,title,timelength));			
		}
		return newslist;
	}
	
	public static boolean getMethosTest(String path,String name,String password) throws Exception{
		//a debug connection string				
				path=AUTHORITY+"GetTestServlet" ;
				Map<String,String> params=new HashMap<String,String>();
				params.put("name",name);
				params.put("password", password);
				
				StringBuilder sb=new StringBuilder(path);
				sb.append("?");
				for(Map.Entry<String,String> entry:params.entrySet()){
					sb.append(entry.getKey()).append("=");
					sb.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
					sb.append("&");
				}
				sb.deleteCharAt(sb.length()-1);				
				
				
				URL url=new URL(sb.toString());		
					HttpURLConnection conn=(HttpURLConnection)url.openConnection();
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");			
				    if(conn.getResponseCode()==200){  	
				    	return true;
				    }		
			    return false;
	}
	
	public static boolean postMethosTest(String path,String name,String age) throws Exception{
		//a debug connection string				
				path=AUTHORITY+"PostTestServlet" ;
				Map<String,String> params=new HashMap<String,String>();
				params.put("name",name);
				params.put("age", age);
				
				StringBuilder sb=new StringBuilder();				
				for(Map.Entry<String,String> entry:params.entrySet()){
					sb.append(entry.getKey()).append("=");
					sb.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
					sb.append("&");
				}
				sb.deleteCharAt(sb.length()-1);				
				
				byte[] data=sb.toString().getBytes();
				
				URL url=new URL(path);		
					HttpURLConnection conn=(HttpURLConnection)url.openConnection();
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("POST");	
					conn.setDoOutput(true);
					conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length",data.length+"");
					OutputStream outStream=conn.getOutputStream();
					outStream.write(data);
					outStream.flush();
				    if(conn.getResponseCode()==200){  	
				    	return true;
				    }		
			    return false;
	}
	
}
