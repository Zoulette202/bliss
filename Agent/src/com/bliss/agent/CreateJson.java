package com.bliss.agent;


import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;


public class CreateJson {
	
	public InfoPC infos ;
	
	public CreateJson () {
		infos = new InfoPC();
	}
	
	public void sendInfo() {
		
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		try {
			
			String url=java.net.InetAddress.getLocalHost().getHostAddress();
			boolean statut = true;
			float ram = OsCheck.ram();
			float disque = OsCheck.disque();
			String message ="OK";
			statut = OsCheck.error(ram, "ram");
			Date d = new Date();
			long time = d.getTime()/1000;
			
		    JSONObject json = new JSONObject();
		    //json.put("idSalle", 1);
		    json.put("nomMachine", infos.userName );
		    //json.put("urlMachine", url);
		    //json.put("etat", statut);
		    json.put("ram", Math.floor(OsCheck.ram() * 100)/100);
		    //json.put("message",message );
		    //json.put("dateDernierRecut", time);
		    //json.put("disque", disque);
		    json.put("ip", infos.ip);
		    String value=json.toString();
		    StringEntity entity = new StringEntity(value);
		    HttpPost request = new HttpPost("http://127.0.0.1:8080/Bliss/rest/check");
		    request.addHeader("content-type", "application/json");
		    request.setEntity(entity);
		    HttpResponse response = httpClient.execute(request);

		}catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
	}

}