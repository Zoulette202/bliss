package com.bliss.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jtransfo.JTransfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bliss.dao.MachineDAO;
import com.bliss.metier.Machine;

@RestController
@ApplicationPath("/rest")
public class restApp extends Application{
	static JTransfo transformateur;
	static JSONObject test;
	
	@RequestMapping(value ="/check/{json}", method = RequestMethod.POST)
	public static void getMachine(@RequestParam("json") JSONObject object) {
		Machine m = new Machine();
		try {
			m.setIp((String) object.get("ip"));
			m.setName((String) object.get("nomMachine"));
			m.setRam((String) object.get("ram"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(m);

		MachineDAO.save(m);
	}
	
	public static void main(String[] args) {
		
	}
}
