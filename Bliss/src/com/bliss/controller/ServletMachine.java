package com.bliss.controller;

import java.util.List;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;

import com.bliss.dao.MachineDAO;
import com.bliss.dao.SalleDAO;
import com.bliss.metier.Machine;
import com.bliss.metier.Salle;

import org.jtransfo.JTransfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

     
@WebServlet(urlPatterns="/machine/*")
public class ServletMachine extends UtilHttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	public void create(){     	     
		if(this.req.getMethod().equals("POST")) {
			Machine m = new Machine(); 
			m.setName(this.getParam("name")); 
			m.setIp(this.getParam("ip"));
			m.setRam(this.getParam("ram"));
			m.setRoom(this.getParamAsInt("room"));
			MachineDAO.save(m);
			List<Salle> allRoom = SalleDAO.list();			
			this.displayView(allRoom);
		} else {
			List<Salle> allRoom = SalleDAO.list();
		    this.displayView(allRoom);
			}
		}
	

	

	}
	